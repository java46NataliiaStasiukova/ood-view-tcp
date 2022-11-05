package telran.view.test;

import telran.view.ArithmeticOperationsMenu;
import telran.view.ConsoleInputOutput;
import telran.view.DateOperationsMenu;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class MainMenuTest {

	public static void main(String[] args) {
		Menu menu = new Menu("Main Menu", getItems());
		menu.perform(new ConsoleInputOutput());
	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Arithmetoc operations", MainMenuTest::getArithmeticsOperationSubmenu),
				Item.of("Date operations",MainMenuTest::getDateOperationSubMenu),
				Item.exit()
		};
		return res;
	}
	static void getDateOperationSubMenu(InputOutput io) {
		Menu subMenu = DateOperationsMenu.getDateOperationMenu();
		subMenu.perform(io);
	}
	static void getArithmeticsOperationSubmenu(InputOutput io) {
		Menu subMenu = ArithmeticOperationsMenu.getArithmeticOperationsMenu();
		subMenu.perform(io);
	}
}
