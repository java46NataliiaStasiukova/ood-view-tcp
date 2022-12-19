package telran.view;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {
InputOutput io = new ConsoleInputOutput();
	@Test
	@Disabled
	void readObjectTest() {
		Integer[] array = io.readObject("Enter numbers separated by space",
				"no number ", s -> {
					
					String strNumbers [] = s.split(" ");
					return Arrays.stream(strNumbers).map(str -> Integer.parseInt(str))
							.toArray(Integer[]::new);
					
				}) ;
		io.writeLine(Arrays.stream(array).collect(Collectors.summarizingInt(x -> x)));
	}

	@Test
	@Disabled
	void readIntMinMax() {
		Integer res = io.readInt("Enter any number in range [1, 40]", "no number ", 1, 40);
		io.writeLine(res);
	}
	
	@Test
	//@Disabled
	void readLongTest() {
		Long res = io.readLong("Enter number ", "no number ");
		io.writeLine(res);
	}
	
	@Test
	@Disabled
	void readOptionTest() {
		List<String> list = new ArrayList<>();
		list.add("Option-1");
		list.add("Option-2");
		list.add("Option-3");
		String options = list.toString().replaceAll("[^a-zA-Z0-9-]", " ");
		String res = io.readOption("Choose option: \n" + options, "no option ", list);
		io.writeLine(res);		
	}
	
	@Test
	@Disabled
	void readDateTest() {
		LocalDate res = io.readDate("Enter date DD/MM/YYYY", "no date ");
		io.writeLine(res);
	}
	
	@Test
	@Disabled
	void readDateTest2() {
		String format = "dd.MM.yyyy";
		LocalDate res = io.readDate("Enter date in format " + format, "no date ", format);
		io.writeLine(res);
	}
	
	@Test
	@Disabled
	void readPredicateTest() {
		String emailRegex = "^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@{0}"
		        + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$";
		Predicate<String> predicate = (p) -> p.matches(emailRegex);
		String res = io.readPredicate("Enter email ", "no email ", predicate);
		io.writeLine(res);
	}
	
	@Test
	@Disabled
	void readPredicateTest2() {

		String phoneRegex = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
		Predicate<String> predicate = (p) -> p.matches(phoneRegex);
		String res = io.readPredicate("Enter phone number ", "no phone number ", predicate);
		io.writeLine(res);
	}

}