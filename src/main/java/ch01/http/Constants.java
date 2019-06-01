package ch01.http;

import java.io.File;

public class Constants {
  public static final String WEB_ROOT = 
    System.getProperty("user.dir") + File.separator + "webroot";
  public static final String Package = "ch01.http";
  public static final int DEFAULT_CONNECTION_TIMEOUT = 60_000;
  public static final int PROCESSOR_IDEL = 0;
  public static final int PROCESSOR_ACTIVE = 1;
  
}








