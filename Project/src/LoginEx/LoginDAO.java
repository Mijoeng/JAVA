package LoginEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginDAO {

	
	public void delete(String id, String pwd) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Members where id = ? and pw = ? ");
		
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LoginVO login(LoginVO user) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, pw, name ");
		sql.append(" from Members ");
		sql.append(" where id = ? and pw = ? ");
		
		
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				pstmt.setString(1, user.getUid());
				pstmt.setString(2, user.getPw());
				
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					
					String uid = rs.getString("id");
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					LoginVO log = new LoginVO(uid, name, pw);
					return log;
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		
	}
	
	

	public void insert(List<LoginVO> list) {
		// db접속해서 insert
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Members(id, name, pw) values (?, ?, ? )");
		
		
		try(
			Connection conn 
				= new ConnectionFactory().getConnection();
			PreparedStatement pstmt 
				= conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, list.get(0).getUid());
			pstmt.setString(2, list.get(0).getName());
			pstmt.setString(3, list.get(0).getPw());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	public void record(String id, int cnt1, int cnt2) {
		StringBuilder sql = new StringBuilder();
		sql.append("update Members set correct_count= correct_count + ?, wrong_count= wrong_count + ? where id = ? ");
		
		try (
				Connection conn 
				= new ConnectionFactory().getConnection();
			PreparedStatement pstmt 
				= conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, cnt1);
			pstmt.setInt(2, cnt2);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<LoginVO> showMembers() {
		List<LoginVO> list = new ArrayList<LoginVO>();
//		select * from members
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name, pw from members ");
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					
					String uid = rs.getString("id");
					String name = rs.getString("name");
					String pw = rs.getString("pw");
					LoginVO member = new LoginVO();
					member.setUid(uid);
					member.setName(name);
					member.setPw(pw);
					list.add(member);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

	public int managerLogin(ManagerVO temp) {
//		select * from manager where id=? and pw=?
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append(" from manager ");
		sql.append(" where id = ? and pw = ? ");
		
		
		
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
				pstmt.setString(1, temp.getId());
				pstmt.setString(2, temp.getPassword());
				
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					return 1;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		
		
	}
	
}


