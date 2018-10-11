package com.sat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class PCSupport4 {

	Statement st;
	Connection con;
	ResultSet rs;

	private JFrame frame;
	private JTextField textField01;
	private JTextField textField02;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PCSupport4 window = new PCSupport4();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PCSupport4() {
		initialize();

		st = Connect01.Connect();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 490, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
//******************************************panel********************************
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 454, 359);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
//************************************************************************
		textField01 = new JTextField();
		textField01.setBounds(195, 52, 136, 20);
		panel.add(textField01);
		textField01.setColumns(10);
//********************************************************************
		textField02 = new JTextField();
		textField02.setBounds(195, 94, 136, 20);
		panel.add(textField02);
		textField02.setColumns(10);

		// *********************Button IP*********************************

		JButton btnNewButton = new JButton("IP");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				btnNewButton.setBackground(Color.RED);

				String myStr = "Goodbye";

				System.out.println(myStr);

				try {

					myPing();

				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// ******************Method myPing************************

			private void myPing() throws UnknownHostException {

				InetAddress add = InetAddress.getLocalHost();

				System.out.println("hostname " + add);

				JOptionPane.showMessageDialog(null, "IP address is: # " + add);
			}
		});

		btnNewButton.setBounds(10, 206, 136, 23);
		panel.add(btnNewButton);

	// ************************Button2*************************************************

		JButton btnNewButton2 = new JButton("NotePad");

		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DosCommand01.myPing2();

				// System.out.println("from hostName");
				// JOptionPane.showMessageDialog(null, "IP address is: # ");
			}

		});

		btnNewButton2.setBounds(180, 206, 103, 23);
		panel.add(btnNewButton2);

// *************************************Login Btn***********************************************

		JButton btnLogin = new JButton("LogIn");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				working();

			}
			
			public void working() {

				try {

					String uname = textField01.getText();

					String upass = textField02.getText();

					String query = "SELECT * FROM MYLOGIN where LOWER(Name)='" + uname + "' and LOWER(Password)='" + upass
							+ "'";

					rs = st.executeQuery(query);

					if (!rs.next()) {

						JOptionPane.showMessageDialog(null, "please put User id pasword");

					}

					String n = rs.getString("Name");

					String p = rs.getString("password");

					System.out.println("" + n + "--" + p + " ----" + uname + "  #   " + upass);

					if (n.equals(uname) && p.equals(upass)) {

						JOptionPane.showMessageDialog(null, "Right credentials");

						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									PCSupport6 frame = new PCSupport6();
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

					} else {

						JOptionPane.showMessageDialog(null, "wrong credentials");

					}

				} catch (Exception e) {

					System.out.println("conection failed" + e);
				}	
			}
			
			
		});
		
		btnLogin.setBounds(150, 151, 89, 23);
		panel.add(btnLogin);
		
		//****************************software********************************

		JLabel Login = new JLabel("PC Support SW");
		Login.setHorizontalAlignment(SwingConstants.CENTER);
		Login.setForeground(Color.MAGENTA);
		Login.setFont(new Font("Tahoma", Font.BOLD, 16));
		Login.setBounds(123, 11, 154, 30);
		panel.add(Login);

		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(100, 55, 46, 14);
		panel.add(lblUserId);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(100, 97, 46, 14);
		
		panel.add(lblPassword);		
		

		// ****************************CMD**********************************
		JButton btnCmd = new JButton("CMD");

		btnCmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Run batch file using java
				String filePath = " cmd.exe /k Start C:/Users/jax/Desktop/CMDOpening.bat";

				try {

					Process p = Runtime.getRuntime().exec(filePath);

					System.out.println("file is: " + filePath);

					/*
					 * p.waitFor();
					 * 
					 * InputStream in = p.getInputStream();
					 * 
					 * BufferedReader reader2 = new BufferedReader(new
					 * InputStreamReader(p.getInputStream()));
					 * 
					 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
					 * 
					 * // ByteArrayOutputStream baos = new
					 * ByteArrayOutputStream(); int c = -1;
					 * 
					 * while ((c = in.read()) != -1) { baos.write(c); }
					 * System.out.println("config " + c);
					 * 
					 * JOptionPane.showMessageDialog(null, "config is " + c);
					 * 
					 * baos.write(c);
					 * 
					 * String response = new String(baos.toByteArray());
					 * 
					 * System.out.println("Response From Exe : " + response);
					 */

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
		
		

		btnCmd.setBounds(33, 260, 89, 23);
		panel.add(btnCmd);
		
		
// ********************************CMD***************************************
		JButton btnCmdresponse = new JButton("CMDResponse");
		btnCmdresponse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String filePath = "cmd.exe /k Start C:/Users/jax/Desktop/BatchResponse.bat";

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

		});

		btnCmdresponse.setBounds(180, 260, 89, 23);
		panel.add(btnCmdresponse);
		
