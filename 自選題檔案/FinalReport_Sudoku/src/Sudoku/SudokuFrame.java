package Sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *  Set the layout of the frame and add essential panel in it.
 */
@SuppressWarnings("serial")
public class SudokuFrame extends JFrame {

	private JLabel title;
	private JPanel numberButtonPanel;
	private SudokuPanel sudoku;
	private JButton timeButton;
	private JPanel stopButtonPanel;
	public boolean answerCorrect;
	public int checkExecuter;
	
	/**
	 *  Set the layout of the frame and add essential panel in it.
	 */
	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Set close condition
		this.setTitle("Sudoku");
		this.setSize(new Dimension(770,770));// Set ordered minimum size to ensure the window will not be to small
		this.setResizable(false);
		
		JPanel windowPanel = new JPanel();// The panel contains every essential elements.
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(700,700));
	
		numberButtonPanel = new JPanel();// The panel contains number buttons.
		numberButtonPanel.setLayout(new FlowLayout());
		numberButtonPanel.setPreferredSize(new Dimension(540, 60));
		sudoku = new SudokuPanel();// The SudokuPanel will produce and execute everything about every slot and box.
		sudoku.setLayout(new FlowLayout(FlowLayout.CENTER));
		title = new JLabel("寫數獨的小孩不會變壞");// Generate a title
		title.setFont(new Font("Noto Sans Traditional Chinese", 1, 36));
		
		timeButton = new JButton("Check");
		timeButton.addActionListener(sudoku.new StopListener());
		timeButton.setPreferredSize(new Dimension(80,40));
		stopButtonPanel = new JPanel();
		stopButtonPanel.setPreferredSize(new Dimension(600, 50));
		stopButtonPanel.add(timeButton);

		
		// Add these elements into the gathered panel
		windowPanel.add(title);
		windowPanel.add(sudoku);
		windowPanel.add(numberButtonPanel);
		windowPanel.add(stopButtonPanel);
		this.add(windowPanel);// Add the gathered panel into the frame

		for(int i = 1;i <= 9; i++) {//The for-loop will generate number buttons of 1 to 9 and add into numberButtonPanel
			String oneToNine = "" + i;
			JButton button = new JButton(oneToNine);
			button.setPreferredSize(new Dimension(50,50));
			button.addActionListener(sudoku.new NumberListener());// Set listener to make the number write in the board and the scene
			numberButtonPanel.add(button);
		}
	}
	
	/**
	 * Main function will start every move and make the frame be saw.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SudokuFrame frame = new SudokuFrame();
				frame.setVisible(true);
			}
		});
	}
	
}
