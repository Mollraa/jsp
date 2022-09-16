package co.micol.hee;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.member.command.AjaxMemberIdCheck;
import co.micol.hee.member.command.MemberInsert;
import co.micol.hee.member.command.MemberJoinForm;
import co.micol.hee.member.command.MemberSelect;
import co.micol.hee.member.command.MemberSelectList;

/**
 * Servlet implementation class Controller 모든 .do 요청을 분석하고 처리한다.
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>(); // 요청한 값을 저장하기 위해. 초기화 Map

	public Controller() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		// 모든 요청을 등록하는 곳
		map.put("/main.do", new MainCommand()); // 처음 시작하는 페이지
		map.put("/memberSelectList.do", new MemberSelectList()); // 맴버목록보기
		map.put("/memberSelect.do", new MemberSelect()); //
		map.put("/memberJoinForm.do", new MemberJoinForm()); // 맴버입력화면
		map.put("/memberInsert.do", new MemberInsert()); // 맴버입력 처리
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck()); // 아이디 중복체크. 다른 커멘드들과 달리 ajax로 처리해서
																	// ajaxMemberIdCheck로 이름정해줌

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청을 분석하고, 처리하고, 결과를 돌려주는 곳
		request.setCharacterEncoding("utf-8"); // 한글깨짐 방지
		String uri = request.getRequestURI(); // 도메인을 제외한 실제 요청정보
		String contextPath = request.getContextPath(); // contextPath 가져오기

		String page = uri.substring(contextPath.length()); // 처리할 요청명 구함. "contextPath/실제요청"이니까 contextPath길이 이후부터 가져오는거
		System.out.println("url: " + request.getRequestURL());
		System.out.println("uri: " + uri);
		System.out.println("contextPath: " + contextPath);
		System.out.println("page: " + page);

		Command command = map.get(page); // 처리할 Command를 찾음
		String viewPage = command.exec(request, response); // Command를 실행하고 돌려줄 페이지를 받음

		// 페이지를 찾을 뷰리절브 / 돌아온 뷰페이지가 끝에 .do가 포함되어있지 않다면.
		if (!viewPage.endsWith(".do")) {
			if (!viewPage.startsWith("ajax:")) { // 아작스를 처리하기위한 뷰리절브
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			} else { // 리턴값이 보여줄 페이지를 가지고올때
				viewPage = "/WEB-INF/views/" + viewPage + ".jsp"; // 서버픽스 + 이름 + jsp
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}

		} else {
			response.sendRedirect(viewPage); // main.do
		}

	}

}
