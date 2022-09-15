package co.micol.hee.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;

public class MemberJoinForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		return "member/memberJoinForm";
	}

}
