package ch01;

import java.io.IOException;

public class StaticResourceProcessor {
  public static void process(Request req, Response res){
    try {
      res.sendStaticResource();
    } catch(IOException e){
      e.printStackTrace();
    }
  }
}







