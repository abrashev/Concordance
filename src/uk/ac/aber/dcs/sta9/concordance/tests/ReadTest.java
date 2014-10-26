package uk.ac.aber.dcs.sta9.concordance.tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import uk.ac.aber.dcs.sta9.concordance.GameFrame;

/**
* @author Stoyko Abrashev - <b>sta9</b>
* @version 1
*/

public class ReadTest {
	
	@Test
	public void testAddingIntoHashtable(){
		GameFrame gui=new GameFrame();
		String test1="night";
		String test2="grown";
		String test3="down";
		String test4="sly";
		String test5="seek";
		gui.addtoYourFile(test1);
		gui.addtoYourFile(test2);
		gui.addtoYourFile(test3);
		gui.addtoYourFile(test4);
		gui.addtoYourFile(test5);
		gui.getGp().addfromIndex("yourindex.txt");
		gui.getGp().printLines();
		
		assertEquals("Wrong name", "sly",gui.getGp().getMywords().get(3));
		//in the first test we added go so now we will test for it
		assertEquals("Wrong name", "night",gui.getGp().getMywords().get(0));
	}
}
