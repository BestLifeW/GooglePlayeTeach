package com.lovec.googleplayeteach.http.protocol;

import com.lovec.googleplayeteach.domain.CategoryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lovec on 2016/8/31.
 */
public class CategoryProtocol extends BaseProtocol<ArrayList<CategoryInfo>> {

    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public String getParams() {
        return "";
    }

    @Override
    public ArrayList<CategoryInfo> parseData(String result) {

        try {
            JSONArray ja = new JSONArray(result);
            ArrayList<CategoryInfo> list = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {  //大分类  获取到标题

                JSONObject jo = ja.getJSONObject(i);
                if (jo.has("title")) {
                    CategoryInfo titleInfo = new CategoryInfo();
                    titleInfo.title = jo.getString("title");
                    titleInfo.isTitle = true;

                    list.add(titleInfo);
                }
                if (jo.has("infos")) {
                    JSONArray ja1 = jo.getJSONArray("infos");
                    for (int j = 0; j < ja1.length(); j++) { //小分类 获取 具体内容
                        JSONObject jo1 = ja1.getJSONObject(j);
                        CategoryInfo info = new CategoryInfo();
                        info.name1 = jo1.getString("name1");
                        info.name2 = jo1.getString("name2");
                        info.name3 = jo1.getString("name3");
                        info.url1 = jo1.getString("url1");
                        info.url2 = jo1.getString("url2");
                        info.url3 = jo1.getString("url3");
                        info.isTitle = false;
                        list.add(info);
                    }
                }
            }
            return list;


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
