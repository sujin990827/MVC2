package hello.typeconverter.converter;

import org.springframework.core.convert.converter.Converter;

import hello.typeconverter.type.IpPort;

public class IpPortToStringConverter implements Converter<IpPort, String> {

	@Override
	public String convert(IpPort source) {
		//IpPort 객체를 -> 127.0.0.1:8080 문자로 반환
		String result = source.getIp() + ":" + source.getPort();
		return result;
	}
}
