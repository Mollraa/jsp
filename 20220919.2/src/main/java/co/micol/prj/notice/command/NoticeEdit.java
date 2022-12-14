package co.micol.prj.notice.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeEdit implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 수정
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("noticeId")));
		vo.setNoticeDate(Date.valueOf(request.getParameter("noticeDate")));
		vo.setNoticeTitle(request.getParameter("noticeTitle"));
		vo.setNoticeSubject(request.getParameter("noticeSubject"));
		
		
		System.out.println("넘어왔음22");
		//첨부파일 처리하고
		String viewPage="notice/noticeError";
		int n = dao.noticeUpdate(vo);
		if(n != 0) {
			vo = dao.noticeSelect(vo);
			viewPage = "notice/noticeSelect"; //수정완료시 완료된 내용 보여주는 곳
		} else {
			request.setAttribute("message", "데이터가 수정되지 않았습니다.");
		}
		return viewPage;
	}

}
