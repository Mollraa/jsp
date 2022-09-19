package co.micol.hee.member.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.hee.common.DataSource;
import co.micol.hee.mapper.MemberMapper;
import co.micol.hee.member.service.MemberVO;

public class MemberServiceImpl implements co.micol.hee.member.service.MemberService {

	  private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	   MemberMapper map = sqlSession.getMapper(MemberMapper.class);

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
	   public boolean isMemberId(String id) {
	      // TODO Auto-generated method stub
	      return map.isMemberId(id);
	   }

}
