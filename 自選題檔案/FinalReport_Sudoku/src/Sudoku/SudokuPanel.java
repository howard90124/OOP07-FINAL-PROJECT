package Sudoku;
import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 * Set up the drawing of game interface and action listener
 */
@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {

	private SudokuBoard playerBoard;//Store the board players filled in
	private SudokuBoard answerBoard;
	private int selectedColumn = -1;// Which column mouse clicked on
	private int selectedRow = -1;// Which row mouse clicked on
	private int boardWidth = 495;// Set the game board to specified width
	private int boardHeight = 495;// Set the game board to specified height
	public int checkRevised;
	
	/**
	 * Basic settings of game board
	 */
	public SudokuPanel() {
		this.setPreferredSize(new Dimension(495, 545));//set the game board to specified size
		this.addMouseListener(new MouseAdapterForPanel());
		SudokuGenerator generatorTool = new SudokuGenerator();
		this.playerBoard = generatorTool.generateRandomSudoku();//store answerByPlayer here
		this.answerBoard = generatorTool.getCorrectAnswer();
	}
	

	/**
	 * Draw game board, including strokes, numbers, selected slot
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D Sheet = (Graphics2D) g;
		Sheet.setColor(new Color(1.0f,1.0f,1.0f));//set background of the whole window to gray
		Sheet.fillRect(0, 50,boardWidth,boardHeight);
		Sheet.setColor(new Color(0.0f,0.0f,0.0f));// set background of game board to white
		//The following if-statement will present the correct context in every condition
		if (checkRevised == 1) {// Condition 1: The answer is correct
			Sheet.setFont(new Font("Heiti TC", 1, 28));
			Sheet.setColor(Color.RED);
			Sheet.drawString("太牛啦全對", 172, 30);
		}
		else if (checkRevised == -1){// Condition 2: The answer is incorrect.
			Sheet.setFont(new Font("Heiti TC", 1, 28));
			Sheet.setColor(Color.RED);
			Sheet.drawString("可惜有錯喔，再檢查看看吧", 82, 30);
		}
		else {// Condition 3: Players hasn't pressed "Check" button yet.
			Sheet.setFont(new Font("Heiti TC", 1, 16));
			Sheet.drawString("規則：填入1~9的數字使數字在欄、列、方格中都不重複", 52, 30);
		}
		
		int slotWidth = 55;
		int slotHeight = 55;
		Sheet.setColor(Color.BLACK);
		for(int x = 0;x <= 9; x++) {// The for-loop aims to draw vertical strokes on boundary.
			if(x % 3 == 0) {// If the stroke are used to separate each box, use thicker one
				Sheet.setStroke(new BasicStroke(4));
				Sheet.drawLine(x * slotWidth, 51, x * slotWidth, boardHeight + 50);
			}
			else {// If the stroke is only used to separate each slot, use thinner one
				Sheet.setStroke(new BasicStroke(2));
				Sheet.drawLine(x * slotWidth, 51, x * slotWidth, boardHeight + 50);
			}
		}

		for(int y = 0;y <= 9; y++) {// The for-loop aims to draw horizontal strokes on boundary.
			if(y == 3 || y == 6) {// If the stroke are used to separate each box, use thicker one
				Sheet.setStroke(new BasicStroke(4));
				Sheet.drawLine(0, y * slotHeight + 50, boardWidth, y * slotHeight + 50);
			}
			else {// If the stroke is only used to separate each slot, use thinner one
				Sheet.setStroke(new BasicStroke(2));
				Sheet.drawLine(0, y * slotHeight + 50, boardWidth, y * slotHeight + 50);
			}
		}
		
		// Following for-loop is used to set up numbers' font on the game board
		Font f = new Font("SanFrancisco", Font.PLAIN, 26);
		Sheet.setFont(f);
		FontRenderContext fContext = Sheet.getFontRenderContext();
		for(int row = 0;row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				if(!playerBoard.isSlotChangable(row, column) || playerBoard.isSlotHaveNumber(row, column)) {//if number in the slot can be changed or the board already has valid value inside, the game board should print it
					int textWidth = (int) f.getStringBounds(playerBoard.valuetoString(row, column), fContext).getWidth();// How width the number is
					int textHeight = (int) f.getStringBounds(playerBoard.valuetoString(row, column), fContext).getHeight();// How height the number is
					if(playerBoard.isSlotChangable(row, column)) {// it will set number to red if the number is writen by player
						Sheet.setColor(Color.RED);
						Sheet.drawString(playerBoard.valuetoString(row, column), (column*slotWidth)+((slotWidth/2)-(textWidth/2)), (row*slotHeight)+((slotHeight/2)+(textHeight/2) + 50));// Set position of the number on game board
					}
					else {// it will set number to black if the number is default value
						Sheet.setColor(Color.BLACK);
						Sheet.drawString(playerBoard.valuetoString(row, column), (column*slotWidth)+((slotWidth/2)-(textWidth/2)), (row*slotHeight)+((slotHeight/2)+(textHeight/2) + 50));// Set position of the number on game board
					}
				}
			}
		}
		// Show which slot mouth click
		if(selectedRow != -1 && selectedColumn != -1) {
			if(playerBoard.changable[selectedRow][selectedColumn] == true) {
			Sheet.setColor(new Color(0.0f,0.0f,1.0f,0.3f));
			Sheet.fillRect(selectedColumn * slotWidth + 2,selectedRow * slotHeight + 52,slotWidth - 3,slotHeight - 3);// The slot mouse clicked will show color differed from others
			}
		}
	}
	
	/**
	 * Follow NumberListener while player clicked number button and write the number into answerByPlayer if it follows duplicated rules
	 * @param buttonString	Which number button players click on
	 */
	public void playerChangeBoard(String buttonString) {
		if(selectedColumn != -1 && selectedRow != -1) {// While players don't click any slot, these number will be the default value, so the if statement will check position players' mouse clicked on.
			int buttonValue = Integer.parseInt(buttonString);// Change read number into integer
			playerBoard.writeIn(selectedRow, selectedColumn, buttonValue, true);
			repaint();// While a new action executed, the method paintComponent will be executed again and add a new entry into the game board.
		}
	}
	
	/**
	 * Execute next movement when players click number button in order to change number of the selected slot
	 */
	public class NumberListener implements ActionListener {
		/**
		 * Override actionPerformed to make panel read the number players clicked on
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			playerChangeBoard(((JButton) e.getSource()).getText());// While players try to change number, call method playerChangeBoard in SudokuPanel to change number if it's valid
		}
	}
	
	/**
	 * Execute next movement when players clicked a slot in order to select it 
	 */
	private class MouseAdapterForPanel extends MouseInputAdapter {
		/**
		 * Override mouseClicked to make panel read the slot players clicked on
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {//BUTTON1 indicates the player has a mouse click
				selectedRow = (e.getY() - 50) / 55; // Judge the row position the player clicks on
				selectedColumn = e.getX() / 55; // Judge the column position the player clicks on
				e.getComponent().repaint();// While a new action executed, the method paintComponent will be executed again and make the selected slot have another color.
			}
		}
	}
	
	/**
	 * Execute method checkAnswer while pressing "Check" button and present whether the answer is correct
	 */
	public class StopListener implements ActionListener {
		/**
		 * Override actionPerformed to check the answer and present it
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (playerBoard.checkAnswer(answerBoard)) {
				checkRevised = 1;// 1 means that the answer is correct
				repaint();
			}
			else {
				checkRevised = -1;// -1 means that the answer is incorrect
				repaint();
			}
			
		}
	}

}


