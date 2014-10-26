package uk.ac.aber.dcs.sta9.concordance;

import java.util.LinkedList;

/**
* @author Stoyko Abrashev - <b>sta9</b>
* @version 1
*/

public class Word {

	private int next;
	private LinkedList <Integer> lines;
	
	/**
	 * Class constructor without arguments
	 */
	public Word(){
		lines=new LinkedList<Integer>();
		next=0;
		
	}

	/**
	 * Class constructor with 
	 * 
	 * @param line adds a line into lines linked list
	 */
	public Word(int line){
		lines=new LinkedList<Integer>();
		next=0;
		this.addLine(line);
	}
	
	/**
     * This method checks if in the lines linked list line exists
     * 
     * @param line this is the line in the source file that we will check
     * 
     * @return <code>true</code> if the line exists in the linked list, <code>false</code> otherwise
     */
	public boolean checkLine(int line){
		boolean check=false;
		if(lines.size()!=0){
			for(int f: lines){
				if(f==line){
					check=true;
					break;
				}
			}
		}
		return check;
	}
	
	/**
     * This method adds line into lines linked list and increment the next free element in there
     * 
     * @param line is the line which will be added in the specified position
     * 
     */
	public void addLine(int line){
		lines.add(next, line);
		next++;
	}

	/**
     * This method searches for index words in a source file
     * 
     * @return a string of every element in lines linked list.If lines is empty return some text info
     * 
     */
	public String toString(){
		String tempString="";
		if(!lines.isEmpty()){
		 for(int s=0;s<lines.size();s++){
			 if(lines.get(s)!=0){
		 	tempString=tempString+String.valueOf(lines.get(s));
			 }
		 	 if(lines.get(s)!=lines.getLast()){
		 	tempString=tempString+",";	 	
		 	 }
		 }return tempString;
		}else{
			return "";//Doesn't exist in the source file or you have not searched for index word in the source text yet";
		}
		
	}
	
}

