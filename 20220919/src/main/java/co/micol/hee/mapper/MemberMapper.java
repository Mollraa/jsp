package co.micol.hee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.hee.member.service.MemberVO;
import co.micol.hee.notice.service.NoticeVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList();    //전체목록 가져오기
	MemberVO memberSelect(MemberVO vo);   //한명의 데이터 가져오기, 로그인 시 사용
	int memberInsert(MemberVO vo);  //데이터 추가
	int memberUpdate(MemberVO vo);  //데이터 갱신
	int memberDelete(MemberVO vo);	//데이터 삭제	
	
	boolean isMemberId(String id);  //아이디 중복체크  존재하면 false, 
}
