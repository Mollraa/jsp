package co.micol.hee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.hee.notice.service.NoticeVO;

public interface NoticeMapper {
	List<NoticeVO> noticeSelectList();
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
	void noticeHitUpdate(int id); //글을 하나 읽으면 
	List<NoticeVO> noticeSearchList(@Param("key") String key, @Param("val")String val); //mapper두개 이상일 때 @Param을 꼭 붙혀준다!!!
}
