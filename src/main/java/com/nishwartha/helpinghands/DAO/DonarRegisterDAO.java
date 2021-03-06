package com.nishwartha.helpinghands.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nishwartha.helpinghands.entity.DonarRegister;
import com.nishwartha.helpinghands.modal.Activities;

@Repository
public interface DonarRegisterDAO extends JpaRepository<DonarRegister, Long> {

	List<DonarRegister> findAllByBloodgroup(String bloodgroup);
	default void print( String blood) {
		System.out.println(blood);
	}
}
