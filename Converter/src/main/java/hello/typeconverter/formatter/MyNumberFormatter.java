package hello.typeconverter.formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class MyNumberFormatter implements Formatter<Number> {

	@Override
	public Number parse(String text, Locale locale) throws ParseException {
		// 1,000 -> 1000
		NumberFormat format = NumberFormat.getInstance(locale);
		Number parse = format.parse(text);
		return parse;
	}

	@Override
	public String print(Number object, Locale locale) {
		NumberFormat instance = NumberFormat.getInstance(locale);
		String format = instance.format(object);
		return format;
	}
}
