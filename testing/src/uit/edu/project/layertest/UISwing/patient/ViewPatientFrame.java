package uit.edu.project.layertest.UISwing.patient;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import uit.edu.project.layertest.domain.doctor.Doctor;
import uit.edu.project.layertest.domain.patient.Patient;
import uit.edu.project.service.doctor.DoctorView;
import uit.edu.project.service.doctor.implementation.DoctorViewBean;
import uit.edu.project.service.patient.PatientView;
import uit.edu.project.service.patient.implementation.PatientViewBean;

/**
 *
 * @author thawaye
 */
public class ViewPatientFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewPatientsFrame
     */
    public ViewPatientFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	model1 = new DefaultTableModel(col,40);//setting column header and the column number
		table=new JTable(model1){@Override 
		public boolean isCellEditable(int arg0, int arg1) 
				{ return true; }};
        
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
		table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 

		 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		 Container contain= getContentPane();
	      	contain.setLayout(new FlowLayout());
	       
	      	//assigning the doctor list that return from DoctorJDBCRepository into the drlist
	      	PatientView patientViewService = new PatientViewBean();
	      	try {
				patientL=patientViewService.selectPatient();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      	
	      	//setting the table column
	      	table.setValueAt("No", 0, 0);
      		table.setValueAt("name",0,1); 
			table.setValueAt("Disease",0,2);
			table.setValueAt("First Date",0,3);
			table.setValueAt("Room and Bed(A1)", 0, 4);
			table.setValueAt("Prescribed doctor", 0, 5);
			table.setValueAt("Address", 0, 6);
			table.setValueAt("Total Bill", 0, 7);
			
			
	      	if(!patientL.isEmpty())	        
	        contain.add(table);
	      	for(int i=0;i<patientL.size();i++)	      		
			{
			table.setValueAt(i+1, i+1, 0);
	      		table.setValueAt(patientL.get(i).getName(),i+1,1); 
				table.setValueAt(patientL.get(i).getDisease(),i+1,2);
				table.setValueAt(patientL.get(i).getFirstDate(),i+1,3);
				table.setValueAt(patientL.get(i).getRoomAndBed(), i+1, 4);
				table.setValueAt(patientL.get(i).getprescribedDoctor(), i+1, 5);
				table.setValueAt(patientL.get(i).getAddress(), i+1, 6);
				table.setValueAt(patientL.get(i).getTotalBill(), i+1, 7);

			}
	     
	     /* 	testing
			System.out.print(drlist.size());*/
	      	
			setClosable(true);
			setIconifiable(true);
			setMaximizable(true);
			setResizable(true);
			setTitle("Patient Select");
	        pack();

    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPatientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPatientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    List<Patient> patientL=new ArrayList<Patient>();
    JTextField name,disease,FirstDate,RoomAndBed,PrescribedDr,Address,TotalBill;
	DefaultTableModel model1;
	JTable table;
	String col[]={"NO","Name","Disease","FirstDate","RoomAndBed","PrescribedDr","Address","Total Bill"};
    // End of variables declaration//GEN-END:variables
}
