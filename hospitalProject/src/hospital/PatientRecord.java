package hospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class PatientRecord extends JFrame implements ActionListener {
Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
 
int width=size.width;
int height=size.height;
//java.net.URL url1 = ClassLoader.getSystemResource("hospitalProject/Plus1.jpg");
JTabbedPane tabbedPane;
JPanel patientList,patientSearch,roomRegister;
JButton back;
public PatientRecord(){
//	Toolkit kit = Toolkit.getDefaultToolkit();
//	Image img = kit.createImage(url1);
setTitle( "Patient Information" );
setSize(width,height);
setLocation(0,0);//Set Position on the screen
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
//	setIconImage(img);
JPanel PatientRec =new JPanel();
PatientRec.setLayout(new BorderLayout());
setContentPane(PatientRec);
listPatient();
searchPatient();
roomRegister();
back=new JButton("<<Back");
back.setBounds(width-100,0,100,20);     
back.addActionListener(this);
PatientRec.add(back);
tabbedPane=new JTabbedPane();
tabbedPane.add("Patient List",patientList);
tabbedPane.add("Search Patient Info",patientSearch);
tabbedPane.add("Room Register",roomRegister);
PatientRec.add(tabbedPane,BorderLayout.CENTER);
}
public void listPatient()
{patientList =new JPanel(new BorderLayout());
PatientList a=new PatientList();
patientList.add(a.PatientList(),BorderLayout.CENTER);
}
public void searchPatient()
{patientSearch =new JPanel(new BorderLayout());
SearchPatientQuery b=new SearchPatientQuery();
patientSearch.add(b.findP(),BorderLayout.CENTER);
}
public void roomRegister(){
roomRegister=new JPanel(new BorderLayout());
Room r=new Room();
roomRegister.add(r.findR(),BorderLayout.CENTER);
}
public static void main(String args[])
{
PatientRecord mainFrame	= new PatientRecord();
mainFrame.setVisible( true );
}
@Override
public void actionPerformed(ActionEvent e) {
if(e.getSource()==back)
{new Menu();
setVisible(false);}
}
}