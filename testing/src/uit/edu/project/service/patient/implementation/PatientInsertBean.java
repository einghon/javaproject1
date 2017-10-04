package uit.edu.project.service.patient.implementation;

import uit.edu.project.infrastructure.PatientRepository;
import uit.edu.project.infrastructure.jdbc.PatientJDBCRepository;
import uit.edu.project.layertest.domain.patient.Patient;
import uit.edu.project.service.patient.PatientInsert;


public class PatientInsertBean implements PatientInsert{
	@Override
	public void addNewPatient(Patient newPatient) {
		// Create new Doctor in database
		// 1. Verify doctor already exists or not.
		// - If exists, throw exception. And go to Step 3.
		// - Else, go to Step 2.
		// 2. Save doctor
		// 3. Done

		PatientRepository patientRepository = new PatientJDBCRepository();
		patientRepository.save(newPatient);

	}

}
