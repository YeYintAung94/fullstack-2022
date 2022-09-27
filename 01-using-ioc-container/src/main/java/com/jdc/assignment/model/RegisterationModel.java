package com.jdc.assignment.model;

import java.util.List;

import com.jdc.assignment.domain.Registeration;

public interface RegisterationModel {
	List<Registeration> getAll(int classid);
	
	void register(Registeration registeration);
	
	
}
