package co.micol.hee.notice.service;

import java.util.List;

public interface NoticeService {
	List<NoticeVO> noticeSelectList();
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
	void noticeHitUpdate(int id); //글을 하나 읽으면 
	List<NoticeVO> noticeSearchList(String key, String val); //게시글 검색
}
