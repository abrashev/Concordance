package uk.ac.aber.dcs.sta9.concordance;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
* @author Stoyko Abrashev - <b>sta9</b>
* @version 1
*/

public class GamePanel extends JPanel implements ListSelectionListener{

	private Hashtable mywords;
	private LinkedList <Word> linkl;
	private GameFrame gf;	
	private JList list;
    private DefaultListModel listModel;
    private JScrollPane listScrollPane;
    
    /**
	 * Class constructor which points to GameFrame class
	 * 
	 * @param gamefr it points to GameFrame class
	 * 
	 */
	public GamePanel(GameFrame gamefr){
	
		mywords = new Hashtable();
		linkl = new LinkedList<Word>();
		gf=gamefr;
		BorderLayout br = new BorderLayout( );		
		this.setLayout(br);
		this.setPreferredSize(new Dimension(150,400));
		listModel = new DefaultListModel();
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setCursor(new Cursor(Cursor.HAND_CURSOR));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(20);
        listScrollPane = new JScrollPane(list);
        Font fona=new Font(Font.DIALOG,Font.PLAIN,16);		
        list.setFont(fona);
	    listScrollPane.setPreferredSize(new Dimension(150,400));
	    
	    this.add(listScrollPane);
	    
	    this.setVisible(true);
	}
	
	/**
	 * This method clears the hashtable, the list and the linked list so we can search in another file
	 * 
	 */
	public void clearAllThings(){
		mywords.clear();
		linkl.clear();
		listModel.clear();
		gf.setHash(false);
		gf.setSearch(true);
		gf.setAdd(true);
		gf.setAddIndex(true);
		gf.setmakeIndex(true);
		gf.setSourceTxt("");
		if(mywords.isEmpty() && linkl.isEmpty() && listModel.isEmpty()){
		JOptionPane.showMessageDialog(null, "All the data in lists and hashtables and lines linked list is deleted :(","Successfully deleted",1);
		}
	}
	
