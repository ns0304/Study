package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
  //회원가입이 잘되는지 테스트
  MemberService memberService;
  MemoryMemberRepository memberRepository;
  @BeforeEach
  public void beforeEach(){
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);

  }

  @AfterEach
  public void clearStore(){ //이 클래스와 같은 이름을 가진 메서드가 다른 클래스에 존재하지만 클래스가 다르기 때문에 충돌이 일어나지 않는다.
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
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
  public void 중복_회원_예외(){
    //give
    Member member1 = new Member();
    member1.setName("spring");
    Member member2 = new Member();
    member2.setName("spring");
    //when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    //then
  }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}