import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;

public class gameFrame extends JFrame {

	private JPanel contentPane;
	public static Timer updateClock;
	private static JLabel lblUpdateResult;
	public JPanel panelHome;
	public JPanel panelVisitor;


	//DragDrop Elements
	private JButton [] Ships;	
	public static int selectedShip;
	//DragDrop Elements

	//Game Panel Displays
	private JButton [] OppFields;
	private JButton [] HomeFields;	
	//Game Panel Displays


	//Game Parameters Home
	private int HomePlayerID;
	private String HomePlayerNick;
	private String [] HomeShips;
	private boolean IamHomePlayer;
	private boolean ItsMyTurn;
	private JButton btnShooter;
	private String HomeMoves;

	//Game Parameters Visitor	
	private int VisitorPlayerID;
	private String VisitorNick;
	private String [] OppShips;
	private String OppMoves;
	private boolean IsOppShipsSet;

	private int GameID;	
	//Game Parameters



	/**
	 * Launch the application.
	 */



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameFrame frame = new gameFrame(10,30);					
					frame.setVisible(true);				


				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param gameID 
	 * @param iD 
	 */
	public gameFrame(int gameID, int palyerid) {

		HomeMoves="";
		OppMoves="";
		GameID=gameID;
		String Details=GetGameDetails(GameID);


		String [] DetailsArr=Details.toString().split(",");

		if(Integer.parseInt(DetailsArr[2].toString())==palyerid)
		{
			HomePlayerID=Integer.parseInt(DetailsArr[2].toString());
			VisitorPlayerID=Integer.parseInt(DetailsArr[4].toString());
			IamHomePlayer=true;
			ItsMyTurn=true;
			HomePlayerNick=DetailsArr[1];		
			VisitorNick=DetailsArr[3];
		}
		else
		{
			HomePlayerID=Integer.parseInt(DetailsArr[4].toString());
			VisitorPlayerID=Integer.parseInt(DetailsArr[2].toString());
			IamHomePlayer=false;
			ItsMyTurn=false;
			HomePlayerNick=DetailsArr[3];		
			VisitorNick=DetailsArr[1];
		}
		System.out.println("Ýlk Detaylar alýndý, ben ev sahibiyim :"+ItsMyTurn);

		selectedShip=0;		
		setTitle(HomePlayerNick+" vs "+VisitorNick);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 603, 696);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("LEAVE GAME");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GameOver("You Leaved The Game");

			}					
		});








		btnNewButton.setBounds(353, 606, 143, 40);
		contentPane.add(btnNewButton);

		JLabel lblHomeNickName = new JLabel(HomePlayerNick);
		lblHomeNickName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeNickName.setFont(new Font("Consolas", Font.BOLD, 20));
		lblHomeNickName.setBounds(330, 11, 247, 40);
		contentPane.add(lblHomeNickName);

		JLabel lblVisitorNickName = new JLabel(VisitorNick);
		lblVisitorNickName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisitorNickName.setFont(new Font("Consolas", Font.BOLD, 20));
		lblVisitorNickName.setBounds(330, 343, 247, 40);
		contentPane.add(lblVisitorNickName);

		panelHome = new JPanel();
		panelHome.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});
		if(!ItsMyTurn)
			panelHome.setBackground(Color.RED);
		else
			panelHome.setBackground(Color.gray);	

		panelHome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelHome.setBounds(10, 10, 310, 310);
		contentPane.add(panelHome);
		panelHome.setLayout(null);






		//HomeFields
		ImageIcon iconFalse = new ImageIcon("images/false.png");
		HomeFields=new  JButton [100]; 
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				int count=(i+j*10);
				JButton btnHomeCell = new JButton();
				btnHomeCell.setHorizontalAlignment(SwingConstants.CENTER);
				btnHomeCell.setBounds(i*30+5, j*30+5, 30, 30);
				btnHomeCell.setIcon(iconFalse);
				HomeFields[count]=btnHomeCell;
				btnHomeCell.setVisible(false);
				panelHome.add(btnHomeCell);
			}
		}
		//HomeFields


		//Set Ships
		ShipDefaultsSetting shipDefaultsSetting= new ShipDefaultsSetting();
		shipDefaultsSetting.setPanel(panelHome);
		Ships=shipDefaultsSetting.Ships;
		for(int i=0;i<5;i++)
		{
			int j=i;
			Ships[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {SelectShip(j+1);}
			});
			Ships[j].addMouseWheelListener(new MouseWheelListener() {
				public void mouseWheelMoved(MouseWheelEvent e) {TurnShip();	}				
			});
		}
		//Set Ships	









		ImageIcon iconSeaBg = new ImageIcon("images/seaBg.jpg");
		JLabel lblPanelHomeBg = new JLabel(iconSeaBg);
		lblPanelHomeBg.addKeyListener(new KeyAdapter() {

		});
		lblPanelHomeBg.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {

				if(selectedShip>0)
				{					
					MoveSelectedShip(selectedShip,e);
				}


			}


		});
		lblPanelHomeBg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectShip(0);
			}
		});
		lblPanelHomeBg.setBounds(5, 5, 300, 300);
		panelHome.add(lblPanelHomeBg);
		lblPanelHomeBg.setBackground(Color.RED);



		panelVisitor = new JPanel();


		if(ItsMyTurn)
			panelVisitor.setBackground(Color.RED);
		else
			panelVisitor.setBackground(Color.gray);


		panelVisitor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelVisitor.setBounds(10, 340, 310, 310);
		contentPane.add(panelVisitor);
		panelVisitor.setLayout(null);

		btnShooter = new JButton("");
		btnShooter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*SHOOT*/
				MakeShoot(btnShooter);
				//System.out.println(btnShooter.getX());
			}


		});

		//OpponentFields


		//OppShips= {"0 0","0 1","0 2","0 3","0 4","1 0","1 1","1 2","1 3","2 0","2 1","2 2","3 0","3 1","4 0","4 1"};
		OppShips=new String[16];
		OppFields=new  JButton [100]; 
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				int count=(i+j*10);
				JButton btnOppCell = new JButton();
				btnOppCell.setHorizontalAlignment(SwingConstants.CENTER);
				btnOppCell.setBounds(i*30+5, j*30+5, 30, 30);
				btnOppCell.setIcon(iconFalse);
				OppFields[count]=btnOppCell;
				btnOppCell.setVisible(false);
				panelVisitor.add(btnOppCell);


			}
		}

		btnShooter.setVisible(false);


		btnShooter.setFont(new Font("Tahoma", Font.PLAIN, 5));
		btnShooter.setBackground(Color.RED);
		btnShooter.setBounds(5, 5, 30, 30);
		panelVisitor.add(btnShooter);

		panelVisitor.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int relativeX = e.getX();
				int relativeY = e.getY();


				int x=relativeX/30;
				int y=relativeY/30;
				x=x*30+5;
				y=y*30+5;
				btnShooter.setBounds(x, y, 30, 30);
				//System.out.println(x+" "+y);
			}
		});

		JLabel lblPanelVisitorBg = new JLabel(iconSeaBg);
		lblPanelVisitorBg.setBounds(5, 5, 300, 300);
		panelVisitor.add(lblPanelVisitorBg);


		lblUpdateResult = new JLabel("Set Your Ships...");
		lblUpdateResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateResult.setFont(new Font("Consolas", Font.BOLD, 14));
		lblUpdateResult.setBounds(330, 62, 247, 40);
		contentPane.add(lblUpdateResult);

		JButton btnStartGame = new JButton("START GAME");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartGame.setVisible(false);


				for(JButton ship: Ships)
				{
					ship.setEnabled(false);
				}
				//btnShooter.setVisible(true);
				SetYourShipsToDB();

				updateClock=new Timer();
				TimerTask gorev=new TimerTask(){
					public void run() 
					{
						UpdateGameInfo();						
					}


				};

				updateClock.schedule(gorev,1000,1000);
			}
		});
		btnStartGame.setBounds(353, 190, 224, 60);
		contentPane.add(btnStartGame);


	}
	private void SelectShip(int i) {

		for(int j=0;j<5;j++)
		{
			Ships[j].setBackground(Color.lightGray);
		}

		if(selectedShip==i )
		{	
			if(selectedShip>0)
				Ships[selectedShip-1].setBackground(Color.lightGray);
			selectedShip=0;

		}
		else
		{
			selectedShip=i;
			if(selectedShip>0)
			{			
				Ships[selectedShip-1].setBackground(Color.red);			
			}
			System.out.print("Ship "+i+" selected");
		}
	}
	private void MoveSelectedShip(int selectedShip,MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		int w=Ships[selectedShip-1].getWidth();
		int h=Ships[selectedShip-1].getHeight();
		x=x/30;
		y=y/30;
		x=x*30+5;
		y=y*30+5;

		if(!CollisionOrOverflow(x,y,w,h,selectedShip))
			Ships[selectedShip-1].setBounds(x, y, w, h);

	}
	private void TurnShip() {
		if(selectedShip>0)
		{
			int x=Ships[selectedShip-1].getX();
			int y=Ships[selectedShip-1].getY();
			int w=Ships[selectedShip-1].getWidth();
			int h=Ships[selectedShip-1].getHeight();
			x=x/30;
			y=y/30;
			x=x*30+5;
			y=y*30+5;

			if(!CollisionOrOverflow(x,y,h,w,selectedShip))
				Ships[selectedShip-1].setBounds(x, y, h, w);
		}

	}
	private boolean CollisionOrOverflow(int x,int y,int w,int h,int selectedShip)
	{
		int startCellY=(x-5)/30;
		int startCellX=(y-5)/30;
		if(x+w>305)
		{
			//System.out.print(x+w);
			return true;
		}
		if(y+h>305)
		{
			//System.out.print(x+w);
			return true;
		}
		int shipWidth=w/30;
		int shipHeight=h/30;
		String[]newCells=new String[(shipHeight*shipWidth)];
		String[]oldCells=new String[16-(shipHeight*shipWidth)];

		int counter=0;
		for(int i=0;i<shipWidth;i++)
		{
			for(int j=0;j<shipHeight;j++)
			{
				newCells[counter]=(startCellX+j)+" "+(startCellY+i);
				counter++;			
			}
		}
		//System.out.println(startCellX+" "+startCellY+" "+Arrays.toString(newCells));
		oldCells=GetOldCells(selectedShip,oldCells.length);	
		for(int i=0;i<oldCells.length;i++){
			for(int j=0;j<newCells.length;j++){
				if(oldCells[i].equals(newCells[j])){
					return true;
				}
			}
		}
		return false;
	}
	private String[] GetOldCells(int selectedShip2,int len) {
		String [] oldcells=new String[len];
		int counter=0;
		for(int i=0;i<5;i++)
		{
			if(i!=selectedShip-1)
			{
				int startCellY=(Ships[i].getX()-5)/30;
				int startCellX=(Ships[i].getY()-5)/30;

				boolean horizontal=true;
				if(Ships[i].getWidth()<Ships[i].getHeight())
				{
					horizontal=false;
				}

				switch(i)
				{
				case 0:
					if(!horizontal)
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+1+" "+startCellY;
						oldcells[counter+2]=startCellX+2+" "+startCellY;
						oldcells[counter+3]=startCellX+3+" "+startCellY;
						oldcells[counter+4]=startCellX+4+" "+startCellY;
						counter+=5;
					}
					else
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+" "+(startCellY+1);
						oldcells[counter+2]=startCellX+" "+(startCellY+2);
						oldcells[counter+3]=startCellX+" "+(startCellY+3);
						oldcells[counter+4]=startCellX+" "+(startCellY+4);
						counter+=5;
					}

					break;

				case 1:
					if(!horizontal)
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+1+" "+startCellY;
						oldcells[counter+2]=startCellX+2+" "+startCellY;   
						oldcells[counter+3]=startCellX+3+" "+startCellY; 
						counter+=4;
					}
					else
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+" "+(startCellY+1);
						oldcells[counter+2]=startCellX+" "+(startCellY+2);  
						oldcells[counter+3]=startCellX+" "+(startCellY+3); 
						counter+=4;
					}

					break;
				case 2:
					if(!horizontal)
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+1+" "+startCellY;
						oldcells[counter+2]=startCellX+2+" "+startCellY;                  		
						counter+=3;
					}
					else
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+" "+(startCellY+1);
						oldcells[counter+2]=startCellX+" "+(startCellY+2);                 		
						counter+=3;
					}

					break;
				default:
					if(!horizontal)
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+1+" "+startCellY;                		                  		
						counter+=2;
					}
					else
					{
						oldcells[counter]=startCellX+" "+startCellY;
						oldcells[counter+1]=startCellX+" "+(startCellY+1);                	               		
						counter+=2;
					}

					break;
				}
			}
		}


		//System.out.println(Arrays.toString(oldcells));

		return oldcells;
	}
	private void MakeShoot(JButton btnShooter) {

		int x=(btnShooter.getX()-5)/30;
		int y=(btnShooter.getY()-5)/30;
		int numberOfOppCell=x+y*10;
		String lastMove=y+" "+x;
		OppFields[numberOfOppCell].setVisible(true);



		boolean hitSuccess=false;
		for(String ship :OppShips )
		{
			if(lastMove.equals(ship))
			{
				hitSuccess=true;				
			}			
		}

		if(!hitSuccess)
		{
			//System.out.println("Karavana");
			ItsMyTurn=false;
		}

		try {
			SendMadeMoveRequest(lastMove);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String [] movesArray=HomeMoves.split("-");

		//kalan gemileri buldum
		int remainingShips=16;
		for(String move:movesArray )
		{
			for(String ship :HomeShips )
			{
				if(move.equals(ship))
				{
					remainingShips--;

				}
			}
		}
		
		System.out.println(remainingShips);
		/*
		if(remainingShips==0)
		{				
			GameOver();
		}
		
		*/
		
		
		
		
		

	}
	private  void SendMadeMoveRequest(String Move)throws IOException {

		System.out.println("Ýstek gönderildi, Hamle: "+Move+", Home : "+IamHomePlayer+", GameÝD : "+GameID);

		
		String USER_AGENT = "Mozilla/5.0";	
		String POST_URL = "http://orcunozdil.site/Battleship/makeShootRequest.aspx";
		String POST_PARAMS = "GameID="+GameID+"&ShootArray="+Move+"&SenderType="+IamHomePlayer;

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

			//System.out.print(response.toString());
			

		} else {
			System.out.println("POST request not worked");
		}

		
		 

	}
	private String GetGameDetails(int gameID) {
		String Details="";
		try {
			Details=GameDetailRequest(Integer.toString(gameID));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return Details;
	}
	private  String GameDetailRequest(String GameID)throws IOException {




		String USER_AGENT = "Mozilla/5.0";	
		String POST_URL = "http://orcunozdil.site/Battleship/gameDetailsRequest.aspx";
		String POST_PARAMS = "GameID="+GameID;

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

			//System.out.print(response.toString());
			return response.toString().replace(",,",", ,").replace(",,",", ,");



		} else {
			System.out.println("POST request not worked");
		}

		return"0,Hata";


	}
	private void SetYourShipsToDB() {
		//System.out.println("SettingShips");
		ImageIcon iconFlame = new ImageIcon("images/flame.png");
		ImageIcon iconFalse = new ImageIcon("images/false.png");

		int shipCounter=0;
		HomeShips= new String [16];
		for(int i=0;i<5;i++)
		{
			int y=(Ships[i].getX()-5)/30;
			int x=(Ships[i].getY()-5)/30;
			boolean horizontal=false;
			if(Ships[i].getWidth()>Ships[i].getHeight())
				horizontal=true;

			if(horizontal&& i==0)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=x+" "+(y+1);
				HomeShips[shipCounter++]=x+" "+(y+2);
				HomeShips[shipCounter++]=x+" "+(y+3);
				HomeShips[shipCounter++]=x+" "+(y+4);
			}
			if(!horizontal&& i==0)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=(x+1)+" "+y;
				HomeShips[shipCounter++]=(x+2)+" "+y;
				HomeShips[shipCounter++]=(x+3)+" "+y;
				HomeShips[shipCounter++]=(x+4)+" "+y;
			}
			if(horizontal&& i==1)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=x+" "+(y+1);
				HomeShips[shipCounter++]=x+" "+(y+2);
				HomeShips[shipCounter++]=x+" "+(y+3);

			}
			if(!horizontal&& i==1)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=(x+1)+" "+y;
				HomeShips[shipCounter++]=(x+2)+" "+y;
				HomeShips[shipCounter++]=(x+3)+" "+y;

			}
			if(horizontal&& i==2)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=x+" "+(y+1);
				HomeShips[shipCounter++]=x+" "+(y+2);				

			}
			if(!horizontal&& i==2)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=(x+1)+" "+y;
				HomeShips[shipCounter++]=(x+2)+" "+y;				

			}
			if(horizontal&& i>2)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=x+" "+(y+1);						

			}
			if(!horizontal&& i>2)
			{
				HomeShips[shipCounter++]=x+" "+y;
				HomeShips[shipCounter++]=(x+1)+" "+y;								

			}
		}
		//String [] HomeShips= {"0 0","0 1","0 2","0 3","0 4","1 0","1 1","1 2","1 3","2 0","2 1","2 2","3 0","3 1","4 0","4 1"};
		for(String s: HomeShips)
		{
			int buttonIndex=Integer.parseInt(s.substring(0, 1))*10+Integer.parseInt(s.substring(2, 3));
			HomeFields[buttonIndex].setIcon(iconFlame);

		}
		//System.out.println("MyShips :"+Arrays.toString(HomeShips).replace(", ", "-").replace("[", "").replace("]", ""));
		try {
			SetMyShipsRequest(Arrays.toString(HomeShips).replace(", ", "-").replace("[", "").replace("]", ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void SetOppShips(String oppShips)
	{


		ImageIcon iconFlame = new ImageIcon("images/flame.png");
		String []oppshipsArray=oppShips.split("-"); 
		IsOppShipsSet=true;
		for(String s : oppshipsArray)
		{			
			int x=Integer.parseInt(s.split(" ")[0]);
			int y=Integer.parseInt(s.split(" ")[1]);
			int count=(y+x*10);
			OppFields[count].setIcon(iconFlame);
		}
		OppShips=oppshipsArray;

	}
	private void SetMyShipsRequest(String shipsArray)throws IOException {	

		String USER_AGENT = "Mozilla/5.0";	
		String POST_URL = "http://orcunozdil.site/Battleship/setMyShipsRequest.aspx";
		String POST_PARAMS = "ShipsArray="+shipsArray+"&GameID="+GameID+"&SenderType="+IamHomePlayer;

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

			//lblUpdateResult.setText(response.toString());
			System.out.println(response.toString());


		} else {
			System.out.println("POST request not worked");
		}

	}
	private void UpdateGameInfo() {


		String Details=GetGameDetails(GameID);
		String [] DetailsArr=Details.toString().split(",");

		String GameStatus=DetailsArr[9];
		String OppShips="";
		String OppMoves="";
		lblUpdateResult.setText("");

		//detaylardan visitor gemileri alýndý
		if(IamHomePlayer)
		{
			OppShips=DetailsArr[6];
			OppMoves=DetailsArr[8].trim();
			HomeMoves=DetailsArr[7].trim();
		}
		else
		{
			OppShips=DetailsArr[5];
			OppMoves=DetailsArr[7].trim();
			HomeMoves=DetailsArr[8].trim();
		}
		ControlOppMoves(OppMoves);

		//Game Over
		if(GameStatus.equals("1"))
		{			
			lblUpdateResult.setText("Game Over");
			GameOver("You Won");
			//System.out.println("Oyun bitti");
			return;
		}		

		//Rakip gemilerini yerleþtirmemiþ
		if(OppShips.trim().equals(""))
		{			
			lblUpdateResult.setText("Waiting opponent to start");
			//System.out.println("Rakip baþlamamýþ");
			return;
		}


		//Rakip gemi bilgilerini al
		if(!IsOppShipsSet)
		{
			SetOppShips(OppShips);
			//System.out.println("Rakip gemi bilgileri alýndý");
		}

		if(ItsMyTurn)
		{			
			lblUpdateResult.setText("Your Turn, Make Move");
			btnShooter.setVisible(true);
			panelHome.setBackground(Color.gray);
			panelVisitor.setBackground(Color.red);
			return;
		}
		else
		{
			lblUpdateResult.setText("Waiting for Move");
			btnShooter.setVisible(false);
			panelHome.setBackground(Color.red);
			panelVisitor.setBackground(Color.gray);
		}

		
		
		
		
		
		
		
		








	}

	private void ControlOppMoves(String oppMoves) {
		if(!OppMoves.equals(oppMoves))
		{


			OppMoves=oppMoves;
			String [] movesArray=OppMoves.split("-");

			//kalan gemileri buldum
			int remainingShips=16;
			for(String move:movesArray )
			{
				for(String ship :HomeShips )
				{
					if(move.equals(ship))
					{
						remainingShips--;

					}
				}
			}
			if(remainingShips==0)
			{				
				GameOver("You Lost");
			}
				
			
			
			
			
			

			String lastMove=movesArray[movesArray.length-1];
			boolean hitSuccess=false;
			for(String ship :HomeShips )
			{
				if(lastMove.equals(ship))
				{
					hitSuccess=true;				
				}			
			}

			if(hitSuccess)
			{
				//System.out.println("Ýsabet");
				ItsMyTurn=false;
			}
			else
			{
				//System.out.println("Karavana");
				ItsMyTurn=true;

			}


			for(String move:movesArray )
			{
				if(move.length()==3)
				{
					int x= Integer.parseInt(move.split(" ")[0]);
					int y= Integer.parseInt(move.split(" ")[1]);
					int cellNumber=x*10+y;
					HomeFields[cellNumber].setVisible(true);				
				}
			}




		}



	}

	private void GameOver(String Message) {
		updateClock.cancel();
		try {
			finishGameRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnShooter.setVisible(false);
		JOptionPane.showMessageDialog(null, HomePlayerNick+" "+Message+" , GAME OVER!");
		dispose();		
		indexFrame.GameFrameClosed();
	}
	private void finishGameRequest()throws IOException {	

		String USER_AGENT = "Mozilla/5.0";	
		String POST_URL = "http://orcunozdil.site/Battleship/finishGame.aspx";
		String POST_PARAMS = "GameID="+GameID;

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

			//lblUpdateResult.setText(response.toString());
			System.out.println(response.toString());


		} else {
			System.out.println("POST request not worked");
		}

	}
}
