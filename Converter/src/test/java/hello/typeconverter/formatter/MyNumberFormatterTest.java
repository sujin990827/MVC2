package hello.typeconverter.formatter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MyNumberFormatterTest {

	MyNumberFormatter formatter = new MyNumberFormatter();

	@Test
	void parse() throws Exception{
		Number number = formatter.parse("1,000", Locale.KOREA);
		Assertions.assertThat(number).isEqualTo(1000L); //Long 타입 주의
	}

	@Test
	void print() {
		String number = formatter.print(1000, Locale.KOREA);
		Assertions.assertThat(number).isEqualTo("1,000");
	}
}