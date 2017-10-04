package uit.edu.project.service.patient;

import java.sql.SQLException;
import java.util.List;
import uit.edu.project.layertest.domain.patient.Patient;
/**
 *
 * @author thawaye
 */

public interface PatientView {

    public List<Patient> selectPatient()throws SQLException;
}
