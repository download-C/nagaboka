package nagaboka;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nagaboka.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
	})
public class SecurityMemberTests {
	// 멤버변수 =========================
	private static final Logger log = LoggerFactory.getLogger(SecurityMemberTests.class);
	
	@Inject
	private PasswordEncoder pwEncoder;
	
	@Inject
	private DataSource ds;
	
	@Inject
	private SqlSession session;
	
	final static String NAMESPACE = "com.nagaboka.mapper.UserMapper";
	// 멤버변수 끝 =========================
	
	
//	@Test   // ㅇㅋ
	public void insertUserTest() throws Exception {
		log.info("(∩^o^)⊃━☆ insertUserTest  실행됨");
		
		// 마이바티스 뭐 이런 거 안 쓰고,, 일단 연결 잘 되나 테스트~
		Connection con = null;
		PreparedStatement pstmt = null;
		
		for(int i = 0; i < 10; i++) {
			con = ds.getConnection();
			String sql = "insert into nagaboka.user(u_id, u_pw, u_name) "
						+ "values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
//			pstmt.setString(2, "pw"+i);
				// 1번 user 비번 => pw1
				// 2번 user 비번 => pw2
				// 3번 user 비번 => pw3
			pstmt.setString(2, pwEncoder.encode("pw"+i));   // 암호화하기!!!!!!!
			
			if( i < 9 ) {
				// 유저 만들고
				pstmt.setString(1, "user"+i);
				pstmt.setString(3, "사용자"+i);
			} else {
				// 관리자 만들고
				pstmt.setString(1, "admin"+i);
				pstmt.setString(3, "관리자"+i);
			}
			
			pstmt.executeUpdate();
			
		} // for
		
	} // insertuserTest()
	
	
	
//	@Test   // ㅇㅋ
	public void 인증정보테이블채우기() throws Exception {
		log.info("(∩^o^)⊃━☆ 인증정보테이블채우기  호출됨");

		Connection con = null;
		PreparedStatement pstmt = null;
		
		for(int i = 0; i < 10; i++) {
			con = ds.getConnection();
			String sql = "insert into user_auth(u_id, auth) "
						+ "values(?, ?)";
			pstmt = con.prepareStatement(sql);

			if( i < 9 ) {
				pstmt.setString(1, "user"+i);
				pstmt.setString(2, "ROLE_MEMBER");
			} else {
				pstmt.setString(1, "admin"+i);
				pstmt.setString(2, "ROLE_ADMIN");
			}
			
			pstmt.executeUpdate();
			
		}// for
		
	} // 인증정보테이블채우기()
	
	
	
	@Test   // ㅇㅋ
	public void getUserTest() throws Exception {
		log.info("(∩^o^)⊃━☆ getUserTest  호출됨");
		
		String u_id = "admin9";
		UserVO vo = session.selectOne(NAMESPACE+".getUser", u_id);
		log.info("(∩^o^)⊃━☆ getUserTest  가져온 횐 정보: " + vo);
		
		vo.getAuthList()
		.forEach(userAuthVO -> log.info("(∩^o^)⊃━☆ getUserTest  UserVO -> " + userAuthVO));
		
	} // getUserTest()

	
	
}
