import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShipDefaultsSetting {

	public  JButton [] Ships= new JButton[5];
	
	public void setPanel(JPanel panel) {
		JButton btnShip1 = new JButton();
		btnShip1.setBackground(Color.LIGHT_GRAY);		
		btnShip1.setBounds(5, 5, 150, 30);
		Ships[0]=btnShip1;
		panel.add(btnShip1);

		JButton btnShip2 = new JButton();
		
		btnShip2.setBackground(Color.LIGHT_GRAY);
	
		btnShip2.setBounds(5, 65, 120, 30);
		Ships[1]=btnShip2;
		panel.add(btnShip2);

		JButton btnShip3 = new JButton();
		
		btnShip3.setBackground(Color.LIGHT_GRAY);
	
		btnShip3.setBounds(5, 125, 90, 30);
		Ships[2]=btnShip3;
		panel.add(btnShip3);

		JButton btnShip4 = new JButton();
		
		btnShip4.setBackground(Color.LIGHT_GRAY);
	
		btnShip4.setBounds(5, 185, 60, 30);
		Ships[3]=btnShip4;
		panel.add(btnShip4);

		JButton btnShip5 = new JButton();
		
		btnShip5.setBackground(Color.LIGHT_GRAY);
		
		btnShip5.setBounds(5, 245, 60, 30);
		Ships[4]=btnShip5;
		panel.add(btnShip5);
		
		
		
	}
	
	
	
}
