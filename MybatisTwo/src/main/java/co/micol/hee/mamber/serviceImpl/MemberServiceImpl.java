package co.micol.hee.mamber.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.hee.common.DataSource;
import co.micol.hee.mamber.service.MemberService;
import co.micol.hee.mamber.service.MemberVO;
import co.micol.hee.mybatis.mapper.MemberMapper;

public class MemberServiceImpl implements MemberService {
	// 데이타 소스객체 연결해준당
	private SqlSession sqlSession = DataSource.getSession().openSession(true);
	private MemberMapper map = sqlSession.getMapper(MemberMapper.class);

	@Override
	public List<MemberVO> memberSelectList() {
		// TODO Auto-generated method stub
		return map.memberSelectList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberInsert(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// TODO Auto-generated method stub
		return map.memberDelete(vo);
	}

	@Override
	public boolean isIdCheck(String id) {
		// TODO Auto-generated method stub
		return map.isIdCheck(id);
	}

}
