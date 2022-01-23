package com.sanjeevani.adapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences userSessions;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String IS_LOGIN="ISLOGGEDIN";
    public static final String KEY_TOKEN="TOKEN";
    public static final String STATE="STATE";
    public static final String USERID="USERID";

    public SessionManager(Context _context){
        context=_context;
        userSessions=context.getSharedPreferences("userLoginSessions",Context.MODE_PRIVATE);
        editor=userSessions.edit();
    }

    public void createloginsession(String state){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(STATE,state);
//        editor.putString(USERID,userid);
//        editor.putString(KEY_TOKEN,token);
        editor.commit();
    }
    public boolean checkLogin(){
        if(userSessions.getBoolean(IS_LOGIN,false)){
            return true;
        }
        else {
            return false;
        }
    }
    public void logoutuserfromsession(){
        editor.clear();
        editor.commit();
    }

    public String getState(){
        return userSessions.getString(STATE,null);
    }

    public int getUserid(){
        return userSessions.getInt(USERID,-1);
    }
}

