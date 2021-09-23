package com.javatpoint.feereport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;


public class StudentDao {

	public static int save(Student s){
		int status=0;
		FileInputStream inputStream = null;
		try{
			Connection con=AccountantDao.getCon();
			PreparedStatement ps=con.prepareStatement("insert into feereport_student(name,email,course,fee,paid,due,address,city,state,country,contactno,student_image) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,s.getName());
			ps.setString(2,s.getEmail());
			ps.setString(3, s.getCourse());
			ps.setInt(4,s.getFee());
			ps.setInt(5,s.getPaid());
			ps.setInt(6,s.getDue());
			ps.setString(7,s.getAddress());
			ps.setString(8,s.getCity());
			ps.setString(9,s.getState());
			ps.setString(10,s.getCountry());
			ps.setString(11,s.getContactno());
			String imgPath = s.getStudent_imagePath();
			File image = new File(imgPath);
			try {
				inputStream = new FileInputStream(image);
				ps.setBinaryStream(12, (InputStream) inputStream, (int)(image.length()));
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException: - " + e);
			}
			status = ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println("SQLException: - " + e);;}
		return status;
	}

	public static int update(Student s){
		int status=0;
		FileInputStream inputStream = null;
		try{
			Connection con=AccountantDao.getCon();
			PreparedStatement ps=con.prepareStatement("update feereport_student set name=?,email=?,course=?,fee=?,paid=?,due=?,address=?,city=?,state=?,country=?,contactno=?,student_image=? where rollno=?");
			ps.setString(1,s.getName());
			ps.setString(2,s.getEmail());
			ps.setString(3, s.getCourse());
			ps.setInt(4,s.getFee());
			ps.setInt(5,s.getPaid());
			ps.setInt(6,s.getDue());
			ps.setString(7,s.getAddress());
			ps.setString(8,s.getCity());
			ps.setString(9,s.getState());
			ps.setString(10,s.getCountry());
			ps.setString(11,s.getContactno());
			ps.setInt(13,s.getRollno());
			String imgPath = s.getStudent_imagePath();
			File image = new File(imgPath);
			try {
				inputStream = new FileInputStream(image);
				ps.setBinaryStream(12, (InputStream) inputStream, (int)(image.length()));
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException: - " + e);
			}
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println("Update Theke Bolchi: " +e);}
		return status;
	}


	public static List<Student> view(){
		List<Student> list=new ArrayList<Student>();
		int roll_no = 0;
		String imagefileName = null;
		try{
			Connection con=AccountantDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from feereport_student");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Student s= new Student();
				s.setRollno(rs.getInt("rollno"));
				roll_no = rs.getInt("rollno");
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setFee(rs.getInt("fee"));
				s.setPaid(rs.getInt("paid"));
				s.setDue(rs.getInt("due"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCountry(rs.getString("country"));
				s.setContactno(rs.getString("contactno"));
				
				imagefileName = roll_no+"_image.png";

				try (FileOutputStream fos = new FileOutputStream(imagefileName)) {

					Blob blob = rs.getBlob("student_image");
					if (blob == null){
						System.out.println("Sorry! Didn't find any data for the student with roll no "+ roll_no);
					}
					else {
						int len = (int) blob.length();

						byte[] buf = blob.getBytes(1, len);

						fos.write(buf, 0, len);
					}
				} catch (IOException ex) {
					System.out.println("Error in fetching Image from database -> "+ex);
				}
				s.setStudent_imagePath(imagefileName);
				list.add(s);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
	public static Student getStudentByRollno(int rollno){
		Student s=new Student();
		String imagefileName = null;
		try{
			Connection con=AccountantDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from feereport_student where rollno=?");
			ps.setInt(1,rollno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				s.setRollno(rs.getInt("rollno"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setFee(rs.getInt("fee"));
				s.setPaid(rs.getInt("paid"));
				s.setDue(rs.getInt("due"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCountry(rs.getString("country"));
				s.setContactno(rs.getString("contactno"));

				imagefileName = rollno+"_image.png";

				try (FileOutputStream fos = new FileOutputStream(imagefileName)) {

					Blob blob = rs.getBlob("student_image");
					if (blob == null){
						System.out.println("Sorry! Didn't find any data for the student with roll no "+ rollno);
					}
					else {
						int len = (int) blob.length();

						byte[] buf = blob.getBytes(1, len);

						fos.write(buf, 0, len);
					}
				} catch (IOException ex) {
					System.out.println("Error in fetching Image from database -> "+ex);
				}
				s.setStudent_imagePath(imagefileName);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return s;
	}
	public static List<Student> due(){
		List<Student> list=new ArrayList<Student>();
		try{
			Connection con=AccountantDao.getCon();
			PreparedStatement ps=con.prepareStatement("select * from feereport_student where due>0");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Student s=new Student();
				s.setRollno(rs.getInt("rollno"));
				s.setName(rs.getString("name"));
				s.setEmail(rs.getString("email"));
				s.setCourse(rs.getString("course"));
				s.setFee(rs.getInt("fee"));
				s.setPaid(rs.getInt("paid"));
				s.setDue(rs.getInt("due"));
				s.setAddress(rs.getString("address"));
				s.setCity(rs.getString("city"));
				s.setState(rs.getString("state"));
				s.setCountry(rs.getString("country"));
				s.setContactno(rs.getString("contactno"));
				list.add(s);
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return list;
	}
}
