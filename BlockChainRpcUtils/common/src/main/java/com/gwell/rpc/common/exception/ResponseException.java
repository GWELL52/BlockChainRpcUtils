package com.gwell.rpc.common.exception;

import com.gwell.rpc.common.model.Response;

public class ResponseException extends RuntimeException {
  private Response.Error error;

  public ResponseException(Response.Error error) {
    super(error.getMessage());
    this.error = error;
  }

  public Response.Error getError() {
    return error;
  }

  public void setError(Response.Error error) {
    this.error = error;
  }

  public static void create(Response.Error error) {
    throw new ResponseException(error);
  }

  public static void create(String message) {
    Response.Error error = new Response.Error(-1, message);
    throw new ResponseException(error);
  }
}
