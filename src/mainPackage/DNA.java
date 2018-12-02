package mainPackage;

public class DNA {
	//implements a DNA strand
	
	//fields
	String sequence; 
	
	//constructors
	public DNA(){
		this.sequence = "";
	}
	public DNA(String dna){
		this.sequence = dna;
		this.sequence = this.sequence.replaceAll("\\s","");
	}
	public DNA(DNA dna1){
		this.copy(dna1);
	}
	
	//setters and getters
	public void setSequence(String dna){
		this.sequence = dna;
		this.sequence = this.sequence.replaceAll("\\s","");
		this.sequence = this.sequence.toUpperCase();
	}
	public String getSequence(){
		return this.sequence;
	}
	
	
	//toString
	public String toString(){
		return "DNA Sequence = " + this.sequence;
	}
	
	//Boolean equals method
	public boolean equals(Object obj){
		if(obj==null)
			return false;
		if(obj.getClass()!=this.getClass())
			return false;
		DNA dna1 = (DNA)obj;
		return this.sequence == dna1.sequence;
		
	}
	
	//copy
	public boolean copy(DNA dna1){
		//copies all values of the input dna in this dna
		//returns true if copying happened, false if there was an error
		
		if(dna1==null)
			return false;
		this.setSequence(dna1.sequence);
		return true;
		
	}
	
	//Boolean validation method to see if entered strings are valid 
	public boolean valid(){
		if(this.sequence==null)
			return false;
		for(int i=0;i<this.sequence.length();i++){
			if(!(this.sequence.charAt(i) == 'G' 
				||this.sequence.charAt(i) == 'A'
				||this.sequence.charAt(i) == 'C'
				||this.sequence.charAt(i) == 'T'))
				return false;
		}
		return true;
	}
}
