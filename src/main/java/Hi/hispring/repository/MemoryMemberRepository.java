package Hi.hispring.repository;

import Hi.hispring.controller.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private  static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //null값 방지 optional.ofNullable
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member ->member.getName()
                .equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
    //파일 테스트를 할때 각 클래스들끼리 의존하지 않도록 메모리를 비우는 역할 메소드
}
