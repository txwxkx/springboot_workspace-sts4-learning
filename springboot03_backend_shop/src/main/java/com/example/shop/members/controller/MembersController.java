package com.example.shop.members.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.members.dto.AuthInfo;
import com.example.shop.members.dto.MembersDTO;
import com.example.shop.members.service.MembersService;


@CrossOrigin("*")
@RestController
public class MembersController {

	@Autowired
	private MembersService membersService;
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	public MembersController() {

	}
	
	//회원가입 폼
//		@RequestMapping(value="/member/signup.do", method=RequestMethod.GET)
//		public ModelAndView addMember(ModelAndView mav) {
//			mav.setViewName("member/signup");
//			return mav;
//		}
	
	/*
	 * jwt를 이용한 회원가입 처리(이제 세션 못쓴다)
	 * 
	 */
	
	//회원가입 처리
	@PostMapping("/member/signup")
	public String addMember(@RequestBody MembersDTO membersDTO) {
		// 비밀번호 암호화
		membersDTO.setMemberPass(encodePassword.encode(membersDTO.getMemberPass()));
		AuthInfo authInfo = membersService.addMemberProcess(membersDTO);
		return "redirect:/home.do";
	}
	
	// 회원 정보 불러오기
	@GetMapping("/member/getinfo/{memberEmail}")
	public MembersDTO getMember(@PathVariable("memberEmail") String memberEmail) {
		return membersService.updateMembersProcess(memberEmail);
	}
	
	// 회원수정 처리
	@PostMapping("/member/update")
	public void updateMember(@RequestBody MembersDTO membersDTO) {
		membersDTO.setMemberPass(encodePassword.encode(membersDTO.getMemberPass()));
		membersService.updateMemberProcess(membersDTO);
		
	}
	
	//로그아웃
//		@RequestMapping(value="/member/logout.do")
//		public String logoutMember(HttpSession session) {
//			session.invalidate();
//			return "redirect:/home.do";
//		}
//		
//		//로그인 폼
//		@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
//		public String loginMember() {
//			return "member/login";
//		}
//		
//		
//		//회원정보 수정 폼
//		@RequestMapping(value="member/editmember.do", method=RequestMethod.GET)
//		public ModelAndView updateMember(ModelAndView mav, HttpSession session) {
//			AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
//			mav.addObject("membersDTO", membersService.updateMembersProcess(authInfo.getMemberEmail()));
//			mav.setViewName("member/editmember");
//			return mav;
//		}
//		
//		//회원정보 수정 처리
//		@RequestMapping(value="member/editmember.do", method=RequestMethod.POST)
//		public String updateMember(MembersDTO membersDTO, HttpSession session) {
//			AuthInfo authInfo = membersService.updateMemberProcess(membersDTO);
//			session.setAttribute("authInfo", authInfo);
//			
//			return "redirect:/home.do";
//		}
//		
//		@RequestMapping(value="member/changepass.do", method=RequestMethod.GET)
//		public String changePassword() {
//			return "member/changepass";
//		}
//		
//		@RequestMapping(value="member/changepass.do", method=RequestMethod.POST)
//		public String changePassword(ChangePwdCommand changePass, HttpSession session) {
//			AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
//			try {
//				membersService.updatePassProcess(authInfo.getMemberEmail(), changePass);
//				return "redirect:/home.do";
//			} catch(WrongEmailPasswordException e) {
//				return "member/changepass";
//			}
//		}
		
}//end class
