package com.example.binhbt.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.adapter.UsersAdapter;
import com.example.binhbt.myapplication.adapter.UsersLayoutManager;
import com.example.binhbt.myapplication.model.MixUser;
import com.example.binhbt.myapplication.model.User;
import com.example.binhbt.myapplication.ui.activity.UserDetailActivity;
import com.vn.vega.net.RequestLoader;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func2;

/**
 * Created by binhbt on 6/8/2016.
 */
public class UserListFragment extends BaseFragment {
    private UsersAdapter usersAdapter;// = new UsersAdapter(getActivity());
    @Bind(R.id.rv_users)
    RecyclerView rv_users;
    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;

    public UserListFragment() {
        setRetainInstance(true);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
//        ButterKnife.bind(this, fragmentView);
//        setupRecyclerView();
//        return fragmentView;
//    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setupRecyclerView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_list;
    }

    private void startSubcriptions() {

        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        new RequestLoader.Builder()
                .api(interval)
                .callback(new RequestLoader.CallBack<Long>() {
                    @Override
                    public void onStart() {
                        Log.i("asdasd", "Start count");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(getActivity(), "got error " + t.toString(), Toast.LENGTH_LONG).show();
                        // Handle error
                        hideLoading();
                        showError("Request failed");
                        showRetry();
                    }

                    @Override
                    public void onFinish(Long result) {
                        Log.i("aassa", "Started in onResume(), running until in onDestroy(): " + result);
                    }
                })
                .container(this)
                .tag("interval")
                .build();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RequestLoader.getDefault().cancelByTag("interval");
            }
        }, 10000);
        new RequestLoader.Builder()
                .api(api.userEntityList())
                .callback(new RequestLoader.CallBack<List<User>>() {
                    @Override
                    public void onStart() {
                        Toast.makeText(getActivity(), "onstart ", Toast.LENGTH_LONG).show();
                        hideRetry();
                        showLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(getActivity(), "got error " + t.toString(), Toast.LENGTH_LONG).show();
                        // Handle error
                        hideLoading();
                        showError("Request failed");
                        showRetry();
                    }

                    @Override
                    public void onFinish(List<User> result) {
                        //Log.e("Got result ", result.toString());
                        //Toast.makeText(getActivity(), "got error "+result.toString(), Toast.LENGTH_LONG).show();
                        // Update UI on the main thread
                        hideLoading();
                        renderUserList(result);
                        //Log.e("response", result.toString());
                    }
                })
                .container(UserListFragment.this)
                .tag("get_list")
                .build();
        //RequestLoader.getDefault().cancelByTag("get_list");
        zipTest();
        flatMapTest();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            //this.loadUserList();
            startSubcriptions();
        }
    }

    private void setupRecyclerView() {
        usersAdapter = new UsersAdapter(getActivity());
        this.usersAdapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
            @Override
            public void onUserItemClicked(User userModel) {
                Intent i = new Intent(getActivity(), UserDetailActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("user", userModel);
                i.putExtras(b);
                startActivity(i);
            }
        });
        this.rv_users.setLayoutManager(new UsersLayoutManager(getActivity()));
        this.rv_users.setAdapter(usersAdapter);
    }

    @Override
    public void onDestroyView() {
        rv_users.setAdapter(null);
        super.onDestroyView();
    }

//    private void showLoading() {
//        this.rl_progress.setVisibility(View.VISIBLE);
//        this.getActivity().setProgressBarIndeterminateVisibility(true);
//    }
//
//    private void hideLoading() {
//        this.rl_progress.setVisibility(View.GONE);
//        this.getActivity().setProgressBarIndeterminateVisibility(false);
//    }

    private void showError(String message) {
        this.showToastMessage(message);
    }

    private void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    private void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        UserListFragment.this.startSubcriptions();
    }

    private void renderUserList(Collection<User> userModelCollection) {
        if (userModelCollection != null) {
            this.usersAdapter.setUsersCollection(userModelCollection);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }
    private void zipTest(){
        Observable<MixUser> combined = Observable.zip(api.userEntityList(), api.userEntityById(1), new Func2<List<User>, User, MixUser>() {
            @Override
            public MixUser call(List<User> lsUser, User user) {
                return new MixUser(lsUser, user);
            }
        });
        new RequestLoader.Builder()
                .api(combined)
                .container(this)
                .callback(new RequestLoader.CallBack<MixUser>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onFinish(MixUser result) {
                        Log.e("mix user", result.toString());
                    }
                })
                .build();
    }
    private void flatMapTest(){
        Observable<User> flat = api.userEntityList().flatMap(ls->api.userEntityById(ls.get(0).getUserId()));
        new RequestLoader.Builder()
                .api(flat)
                .tag("get_detail_first")
                .container(this)
                .callback(new RequestLoader.CallBack<User>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onFinish(User result) {
                        Log.e("First user", result.toString());
                    }
                });
    }
}