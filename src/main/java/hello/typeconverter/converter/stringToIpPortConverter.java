package hello.typeconverter.converter;

import org.springframework.core.convert.converter.Converter;
import hello.typeconverter.type.IpPort;

public class stringToIpPortConverter implements Converter<String, IpPort> {

	@Override
	public IpPort convert(String source) {
		//127.0.0.1:8080 형태의 문자가 들어온다
		String[] split = source.split(":");
		String ip = split[0];
		int port = Integer.parseInt(split[1]);
		return new IpPort(ip, port);
	}

}
