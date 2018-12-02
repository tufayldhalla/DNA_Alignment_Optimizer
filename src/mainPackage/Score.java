package mainPackage;

public class Score {
	//keeps the score of a cell
	//keeps track of where the above score came from
	
	//fields
	double cellScore; //keeps the score of the current cell 
	char track;
	char track1 = ' '; //tracks the position where the score came from in the matrix t for top, l for left, and d for diagonal
	char track2 = ' '; //tracks a second possible path
	char track3 = ' '; //tracks a third possible path
	
	public Score(){
		cellScore = 0;
	}
}
