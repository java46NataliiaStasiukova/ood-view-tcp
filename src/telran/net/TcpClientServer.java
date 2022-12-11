package telran.net;

import java.net.*;
import java.io.*;

public class TcpClientServer implements Runnable {
	private static final int READ_TIME_OUT = 100;
	private static final long CLIENT_IDLE_TIMEOUT = 60000;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private ApplProtocol protocol;
	private TcpServer tcpServer;

	public long idlePeriod = 0;
	
	public TcpClientServer(Socket socket, ApplProtocol protocol, TcpServer tcpServer) throws Exception {
		this.socket = socket;
		this.socket.setSoTimeout(READ_TIME_OUT);
		this.protocol = protocol;
		this.tcpServer = tcpServer;
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
		
	}
	@Override
	public void run() {
		TcpServer.count.decrementAndGet();
		while(!tcpServer.isShutdown) {
			try {
				Request request = (Request) input.readObject();
				idlePeriod = 0;
				Response response = protocol.getResponse(request);
				output.writeObject(response);
			} catch (SocketTimeoutException e) {
				idlePeriod += READ_TIME_OUT;
				if(idlePeriod >= CLIENT_IDLE_TIMEOUT && TcpServer.count.get() > 0) {
					try {
						socket.close();
					} catch (IOException e1) {

					}
				}		
			} catch (EOFException e) {
				System.out.println("client closed connection");	
				break;
			} catch(Exception e) {
				System.out.println("socked closed by server: " + e.getMessage());
				break;
			}	
		} 
		if(tcpServer.isShutdown) {
			System.out.println("client connection closed by server shutdown");
			try {
				socket.close();
			} catch (IOException e1) {
				
			}
		}
	}

}
