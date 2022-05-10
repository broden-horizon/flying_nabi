package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public int login(String userEmail, String userPW) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String dbURL = "jdbc:mysql://localhost:3306/FLYING";
		String dbID = "root";
		String dbPW = "12345678";
		String SQL = "SELECT userPW FROM USER WHERE userEmail = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if(rs.getString(1).equals(userPW)) {
					return 1;//log success
				}
				return 0; //pw unmatch
			}
			return -1; //일치하는 아이디가 없음 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return -2; //데이터 베이스 오류
	}
	
	public int join(User user) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String dbURL = "jdbc:mysql://localhost:3306/FLYING";
		String dbID = "root";
		String dbPW = "12345678";
		String SQL = "INSERT INTO USER VALUES (?, ?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserEmail());
			pstmt.setString(2, user.getUserPW());
			pstmt.setInt(3, 0);
			System.out.println(pstmt);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -2; // 데이터베이스 오
		
	}
	
	
	public int getNumOfWriting(String userEmail) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String dbURL = "jdbc:mysql://localhost:3306/FLYING";
		String dbID = "root";
		String dbPW = "12345678";
		String SQL = "SELECT numOfWriting FROM USER WHERE userEmail = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} else {
				return -1; // 에? ㅜ
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -2; // 데이터베이스 오류
		
	}
	public int addNumOfWriting(String userEmail) {
		String SQL = "UPDATE USER SET numOfWriting = ? WHERE userEmail = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String dbURL = "jdbc:mysql://localhost:3306/FLYING";
		String dbID = "root";
		String dbPW = "12345678";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNumOfWriting(userEmail) + 1);
			pstmt.setString(2, userEmail);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}

}

