package hello.mvc2.message;

import java.util.Locale;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

	@Autowired
	MessageSource messageSource;

	@Test
	void helloMessage(){
		String result = messageSource.getMessage("hello", null, Locale.ENGLISH);
		Assertions.assertThat(result).isEqualTo("hello");
	}

	@Test
	void notFoundMessageCode(){
		Assertions.assertThatThrownBy(()-> messageSource.getMessage("no_code", null, null))
			.isInstanceOf(NoSuchMessageException.class);

	}

	@Test
	void basicMessage(){
		String message = messageSource.getMessage("no_code", null, "기본 메시지", null);
		Assertions.assertThat(message).isEqualTo("기본 메시지");
	}

	//매개변수
	@Test
	void argumentMessage(){
		String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, Locale.ENGLISH);
		Assertions.assertThat(message).isEqualTo("hello Spring");
	}

}
