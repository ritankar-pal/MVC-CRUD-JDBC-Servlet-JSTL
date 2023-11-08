package in.ineuron.dao;
import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

import java.io.IOException;
import java.sql.*;




public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	PreparedStatement pstm = null;
	ResultSet resultSet = null;
	Student std = null;
	
	
	@Override
	public String addStudent(Student student) {
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlInsertQuery = "insert into student(name, age, address) values(?,?,?)";
			
			
			if(connection != null) {
				pstm = connection.prepareStatement(sqlInsertQuery);
			}
			
			if(pstm != null) {
				
				
				//setting the values::
				pstm.setString(1, student.getSname());
				pstm.setInt(2, student.getSage());
				pstm.setString(3, student.getSaddress());
				
				//Executing the query::
				int rowAffected = pstm.executeUpdate();
				
				if(rowAffected == 1) {
					return "success";
				}
				
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return "failure";
	}


	//Logic for searching Operation::
	@Override
	public Student searchStudent(Integer sid) {
		
		
		try {
			
			connection = JdbcUtil.getJdbcConnection();
			String sqlInsertQuery = "select id, name, age, address from student where id = ?";
			
			if(connection != null) {
				pstm = connection.prepareStatement(sqlInsertQuery);
			}
			
			if(pstm != null) {
				pstm.setInt(1, sid);
				
			}
			
			if(pstm != null) {
				resultSet = pstm.executeQuery();
			}
			
			
			if(resultSet != null) {
				
				if(resultSet.next()) {
					std = new Student();
					
					//Copying the result to Student Object::
					std.setSid(resultSet.getInt(1));
					std.setSname(resultSet.getString(2));
					std.setSage(resultSet.getInt(3));
					std.setSaddress(resultSet.getString(4));
					
					return std;
				}
			}
			
		}
		catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return std;
		
	}
	
	
	

//	@Override
//	public String updateStudent(Integer sid, String sname, Integer sage, String saddress) {
//		
//		try {
//			connection = JdbcUtil.getJdbcConnection(); 
//			String sqlUpdateQuery = "update student set name = ?, age = ?, address = ? where id = ?";
//			
//			if(connection != null) {
//				pstm = connection.prepareStatement(sqlUpdateQuery);
//			}
//			
//			
//			if(pstm != null) {
//				pstm.setInt(4, sid);
//				pstm.setString(1, sname);
//				pstm.setInt(2, sage);
//				pstm.setString(3, saddress);
//			}
//			
//			if(pstm != null) {
//				int rowAffected = pstm.executeUpdate();
//				
//				if(rowAffected == 1) {
//					return "success";
//				}
//				
//			}
//			
//			
//		}catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		
//		return "failure";
//	}
	
	
	@Override
	public String updateStudent(Student student){
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlUpdateQuery = "update student set name=?, age=?, address=? where id=?";
			
			
			if(connection != null) {
				pstm = connection.prepareStatement(sqlUpdateQuery);
			}
			
			if(pstm != null) {
				
				//setting the values::
				pstm.setString(1, student.getSname());
				pstm.setInt(2, student.getSage());
				pstm.setString(3, student.getSaddress());
				pstm.setInt(4, student.getSid());
				
				//Executing the query::
				int rowAffected = pstm.executeUpdate();
				
				if(rowAffected == 1) {
					return "success";
				}
				
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return "failure";
			
		}
		
		return "failure";
	}
	
	

	@Override
	public String deleteStudent(Integer sid) {
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlDeleteQuery = "delete from student where id = ?";
			
			
			if(connection != null) {
				pstm = connection.prepareStatement(sqlDeleteQuery);
			}
			
			if(pstm != null) {
				
				//setting the values::
				pstm.setInt(1, sid);
				
				//Executing the query::
				int rowAffected = pstm.executeUpdate();
				
				if(rowAffected == 1) {
					return "success";
				}
				else {
					return "NotFound";
				}
				
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			return "failure";
		}
		
		return "failure";
	}

}
