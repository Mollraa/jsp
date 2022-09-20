package co.micol.prj.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeEditForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 수정 폼 호출
		System.out.println("넘어왔음");
		//수정 값 가져옴
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("id"))); // <input type="hidden" id="noticeId" name="noticeId" value="${vo.noticeId }">
		vo = dao.noticeSelect(vo);
		request.setAttribute("vo", vo);
		return "notice/noticeEditForm";
	}

}
