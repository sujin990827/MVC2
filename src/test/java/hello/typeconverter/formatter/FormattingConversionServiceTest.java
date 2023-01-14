package hello.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.stringToIpPortConverter;
import hello.typeconverter.type.IpPort;

public class FormattingConversionServiceTest {

	@Test
	void formattingConversionService(){

		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

		// 컨버터 등록
		conversionService.addConverter(new stringToIpPortConverter());
		conversionService.addConverter(new IpPortToStringConverter());
		// 포멧터 등록
		conversionService.addFormatter(new MyNumberFormatter());
		//컨버터 사용
		IpPort convert = conversionService.convert("127.0.0.1:8080", IpPort.class);
		Assertions.assertThat(convert).isEqualTo(new IpPort("127.0,0,1", 8080));
	}
}
