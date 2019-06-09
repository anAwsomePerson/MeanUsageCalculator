package notDefault;

public class Tree {
	//It's a very standard binary search tree I coded myself. Each Tree object (aka node) contains a Pokemon name and a stat. Names earlier in the alphabet go on the left; names later go on the right.
    DoubleString datum;
    Tree left;
    Tree right;
    
    public Tree(DoubleString input) {
    	datum = input;
    }
    
    public void insert(DoubleString input) {
    	/*If the input's name is the same as this node's name, then the input stat gets added to this node's stat. 
    	Otherwise, names earlier or later in the alphabet get inserted into the left or right branch, respectively. 
    	If that branch doesn't exist, then this method creates the branch.*/
    	Tree currentTree = this;
    	int stringDifference = currentTree.datum.compareTo(input);
    	
    	while(stringDifference != 0) {
    		//System.out.println(currentTree.datum.toString() + "reached0");
    		
    		if(stringDifference < 0) {
    			//System.out.println("reached5 " + currentTree.datum.toString() + ' ' + (left == null));
    			
    			if(currentTree.left == null) {
    				//System.out.println(currentTree.datum.toString() + "reached2");
    				currentTree.left = new Tree(input);
    				return;
    			}
    			
    			//System.out.println("reached6 " + currentTree.datum.toString() + ' ' + (left == null));
    			currentTree = currentTree.left;
    		}else {
    			//System.out.println(currentTree.datum.toString() + "reached3");
    			if(currentTree.right == null) {
    				//System.out.println(currentTree.datum.toString() + "reached4");
    				currentTree.right = new Tree(input);
    				return;
    			}
    			
    			currentTree = currentTree.right;
    		}
    		
    		stringDifference = currentTree.datum.compareTo(input);
    	}
    	
    	currentTree.datum.plusEquals(input.number);
    }
    
    public static DoubleString[] toArray(Tree tree) {
    	//turns the tree into an array where each element contains a Pokemon name and stat, and the Pokemon names are in alphabetical order
    	if(tree == null) {
    		return(new DoubleString[0]);
    	}
    	
    	DoubleString[] leftArray = toArray(tree.left);
    	DoubleString[] rightArray = toArray(tree.right);
    	DoubleString[] out = new DoubleString[leftArray.length + 1 + rightArray.length];
    	
    	for(int i = 0; i < out.length; i ++) {
    		if(i < leftArray.length) {
    			out[i] = leftArray[i];
    		}
    		
    		if(i == leftArray.length) {
    			out[i] = tree.datum;
    		}
    		
    		if(i > leftArray.length) {
    			out[i] = rightArray[i - leftArray.length - 1];
    		}
    	}
    	
    	return(out);
    }
}