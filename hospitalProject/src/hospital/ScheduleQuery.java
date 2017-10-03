package hospital;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;


public class ScheduleQuery extends JFrame implements ActionListener
{	Dimension size=Toolkit.getDefaultToolkit().getScreenSize();

	int width1=(2*size.width)/3;
int width2=size.width/3;
int height=size.height;
	JScrollPane pane;
	JTable table;
	DefaultTableModel model2;
	JButton refresh,print,reset,delete,add;
String col[]={"Name","Contact","Speciality","Address"};
JTextField name=new JTextField(10);
JTextField f1=new JTextField(10);
JButton b=new JButton();
JButton b1=new JButton();
JButton ser=new JButton();
String time[] ={"1:00-3:00","3:00-6:00","6:00-9:00 ","9:00-12:00","12:00-15:00","15:00-18:00","18:00-21:00 ","21:00-24:00"};
JComboBox box=new JComboBox(time);
String speciality[]={"Audiologist","Allergist","Cardiologist","Dentist","Dermatologist","Epidemiologist","Neurologist","Physiologist","Radiologist","Surgeon"};
JComboBox box1=new JComboBox(speciality);


	int hour =LocalDateTime.now().getHour();
int hour_for_query;
	Connection conn;

	java.sql.Statement stmt;
	 
	ResultSet rs;
	JLabel l1=new JLabel();
	JLabel l2=new JLabel();
	JLabel l3=new JLabel();
	JLabel l4=new JLabel();
	JLabel l5=new JLabel();
	JLabel l6=new JLabel();
	JPanel allPanel;
	JPanel p1,p2,p3,p4,p5,p6,p8;
	JPanel p7=new JPanel();
	
	JPanel leftPanel=new JPanel();
	JPanel rightPanel=new JPanel();
	
