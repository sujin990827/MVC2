package hello.typeconverter.converter;

import org.springframework.core.convert.converter.Converter;

public class IntegerToStringConverter implements Converter<Integer, String> {

	@Override
	public String convert(Integer source) {
		String string = String.valueOf(source);
		return string;
	}
}
