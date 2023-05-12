package com.icia.board.repository;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.MemberDTO;
import com.icia.board.dto.MemberProfileDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public int save(MemberDTO memberDTO) {
        return sql.insert("Member.save",memberDTO);
    }


    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login",memberDTO);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return sql.selectOne("Member.findByMemberEmail", loginEmail);
    }

    public void update(MemberDTO memberDTO) {
        sql.update("Member.update" , memberDTO);

    }

    public void delete(Long id) {
        sql.delete("Member.delete", id);
    }

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    }
}