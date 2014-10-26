package uk.ac.aber.dcs.sta9.concordance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

/**
* @author Stoyko Abrashev - <b>sta9</b>
* @version 1
*/

public class GameFrame extends JFrame{

	
	private JMenuBar mb=new JMenuBar();	
	private JMenu fileMenu=new JMenu("File");	
	private JMenuItem makeIndex=new JMenuItem("New Default index");
	private JMenuItem addtoIndex=new JMenuItem("Add Words to Your index");
	private JMenuItem addtoHash=new JMenuItem("Add Index to Hashtable");
	private JMenuItem clearHash=new JMenuItem("Clear All Data");
	private JMenuItem exit=new JMenuItem("Exit");
	private JMenu indexMenu=new JMenu("Index");
	private JRadioButtonMenuItem defaultIndex=new JRadioButtonMenuItem("Default Index");
	private JRadioButtonMenuItem yourIndex=new JRadioButtonMenuItem("Your Index");
	private JMenu sourceMenu=new JMenu("Source");	
	private JRadioButtonMenuItem source1=new JRadioButtonMenuItem("Source One");
	private JRadioButtonMenuItem source2=new JRadioButtonMenuItem("Source Two");
	private JRadioButtonMenuItem source3=new JRadioButtonMenuItem("Source Three");
	private JRadioButtonMenuItem yourSource=new JRadioButtonMenuItem("Your Source");
	private JMenuItem browseSource=new JMenuItem("Browse for Source");
	private JMenuItem searchSource=new JMenuItem("Search Source");
	private JMenu helpMenu=new JMenu("Help");
	private JMenuItem help=new JMenuItem("Help Contents");	
	private JMenuItem about=new JMenuItem("About Concordance");	
	private GamePanel gp=new GamePanel(this);
	private MenuListener menu=new MenuListener(this);
	private File yourFile=null;
	private JTextArea sourceTxt;
	private JTextField sourceLines;
	private JScrollPane listTxt;
	
	/**
	 * Class constructor without arguments
	 * 
	 */
	public GameFrame(){
		
		
		this.setPreferredSize(new Dimension(800,500));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			
		}
		this.setLocation(200, 100);
		this.setTitle("Con*cord'ance");
		this.setJMenuBar(mb);
		this.setResizable(true);
		Font fon=new Font(Font.DIALOG,Font.ROMAN_BASELINE,16);
		mb.setFont(fon);
		Font fona=new Font(Font.SERIF,Font.PLAIN,18);		
		mb.add(fileMenu);		
		fileMenu.add(makeIndex);
		
		makeIndex.addActionListener(menu);
		fileMenu.add(addtoIndex);
		
		addtoIndex.addActionListener(menu);
		fileMenu.add(addtoHash);
		fileMenu.add(clearHash);
		fileMenu.addSeparator();
		fileMenu.add(exit);	
		
