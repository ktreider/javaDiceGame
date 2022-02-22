package diceGame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder; //for spacing


public class Dice extends JFrame {
	
	private JPanel imagePanel1; 	//show dieLabel1, the first die image
	private JPanel imagePanel2; 	//show dieLabel2, the second die image
	private JLabel dieLabel1; 		//hold dieImage
	private JLabel dieLabel2;		//hold dieImage2
	private JPanel buttonPanel; 	//hold button
	private JButton rollButton; 	//show button
	private JButton clearButton; 	//clear button
	private JPanel textPanel; 		//hold roll and win labels
	private JLabel rollLabel;		//show roll count
	private JLabel winLabel;		//show win count
	
	
	int roll_num = 0;	//counter for roll number when user clicks to roll
	int wins = 0;		//counter for number of wins
	
	//constructor
	public Dice() {
		//set title
		setTitle("Dice Game");
		 
		//action to close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//border manager
		setLayout(new BorderLayout());
		
		//build panels
		buildTextPanel();
		buildImagePanel();
		buildButtonPanel();
		
		//add panels to content pane
		add(textPanel, BorderLayout.NORTH);
		add(imagePanel1, BorderLayout.WEST);
		add(imagePanel2, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		

		//pack and display the window
		pack();
		setVisible(true);
		
	}
	
	
	//adds labels (holding text) to panel
	private void buildTextPanel() {
		//create panel
		textPanel = new JPanel();

		//create initial labels
		rollLabel = new JLabel("Rolls:");
		winLabel = new JLabel("Wins:");

		//for some spacing
		winLabel.setBorder(new EmptyBorder(0, 175, 0, 0));

		//add labels to panel
		textPanel.add(rollLabel);
		textPanel.add(winLabel);
		
	}
	
	
	//adds labels (which will hold images) to panel
	private void buildImagePanel() {
		//create panels
		imagePanel1 = new JPanel();
		imagePanel2 = new JPanel();

		//create initial labels
		dieLabel1 = new JLabel("<html>Click the button to roll the die.<br/>"
								+ "Computer on the left. Player on the right.</html>");
		dieLabel2 = new JLabel("");
		
		//for some spacing
		dieLabel1.setBorder(new EmptyBorder(95, 25, 115, 0));

		//add labels to respective panels
		imagePanel1.add(dieLabel1);
		imagePanel2.add(dieLabel2);

	}
	
	
	//add buttons to panel
	private void buildButtonPanel() {
		//create panel
		buttonPanel = new JPanel();
		
		//create roll and clear buttons, as well as spacing
		rollButton = new JButton("Roll");
		rollButton.setPreferredSize(new Dimension(250, 25));
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(250, 25));

		//register action listeners with respective buttons
		rollButton.addActionListener(new RollListener());
		clearButton.addActionListener(new ClearListener());
		
		//add buttons to panel
		buttonPanel.add(rollButton);
		buttonPanel.add(clearButton);

	}
	
	
	//handle click event
	private class RollListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			//random die roll 1-6
		    int dieResult = (int)((6 * Math.random())+1);
		    int dieResult2 = (int)((6 * Math.random())+1);
		    
		    //if player is higher, win. player is the east-position die (dieresult2)
		    if (dieResult2 > dieResult) {
			    wins += 1;
		    }
		    
		    roll_num += 1; //increment roll count on click	    
		    
			//read image file into ImageIcon object to obtain dice images
		    String path = "C:\\Users\\ktrei\\Documents\\senior2022\\CS453SeniorSeminar\\exams\\game\\images";
			ImageIcon dieImage = new ImageIcon(path + "\\dice-" + dieResult + ".png");
			ImageIcon dieImage2 = new ImageIcon(path + "\\dice-" + dieResult2 + ".png");

			//display image in label
			dieLabel1.setIcon(dieImage);
			dieLabel2.setIcon(dieImage2);

			//remove text from initial label & remove text spacing
			dieLabel1.setText(null);
			dieLabel1.setBorder(new EmptyBorder(0, 0, 0, 0));

			//set correct numbers for rolls and wins
			rollLabel.setText("Rolls: " + roll_num);
			winLabel.setText("Wins: " + wins);

			//pack frame again to accommodate the new size of labels/images
			pack();
			
		}
	}
	
	
	//handle click event
	private class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				
			    roll_num = 0; //reset roll count
			    wins = 0;	  //reset win count
			    winLabel.setText("Wins: 0"); //reset label
				rollLabel.setText("Roll: 0"); //reset label
				
		}
	}
	
	
	//main - create instance of class to display window/content pane
	public static void main(String[] args) {
		new Dice();
	}

}


