package uk.ac.aber.dcs.sta9.concordance;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
* @author Stoyko Abrashev - <b>sta9</b>
* @version 1
*/

public class HelpFrame extends JFrame{

	/**
	 * Class constructor without arguments
	 */
	public HelpFrame(){
		this.setPreferredSize(new Dimension(300,300));
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			
		}
		this.setLocation(200, 100);
		this.setPreferredSize(new Dimension(700,270));
		this.setTitle("Help Contents");
		this.setResizable(true);
		Font fon=new Font(Font.SERIF,Font.PLAIN,18);
		
		JEditorPane ja=new JEditorPane();
		ja.setFont(fon);
		ja.setText("NewDefaultIndex - it updates words in index.txt with new 200 words\nAdd words to Your index - " +
				"this displays a popup window in which you can add your word\n" +
				"Add to Hashtable - adds index file to hashtable\n" +
				"Clear All Data - clears all data in the structures\n" +
				"Default or Your Index - choose which index will be used\n" +
				"Choose between four sources\nbrowseSource - you can open a file you want\n" +
				"Search Source - search for index words in source file\n" +
				"");
		ja.setBackground(null);
		this.add(ja,BorderLayout.PAGE_START);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		pack();
	}
	
	/**
     * This method sets visible of the help frame to true
     */
	public void showIt() {
		this.setVisible(true);
	}

	/**
     * This method sets visible of the help frame to false
     */
	public void hideIt() {
		this.setVisible(false);
	}
}
