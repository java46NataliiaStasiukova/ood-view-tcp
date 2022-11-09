package telran.net.test;

import telran.net.TcpHandler;
import telran.view.ConsoleInputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorAppl {

	public static void main(String[] args) {
		try (TcpHandler handler = new TcpHandler("localhost", 3000);
				NetCalculatorProxy proxy = new NetCalculatorProxy(handler);){
			Item[] items = CalculatorMenu.getCalculatorItems(proxy);
			Menu menu = new Menu("Calculator", items);
			menu.perform(new ConsoleInputOutput());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
