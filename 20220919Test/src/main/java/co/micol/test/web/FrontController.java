package co.micol.test.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.test.MainCommand;
import co.micol.test.common.Command;
import co.micol.test.common.MemberLoginForm;
import co.micol.test.common.MemberLogin;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet { // HttpServlet : ajax
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
       
   
    public FrontController() {
        super();

    }

	public void init(ServletConfig config) throws ServletException {
    	//명령집단 저장
		map.put("/main.do", new MainCommand()); //처음도착 페이지
		map.put("/memberLoginForm.do", new MemberLoginForm()); //로그인 폼 호출
		map.put("/memberLogin.do", new MemberLogin()); //로그인 처리
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//실제 수행 서비스
		request.setCharacterEncoding("utf-8"); //한글처리
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());

		System.out.println("page: " + page); //페이지 보이게
		
		Command command = map.get(page); //value
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do")) { //.do가 아니면
			if(viewPage.startsWith("ajax:")) { //ajax처리를 위한 view Resolve
				response.setContentType("text/html; charset=UTF-8"); //로 주겠다.
				response.getWriter().append(viewPage.substring(5)); //ajax: 요 많큼 잘라줌
				return;
			} else { //리턴 값이 보여줄 페이지를 가지고 올때
				viewPage = "/WEB-INF/views/" + viewPage + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				//viewPage 처리결과를 dispatcher에 담아준다. Servlet -> request
				dispatcher.forward(request, response); //<jsp:forward>
			}
			System.out.println("viewPage: "+ viewPage);
		} else {
			response.sendRedirect(viewPage); //리턴값이 *.do로 올때 처리
		}
	}

}
