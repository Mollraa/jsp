package co.micol.hee.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.hee.common.Command;

public class NoticeWriterForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글쓰기 폼 호출
		return "notice/noticeWriterForm";
	}

}
