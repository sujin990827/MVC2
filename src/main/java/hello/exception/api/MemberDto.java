package hello.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {

	private String memberId;
	private String name;

}
