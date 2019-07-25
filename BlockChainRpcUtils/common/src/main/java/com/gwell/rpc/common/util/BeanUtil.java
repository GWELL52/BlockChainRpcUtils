package com.gwell.rpc.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class BeanUtil {

  public static <T> T clone(Object obj, Class<T> responseType) {
    String json = JSON.toJSONString(obj);
    return JSONObject.parseObject(json, responseType);
  }
}
