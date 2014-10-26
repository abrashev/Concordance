package uk.ac.aber.dcs.sta9.concordance;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
* @author Stoyko Abrashev - <b>sta9</b>
* @version 1
*/

public class MenuListener implements ActionListener{

	
	private GameFrame gFrame;
	private HelpFrame hellp=new HelpFrame();
	
	/**
	 * Class constructor which points to GameFrame class
	 * 
	 * @param gamef it points to GameFrame class
	 * 
	 */
	public MenuListener(GameFrame gamef){
		gFrame=gamef;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command=e.getActionCommand();
		if (command.equals("Exit")){
			System.exit(0);
		}
		else if (command.equals("New Default index")){
			gFrame.newIndex();
			
		}
		else if (command.equals("Add Words to Your index")){
			String input = JOptionPane.showInputDialog(null,
			           "Please enter a word", "Add the word into your source text file",JOptionPane.DEFAULT_OPTION);
			 String wordpattern = "[a-z]+";
			
			 if(input!=null && input.matches(wordpattern)){
				 gFrame.addtoYourFile(input);
				 //System.out.println(input);   
			 }else{
				 JOptionPane.showMessageDialog(null, "The word you entered is not valid\nPlease type only one word with lowercase letters :)","Error",0);
			 }
			 
		}
		else if (command.equals("Add Index to Hashtable")){
			gFrame.addtoHashtable();
			
		}
		else if (command.equals("Clear All Data")){
			gFrame.clearAll();
			 
		}
		else if (command.equals("Default Index")){
			 gFrame.selectIndex(true, false);			
		}
		else if (command.equals("Your Index")){
			 gFrame.selectIndex(false, true);			
		}
		
		else if (command.equals("Source One")){
			 gFrame.selectSource(true, false, false, false);			
		}
		else if (command.equals("Source Two")){
			gFrame.selectSource(false, true, false, false);	
		}
		else if (command.equals("Source Three")){
			gFrame.selectSource(false, false, true, false);	
		}
		else if (command.equals("Your Source")){
			gFrame.selectSource(false, false, false, true);	
		}
		else if (command.equals("Browse for Source")){
			 
			try {
				gFrame.chooseFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if (command.equals("Search Source")){
			 String file="";
			gFrame.searchInText(file);
			
		}
		else if (command.equals("Help Contents")){
			
			hellp.showIt();
			
		}
		else if (command.equals("About Concordance")){
			
			JOptionPane.showMessageDialog(null,"Con*cord\"ance \n by \n Stoyko Abrashev \n sta9@aber.ac.uk",  "About Concordance",1);
		
		}
		
	}
}

