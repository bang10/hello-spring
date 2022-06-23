package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //같은 인스턴스를 사용 함
    //DI 의존성
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        //Optional<Member> one = Optional.of(memberService.findOne(saveId).get());
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //예외 처리 (더 중요함)
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1. setName("spring");

        Member member2 = new Member();
        member2. setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");

        //상당히 비효율 적
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("12");
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