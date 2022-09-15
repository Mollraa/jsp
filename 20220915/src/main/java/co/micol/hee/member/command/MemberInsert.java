package co.micol.hee.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.member.service.MemberService;
import co.micol.hee.member.service.MemberVO;
import co.micol.hee.member.serviceImpl.MemberServiceImpl;

public class MemberInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 맴버추가
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPassword(request.getParameter("memberPassword"));
		vo.setMemberName(request.getParameter("memberName"));
		vo.setMemberTel(request.getParameter("memberTel"));
		vo.setMemberAuthor(request.getParameter("memberAuthor"));
		// 성공, 실패에 따라 페이지 나눠줄때
		int n = dao.memberInsert(vo);
		String viewPage = null;
		if (n != 0) {
			// request.setAttribute("message", "정상적으로 입력이 되었습니다.");
			viewPage = "memberSelectList.do";
		} else {
			request.setAttribute("message", "맴버 추가가 실패했습니다.");
			viewPage = "member/memberMessage";
		}
		return viewPage;
	}

}
