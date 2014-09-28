/*
 * Author : SAURAV KUMAR
 * Intake : PT1081148
 * Project Name : Prison Management System
 * Developed in : JAVA
 * This System is meant for managing Prisoners and Wardens
 * Creation Date : 30 April 2012
 * Creation Place : APIIT SD INDIA
 * */
//imported packages
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
//Creation of Java Class ManageMain
public class ManageMain extends JFrame implements ActionListener 
{
	/*
	 * Declaration of labels, textfields, comboboxes, buttons, menu, panel, menuitems, list, ArrayList
	 */
  //labels used to display text on gui
  public JLabel lblpun, lblpnme, lblpedate, lblptime, lblphigh, lblplvl, lblpshare, lblwnme, lblwrank, lblnofp, lblnofw, lblnofhsp, lblnofhrw, lblstatus, lblwun, lblhead, lblfoot, lbllogname, lbllogpass, lblnull;
  //used for taking text input
  public JTextField txtpun, txtpnme, txtpedate, txtptime, txtplvl, txtwnme, txtwrank,txtwun, txtlogname;
  //used for taking password input
  public JPasswordField txtlogpass;
  //used for providing choices to user
  public JComboBox cmbphigh, cmbpshare;
  //used for allowing user submit their input or call any query
  public JButton btnpadd, btnpdel, btnprls, btnpdsp, btnpdspall, btnwadd, btnwdel, btnwdspall, btnwclr, btnpclr, btnlogin;
  //used to provide menu in gui
  public JMenu mainmenu;
  //used to add and group components
  public JPanel pnlPrisoner, pnlWarden, pnlCheck, pnlpdspall, pnlwdspall, pnllogin;
  public JMenuItem menuprisoner, menuwarden, menucheck, menulogout;
  //used to display values in list
  public List lstp, lstw;
  //used to store items of comboboxes here
  ArrayList<String> cmbList = new ArrayList<String>();
  /*
   * main method of the class
   */
  public static void main(String[] args) {
	  //creation  of object of class
	  ManageMain s = new ManageMain();
  }
  public ManageMain(){
	  //setting title of the GUI window
	  setTitle("Prison Management System");
	  //user not allowed to resize window
	  setResizable(false);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setBounds(100,100,600,600);
	  JMenuBar menubar = new JMenuBar();
	  mainmenu = new JMenu("Menu");
	  menuprisoner = new JMenuItem("Prisoner");
	  //adding action listener to the menu item
	  menuprisoner.addActionListener(this);
	  menuwarden = new JMenuItem("Warden");
	  menuwarden.addActionListener(this);
	  menucheck = new JMenuItem("Check Prison");
	  menucheck.addActionListener(this);
	  menulogout = new JMenuItem("Log Out");
	  menulogout.addActionListener(this);
	  //adding menu items to menu
	  mainmenu.add(menuprisoner);
	  mainmenu.add(menuwarden);
	  mainmenu.add(menucheck);
	  mainmenu.add(menulogout);
	  //adding separator between menu items
	  menucheck.add(new JSeparator());
	  menulogout.add(new JSeparator());
	  //adding menu to frame
	  menubar.add(mainmenu);
	  //setting range of visible items in list
	  lstp = new List(20, true);
	  lstw = new List(20,true);
	  pnlPrisoner = new JPanel();
	  pnlWarden = new JPanel();
	  pnlCheck = new JPanel();
	  pnlpdspall = new JPanel();
	  pnlwdspall = new JPanel();
	  pnllogin=new JPanel();
	  //setting label text
	  lblpun = new JLabel("Unique Number : ");
	  lblpnme = new JLabel("Name : ");
	  lblptime = new JLabel("Time to serve : ");
	  lblphigh = new JLabel("Security : ");
	  lblplvl = new JLabel("Security Level : ");
	  lblpshare = new JLabel("Allowed To Share Cell : ");
	  lblwnme = new JLabel("Name : ");
	  lblwrank = new JLabel("Rank : ");
	  lblwun = new JLabel("Unique Number : ");
	  lblpedate= new JLabel("Date of Prisonment (YYYY.MM.DD) : ");
	  lblhead = new JLabel();
	  lblfoot = new JLabel();
	  lblhead.setOpaque(true);
	  lblfoot.setOpaque(true);
	  lblhead.setBackground(Color.darkGray);
	  lblfoot.setBackground(Color.darkGray);
	  lblhead.setForeground(Color.white);
	  lblfoot.setForeground(Color.white);
	  //setting font of label
	  lblhead.setFont(new Font("Arial", Font.BOLD,20));
	  lblfoot.setFont(new Font("Arial", Font.BOLD,14));
	  lblnofp = new JLabel();
  	  lblnofw = new JLabel();
  	  lblnofhsp = new JLabel();
  	  lblnofhrw = new JLabel();
  	  lblstatus = new JLabel();
  	  lbllogname = new JLabel("            User Name : ");
  	  lbllogpass = new JLabel("            Password : ");
  	  lblnull = new JLabel("");
  	  //setting size of text fields
	  txtpun = new JTextField(20);
	  txtpnme = new JTextField(20);
	  txtptime = new JTextField(20);
	  txtplvl = new JTextField(20);
	  txtwun = new JTextField(20);
	  txtwnme = new JTextField(20);
	  txtwrank = new JTextField(20);
	  txtpedate=new JTextField(20);
	  txtlogname=new JTextField(20);
	  txtlogpass=new JPasswordField(20);
	  cmbphigh = new JComboBox();
	  cmbpshare = new JComboBox();
	  //adding data to list box
	  cmbList.add("High");
	  cmbList.add("Low");
	  cmbList.add("Yes");
	  cmbList.add("No");
	  //adding data to comboboxes
	  cmbphigh.addItem(cmbList.get(0));
	  cmbphigh.addItem(cmbList.get(1));
	  cmbpshare.addItem(cmbList.get(2));
	  cmbpshare.addItem(cmbList.get(3));
	  //adding buttons and setting their text
	  btnpadd = new JButton("Add");
	  btnpdel = new JButton("Delete");
	  btnprls = new JButton("Release");
	  btnpdsp = new JButton("Display");
	  btnpdspall = new JButton("Display All");
	  btnwadd = new JButton("Add");
	  btnwdel = new JButton("Delete");
	  btnwdspall = new JButton("Display All");
	  btnwclr = new JButton("Clear");
	  btnpclr = new JButton("Clear");
	  btnlogin = new JButton("LogIn");
	  //disabling delete and release buttons
	  btnpdel.setEnabled(false);
	  btnprls.setEnabled(false);
	  //adding action listener to button
	  btnpadd.addActionListener(this);
	  btnpdel.addActionListener(this);
	  btnprls.addActionListener(this);
	  btnpdsp.addActionListener(this);
	  btnpdspall.addActionListener(this);
	  btnwadd.addActionListener(this);
	  btnwdel.addActionListener(this);
	  btnwdspall.addActionListener(this);
	  btnwclr.addActionListener(this);
	  btnpclr.addActionListener(this);
	  btnlogin.addActionListener(this);
	  //adding controls to respective panels
	  pnlPrisoner.add(lblpun);
	  pnlPrisoner.add(txtpun);
	  pnlPrisoner.add(lblpnme);
	  pnlPrisoner.add(txtpnme);
	  pnlPrisoner.add(lblpedate);
	  pnlPrisoner.add(txtpedate);
	  pnlPrisoner.add(lblptime);
	  pnlPrisoner.add(txtptime);
	  pnlPrisoner.add(lblphigh);
	  pnlPrisoner.add(cmbphigh);
	  pnlPrisoner.add(lblplvl);
	  pnlPrisoner.add(txtplvl);
	  pnlPrisoner.add(lblpshare);
	  pnlPrisoner.add(cmbpshare);
	  pnlPrisoner.add(btnpadd);
	  pnlPrisoner.add(btnpdel);
	  pnlPrisoner.add(btnprls);
	  pnlPrisoner.add(btnpdsp);
	  pnlPrisoner.add(btnpdspall);
	  pnlPrisoner.add(btnpclr);
	  pnlWarden.add(lblwun);
	  pnlWarden.add(txtwun);
	  pnlWarden.add(lblwnme);
	  pnlWarden.add(txtwnme);
	  pnlWarden.add(lblwrank);
	  pnlWarden.add(txtwrank);
	  pnlWarden.add(btnwadd);
	  pnlWarden.add(btnwdel);
	  pnlWarden.add(btnwdspall);
	  pnlWarden.add(btnwclr);
	  pnlpdspall.add(lstp);
	  pnlwdspall.add(lstw);
	  pnlCheck.add(lblnofp);
	  pnlCheck.add(lblnofw);
	  pnlCheck.add(lblnofhsp);
	  pnlCheck.add(lblnofhrw);
	  pnlCheck.add(lblstatus);
	  pnllogin.add(lbllogname);
	  pnllogin.add(txtlogname);
	  pnllogin.add(lbllogpass);
	  pnllogin.add(txtlogpass);
	  pnllogin.add(lblnull);
	  pnllogin.add(btnlogin);
	  //setting border around panel
	  pnlPrisoner.setBorder(new EmptyBorder(20,20,20,20));
	  pnlWarden.setBorder(new EmptyBorder(20,20,20,20));
	  pnlCheck.setBorder(new EmptyBorder(20,20,20,20));
	  pnllogin.setBorder(new EmptyBorder(100,20,100,20));
	  //setting layout of frame and panels
	  setLayout(new BorderLayout());
	  pnlPrisoner.setLayout(new GridLayout(10,2,20,20));
	  pnlWarden.setLayout(new GridLayout(5,2,20,20));
	  pnlpdspall.setLayout(new GridLayout(1,1));
	  pnlwdspall.setLayout(new GridLayout(1,1));
	  pnlCheck.setLayout(new GridLayout(5,1,20,20));
	  pnllogin.setLayout(new GridLayout(3,2,20,20));
	  //adding panel to frame in center
	  getContentPane().add(pnllogin, BorderLayout.CENTER);
	  //enabling visibility of panel
	  pnllogin.setVisible(true);
	  lblhead.setText("Please Login");
	  getContentPane().add(lblhead, BorderLayout.NORTH);
	  lblfoot.setText("Login Form Displayed Successfully");
	  getContentPane().add(lblfoot, BorderLayout.SOUTH);
	  //adding menubar to frame
	  setJMenuBar(menubar);
	  //disabling menubar
	  mainmenu.setEnabled(false);
	  //enabling visibility of frame
	  setVisible(true);
	  //packing frame to size
	  pack();
  }

