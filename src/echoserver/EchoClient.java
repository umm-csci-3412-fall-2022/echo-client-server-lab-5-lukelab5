package echoserver;
import java.net.*;
import java.io.*;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;
	public static final int BUFFER_SIZE = 1024;

	public static void main(String[] args){
		String serverName;
		if(args.length == 0){
			serverName = "127.0.0.1";
		} else{
			serverName = args[0];
		}

		try{
			Socket s = new Socket(serverName, PORT_NUMBER);
			InputStream inSocket = s.getInputStream();
			OutputStream outSocket = s.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int readBytes;

			while((readBytes = System.in.read(buffer)) != -1){
				outSocket.write(buffer,0,readBytes);
				outSocket.flush();
				readBytes = inSocket.read(buffer);
				System.out.write(buffer,0,readBytes);
				System.out.flush();
			}
			s.shutdownOutput();

			while((readBytes = inSocket.read(buffer)) != -1){
				System.out.write(buffer,0,readBytes);
				System.out.flush();
			}
			s.close();
		} catch(IOException ioe){
			System.err.println("Unexpected error");
			ioe.printStackTrace();
		}
	}
}
