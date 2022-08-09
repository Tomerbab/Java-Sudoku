import java.util.Scanner;

public class SODUKO{

	static Scanner sc = new Scanner(System.in);
	public static int[][] board = new int[9][9];
	public static boolean [][] checker = new boolean [9][9]; // this matrix is for tracking the given numbers from the user
	public static int val,col,row;
	public static int i =1;
	public static void main(String[] args) { // main
		val = 0;
		col = 0; 
		row = 0;
		completeBoard();
	}

	public static void completeBoard(){ // main function
		welcome();
		placeInput(); 
		pointToStart();
		while(notDone()) { 
			if(placeNumber()) 
				progressToNext();
			else
				returnToLast();	
		}
	}

	public static void welcome() {
		System.out.println("Welcome to FATMA SODUKO 2022. To start, press enter");
		String str1 = sc.nextLine();
		while(!str1.equals("")) {
			System.out.println("Welcome to FATMA SODUKO 2022. To start, press enter");
			str1 = sc.nextLine();
		}
	}

	public static void placeInput() { // placing the given numbers from the user
		int numVal = 1;
		int numRow = 1;
		int numCol = 1;
		while (!(numVal == 0 && numRow == 0 && numCol == 0)) { 
			System.out.println("\nPlease enter a given number and its location.\n");
			String str = sc.next();
			char charVal = str.charAt(3);
			char charCol = str.charAt(1); 
			char charRow = str.charAt(0);
			numVal = Character.getNumericValue(charVal);
			numRow = Character.getNumericValue(charRow);
			numCol = Character.getNumericValue(charCol);
			val = numVal;
			row = numRow;
			col = numCol;
			i = numVal;
			if (val == 0 && row == 0 && col == 0)
				break;
			else {
				if (numVal>9 || numVal<1 || numRow>8 || numRow<0 || numCol>8 || numCol<0 || str.charAt(2) != '-' || !checkIfCanPlace()) {
					System.out.println("\nThe input is not valid. Please try again");
					continue;
				}
				else {
					board[numRow][numCol] = numVal;
					checker[numRow][numCol] = true; 
				}
			}

		}
		System.out.println("\nThe given numbers are:\n");
		printBoard();
		System.out.println("\nlooking for a solution...\n");
	}
	public static void returnToLast() { // returns to the last square
		do {
			if(col == 0) {
				row --;
				col = 8;
			}
			else
				col --;
			if(row < 0 || col < 0 ) 
				return;
			i = board[row][col] +1;
		}while(isGiven()); // if the number is given keep tracking 
	}

	public static void printBoard() { // prints the board 
		for (int i=0; i<9; i++) {
			if (i>0) 
				System.out.println("");
			for (int j=0; j<9; j++) {
				System.out.print(board[i][j]+" ");
			}
		}
	}

	public static boolean placeNumber() { // the function is placing numbers from 1-9 according to the rules in addition to solving the board
		if(isGiven()) {
			return true;
		}
		while(i < 10) {
			if(checkIfCanPlace()) {
				board[row][col] = i;
				i = 1;
				return true; // means the number has been placed 
			} 
			else 
				i ++;
		}
		board[row][col] = 0;
		return false;
	}

	public static void pointToStart() { // point to the start of the board
		row = 0;
		col = 0;
		i = 1;
	}

	public static boolean notDone() { // checks whether you finished the board or not
		if(row < 0 || col < 0) {
			System.out.println("There is no valid solution");
			return false;
		}
		if(row == 8 && col == 8) {
			if(placeNumber()) {
				System.out.println("The solution is:\n");
				printBoard();
				return false; //means done
			}

		}
		return true;
	}

	public static boolean isGiven() { // checks if the number is given by the user
		if(checker[row][col]) 
			return true;
		return false;
	}

	public static void progressToNext() { // proceed to the next square
		if (col == 8) {
			row ++;
			col = 0;
		}
		else
			col ++;
	}

	public static boolean checkIfCanPlace(){ // the function checks whether you can or can't place a number
		for (int k = 0; k< 9; k++) {
			if(board[k][col] == i)
				return false;
		}
		for (int j = 0; j< 9; j++) {
			if(board[row][j] == i)
				return false;
		}
		int newRow = row-row%3;
		int newCol = col-col%3;
		for (int k = newRow; k < newRow+3;k++) {
			for(int j = newCol; j < newCol+3; j++) {
				if(board[k][j] == i)
					return false;;
			}
		}
		return true;
	}
}
