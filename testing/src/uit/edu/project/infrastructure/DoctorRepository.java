
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.edu.project.infrastructure;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uit.edu.project.layertest.domain.doctor.Doctor;

/**
 *
 * @author thawaye
 */
public interface DoctorRepository {
    
    public void save(Doctor doctor);
    
    public List<Doctor> find(Doctor doctorExample);
    public  List<Doctor> select_dr() throws SQLException;
}

