package com.lovec.googleplayeteach.http.protocol;

import com.lovec.googleplayeteach.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/9/3.
 */
public class HomeDetailProtocol extends BaseProtocol<AppInfo> {

    public String packageName;


    public HomeDetailProtocol(String packagname) {
        this.packageName = packagname;
    }

    @Override
    public String getKey() {
        return "detail";
    }

    @Override
    public String getParams() {
        return "&packageName=" + packageName;
    }

    @Override
    public AppInfo parseData(String result) {
        try {
            JSONObject jo1 = new JSONObject(result);
            AppInfo info = new AppInfo();
            info.des = jo1.getString("des");
            info.downloadUrl = jo1.getString("downloadUrl");
            info.iconUrl = jo1.getString("iconUrl");
            info.id = jo1.getString("id");
            info.name = jo1.getString("name");
            info.packageName = jo1.getString("packageName");
            info.size = jo1.getLong("size");
            info.stars = (float) jo1.getDouble("stars");

            info.author = jo1.getString("author");
            info.date = jo1.getString("date");
            info.version=jo1.getString("version");
            info.downloadNum = jo1.getString("downloadNum");
            info.downloadUrl = jo1.getString("downloadUrl");
            JSONArray ja = jo1.getJSONArray("safe");
            //解析安全信息
            ArrayList<AppInfo.SafeInfo> safe = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo2 = ja.getJSONObject(i);
                AppInfo.SafeInfo safeInfo = new AppInfo.SafeInfo();
                safeInfo.safeDes = jo2.getString("safeDes");
                safeInfo.safeDesUrl = jo2.getString("safeDesUrl");
                safeInfo.safeUrl = jo2.getString("safeUrl");

                safe.add(safeInfo);
            }
            info.safe = safe;
            JSONArray ja1 = jo1.getJSONArray("screen");

            ArrayList<String> screen = new ArrayList<>();
            for (int i = 0; i < ja1.length(); i++) {
                String pic = ja1.getString(i);
                screen.add(pic);

            }
            info.screen = screen;

            return info;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