// ***********************************Services******************************
		JButton btnServices = new JButton("Name of the Services");
		btnServices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the Dialogue from user

				String name = JOptionPane.showInputDialog("service");

				// get the name of the typed input Service from the Pane

				String[] command = { "cmd.exe", "/c", "sc", "query", name };

				try {

					Process process = new ProcessBuilder(command).start();
					InputStream inputStream = process.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

					String line = null;

					// The JTextArea to wich the output stream will be
					// redirected.

					frame = new JFrame("Java");
					frame.setVisible(true);
					JTextArea textArea = new JTextArea();

					final PipedInputStream pin = new PipedInputStream();

					final PipedOutputStream pout = new PipedOutputStream(new PipedInputStream());

					System.setOut(new PrintStream(pout, true));

					// textArea.append("" +io.get);

					// textArea.append(line+"\n");

					// textArea.add(panel);

					/*
					 * /*Object [][] rows ={}; Object[][] colums = {};
					 * 
					 * JTable jt = new JTable(rows, colums);
					 * 
					 * JScrollPane scroll = new JScrollPane(jt);
					 * //jt.setFillsViewportHeight(true);
					 */

					// JOptionPane.showMessageDialog(null, new JScrollPane(jt));

					while ((line = bufferedReader.readLine()) != null) {

						System.out.println("inside while" + line);

						textArea.append(line + "\n");

						/*
						 * Object [][] rows ={}; Object[][] colums = {};
						 * 
						 * JTable jt = new JTable();
						 * 
						 * JOptionPane.showMessageDialog(null, new
						 * JScrollPane(jt));
						 */

						// JOptionPane.showMessageDialog(frame, "<HTML>
						// +bufferedReader.readLine() </HTML");

						// JOptionPane.showMessageDialog(null, "SERVICE_NAME is-
						// " + line);

					}

				} catch (Exception ex) {
					System.out.println("Exception : " + ex);
				}

				System.exit(0); // end the prgm
			}
		});
		btnServices.setBounds(317, 206, 89, 23);
		panel.add(btnServices);
		
