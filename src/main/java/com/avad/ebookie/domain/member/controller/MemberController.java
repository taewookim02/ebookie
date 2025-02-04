package com.avad.ebookie.domain.member.controller;

import com.avad.ebookie.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;


}
