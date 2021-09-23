package com.javatpoint.feereport;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddStudent extends JFrame {
	static AddStudent frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	JTextArea textArea;
	private JLabel label;
	String imgpath = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ImageIcon ResizeImage(String imgPath)
	{
		ImageIcon MyImage = new ImageIcon(imgPath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	/**
	 * Create the frame.
	 */
	public AddStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddAccountant = new JLabel("Add Student");
		lblAddAccountant.setForeground(Color.DARK_GRAY);
		lblAddAccountant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblEmail = new JLabel("Email:");
		
		JLabel lblCourse = new JLabel("Course:");
		
		JLabel lblFee = new JLabel("Fee:");
		
		JLabel lblPaid = new JLabel("Paid:");
		
		JLabel lblDue = new JLabel("Due:");
		
		JLabel lblAddress = new JLabel("Address:");
		
		JLabel lblCity = new JLabel("City:");
		
		JLabel lblState = new JLabel("State:");
		
		JLabel lblCountry = new JLabel("Country:");
		
		JLabel lblContactNo = new JLabel("Contact No:");


		label = new JLabel();
		label.setBounds(10,10,100,100);

		JButton uploadImage = new JButton("Upload Image");
		uploadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				//filter the files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
				file.addChoosableFileFilter(filter);
				int result = file.showSaveDialog(null);
				//if the user click on save in Jfilechooser
				if(result == JFileChooser.APPROVE_OPTION){
					File selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					label.setIcon(ResizeImage(path));
					imgpath = path;
				}
				//if the user click on save in Jfilechooser
				else if(result == JFileChooser.CANCEL_OPTION){
					System.out.println("No File Select");
				}
			}
		});
		
		JButton btnAddAccountant = new JButton("Add Student");
		btnAddAccountant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				String email=textField_1.getText();
				String course=textField_2.getText();
				int fee=Integer.parseInt(textField_3.getText());
				int paid=Integer.parseInt(textField_4.getText());
				int due=Integer.parseInt(textField_5.getText());
				String address=textArea.getText();
				String city=textField_6.getText();
				String state=textField_7.getText();
				String country=textField_8.getText();
				String contactno=textField_9.getText();
				String imagePath = imgpath;
				Student s = new Student(name,email,course,fee,paid,due,address,city,state,country,contactno,imagePath);
				int status=StudentDao.save(s);
				
				if(status>0){
					JOptionPane.showMessageDialog(AddStudent.this,"Student added successfully!");
					textField.setText("");textField_1.setText("");textField_2.setText("");
					textField_3.setText("");textField_4.setText("");textField_5.setText("");
					textField_6.setText("");textField_7.setText("");textField_8.setText("");
					textField_9.setText("");textArea.setText("");
				}else{
					JOptionPane.showMessageDialog(AddStudent.this,"Sorry, Unable to add student!");
				}
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setRows(3);
		textArea.setColumns(20);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountantSection.main(new String[]{});
				frame.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(142)
							.addComponent(lblAddAccountant))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblCourse, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFee, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPaid, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDue, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_8, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_9, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
								.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
					.addContainerGap(124, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblContactNo)
					.addContainerGap(356, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCountry, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
					.addGap(350))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(383, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(label))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(uploadImage)
					.addGap(18)
					.addComponent(btnAddAccountant, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBack)
					.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblState, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(364, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAddAccountant)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCourse)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFee)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPaid)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDue)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCity)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblState)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCountry)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContactNo)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup((gl_contentPane.createParallelGroup(Alignment.LEADING))
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(uploadImage))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddAccountant, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
