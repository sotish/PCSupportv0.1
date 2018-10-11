package com.sat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class GetResponseMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filePath = "C:/Users/jax/Desktop/BatchResponse.bat";

		try {

			Process p = Runtime.getRuntime().exec(filePath);
			p.waitFor();
			InputStream in = p.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int c = -1;
			while ((c = in.read()) != -1) {
				baos.write(c);
			}

			String response = new String(baos.toByteArray());
			System.out.println("Response From Exe : " + response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
