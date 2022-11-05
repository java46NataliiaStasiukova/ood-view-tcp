package telran.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateOperationsMenu {

	public static Menu getDateOperationMenu(){
		
		Menu menu = new Menu("Date Operations", getItems());
		return menu;
	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Date after given number days",  DateOperationsMenu::getDateAfter),
				Item.of("Date before given number days", DateOperationsMenu::getDateBefore),
				Item.of("Number days between two dates", DateOperationsMenu::getDaysBetween),
				Item.of("Age according to the birthdate", DateOperationsMenu::getAge),
				Item.exit()
		};
		return res;
	}
	static void getDateAfter(InputOutput io) {
		int days = enterDaysNumber(io);
		LocalDate date = LocalDate.now();
		io.writeLine(date.plusDays(days));
		
	}
	static void getDateBefore(InputOutput io) {
		int days = enterDaysNumber(io);
		LocalDate date = LocalDate.now();
		io.writeLine(date.minusDays(days));
		
	}
	static void getDaysBetween(InputOutput io) {
		LocalDate[] dates = enterTwoDates(io);
		io.writeLine(ChronoUnit.DAYS.between(dates[0], dates[1]));
	}

	static void getAge(InputOutput io) {
		LocalDate date = enterDate(io);
		io.writeLine(ChronoUnit.YEARS.between(date, LocalDate.now()));
	}
	private static int enterDaysNumber(InputOutput io) {
		int res = io.readInt("enter number of days", "no number", 0, Integer.MAX_VALUE);
		return res;
	}
	private static LocalDate[] enterTwoDates(InputOutput io) {
		LocalDate[] res = new LocalDate[2];
		res[0] = io.readDate("enter first date", "no date");
		res[1] = io.readDate("enter second date", "ni date");
		return res;
	}
	private static LocalDate enterDate(InputOutput io) {
		LocalDate res = io.readDate("enter birthday date", "no date");
		return res;
	}
	

}