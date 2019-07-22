package com.gwell.rpc.eth.common.model;

import com.alibaba.fastjson.JSONObject;
import com.gwell.rpc.eth.common.exception.ResponseException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Data
public class Response<T> {
  private Error error;
  private String rawResult;

  public boolean hasError() {
    return error != null;
  }

  public String getRawResult() {
    if (hasError()) {
      ResponseException.create(error);
    }
    return rawResult;
  }

  @Getter
  @Setter
  public static class Error {
    private int code;
    private String message;

    public Error() {}

    public Error(int code, String message) {
      this.code = code;
      this.message = message;
    }
  }

  public static <S extends Response> S success(okhttp3.Response response, Class<S> responseType) {
    S result;
    try {
      result = responseType.newInstance();
    } catch (Exception e) {
      throw new RuntimeException("实例化类失败! Message=" + e.getMessage());
    }
    String body;
    try {
      body = response.body().string();
    } catch (IOException e) {
      throw new RuntimeException("获取Response Body失败!");
    }
    JSONObject jsonObject = JSONObject.parseObject(body);
    String errorStr = jsonObject.getString("error");
    if (response.isSuccessful() && StringUtils.isBlank(errorStr)) {
      String resultJson = jsonObject.getString("result");
      result.setRawResult(resultJson);
    } else {
      Error error = new Error();
      error.setCode(response.code());
      if (StringUtils.isNotBlank(errorStr)) {
        error.setMessage(errorStr);
      } else {
        error.setMessage(body);
      }
      result.setError(error);
    }
    return result;
  }

  public static <S extends Response> S fail(Exception e, Class<S> responseType) {
    S result;
    try {
      result = responseType.newInstance();
    } catch (Exception e1) {
      throw new RuntimeException("实例化类失败! Message=" + e1.getMessage());
    }
    Error error = new Error();
    error.setCode(-1);
    error.setMessage(e.getMessage());
    result.setError(error);
    return result;
  }

  public static <S extends Response> S timeOut(Class<S> responseType) {
    S result;
    try {
      result = responseType.newInstance();
    } catch (Exception e) {
      throw new RuntimeException("实例化类失败! Message=" + e.getMessage());
    }
    Error error = new Error();
    error.setCode(408);
    error.setMessage("请求超时");
    result.setError(error);
    return result;
  }
}
