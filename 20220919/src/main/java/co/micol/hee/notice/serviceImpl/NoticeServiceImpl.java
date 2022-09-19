package co.micol.hee.notice.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.hee.common.DataSource;
import co.micol.hee.mapper.NoticeMapper;
import co.micol.hee.notice.service.NoticeService;
import co.micol.hee.notice.service.NoticeVO;

public class NoticeServiceImpl implements NoticeService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true); //.openSession()이렇게 하면 오토커밋 안됨 
	private NoticeMapper map = sqlSession.getMapper(NoticeMapper.class);
	@Override
	public List<NoticeVO> noticeSelectList() {
		// 매소드랑 같은이름 
		return map.noticeSelectList();
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		
		return map.noticeSelect(vo);
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		
		return map.noticeInsert(vo);
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
	
		return map.noticeUpdate(vo);
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
	
		return map.noticeDelete(vo);
	}

	@Override
	public void noticeHitUpdate(int id) {
	map.noticeHitUpdate(id);
	}

	@Override
	public List<NoticeVO> noticeSearchList(String key, String val) {
		// TODO Auto-generated method stub
		return map.noticeSearchList(key, val);
	}

}
