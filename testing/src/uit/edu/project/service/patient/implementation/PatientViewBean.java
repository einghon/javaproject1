package uit.edu.project.service.patient.implementation;

import java.util.List;

import uit.edu.project.layertest.domain.patient.Patient;

import uit.edu.project.layertest.domain.doctor.Doctor;
import uit.edu.project.layertest.domain.patient.Patient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uit.edu.project.infrastructure.DoctorRepository;
import uit.edu.project.infrastructure.PatientRepository;
import uit.edu.project.infrastructure.jdbc.DoctorJDBCRepository;
import uit.edu.project.infrastructure.jdbc.PatientJDBCRepository;
import uit.edu.project.service.doctor.DoctorView;
import uit.edu.project.service.patient.PatientView;
import uit.edu.project.layertest.domain.doctor.*;

/**
 *
 * @author thawaye
 */
public class PatientViewBean implements PatientView {

	@Override
	public List<Patient> selectPatient() {


		PatientRepository patientRepository = new PatientJDBCRepository();
		List<Patient>patients=new ArrayList<Patient>();
		try {
			patients=patientRepository.select_patient();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(patients.size()!=0)
		return patients;
		else return null;

	}

}
