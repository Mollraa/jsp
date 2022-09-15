package co.micol.hee.member.service;

import java.util.List;

public interface MemberService {
//interface = 메소드 정의 / CRUD기본 - 5가지 처리하는 메소드 만들어줌
	
	List<MemberVO> memberSelectList(); //전체목록 가져오기
	MemberVO memberSelect(MemberVO vo); //한명의 목록가져오기, 로그인시 사용
	int memberInsert(MemberVO vo); //데이터 추가. 리턴값이 int
	int memberUpdate(MemberVO vo); //데이터 갱신
	int memberDelete(MemberVO vo); //데이터 삭제
	
	//아이디 중복체크(미리만들어두기 필수)
	//내가 정하는거 존재하면 false, 사용가능 true
	boolean memberIdcheck(String id);
	
}
