package co.micol.hee.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {
	private static SqlSessionFactory sqlSessionFactory;

	private DataSource() {
	};

	public static SqlSessionFactory getSession() {
		String resource = "config/mybatis-config.xml"; // 파일위치, 이름 정확하게
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource); // 이칭구는 꼭 try문 안에넣어주자
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}

}
