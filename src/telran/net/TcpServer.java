package telran.net;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
public class TcpServer implements Runnable {
	
	private static final int DEFAULT_N_THREADS = 3;
	private static final int ACCEPT_TIME_OUT = 100;
	public ExecutorService executor;
	volatile boolean isShutdown = false;
	public AtomicInteger count = new AtomicInteger(0);
	public int nThreads;
	
   private ServerSocket serverSocket;
   private int port;
   private ApplProtocol protocol;
   public TcpServer(int port, ApplProtocol protocol, int nThreads) throws Exception{
	   this.port = port;
	   this.protocol = protocol;
	   this.nThreads = nThreads;
	   executor = Executors.newFixedThreadPool(nThreads);
	   serverSocket = new ServerSocket(port);
	   serverSocket.setSoTimeout(ACCEPT_TIME_OUT);
	   }
   public TcpServer(int port, ApplProtocol protocol) throws Exception {
		this(port, protocol, DEFAULT_N_THREADS);
	}
	@Override
	public void run() {
		System.out.println("Server is listening on the port " + port);
		while(!isShutdown) {
			try {				
				Socket socket = serverSocket.accept();////
				count.getAndIncrement();
				TcpClientServer clientServer = new TcpClientServer(socket, protocol, this);
				executor.execute(clientServer);
			} catch(SocketTimeoutException e) {
					
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
				break;
			}
		}
	}
	public void shutDown() {
		isShutdown = true;
		executor.shutdown();
	}

}