package telran.net.test;
import telran.net.*;
public class CalculatorServerAppl {

	private static final int PORT = 3000;

	public static void main(String[] args) {
		try {
			TcpServer server = new TcpServer(PORT,
					new CalculatorProtocol(new CalculatorImpl()));
			server.run();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
