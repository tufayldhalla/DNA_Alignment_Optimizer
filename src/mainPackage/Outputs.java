package mainPackage;

import java.util.Scanner;

public class Outputs {

	public void initializedMatrix(Algorithm A1){
		//prints full matrix
		
				//print DNA2 sequence
				System.out.print("       ");
				for(int i=0;i<A1.DNA2.sequence.length();i++)
					System.out.printf("%6s",A1.DNA2.sequence.charAt(i));
				System.out.println("");
				
				//print matrix and DNA1 sequence
				for(int row=0;row<=A1.DNA1.sequence.length();row++){
					for(int col=0;col<=A1.DNA2.sequence.length()+1;col++){
						if(col==0&&row==0)
							System.out.print("  ");
						if(col==0 && row>0 && row<=A1.DNA1.sequence.length())
							System.out.print(A1.DNA1.sequence.charAt(row-1) + " ");
						//first row and first column values of the matrix
						if(col>0 && row == 0)
							System.out.printf("%6.1f", A1.nwMatrix[row][col-1].cellScore);
						if (row>0 && col == 0 && row<=A1.DNA1.sequence.length())
							System.out.printf("%6.1f", A1.nwMatrix[row][0].cellScore);
					}
					System.out.println("");
				}
	}

	public void outputFullMatrix(Algorithm A1){
		//prints full matrix
		
		//print DNA2 sequence
		System.out.print("       ");
		for(int i=0;i<A1.DNA2.sequence.length();i++)
			System.out.printf("%6s",A1.DNA2.sequence.charAt(i));
		System.out.println("");
		
		//print matrix and DNA1 sequence
		for(int row=0;row<=A1.DNA1.sequence.length();row++){
			for(int col=0;col<=A1.DNA2.sequence.length()+1;col++){
				if(col==0&&row==0)
					System.out.print("  ");
				if(col==0 && row>0 && row<=A1.DNA1.sequence.length())
					System.out.print(A1.DNA1.sequence.charAt(row-1) + " ");
				//Values for the matrix 
				if(col>0)
					System.out.printf("%6.1f", A1.nwMatrix[row][col-1].cellScore);
			}
			System.out.println("");
		}
	}//end output matrix
	
	
	
	
}
