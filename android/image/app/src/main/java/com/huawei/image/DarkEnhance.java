package com.huawei.image;

import android.graphics.Bitmap;

import com.huawei.image.utils.AccessService;
import com.huawei.image.utils.HttpClientUtils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.Call;
import okhttp3.Callback;

public class DarkEnhance {
    private AccessService service;

    public DarkEnhance(AccessService service) {
        this.service = service;
    }

    public void darkEnhance(Bitmap bit, Callback callback) throws Exception {
        //
        // 2.构建访问低光照增强服务需要的参数
        //
        String uri = "/v1.0/vision/dark-enhance";
        String fileBase64Str = HttpClientUtils.BitmapStrByBase64(bit);

        JSONObject json = new JSONObject();
        json.put("image", fileBase64Str);
        json.put("brightness", 0.9);

        // 3.传入低光照增强服务对应的uri参数, 传入低光照增强服务需要的参数，
        // 该参数主要通过JSON对象的方式传入, 使用POST方法调用服务
        Call call = service.post(uri, json.toJSONString());
        call.enqueue(callback);
    }
}
