package com.gwell.rpc.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtil {
  public static File getFile(String urlStr) throws FileNotFoundException {
    try {
      URL url = new URL(urlStr);
      if (!"file".equals(url.getProtocol())) {
        throw new FileNotFoundException(
            "URL cannot be resolved to absolute file path "
                + "because it does not reside in the file system: "
                + url);
      }
      try {
        URI uri = new URI(StringUtils.replace(urlStr, " ", "%20"));
        return new File(uri.getSchemeSpecificPart());
      } catch (URISyntaxException ex) {
        // Fallback for URLs that are not valid URIs (should hardly ever happen).
        return new File(url.getFile());
      }
    } catch (MalformedURLException ex) {
      // no URL -> treat as file path
      return new File(urlStr);
    }
  }
}
