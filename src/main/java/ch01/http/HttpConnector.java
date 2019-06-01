package ch01.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// dispatch incoming connections to http processor
public class HttpConnector implements Runnable {

  boolean stopped;
  private String scheme = "http";

  public String getScheme(){
    return scheme;
  }

  public void run(){
    ServerSocket serverSocket = null;
    int port = 8080;

    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    } catch(IOException e){
      e.printStackTrace();
      System.exit(0);
    }
    while(!stopped){
      Socket socket = null;
      // accept next connection even if error
      try{
        socket = serverSocket.accept();
      } catch(Exception e){
        continue;
      }
      HttpProcessor processor = new HttpProcessor(this);
      processor.process(socket);
    }
  }

  public void start(){
    Thread thread = new Thread(this);
    thread.start();
  }
  
}





