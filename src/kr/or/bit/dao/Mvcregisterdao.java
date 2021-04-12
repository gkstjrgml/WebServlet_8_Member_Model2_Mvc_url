package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.or.bit.dto.registerdto;
import kr.or.bit.utils.ConnectionHelper;
public class Mvcregisterdao {

	/*
	참고용 ^^
	 
	static DataSource ds;
	//1. 생성자에서 ds 객체 초기화 가능 
	//2. static 자원 초기화  static { } 초기자 함수
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	static {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			Context envctx = (Context)ctx.lookup("java:comp/env"); //기본 설정
			ds = (DataSource)envctx.lookup("/jdbc/oracle"); //context.xml (name="jdbc/oracle")
			
		}catch (Exception e) {
			System.out.println("look up fail : " + e.getMessage());
		}
	} 
	  
	*/
	
	//CRUD
	
	//writeOk ...
	//insert into mvcregister(id,pwd,email) values(?,?,?)
	public int writeOk(registerdto dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int resultrow=0;
			
		try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="insert into mvcregister(id,pwd,email) values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1,dto.getId());
				pstmt.setString(2,dto.getPwd());
				pstmt.setString(3,dto.getEmail());
				
				resultrow = pstmt.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return resultrow;
	}
}
