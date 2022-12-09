package telran.net;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class TcpServer implements Runnable {
	
	private static final int N_THREADS = 2;
	public ExecutorService executor;
	
   private ServerSocket serverSocket;
   private int port;
   private ApplProtocol protocol;
   public TcpServer(int port, ApplProtocol protocol) throws Exception{
	   this.port = port;
	   this.protocol = protocol;
	   executor = Executors.newFixedThreadPool(N_THREADS);
	   serverSocket = new ServerSocket(port);
   }
	@Override
	public void run() {
		System.out.println("Server is listening on the port " + port);
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				TcpClientServer clientServer = new TcpClientServer(socket, protocol);
				executor.execute(clientServer);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}