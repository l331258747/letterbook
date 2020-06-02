package com.csxs.core.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.csxs.core.net.RetryWithDelay;
import com.csxs.core.net.exception.HttpResponseFunc;
import com.csxs.core.net.exception.ServerResponseFunc;
import com.csxs.core.net.result.Result;
import com.csxs.core.utils.Preconditions;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.uber.autodispose.AutoDispose.autoDisposable;


/**
 * Created by SenYe on 2018/1/24.
 */
public abstract class BasePresenter<M extends IModel,V extends IBaseView> implements IPresenter {

    //在传统的设计中，通常由调用者来创建被调用者的实例
    // （一般的，我们通过 new 的方式创建。然而依赖注入的方式，创建被调用者不再由调用者创建实例，
    // 创建被调用者的实例对象的工作由 IOC 容器 来完成，然后注入到调用者。因此也被称为 依赖注入。
    @Inject
    protected M mModel;

    //Reference是java中的引用类，它用来给普通对像进行包装，从而在JVM在GC时，按照引用类型的不同，在回收时采用不同的逻辑。
    public Reference<V> mReferenceView;
    public Reference<Context> mReferenceContext;

    public V views;

    public BasePresenter(){
    }


    public V getView() {
        return isAttach() ? mReferenceView.get() : null;
    }

    public void attachView(V v) {
        Preconditions.checkNotNull(v,"view reference is null");
        mReferenceView = new WeakReference<V>(v); //建立关联
        views = mReferenceView.get();
    }

    public void setContext(Context context) {
        mReferenceContext = new WeakReference<Context>(context); //建立关联
    }

    public Context getContext() {
        return isAttachContext() ? mReferenceContext.get() : null;
    }

    protected boolean isAttach() {
        return mReferenceView != null &&
                mReferenceView.get() != null;
    }

    protected boolean isAttachContext() {
        return mReferenceContext != null &&
                mReferenceContext.get() != null;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        if (isAttach()) {
            mReferenceView.clear();
            mReferenceView = null;
            views=null;
        }

        if (isAttachContext()){
            mReferenceContext.clear();
            mReferenceContext = null;
        }
    }


    /**
     * 服务器 返回 code 200 是 data 为null的情况下 需要 自己处理
     * @param lifecycle
     * @param observable
     * @param callback
     * @param <T>
     */
    public  <T> void requestNet(Lifecycle lifecycle, Observable<T> observable, Observer<T> callback){
        observable.onErrorResumeNext(new HttpResponseFunc<T>())
                .retryWhen(new RetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                .subscribe(callback);
    }

    /**
     * 服务器返回code 200 data 不为null
     * @param lifecycle
     * @param observable
     * @param callback
     * @param <T>
     */

    public  <T> void requestResultNet(Lifecycle lifecycle, Observable<Result<T>> observable, Observer<T> callback){
        observable.map(new ServerResponseFunc<T>())
                .onErrorResumeNext(new HttpResponseFunc<T>())
                .retryWhen(new RetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                .subscribe(callback);
    }

    //服务器返回code 200 data 不为null 的情况下
    public  <T> void requestStringResultNet(Lifecycle lifecycle, Observable<String> observable, Observer<String> callback){
        observable.onErrorResumeNext(new HttpResponseFunc<String>())
                .retryWhen(new RetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                .subscribe(callback);
    }

    //服务器返回code 200 data 不为null 的情况下
    public Observable<String> requestStringNet(Lifecycle lifecycle, Observable<String> observable, Observer<String> callback){
       return observable.onErrorResumeNext(new HttpResponseFunc<String>())
               .retryWhen(new RetryWithDelay())
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一处理 错误
     * @param lifecycle
     * @param observable
     * @param callback
     * @param <T>
     */

    public  <T> void requestHttpResult(Lifecycle lifecycle, Observable<T> observable, Observer<T> callback){
        observable

                . retryWhen(new RetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                .subscribe(callback);
    }
}
