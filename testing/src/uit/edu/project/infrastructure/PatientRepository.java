package uit.edu.project.infrastructure;

import java.sql.SQLException;
import java.util.List;

import uit.edu.project.layertest.domain.doctor.Doctor;
import uit.edu.project.layertest.domain.patient.*;

public interface PatientRepository {
	 
    public void save(Patient patient);
    
    public  List<Patient> select_patient() throws SQLException;
}
