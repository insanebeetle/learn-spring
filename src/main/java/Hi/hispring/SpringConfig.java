package Hi.hispring;

import Hi.hispring.repository.JdbcTemplateMemberRepository;
import Hi.hispring.repository.MemberRepository;
import Hi.hispring.repository.MemoryMemberRepository;
import Hi.hispring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource datasource;

    public SpringConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(datasource);
    }

}
