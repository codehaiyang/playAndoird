package com.playa.aiy.playandroid.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    Context mContext;

    public MyView(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * 重写draw方法
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建一个画笔
        Paint paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置画笔颜色
        paint.setColor(Color.RED);
        //设置填充样式
        paint.setStyle(Paint.Style.FILL);
        //设置画笔宽度
        paint.setStrokeWidth(5);
        //设置阴影
        paint.setShadowLayer(10,15,15,Color.GREEN);
        //画布背景色
        canvas.drawARGB(255,255,255,255);
        //画圆
        canvas.drawCircle(190,200,150,paint);
    }
}
