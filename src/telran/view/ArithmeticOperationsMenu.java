package telran.view;

public class ArithmeticOperationsMenu {

	public static Menu getArithmeticOperationsMenu() {
		
		Menu menu = new Menu("Arithmetic Operations", getItems());
		return menu;
	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Adding", ArithmeticOperationsMenu::add),
				Item.of("Subtracting", ArithmeticOperationsMenu::subtruct),
				Item.of("Dividing", ArithmeticOperationsMenu::divide),
				Item.of("Multiplying", ArithmeticOperationsMenu::multiply),
				Item.exit()
		};
		return res;
	}
	static void add(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] + numbers[1]);
	}
	static void subtruct(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] - numbers[1]);
	}
	static void divide(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] / numbers[1]);
	}
	static void multiply(InputOutput io) {
		int numbers[] = enterNumbers(io);
		io.writeLine(numbers[0] * numbers[1]);
	}
	private static int[] enterNumbers(InputOutput io) {
	int res[] = new int[2];
	res[0] = io.readInt("enter first number", "no number");
	res[1] = io.readInt("enter second number", "no number");
	return res;
}

}