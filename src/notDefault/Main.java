package notDefault;

import java.io.*;

public class Main {
	//makes the program look through all files from 0.txt to <fileCount - 1>.txt
	public static final int fileCount = 8;
	//prints "Combined usage for " + format + " stats)" at the top of the output file
	public static final String format = "DPP OU (1630";

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		//This creates the root where all other trees eventually get attached. "M" isn't a Pokemon name, so it should always get 0 usage and end up near the bottom of the output file.
        Tree tree = new Tree(new DoubleString(0, "M"));
        
        for(int x = 0; x < fileCount; x ++) {
        	//inserts every line from every input file into the tree
        	BufferedReader input = new BufferedReader(new FileReader(x + ".txt"));
        	
        	for(int y = 0; y < 5; y ++) {
        		input.readLine();
        	}
        	
        	String line = input.readLine();
        	
        	while(line != null) {
        		if(line.contains("----")) {
        			break;
        		}
        		
        		tree.insert(new DoubleString(Double.parseDouble(betweenPipes(line, 3).trim().substring(0, betweenPipes(line, 3).trim().length() - 1)), betweenPipes(line, 2).trim()));
        		line = input.readLine();
        	}
        	
        	input.close();
        }
        
        //converts the tree into an array and sorts the array
        DoubleString[] array = Tree.toArray(tree);
        DoubleString.sort(array, true);
        //creates (or overwrites, if it's already created) the output file
        PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
        output.println("[hide=" + format + " stats)][code]Combined usage for " + format + " stats)\n" + 
        		"+ ---- + ------------------ + ------- +\n" + 
        		"| Rank | Pokemon            | Percent |\n" + 
        		"+ ---- + ------------------ + ------- +");
        
        for(int i = 0; i < array.length; i ++) {
        	//divides every stat from the array by the number of months and prints it into the output file
        	array[i].divideEquals(fileCount);
        	output.print("| ");
        	String rank = i + "";
        	
        	while(rank.length() < 3) {
        		rank = ' ' + rank;
        	}
        	
        	output.print(rank + "  | ");
        	String name = array[i].toString();
        	
        	while(name.length() < 19) {
        		name = name + ' ';
        	}
        	
        	output.print(name + "| ");
        	String integer = (array[i].getNumber() + "").substring(0, (array[i].getNumber() + "").indexOf('.'));
        	String fraction = (array[i].getNumber() + "000").substring((array[i].getNumber() + "").indexOf('.')).substring(0, 4);
        	
        	while(integer.length() < 2) {
        		integer = ' ' + integer;
        	}
        	
        	output.println(integer + fraction + "% |");
        }
        
        //line of code I have to include to prevent weird things
        output.close();
	}
	
	public static String betweenPipes(String string, int number) {
		/*This method looks at a line of text and returns the segment between any 2 pipes. 
		If number == 0, then it returns text up to the first pipe. 
		If number == 1, then it returns text between the first and second pipes. 
		etc. */
		for(int i = 0; i < number; i ++) {
			string = string.substring(string.indexOf('|') + 1);
		}
		
		if(!string.contains("|")) {
			return(string);
		}
		
		return(string.substring(0, string.indexOf('|')));
	}
}