package co.micol.hee.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public String exec(HttpServletRequest request, HttpServletResponse response);
	// String값으로 HttpServletRequest request, HttpServletResponse response 받겠다.

}
