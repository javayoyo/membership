package com.icia.board.service;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.MemberDTO;
import com.icia.board.dto.MemberProfileDTO;
import com.icia.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;


    public int save(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO);
    }


    public boolean login(MemberDTO memberDTO) {
        MemberDTO dto = memberRepository.login(memberDTO);
        if(dto != null) {
            return true;
        }else {
            return false;
        }
    }


    public MemberDTO findByMemberEmail(String loginEmail) {
        return memberRepository.findByMemberEmail(loginEmail);
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.update(memberDTO);

    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

}