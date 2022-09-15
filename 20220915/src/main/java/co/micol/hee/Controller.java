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
import co.micol.hee.member.command.MemberInsert;
import co.micol.hee.member.command.MemberJoinForm;
import co.micol.hee.member.command.MemberSelect;
import co.micol.hee.member.command.MemberSelectList;

/**
 * Servlet implementation class Controller
 * 모든 .do 요청을 분석하고 처리한다.
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>(); //요청한 값을 저장하기 위해. 초기화 Map
       
    public Controller() {
        super();
        
    }

	public void init(ServletConfig config) throws ServletException {
		//모든 요청을 등록하는 곳
		map.put("/main.do", new MainCommand()); //처음 시작하는 페이지
		map.put("/memberSelectList.do", new MemberSelectList()); //맴버목록보기
		map.put("/memberSelect.do", new MemberSelect()); //
		map.put("/memberJoinForm.do", new MemberJoinForm()); //맴버입력화면
		map.put("/memberInsert.do", new MemberInsert()); //맴버입력 처리
	
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청을 분석하고, 처리하고, 결과를 돌려주는 곳
		request.setCharacterEncoding("utf-8"); //request가 넘어올때 한글깨짐 방지
		String uri = request.getRequestURI(); //도메인을 제외한 실제 요청정보 
		String contextPath = request.getContextPath();//uri에서 contextPath구함
		String page = uri.substring(contextPath.length()); //처리할 요청명 구함
	
		System.out.println(page);
		
		Command command = map.get(page); //처리할 command를 찾음
		String viewPage = command.exec(request, response); //Command를 실행하고 돌려줄 페이지를 받음
		
		//페이지를 찾을 뷰리절브 / 돌아온 뷰페이지가 끝에 .do가 포함되어있지 않다면.
		if(!viewPage.endsWith(".do")) {
			viewPage = "/WEB-INF/views/" + viewPage + ".jsp"; //서버픽스 + 이름 + jsp
		
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			
		} else {
			response.sendRedirect(viewPage); //main.do
		}
		
		
	}

}
