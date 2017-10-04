
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.edu.project.service.doctor.implementation;

import uit.edu.project.layertest.domain.doctor.Doctor;
import uit.edu.project.layertest.domain.patient.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uit.edu.project.infrastructure.DoctorRepository;
import uit.edu.project.infrastructure.jdbc.DoctorJDBCRepository;
import uit.edu.project.service.doctor.DoctorView;
import uit.edu.project.layertest.domain.doctor.*;

/**
 *
 * @author thawaye
 */
public class DoctorViewBean implements DoctorView {

	@Override
	public List<Doctor> selectDoctor() {


		DoctorRepository doctorRepository = new DoctorJDBCRepository();
		List<Doctor>doctors=new ArrayList<Doctor>();
		try {
			doctors=doctorRepository.select_dr();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(doctors.size()!=0)
		return doctors;
		else return null;

	}

}