// ************************OS*******************************
		JButton btnOs = new JButton("OS");
		btnOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String OSName = System.getProperty("os.name");
				String OSArch = System.getProperty("os.arch");
				String OSVersion = System.getProperty("os.version");
				String UserName = System.getProperty("user.name");

				// SystemInfo systemInfo = new SystemInfo();

				// HardwareAbstractionLayer hardwareAbstarctionLayer =
				// systemInfo.getHardware();

				// CentralProcessor processor =
				// hardwareAbstarctionLayer.getProcessor();

				// String vendor = OperatingSystem.getManufacturer();

				// ComputerSystem compSys =
				// hardwareAbstractionLayer.getComputerSystem();

				System.out.println("OS system is: " + OSName);
				System.out.println("OS system is: " + OSArch);
				System.out.println("OS system is: " + OSVersion);
				System.out.println("OS system is: " + UserName);

				JOptionPane.showMessageDialog(null, "Name: " + OSName + "\n Arch: " + OSArch + "\n Version: "
						+ OSVersion + "\n User name:" + UserName);

				// String processor = (OSName);

			}
		});
		btnOs.setBounds(317, 260, 89, 23);
		panel.add(btnOs);

		// **************************************************Redirect****************

		JButton btnRedirect = new JButton("Redirect");

		btnRedirect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				RedirectFrame f = new RedirectFrame();

				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout(5, 5));
				panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));

				JTextArea jtext = new JTextArea("Command direction", 10, 30);

				jtext.setBackground(Color.YELLOW);

				jtext.setSize(300, 300);
				
				
				String str = System.out.toString();

				System.out.println("hello"+str);
				
				jtext.append(str);
				
				//jtext.append(str);
				
				/*
				 * final PipedInputStream pin = new PipedInputStream();
				 * 
				 * PipedOutputStream pout = new PipedOutputStream(pin);
				 * 
				 * 
				 * 
				 * 
				 * System.setOut(new PrintStream(pout, true));
				 * 
				 * Thread thread = new Thread();
				 * 
				 * Thread reader = Thread.currentThread();
				 * 
				 * reader.start();
				 */

				// String str = System.console().readLine();

				// jtext.append(str);

				// jtext.append(pout);

				/*
				 * 
				 * 
				 * //jtext.
				 * 
				 * //jtext.setFont(); InputStream.this.
				 * 
				 * 
				 * InputStreamReader ir = new InputStreamReader(System.out);
				 * BufferedReader br = new BufferedReader(ir);
				 */

				// String str = System.setOut(out);;
				/*
				 * Console c = System.console(); String str= c.readLine("");
				 * jtext.setText(str);
				 */

				panel.add(jtext, BorderLayout.LINE_START);

				f.setContentPane(panel);

				f.pack();
				f.setLocationByPlatform(true);

				f.setVisible(true);

			}
		});

		btnRedirect.setBounds(33, 313, 89, 23);
		panel.add(btnRedirect);
		
		
	//*************************ListPrinters************************	
		JButton btnListprinters = new JButton("ListPrinters");
		btnListprinters.addActionListener(new ActionListener() {			
		
			public void actionPerformed(ActionEvent arg0) {
				
				BufferedWriter writer = null;
				 
				 String printerNames = "";
				 printerNames += getPrinterNames();
				 try {
				  writer = new BufferedWriter(new FileWriter("printer.txt"));
				  writer.write(printerNames);
				  
				  System.out.println("Done!");
				  
				 } catch (IOException e) {
				  e.printStackTrace();
				 } 
				 
				 finally {
				  try {
				   if(writer != null) {
				    writer.close();
				   }
				  } catch (Exception ex) {
				   ex.printStackTrace();
				  }
				 				 
				 }}

			public String getPrinterNames() {
				String content = "";
				 PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
				 
				 content += "Number of print services: " + services.length;
				 content += "\n";
				 
				 if(services.length != 0 || services != null) {
				  int i = 1;
				  for(PrintService service : services) {
				   String name = service.getName();
				   
				   content += "Printer " + i + " name: " + name;
				   content += "\n";
				   i++;
				  }
				 }
				 
				 System.out.println(content);
				 
				 JOptionPane.showMessageDialog( null, "printers: "+content);
				 
				 return content;
				
			}
			});
			
			
		btnListprinters.setBounds(180, 313, 89, 23);
		panel.add(btnListprinters);
		
		
	//*********************ListDrives*************************	
		JButton btnListdrives = new JButton("ListDrives");
		btnListdrives.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				File[] roots = File.listRoots();
		        for (int i = 0; i < roots.length; i++) {
		            System.out.println("root " + i + " = " + roots[i]);
		            
		            System.out.println("isNetworkDrive = " + isNetworkDrive(getDriveName(roots[i].getAbsolutePath())));
		        }
				
			}

			   public boolean isNetworkDrive(String drive) {
			        boolean result = false;

			        String command = "cmd.exe /c  net use " + drive;
			        
			        System.out.println("command = " + command);
			        try {
			            Process p = Runtime.getRuntime().exec(command);
			            InputStream stderr = p.getErrorStream();
			            InputStream stdout = p.getInputStream();

			            StringBuffer consoleErrors = new StringBuffer();
			            StringBuffer consoleOutput = new StringBuffer();
			            
			            String line;
			            BufferedReader in = new BufferedReader(new InputStreamReader(stdout));
			            while ((line = in.readLine()) != null) {
			                System.out.println(line);
			                consoleOutput.append(line);
			            }
			            in.close();
			            
			            in = new BufferedReader(new InputStreamReader(stderr));
			            while ((line = in.readLine()) != null) {
			                System.out.println(line);
			                consoleErrors.append(line);
			            }
			            in.close();
			            
			            String mark = "The network connection could not be found.";
			            if (consoleErrors.toString().contains(mark)) {
			                result = false;
			            } else {
			                result = true;
			            }

			        } catch (Exception e) {
			            e.printStackTrace();
			        } finally {
			        }
					return result;
			   }

			private String getDriveName(String path) {
				String result = path;
		        if (path.endsWith(":\\")) {
		            result = path.substring(0, path.length() - 1);
		        }

		        return result;
		        
			}
		});
		
		btnListdrives.setBounds(305, 313, 89, 23);
		panel.add(btnListdrives);


}

}
