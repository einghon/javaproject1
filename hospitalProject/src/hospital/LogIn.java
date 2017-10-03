package hospital;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class LogIn extends JFrame implements ActionListener
{	
//	java.net.URL url = ClassLoader.getSystemResource("HospitalSystemJava/Plus1.jpg");
	
	
    String strr,pas;
	JPanel allPanel=new JPanel(new GridLayout(4,0));
	JPanel ap;
	JPanel firstGrid=new JPanel();
	JPanel secondGrid=new JPanel(new FlowLayout());//Picture and title
	JPanel thirdGrid=new JPanel(new BorderLayout());//user name,password
	
	JPanel pwPanel=new JPanel(new FlowLayout());
	JPanel userNamePanel=new JPanel(new FlowLayout()); //password,text field
	JPanel loginPanel=new JPanel();
	//JPanel errorPanel=new JPanel();
	
//	ImageIcon icon=new ImageIcon(new ImageIcon(getClass().getResource("/HospitalSystemJava/MH.jpg")).getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));	
	Toolkit kit = Toolkit.getDefaultToolkit();
	//Image img = kit.createImage(url);
	
	JLabel name=new JLabel();
//	JLabel icon1=new JLabel(icon);
	//JLabel icon2=new JLabel(icon);
	JLabel error=new JLabel();
	
	JLabel userName=new JLabel();
	JLabel password=new JLabel();
	JButton loginButton=new JButton();
	
	JTextField u=new JTextField(15);
	JPasswordField p=new JPasswordField(15);

     
	public LogIn()
	{
		
		//firstGrid
		
	//	final Image image = requestImage();

       ap = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
               // g.drawImage(image, 0, 0, null);
            }
        };
        allPanel.add(firstGrid);
		//secondGrid
	//	secondGrid.add(icon1);
		name.setText("Hospital System Controller");
		name.setFont(new Font("Serif",Font.BOLD,50));
		secondGrid.add(name);
//		secondGrid.add(icon2);
		allPanel.add(secondGrid);
		
		//thirdGrid
		userName.setText("User name");
		password.setText("Password");
		userNamePanel.add(userName);
		userNamePanel.add(u);
		pwPanel.add(password);
		pwPanel.add(p);
	//	errorPanel.add(error);
		
		
		loginButton.setText("Log In");
		loginPanel.add(loginButton);
		loginButton.addActionListener(this);
		
		thirdGrid.add(userNamePanel,BorderLayout.NORTH);
		thirdGrid.add(pwPanel,BorderLayout.CENTER);
		thirdGrid.add(loginPanel,BorderLayout.SOUTH);
		thirdGrid.setBounds(200, 100, 200, 100);
		allPanel.add(thirdGrid);
	//	allPanel.add(createContent());
		ap.add(allPanel);
	//	pack();
		setContentPane(ap);
		
		setTitle("Hospital System Controller");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocation(0,0);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setIconImage(img);
	}
	public static  void main(String[] args)
	{
		  SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new LogIn();
	            }
	        });
		//new LogIn();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//for user name text field
		if(e.getSource()==loginButton){
			strr=u.getText();
			pas=p.getText();
			
			if(strr.equals("admin") &&  pas.equals("1111")){
				new Menu();
				setVisible(false);
			}
			else{ 
				 int choice = JOptionPane.showConfirmDialog(null, "Please check your user name or password !!! " ,null, JOptionPane.WARNING_MESSAGE,JOptionPane.OK_OPTION);
			        if (choice == JOptionPane.OK_OPTION) {
			        	 LogIn();
			           
			        } 
				
				
				}
			}	
	}
	private void LogIn() {
		// TODO Auto-generated method stub
		u.setText("");
		p.setText("");
	}
}