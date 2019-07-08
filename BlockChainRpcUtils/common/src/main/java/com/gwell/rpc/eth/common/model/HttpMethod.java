package com.gwell.rpc.eth.common.model;

import okhttp3.*;

import java.net.SocketTimeoutException;

public abstract class HttpMethod {
  private Request<? extends Response> request = getRequest();

  public abstract HttpUrl.Builder getUrlBuilder();

  public abstract OkHttpClient getClient();

  public abstract Request<? extends Response> getRequest();

  /** 发起请求 */
  public <T extends Response> T send() {
    okhttp3.Request httpRequest = getHttpRequest(request);
    try (okhttp3.Response response = getClient().newCall(httpRequest).execute()) {
      return (T) Response.success(response, request.getResponseType());
    } catch (SocketTimeoutException e) {
      return (T) Response.timeOut(request.getResponseType());
    } catch (Exception e) {
      return (T) Response.fail(e, request.getResponseType());
    }
  }

  private HttpUrl getUrl(Request<? extends Response> request) {
    HttpUrl.Builder builder = getUrlBuilder();
    if (request.getPathSegments() != null && request.getPathSegments().size() > 0) {
      request.getPathSegments().forEach(pathSegment -> builder.addPathSegment(pathSegment));
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

  private okhttp3.Request getHttpRequest(Request<? extends Response> request) {
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
                MediaType.parse("application/json; charset=utf-8"),
                request.getParams() != null && request.getParams().size() > 0
                    ? request.getParams().toJSONString()
                    : null);
        requestBuilder.post(requestBody);
      }
    }
    return requestBuilder.build();
  }
}
