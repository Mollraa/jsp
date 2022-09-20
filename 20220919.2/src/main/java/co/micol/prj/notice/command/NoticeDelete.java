package co.micol.prj.notice.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 삭제
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("id")));
		int n = dao.noticeDelete(vo);
		String viewPage = "notice/noticeError";
		if( n != 0) {
			viewPage ="noticeSelectList.do";
		} else {
			request.setAttribute("message", "삭제를 실패했습니다.");
		}
		return viewPage;
	}

}
