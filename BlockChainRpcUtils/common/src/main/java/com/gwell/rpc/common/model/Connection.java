package com.gwell.rpc.common.model;

import com.gwell.rpc.common.Interceptor.HttpLogger;
import lombok.Builder;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;

@Builder
public class Connection {
  private String ip;
  private Integer port;
  private String username;
  private String password;
  private String url;

  public static Connection.ConnectionBuilder builder(String url) {
    ConnectionBuilder builder = new Connection.ConnectionBuilder();
    builder.url(url);
    return builder;
  }

  public static Connection.ConnectionBuilder builder(String ip, Integer port) {
    ConnectionBuilder builder = new Connection.ConnectionBuilder();
    builder.ip(ip);
    builder.port(port);
    return builder;
  }

  public static Connection.ConnectionBuilder builder(
      String ip, Integer port, String username, String password) {
    ConnectionBuilder builder = new Connection.ConnectionBuilder();
    builder.ip(ip);
    builder.port(port);
    builder.username(username);
    builder.password(password);
    return builder;
  }

  public HttpUrl.Builder getUrlBuilder() {
    if (StringUtils.isNotBlank(url)) {
      return HttpUrl.parse(url).newBuilder();
    } else {
      HttpUrl.Builder builder = new HttpUrl.Builder().scheme("http").host(ip);
      if (port != null && port > 0 && port <= 65535) {
        builder.port(port);
      }
      if (StringUtils.isNoneBlank(username, password)) {
        builder.username(username).password(password);
      }
      return builder;
    }
  }

  public OkHttpClient getClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    // 添加日志
    builder.addNetworkInterceptor(
        new HttpLoggingInterceptor(new HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY));

    if (StringUtils.isNoneBlank(username, password)) {
      return builder
          .authenticator(
              (route, response) ->
                  response
                      .request()
                      .newBuilder()
                      .header("Authorization", Credentials.basic(username, password))
                      .build())
          .build();
    }
    return builder.build();
  }
}
