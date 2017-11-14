package com.example.binhbt.myapplication.net;

import com.example.binhbt.myapplication.model.MixUser;
import com.example.binhbt.myapplication.model.User;

import java.util.List;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by leobui on 11/9/2017.
 */

public class MixRequest extends BaseRequest{
    @Override
    protected Observable getApi() {
        UserListRequest userListRequest = new UserListRequest();
        UserDetailRequest detailRequest = new UserDetailRequest();
        Observable<MixUser> combined = Observable.zip(userListRequest.getApi(), detailRequest.getApi(), new Func2<List<User>, User, MixUser>() {
            @Override
            public MixUser call(List<User> lsUser, User user) {
                return new MixUser(lsUser, user);
            }
        });
        return combined;
    }
}
