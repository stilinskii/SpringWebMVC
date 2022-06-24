package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id;

    @NotEmpty
    private String loginId;//로그인할때 사용하는 아이디
    @NotEmpty
    private String name;//사용자 이름
    @NotEmpty
    private String password;
}