	/**
	 * This method reads from dictionary file and prints out 200 random words from 50000 into index text file 
	 * 
	 */
	public void makeIndex(){
		
		try{
			Random r = new Random();
			PrintWriter outfile= new PrintWriter(new BufferedWriter(new FileWriter("index.txt")));
			BufferedReader dict= new BufferedReader(new FileReader("dict.txt"));
			String[]ar; 
			ar = new String[50000];
        	for(int i=0;i<50000;i++){
        		 String line=dict.readLine();
        		 ar[i]=line;
        		 
        	}
        	
        	int sz = ar.length;
        	int rand=0;
			for(int i=0;i<1000;i++){
				 rand = r.nextInt(sz); 
				
				outfile.println(ar[rand]);
			}
        	outfile.close();
        	dict.close();
        	JOptionPane.showMessageDialog(null, "The Index file is updated :)","Successfully updated",1);
        	//System.out.println("The Index file is updated successfully :)");
       
		}       
        catch (IOException e){
        	JOptionPane.showMessageDialog(null,"No such file","IOException",0);
        	//System.out.println("No such file");
        }
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null,"Something went wrong on read","Exception",0);
        	//System.out.println("Something went wrong on read");
        }
    
	}
	
	/**
	 * This method returns a String that will be put in text field in GameFrame class
	 * 
	 * @param id this is the id of the selected word in the list
	 * @return the string that will be used by GameFrame class
	 * 
	 */
	public String selectLines(int id){
		String ret="";
		for(int wel=0;wel<mywords.size();wel++){
			 
			if(listModel.get(id).equals(mywords.get(wel))){
				ret="List:"+id+", Hashtable:"+wel+" - ("+listModel.get(id)+") - "+linkl.get(wel).toString();
				break;
			}
		 }
		return ret;
	}

	/**
	 * This method sorts all index words(the hashtable values)
	 */	
	 public void sortWords() {
		 if(!mywords.isEmpty()){
			 
		 Vector v = new Vector(mywords.values());
		    Collections.sort(v);
		    Iterator it = v.iterator();
		    int el=0;
		    while (it.hasNext()) {
		       String element =  (String)it.next();
		       listModel.set(el, element);
		       el++;
		    }	
		 }else{
			 JOptionPane.showMessageDialog(null, "Hashtable is Empty :(","Error",0);
		 }
    }
	 
	 	/**
		 * This method searches a source text for words from a hashtable
		 * 
		 * @param file the File which we want to search for
		 * 
		 */
		public void searchSource(File file){
			try{
				if(!mywords.isEmpty()){
				//PrintWriter index= new PrintWriter(new BufferedWriter(new FileWriter("index.txt")));
				 //BufferedReader readIn= new BufferedReader(new FileReader("lines.txt"));
					
				 Scanner readIn =new Scanner(new InputStreamReader 
			            (new FileInputStream(file)));
				 
				String[] str = new String[50];			
				int line=0;
				  while(readIn.hasNextLine()){		
					 String w=readIn.nextLine();
					 line++;
					 if(w.equals("")){
						 continue;
					 }
					String regex="\\W+";
					  str=w.split(regex);
					 for(int k=0;k<str.length;k++){
						  
						if(str[k].equals(" ")){
								System.out.println("empty");
								 continue;
						}
						 if(mywords.containsValue(str[k].toLowerCase())) {
							 
							 for(int pp=0;pp<mywords.size();pp++){
								  
								 if(mywords.get(pp).equals(str[k].toLowerCase())){
									 if(linkl.get(pp).checkLine(line)==false){
										 linkl.get(pp).addLine(line);
										 break;
									 }
								 }
							 }
							 
						 }
						  
					  }
					  		
				  	}
				  JOptionPane.showMessageDialog(null, "Searching in file "+file.getName()+" is completed :)","Successfully completed",1);
				  	//System.out.println("Search is completed successfully :)");
				  	gf.showSource(file);
				  	this.printLines();
				  	//this.printWords();
				  	gf.setSearch(false);
				  	gf.setHash(true);
				  	readIn.close();
					}else{
						JOptionPane.showMessageDialog(null, "Hashtable is empty :(\nPlease click on \"Add to Hashtable\" menu","Error",0);
					}
			}
				 	catch (IOException e){
				 		JOptionPane.showMessageDialog(null, "No such file");
				    	//System.out.println("No such file");
				    }
				    catch (Exception e) {
				    	JOptionPane.showMessageDialog(null, "Something went wrong on read");
				    	//System.out.println("Something went wrong on read");
				    }
		}
		
	/**
	 * This method prints all index words
	 */
	public void printWords(){
			
		for(int i=0;i<mywords.size();i++){
			if(!linkl.get(i).toString().equals("")){
			System.out.println(i+": "+mywords.get(i)+"-"+linkl.get(i).toString());
			}
		}
	}
		
	/**
	 * This method prints the positin of every word in the list and the hashtable and also all lines for each index word
	 */
	public void printLines(){
		
		 for(int lel=0;lel<listModel.size();lel++){
			for(int wel=0;wel<mywords.size();wel++){
				 
				if(listModel.get(lel).equals(mywords.get(wel))){
					if(!linkl.get(wel).toString().equals("")){
						System.out.println("List:"+lel+", Hash:"+wel+" :"+listModel.get(lel)+" - "+linkl.get(wel).toString());
					}
					break;
				}
			 }
		 }
	}
	
	/**
	 * This method adds all words from the index file into a hashtable
	 * 
	 * @param file this is a String which is the name of the index file
	 */
	public void addfromIndex(String file){
		
		try{
			
			Scanner readFrom =new Scanner(new InputStreamReader 
		            (new FileInputStream(file)));
			if(file.equals("yourindex.txt")){
			if(readFrom.hasNextLine()==false){
				 JOptionPane.showMessageDialog(null, "The file is probably empty.\nPlease click on \"Add to Your Index\" menu","Error",0);
				return ;
			}
			}
			int id=0;
			while(readFrom.hasNextLine()){
			String gg=readFrom.nextLine();
			if(mywords.containsValue(gg.toLowerCase())){
				continue;
			}
			listModel.add(id,gg.toLowerCase());
			mywords.put(id, gg.toLowerCase());
			linkl.add(new Word());
			
			id++;
			}
			this.sortWords();
			gf.setAdd(false);
			gf.setAddIndex(false);
			gf.setmakeIndex(false);
			//this.printLines();
			//this.printWords();
			JOptionPane.showMessageDialog(null, "Words from "+file+" are added to a hashtable successfully :)","Successfully added",1);
			//System.out.println("Words are added to a hashtable successfylly :)");
			readFrom.close();
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null, "No such file","Error",0);
	    	//System.out.println("No such file");
	    }
	    catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Something went wrong on read","Error",0);
	    	//System.out.println("Something went wrong on read");
	    }
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
	
		gf.setSourceLines(this.selectLines(list.getSelectedIndex()));
               
	}
	
	/**
	 * This method returns mywords hashtable
	 * 
	 * @return Hashtable it is the hashtable that we store index words
	 * @see Hashtable
	 */
	public Hashtable getMywords() {
		return this.mywords;
	}
	
	/**
	 * This method returns our list
	 * 
	 * @return DefaultListModel is the list with all words in alphabetical order
	 * @see DefaultListModel
	 */
	public DefaultListModel getMyList(){
		return this.listModel;
	}

	/**
	 * This method returns our linked list with words
	 * 
	 * @return LinkedList<Word> is the list of every word
	 * @see LinkedList
	 */
	public LinkedList<Word> getLinkl() {
		return this.linkl;
	}
	
}

