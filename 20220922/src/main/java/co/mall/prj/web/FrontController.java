package co.mall.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mall.prj.Main;
import co.mall.prj.common.Command;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.yd")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 명령 집단 저장소
		map.put("/main.yd", new Main()); // 처음도착 페이지
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 실제 수행 서비스
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());

		System.out.println("uri: " + uri);
		System.out.println("page: " + page);

		Command command = map.get(page);
		String viewPage = command.exec(request, response);

		if (!viewPage.endsWith(".yd")) {
			if (viewPage.startsWith("ajax:")) { // ajax를 사용할 때
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			} else {
				if (viewPage.startsWith("no:")) { // Tiles 적용 안할때
					// = if (viewPage.startsWith("noTiles:")) {}
					// viewPage = "/WEB-INF/views/" + viewPage + ".jsp";

					viewPage = "/WEB-INF/views/" + viewPage.substring(3) + ".jsp";
					System.out.println("viewPage: " + viewPage);

				} else {
					viewPage = viewPage + ".tiles"; // tiles layout 사용
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect(viewPage); // .yd return
		}
	}
}
