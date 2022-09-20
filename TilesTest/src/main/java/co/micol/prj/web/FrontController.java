package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.Main;
import co.micol.prj.command.About;
import co.micol.prj.command.Admin;
import co.micol.prj.command.Contact;
import co.micol.prj.command.Shop;
import co.micol.prj.command.ShopSingle;
import co.micol.prj.common.Command;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 명령집단 저장하는 곳
		map.put("/main.do", new Main()); // 처음 도착하는 페이지
		map.put("/about.do", new About());
		map.put("/shop.do", new Shop());
		map.put("/shop-single.do", new ShopSingle());
		map.put("/contact.do", new Contact());
		map.put("/admin.do", new Admin());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 실제 수행할 서비스
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());

		System.out.println("page: " + page);

		Command command = map.get(page);
		String viewPage = command.exec(request, response);

		if (!viewPage.endsWith(".do")) {
			if (viewPage.startsWith("ajax:")) { //ajax를 사용할 때
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			} else {
				if (viewPage.startsWith("no:")) { // Tiles 적용 안할때
				//= if (viewPage.startsWith("noTiles:")) {}
					//viewPage = "/WEB-INF/views/" + viewPage + ".jsp"; 
					
					viewPage = "/WEB-INF/views/" + viewPage.substring(3) + ".jsp";
					System.out.println("viewPage: " + viewPage);
					
				} else {
					viewPage = viewPage + ".tiles"; // tiles layout 사용
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect(viewPage); //.do return
		}
	}

}
