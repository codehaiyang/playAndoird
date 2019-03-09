package com.playa.aiy.playandroid.presenter;

import com.playa.aiy.playandroid.base.BasePresenter;
import com.playa.aiy.playandroid.contract.SystemContract;
import com.playa.aiy.playandroid.data.SystemBean;
import com.playa.aiy.playandroid.net.ApiService;
import com.playa.aiy.playandroid.net.ApiStore;
import com.playa.aiy.playandroid.net.response.BaseResp;
import com.playa.aiy.playandroid.utils.ConstantUtil;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SystemPagePresenter extends BasePresenter<SystemContract.View> implements SystemContract.Pre{

    private SystemContract.View view;

    public SystemPagePresenter(SystemContract.View view){
        this.view = view;
    }

    @Override
    public void getSystemList() {
        ApiStore.createApi(ApiService.class)
                .getSystemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<List<SystemBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResp<List<SystemBean>> listBaseResp) {
                        if (ConstantUtil.REQUEST_ERROR == listBaseResp.errorCode){
                            view.getSystemListErr(listBaseResp.errorMsg);
                        }else if(ConstantUtil.REQUEST_SUCCESS == listBaseResp.errorCode){
                            view.getSystemListOk(listBaseResp.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
