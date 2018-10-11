package com.sat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DosCommand01 {

	public static void myPing() {

		try {
			Process p = Runtime.getRuntime().exec("cmd /c ver");
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println("ms version #  " + line);

				JOptionPane.showMessageDialog(null, "ms version is " + line);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		System.out.println("Done");

	}

	public static void myPing2() {

		try {
			Process prc = Runtime.getRuntime().exec("cmd /c Start notepad.exe  ");

			//prc.waitFor();

			BufferedReader reader2 = new BufferedReader(new InputStreamReader(prc.getInputStream()));

			String line2= null ;

			while ((line2 = reader2.readLine()) != null) {

				System.out.println("config " + line2);

				JOptionPane.showMessageDialog(null, "config is " + line2);
			}

		} catch (Exception e) {
			
		
			e.printStackTrace();

		System.out.println("nothing from ipconfig" );
		
		}

		
	}
	
}
	


