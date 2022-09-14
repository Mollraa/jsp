package co.micol.prj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.DAO;
import oracle.jdbc.logging.annotations.DisableTrace;

@WebServlet("/FirstServlet")//언어텐션
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//임시로 DB내용가져옴
	private PreparedStatement psmt; //DBMS에 명령을 보내고
	private ResultSet rs; //실행된 결과를 돌려받을 때.
    
    public FirstServlet() {
        super();
        
    }
    

	//get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO(); //데이터 베이스 연결을 확인한다.
		String sql ="select * from member";
		try {
			psmt = dao.conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getString("member_id"));
				System.out.println(rs.getString("member_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); //한글설정
		response.setContentType("text/html; charset=UTF-8"); //index.jsp에 주소값
		String name = request.getParameter("name");
		PrintWriter out = response.getWriter(); //html 문서를 만드는 메소드
		if(!name.isEmpty()) {
			out.print("넘어온 이름은 :  ");
			out.print(name);
		}
		
		out.print("넘어온 아이디 값 : ");
		out.print(request.getParameter("id"));
		out.print("<br>");
		out.print("넘어온 비밀번호 값 : ");
		out.print(request.getParameter("password"));
		
//		out.print("<html>"); //out.print 화면출력방식
//		out.print("<head>");
//		out.print("</head>");
//		out.print("<body>");
//		out.print("<h1>행복한 세상</h1>");
//		out.print("</body>");
//		out.print("</html>");
	}

	//post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
