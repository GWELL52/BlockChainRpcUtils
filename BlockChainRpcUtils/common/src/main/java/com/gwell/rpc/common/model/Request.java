package com.gwell.rpc.common.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Request<T extends Response> extends HttpMethod {
  private List<String> pathSegments;
  private JSONObject params;
  private boolean post;
  private boolean get;
  private boolean formData;
  private Class<T> responseType;
  private Connection connection;

  public Request(
      Connection connection,
      boolean get,
      boolean post,
      boolean formData,
      List<String> pathSegments,
      JSONObject params,
      Class<T> responseType) {
    this.connection = connection;
    this.pathSegments = pathSegments;
    this.params = params;
    this.post = post;
    this.get = get;
    this.formData = formData;
    this.responseType = responseType;
  }

  @Override
  public HttpUrl.Builder getUrlBuilder() {
    return connection.getUrlBuilder();
  }

  @Override
  public OkHttpClient getClient() {
    return connection.getClient();
  }

  @Override
  public Request getRequest() {
    return this;
  }

  public static <T extends Response> Request<T> get(Connection connection, Class<T> responseType) {
    return new Request<>(connection, true, false, false, null, null, responseType);
  }

  public static <T extends Response> Request<T> get(
      Connection connection, List<String> pathSegments, Class<T> responseType) {
    return new Request<>(connection, true, false, false, pathSegments, null, responseType);
  }

  public static <T extends Response> Request<T> get(
      Connection connection, JSONObject params, Class<T> responseType) {
    return new Request<>(connection, true, false, false, null, params, responseType);
  }

  public static <T extends Response> Request<T> get(
      Connection connection, List<String> pathSegments, JSONObject params, Class<T> responseType) {
    return new Request<>(connection, true, false, false, pathSegments, params, responseType);
  }

  public static <T extends Response> Request<T> post(
      Connection connection, boolean isFormData, Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, null, null, responseType);
  }

  public static <T extends Response> Request<T> post(
      Connection connection, boolean isFormData, List<String> pathSegments, Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, pathSegments, null, responseType);
  }

  public static <T extends Response> Request<T> post(
      Connection connection, boolean isFormData, JSONObject params, Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, null, params, responseType);
  }

  public static <T extends Response> Request<T> post(
      Connection connection,
      boolean isFormData,
      List<String> pathSegments,
      JSONObject params,
      Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, pathSegments, params, responseType);
  }

  public static <T extends Response> Request<T> rpc(
      Connection connection, String method, List<Object> params, Class<T> responseType) {
    JSONObject jsonObject = new JSONObject(true);
    if (params != null && params.size() > 0) {
      // 去除空值
      params = params.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
    jsonObject.put("jsonrpc", "2.0");
    jsonObject.put("method", method);
    jsonObject.put("params", params);
    jsonObject.put("id", 1);

    return new Request<>(connection, false, true, false, null, jsonObject, responseType);
  }
}
