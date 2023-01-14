package hello.typeconverter.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToIntegerConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		Integer integer = Integer.valueOf(source);
		return integer;
	}
}
