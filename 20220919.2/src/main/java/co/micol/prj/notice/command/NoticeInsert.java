package co.micol.prj.notice.command;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

//스프링 배우기전.. 파일 업로드 방법 3가지..part1.cos...
public class NoticeInsert implements Command { // cos라이브러리 사용시...MultipartRequest객체사용
	// 파일 저장공간
	private String saveFolder = "c:\\fileUploadTest"; // 실제파일 저장공간
	private String charactSet = "utf-8"; // 전송되는 문자열 엔코딩 타입
	private int maxSize = 1024 * 1024 * 1024; // 업로드할 파일 최대사이즈 / 최대 100매가 정도됨.

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 등록
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		String viewPage = "notice/noticeError";
		// 파일처리
		String fileName = "";
		String originalFileName = "";

		try {
			MultipartRequest multi = new MultipartRequest(request, saveFolder, maxSize, charactSet,
															new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("file"); // 실제 물리적 위치에 파일저장 / noticeWriteForm.jsp의 <input type="file"
														// id="file" name="file"> => name가져온다.
			originalFileName = multi.getOriginalFileName("file"); // 실제파일명

			vo.setNoticeWriter(multi.getParameter("noticeWriter"));
			vo.setNoticeDate(Date.valueOf(multi.getParameter("noticeDate")));
			vo.setNoticeTitle(multi.getParameter("noticeTitle"));
			vo.setNoticeSubject(multi.getParameter("noticeSubject"));
			vo.setNoticeAttech(originalFileName);
			vo.setNoticeAttechDir(saveFolder + File.separator + fileName); // File.separator : 리눅스랑 윈도우에서 자유롭게 사용가능.

		} catch (IOException e) {
			e.printStackTrace();
		} // 파일처리end

		// 첨부파일이 있을시 이곳에서 처리함
		int n = dao.noticeInsert(vo);
		if (n != 0) {
			viewPage = "noticeSelectList.do";
		} else {
			request.setAttribute("message", "게시글 등록이 실패했다.");
		}

		return viewPage;

		// vo.setNoticeWriter(request.getParameter("noticeWriter"));
		// vo.setNoticeDate(Date.valueOf(request.getParameter("noticeDate"))); //문자를
		// java Date객체로 변환
		// vo.setNoticeTitle(request.getParameter("noticeTitle"));
		// vo.setNoticeSubject(request.getParameter("noticeSubject"));

	}

}
