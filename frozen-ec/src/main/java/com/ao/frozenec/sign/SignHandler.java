package com.ao.frozenec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ao.frozenec.database.DatabaseManager;
import com.ao.frozenec.database.UserProfile;
import com.ao.frozens.app.AccountManager;

/**
 * com.ao.frozenec.sign
 * <p>
 * <p>
 * Created by Leo on 2017/11/23.
 */

public class SignHandler {

    public static void onSignUp(String response, ISignListener iSignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener iSignListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }

}
