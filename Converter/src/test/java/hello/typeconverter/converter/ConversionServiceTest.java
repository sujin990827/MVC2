package hello.typeconverter.converter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

	@Test
	void conversionService(){
		//컨버터 등록
		DefaultConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(new StringToIntegerConverter());
		conversionService.addConverter(new IntegerToStringConverter());
		conversionService.addConverter(new stringToIpPortConverter());
		conversionService.addConverter(new IpPortToStringConverter());

		//컨버터 사용
		Integer result = conversionService.convert("10", Integer.class);
		Assertions.assertThat(result).isEqualTo(10);
	}
}
