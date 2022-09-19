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
import co.micol.hee.member.command.MemberLogin;
import co.micol.hee.member.command.MemberLoginForm;
import co.micol.hee.member.command.MemberLogout;
import co.micol.hee.notice.command.NoticeInsert;
import co.micol.hee.notice.command.NoticeSelect;
import co.micol.hee.notice.command.NoticeSelectList;
import co.micol.hee.notice.command.NoticeWriterForm;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
    
    public FrontController() {
        super();
        
    }

	public void init(ServletConfig config) throws ServletException {
		//명령집단 저장하는 곳
		map.put("/main.do", new Main()); //처음 도착하는 페이지
		map.put("/noticeSelectList.do", new NoticeSelectList()); //noticeSelectList.jsp/ 공지글 전체 목록 보기
		map.put("/noticeWriterForm.do", new NoticeWriterForm()); //NoticeWriterForm
		map.put("/noticeInsert.do", new NoticeInsert()); // noticeInsert.do/ 게시글 등록
		map.put("/noticeSelect.do", new NoticeSelect()); //noticeSelectList.jsp
		map.put("/memberLoginForm.do", new MemberLoginForm()); // 로그인
		map.put("/memberLogin.do", new MemberLogin());// 로그인처리
		map.put("/memberLogout.do", new MemberLogout()); // 로그아웃처리
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//실제 수행할 서비스
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		System.out.println("uri: " + uri);
		System.out.println("contextPath: " + contextPath);
		System.out.println("page: " + page);
		
		Command command = map.get(page);
		String viewpage = command.exec(request, response);
		
		System.out.println("command: " + command);
		System.out.println("viewpage: " + viewpage);
		
		if(!viewpage.endsWith(".do")) {
			if(viewpage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewpage.substring(5));
				return;
			} else {
				viewpage = "/WEB-INF/views/" + viewpage + ".jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage);
				dispatcher.forward(request, response);
			} 
		} else {
			response.sendRedirect(viewpage);
		}
	}

}
