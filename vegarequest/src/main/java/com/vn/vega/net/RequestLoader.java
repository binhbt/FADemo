package com.vn.vega.net;



import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by binhbt on 6/20/2016.
 */
public class RequestLoader {
    public interface OnCancelRequest{
        public void onCancel();
    }
    public interface CallBack<T>{
        public void onStart();
        public void onError(Throwable t);
        public void onFinish(T result);
    }
    static volatile RequestLoader singleton = null;
    private Map<Object, CompositeSubscription> mapRequest = new HashMap<>();
    private Map<Object, CompositeSubscription> containerRequest = new HashMap<>();
    public static RequestLoader getDefault() {
        if (singleton == null) {
            synchronized (RequestLoader.class) {
                if (singleton == null) {
                    singleton = new RequestLoader();
                }
            }
        }
        return singleton;
    }
    public void add(Subscription subcribtion, Object tag){
        CompositeSubscription compositeSubscription = mapRequest.get(tag);
        if (compositeSubscription == null){
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subcribtion);
        mapRequest.put(tag, compositeSubscription);
    }

    public void cancelByTag(Object tag){
        CompositeSubscription compositeSubscription = mapRequest.get(tag);
        if (compositeSubscription != null){
            compositeSubscription.unsubscribe();
            mapRequest.remove(tag);
        }else{
            //ignore it. i don't care
            //throw new IllegalArgumentException("Request not found. Can not cancel");
        }
    }
    public void addToContainerGroup(Subscription subcribtion, Object container){
        CompositeSubscription compositeSubscription = containerRequest.get(container);
        if (compositeSubscription == null){
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subcribtion);
        containerRequest.put(container, compositeSubscription);
    }
    public void cancelAll(Object container){
        CompositeSubscription compositeSubscription = containerRequest.get(container);
        if (compositeSubscription != null){
            compositeSubscription.unsubscribe();
            containerRequest.remove(container);
        }else{
            //ignore it. i don't care
            //throw new IllegalArgumentException("Request not found. Can not cancel");
        }
    }
    public static final class Builder {
        public static final String DEFAULT_TAG = "VEGA_REQUEST";
        private OnCancelRequest cancel;
        private CallBack callback;
        private Object container;
        private Observable<?> observable;
        private Object tag = DEFAULT_TAG;
        public Builder cancel(OnCancelRequest cancel){
            this.cancel = cancel;
            return this;
        }
        public Builder callback(CallBack callback){
            this.callback = callback;
            return this;
        }
        public <C> Builder container(C container){
            this.container = container;
            return this;
        }
        public <T> Builder api(Observable<T> observable){
            this.observable = observable;
            return this;
        }
        public Builder tag(String tag){
            this.tag = tag;
            return this;
        }
        public Subscription build(){
            if (callback == null){
                callback = new CallBack() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onFinish(Object result) {

                    }
                };
            }
            if (cancel == null){
                cancel = new OnCancelRequest() {
                    @Override
                    public void onCancel() {

                    }
                };
            }
            //Onstart
            callback.onStart();
            Subscription sub = observable.subscribeOn(Schedulers.io())
                    // Observe result in the main thread to be able to update UI
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnUnsubscribe(new Action0() {
                        @Override
                        public void call() {
                            cancel.onCancel();
                        }
                    })
                    .subscribe((data) -> {
                        callback.onFinish(data);
                    },(error) -> {
                        callback.onError(error);
                    });
            RequestLoader.getDefault().add(sub, tag);
            RequestLoader.getDefault().addToContainerGroup(sub, container);
            return sub;
        }
    }
}
