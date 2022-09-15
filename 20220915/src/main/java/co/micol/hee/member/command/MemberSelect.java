package co.micol.hee.member.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.member.service.MemberService;
import co.micol.hee.member.service.MemberVO;
import co.micol.hee.member.serviceImpl.MemberServiceImpl;

public class MemberSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 한명 조회
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("id")); // 폼에서 넘어온 아이디 담고
		vo = dao.memberSelect(vo); // 검색결과 열기

		request.setAttribute("member", vo); // 페이지에 전달하기 위해

		return "member/memberSelect";
	}

}
