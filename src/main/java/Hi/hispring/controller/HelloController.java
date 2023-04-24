package Hi.hispring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    //getMapping으로 hello라는 url이 들어오면 아래에서 모델을 호출함
    //리턴값이 html명이고 그 html에 데이터를 addAttribute로 전달가능
    public String hello(Model model){
        model.addAttribute("data","hello!");
                return "hello";
    }
    //참고 : 리소스 파일에 있는 static은 말그대로 정적 웹페이지를 넣으며, 보통 index를 넣는듯? 홈페이지용
    //템플릿은 페이지 라우팅용?인듯
    //요점은 딱히 url을 받아와서 스트링싸서 변환하고하는 똥꼬쇼 필요없음
    //그냥 원하는 ~/url명을 템플릿에 넣고, 컨트롤러 생성해서 getmapping으로 반환해주면 라우팅끝

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name",name);
        //addAttribute의 첫번째 인자는 hello-template의 변수명이고 두번째인자가 들어가는 밸류값
        return "hello-template";
    }
    //requestparam 은 url 파라메타를 넘겨준다는 뜻..
    //ex >  http://localhost:8080/hello-mvc?name=Spring! 여기서 ?name의 값을 url에서 유저가 입력하면
    //결과가 웹브라우저에 출력됨

    @GetMapping("hello-string")
    @ResponseBody //http의 body부에 내용을 직접 넣겠다는 선언
    //이를 사용하면 html을 내려주지 않고 데이터 그 자체를 넘겨버림
    //기본적으로 뷰리졸버로 html을 넘기는데 이를 사용하지 않게함
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    //리스폰 바디를 적용해서 객체를 넘기면 JSON형식이 됨
    //객체가 아니면 그냥 String형식 그대로 넘김
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return  hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
