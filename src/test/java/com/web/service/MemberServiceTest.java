package com.web.service;

import com.web.domain.Member;
import com.web.repository.MemberRepository;
import com.web.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    void beforeEach(){
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    // 테스트 실행 후 메모리 DB clean
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("jiyeong");
        //when
        Long memberId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(memberId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("psh");

        Member member2 = new Member();
        member2.setName("psh");
        //when
        memberService.join(member1);


        //try catch 대체 assertThrows
        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}