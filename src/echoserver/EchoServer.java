package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {
	public static final int PORT_NUMBER = 6013;
	public static final int BUFFER_SIZE = 1024;

	public static void main(String[] args){
		try{
			ServerSocket s = new ServerSocket(PORT_NUMBER);

			while(true){
				Socket clientSocket = s.accept();
				InputStream input = clientSocket.getInputStream();
				OutputStream output = clientSocket.getOutputStream();
				byte buffer = new byte[BUFFER_SIZE];
				int readBytes;

				while((readBytes = input.read(buffer)) != -1){
					output.write(buffer,0,readBytes);
					output.flush();
				}
				clientSocket.shutdownOutput();
			}
		} catch (IOException ioe){
			System.err.println("Unexpected error");
			ioe.printStackTrace();
		}
	}
}
