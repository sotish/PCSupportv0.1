package com.sat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connect01 {

	private static Statement st;

	private static Connection con;

	@SuppressWarnings("unused")
	static Statement Connect() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "root");

			st = con.createStatement();

		} catch (Exception e) {

			// TODO: handle exception

			System.out.println(e);

			JOptionPane.showMessageDialog(null, "connection is failed");
		}

		return st;
	}

}
