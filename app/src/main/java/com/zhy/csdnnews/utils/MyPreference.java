package com.zhy.csdnnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPreference {
    public static final String NEW_TYPE__PREFIX = "new_type_";
    private static MyPreference instance = null;
    private Context context;
    private SharedPreferences sp;

    private MyPreference(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("config.xml", Context.MODE_PRIVATE);
    }

    public static MyPreference getInstance(Context context) {
        if (null == instance) {
            instance = new MyPreference(context);
        }
        return instance;
    }


    public void setRefreshTime(int newsType) {
        SimpleDateFormat df = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(NEW_TYPE__PREFIX + newsType, df.format(new Date()));
        edit.commit();
    }

    public String getRefreshTime(int newsType) {
        return sp.getString(NEW_TYPE__PREFIX + newsType, "我好笨啊，忘记了>_<");
    }

}
