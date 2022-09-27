package com.jdc.assignment.model.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.model.OpenClassModel;

public class OpenClassModelImpl implements OpenClassModel{
	
	private static final String SELECT_SQL = """
			select oc.id,oc.start_date,oc.teacher,
			c.id,c.name,c.fees,c.duration,c.description
			from open_class oc
			join course c on oc.course_id = c.id
			where c.id=?
			""";
	private static final String INSERT_SQL = "insert into open_class (course_id,teacher,start_date) values (?,?,?);";
	private static final String SELECT_ONE = "select * from open_class where id=?";
	private DataSource dataSource;
	
	

	public OpenClassModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<OpenClass> findByCourse(int courseId) {
		
		System.out.println(courseId+" this is course Id FROM OPENCLASS");
		
		var list=new ArrayList<OpenClass>();

		try(var conn=dataSource.getConnection();
				var stmt=conn.prepareStatement(SELECT_SQL)){

			stmt.setInt(1, courseId);
			
			var rs=stmt.executeQuery();
			
			while(rs.next()) {
				var c=new Course();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDuration(rs.getInt("duration"));
				c.setFees(rs.getInt("fees"));
				c.setDescription(rs.getString("description"));
				
				
				
				
				var oc=new OpenClass();
				oc.setCourse(c);
				oc.setId(rs.getInt("id"));
				oc.setTeacher(rs.getString("teacher"));
				oc.setStartDate(rs.getDate("start_date").toLocalDate());
				
				
				list.add(oc);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void create(OpenClass openClass) {
		
		try(var conn=dataSource.getConnection();
				var stmt=conn.prepareStatement(INSERT_SQL)){
			System.out.println("THIS IS "+openClass.getCourse().getId());
			
			
			stmt.setInt(1, openClass.getCourse().getId());
			stmt.setString(2, openClass.getTeacher());
			stmt.setDate(3, java.sql.Date.valueOf(openClass.getStartDate()));
			
			stmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public OpenClass findById(int id) {
		
		try(var conn=dataSource.getConnection();
				var stmt=conn.prepareStatement(SELECT_ONE)){
			
			stmt.setInt(1, id);
			
			var result=stmt.executeQuery();
			
			while(result.next()) {
				var oc=new OpenClass();
				oc.setId(result.getInt("id"));
				oc.setStartDate(result.getDate("start_date").toLocalDate());
				oc.setTeacher(result.getString("teacher"));
				
				return oc;
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
