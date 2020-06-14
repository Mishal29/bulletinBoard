package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import bean.PostBean;

public class PostDao {
	private static final String url = "jdbc:mysql://localhost:3306/bulletin_board?serverTimezone=JST";
	private static final String user = "root";
	private static final String pw = "root";

	public static ArrayList<PostBean> selectAllPost(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PostBean> list = new ArrayList<PostBean>();

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);

			String sql = "SELECT * FROM post";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String mail = rs.getString("mail");
				String content = rs.getString("content");
				String file = rs.getString("file");
				Timestamp postTime = rs.getTimestamp("post_time");
				Timestamp updateTime = rs.getTimestamp("update_time");

				PostBean book = new PostBean(id, name, mail, content, file, postTime, updateTime);
				list.add(book);
			}

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return list;
	}

	public static void editPost(PostBean p){
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);

			String sql = "UPDATE post SET name = ?, mail = ?, content = ?, update_time = NOW() WHERE id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getMail());
			pstmt.setString(3, p.getContent());
			pstmt.setInt(4, p.getId());

			pstmt.executeUpdate();

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	public static void addPost(PostBean p){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);

			String sql = "SELECT name, mail, content FROM post WHERE id = (SELECT MAX(id) FROM post)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			String name = rs.getString("name");
			String mail = rs.getString("mail");
			String content = rs.getString("content");

			pstmt.close();

			if((p.getName().equals(name)) && (p.getMail().equals(mail)) && (p.getContent().equals(content))){
				return;
			}

			sql = "INSERT INTO post(name, mail, content, file, post_time) VALUES(?, ?, ?, ?, NOW())";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getMail());
			pstmt.setString(3, p.getContent());
			if(p.getFile().equals("")){
				pstmt.setNull(4, Types.VARCHAR);
			}else{
				pstmt.setString(4, p.getFile());
			}

			pstmt.executeUpdate();

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	public static void deletePost(int id){
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pw);

			String sql = "DELETE FROM post WHERE id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}
}
