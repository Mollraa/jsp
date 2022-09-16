package co.micol.hee.mybatis.mapper;

import java.util.List;

import co.micol.hee.mamber.service.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList();

	MemberVO memberSelect(MemberVO vo); //한명조회하고 로그인도 체크

	int memberInsert(MemberVO vo);

	int memberUpdate(MemberVO vo);

	int memberDelete(MemberVO vo);

	boolean isIdCheck(String id); // 아이디 중복체크


}
