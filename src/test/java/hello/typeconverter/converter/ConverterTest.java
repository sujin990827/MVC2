package hello.typeconverter.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.typeconverter.type.IpPort;

class ConverterTest {

	@Test
	void stringToInteger() {
		StringToIntegerConverter converter = new StringToIntegerConverter();
		Integer result = converter.convert("10");
		Assertions.assertThat(result).isEqualTo(10);
	}

	@Test
	void integerToString(){
		IntegerToStringConverter converter = new IntegerToStringConverter();
		String result = converter.convert(10);
		Assertions.assertThat(result).isEqualTo("10");
	}

	@Test
	void stringToIpPort() {
		stringToIpPortConverter converter = new stringToIpPortConverter();
		String source = "127.0.0.1:8080";
		IpPort result = converter.convert(source);
		Assertions.assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
	}
	@Test
	void ipPortToString() {
		IpPortToStringConverter converter = new IpPortToStringConverter();
		IpPort source = new IpPort("127.0.0.1", 8080);
		String result = converter.convert(source);
		Assertions.assertThat(result).isEqualTo("127.0.0.1:8080");
	}
}