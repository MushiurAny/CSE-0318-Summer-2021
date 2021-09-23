package com.javatpoint.feereport;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ViewStudent extends JFrame {
	static ViewStudent frame;
	public ViewStudent() {
		//Code to view data in JTable
		List<Student> list=StudentDao.view();
		int size=list.size();
		//System.out.println(size);
		Object data[][]=new Object[size][13];
		int row=0;
		JLabel label;
		for(Student s:list){
			data[row][0]=String.valueOf(s.getRollno());
			data[row][1]=s.getName();
			data[row][2]=s.getEmail();
			data[row][3]=s.getCourse();
			data[row][4]=String.valueOf(s.getFee());
			data[row][5]=String.valueOf(s.getPaid());
			data[row][6]=String.valueOf(s.getDue());
			data[row][7]=s.getAddress();
			data[row][8]=s.getCity();
			data[row][9]=s.getState();
			data[row][10]=s.getCountry();
			data[row][11]=s.getContactno();
			label = new JLabel();
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(s.getStudent_imagePath()));
			} catch (IOException e) {
				System.out.println("ViewStudent Image theke bolchi... "+ e);;
			}
			Image dimg = img.getScaledInstance(100, 100,
					Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			label.setIcon(imageIcon);
			data[row][12] = label;
			row++;
		}
		String columnNames[]={"Rollno","Name","Email","Course","Fee","Paid","Due","Address","City","State","Country","Contact No","Image"};
		
		JTable jt=new JTable(data,columnNames);
		jt.getColumn("Image").setCellRenderer(new LabelRenderer());
		jt.setCellSelectionEnabled(true);
		JScrollPane sp=new JScrollPane(jt);
		add(sp);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 500);
	}

	class LabelRenderer implements TableCellRenderer{
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			TableColumn tc = table.getColumn("Image");
			tc.setMinWidth(100);
			tc.setMaxWidth(100);
			table.setRowHeight(100);
			return (Component) value;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ViewStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
