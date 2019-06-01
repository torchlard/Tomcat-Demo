package ch01.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.res.StringManager;

public class SocketInputStream extends InputStream {

  private static final byte CR = (byte) '\r';
  private static final byte LF = (byte) '\n';
  private static final byte SP = (byte) ' ';
  private static final byte HT = (byte) '\t';
  private static final byte COLON = (byte) ':';

  private static final int LC_OFFSET = 'A'-'a';
  // actual byte buffer data
  protected byte buf[];
  // last valid byte
  protected int count;
  // position in buffer
  protected int pos;
  protected InputStream is;

  protected static StringManager sm = StringManager.getManager(Constants.Package);

  public SocketInputStream(InputStream is, int bufferSize){
    this.is = is;
    this.buf = new byte[bufferSize];
  }

  // read all?
  public int read() throws IOException {
    if(pos >= count){
      fill();
      if(pos >= count)
        return -1;
    }
    return buf[pos++] & 0xff;
  }

  public int available() throws IOException {
    return (count - pos) + is.available();
  }

  public void close() throws IOException {
    if (is == null) return;
    is.close();
    is = null;
    buf = null;
  }

  // fill buffer using data from input stream
  protected void fill() throws IOException{
    pos = 0;
    count = 0;
    int nRead = is.read(buf, 0, buf.length);
    if (nRead > 0)
      count = nRead;
  }

  
  
}






