package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { //
  MemoryMemberRepository repository = new MemoryMemberRepository();
  @AfterEach
  public void clearStore(){ //이 클래스와 같은 이름을 가진 메서드가 다른 클래스에 존재하지만 클래스가 다르기 때문에 충돌이 일어나지 않는다.
    repository.clearStore();
  }

  @Test
  public void save(){
    Member member = new Member();
    member.setName("진성민");

    repository.save(member);
    Member result = repository.findById(member.getId()).get();
    assertThat(member).isEqualTo(result);
  }
  @Test
  public void findByName(){
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring1").get();

    assertThat(result).isEqualTo(member1);
  }
  @Test
  public void findAll(){
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);

  }

}
