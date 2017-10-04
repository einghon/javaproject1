package uit.edu.project.infrastructure.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import uit.edu.project.layertest.domain.doctor.Doctor;
import uit.edu.project.layertest.domain.patient.Patient;
import uit.edu.project.infrastructure.ConnectionPoolManager;
import uit.edu.project.infrastructure.PatientRepository;


/**
 *
 * @author thawaye
 */
public class PatientJDBCRepository implements PatientRepository {
 
	ResultSet rs;
	Statement stmt;
	
    private static final String INSERT_PATIENT = "INSERT INTO patient (name, Disease, FirstDate,RoomORBed, PrescribedDoctor,Address, TotalBill) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_PATIENT="Select * from patient";

   
    

    @Override
    public void save(Patient patient) {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            conn = ConnectionPoolManager.getConnection();
            conn.setAutoCommit(false);

            pstm = conn.prepareStatement(INSERT_PATIENT);
            
            pstm.setString(1, patient.getName());
            pstm.setString(2, patient.getDisease());
            pstm.setString(3, patient.getFirstDate());
            pstm.setString(4, patient.getRoomAndBed());
            pstm.setString(5, patient.getprescribedDoctor());
            pstm.setString(6, patient.getAddress());
            pstm.setDouble(7, patient.getTotalBill());
            pstm.execute();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(PatientJDBCRepository.class.getName()).log(Level.SEVERE, null, ex);

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(PatientJDBCRepository.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    // Do nothing.
                }
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PatientJDBCRepository.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public  List<Patient> select_patient() {
    	Connection conn1=null;
    	PreparedStatement pstm1=null;
    	try{
    		 conn1 = ConnectionPoolManager.getConnection();
             conn1.setAutoCommit(false);
    	
             pstm1 = conn1.prepareStatement(SELECT_PATIENT);
             rs=pstm1.executeQuery(SELECT_PATIENT);
             if(rs!=null)
 
             {  while(rs.next()){
            	 Patient patient=new Patient();
            	 patient.setName(rs.getString(1));
            	 patient.setDisease(rs.getString(2));
            	 patient.setFirstDate(rs.getString(3));
            	 patient.setroomAndBed(rs.getString(4));
            	 patient.setprescribedDoctor(rs.getString(5));
            	 patient.setAddress(rs.getString(6));
            	 patient.setTotalBill(rs.getDouble(7));
 				patientList.add(patient);
    	
			
		//		System.out.print(patientList);
 				}
             return patientList;}
			
			else return null;
    	 }
    	 catch (SQLException ex) {
             Logger.getLogger(DoctorJDBCRepository.class.getName()).log(Level.SEVERE, null, ex);

             if (conn1 != null) {
                 try {
                     conn1.rollback();
                 } catch (SQLException ex1) {
                     Logger.getLogger(DoctorJDBCRepository.class.getName()).log(Level.SEVERE, null, ex1);
                 }
             }

         } finally {
             if (pstm1 != null) {
                 try {
                     pstm1.close();
                 } catch (SQLException ex) {
                     // Do nothing.
                 }
             }
             if (conn1 != null) {
                 try {
                     conn1.setAutoCommit(true);
                 } catch (SQLException ex) {
                     Logger.getLogger(DoctorJDBCRepository.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
		return patientList;
             }
    //array list declaration
    ArrayList<Patient> patientList=new ArrayList<Patient>();

}