	String url="jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
	String username="root";
	String password="new_password";
	JPanel ScheduleQuery()
	{
		
		DisplaySchedule();
		//AddSchedule();
		// Border border=BorderFactory.createLineBorder(Color.BLACK);
		allPanel=new JPanel();
		allPanel.setLayout(null);
		//leftPanel=new JPanel();
		//rightPanel=new JPanel();
	leftPanel.setBounds(0,0,width1,height);
	rightPanel.setBounds(width1,0,width2,height);
		//leftPanel.setBounds(0,0,900,600);
		//rightPanel.setBounds(900,0,300,600);
	allPanel.add(leftPanel);
	allPanel.add(rightPanel);

		return allPanel;
	}
	public void DisplaySchedule(){
		//JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
		//String query="SELECT * FROM PATIENT ORDER BY Name";
		model2 = new DefaultTableModel(col,40);//setting column header and the column number
		table=new JTable(model2){@Override 
		public boolean isCellEditable(int arg0, int arg1) 
				{ return false; }};
        
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
		table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 
		//table.setName("Patient Record");
		pane = new JScrollPane(table);
		pane.setBounds(0,0,900,600);
		
		/*refresh=new JButton("Refresh");
		refresh.setBounds(250,625,100,20);
		refresh.addActionListener(this);	
		
		delete=new JButton("Delete");
		delete.setBounds(400,625,100,20);
		delete.addActionListener(this);	

		print=new JButton("Print");
		print.setBounds(550,625,100,20);
		print.addActionListener(this);	
		
		*/
		leftPanel=new JPanel(new BorderLayout());
		//leftPanel.setLayout(null);
		leftPanel.add(pane,BorderLayout.CENTER);
		leftPanel.setVisible(true);
		//leftPanel.add(refresh);
//		leftPanel.add(delete);
	//	leftPanel.add(print);
	     
		  String query1=null;
		  ///////////////////////////////////////
			if (isBetween(hour, 1,3 )) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty1='FREE');";
				}
		    else if (isBetween(hour, 3, 6)) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty2='FREE');";
				}
			if (isBetween(hour,6 ,9 )) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty3='FREE');";
	 				}
	 		else if (isBetween(hour, 9, 12)) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty4='FREE');";
	 				}
			if (isBetween(hour, 12,15 )) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty5='FREE');";
				}
		    else if (isBetween(hour, 15, 18)) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty6='FREE');";
				}
			
			if (isBetween(hour, 18,21 )) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty7='FREE');";
				}
		    else if (isBetween(hour, 21, 24)) {
				query1="select d.name,d.contact,d.speciality,d.address from doctorRecord d,docschedule1 s where(s.name=d.name and s.duty8='FREE');";
				}
			
			/*model2 = new DefaultTableModel(col,40);//setting column header and the column number
			table=new JTable(model2){@Override 
			public boolean isCellEditable(int arg0, int arg1) 
					{ return false; }};
	        
			table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);//for the flexible column width 
			table.setPreferredScrollableViewportSize(table.getPreferredSize());//for the flexible column width 
			//table.setName("Patient Record");
			pane = new JScrollPane(table);
			pane.setBounds(0,0,900,600);
			
			refresh=new JButton("Refresh");
			refresh.setBounds(250,625,100,20);
			refresh.addActionListener(this);	
			
			delete=new JButton("Delete");
			delete.setBounds(400,625,100,20);
			delete.addActionListener(this);	

			print=new JButton("Print");
			print.setBounds(550,625,100,20);
			print.addActionListener(this);	
			
			
			leftPanel=new JPanel();
			leftPanel.setLayout(null);
			leftPanel.add(pane);
			leftPanel.add(refresh);
			leftPanel.add(delete);
			leftPanel.add(print);
		    // leftPanel.add(pane);*/
			try{			 
				 Class.forName("com.mysql.jdbc.Driver");
				 conn = DriverManager.getConnection(url, username, password);
				 stmt = conn.createStatement();
			     rs=stmt.executeQuery(query1);
			     int i=0;
			     while(rs.next()){
			    	  
			     String name=rs.getString(1);
			     String contact=rs.getString(2);
			     String speciality=rs.getString(3);
			     String address=rs.getString(4);
			     
			     table.setValueAt(name,i,0); 
				 table.setValueAt(contact,i,1);
				 table.setValueAt(speciality,i,2);
				 table.setValueAt(address,i,3);
			     
				 i++; 
				 
				}
			     /*if ( name[0]==null)
			     {
			   System.out.print("Found Nothing!!");}*/
			     	 rs.close();
			     	 conn.close();
			 	 } 
			catch(ClassNotFoundException cnfe){
				System.out.print(cnfe+"Error");
			 }
			 catch (SQLException sqle){
				System.out.print(sqle+"Error"); } 
			
	
	}
	public void AddSchedule(){
		p1=new JPanel();
		rightPanel=new JPanel(new BorderLayout());
		
		l1.setText("Search for");
		l1.setPreferredSize(new Dimension(500,60));
		//l1.setAlignmentX((float) 10.1);
		//l1.setAlignmentX(1000);
		//l1.setAlignmentY(alignmentY);
		l1.setBounds(20,0,70,50);
		l1.setFont(new Font("Serif",Font.BOLD,24));
		rightPanel.add(l1);
		//panelforsearch.add(l1);
		//panel1.add(panelforsearch);
		///////////////////////////////
		ser.setText("Current Free Doctor");
		ser.addActionListener(this);
		ser.setBounds(20,90,70,50);
		
		rightPanel.add(ser);
		//p11.add(p1);
		//panelforbutton2.add(ser);
		//panel2.add(panelforbutton2,BorderLayout.SOUTH);
	//	p2=new JPanel(new FlowLayout());
		l6.setText("OR");
		l6.setBounds(20,180,70,50);
		rightPanel.add(l6);
		l2.setText("Name");
		l2.setBounds(20,270,70,50);
		
		//p3.add(l2);150,15,170,20
		name.addActionListener(this);
		name.setBounds(150,270,70,50);
		rightPanel.add(name);
	
		//panel2.add(panelforname,BorderLayout.NORTH);
		//panel1.add(panel2);
		//////////////////////////////////
	//	p44=new JPanel(new BoxLayout(p44,BoxLayout.X_AXIS));
		//p4=new JPanel();
		l5.setText("Speciality");
		l5.setBounds(20,358,110,50);
		
		rightPanel.add(l5);
	//	p4.add(l5);
		box1.setBounds(110,448,110,50);
		
		rightPanel.add(box1);

		l3.setText("Time");
		l3.setBounds(20,448,70,50);
		box.setBounds(70,448,70,50);
		rightPanel.add(box);
		rightPanel.add(l3);
		b.setText("Search");
		b.setBounds(20, 528, 70, 50);
		b.addActionListener(this);
		rightPanel.add(b);
	    //panelforbutton.add(b);
	    b1.setText("Reset");
	    b1.addActionListener(this);
	    rightPanel.add(b1);
	    l4.setText("Room");
	    rightPanel.add(l4);// p66.add(p6);
		rightPanel.add(p1,BorderLayout.CENTER);
		rightPanel.setVisible(true);
	    
	}
	/*	setVisible(true);
		setLocation(350,100);
		setSize(400,400);
		setResizable(false);
		setTitle("Schedule Query");
		p1=new JPanel();
		l1.setText("Search for");
		l1.setPreferredSize(new Dimension(500,60));
		
		l1.setBounds(100,100,100,100);
		l1.setFont(new Font("Serif",Font.BOLD,24));
	    p1.add(l1);
	    p1.setBorder(border);
		
		ser.setText("Current Free Doctor");
		ser.addActionListener(this);
		p11=new JPanel();
		p11.add(ser);
		p11.setBorder(border);
		p2=new JPanel(new FlowLayout());
		l6.setText("OR");
		p2.add(l6);
		p2.setBorder(border);
		p33=new JPanel(new BoxLayout(p33,BoxLayout.X_AXIS));
		p3=new JPanel();
		l2.setText("Name");
		
		p3.add(l2);
			name.addActionListener(this);
		name.setBounds(0,0,900,800);
		p3.add(name);
	
		//panel2.add(panelforname,BorderLayout.NORTH);
		//panel1.add(panel2);
		//////////////////////////////////
	//	p44=new JPanel(new BoxLayout(p44,BoxLayout.X_AXIS));
		p4=new JPanel();
		l5.setText("Speciality");
		p4.add(l5);
		p4.add(box1);
		///p44.add(p4);
		//panelforspec.add(l5);
		//panelforspec.add(box1);
		//panel3.add(panelforspec);
        //////////////////////////////////
		//p88=new JPanel(new BoxLayout(p88,BoxLayout.X_AXIS));
		p8=new JPanel();
		l3.setText("Time");
		p8.add(l3);
		p8.add(box);
		//p88.add(p8);
		//panelfortime.add(l3);
		//panelfortime.add(box);
		//panel3.add(panelfortime);
		/////////////////////////////////
		//p55=new JPanel(new BoxLayout(p55,BoxLayout.X_AXIS));
		p5=new JPanel();
		b.setText("Search");
		b.addActionListener(this);
		p5.add(b);
	    //panelforbutton.add(b);
	    b1.setText("Reset");
	    b1.addActionListener(this);
	    p5.add(b1);
	    //p55.add(p5);
	    //panelforbutton.add(b1);
	    //panel3.add(panelforbutton);
	    ///////////////////////////////////////
	    //p66=new JPanel(new BoxLayout(p66,BoxLayout.X_AXIS));
	    p6=new JPanel();
	    l4.setText("Room");
	    p6.add(l4);
	    p6.add(f1);
	   // p66.add(p6);
	   
	    p7=new JPanel(new GridLayout(8,1));
	    //p7.setLayout(new BoxLayout(p7,BoxLayout.PAGE_AXIS));
	    //p7.setBounds(0,0,900,600);
	    p7.setSize(new Dimension(300,900));
	    p7.add(p1);
	    p7.add(p11);
	    p7.add(p2);
	    p7.add(p3);
	    p7.add(p4);
	    p7.add(p8);
	    p7.add(p5);
	    p7.add(p6);
	    p7.setBorder(border);
	    //p22=new JPanel(new BorderLayout());
	   // JLabel p=new JLabel();
	   // p.setText("");
	   // p22.add(p,BorderLayout.EAST);
	   // JLabel p1=new JLabel();
	   // p1.setText("");
	   // p22.add(p1,BorderLayout.WEST);
	   // p22.setBorder(border);
	   // p22.add(p7,BorderLayout.CENTER);
	   // return p7;
	    //panelforroom.add(l4);
	    //panelforroom.add(f1);
	    //panel3.add(panelforroom);
	    ////////////////////////////////////////////
	    //panel3.add(labelforroom);
	    //panel3.setBounds(100,100,100,100);
	    //panelforall.add(panel1,BorderLayout.NORTH);
	    //panelforall.add(panel2,BorderLayout.CENTER);
	    //panelforall.add(panel3,BorderLayout.SOUTH);
		setContentPane(p7);
		return p7;
		//return panelforall;
		*/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b)
		{
			String query=null;
			String str=name.getText();
			String str1=(String)box.getSelectedItem();
			String str2=(String)box1.getSelectedItem();
			//int i=1;
			switch(str1)
			{
			case"1:00-3:00":{ 
				
					query="SELECT s.duty1 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";
				
					
													break;}
			case"3:00-6:00": {
				
				
				query="SELECT s.duty2 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";
													break;}
			case"6:00-9:00": {	
				
				
					query="SELECT s.duty3 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";break;}
			case"9:00-12:00":{ 
				
					query="SELECT s.duty4 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";break;}
			case"12:00-15:00":{ 
				
					query="SELECT s.duty5 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";break;}
			case"15:00-18:00":{
				
					query="SELECT s.duty6 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";break;}
			case"18:00-21:00":{ 
				
					query="SELECT s.duty7 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";break;}
			case"21:00-24:00":{ 
				query="SELECT s.duty8 FROM schedule s,DoctorRecord r WHERE s.name='"+str+"' AND r.speciality='"+str2+"' and r.name=s.name;";break;}
			
			}
			if(query!=null){
			//System.out.println(query);
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(url, username, password);
				stmt=conn.createStatement();
				rs=stmt.executeQuery(query);
				//while(rs.next()){
				rs.next();
				String roomNO=rs.getString(1);
		    	f1.setText(roomNO);
				rs.close();
				conn.close();
			}
			catch(ClassNotFoundException cnfe)
			{
				JOptionPane.showMessageDialog(null, cnfe,"Error",JOptionPane.ERROR_MESSAGE);
			}
			catch(SQLException sqle)
			{
				JOptionPane.showMessageDialog(null, sqle,"Error",JOptionPane.ERROR_MESSAGE);
			}
			}
			}
		if(e.getSource()==b1){
			name.setText("");
			box.setSelectedItem("1:00-3:00");
			f1.setText("");
		}
		if(e.getSource()==ser){
			new CurrentFreeDoctor();
			
		}
		if(e.getSource()==name){
			String txt=name.getText();
			if(name.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please fill all required field!","error",JOptionPane.ERROR_MESSAGE);
				name.setText("");
				name.requestFocus();
			}
			else{
				try{
					String regx="^[a-zA-Z \\s]{1,}[\\.]{0,1}[a-zA-Z0-9 \\s]{0,}$";
					Pattern pattern=Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
					Matcher matcher=pattern.matcher(txt);
					boolean m=matcher.find();
					if(m==false){
						JOptionPane.showMessageDialog(null, "invalid name!");
						name.setText(" ");
						name.requestFocus();
					}
					else{
						box1.requestFocus();
					}
			
				}
				catch(Exception exp){
					JOptionPane.showMessageDialog(null, "invalid name!");
				}
			}}
		
		}
			
	public static boolean isBetween(int x, int lower, int upper) {
	 	 boolean boo;
	 		if(lower<=x && upper>x)
	 		boo=true;
	 		else boo=false;
	 		return boo;
	 	}
    

       public static void main(String args[])
       {
    	   new ScheduleQuery();
       }

}