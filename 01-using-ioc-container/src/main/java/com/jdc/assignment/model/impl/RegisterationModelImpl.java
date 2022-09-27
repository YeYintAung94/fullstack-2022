package com.jdc.assignment.model.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domain.Course;
import com.jdc.assignment.domain.OpenClass;
import com.jdc.assignment.domain.Registeration;
import com.jdc.assignment.model.RegisterationModel;

public class RegisterationModelImpl implements RegisterationModel {

	private static final String REGISTER_SQL = "insert into registeration (open_class_id,student,phone,email) values (?,?,?,?);";
	// private static final String SELECT_ALL = "select * from registeration where
	// open_class_id=?";

	private static final String SELECT_ALL = """
									SELECT r.id,r.student,r.phone,r.email
			FROM registeration r
			JOIN open_class oc
			ON oc.id=r.open_class_id
			JOIN course c
			ON oc.course_id=c.id
			WHERE oc.id=?
			""";

//	private static final String SELECT_ALL = """
//			SELECT r.id,r.open_class_id,r.student,c.name
//			FROM registeration r
//			INNER JOIN course c 
//			ON c.id=(SELECT course_id FROM open_class WHERE id=?)
//			WHERE r.open_class_id=?;
//			""";

	private DataSource dataSource;

	public RegisterationModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Registeration> getAll(int classid) {

		System.out.println(classid + " okkkkkk FROM REGISTERATION SERVLET ");
		var list = new ArrayList<Registeration>();

		try (var conn = dataSource.getConnection(); var stmt = conn.prepareStatement(SELECT_ALL)) {

			stmt.setInt(1, classid);

			var result = stmt.executeQuery();

			while (result.next()) {
				var openclass = new OpenClass();
				openclass.setId(result.getInt("id"));
				
				var registeration = new Registeration();
				registeration.setId(result.getInt("id"));
				registeration.setOpenClass(openclass);
				registeration.setStudent(result.getString("student"));
				registeration.setPhone(result.getString("phone"));
				registeration.setEmail(result.getString("email"));

				list.add(registeration);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void register(Registeration registeration) {
		
		try(var conn=dataSource.getConnection();
				var stmt=conn.prepareStatement(REGISTER_SQL)){
			
			stmt.setInt(1, registeration.getOpenClass().getId());
			stmt.setString(2, registeration.getStudent());
			stmt.setString(3, registeration.getPhone());
			stmt.setString(4, registeration.getEmail());
			
			stmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
