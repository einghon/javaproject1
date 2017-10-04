

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uit.edu.project.service.doctor;
import java.sql.SQLException;
import java.util.List;

import uit.edu.project.layertest.domain.doctor.Doctor;
import uit.edu.project.layertest.domain.patient.Patient;

/**
 *
 * @author thawaye
 */

public interface DoctorView {

    public List<Doctor> selectDoctor()throws SQLException;
}
