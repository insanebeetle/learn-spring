package Hi.hispring.controller;


import Hi.hispring.controller.domain.Member;
import Hi.hispring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //스프링이 싱행될대 스프링 컨테이너에 넣어놨다가
public class MemberController {

    private final MemberService memberService;
    //@Autowired //이놈이 스프링 콘테이너에 등록? 연결을 시켜놔줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //멤버서비스 클래스는 여러곳에서 많이 쓰임.
    // 근데 타 클래스에서 일일이 만들기보다는 하나의 객체를 돌림빵하는게 편함
    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //브라우저에서 포스트로 요청이 올때 이걸 읽어 들임, get방식은 위코드로
    public String create(MemberForm form){  //브라우저의 name값이 MemberForm의 name으로 자동으로 들어감
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
