package co.micol.hee.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.notice.service.NoticeService;
import co.micol.hee.notice.service.NoticeVO;
import co.micol.hee.notice.serviceImpl.NoticeServiceImpl;

public class NoticeSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 상세보기
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("id")));
		
		vo = dao.noticeSelect(vo);
		if(vo != null) {
			dao.noticeHitUpdate(vo.getNoticeId()); //조회수증가
			request.setAttribute("vo", vo);
		}
		
		return "notice/noticeSelect";
	}

}
