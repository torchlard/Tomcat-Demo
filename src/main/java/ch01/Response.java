package ch01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {

  private static final int BUFFER_SIZE = 1024;
  Request request;
  OutputStream output;
  PrintWriter writer;

  public Response(OutputStream output){
    this.output = output;
  }

  public void setRequest(Request req){
    this.request = req;
  }

  public void sendStaticResource() throws IOException {
    byte[] bytes = new byte[BUFFER_SIZE];
    FileInputStream fis = null;
    try {
      File file = new File(Constants.WEB_ROOT, request.getUri());
      fis = new FileInputStream(file);
      
      int ch = fis.read(bytes, 0, BUFFER_SIZE);
      while(ch != -1){
        output.write(bytes, 0, ch);
        ch = fis.read(bytes, 0, BUFFER_SIZE);
      }
    } catch(FileNotFoundException e){
      String errorMsg = "HTTP/1.1 404 File Not Found\r\n" +
        "Content-Type: text/html\r\n"+
        "Content-Length: 23\r\n"+
        "\r\n"+
        "<h1>File not found</h1>";
      output.write(errorMsg.getBytes());
    } finally {
      if (fis != null)
        fis.close();
    }
  }

  public void flushBuffer() throws IOException{}
  public int getBufferSize(){
    return 0;
  }
  public String getCharacterEncoding(){
    return null;
  }
  public Locale getLocale(){
    return null;
  }
  public ServletOutputStream getOutputStream() throws IOException {
    return null;
  }
  public PrintWriter getWriter() throws IOException {
    writer = new PrintWriter(output, true);
    return writer;
  }
  public boolean isCommitted(){
    return false;
  }
  public void reset(){}
  public void resetBuffer(){}
  public void setBufferSize(int size){}
  public void setContentLength(int length){}
  public void setContentType(String type){}
  public void setLocale(Locale locale){}

  @Override
  public String getContentType() {
    return null;
  }

  @Override
  public void setCharacterEncoding(String charset) {

  }

  @Override
  public void setContentLengthLong(long len) {

  }
  
  
  
}





