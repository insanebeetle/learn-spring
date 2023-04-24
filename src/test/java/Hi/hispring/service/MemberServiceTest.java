package Hi.hispring.service;

import Hi.hispring.controller.domain.Member;
import Hi.hispring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    //테스트할 클래스명에다 c s T 하면 테스트 패키지 파일을 아래처럼 만들어줌
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;
    @BeforeEach //각 클래스 실행전에 이 코드가 한번씩 사용
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    //아래의 애매함을 해결하기 위해 위의 코드로 변경
    /* 위 코드의 테스트용 레파지토리와 memberservice의 레파지토리는 다른 객체를 가지고 테스트를 진행하는거임
    여기서 애매해지는 부분이 발생함 (심지어 현재 static변수를 사용하고 있어서 없을경우 머리아파짐)
    ㄱ래서 memberservice의 변수를 생성자를 붙여줌 memberservice참고
    * */


    @AfterEach //이게 붙으면 각 클래스들이 실행될때 아래 메소드가 자동실행됨
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복회원예외테스트(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");

        //()->memberService.join(member2)라는 로직을 실행할건데 해당예외가 발생해야함
//        try { 트라이 캐치로 잡을 수도 있지만 위 코드가 좀더 실무적
//            memberService.join(member2);
//            fail(); //예외가 발생하지 않은 경우 실행
//        }catch (IllegalStateException e){
//            //System.out.println(e);
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}