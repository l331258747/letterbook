package com.csxs.core.net.result;

import android.content.Context;
import android.os.Handler;
import android.os.Message;


/**
 * Dialog的进度控制
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
         //   CustomProgressDialog.show(context, "加载中...", false,mProgressCancelListener);
    }

    private void dismissProgressDialog() {
//            CustomProgressDialog.dailogDismiss();
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                 initProgressDialog();
              //  DialogUtil.getDialogUtil(context).show();
                break;
            case DISMISS_PROGRESS_DIALOG:
//                 Observable.timer(1000, TimeUnit.MILLISECONDS).
//                        subscribeOn(AndroidSchedulers.mainThread()).
//                        subscribe(new Observer<Long>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(Long aLong) {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
                dismissProgressDialog();

                //DialogUtil.getDialogUtil(context).dismissDialog();
                break;
        }
    }


}
