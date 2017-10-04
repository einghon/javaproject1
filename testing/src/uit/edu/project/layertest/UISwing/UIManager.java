package uit.edu.project.layertest.UISwing;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import uit.edu.project.layertest.UISwing.doctor.InsertDrFrame;
import uit.edu.project.layertest.UISwing.doctor.ViewDrFrame;
import uit.edu.project.layertest.UISwing.patient.InsertPatientFrame;
import uit.edu.project.layertest.UISwing.patient.ViewPatientFrame;

/**
 *
 * @author thawaye
 */
public class UIManager {

    // Doctor
    private final static InsertDrFrame NEW_DOCTOR_FRAME = new InsertDrFrame();
    private final static ViewDrFrame VIEW_DOCTORS_FRAME = new ViewDrFrame();

    // Patient
    private final static InsertPatientFrame NEW_PATIENT_FRAME = new InsertPatientFrame();
    private final static ViewPatientFrame VIEW_PATIENTS_FRAME = new ViewPatientFrame();

    private final static Map<Class<? extends JInternalFrame>, JInternalFrame> FRAMES = new HashMap<>();

    static {
        FRAMES.put(InsertDrFrame.class, NEW_DOCTOR_FRAME);
        FRAMES.put(ViewDrFrame.class, NEW_PATIENT_FRAME);
        FRAMES.put(InsertPatientFrame.class, VIEW_DOCTORS_FRAME);
        FRAMES.put(ViewPatientFrame.class, VIEW_PATIENTS_FRAME);

    }

    public static <T extends JInternalFrame> void shownInDesktopPane(JDesktopPane desktopPane, Class<T> frameClass) {

        JInternalFrame showingFrame = FRAMES.get(frameClass);
        JInternalFrame[] internalFrames = desktopPane.getAllFrames();

        boolean found = false;
        for (JInternalFrame internalFrame : internalFrames) {
            if (internalFrame == showingFrame) {
                try {
                    showingFrame.setSelected(true);
                    showingFrame.setVisible(true);
                    found = true;
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(UIManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }

        if (!found) {
            try {
                desktopPane.add(showingFrame);
                showingFrame.setSelected(true);
                showingFrame.setVisible(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(UIManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