		clearHash.setEnabled(false);
		clearHash.addActionListener(menu);		
		addtoHash.addActionListener(menu); 
		exit.addActionListener(menu);
		mb.add(indexMenu);
		indexMenu.add(defaultIndex);
		indexMenu.add(yourIndex);
		defaultIndex.addActionListener(menu);
		yourIndex.addActionListener(menu);
		mb.add(sourceMenu);
		sourceMenu.add(searchSource);
		searchSource.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		searchSource.addActionListener(menu);
		sourceMenu.addSeparator();
		sourceMenu.add(browseSource);
		browseSource.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		browseSource.addActionListener(menu);
		sourceMenu.addSeparator();
		sourceMenu.add(yourSource);
		sourceMenu.add(source1);		
		sourceMenu.add(source2);
		sourceMenu.add(source3);
		source1.setToolTipText("Oliver Twist by Charles Dickens");
		source2.setToolTipText("War of the Worlds by H. G. Wells");
		source3.setToolTipText("The Prince by Nicolo Machiavelli");
		source1.addActionListener(menu);
		source2.addActionListener(menu);
		source3.addActionListener(menu);
		yourSource.addActionListener(menu);
		mb.add(helpMenu);
		helpMenu.add(help);
		helpMenu.add(about);
		help.addActionListener(menu);
		about.addActionListener(menu);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sourceTxt=new JTextArea("This is the area \nwhere the source file \nwill show up\nfor sure\n its the best thing\nright");
		sourceTxt.setSelectedTextColor(Color.black);
		sourceLines=new JTextField(30);
		sourceTxt.setFont(fona);
		listTxt = new JScrollPane(sourceTxt);
		sourceTxt.setCaretPosition(11);
		sourceTxt.setSelectionStart(0);
		sourceTxt.setSelectionEnd(2);
		this.add(sourceLines,BorderLayout.PAGE_START);		
		this.add(listTxt,BorderLayout.CENTER);
		this.add(gp,BorderLayout.LINE_START);
		 pack();
	}
	
	/**
     * This method sets which radio button will be selected and the other one deselected
     * 
     * @param one the value one will set defaultIndex menu
     * @param two the value two will set yourIndex menu
     */
	public void selectIndex(boolean one,boolean two){
		defaultIndex.setSelected(one);
		yourIndex.setSelected(two);
	}
	
	
	/**
     * This method sets makeIndex menu to enabled or disabled
     * 
     * @param set it will enable or disable makeIndex menu
     */
	public void setmakeIndex(boolean set){
		makeIndex.setEnabled(set);
	}
	
	/**
     * This method sets searchSource menu to enabled or disabled
     * 
     * @param set it will enable or disable searchSource menu
     */
	public void setSearch(boolean set){
		searchSource.setEnabled(set);
	}
	
	/**
     * This method sets clearHash menu to enabled or disabled
     *
     * @param set it will enable or disable clearHash menu
     */
	public void setHash(boolean set){
		clearHash.setEnabled(set);
	}
	
	/**
     * This method sets addtoHash menu to enabled or disabled
     *
     * @param set it will enable or disable addtoHash menu
     */
	public void setAdd(boolean set){
		addtoHash.setEnabled(set);
	}
	
	/**
     * This method sets addtoIndex menu to enabled or disabled
     *
     * @param set it will enable or disable addtoIndex menu
     */
	public void setAddIndex(boolean set){
		addtoIndex.setEnabled(set);
	}
	
	/**
     * This method calls clearAllThings() method in GamePanel
     */
	public void clearAll(){
		gp.clearAllThings();
	}
	
	/**
     * This method sets which radio button will be selected and the other ones deselected
     * 
     * @param one the value one will set source1 menu
     * @param two the value two will set source2 menu
     * @param three the value three will set source3 menu
     * @param four the value four will set yourSource menu
     * 
     */
	public void selectSource(boolean one,boolean two,boolean three,boolean four){
		source1.setSelected(one);
		source2.setSelected(two);
		source3.setSelected(three);
		yourSource.setSelected(four);
	}
	
	/**
     * This method allows the user to select source file
     * 
     */
	public void chooseFile() throws IOException{
		File file = new File("");

		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new MyFileFilter());
		chooser.setFileHidingEnabled(true);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			yourFile=file;
			JOptionPane.showMessageDialog(null, "You selected your source file :)\nNow you can click on Search Source","Successfully chosen",1);
		}
		
		
	}
	
	
	/**
     * This method prints returns the last word added
     * 
     * @param file this is the file which will be used
     * @return String the last word in the file
     * 
     */
	public String printFile(String file){		 
		String w="";
		try{
			Scanner readIn =new Scanner(new InputStreamReader 
		            (new FileInputStream(file)));
			
			while(readIn.hasNextLine()){	
				 
				w=readIn.nextLine();
			
			}
			readIn.close();
		}catch (IOException e){
	 		JOptionPane.showMessageDialog(null, "No such file");
	    	//System.out.println("No such file");
	    }
	    catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Something went wrong on read");
	    	//System.out.println("Something went wrong on read");
	    }
	    return w;
	}
	
	/**
     * This method returns GamePanel
     * 
     * @return GamePanel this is our GamePanel
     */
	public GamePanel getGp() {
		return gp;
	}

	/**
     * This method calls printWords() method in GamePanel
     */
	public void printHashtable(){		 
		gp.printWords();		
	}
	
	/**
     * This method calls sortWords(),printLines() and printWords() methods in GamePanel
     */
	public void sortHashtable(){		 
		gp.sortWords();	
		gp.printLines();
		gp.printWords();
	}
	
	/**
     * This method calls searchSource() method in GamePanel with the name of the selected source file
     */
	public void searchInText(String file){
		if(source1.isSelected()){
			file="oliver.txt";
			File thefile=new File(file);
			yourFile=thefile;	
			gp.searchSource(yourFile);
		
		 }
		else if(source2.isSelected()){
			file="worlds.txt";
			File thefile=new File(file);
			yourFile=thefile;
			gp.searchSource(yourFile);
			
			 }
		else if(source3.isSelected()){
			file="prince.txt";
			File thefile=new File(file);
				yourFile=thefile;
				gp.searchSource(yourFile);
			
			 }
		else if(yourSource.isSelected()){
			if(yourFile!=null){
				gp.searchSource(yourFile);
				
			}else{
				JOptionPane.showMessageDialog(null, "You have to browse for your file first :(","Error",1);
			}
			
		}else if(file.equals("testsource.txt")){
			File thefile=new File(file);
			yourFile=thefile;
			gp.searchSource(yourFile);
		}
		
		else{
			JOptionPane.showMessageDialog(null, "You have not selected any of the source files :(","Error",1);
		}
	}
	
	/**
     * This method calls makeIndex() method in GamePanel
     */
	public void newIndex(){
		gp.makeIndex();
	}
	
	/**
     * This method adds a word to your index file
     */
	public void addtoYourFile(String word){
		try{
			PrintWriter outfile=null;
			Scanner readYour =new Scanner(new InputStreamReader 
		            (new FileInputStream("yourindex.txt")));
			String txt="";
			int l=0;
			//readYour.nextLine();
			if(readYour.hasNextLine()==true){
				
				while(readYour.hasNextLine()){
					if(l==0){
						txt=readYour.nextLine();
						l++;
					}else{
						txt=txt+"\n"+readYour.nextLine();
					}
				}
				readYour.close();
				outfile= new PrintWriter(new BufferedWriter(new FileWriter("yourindex.txt")));
				outfile.println(txt+"\n"+word);
				outfile.close();
			}else{
				readYour.close();
				outfile= new PrintWriter(new BufferedWriter(new FileWriter("yourindex.txt")));
				outfile.println(word);
				outfile.close();
			}
		 
		
		
		}catch (IOException e){
	 		JOptionPane.showMessageDialog(null, "No such file");
	    	//System.out.println("No such file");
	    }
	    catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Something went wrong on read");
	    	//System.out.println("Something went wrong on read");
	    }
	}
	/**
     * This method checks which radio button is selected and calls addfromindex method with appropriate index file
     */
	public void addtoHashtable(){
		if(defaultIndex.isSelected()==true){
		gp.addfromIndex("index.txt");
		}
		else if(yourIndex.isSelected()==true){
			gp.addfromIndex("yourindex.txt");
		}else{
			JOptionPane.showMessageDialog(null, "You have not selected index file :)\nPlease select one of the index files in the menu","Error",1);
		}
	}
	
	
	
	/**
     * This method sets sourceTxt text field
     *
     *@param file this is the file we want to see in sourceTxt
     */
	public void showSource(File file){
		try{
			
			Scanner readIn =new Scanner(new InputStreamReader 
		            (new FileInputStream(file)));
			String w="";
			int line=1;
			while(readIn.hasNextLine()){	
				
				if(line==1){
					w=line+": "+readIn.nextLine();
					line++;
				}else{
					w=w+"\n"+line+": "+readIn.nextLine();
					line++;
				}
				 
				 
			}
			sourceTxt.setText(w);
			readIn.close();
			//System.out.println(file.getName());
		}catch (IOException e){
	 		JOptionPane.showMessageDialog(null, "No such file");
	    	System.out.println("No such file");
	    }
	    catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Something went wrong on read");
	    	System.out.println("Something went wrong on read");
	    }
	}
	
	/**
     * This method sets visible of the frame to true
     */
	public void showIt() {
		this.setVisible(true);
	}

	/**
     * This method sets visible of the frame to false
     */
	public void hideIt() {
		this.setVisible(false);
	}

	/**
     * This method gets the text of sourceLines JLabel
     * 
     * @return String is the text of the label
     */
	public String getSourceLines() {
		return sourceLines.getText();
	}

	/**
     * This method sets the text in sourceLines label
     * 
     * @param txt is the text that will be set in sourceLines
     */
	public void setSourceLines(String txt) {
		this.sourceLines.setText(txt);
	}
	
	/**
     * This method sets the text in sourceTxt text field
     * 
     * @param txt is the text that will be set the label
     */
	public void setSourceTxt(String txt) {
		this.sourceTxt.setText(txt);
	}

	
	
}

