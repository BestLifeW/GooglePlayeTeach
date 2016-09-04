package com.lovec.googleplayeteach.http.protocol;

import com.lovec.googleplayeteach.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/8/27.
 */
public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {

    private ArrayList<String> pictures;

    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public String getParams() {
        return "";//不要传null
    }

    @Override
    public ArrayList<AppInfo> parseData(String result) {
        //使用jsonObject来解析
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray list = jsonObject.getJSONArray("list");
            ArrayList<AppInfo> appInfoList = new ArrayList<>();

            for (int i = 0; i < list.length(); i++) {
                JSONObject jo1 = list.getJSONObject(i);

                AppInfo info = new AppInfo();
                info.des = jo1.getString("des");
                info.downloadUrl = jo1.getString("downloadUrl");
                info.iconUrl = jo1.getString("iconUrl");
                info.id = jo1.getString("id");
                info.name = jo1.getString("name");
                info.packageName = jo1.getString("packageName");
                info.size = jo1.getLong("size");
                info.stars = (float) jo1.getDouble("stars");

                appInfoList.add(info);
            }
            JSONArray ja1 = jsonObject.getJSONArray("picture");
            pictures = new ArrayList<String>();
            for (int i = 0; i < ja1.length(); i++) {
                String pic = ja1.getString(i);
                pictures.add(pic);
            }
            return appInfoList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getPicturelist() {
        return pictures;
    }
}
