package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class DoctorRecord extends JFrame implements ActionListener {
	Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	 
	 int width=size.width;
	 int height=size.height;
//	java.net.URL url = ClassLoader.getSystemResource("HospitalSystemJava/Plus1.jpg");
	JTabbedPane tabbedPane;
	JPanel schedule,scheduleSearch,list,finding;
	JButton back;
	public DoctorRecord(){
		setTitle( "Doctors\' Information" );
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocation(0,0);				//Set Position on the screen
	    setLayout(new FlowLayout());
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    ListDoctor();
	    FindDoctor();
	    ScheduleDoctor();
	    searchSchedule();
	    tabbedPane=new JTabbedPane();
	    JPanel panel=new JPanel();
	    panel.setLayout(new BorderLayout());
	    ImageIcon backicon=new ImageIcon("back.ico");
	    back=new JButton("Back");
		back.setBounds(width-100,0,100,20);    
		back.addActionListener( this);
		panel.add(back);
		tabbedPane.add("Doctor List",list);
		tabbedPane.add("Search Doctor Info",finding);
	    tabbedPane.add("Doctor Schedule",schedule);
	    tabbedPane.add("Search Schedule",scheduleSearch);
	
		panel.add(tabbedPane,BorderLayout.CENTER);
		Toolkit kit = Toolkit.getDefaultToolkit();
	//	Image img = kit.createImage(url);
		setContentPane(panel);
		setVisible(true);
	//	setIconImage(img);
	}
	public void ListDoctor()
	{list =new JPanel(new BorderLayout());
	
	DoctorList a=new DoctorList();
	list.add(a.DoctorList(),BorderLayout.CENTER);
	
	}
	public void FindDoctor()
	{
		finding=new JPanel(new BorderLayout());
		SearchDoctor b=new SearchDoctor();
		finding.add(b.SearchDoctor(),BorderLayout.CENTER);
	}
	public  void ScheduleDoctor(){
		schedule=new JPanel(new BorderLayout());
		Schedule c=new Schedule();
		schedule.add(c.Schedule(),BorderLayout.CENTER);
	}
	public void searchSchedule(){
		//Border border=BorderFactory.createLineBorder(Color.BLACK);
		scheduleSearch=new JPanel(new BorderLayout());
		ScheduleQuery s=new ScheduleQuery();
		scheduleSearch.add(s.ScheduleQuery(),BorderLayout.CENTER);
		
	}
	
	
	public static void main(String args[]){
		new DoctorRecord();
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back)
		{new Menu();
		setVisible(false);}
		
	}
}
