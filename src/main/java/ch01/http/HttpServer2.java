package ch01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer2 {

  private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
  
  private boolean shutdown = false;

  public static void main(String[] args) {
    HttpServer2 server = new HttpServer2();
    server.await();
  }

  public void await(){
    ServerSocket serverSocket = null;
    int port = 8080;

    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    } catch(IOException e){
      e.printStackTrace();
      System.exit(0);
    }

    while(!shutdown){
      try {
        Socket socket = serverSocket.accept();
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        
        Request request = new Request(input);
        request.parse();

        Response response = new Response(output);
        response.setRequest(request);

        if (request.getUri().startsWith("/servlet/")){
          ServletProcessor2 processor = new ServletProcessor2();
          processor.process(request, response);
        } else {
          StaticResourceProcessor.process(request, response);
        }
        socket.close();
        shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

      } catch (Exception e){
        e.printStackTrace();
        System.exit(0);
      }

    }
  }
  
  
}




