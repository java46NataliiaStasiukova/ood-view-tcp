package telran.net.test;
import java.util.Scanner;

import telran.net.*;
public class CalculatorServerAppl {

	private static final int PORT = 3000;

	public static void main(String[] args) {
		try {
			TcpServer server = new TcpServer(PORT,
					new CalculatorProtocol(new CalculatorImpl()), 3);
			Thread thread = new Thread(server);
			thread.start();
			Scanner scanner = new Scanner(System.in);
			while(true) {
				System.out.println("For server shutdown enter type 'exit' ");
				String line = scanner.nextLine();
				if(line.equals("exit")) {
					server.shutDown();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
