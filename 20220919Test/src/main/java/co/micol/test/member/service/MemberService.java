package co.micol.test.member.service;

import java.util.List;

public interface MemberService { 
	//CRUD
	List<MemberVO> memberSelectList(); //전체목록
	MemberVO memberSelect(MemberVO vo); //한명 데이터
	int memberInsert(MemberVO vo); // 데이터 추가
	int memberUpdate(MemberVO vo); // 데이터 갱신
	int memberDelete(MemberVO vo); // 데이터 삭제
	
	boolean isMemberId(String id); // 아이디 중복 체크(존재하면false)
}
