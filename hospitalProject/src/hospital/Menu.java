package hospital;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Menu extends JFrame implements ActionListener
{
//	java.net.URL url = ClassLoader.getSystemResource("HospitalSystemJava/Plus1.jpg");
	JPanel allPanel=new JPanel(new GridLayout(4,0));
	JPanel firstGrid=new JPanel();
	JPanel secondGrid=new JPanel(new FlowLayout());//Mercy Hospital,Picture
	JPanel thirdGrid=new JPanel(new BorderLayout());
	
	JPanel txtPanel=new JPanel(new FlowLayout());
	JPanel catagoryPanel=new JPanel(new FlowLayout()); //password,text field
	JPanel Okpanel=new JPanel();	//login
	JLabel thumb;
//	ImageIcon icon=new ImageIcon(new ImageIcon(getClass().getResource("/HospitalSystemJava/MH.jpg")).getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));	
	
	JLabel name=new JLabel();
//	JLabel icon1=new JLabel(icon);
	//JLabel icon2=new JLabel(icon);
	JLabel error=new JLabel();
	Toolkit kit = Toolkit.getDefaultToolkit();
//	Image img = kit.createImage(url);
	JLabel Catagories=new JLabel();

	JButton OkButton=new JButton("OK");
	
	JTextField txt;
	String cata[]={"","Patient Information","Doctor Information"};
	JComboBox combo=new JComboBox(cata);
	JButton back;
	
	public Menu()
	{
		//firstGrid
		allPanel.add(firstGrid);
		combo.setPreferredSize(new Dimension(250,30));

		combo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				String str=(String)combo.getSelectedItem();
				txt.setText(str);
			}
		});
		txt=new JTextField(13);
				
		//secondGrid
//		secondGrid.add(icon1);
		name.setText("Hospital System Controller");
		name.setFont(new Font("Serif",Font.BOLD,50));
		secondGrid.add(name);
//		secondGrid.add(icon2);
		allPanel.add(secondGrid);
		
		//thirdGrid
		Catagories.setText("Catagories");
		Catagories.setFont(new Font("Serif",Font.BOLD,25));
		catagoryPanel.add(Catagories);
	    catagoryPanel.add(combo);
		//txtPanel.add(txt);

		
		//OkButton.setPreferredSize(new Dimension(100,60));
		//OkButton.setText("OK");
		Okpanel.add(OkButton);
		OkButton.addActionListener(this);
		back=new JButton("Back");
		back.setBounds(800,0,100,20);     
		back.addActionListener(this);
		Okpanel.add(back);
		
		thirdGrid.add(catagoryPanel,BorderLayout.NORTH);
	//	thirdGrid.add(txtPanel,BorderLayout.CENTER);
		thirdGrid.add(Okpanel,BorderLayout.SOUTH);
		thirdGrid.setBounds(500, 100, 200, 100);
		//Border border=BorderFactory.createLineBorder(Color.BLACK);
		//thirdGrid.setBorder(border);
		//thirdGrid.setBounds(100,100,100,100);
		allPanel.add(thirdGrid);
	   // BufferedImage bg;
		//bg=javax.imageio.ImageIO.read(new java.io.File("/HospitalSystemJava/hospital-hallway.jpg"));
		//Graphics2D g2=()
		ImageIcon image=new ImageIcon("/HospitalSystemJava/hospital-hallway.jpg");
		//Image bg=Toolkit.getDefaultToolkit().createImage("/hospital-hallway.jpg");
		thumb=new JLabel();
		thumb.setIcon(image);
		allPanel.add(thumb);
		
		setContentPane(allPanel);
		
		setTitle("Hospital System Controller");
		setSize(700,700);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocation(100,20);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setIconImage(img);
	}
	public static  void main(String[] args)
	{
		new Menu();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String s=txt.getText();
		
		if(e.getSource()==OkButton){
			if(s.equals(cata[1]))
				{new PatientRecord();
				setVisible(false);}
			else if(s.equals(cata[2])) 
				{new DoctorRecord();
				setVisible(false);}
		}	
		if(e.getSource()==back)
		{new LogIn();
		setVisible(false);}
	}
}