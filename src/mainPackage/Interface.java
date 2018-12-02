package mainPackage;

import java.util.InputMismatchException;
import java.util.Scanner; 

public class Interface {

	//The reason why the main method was implemented is for this case, where if the user wanted to repeat the entire 
	//process, they can and the program would start from the beginning  
	public static void main(String[] args){
		boolean repeat;
		repeat = true;
		while (repeat) {
			repeat = UserInput();
		}
	}
	
	//The reason of this method is to ask the user for all the parameters such as DNA sequence 1 and 2, match score, mismatch score,
	//and gap penalty. Also to check for errors of the parameters, and if any, re-ask the user to input new values.
	public static boolean UserInput() {
		
		//Asking the user for the first DNA sequence
		DNA DNA1 = new DNA();
		Scanner var1 = new Scanner(System.in);
		System.out.print("Enter first DNA sequence: ");
		//Sending the entered sequence to the setSequence method to makes all letters capital and remove blank spaces if needed
		DNA1.setSequence(var1.nextLine());
		
		//Asking the user for the second DNA sequence
		DNA DNA2 = new DNA();
		Scanner var2 = new Scanner(System.in);
		System.out.print("Enter second DNA sequence: ");
		//Sending the entered sequence to the setSequence method to makes all letters capital and remove blank spaces if needed
		DNA2.setSequence(var2.nextLine());
		
		//An if statement if both DNA strings are invalid, then re-ask the user for new strings
		//A do while loop was implemented so keep asking until the right sequences are entered
		if (!(DNA2.valid() && DNA1.valid())) {
			do {
				System.out.println("One of the two DNA sequences was entered incorrectly");
				
				System.out.print("Please re-enter first DNA sequence: ");
				DNA1.setSequence(var1.next());
				
				System.out.print("Please re-enter second DNA sequence: ");
				DNA2.setSequence(var2.next());
	
			} while (!(DNA2.valid() && DNA1.valid())); 
		}
		
		//List of parameters
		double matchScore, mismatchScore, gapPenalty;
		
		//A try and catch is implemented for all three parameters in case the user entered a letter instead of a number
		try {
		Scanner var3 = new Scanner(System.in);
		System.out.print("Enter match score: ");
		matchScore = var3.nextDouble();
		} catch(InputMismatchException e) {
			System.err.print("Invalid input: Please input a number");
			System.out.println("");
			Scanner var3 = new Scanner(System.in);
			System.out.print("Enter match score: ");
			matchScore = var3.nextDouble();
		}
		
		try{
		Scanner var4 = new Scanner(System.in);
		System.out.print("Enter mismatch score: ");
		mismatchScore = var4.nextDouble();
		}  catch(InputMismatchException e) {
			System.err.print("Invalid input: Please input a number");
			System.out.println("");
			Scanner var4 = new Scanner(System.in);
			System.out.print("Enter mismatch score: ");
			mismatchScore = var4.nextDouble();
		}
		
		try{
		Scanner var5 = new Scanner(System.in);
		System.out.print("Enter gap penalty: ");
		gapPenalty = var5.nextDouble();
		}  catch(InputMismatchException e) {
			System.err.print("Invalid input: Please input a number");
			System.out.println("");
			Scanner var5 = new Scanner(System.in);
			System.out.print("Enter gap penalty: ");
			gapPenalty = var5.nextDouble();
		}
	
		//Creating an object of type Algorithm and Outputs to create matrix, initialized matrix, etc.
		Algorithm A1 = new Algorithm(DNA1, DNA2, matchScore, mismatchScore, gapPenalty);
		Outputs O1 = new Outputs();
		String[] singleAlignment = new String[2];
		
		A1.createMatrix();
		A1.fillMatrix();
		singleAlignment = A1.singleTraceback();
		A1.multTraceback();
		
		System.out.println();
		boolean repeat1 = true;
		
		//Asking user output options 
		while(repeat1) {
			System.out.println("The output options are:");
			System.out.println("1. Re-enter parameters");
			System.out.println("2. Print initialized matrix");
			System.out.println("3. Print completed matrix");
			System.out.println("4. Print optimal alignment");
			System.out.println("5. Print all optimal alignments");
			System.out.println("6. Exit program");
			Scanner var6 = new Scanner(System.in);
			System.out.print("Enter a number to the corresponding desired output: ");
			int desiredOutput = var6.nextInt();
			System.out.println();
			
			//6 if statements depending on which number the user entered 
			if (desiredOutput == 1){
				//The reason why the main method was implemented is for this case, where if the user wanted to repeat the entire 
				//process, they can and the program would start from the beginning 
				return true;
			}
			else if (desiredOutput == 2){
				//Used to output the initialize matrix 
				O1.initializedMatrix(A1);
				repeat1 = true;
			}
			else if (desiredOutput == 3){
				//Used to output the completed matrix 
				O1.outputFullMatrix(A1);
				repeat1 = true;
			}
			else if (desiredOutput == 4){
				//Used to output the optimal alignment 
				System.out.println(singleAlignment[0]);
				System.out.println(singleAlignment[1]);
				repeat1 = true;
			}
			else if (desiredOutput == 5){
				//Used to output all the optimal alignments
				for(int i=0;i<A1.multAlignments.size();i++){
						System.out.println("DNA1: " + A1.multAlignments.get(i)[0]);
						System.out.println("DNA2: " + A1.multAlignments.get(i)[1]);
						System.out.println();
						
				}//end for
				System.out.println("Returning to the output options");
				repeat1 = true;
			}
			//Used to terminate the program 
			else if (desiredOutput == 6){
				System.out.println("The program has terminated");
				System.exit(0);
			}
			System.out.println();
		}
		return false;
	}

}
