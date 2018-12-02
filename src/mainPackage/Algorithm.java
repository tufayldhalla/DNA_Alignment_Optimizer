package mainPackage;

import java.util.ArrayList;

public class Algorithm {
	//implements the Needleman Wunch Matrix of two specific DNA strands, 
	//traces back the matrix to obtain the optimal alignments
	
	//fields
	Score[][] nwMatrix;
	DNA DNA1 = new DNA();
	DNA DNA2 = new DNA();
	
	double matchScore;
	double mismatchScore;
	double gapPenalty;
	
	double scoreFromLeft; //keeps the score created using the specified adjacent cell
	double scoreFromTop;
	double scoreFromDia;
	
	//String[] singleAlignmentDNA1 = new String[2];
	ArrayList<String[]> multAlignments = new ArrayList<String[]>();
	
	
	//constructors
	public Algorithm(DNA DNA1,DNA DNA2,double match,double miss, double gap){
		nwMatrix = new Score[DNA1.sequence.length()+1][DNA2.sequence.length()+1];
		this.DNA1.copy(DNA1);
		this.DNA2.copy(DNA2);
		matchScore = match;
		mismatchScore = miss;
		gapPenalty = gap;
	}
	
	public void createMatrix(){
		//creates an object of type Score in each cell of the matrix
		for(int i=0;i<=DNA1.sequence.length();i++){
			for(int j=0;j<=DNA2.sequence.length();j++){
				this.nwMatrix[i][j] = new Score();
			}
		}
	}
	
	
	public void fillMatrix(){
		//Filling up matrix	with needleman algorithm	
		for(int i=0;i<=DNA1.sequence.length();i++){
			for(int j=0;j<=DNA2.sequence.length();j++){
				if(i==0){ //set the first row
					nwMatrix[i][j].cellScore = gapPenalty*j;
					nwMatrix[i][j].track1 = 'l';
				}
				else if(j==0){//set the first col
					nwMatrix[i][j].cellScore = gapPenalty*i;
					nwMatrix[i][j].track1 = 't';
					
				}
				else{//fills in the rest of the matrix
					scoreFromDia = nwMatrix[i-1][j-1].cellScore + CreatedScore(DNA1.sequence.charAt(i-1),DNA2.sequence.charAt(j-1));
					scoreFromTop = nwMatrix[i-1][j].cellScore + gapPenalty;
					scoreFromLeft = nwMatrix[i][j-1].cellScore + gapPenalty;
					nwMatrix[i][j].cellScore = Math.max(Math.max(scoreFromDia, scoreFromTop), scoreFromLeft);
					
					//set the direction for traceback
					if(scoreFromDia>scoreFromTop && scoreFromDia>scoreFromLeft)
						nwMatrix[i][j].track1 = 'd';
					else if(scoreFromTop>scoreFromDia && scoreFromTop>scoreFromLeft)
						nwMatrix[i][j].track1 = 't';
					else if(scoreFromLeft>scoreFromTop && scoreFromLeft>scoreFromDia)
						nwMatrix[i][j].track1 = 'l';
					else if(scoreFromDia==scoreFromTop && scoreFromLeft==scoreFromTop){
						nwMatrix[i][j].track1 = 'l';
						nwMatrix[i][j].track2 = 't';
						nwMatrix[i][j].track2 = 'd';
					}else if(scoreFromDia==scoreFromTop){
						nwMatrix[i][j].track1 = 'd';
						nwMatrix[i][j].track2 = 't';
					}else if(scoreFromDia==scoreFromLeft){
						nwMatrix[i][j].track1 = 'd';
						nwMatrix[i][j].track2 = 'l';
					}else if(scoreFromLeft==scoreFromTop){
						nwMatrix[i][j].track1 = 'l';
						nwMatrix[i][j].track2 = 't';
					}
	
				}//end if
				
				nwMatrix[i][j].track = nwMatrix[i][j].track1;//sets the current track to track1
			}//end for j
		}//end for i
			
	}//end fill Matrix
	
	public String[] singleTraceback(){
		String str1 = "";
		String str2 = "";
		return singleTraceback(str1,str2);
	}
	
	public String[] singleTraceback(String singleAlignmentDNA1,String singleAlignmentDNA2){
		//completes the traceback of the matrix. Only finds a single traceback solution. 
		int row = DNA1.sequence.length();
		int col = DNA2.sequence.length();
		
		while(!(row<1 && col<1)){ //we do not want row==0 and col==0 since at that cell, the trace back should be completed
			//we start at the bottom right
			
			if(nwMatrix[row][col].track=='t'){	//score comes from top
				singleAlignmentDNA2 += "_";
				singleAlignmentDNA1 += DNA1.sequence.charAt(row-1); 
				row--;
			}else if(nwMatrix[row][col].track=='l'){
				singleAlignmentDNA1 += "_";
				singleAlignmentDNA2 += DNA2.sequence.charAt(col-1);
				col--;
			}else if(nwMatrix[row][col].track=='d'){
				singleAlignmentDNA1 += DNA1.sequence.charAt(row-1);
				singleAlignmentDNA2 += DNA2.sequence.charAt(col-1);
				col--;
				row--;
			}//end if
		}//end while
		
		
		//reverse strands
		String str1 = "";
		String str2 = "";
		for(int i=singleAlignmentDNA1.length();i>0;i--){
			str1 += singleAlignmentDNA1.charAt(i-1);
		}
		for(int i=singleAlignmentDNA2.length();i>0;i--){
			str2 += singleAlignmentDNA2.charAt(i-1);
		}
		
		singleAlignmentDNA1 = str1;
		singleAlignmentDNA2 = str2;
		
		/*
		System.out.println();
		System.out.println("opt DNA2 = " + singleAlignmentDNA2);
		System.out.println("opt DNA1 = " + singleAlignmentDNA1);
		*/
		
		String[] str = new String[2];
		str[0] = singleAlignmentDNA1;
		str[1] = singleAlignmentDNA2;
		
		return str;
		
	}//end single traceback
	
	
	
	
	public void multTraceback(){
		//Performs the trace back for multiple solutions
		//Stores the multiple solutions in the multAlignment array
		
		//first level alignments
		int row = DNA1.sequence.length();
		int col = DNA2.sequence.length();
		String str1 = "";
		String str2 = "";
		
		//first normal traceback 
		multAlignments.add(singleTraceback(str1,str2));
		
		while(row!=0 && col!=0){ //keeps track of what cell we are looking to for a track2 possible alignment
			str1="";
			str2="";
			if(nwMatrix[row][col].track2 !=' '){
				nwMatrix[row][col].track = nwMatrix[row][col].track2;
				multAlignments.add(singleTraceback(str1,str2)); 
				nwMatrix[row][col].track = nwMatrix[row][col].track1;
			}
			
			if(nwMatrix[row][col].track=='t')
				row--;
			else if(nwMatrix[row][col].track=='l')
				col--;
			else if(nwMatrix[row][col].track=='d'){
				col--;
				row--;
			}
			
		}//end while
	}//end multiple trace backs
	
	
	public double CreatedScore(char a,char b){
		//used to determine 
		if(a==b)
			return matchScore;
		else
			return mismatchScore;	
	}
	

	
	
	
}//end class
