package notDefault;

public class DoubleString implements Comparable<DoubleString>{
	//contains a number and a string, in this case a stat and a Pokemon name
    double number;
    String string;
	
	public DoubleString(double inNumber, String inString) {
		number = inNumber;
		string = inString;
	}
	
	public double getNumber() {
		return(number);
	}
	
	@Override
	public String toString() {
		return(string);
	}
	
	/*public String toAlignedString() {
    	String out = "";
    	
    	while(string.length() + out.length() + ((Double)number).toString().substring(0, ((Double)number).toString().indexOf(".") + 1).length() < 32) {
    		out = out + " ";
    	}
    	
    	return(string + out + number);
    }*/
	
	public void plusEquals(double addition) {
		number += addition;
	}
	
	public void divideEquals(double division) {
		number /= division;
	}
    
    public int compareTo(DoubleString other) {
    	//tells which DoubleString's Pokemon name comes later in the alphabet
    	return(string.compareTo(other.string));
    }
    
    public double compareNumber(DoubleString other) {
    	//tells which DoubleString's number is bigger
    	return(number - other.number);
    }
    
    public boolean equalNumber(DoubleString other) {
    	//whether the DoubleStrings contain the same number
    	return(number == other.number);
    }
    
    public boolean congruentString(DoubleString other) {
    	//whether the DoubleStrings contain the same name
    	return(other.string.equals(string));
    }
	
	/*public static DoubleString getDoubleString(DoubleString[] array, String string) {
		for(int i = 0; i < array.length; i ++) {
			if(array[i].toString().equals(string)) {
				return(array[i]);
			}
		}
		
		return(new DoubleString(0, "I'm an accident!"));
	}*/
	
    //sorts DoubleStrings by their numbers using the heap sort algorithm, which is easier to Google than asking me to explain how this works
	public static void sort(DoubleString[] array, boolean sortNumber){
		//for(int i = array.length - 1; i >= 9; i --){
    	for(int i = array.length - 1; i >= 0; i --){
    		trickle(array, i, 0, sortNumber);
    	}
    	
    	for(int i = 1; i < array.length; i ++){
    		swap(array, 0, array.length - i);
    		trickle(array, 0, i, sortNumber);
    	}
    }
    
    public static void trickle(DoubleString[] array, int target, int sorted, boolean sortNumber){
    	while(2 * target + 1 < array.length - sorted){
    		if(sortNumber && (array[target].compareNumber(array[2 * target + 1]) <= 0) && (array.length - sorted <= 2 * target + 2)){
    			return;
    		}
    			
    		if(!sortNumber && (array[target].compareTo(array[2 * target + 1]) >= 0) && (array.length - sorted <= 2 * target + 2)){
    			return;
    		}
    		
    		if(sortNumber && (array[target].compareNumber(array[2 * target + 1]) <= 0) 
    				&& (array[target].compareNumber(array[2 * target + 2]) <= 0)){
    			return;
    		}
    		
    		if(!sortNumber && (array[target].compareTo(array[2 * target + 1]) >= 0) 
    				&& (array[target].compareTo(array[2 * target + 2]) >= 0)){
    			return;
    		}
    		
    		if(2 * target + 2 >= array.length - sorted){
    			swap(array, target, 2 * target + 1);
    			target = 2 * target + 1;
    			continue;
    		}
    		
    		if(sortNumber) {
    			if(array[2 * target + 1].compareNumber(array[2 * target + 2]) <= 0){
        			swap(array, target, 2 * target + 1);
        			target = 2 * target + 1;
        		}else{
        			swap(array, target, 2 * target + 2);
        			target = 2 * target + 2;
        		}
    		}else {
    			if(array[2 * target + 1].compareTo(array[2 * target + 2]) >= 0){
        			swap(array, target, 2 * target + 1);
        			target = 2 * target + 1;
        		}else{
        			swap(array, target, 2 * target + 2);
        			target = 2 * target + 2;
        		}
    		}
    	}
    }
    
    public static void swap(DoubleString[] array, int i, int j) {
        DoubleString temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}