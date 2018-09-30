package com.ubs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ubs.beans.StartOfTheDayPostions;
import com.ubs.beans.Transactions;
import com.ubs.main.TransactionManager;

public class TransactionManagerTest {

	
	
	String tfilePath="1537277231233_Input_Transactions.txt";
	String sFilePath="Input_StartOfDay_Positions.txt";
	
	
	@Test	
	 public void testTransactionList() {
		 
		 List<Transactions> expected = Arrays.asList(new Transactions(1L, "IBM", 'B', 1000), 
				 new Transactions(2L, "APPL", 'S', 200),
				 new Transactions(3L, "AMZN", 'S', 5000),
				 new Transactions(4L, "MSFT", 'B', 50),
				 new Transactions(5L, "APPL", 'B', 100),
				 new Transactions(6L, "APPL", 'S', 20000),
				 new Transactions(7L, "AMZN", 'S', 5000),
				 new Transactions(8L, "MSFT", 'S', 300),
				 new Transactions(9L, "AMZN", 'B', 200),
				 new Transactions(10L, "APPL", 'B', 9000),
				 new Transactions(11L, "AMZN", 'S', 5000),
				 new Transactions(12L, "AMZN", 'S', 50));
		  List<String> actual = Arrays.asList("fee", "fi", "foe");
		  
		  assertEquals(expected, TransactionManager.readFromJsonFile(tfilePath), "List ");
		 
	 } 
	
	
	
	@Test	
	 public void testStartDayFileListNegative() {
		 
		 List<StartOfTheDayPostions> expected = Arrays.asList(new StartOfTheDayPostions("IBM",101L,'E',100000L), 
				 new StartOfTheDayPostions("IBM",201L,'I',(long) -100000),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L),
				 new  StartOfTheDayPostions("IBM",101L,'E',100000L));
		  assertEquals(expected, TransactionManager.readFromJsonFile(tfilePath), "List");
		 
	 } 
	  
	  
}
