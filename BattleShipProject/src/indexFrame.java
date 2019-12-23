import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class indexFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordFieldPassword;
	private JPasswordField passwordFieldReg;
	private JTextField textFieldUsernameReg;
	private JPasswordField passwordFieldRegRepeat;
	private JTextField textFieldNickName;
	private JTextField textFieldUsername ;
	private JLabel lblLoginMessage;
	private JLabel lblRegisterMessage ;
	private JTabbedPane tabbedPane ;
	private JButton btnJoinGame;
	private JButton btnLogOut;
	private JPanel panelLogin;
	private JPanel panelRegister;
	private JLabel lblLoadingGame;
	private JLabel lblRequestResult;
	private JLabel lblWelcome;
	private Timer clock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					indexFrame frame = new indexFrame();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	/*Variables*/
	public Player homePlayer;
	public int gameRequestCounter;
	/*Variables*/
	public indexFrame() {
		setBackground(Color.BLACK);
		setTitle("BATTLESHIP PROJECT BY OR\u00C7UN \u00D6ZD\u0130L");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Tab Menu
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);		
		tabbedPane.setBounds(0, 149, 500, 254);
		contentPane.add(tabbedPane);



		//Tab Menu Welcome Page***********************************************************
		JPanel panelWelcome = new JPanel();
		panelWelcome.setBackground(Color.BLACK);
		tabbedPane.addTab("Welcome", null, panelWelcome, null);
		panelWelcome.setLayout(null);

		//Oyuna Gir butonu
		btnJoinGame = new JButton("JOIN A GAME");
		btnJoinGame.setFont(new Font("Consolas", Font.BOLD, 14));
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TryToFindOpponent();							
			}


		});
		btnJoinGame.setBounds(10, 122, 141, 76);
		panelWelcome.add(btnJoinGame);
		btnJoinGame.setVisible(false);

		//Çýkýþ Yap butonu
		btnLogOut = new JButton("LOG OUT");
		btnLogOut.setFont(new Font("Consolas", Font.BOLD, 14));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logout();
			}


		});
		btnLogOut.setBounds(325, 122, 141, 77);
		panelWelcome.add(btnLogOut);
		btnLogOut.setVisible(false);

		//Karþýlama Ekraný
		lblWelcome = new JLabel("Welcome Guest, Please Login");
		lblWelcome.setForeground(new Color(0, 255, 0));
		lblWelcome.setFont(new Font("Consolas", Font.BOLD, 14));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(10, 11, 475, 25);
		panelWelcome.add(lblWelcome);

		ImageIcon iconLoading = new ImageIcon("images/loader.gif");

		lblRequestResult = new JLabel("Searching game ...");
		lblRequestResult.setBackground(Color.WHITE);
		lblRequestResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequestResult.setForeground(Color.RED);
		lblRequestResult.setFont(new Font("Consolas", Font.BOLD, 14));
		lblRequestResult.setBounds(78, 97, 343, 25);
		lblRequestResult.setVisible(false);
		panelWelcome.add(lblRequestResult);
		lblLoadingGame = new JLabel(iconLoading);
		lblLoadingGame.setBounds(190, 97, 100, 100);
		panelWelcome.add(lblLoadingGame);
		lblLoadingGame.setVisible(false);
		//Tab Menu Welcome Page***********************************************************




		//Tab Menu Login Page***********************************************************
		panelLogin = new JPanel();
		tabbedPane.addTab("Login", null, panelLogin, null);
		panelLogin.setLayout(null);

		JLabel lblForUsername = new JLabel("Username (Email) :");
		lblForUsername.setFont(new Font("Consolas", Font.BOLD, 14));
		lblForUsername.setBounds(29, 64, 151, 14);
		panelLogin.add(lblForUsername);

		JLabel lblForPassword = new JLabel("Password         :");
		lblForPassword.setFont(new Font("Consolas", Font.BOLD, 14));
		lblForPassword.setBounds(29, 104, 151, 14);
		panelLogin.add(lblForPassword);

		lblLoginMessage = new JLabel("Please Login ...");
		lblLoginMessage.setForeground(Color.RED);
		lblLoginMessage.setBackground(new Color(0, 0, 0));
		lblLoginMessage.setFont(new Font("Consolas", Font.BOLD, 14));
		lblLoginMessage.setBounds(29, 11, 456, 36);
		panelLogin.add(lblLoginMessage);




		textFieldUsername = new JTextField();	
		textFieldUsername.setFont(new Font("Consolas", Font.BOLD, 14));
		textFieldUsername.setBounds(201, 58, 232, 20);
		panelLogin.add(textFieldUsername);
		textFieldUsername.setColumns(10);		

		//username max 50 karakter
		textFieldUsername.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				LimitInput(textField);   
			}
		});	

		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setFont(new Font("Consolas", Font.BOLD, 14));
		passwordFieldPassword.setBounds(201, 98, 232, 20);		
		//password max 50 karakter
		passwordFieldPassword.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				LimitInput(textField);   
			}
		});
		panelLogin.add(passwordFieldPassword);		

		//Login butonu
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}


		});
		btnLogin.setBackground(new Color(176, 224, 230));
		btnLogin.setFont(new Font("Consolas", Font.BOLD, 14));
		btnLogin.setBounds(29, 161, 170, 40);
		panelLogin.add(btnLogin);

		//Registera git butonu
		JButton btnGoToRegister = new JButton("GO TO REGISTER");
		btnGoToRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnGoToRegister.setBackground(new Color(255, 160, 122));
		btnGoToRegister.setFont(new Font("Consolas", Font.BOLD, 14));
		btnGoToRegister.setBounds(282, 161, 151, 40);
		panelLogin.add(btnGoToRegister);
		//Tab Menu Login Page***********************************************************







		//Tab Menu Register Page***********************************************************
		panelRegister = new JPanel();
		tabbedPane.addTab("Register", null, panelRegister, null);
		panelRegister.setLayout(null);

		lblRegisterMessage = new JLabel("Please Register ...");
		lblRegisterMessage.setForeground(new Color(0, 128, 0));
		lblRegisterMessage.setFont(new Font("Consolas", Font.BOLD, 14));
		lblRegisterMessage.setBackground(Color.BLACK);
		lblRegisterMessage.setBounds(10, 0, 475, 36);
		panelRegister.add(lblRegisterMessage);

		JLabel lblForUserNameReg = new JLabel("Username (Email) :");
		lblForUserNameReg.setFont(new Font("Consolas", Font.BOLD, 14));
		lblForUserNameReg.setBounds(23, 47, 151, 14);
		panelRegister.add(lblForUserNameReg);

		JLabel lblForPasswordReg = new JLabel("Password         :");
		lblForPasswordReg.setFont(new Font("Consolas", Font.BOLD, 14));
		lblForPasswordReg.setBounds(23, 78, 151, 14);
		panelRegister.add(lblForPasswordReg);

		JLabel lblForPasswordRegRepeat = new JLabel("Password Repeat  :");
		lblForPasswordRegRepeat.setFont(new Font("Consolas", Font.BOLD, 14));
		lblForPasswordRegRepeat.setBounds(23, 109, 151, 14);
		panelRegister.add(lblForPasswordRegRepeat);

		JLabel lblForNickName = new JLabel("Type a Nick Name :");
		lblForNickName.setFont(new Font("Consolas", Font.BOLD, 14));
		lblForNickName.setBounds(23, 140, 151, 14);
		panelRegister.add(lblForNickName);

		textFieldUsernameReg = new JTextField();
		textFieldUsernameReg.setFont(new Font("Consolas", Font.BOLD, 14));
		textFieldUsernameReg.setColumns(10);
		textFieldUsernameReg.setBounds(201, 47, 232, 20);
		textFieldUsernameReg.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				LimitInput(textField);   
			}
		});
		panelRegister.add(textFieldUsernameReg);

		passwordFieldReg = new JPasswordField();
		passwordFieldReg.setFont(new Font("Consolas", Font.BOLD, 14));
		passwordFieldReg.setBounds(201, 78, 232, 20);
		passwordFieldReg.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				LimitInput(textField);   
			}
		});
		panelRegister.add(passwordFieldReg);

		passwordFieldRegRepeat = new JPasswordField();
		passwordFieldRegRepeat.setFont(new Font("Consolas", Font.BOLD, 14));
		passwordFieldRegRepeat.setBounds(201, 109, 232, 20);
		passwordFieldRegRepeat.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				LimitInput(textField);   
			}
		});
		panelRegister.add(passwordFieldRegRepeat);

		textFieldNickName = new JTextField();
		textFieldNickName.setFont(new Font("Consolas", Font.BOLD, 14));
		textFieldNickName.setColumns(10);
		textFieldNickName.setBounds(201, 140, 232, 20);
		textFieldNickName.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				LimitInput(textField);               
			}

		});
		panelRegister.add(textFieldNickName);

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register();
			}
		});
		btnRegister.setFont(new Font("Consolas", Font.BOLD, 14));
		btnRegister.setBackground(new Color(255, 160, 122));
		btnRegister.setBounds(23, 175, 182, 40);
		panelRegister.add(btnRegister);

		JButton btnGotToLogin = new JButton("GOT TO LOGIN");
		btnGotToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnGotToLogin.setFont(new Font("Consolas", Font.BOLD, 14));
		btnGotToLogin.setBackground(new Color(176, 224, 230));
		btnGotToLogin.setBounds(263, 175, 170, 40);
		panelRegister.add(btnGotToLogin);


		ImageIcon iconBg = new ImageIcon("images/bg.jpg");
		JLabel lblHeaderImage = new JLabel(iconBg);
		lblHeaderImage.setBounds(0, 0, 500, 150);
		contentPane.add(lblHeaderImage);
	}
	//*************************************************************************************************************
	public static void GameFrameClosed()
	{
		//JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
	}
	private void LimitInput(JTextField textField) {
		String text = textField.getText();
		if(text.length()>=50)
		{
			text=text.substring(0, 49);                	           	
			textField.setText(text);
		}	

	}
	private void Login() {
		String username=textFieldUsername.getText();
		@SuppressWarnings("deprecation")
		String password=passwordFieldPassword.getText();
		String loginMessage="Please Login ...";

		if(username.trim().length()==0 || password.trim().length()==0)
		{
			loginMessage="Kullanýcý adý veya þifre boþ olamaz";				
		}
		else if(!isEmailValid(username))
		{
			loginMessage="Lütfen geçerli bir email adresi giriniz";	
		}
		else
		{
			homePlayer=null;
			try {
				homePlayer= LoginRequest(username,password);
			} catch (IOException e1) {				
				e1.printStackTrace();
			}



			if(homePlayer.ID==0 || homePlayer==null)
			{
				loginMessage="Kullanýcý bulunamadý, kullanýcý adý veya þifre hatalý";	
			}		
			else
			{
				textFieldUsername.setText("");
				passwordFieldPassword.setText("");
				SetWelcomePanel();
			}
		}
		lblLoginMessage.setText(loginMessage);


	}

	@SuppressWarnings("deprecation")
	private void Register() {
		String username=textFieldUsernameReg.getText();
		String passwordReg=passwordFieldReg.getText();
		String passwordRegRep=passwordFieldRegRepeat.getText();
		String nickname=textFieldNickName.getText();

		String registerMessage="Please register";
		if(username.trim().length()==0 || passwordReg.trim().length()==0 || nickname.trim().length()==0  )
		{
			registerMessage="Kullanýcý adý, þifre veya nick boþ olamaz";				
		}
		else if(!passwordReg.equals(passwordRegRep))
		{
			registerMessage="Þifreler eþleþmiyor";				
		}
		else if(!isEmailValid(username))
		{
			registerMessage="Lütfen geçerli bir email adresi giriniz";	
		}
		else
		{			

			try
			{
				String [] arrOfResult=RegisterRequest(username,passwordReg,passwordRegRep,nickname).split(",");
				System.out.print(arrOfResult[1]);

				registerMessage=arrOfResult[1];
				if(arrOfResult[0].equals("1"))
				{

					homePlayer=null;
					try {
						homePlayer= LoginRequest(username,passwordReg);
					} catch (IOException e1) {				
						e1.printStackTrace();
					}

					if(homePlayer.ID==0 || homePlayer==null)
					{
						registerMessage="Kullanýcý bulunamadý, kullanýcý adý veya þifre hatalý";	
					}		
					else
					{
						textFieldUsernameReg.setText("");
						passwordFieldReg.setText("");
						passwordFieldRegRepeat.setText("");
						textFieldNickName.setText("");
						registerMessage="Please Register ...";
						SetWelcomePanel();
					}
				}

			}
			catch (IOException e1) {				
				e1.printStackTrace();
			}
		}
		lblRegisterMessage.setText(registerMessage);

	}

	private Player LoginRequest(String username, String password)throws IOException {

		Player player= new Player();


		String USER_AGENT = "Mozilla/5.0";	
		String POST_URL = "http://orcunozdil.site/Battleship/login.aspx";
		String POST_PARAMS = "username="+username+"&password="+password;

		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		//System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();




			String[] arrOfResult = response.toString().split(",");

			player.ID=Integer.parseInt(arrOfResult[0]);	
			player.NickName=arrOfResult[1];
			player.point=Integer.parseInt(arrOfResult[2]);


			if(player.ID==0)
			{
				System.out.println(arrOfResult[3]);
			}

		} else {
			System.out.println("POST request not worked");
		}



		return player;
	}

	private String RegisterRequest(String username, String password, String passwordRep, String nickname) throws IOException{

		String USER_AGENT = "Mozilla/5.0";	
		String POST_URL = "http://orcunozdil.site/Battleship/register.aspx";
		String POST_PARAMS = "Username="+username+"&Password="+password+"&PasswordRepeat="+passwordRep+"&Nickname="+nickname;

		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		String responseString="";

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			responseString= response.toString();

		} else {
			System.out.println("POST request not worked");
		}

		return responseString;
	}

	private void Logout() {

		int dialogResult = JOptionPane.showConfirmDialog (null, "Do you realy want to logout?","Warning", JOptionPane.YES_NO_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION){

			tabbedPane.setEnabledAt(1, true); 
			tabbedPane.setEnabledAt(2, true);
			btnJoinGame.setVisible(false);
			btnLogOut.setVisible(false);
			tabbedPane.setSelectedIndex(1);
			homePlayer=null;
			lblWelcome.setText("Welcome Guest, Please Login");
		}

	}
	private boolean isEmailValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
	private void SetWelcomePanel( ) {

		//JOptionPane.showMessageDialog(null, "Hoþgeldiniz oyuncu");
		lblWelcome.setText("Welcome "+homePlayer.NickName.toUpperCase());
		tabbedPane.setSelectedIndex(0); 
		btnJoinGame.setVisible(true);
		btnLogOut.setVisible(true);

		tabbedPane.setEnabledAt(1, false); 
		tabbedPane.setEnabledAt(2, false);


	}
	private void TryToFindOpponent() {

		gameRequestCounter=0;

		clock=new Timer();
		TimerTask myTask=new TimerTask(){
			public void run() 
			{
				try {
					GameRequest(Integer.toString(homePlayer.ID));
				} catch (IOException e) {					
					e.printStackTrace();
				}
				//System.out.println("Süre :"+gameRequestCounter++);  	
				/*if(gameRequestCounter==5)
				{
					clock.cancel();
				}*/
			}
		};

		clock.schedule(myTask,1000,3000);

		lblLoadingGame.setVisible(true);
		lblRequestResult.setVisible(true);


	}

	private void GameRequest(String PlayerID)throws IOException {
		String USER_AGENT = "Mozilla/5.0";	
		String POST_URL = "http://orcunozdil.site/Battleship/gameRequest.aspx";
		String POST_PARAMS = "PlayerID="+PlayerID;

		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		//System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			String[] arrOfResult = response.toString().split(",");

			int gameID=Integer.parseInt(arrOfResult[0]);	
			//int visitor=Integer.parseInt(arrOfResult[1]);	
			String message=arrOfResult[2];
			if(gameID==0)
			{
				lblRequestResult.setText(message);
			}
			else
			{
				clock.cancel();
				lblLoadingGame.setVisible(false);
				lblRequestResult.setVisible(false);
				JFrame newFrame = new gameFrame(gameID,homePlayer.ID);				
				newFrame.setVisible(true);
			}

		} else {
			System.out.println("POST request not worked");
		}		
	}
}
