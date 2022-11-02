package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeObject(Object obj);
	
	default void close() {}

	default void writeLine(Object obj) {
		String str = obj + "\n";
		writeObject(str);
	}

	default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {
		R result = null;
		while (true) {
			String str = readString(prompt);
			try {
				result = mapper.apply(str);
				break;
			} catch (Exception e) {
				writeLine(errorPrompt + " " + e.getMessage());
			}
		}
		return result;

	}
	
	default Integer readInt(String prompt, String errorPrompt) {
		
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}
	
	default Integer readInt(String prompt, String errorPrompt, int min, int max) {
		return readObject(prompt, errorPrompt, s -> {
			int num = Integer.parseInt(s);
			if (num < min) {
				throw new RuntimeException("less than " + min);
			}
			if (num > max) {
				throw new RuntimeException("greater than " + max);
			}
			return num;
			
		});
	}
	
	default Long readLong(String prompt, String errorPrompt) {
		
		return readObject(prompt, errorPrompt, Long::parseLong);
	}

	default String readOption(String prompt, String errorPrompt, List<String> options) {
		return readObject(prompt, errorPrompt, s -> {
			int i = Integer.parseInt(s);
			if(i < 0 || i > options.size()) {
				throw new RuntimeException("" + i);
			}
			return options.get(i - 1);
		});
	}
	
	default LocalDate readDate(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, s -> 
		 	LocalDate.parse(s, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	default LocalDate readDate(String prompt, String errorPrompt, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return readObject(prompt, errorPrompt, s -> LocalDate.parse(s, formatter));
		
		
	}
	
	default String readPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
		return readObject(prompt, errorPrompt, e -> {
			if(!predicate.test(e)) {
				throw new RuntimeException("" + e);
			}
			return e;
		});
	}

	
}
