package co.micol.hee.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	String exec(HttpServletRequest request, HttpServletResponse response); //서버가 만들어줘서 자동으로 담김
	

}
