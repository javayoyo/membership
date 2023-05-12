package com.icia.board.controller;


import com.icia.board.dto.MemberDTO;
import com.icia.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/save")
    public String SaveForm() {
        return "memberPages/memberSave";
    }

    @PostMapping("/save")
    public String save (@ModelAttribute MemberDTO memberDTO) {
        int saveResult = memberService.save(memberDTO);
        if(saveResult > 0) {
            return "memberPages/memberLogin";
        }else {
            return "index";
        }
    }



    @PostMapping("/email-check")
    public ResponseEntity emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);

        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);

        if(memberEmail.length() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else if(memberDTO == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/login")
    public String loginForm() {
        return "memberPages/memberLogin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.login(memberDTO);

        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());

            return "redirect:/board/list";
        } else {

            return "memberPages/memberLogin";
        }
    }

    @GetMapping ("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";

    }

    @GetMapping("/mypage")
    public String myPage() {
        return "memberPages/memberMain";
    }

    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        String loginEmail = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO  = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member" , memberDTO);
        return "memberPages/memberUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        memberService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberPages/memberList";
    }

}