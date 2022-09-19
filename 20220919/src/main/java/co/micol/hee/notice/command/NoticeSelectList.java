package co.micol.hee.notice.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;
import co.micol.hee.notice.service.NoticeService;
import co.micol.hee.notice.service.NoticeVO;
import co.micol.hee.notice.serviceImpl.NoticeServiceImpl;

public class NoticeSelectList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지글 전체 목록 보기
		NoticeService dao = new NoticeServiceImpl();
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		list = dao.noticeSelectList();
		request.setAttribute("list", list);
		return "notice/noticeSelectList";
	}

}
