package com.gwell.rpc.common.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class Request<T extends Response<?>> {
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

  public static <T extends Response<?>> Request<T> get(
      Connection connection, Class<T> responseType) {
    return new Request<>(connection, true, false, false, null, null, responseType);
  }

  public static <T extends Response<?>> Request<T> get(
      Connection connection, List<String> pathSegments, Class<T> responseType) {
    return new Request<>(connection, true, false, false, pathSegments, null, responseType);
  }

  public static <T extends Response<?>> Request<T> get(
      Connection connection, JSONObject params, Class<T> responseType) {
    return new Request<>(connection, true, false, false, null, params, responseType);
  }

  public static <T extends Response<?>> Request<T> get(
      Connection connection, List<String> pathSegments, JSONObject params, Class<T> responseType) {
    return new Request<>(connection, true, false, false, pathSegments, params, responseType);
  }

  public static <T extends Response<?>> Request<T> post(
      Connection connection, boolean isFormData, Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, null, null, responseType);
  }

  public static <T extends Response<?>> Request<T> post(
      Connection connection, boolean isFormData, List<String> pathSegments, Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, pathSegments, null, responseType);
  }

  public static <T extends Response<?>> Request<T> post(
      Connection connection, boolean isFormData, JSONObject params, Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, null, params, responseType);
  }

  public static <T extends Response<?>> Request<T> post(
      Connection connection,
      boolean isFormData,
      List<String> pathSegments,
      JSONObject params,
      Class<T> responseType) {
    return new Request<>(connection, false, true, isFormData, pathSegments, params, responseType);
  }

  public static <T extends Response<?>> Request<T> rpc(
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

  /** 发起请求 */
  public T send() {
    okhttp3.Request httpRequest = getHttpRequest(this);
    try (okhttp3.Response response = connection.getClient().newCall(httpRequest).execute()) {
      return Response.success(response, this.getResponseType());
    } catch (SocketTimeoutException e) {
      return Response.timeOut(this.getResponseType());
    } catch (Exception e) {
      return Response.fail(e, this.getResponseType());
    }
  }

  private HttpUrl getUrl(Request<T> request) {
    HttpUrl.Builder builder = connection.getUrlBuilder();
    if (request.getPathSegments() != null && request.getPathSegments().size() > 0) {
      request.getPathSegments().forEach(builder::addPathSegment);
    }
    if (request.isGet() && request.getParams().size() > 0) {
      request
          .getParams()
          .getInnerMap()
          .forEach(
              (key, value) -> {
                builder.addQueryParameter(key, value.toString());
              });
    }
    return builder.build();
  }

  private okhttp3.Request getHttpRequest(Request<T> request) {
    okhttp3.Request.Builder requestBuilder = new okhttp3.Request.Builder().url(getUrl(request));
    if (request.isPost()) {
      if (request.isFormData()) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (request.getParams() != null && request.getParams().size() > 0) {
          request
              .getParams()
              .getInnerMap()
              .forEach((k, v) -> formBuilder.addEncoded(k, v.toString()));
        }
        requestBuilder.post(formBuilder.build());
      } else {
        RequestBody requestBody =
            RequestBody.create(
                request.getParams() != null && request.getParams().size() > 0
                    ? request.getParams().toJSONString()
                    : "",
                MediaType.parse("application/json; charset=utf-8"));
        requestBuilder.post(requestBody);
      }
    }
    return requestBuilder.build();
  }
}
