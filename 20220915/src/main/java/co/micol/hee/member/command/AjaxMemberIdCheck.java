package co.micol.hee.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.member.service.MemberService;
import co.micol.hee.member.serviceImpl.MemberServiceImpl;

public class AjaxMemberIdCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// Ajax로 아이디 중복 체크를 하는 부분.
		// 리턴값이 true면 사용가능한 아이디.
		
		//리턴값을 만들자
		MemberService dao = new MemberServiceImpl();
		String id = request.getParameter("id");
		boolean b = dao.isMemberId(id);
		String str = "ajax:0"; //페이지에 돌려줄 값을 담을 변수
		if(b) {
			str = "ajax:1"; //view Resolve에 Ajax호출이라는 것을 알려주기위해
			
		}
		
		return str;
	}

}
