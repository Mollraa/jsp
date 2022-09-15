package co.micol.hee.member.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.member.service.MemberService;
import co.micol.hee.member.service.MemberVO;
import co.micol.hee.member.serviceImpl.MemberServiceImpl;

@WebServlet("/MemberList")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberList() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 수행할 코드를 적는곳(맴버목록 가져오기)
		response.setCharacterEncoding("utf-8");
		MemberService dao = new MemberServiceImpl(); // 인터페이스는 자시자신을 초기화 하지 못해서 부여체로해줌
		List<MemberVO> members = new ArrayList<>();
		members = dao.memberSelectList();
		request.setAttribute("members", members); // request.객체에 / "members"라는 변수로 / members(리스트타입)결과를 담는다.
		String viewPage ="/WEB-INF/views/member/memberList.jsp"; // 결과를 돌려줄 페이지 / 서버에서 미리접근해서 결과만 던저줌
		
		//☆RequestDispatcher / 요청시 마다 request, response가 생성되기 때문에 같이 묶어서 전달해줘야한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
