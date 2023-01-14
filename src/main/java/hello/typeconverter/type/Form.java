package hello.typeconverter.type;

import lombok.Data;

@Data
public class Form {

	private IpPort ipPort;

	public Form(IpPort ipPort) {
		this.ipPort = ipPort;
	}
}
