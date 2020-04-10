package com.huawei.image;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSONObject;
import com.huawei.image.utils.AccessService;
import com.huawei.image.utils.HttpClientUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Defog {
    private AccessService service;

    public Defog(AccessService service) {
        this.service = service;
    }

    public void defog(Bitmap bit, Callback callback) throws Exception {
        //
        // 2.构建访问去雾服务需要的参数
        //
        String uri = "/v1.0/vision/defog";
        String fileBase64Str = HttpClientUtils.BitmapStrByBase64(bit);

        JSONObject json = new JSONObject();
        json.put("image", fileBase64Str);
        json.put("gamma", 1.5);
        json.put("natural_look", true);

        // 3.传入去雾服务对应的uri参数, 传入去雾服务需要的参数，
        // 该参数主要通过JSON对象的方式传入, 使用POST方法调用服务
        Call call = service.post(uri, json.toJSONString());
        call.enqueue(callback);
    }
}
