package com.example.binhbt.myapplication.ui.mvpdemo;

import com.example.binhbt.myapplication.model.MixUser;
import com.example.binhbt.myapplication.model.User;
import com.example.binhbt.myapplication.model.VersionApp;
import com.example.binhbt.myapplication.net.BaseRequest;
import com.example.binhbt.myapplication.net.DemoRequestFactory;
import com.example.binhbt.myapplication.net.UserDetailRequest;
import com.example.binhbt.myapplication.net.UserListRequest;
import com.google.gson.reflect.TypeToken;
import com.vn.fa.base.mvp.BasePresenter;
import com.vn.fa.base.data.net.FaRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

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
                .callBack(new FaRequest.RequestCallBack<List<VersionApp>>() {
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
                .callBack(new FaRequest.RequestCallBack<List<VersionApp>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                        t.printStackTrace();
                    }

                    @Override
                    public void onFinish(List<VersionApp> result) {
                        if (result != null) {
                            if (result != null && result.size() > 0) {
                                getMvpView().loadDataToView(result);
                            }
                        }
                        //testUser();
                        //mixRequest();
                    }
                })
                .doRequest();
/*        new FlatRequest()
                .callBack(new FaRequest.RequestCallBack<User>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onFinish(User result) {
                        ArrayList<User> list = new ArrayList<User>();
                        list.add(result);
                        if (result != null) {
                           //getMvpView().loadDataToView(list);
                        }
                    }
                })
                .doRequest();*/
    }
    public void testUser(){
        DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.USER_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<User>() {}.getType())
                .callBack(new FaRequest.RequestCallBack<User>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {
                        getMvpView().showError();
                    }

                    @Override
                    public void onFinish(User result) {
                        if (result != null) {
                            //mixRequest();
                        }
                    }
                })
                .doRequest();
    }
    public void mixRequest(){
        final FaRequest request1 = DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType());

        final FaRequest request2 = DemoRequestFactory.getInstance().getRequest(DemoRequestFactory.DemoRequestType.HOME_REQUEST)
                .params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType());
        BaseRequest mixRequest = new BaseRequest(){
            @Override
            public Observable getApi() {
                Observable<List<VersionApp>> combined = Observable.zip(request1.getApi(), request2.getApi(), new BiFunction<List<VersionApp> , List<VersionApp>, List<VersionApp>>() {
                    @Override
                    public List<VersionApp> apply(List<VersionApp> appList1, List<VersionApp> appList2) {
                        List<VersionApp> result = new ArrayList<>();
                        result.addAll(appList1);
                        result.addAll(appList2);
                        return result;
                    }
                });
                return combined;
            }
        };
        mixRequest.params(new HashMap<>())
                .dataType(new TypeToken<List<VersionApp>>() {}.getType())
                .callBack(new FaRequest.RequestCallBack<List<VersionApp>>() {
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
