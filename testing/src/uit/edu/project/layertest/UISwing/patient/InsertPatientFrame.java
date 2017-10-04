package uit.edu.project.layertest.UISwing.patient;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JLabel;

import uit.edu.project.layertest.domain.doctor.Doctor;
import uit.edu.project.layertest.domain.patient.Patient;
import uit.edu.project.service.doctor.DoctorInsert;
import uit.edu.project.service.doctor.implementation.DrInsertBean;
import uit.edu.project.service.patient.PatientInsert;
import uit.edu.project.service.patient.implementation.PatientInsertBean;

/**
 *
 * @author thawaye
 */
public class InsertPatientFrame extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form NewDoctorFrame
	 */
	public InsertPatientFrame() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		lblName = new javax.swing.JLabel();
		txtName = new javax.swing.JTextField();
		lblDisease = new javax.swing.JLabel();
		txtDisease = new javax.swing.JTextField();
		lblFirstDate = new javax.swing.JLabel();
		txtFirstDate = new javax.swing.JTextField();
		lblRoomAndBed = new javax.swing.JLabel();
		txtRoomAndBed = new javax.swing.JTextField();
		lblPrescribedDoctor = new javax.swing.JLabel();
		txtPrescribedDoctor = new javax.swing.JTextField();
		lblAddress = new javax.swing.JLabel();
		txtAddress = new javax.swing.JTextField();
		lblTotalBill = new javax.swing.JLabel();
		txtTotalBill = new javax.swing.JTextField();
		
		btnSave = new javax.swing.JButton();

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("New Patient");

		lblName.setText("Name");
		lblDisease.setText("Disease");
		lblFirstDate.setText("first date");
        lblAddress.setText("address");
        lblRoomAndBed.setText("room and bed");
        lblPrescribedDoctor.setText("prescribed doctor");
        lblTotalBill.setText("total bill");
        
        
		

		btnSave.setText("Save");
		btnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
		});
		
        
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblDisease).addComponent(lblFirstDate).addComponent(lblAddress).addComponent(lblRoomAndBed).addComponent(lblPrescribedDoctor).addComponent(lblTotalBill))
										.addGap(0, 50, Short.MAX_VALUE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btnSave)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(txtName).addComponent(txtDisease).addComponent(txtFirstDate).addComponent(txtAddress).addComponent(txtRoomAndBed).addComponent(txtPrescribedDoctor).addComponent(txtTotalBill,
												javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblName).addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

								.addComponent(lblDisease).addComponent(txtDisease, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblFirstDate).addComponent(txtFirstDate, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblAddress).addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblRoomAndBed).addComponent(txtRoomAndBed, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblPrescribedDoctor).addComponent(txtPrescribedDoctor, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblTotalBill).addComponent(txtTotalBill,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSave)
						.addContainerGap()));

		pack();

	}

	private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
		lblName.setText("Name");
		lblDisease.setText("Disease");
		lblFirstDate.setText("first date");
        lblAddress.setText("address");
        lblRoomAndBed.setText("room and bed");
        lblPrescribedDoctor.setText("prescribed doctor");
        lblTotalBill.setText("total bill");
		Patient newPatient = new Patient();
		newPatient.setName(this.txtName.getText());
		newPatient.setDisease(this.txtDisease.getText());
		newPatient.setFirstDate(this.txtFirstDate.getText());
		newPatient.setAddress(this.txtAddress.getText());
		newPatient.setroomAndBed(this.txtRoomAndBed.getText());
		newPatient.setprescribedDoctor(this.txtPrescribedDoctor.getText());
		newPatient.setTotalBill(Integer.parseInt(this.txtTotalBill.getText()));
		
	
		PatientInsert patientService = new PatientInsertBean();
		patientService.addNewPatient(newPatient);

		// Display dialog saying doctor has been saved successfully.
		// Clear the fields.
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(InsertPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(InsertPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(InsertPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(InsertPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new InsertPatientFrame().setVisible(true);
			}
		});
	}


	private javax.swing.JButton btnSave;
	private javax.swing.JLabel lblName;
	private javax.swing.JLabel lblDisease;
	private javax.swing.JLabel lblFirstDate;
	private javax.swing.JLabel lblRoomAndBed;
	private javax.swing.JLabel lblAddress;
	private javax.swing.JLabel lblPrescribedDoctor;
	private javax.swing.JLabel lblTotalBill;
	private javax.swing.JTextField txtName;
	private javax.swing.JTextField txtDisease;
	private javax.swing.JTextField txtFirstDate;
	private javax.swing.JTextField txtRoomAndBed;
	private javax.swing.JTextField txtAddress;
	private javax.swing.JTextField txtPrescribedDoctor;
	private javax.swing.JTextField txtTotalBill;
	
}

