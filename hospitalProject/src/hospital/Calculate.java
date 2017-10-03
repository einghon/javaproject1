package hospital;



import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
public class Calculate extends JFrame implements ActionListener{
//java.net.URL url1 = ClassLoader.getSystemResource("HospitalSystemJava/Plus1.jpg");
JLabel l1,l2,l3,l4,total;
JTextField f1,f2,f3;
JButton b2,b3;
JPanel cal,main;
static String s1;
String url="jdbc:mysql://localhost:3306/Hospital?autoReconnect=true&useSSL=false";
String username="root";
String password="new_password";
double total1=0.0;
public Calculate(){
	setTitle("Calculating Medicine");
	setSize(200,200);
	Border border=BorderFactory.createLineBorder(Color.BLACK);
	l1=new JLabel("	Medicine");
	l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	//l1.setBorder(border);
	l2=new JLabel("Quantity");
	l2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	//l2.setBorder(border);
	l3=new JLabel("Price");
	l3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	//l3.setBorder(border);
	l4=new JLabel();
	l4.setBorder(border);
	l4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	f1=new JTextField();
	
	f2=new JTextField();
	f3=new JTextField();
	
	total=new JLabel("Total cost =");
	total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	b2=new JButton("Done");
	//b3=new JButton("Clear");
	
	cal=new JPanel();
	main=new JPanel();
	main.setLayout(new BorderLayout());
	cal.setLayout(new GridLayout(5,2));
	cal.add(l1);cal.add(f1);
	cal.add(l2);cal.add(f2);
	cal.add(l3);cal.add(f3);
	cal.add(total);cal.add(l4);
	
	cal.add(b2);
	
		f1.requestFocus();
		f1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			if(f1.getText().isEmpty()){
				 JOptionPane.showMessageDialog(null,"Please enter the name of medicine","Error",JOptionPane.ERROR_MESSAGE);
				 f1.requestFocus();
			}
			else
				f2.requestFocus();
				
			}
			
		});
		f2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			if(f2.getText().isEmpty()){
				 JOptionPane.showMessageDialog(null,"Please enter the quantity used","Error",JOptionPane.ERROR_MESSAGE);	
				 f2.requestFocus();
			}
			else 
				try{
					Integer.parseInt(f2.getText());
					f3.requestFocus();
				
					}
				catch(Exception exp){
					 JOptionPane.showMessageDialog(null,"Please enter number only","Error",JOptionPane.ERROR_MESSAGE);
					 f2.requestFocus();	
					 f2.setText("");
				}}
			
		});
	
	f3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(f3.getText().isEmpty()){
				 JOptionPane.showMessageDialog(null,"Please enter the quantity used","Error",JOptionPane.ERROR_MESSAGE);
					f2.requestFocus();
			}
			else 
				try{
					Integer.parseInt(f3.getText());
					f3.requestFocus();
				
					}
				catch(Exception exp){
					 JOptionPane.showMessageDialog(null,"Please enter number only","Error",JOptionPane.ERROR_MESSAGE);
					 f3.setText("");
					 f3.requestFocus();	
				}
			
				int quantity=Integer.parseInt(f2.getText());
				double price=Double.parseDouble(f3.getText());
				s1=String.valueOf(add(quantity,price));
				//System.out.print(s1);
				l4.setText(s1);
				setS1(s1);
				getS1();
				f1.setText("");
				f2.setText("");
				f3.setText("");
				f1.requestFocus();
						
			//int quantity=Integer.parseInt(f2.getText());
			
			
		}
		
	});
	//b1.addActionListener(this);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			dispose();
		}
		
	});
//	b3.addActionListener(this);
		
	Toolkit kit = Toolkit.getDefaultToolkit();
	//Image img = kit.createImage(url1);
	
	JTabbedPane tab=new JTabbedPane();
	tab.addTab("Calculating Medicine",cal);
	
	main.add(tab);
	//main.setVisible(true);
	main.setSize(200,200);
	setSize(400,400);
	setLocation(200,300);
	setContentPane(main);
	//this.setVisible(true);
	
	setResizable(false);
	//setIconImage(img);
}
public void actionPerformed(ActionEvent e){

	}

private double add(int quantity,double price){
	int q=quantity;
	double p=price;
	double cost=p*q;
	double total=checking(cost);
	return total;
	
}
private double checking(double cost){
	total1=total1+cost;
	return total1;
	
}
public static void main(String args[]){
	new Calculate();
}
public String getS1() {
	return s1;
}
public void setS1(String s1) {
	Calculate.s1 = s1;
}

}
