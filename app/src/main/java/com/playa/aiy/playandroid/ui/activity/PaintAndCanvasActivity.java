package com.playa.aiy.playandroid.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.playa.aiy.playandroid.R;
import com.playa.aiy.playandroid.base.BaseActivity;
import com.playa.aiy.playandroid.utils.FucUtil;
import com.playa.aiy.playandroid.utils.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;

public class PaintAndCanvasActivity extends BaseActivity {

    private static final String TAG = "PaintAndCanvasActivity";

    @BindView(R.id.bt_test)
    Button button;
    @BindView(R.id.et_result)
    EditText mEtresult;

    private Toast mToast;
    // 语音听写UI
    private RecognizerDialog mIatDialog;
    private SpeechRecognizer mIat;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    Handler han = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x001) {
                executeStream();
            }
        }
    };
    int ret = 0; // 函数调用返回值
    //执行音频流识别操作
    private void executeStream() {
        //mResultText.setText(null);// 清空显示内容
        mIatResults.clear();
        // 设置参数
        setParam();
        // 设置音频来源为外部文件
        mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
        // 也可以像以下这样直接设置音频文件路径识别（要求设置文件在sdcard上的全路径）：
        // mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-2");
        //mIat.setParameter(SpeechConstant.ASR_SOURCE_PATH, "sdcard/XXX/XXX.pcm");
        ret = mIat.startListening(mRecognizerListener);
        if (ret != ErrorCode.SUCCESS) {
            showTip("识别失败,错误码：" + ret);
        } else {
            byte[] audioData = FucUtil.readAudioFile(PaintAndCanvasActivity.this, "iattest.wav");

            if (null != audioData) {
                showTip(getString(R.string.text_begin_recognizer));
                // 一次（也可以分多次）写入音频文件数据，数据格式必须是采样率为8KHz或16KHz（本地识别只支持16K采样率，云端都支持），
                // 位长16bit，单声道的wav或者pcm
                // 写入8KHz采样的音频时，必须先调用setParameter(SpeechConstant.SAMPLE_RATE, "8000")设置正确的采样率
                // 注：当音频过长，静音部分时长超过VAD_EOS将导致静音后面部分不能识别。
                // 音频切分方法：FucUtil.splitBuffer(byte[] buffer,int length,int spsize);
                mIat.writeAudio(audioData, 0, audioData.length);

                mIat.stopListening();
            } else {
                mIat.cancel();
                showTip("读取音频流失败");
            }
        }
    }


    @Override
    protected void initData() {
        // 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(PaintAndCanvasActivity.this,mInitListener);
        mIat = SpeechRecognizer.createRecognizer(PaintAndCanvasActivity.this, mInitListener);
    }

    @Override
    protected void initView() {
        //mRoot.addView(new MyView(PaintAndCanvasActivity.this));
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setParam();

            }
        });
    }

    private void setParam() {
            //初始化识别无UI识别对象
    //使用SpeechRecognizer对象，可根据回调消息自定义界面；
            mIat = SpeechRecognizer.createRecognizer(PaintAndCanvasActivity.this, mInitListener);

    //设置语法ID和 SUBJECT 为空，以免因之前有语法调用而设置了此参数；或直接清空所有参数，具体可参考 DEMO 的示例。
            mIat.setParameter( SpeechConstant.CLOUD_GRAMMAR, null );
            mIat.setParameter( SpeechConstant.SUBJECT, null );
    //设置返回结果格式，目前支持json,xml以及plain 三种格式，其中plain为纯听写文本内容
            mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");
    //此处engineType为“cloud”
            mIat.setParameter( SpeechConstant.ENGINE_TYPE, "cloud" );
    //设置语音输入语言，zh_cn为简体中文
            mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
    //设置结果返回语言
            mIat.setParameter(SpeechConstant.ACCENT, "mandarin");
    // 设置语音前端点:静音超时时间，单位ms，即用户多长时间不说话则当做超时处理
    //取值范围{1000～10000}
            mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
    //设置语音后端点:后端点静音检测时间，单位ms，即用户停止说话多长时间内即认为不再输入，
    //自动停止录音，范围{0~10000}
            mIat.setParameter(SpeechConstant.VAD_EOS, "1000");
    //设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
            mIat.setParameter(SpeechConstant.ASR_PTT,"1");

    //开始识别，并设置监听器
            mIat.startListening(mRecognizerListener);
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            }
        }
    };

    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.paint_and_canvas_activity;
    }

    private boolean mTranslateEnable = false;
    private boolean cyclic = false;//音频流识别是否循环调用
    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            showTip("开始说话");
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            showTip("结束说话");
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d(TAG, results.getResultString());
            if( mTranslateEnable ){
                printTransResult( results );
            }else{
                printResult(results);
            }

            if (isLast & cyclic) {
                // TODO 最后的结果
                Message message = Message.obtain();
                message.what = 0x001;
                han.sendMessageDelayed(message,100);
            }
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            showTip("当前正在说话，音量大小：" + volume);
            Log.d(TAG, "返回音频数据："+data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    private void printTransResult (RecognizerResult results) {
        String trans  = JsonParser.parseTransResult(results.getResultString(),"dst");
        String oris = JsonParser.parseTransResult(results.getResultString(),"src");

        if( TextUtils.isEmpty(trans)||TextUtils.isEmpty(oris) ){
            showTip( "解析结果失败，请确认是否已开通翻译功能。" );
        }else{
            Log.d(TAG, "printTransResult: " +  "原始语言:\n"+oris+"\n目标语言:\n"+trans );
            mEtresult.setText( "原始语言:\n"+oris+"\n目标语言:\n"+trans );
        }

    }
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        // 读取json结果中的sn字段
        try {
            JSONObject resultJson = new JSONObject(results.getResultString());
            sn = resultJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn, text);

        StringBuffer resultBuffer = new StringBuffer();
        for (String key : mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }
        Log.d(TAG, "printResult: " + resultBuffer.toString());
        mEtresult.setText(resultBuffer.toString());
        mEtresult.setSelection(mEtresult.length());
    }

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            if( mTranslateEnable ){
                printTransResult( results );
            }else{
                printResult(results);
            }

        }

        /**
         * 识别回调错误.
         */
        @Override
        public void onError(SpeechError error) {
            if(mTranslateEnable && error.getErrorCode() == 14002) {
                showTip( error.getPlainDescription(true)+"\n请确认是否已开通翻译功能" );
            } else {
                showTip(error.getPlainDescription(true));
            }
        }

    };
}
