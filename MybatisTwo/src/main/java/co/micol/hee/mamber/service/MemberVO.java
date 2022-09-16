package co.micol.hee.mamber.service;

import lombok.Data;

@Data
public class MemberVO { //DB켜서 꼭 보고만들기, 만들고나서 -> 서비스만들어주러가자
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberTel;
	private String memberAuthor; 
	//오류라인 없으면 롬복이 잘 실행되고 있는 중이다.
	
	
}
