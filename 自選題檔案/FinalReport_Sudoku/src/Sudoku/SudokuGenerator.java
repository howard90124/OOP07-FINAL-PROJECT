package Sudoku;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generate a random Sudoku which matches the rule and produce default value in random
 */
public class SudokuGenerator {
	SudokuBoard answerByPlayer = new SudokuBoard();
	SudokuBoard correctAnswer = new SudokuBoard();
	public SudokuGenerator(){
		
	}
	/**
	 * To generate random Sudoku, there's two board to be produced by class SudokuBoard
	 * Step1: To produce correctAnswer, it uses a for loop to generate the first column of the answer.
	 * Step2: Use method backtrackSudokuSolver to find reasonable answer of the random column.
	 * Step3: Based on correctAnswer, a for-loop will pick up two or three number in one row as default values.
	 * @return	answerByPlayer	 Assist class SudokuPanel to change the content while player enters answers.
	 */
	public SudokuBoard generateRandomSudoku() {
		Random randomGenerator = new Random();
		//Produce a ArrayList to store possible number
		ArrayList<Integer> notUsedValues =  new ArrayList<Integer>();
		for(int i = 0; i < 9; i++) {
			notUsedValues.add(i);
		}
		// The for-loop is to generate the first column of the answer.
		for(int r = 0;r < 9;r++) { 
			int randomValue = randomGenerator.nextInt(notUsedValues.size());//Choose a number to fill in the answer of the first column
			correctAnswer.writeIn(r, 0, notUsedValues.get((Integer)randomValue) + 1, false);//Because method .nextInt would produce number from 0 to 8, there should add 1 to match the rule.
			notUsedValues.remove(randomValue);//Remove used number to ensure that next round of the loop won't generated number appearing before
		}

		backtrackSudokuSolver(0, 1, correctAnswer);//Use the method to produce other columns of the answer
		System.out.println(correctAnswer);
		
		// The for-loop is to generate default values and fill in the board answerByPlayer.
		// When randomGenerator pick up duplicated Column in the same Row, it will not produce default values, so Row 1, 4, 7 will generate one more default value to make sure there will generate 20 default values in expectation.
		for(int i = 0;i < 9; i++) {
			for(int j = 0;j < 3; j++) {//To ensure that every row will have two default values in expectation
				if(i % 3 != 0 && j == 2) continue;
				else {
					int randomColumn = randomGenerator.nextInt(9);
					answerByPlayer.writeIn(i, randomColumn, correctAnswer.board[i][randomColumn], false);//Values produced here cannot be changed by player, so there should change its status to unchangeable.
				}
			}
		}
		return answerByPlayer;//return Board with default values to SudokuPanel and help players to revise
	}
	public SudokuBoard getCorrectAnswer() {
		return correctAnswer;
	}

    /**
     * Solves correctAnswer
     * @param r the current row
     * @param c the current column
     * @param Answer	correctAnswerBoard
     * @return boolean	Indicate whether the answer is done or not
     */
    private boolean backtrackSudokuSolver(int r,int c,SudokuBoard Answer) {
		if(Answer.isSlotChangable(r, c)) {
			for(int i = 1;i <= 9;i++) {
				if(!Answer.checkDuplicate(r, c, i)) {//Fill in the board if it matches the rule
					Answer.writeIn(r, c, i, true);
					if(Answer.boardFull()) {
						return true;
					}
				
					if(r == 8) {
						if(backtrackSudokuSolver(0,c + 1, Answer))//Change to the next column if the current column is done
							return true;
					}
					else {
						if(backtrackSudokuSolver(r + 1,c, Answer))//Move to the next row if the column hasn't done yet
							return true;
					}
				}
			}
		}
		
		else {
			if(r == 8) {
				if(backtrackSudokuSolver(0,c + 1,Answer))//Change to the next column if the current column is done
					return true;
			} 
			else {
				if(backtrackSudokuSolver(r + 1,c,Answer))//Move to the next row if the column hasn't done yet
					return true;
			}
		}
		Answer.SlotReset(r, c);//The previous answer of the slot would be clear if the current slot has no answer to fill, and the previous one would find continuously.
		return false;
	}
}
