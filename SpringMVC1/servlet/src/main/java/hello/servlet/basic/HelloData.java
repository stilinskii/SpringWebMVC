package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//request데이타를 json으로 변환하기위한 객체
@Getter @Setter
public class HelloData {
    private String username;
    private int age;

//jackson라이브러리가 setter를 호출함
// java property 접근법, java Bean 접근법이라고도 함

}
