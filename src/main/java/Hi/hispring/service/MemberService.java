package Hi.hispring.service;

import Hi.hispring.controller.domain.Member;
import Hi.hispring.repository.MemberRepository;
import Hi.hispring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //중복회원 금지의 경우 구현
        validateDuplicateMember(member);
        //이 코드로 리팩토링 가능- 중복이름을 걸러내기위한 메소드를 따로 생성해줌 c+a+s+t
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent((m ->{ //ifPresent 널이 아닌 다른 값이 있으면 true반환
//            throw new IllegalStateException("이미 존재하는 회원");
//        }));위처럼 코딩 할수 있지만 optianal등 가져오기 번거로우므로
        memberRepository.findByName(member.getName())
                .ifPresent((m ->{
                    throw new IllegalStateException("이미 존재하는 회원");
                })); //이런 식으로 만들기도 가능
    }

    //실무에서 서비스 패키지는 업무적인 용어들을 주로 사용함
    //레퍼지토리는 개발용 용어를 주로 사용
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}