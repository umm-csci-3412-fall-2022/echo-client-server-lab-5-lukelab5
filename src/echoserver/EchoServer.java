package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {
  public static final int PORT_NUMBER = 6013;
  public static final int BUFFER_SIZE = 1024;

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

      while (true) {
        Socket socketToClient = serverSocket.accept();
        InputStream in = socketToClient.getInputStream();
        OutputStream out = socketToClient.getOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while ((bytesRead = in.read(buffer)) != -1) {
          out.write(buffer, 0, bytesRead);
          out.flush();
        }
        socketToClient.shutdownOutput();
      }
    } catch (IOException ioe) {
      System.err.println("Unexpected exception:");
      ioe.printStackTrace();
    }
  }
}
