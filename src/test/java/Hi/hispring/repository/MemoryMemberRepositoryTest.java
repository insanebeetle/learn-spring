package Hi.hispring.repository;

import Hi.hispring.controller.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //이게 붙으면 각 클래스들이 실행될때 아래 메소드가 자동실행됨
    public void afterEach(){
        repository.clearStore();
    }
    //전체 테스트를 돌리면 각테스트끼리 의존되어 있음(객체의 데이터들이 메모리에 남음)
    //따라서 전체적으로 돌릴땐 한 테스트가 끝날때 메모리를 클리어 해줘야함
    @Test
    public  void save(){
        //처음엔 코드없이 한번 돌려보고, 그후 아래 처럼 코드만들고 테스트
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //sout 으로 result를 뽑아서 확인도 가능 그러나 기본 아래처럼 실행
        //Assertions.assertEqual(member, result);
        //assertEquals(예상결과값, 실제수행값);
        Assertions.assertThat(member).isEqualTo(result);
        // 이것도 가능인데 왜 안되냐..
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);
        //문자 동시 변경 sht + f6

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }
    
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}
