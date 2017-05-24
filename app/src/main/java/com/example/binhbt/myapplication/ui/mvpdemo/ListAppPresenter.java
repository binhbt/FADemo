package com.example.binhbt.myapplication.ui.mvpdemo;

import com.example.binhbt.myapplication.model.VersionApp;
import com.example.binhbt.myapplication.net.EndPoints;
import com.vn.vega.base.mvp.BasePresenter;
import com.vn.vega.net.ApiService;
import com.vn.vega.net.RequestLoader;

import java.util.List;

/**
 * Created by binhbt on 3/7/2017.
 */
public class ListAppPresenter extends BasePresenter<ListAppView> {

    public void loadData() {
        EndPoints apix = new ApiService.Builder()
                .baseUrl(EndPoints.API_ENDPOINT)
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
                .build();
    }
}
