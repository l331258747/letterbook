package com.csxs.core.net.result;

import android.content.Context;
import android.util.Log;


import com.csxs.core.net.exception.ErrorCode;
import com.csxs.core.net.exception.ExceptionHandle;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by SenYe on 2018/1/26.
 */

public abstract class ResultObserver<T> extends DisposableObserver<T> {
    private WeakReference<Context> mView;
    private boolean isShow = false;
    private Disposable d;

    public ResultObserver() {
    }

   // private ProgressHandler mProgressHandler;

    public ResultObserver(Context context) {
        mView = new WeakReference<>(context);
    }

    public ResultObserver(Context context, boolean isShow) {
        this.isShow = isShow;
        mView = new WeakReference<>(context);
        if (isShow) {
            // mProgressDialogHandler = new ProgressDialogHandler(context, this);
            //  CustomProgressDialog.show(mView.get(), "加载中...", false);
        //    mProgressHandler = new ProgressHandler(mView.get(), "加载中");
        }
    }


//    @Override
//    public void onSubscribe(Disposable d) {
//        this.d = d;
//
//    }


    @Override
    protected void onStart() {
        super.onStart();
        //mProgressHandler.initProgressDialog();
       showProgressDialog();
    }


    @Override
    public void onNext(T result) {
        if(!isDisposed()){
            try {
                handlerResult(result);
            }catch (Throwable e){
                Exceptions.throwIfFatal(e);
                dispose();
                onError(e);
            }
        }


    }

    @Override
    public void onError(Throwable e) {
        if(!isDisposed()){
            try {
                handlerError(((ExceptionHandle.ResponeThrowable) e).code, ((ExceptionHandle.ResponeThrowable) e).message);
            }catch (Throwable t){
                RxJavaPlugins.onError(new CompositeException(e, t));
            }
        }else{
            RxJavaPlugins.onError(e);
        }

    }


    @Override
    public void onComplete() {
        if (isShow) {
            //CustomProgressDialog.dailogDismiss();
            dismissProgressDialog();
            mView.clear();
        }

    }


    private void showProgressDialog() {
//        if (mProgressHandler != null) {
//            mProgressHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
//        }
    }

    private void dismissProgressDialog() {
//        if (mProgressHandler != null) {
//            mProgressHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
//           // mProgressHandler.removeCallbacksAndMessages(null);
//           // mProgressHandler = null;
//
//        }
    }

    /**
     * 返回正常数据
     */
    public abstract void handlerResult(T t);

    /**
     * 返回业务错误
     */
    public abstract void handlerError(int code, String msg);

}