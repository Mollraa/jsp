package co.micol.hee.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.Main;
import co.micol.hee.common.Command;
import co.micol.hee.member.command.MemberJoinForm;
import co.micol.hee.member.command.MemberSelect;
import co.micol.hee.member.command.MemberSelectList;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 기억장소
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		// "main.do" 요청과 명령을 매핑하는 곳
		map.put("/main.do", new Main());
		map.put("/memberSelectList.do", new MemberSelectList());
		map.put("/memberJoinForm.do", new MemberJoinForm()); 
		map.put("/memberSelect.do", new MemberSelect());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청을 분석하고 실행하고 결과를 돌려주는 곳
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length()); // memberSelectList.do

		Command command = map.get(page);
		String viewPage = command.exec(request, response); // 요청결과 수행

		if (!viewPage.endsWith(".do")) {
			if (viewPage.startsWith("ajax:")) {
				// ajax
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5)); // 5글자뺴고
				return;

			} else {
				viewPage = "/WEB-INF/views/" + viewPage + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect(viewPage);
		}

	}
}
