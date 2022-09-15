package co.micol.hee.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.member.service.MemberService;
import co.micol.hee.member.service.MemberVO;
import co.micol.hee.member.serviceImpl.MemberServiceImpl;

public class MemberSelectList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberService dao = new MemberServiceImpl(); // 인터페이스는 자시자신을 초기화 하지 못해서 부여체로해줌
		List<MemberVO> members = new ArrayList<>();
		members = dao.memberSelectList();
		request.setAttribute("members", members); // request.객체에 / "members"라는 변수로 / members(리스트타입)결과를 담는다.
		return "member/memberList";
	}

}
