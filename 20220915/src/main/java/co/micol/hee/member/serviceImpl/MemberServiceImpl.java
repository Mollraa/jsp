package co.micol.hee.member.serviceImpl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.hee.common.DataSource;
import co.micol.hee.member.service.MemberService;
import co.micol.hee.member.service.MemberVO;

public class MemberServiceImpl implements MemberService {
	private DataSource dao = new DataSource(); // 데이터 연결객체 생성
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<MemberVO> memberSelectList() {
		// 전체 맴버 목록 가져오기
		List<MemberVO> list = new ArrayList<>();
		MemberVO vo;
		String sql = "SELECT * FROM MEMBER";
		try {

			psmt = dao.conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_Password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberTel(rs.getString("member_tel"));
				vo.setMemberAuthor(rs.getString("member_author"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// 한명의 데이터 조회
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? ";
		try {
			psmt = dao.conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPassword(rs.getString("member_Password"));
				vo.setMemberName(rs.getString("member_name"));
				vo.setMemberTel(rs.getString("member_tel"));
				vo.setMemberAuthor(rs.getString("member_author"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// 한명의 데이터를 추가(=입력)
		int n = 0;
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?)";
		try {
			psmt = dao.conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPassword());
			psmt.setString(3, vo.getMemberName());
			psmt.setString(4, vo.getMemberTel());
			psmt.setString(5, vo.getMemberAuthor());
			n = psmt.executeUpdate(); // 결과 set을 리턴. insert 성공 = 1, 실패 = 0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 한명의 데이터 변경, 아이디 빼고 모든 값 변경 가능 함.
		int n = 0;
		String sql = "UPDATE MEMBER SET MEMBER_PASSWORD = ?, MEMBER_NAME = ?, MEMBER_TEL = ?,"
				+ " MEMBER_AUTHOR = ? WHERE MEMBER_ID = ? ";
		try {
			psmt = dao.conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberPassword());
			psmt.setString(2, vo.getMemberName());
			psmt.setString(3, vo.getMemberTel());
			psmt.setString(4, vo.getMemberAuthor());
			psmt.setString(5, vo.getMemberId());
			n = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// 한명의 맴버삭제
		int n = 0;
		String sql = "DELETE * FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			psmt = dao.conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			n = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public boolean memberIdcheck(String id) {
		// 아이디 중복체크, 존재하면 false로 해줌.
		boolean b = false; // false or true 내가편한거로 지정
		String sql = "SELECT * MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			psmt = dao.conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (!rs.next()) {
				b = true; // 존재하지 않으면
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return b;
	}

	// DBMS와 연결을 끊어주는 메소드.
	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (dao.conn != null)
				dao.conn.close();

		} catch (SQLException e) {

		}
	}
}
