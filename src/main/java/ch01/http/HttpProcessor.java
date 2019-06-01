package ch01.http;

import java.net.Socket;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.tomcat.util.res.StringManager;

public class HttpProcessor {

  private HttpConnector connector = null;
  private HttpRequest request;
  private HttpRequestLine requestLine = new HttpRequestLine();
  private HttpResponse response;

  protected String method = null;
  protected String queryString = null;

  public HttpProcessor(HttpConnector connector){
    this.connector = connector;
  }

  protected StringManager sm = StringManager.getManager("ch01.http");

  public void process(Socket socket){
    SocketInputStream 
  }



  
}