  public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuprisoner)
		{
			/*
			 * Action performed when prisoner menu chosen
			 */
			getContentPane().add(pnlPrisoner, BorderLayout.CENTER);
			pnlWarden.setVisible(false);
			pnlpdspall.setVisible(false);
			pnlwdspall.setVisible(false);
			pnlCheck.setVisible(false);
			//setting prisoner panel visible
			pnlPrisoner.setVisible(true);
			lblhead.setText("Prisoner Management Area");
			lblfoot.setText("Prisoner's Form Displayed Successfully");
			pack();
			clear();
			//clearing list boxes
			lstp.clear();
			lstw.clear();
		}
		else if (e.getSource() == menuwarden)
		{
			//displaying warden panel
			getContentPane().add(pnlWarden, BorderLayout.CENTER);
			pnlPrisoner.setVisible(false);
			pnlwdspall.setVisible(false);
			pnlpdspall.setVisible(false);
			pnlCheck.setVisible(false);
			pnlWarden.setVisible(true);
			lblhead.setText("Warden Management Area");
			lblfoot.setText("Warden's Form Displayed Successfully");
			pack();
			//clearing all fields
			clear();
			lstp.clear();
			lstw.clear();
		}
		else if (e.getSource() == menucheck)
		{
			//declaring array to be used to store prisoner warden counting
			int[] count=new int[4]; 
	        try{
	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	            Connection connect=DriverManager.getConnection("jdbc:odbc:pms");
	            Statement state=connect.createStatement();
	            //sql statement to check all prisoners that are in prison
	            String sql="select * from prisoner where status='inprison'";
	            ResultSet rs=state.executeQuery(sql);
	            while(rs.next())
	            {
	            	//total prisoners
	            	count[0]=count[0]+1;
	            	if(rs.getString(5).equals("High"))
	            		//high security prisoners
	            		count[1]=count[1]+1;
	            }
	            rs.close();
	            state.close();
	     }
	    catch (Exception ex) {
	    	lblfoot.setText(ex+"");
	    }
	    try{
	        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	        Connection connect=DriverManager.getConnection("jdbc:odbc:pms");
	        Statement state=connect.createStatement();
	        String sql="select * from warden where status='working'";
	        ResultSet rs=state.executeQuery(sql);
	        while(rs.next())
	        {
	        	count[2]=count[2]+1;
	        	//total wardens
	        	if(rs.getInt(3)>=3)
	        		//high rank wardens
	        		count[3]=count[3]+1;
	        }
	        rs.close();
	        state.close();
	    }
	    catch (Exception ex) {
	    	lblfoot.setText(ex+"");
	    }
	    //displaying output of check prison
	    lblnofp.setText("Number of Current Prisoners = "+count[0]);
	    lblnofw.setText("Number of Current Wardens = "+count[1]);
	    lblnofhsp.setText("Number of High Security Prisoner = "+count[2]);
	    lblnofhrw.setText("Number of High Rank Warden = "+count[3]);
	    lblstatus.setText("The prison must have " + count[0]/5 + " low rank warden and " +count[1]*2/4+" high rank warden.");
	    getContentPane().add(pnlCheck, BorderLayout.CENTER);
	    pnlPrisoner.setVisible(false);
	    pnlWarden.setVisible(false);
	    pnlpdspall.setVisible(false);
	    pnlwdspall.setVisible(false);
	    pnlCheck.setVisible(true);
	    lblhead.setText("Prison Check Result");
	    lblfoot.setText("Prison Checked Successfully");
	    pack();
	    clear();
	    lstp.clear();
		lstw.clear();
		}
		else if (e.getSource() == menulogout)
		{
			//hiding all panels
			pnlPrisoner.setVisible(false);
			pnlwdspall.setVisible(false);
			pnlpdspall.setVisible(false);
			pnlCheck.setVisible(false);
			pnlWarden.setVisible(false);
			//showing login panel
			pnllogin.setVisible(true);
			lblhead.setText("Please Login");
			lblfoot.setText("Login Form Displayed Successfully");
			mainmenu.setEnabled(false);
			lblhead.setText("Please Login");
		    lblfoot.setText("Logged out Successfully");
		    setBounds(100,100,580,420);
		    lblfoot.setText("Logged out Successfully");
			clear();
			lstp.clear();
			lstw.clear();
		}
		else if (e.getSource() == btnpadd)
		{
			try
            {
				/*
				   * setting database driver to JdbcOdbcDriver to connect to msaccess database
				   * connecting to the database through dsn
				   */
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection connect = DriverManager.getConnection("jdbc:odbc:pms");
                Statement state = connect.createStatement();
                //sql statement to add data to database
                String sql = "insert into prisoner values ('" + txtpun.getText() + "','" + txtpnme.getText() + "','" + txtpedate.getText() + "','"+ txtptime.getText() +"','"+ cmbphigh.getSelectedItem() +"','"+txtplvl.getText()+"','"+cmbpshare.getSelectedItem()+"','"+"inprison"+"')";
                //validates if any field left empty
                if (txtpun.getText().equals("") || txtpnme.getText().equals("") || txtpedate.getText().equals("") || txtptime.getText().equals("") || txtplvl.getText().equals(""))
                {
                	lblfoot.setText("ERROR : Please Provide Valid Input");
                }
                else
                {
                	state.execute(sql);
                    state.close();
                    lblfoot.setText("Prisoner Added Successfully");
                }
                connect.close();
                clear();
            }
            catch (Exception ex) {
            	lblfoot.setText(ex+"");
            }
		}
		else if (e.getSource() == btnpdel)
		{
			 try
	           { 
				   /*
				   * setting database driver to JdbcOdbcDriver to connect to msaccess database
				   * connecting to the database through dsn
				   */
				   int un = Integer.parseInt(txtpun.getText());
	               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	               Connection connect = DriverManager.getConnection("jdbc:odbc:pms");
	               Statement state = connect.createStatement();
	               //sql statement to change status of prisoner to deleted
	               String sql = "UPDATE prisoner SET status='deleted' WHERE punumber="+un;
	               state.execute(sql);
	               state.close();
	               connect.close();
	               clear();
	               lblfoot.setText("Prisoner Deleted Successfully");
	           }
	           catch (Exception ex) {
	        	   lblfoot.setText(ex+"");
	           }
		}
		else if (e.getSource() == btnprls)
		{
			try
	           {
				/*
				   * setting database driver to JdbcOdbcDriver to connect to msaccess database
				   * connecting to the database through dsn
				   */
					//parsing unique number to integer from textfield
				   int un = Integer.parseInt(txtpun.getText());
	               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	               Connection connect = DriverManager.getConnection("jdbc:odbc:pms");
	               Statement state = connect.createStatement();
	               //sql statement to select desired prisoner
	               String sql1="select * from prisoner where punumber=" +un+" AND status='inprison'";
	               //sql statement to release desired prisoner
	               String sql2 = "UPDATE prisoner SET status='released' WHERE punumber="+un;
	               ResultSet rs=state.executeQuery(sql1);
	               if(rs.next())
	               {
	            	   //declaring calendar objects
	            	   Calendar calendar = new GregorianCalendar();
	            	   Calendar calcurrent = new GregorianCalendar();
	            	   Calendar calentry = new GregorianCalendar();
	            	   //declaring string tokenizer object with input string and tokenizing character
	            	   StringTokenizer st = new StringTokenizer(rs.getString(3), "."); 
	            	   int days=0;
	            	   int years=0;
	            	   int months=0;
	            	   //loop goes until the tokenizer has next more tokens of string
	            	   while(st.hasMoreTokens()) {
	            		   //staring tokens in string variables
	            		   years = Integer.parseInt(st.nextToken());
	            		   months = Integer.parseInt(st.nextToken());
	            		   days = Integer.parseInt(st.nextToken());
	            		   }
	            	   //setting calendar date according to current date
            		   calcurrent.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
            		   //setting calendar date according to date retrieved from database
	            	   calentry.set(years,months,days);
	            	   //counting number of days difference between two calendar dates 
	            	   long servedays = (calcurrent.getTimeInMillis()-calentry.getTimeInMillis())/(1000*60*60*24);
	            	   //checking if days counted - serve days is less than 7 or not
	            	   if((long)rs.getInt(4)-servedays<7)
	            	   {
	            		   //executing the statement
	            		   state.execute(sql2);
	            		   lblfoot.setText("Prisoner Released Successfully");
	            	   }
	            	   else
		               {
	            		   //showing error message
		            	   JOptionPane.showMessageDialog(null,"Must have less than 7 days left in prisonment","Alert",JOptionPane.INFORMATION_MESSAGE);
		            	   lblfoot.setText("Error releasing prisoner");
		               }
	               }
	               rs.close();
	               connect.close();
	               state.close();
	               clear();
	           }
	           catch (Exception ex) {

	           }
		}
		else if (e.getSource() == btnpdsp)
		{
			try{
				/*
				   * setting database driver to JdbcOdbcDriver to connect to msaccess database
				   * connecting to the database through dsn
				   */
				//parsing unique number to integer variable
				   int un = Integer.parseInt(txtpun.getText());
	               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	               Connection connect=DriverManager.getConnection("jdbc:odbc:pms");
	               Statement state=connect.createStatement();
	               //Retrieving those prisoners data from database which are currently in prison
	               String sql="select * from prisoner where punumber=" +un+" AND status='inprison'";
	               ResultSet rs=state.executeQuery(sql);
	               //retrieving data from each row displaying them in their corresponding field
	               if(rs.next())
	               {
	            	   txtpun.setText(rs.getInt(1)+"");
	                   txtpnme.setText(rs.getString(2));
	                   txtpedate.setText(rs.getString(3));
	                   txtptime.setText(rs.getInt(4)+"");
	                   cmbphigh.setSelectedItem(rs.getString(5));
	                   txtplvl.setText(rs.getInt(6)+"");
	                   cmbpshare.setSelectedItem(rs.getString(7));
	                   btnpdel.setEnabled(true);
		               btnprls.setEnabled(true);
	               }
	               else
            	   {
	            	   //if data not available then displaying response message
            		   JOptionPane.showMessageDialog(null,"This Prisoner is no longer available","Alert",JOptionPane.INFORMATION_MESSAGE);
            		   clear();
            	   }
	               rs.close();
	               state.close();
	               lblfoot.setText("Prisoner Displayed Successfully");
	        }
	       catch (Exception ex) {
	    	   lblfoot.setText(ex+"");
	       }
		}
		else if (e.getSource() == btnpdspall)
		{
			try{
				/*
				   * setting database driver to JdbcOdbcDriver to connect to msaccess database
				   * connecting to the database through dsn
				   */
		          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		          Connection connect=DriverManager.getConnection("jdbc:odbc:pms");
		          Statement state=connect.createStatement();
		          //sql statement to retrieve data from database
		          String sql="select * from prisoner where status='inprison'";
		          ResultSet rs=state.executeQuery(sql);
		          //for each valid row in table data is added to list
		          while(rs.next())
		          {
		          	lstp.add("Unique No. = "+rs.getInt(1)+", Name = "+rs.getString(2)+", Date of entry =  "+rs.getString(3)+", Time to serve = "+rs.getInt(4)+", Security = " + rs.getString(5) + ", Security Level = "+ rs.getInt(6)+", Can Share Cell = "+rs.getString(7));
		          }
		          rs.close();
		          state.close();
		          clear();
		          lblfoot.setText("All Prisoners Displayed Successfully");
		   }
		  catch (Exception ex) {
			  lblfoot.setText(ex+"");
		  }
		  //disabling visibility of panel
			pnlPrisoner.setVisible(false);
			pnlWarden.setVisible(false);
			pnlwdspall.setVisible(false);
			pnlCheck.setVisible(false);
			//adding panel to frame
			getContentPane().add(pnlpdspall, BorderLayout.CENTER);
			//setting visibility of panel to enabled
			pnlpdspall.setVisible(true);
			lblhead.setText("List of All Prisoners");
			//packing frame to retain its size
			pack();
		}
		else if (e.getSource() == btnwadd)
		{
			try
            {
				/*
				   * setting database driver to JdbcOdbcDriver to connect to msaccess database
				   * connecting to the database through dsn
				   */
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection connect = DriverManager.getConnection("jdbc:odbc:pms");
                Statement state = connect.createStatement();
                //sql statement to insert input data to specific columns
                String sql = "insert into warden (wun, wname, wrank, status) values ('" + txtwun.getText() + "','" + txtwnme.getText() + "','" + txtwrank.getText() + "','" + "working" + "')";
                //checking if any field is left empty by user
                if (txtwun.getText().equals("") || txtwnme.getText().equals("") || txtwrank.getText().equals(""))
                {
                	//displaying error
                	lblfoot.setText("ERROR : Please Provide Valid Input");
                }
                else
                {
                	//if all fields are filled
                	state.execute(sql);
                    state.close();
                    lblfoot.setText("Warden Added Successfully");
                }
                //closing the connection
                connect.close();
                clear();
            }
            catch (Exception ex) {
            	lblfoot.setText(ex+"");
            }
		}
		//checking to see if click event's source matches or not with this button source
		else if (e.getSource() == btnwdel)
		{
			try
	           {
				   /*
				   * setting database driver to JdbcOdbcDriver to connect to msaccess database
				   * connecting to the database through dsn
				   */
	               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	               Connection connect = DriverManager.getConnection("jdbc:odbc:pms");
	               Statement state = connect.createStatement();
	               //sql statement for deleting valid data from database
	               String sql = "UPDATE warden SET status='deleted' WHERE wun ="+Integer.parseInt(txtwun.getText());
	               //executing sql statement
	               state.execute(sql);
	               state.close();
	               connect.close();
	               clear();
	               lblfoot.setText("Warden Deleted Successfully");
	           }
	           catch (Exception ex) {
	        	   lblfoot.setText(ex+"");
	           }
		}
		//checking to see if click event's source matches or not with this button source
		else if (e.getSource() == btnwdspall)
		{
			try{
				/*
				 * setting database driver to JdbcOdbcDriver to connect to msaccess database
				 * connecting to the database through dsn
				 */
	            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	            Connection connect=DriverManager.getConnection("jdbc:odbc:pms");
	            Statement state=connect.createStatement();
	            //sql statement for retrieving valid data from database
	            String sql="select * from warden where status='working'";
	            //executing result set query
	            ResultSet rs=state.executeQuery(sql);
	            //adding data to list for each valid available row
	            while(rs.next())
	            {
	            	lstw.add("Unique No. = "+ rs.getInt(1) +", Name = "+rs.getString(2)+", Rank = "+rs.getInt(3));
	            }
	            rs.close();
	            state.close();
	            clear();
	            lblfoot.setText("All Wardens Displayed Successfully");
	     }
	    catch (Exception ex) {
	    	//displaying exception message in label
	    	lblfoot.setText(ex+"");
	    }
	    	//disabling visibility of panels
			pnlPrisoner.setVisible(false);
			pnlWarden.setVisible(false);
			pnlpdspall.setVisible(false);
			pnlCheck.setVisible(false);
			//adding panel to form
			getContentPane().add(pnlwdspall, BorderLayout.CENTER);
			//setting panel visible
			pnlwdspall.setVisible(true);
			lblhead.setText("List of All Wardens");
			pack();
		}
		//checking to see if click event's source matches or not with this button source
		else if (e.getSource() == btnpclr)
		{
			//calling clear() method
			clear();
			lblfoot.setText("All fields cleared successfully");
		}
		//checking to see if click event's source matches or not with this button source
		else if (e.getSource() == btnwclr)
		{
			clear();
			lblfoot.setText("All fields cleared successfully");
		}
		else if (e.getSource() == btnlogin)
		{
			try
	           {
					//checks user name and password
				   if(txtlogname.getText().equals("admin") && txtlogpass.getText().equals("admin"))
				   {
					   //enables menu on successful login
					   mainmenu.setEnabled(true);
					   //hides login panel
					   pnllogin.setVisible(false);
					   lblhead.setText("WELCOME TO PRISON MANAGEMENT SYSTEM");
					   lblfoot.setText("Logged In Successfully");
					   //greets user
					   JOptionPane.showMessageDialog(null,"Welcome Admin","Information",JOptionPane.INFORMATION_MESSAGE); 
				   }
				   else
				   {
					   //warns user for wrong authentication
					   JOptionPane.showMessageDialog(null,"Wrong User Name or Password","Warning",JOptionPane.WARNING_MESSAGE);
					   clear();
				   }
	           }
	           catch (Exception ex) {
	        	   lblfoot.setText(ex+"");
	           }
		}
	}
  /*
   * void clear() method used for clearing or reseting input fields like textfields and comboboxes
   * also disables release and delete buttons
   * called whenever clear button on warden and prisoner form is clicked
   */
  public void clear()
  {
	    //clearing/reseting all fields
	    txtpun.setText("");
		txtpnme.setText("");
		txtptime.setText("");
		txtplvl.setText("");
		txtpedate.setText("");
		cmbphigh.setSelectedIndex(0);
		cmbpshare.setSelectedIndex(0);
		btnpdel.setEnabled(false);
        btnprls.setEnabled(false);
		txtwnme.setText("");
		txtwun.setText("");
		txtwrank.setText("");
		txtlogname.setText("");
		txtlogpass.setText("");
  }
  
}//end of class