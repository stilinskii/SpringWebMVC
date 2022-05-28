package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    //이미 싱글톤이기때문에 여기 둘에서는 static이 없어도 된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence =0L;

    //single tone
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        //store를 보호하기 위해, store원본에 있는 값들을 유지하기 위해서
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
