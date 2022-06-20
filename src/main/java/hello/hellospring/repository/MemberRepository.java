package hello.hellospring.repository;


import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //저장
    Optional<Member> findById(Long id);
    Optional<Member> findBtName(String name);
    List<Member> findAll();

}
