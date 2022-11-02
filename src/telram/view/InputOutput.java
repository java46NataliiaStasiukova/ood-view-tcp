package telram.view;

import java.util.function.Function;

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
	while(true) {
		String str = readString(prompt);
		try {
			result = mapper.apply(str);
			break;
		} catch (Exception e) {
			writeLine(errorPrompt + e.getMessage());
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
		if(num < min) {
			throw new RuntimeException("Less than " + min);
		}
		if(num > max) {
			throw new RuntimeException("Greater than " + max);
		}
		return num;
	});
}


}
