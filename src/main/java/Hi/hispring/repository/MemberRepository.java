package Hi.hispring.repository;

import Hi.hispring.controller.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //find구문을 쓰다보면 null값이 반환될때도 있는데 이때 에러가 발생함
    //이때 optional을 사용하면 에러처리를 wrapper해서 에러종료를 방지시켜줌
    List<Member> findAll();


}
