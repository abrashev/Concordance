package uk.ac.aber.dcs.sta9.concordance.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.aber.dcs.sta9.concordance.GameFrame;

public class SearchTest {

	@Test
	public void testSourceFileforIndexWords(){
		GameFrame gui=new GameFrame();
		gui.setVisible(true);
		gui.getGp().addfromIndex("testindex.txt");
		gui.searchInText("testsource.txt");
		//you have to see the hashtable number(5) word or (12) word and the lines for these words
		//now I will add new word to yourindex and we will see what will happen
	
		assertEquals("Not equals", true,gui.getGp().getLinkl().get(5).checkLine(4));//exists in lines
		assertEquals("Not equals", true,gui.getGp().getLinkl().get(5).checkLine(11));//exists in lines
		assertEquals("Not equals", false,gui.getGp().getLinkl().get(12).checkLine(4));//not exists in line
		assertEquals("Not equals", true,gui.getGp().getLinkl().get(12).checkLine(12));//exists in lines
		gui.clearAll();
		//the words will be "old" and "child" to yourindex.txt and will search source for the two words
		String word1="old";
		String word2="child";
		gui.addtoYourFile(word1);
		gui.addtoYourFile(word2);
		gui.getGp().addfromIndex("yourindex.txt");
		gui.searchInText("testsource.txt");
		gui.getGp().printLines();
		//from the first test we have 5 words remember when i finish all tests i
		//will delete all in yourindex.txt so the user can add to it
		  assertEquals("Not equals", true,gui.getGp().getLinkl().get(5).checkLine(1));//exists in line 1
		  assertEquals("Not equals", true,gui.getGp().getLinkl().get(5).checkLine(16));//exists in line 16
		 assertEquals("Not equals", true,gui.getGp().getLinkl().get(6).checkLine(11));//exists in line 11
		 assertEquals("Not equals", true,gui.getGp().getLinkl().get(6).checkLine(18));//exists in line 18
	}
	
	@Test
	public void alphabeticalTest(){
		GameFrame gui=new GameFrame();
		//delete contents in yourindex.txt and run first ReadTest
		//and then SearchTest
		//your index is now with 8 words
		gui.getGp().addfromIndex("yourindex.txt");
		gui.getGp().sortWords();
		gui.searchInText("testsource.txt");
		gui.getGp().printLines();
		assertEquals("Wrong name", "night",gui.getGp().getMywords().get(0));//number of night in hashtable is 0
		assertEquals("Wrong name", "down",gui.getGp().getMywords().get(2));//number of down in hashtable is 2
		assertEquals("Wrong name", "night",gui.getGp().getMyList().get(3));//number of night in list is 3
		assertEquals("Wrong name", "down",gui.getGp().getMyList().get(1));//number of down in list is 1
	
	}
}
