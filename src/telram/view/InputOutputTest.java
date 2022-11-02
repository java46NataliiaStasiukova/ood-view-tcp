package telram.view;

import static org.junit.jupiter.api.Assertions.*;

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
				"No number ", s -> {
					String strNumbers[] = s.split(" ");
					return Arrays.stream(strNumbers).map(str -> Integer.parseInt(str))
							.toArray(Integer[]::new);
				});
		io.writeLine(Arrays.stream(array).collect(Collectors.summarizingInt(x -> x)));
	}
	@Test
	void readIntMinMax() {
		Integer res = io.readInt("Enter any number in range [1, 40]", "no number ", 1, 40);
		io.writeLine(res);
	}


}
