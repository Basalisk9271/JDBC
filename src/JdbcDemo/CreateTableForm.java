package JdbcDemo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CreateTableForm {
	public Connection con;
	public SqlServerDbAccessor dba;
	
	public CreateTableForm() {
		dba = new SqlServerDbAccessor();
		dba.setDbName("Test2");
		dba.connectToDb();
		con = dba.getConnection();
	}

	public static void main(String[] args) {
		final CreateTableForm ctf = new CreateTableForm();
		final JTextArea jtaQueryInput = new JTextArea(16, 50);
		jtaQueryInput.setText(ctf.dba.getUrl());
		
		JFrame form = new JFrame("Create Table Form");
		Container cp = form.getContentPane();
		cp.add(jtaQueryInput);

		JButton jbtCommit = new JButton("Commit Query");
		cp.add(jbtCommit, BorderLayout.SOUTH);
		
		jbtCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sql = jtaQueryInput.getText();
				System.out.println(sql);
				try {
					Statement stmt = ctf.con.createStatement();
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		form.pack();
		form.setVisible(true);
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
