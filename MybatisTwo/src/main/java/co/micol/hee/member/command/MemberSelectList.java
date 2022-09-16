package co.micol.hee.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.mamber.service.MemberService;
import co.micol.hee.mamber.service.MemberVO;
import co.micol.hee.mamber.serviceImpl.MemberServiceImpl;

public class MemberSelectList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//맴버목록 가져오기
		MemberService dao = new MemberServiceImpl();
		List<MemberVO> list = new ArrayList<>();
		list = dao.memberSelectList();
		request.setAttribute("members", list);

		return "member/memberSelectList";
	}

}
