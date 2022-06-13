package Sudoku;

/**
 *	Set the Board, Set the changeable status, and method to check duplication
 */
public class SudokuBoard {

	protected int [][] board = new int[9][9];
	protected boolean [][] changable = new boolean[9][9];
	
	/**
	 * Set the original changeable status of every slot to true
	 */
	public SudokuBoard() {
		for(int row = 0;row < 9; row++) {
			for(int col = 0;col < 9; col++) {
				this.changable[row][col] = true;
			}
		}
	}

	/**
	 * Try to write the entry value into the board
	 * @param row	Targeted row to write in
	 * @param column	Targeted column to write in
	 * @param entry		Number intends to write in
	 * @param changeable	Set whether the ordered slot can be changed or not in the future
	 */
	public void writeIn(int row,int column,int entry,boolean changeable) {
		if(this.isSlotChangable(row, column))// Check whether the ordered slot can be changed or not
			if(!this.checkDuplicate(row, column, entry)) {// Check whether the entry value matches the duplicated rule 
				this.board[row][column] = entry;//Replace the value in the board
				this.changable[row][column] = changeable;//Set whether the ordered slot can be changed or not in the future
		}
	}
	
	/**
	 * Check whether the entry value matches the duplicated rule
	 * @param row	Targeted row to write in
	 * @param column	Targeted column to write in
	 * @param entry		Number intends to write in
	 * @return	whether the value matches the rule
	 */
	public boolean checkDuplicate(int row, int column, int entry) {
		for(int i = 0; i < 9; i++) {//The for-loop check whether there's a value as same as the entry in the targeted row.
			if(entry == this.board[row][i]) {
				return true;
			}
		}
		for(int j = 0; j < 9; j++) {//The for-loop check whether there's a value as same as the entry in the targeted column.
			if(entry == this.board[j][column]) {
					return true;
			}
		}
		// The followings talk about whether checks the box rule of Sudoku matches or not.
		int RowSection = row / 3;// Find which box the slot located in
		int ColumnSection = column / 3;
		int StartRow = RowSection * 3;
		int StartColumn = ColumnSection * 3;
		
		//After finding the box having to be checked, the for-loop will execute duplicated check.
		for(int i = StartRow;i < StartRow + 3; i++) {
			for(int j = StartColumn; j < StartColumn + 3; j++) {
				if(entry == this.board[i][j]) {
					return true;
				}
			}
		}
		return false;//If the entry pass previous checks, the value is able to write in.
	}

	/**
	 * Used by paintComponent in class SudokuPanel to check whether there's a valid value in the board can be presented on the panel
	 * @param row	Targeted row to write in
	 * @param column	Targeted column to write in
	 * @return	true if the slot have number inside
	 */
	public boolean isSlotHaveNumber(int row, int column) {
		return (this.board[row][column] != 0);
	}
	
	/**
	 * Invoke the changeable status in the ordered slot
	 * @param row	Targeted row to write in
	 * @param column	Targeted column to write in
	 * @return	true if value in the slot is not default value and it can be changed by players
	 */
	public boolean isSlotChangable(int row,int column) {
		return this.changable[row][column];
	}
	
	/**
	 * Used by paintComponent in class SudokuPanel to turn value of the board into String, which makes printout easier
	 * @param row	Targeted row to print out
	 * @param column	Targeted column to print out
	 * @return	Changed String
	 */
	public String valuetoString(int row,int column) {
		String returnString = "" + this.board[row][column];
		return returnString;
	}

	/**
	 * Used by method backtrackSudokuSolver in class SudokuGenerator to check whether correctAnswer is done or not
	 * @return	false if there's still a value of slot is 0
	 */
	public boolean boardFull() {
		for(int r = 0;r < 9;r++) {
			for(int c = 0;c < 9;c++) {
				if(this.board[r][c] == 0) return false;
			}
		}
		return true;//Return true if all slot is done
	}

	/**
	 * Used by method backtrackSudokuSolver in class SudokuGenerator to reset existed value to 0
	 * @param row	Targeted row to reset
	 * @param column	Targeted column to reset
	 */
	public void SlotReset(int row,int column) {
		this.board[row][column] = 0;
	}
	
	/**
	 * Compared answerByPlayer to correctAnswer to check whether the answer is correct or not
	 * @param answerByPlayer	Answer by player
	 * @return true	If the answer is correct.
	 */
	public boolean checkAnswer(SudokuBoard answerByPlayer) {
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				if(this.board[row][column] != answerByPlayer.board[row][column]) return false;
			}
		}
		return true;
	}
}
