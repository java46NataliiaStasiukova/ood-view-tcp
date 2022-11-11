package telran.net.test;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import telran.net.TcpHandler;
import telran.view.ConsoleInputOutput;
import telran.view.Item;
import telran.view.Menu;

public class CalculatorAppl {

	public static void main(String[] args) {
		try (TcpHandler handler = new TcpHandler("localhost", 3000);
				NetCalculatorProxy proxy = new NetCalculatorProxy(handler);){
			Item[] items = CalculatorMenu.getCalculatorItems(proxy);
			Item exit = Item.of("Exit", io -> {
				if(proxy instanceof Closeable) {
					try {
						((Closeable)proxy).close();
					} catch (IOException e) {
						throw new RuntimeException("can not be closed " + e.getMessage());
					}
				}
			}, true);
			ArrayList<Item> menuItems = new ArrayList<>(Arrays.asList(items));
			menuItems.add(exit);
			Menu menu = new Menu("Calculator", menuItems);
			menu.perform(new ConsoleInputOutput());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
