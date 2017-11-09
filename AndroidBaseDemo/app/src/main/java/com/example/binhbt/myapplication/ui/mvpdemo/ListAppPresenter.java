package com.example.binhbt.myapplication.ui.mvpdemo;

import com.example.binhbt.myapplication.model.VersionApp;
import com.example.binhbt.myapplication.net.DemoRequestFactory;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.net.FARequest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by binhbt on 3/7/2017.
 */
public class ListAppPresenter extends BasePresenter<ListAppView> {

    public void loadData() {
/*        EndPoints apix = new Request.Builder()
                .baseUrl(EndPoints.API_ENDPOINT)
//                .addRequestAdapterFactory(new OkHttpAdapterFactory())
                .logging(true)
                .build()
                .create(EndPoints.class);
        new RequestLoader.Builder()
                .api(apix.listOtherApp())
                .callback(new RequestLoader.CallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                    }
                })
                .container(getMvpView())
                .build();*/
/*        EndPoints apix = new Request.Builder()
                .baseUrl(EndPoints.API_ENDPOINT)
                .addRequestAdapterFactory(new OkHttpAdapterFactory())
                .logging(true)
                .build()
                .create(EndPoints.class);
        new RequestLoader.Builder()
                .api(apix.listOtherApp())
                .callback(new RequestLoader.CallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                    }
                })
                .container(getMvpView())
                .build();*/
//        Type type = new TypeToken<List<VersionApp>>() {}.getType();
        //new BaseRequest()
/*
        DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .container(getMvpView())
                .dataType(new TypeToken<List<VersionApp>>() {
                }.getType())
                .type(RequestType.GET)
                .callBack(new FARequest.RequestCallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                    }
                })
                .doRequest();
*/

        DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType())
                .callBack(new FARequest.RequestCallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                    }
                })
                .doRequest();
    }
}
