/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import uit.edu.project.infrastructure.ConnectionPoolManager;
import uit.edu.project.infrastructure.DoctorRepository;
import uit.edu.project.layertest.UISwing.doctor.ViewDrFrame;

/**
 *
 * @author thawaye
 * @param <doctorlist>
 */
public class DoctorJDBCRepository<doctorlist> implements DoctorRepository {

	ResultSet rs;
	Statement stmt;

	private static final String INSERT_DOCTOR = "INSERT INTO doctorrecord (name, speciality, consfee,contact,address) VALUES (?,?,?,?,?)";
	private static final String SELECT_DOCTOR = "Select * from doctorrecord";

	@Override
	public void save(Doctor doctor) {

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			conn = ConnectionPoolManager.getConnection();
			conn.setAutoCommit(false);

			pstm = conn.prepareStatement(INSERT_DOCTOR);

			pstm.setString(1, doctor.getName());
			pstm.setString(2, doctor.getSpeciality());
			pstm.setDouble(3, doctor.getConfee());
			pstm.setString(4, doctor.getContactNo());
			pstm.setString(5, doctor.getAddress());

			pstm.execute();

			conn.commit();

		} catch (SQLException ex) {
			Logger.getLogger(DoctorJDBCRepository.class.getName()).log(Level.SEVERE, null, ex);

			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex1) {
					Logger.getLogger(DoctorJDBCRepository.class.getName()).log(Level.SEVERE, null, ex1);
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
					Logger.getLogger(DoctorJDBCRepository.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	@Override
	public List<Doctor> find(Doctor doctorExample) {
		return null;
	}

	@Override
	public List<Doctor> select_dr() {
		Connection conn1 = null;
		PreparedStatement pstm1 = null;
		try {
			conn1 = ConnectionPoolManager.getConnection();
			conn1.setAutoCommit(false);

			pstm1 = conn1.prepareStatement(SELECT_DOCTOR);
			rs = pstm1.executeQuery(SELECT_DOCTOR);
			if (rs != null) {
				while (rs.next()) {
					Doctor doctor = new Doctor();
					doctor.setName(rs.getString(1));
					doctor.setSpeciality(rs.getString(2));
					doctor.setConfee(Double.parseDouble(rs.getString(3)));
					doctor.setAddress(rs.getString(4));
					doctor.setContactNo(rs.getString(5));
					doctorList.add(doctor);

				}
				return doctorList;
			}

			else
				return null;
		} catch (SQLException ex) {
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
		return doctorList;
	}

	// array list declaration
	ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
}
