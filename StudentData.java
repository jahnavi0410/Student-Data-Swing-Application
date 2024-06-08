
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class StudentData extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentData frame = new StudentData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD DATA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{     Class.forName("com.mysql.cj.jdbc.Driver");//load the driver
		             String url = "{url}";
		              String username = "{username}";
		             String password = "{password}";
                    Connection con = DriverManager.getConnection(url,username,password);//create a connection
                     Statement st = con.createStatement();
					   
                        if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||textField_3.getText().equals("")||textField_4.getText().equals(""))
				          {
		            		JOptionPane.showInputDialog(this,"Pls Enter all data");	
				          }
                        else
                        {   String ID = textField.getText();
                             String Name = textField_1.getText();
                             String Gender = textField_2.getText();
                             String Age = textField_3.getText();
                             String Branch = textField_4.getText();
                        	String data[] = {ID,Name,Gender,Age,Branch};
							 DefaultTableModel model = (DefaultTableModel) table.getModel();
							 model.addRow(data);
							 JOptionPane.showInputDialog(this,"Data Added Successfullt");
							/* textField.setText("");
							 textField_1.setText("");
							 textField_2.setText("");
							 textField_3.setText("");
							 textField_4.setText("");*/
							String q2 =  "insert into SD(ID,Name,Gender,Age,Branch)values(?,?,?,?,?)";
							PreparedStatement pstmt = con.prepareStatement(q2);
							pstmt.setString(1,ID);
							pstmt.setString(2,Name);
							pstmt.setString(3,Gender);
							pstmt.setString(4,Age);
							pstmt.setString(5,Branch);
							pstmt.executeUpdate();
							
							 
                        }
                        st.close();
                        con.close();
                        
                       
				
				
			}
				
				catch(Exception e5)
				{
					System.out.println(e5);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(47, 87, 130, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(37, 157, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(37, 193, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(37, 230, 62, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Age");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(37, 266, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Branch");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(37, 304, 102, 13);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(148, 156, 112, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 192, 112, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(148, 229, 112, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(148, 265, 112, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(149, 303, 112, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("VIEW DATA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2)
			{
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");//load the driver
			        String url = "jdbc:mysql://localhost:3306/jdbc";
			        String username = "root";
			        String password = "root123";
                   Connection con = DriverManager.getConnection(url,username,password);//create a connection
                    Statement st = con.createStatement();
                    String q = "select * from SD";
                    ResultSet set = st.executeQuery(q);
                   ResultSetMetaData rsmd = (ResultSetMetaData) set.getMetaData();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					 int cols = rsmd.getColumnCount();
						String[] colname = new String[cols];
						for(int i=0;i<cols;i++)
						{
							colname[i] = rsmd.getColumnName(i+1);
						}
						((javax.swing.table.DefaultTableModel) model).setColumnIdentifiers(colname);
						while (set.next()) {
		        		    String ID = set.getString(1);
		        	        String Name = set.getString(2);
		        	        String Gender = set.getString(3);
		        	        String Age = set.getString(4);
		        	        String Branch = set.getString(5);
		        	        String[] row = {ID,Name,Gender,Age,Branch};
		        	        ((javax.swing.table.DefaultTableModel) model).addRow(row);
		        	       
		        	      }
		        	st.close();
		        	con.close();
				}
				catch(Exception e3)
				{
					e3.printStackTrace();
				}
			}
			});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(450, 87, 130, 35);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(353, 151, 358, 171);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_5 = new JLabel("WELCOME!!");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(319, 32, 91, 13);
		contentPane.add(lblNewLabel_5);
	}

}
