package co.micol.hee.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command { // 실행할 command interface
	String exec(HttpServletRequest request, HttpServletResponse response); //interface 1개 만들었어

}
