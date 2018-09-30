package com.ubs.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ubs.beans.ExpectedEODPositions;
import com.ubs.beans.StartOfTheDayPostions;
import com.ubs.beans.Transactions;

public class TransactionManager {

	
	
	public static void main(String[] args) {
		Map<String,Long> buyInstument= new HashMap<String,Long>();
		Map<String,Long> sellInstument= new HashMap<String,Long>();
		
		List<Transactions> transactionList = readFromJsonFile("1537277231233_Input_Transactions.txt");
		List<StartOfTheDayPostions> startPosList=readFromstartOfDayPosFile("Input_StartOfDay_Positions.txt");
		List<ExpectedEODPositions> expectedEOSPosList = new ArrayList<ExpectedEODPositions>();
		
		for (Transactions t : transactionList) {
			if(t.getTransactionType()=='B') {
				if(buyInstument.containsKey(t.getInstrument())) {
					long prevVal = buyInstument.get(t.getInstrument());
					long newVal= prevVal+ t.getTransactionQuantity();
					buyInstument.put(t.getInstrument(), newVal);
				}
				else 
					buyInstument.put(t.getInstrument(), t.getTransactionQuantity());
			}else if(t.getTransactionType()=='S') {
				if(t.getTransactionType()=='S') {
					if(sellInstument.containsKey(t.getInstrument())) {
						long prevVal = sellInstument.get(t.getInstrument());
						long newVal= prevVal+ t.getTransactionQuantity();
						sellInstument.put(t.getInstrument(), newVal);
					}
					else 
						sellInstument.put(t.getInstrument(), t.getTransactionQuantity());
			}
		}}
		
		//calcute the delta
		for (StartOfTheDayPostions sPos : startPosList) {
			ExpectedEODPositions pos =new ExpectedEODPositions();
			if(sPos.getAccountType()=='E') {
				Long buyQuantity = new Long(0);
				Long sellQuantity = new Long(0);
				Long finalQuantity = new Long(0);
				Long delta = new Long(0);
				boolean isTransactionExist=false;
				long buyIntrumentVal=0;
				if(buyInstument.containsKey(sPos.getInstrument())) {
					isTransactionExist=true;
					buyIntrumentVal =buyInstument.get(sPos.getInstrument());
					 buyQuantity= sPos.getQuantity()+ buyInstument.get(sPos.getInstrument()); //od = 100000, t = 1000 == 101000
				}
				if(sellInstument.containsKey(sPos.getInstrument())) {
					isTransactionExist=true;
					sellQuantity= sellInstument.get(sPos.getInstrument()); //0
				}
				finalQuantity=buyQuantity - sellQuantity;
				
				if(isTransactionExist)
				 delta = finalQuantity-sPos.getQuantity(); //-100000///// 101000 -
				
				pos.setAccount(sPos.getAccount());
				pos.setAccountType(sPos.getAccountType());
				pos.setInstrument(sPos.getInstrument());
				pos.setQuantity(sPos.getQuantity()+buyIntrumentVal-sellQuantity);
				pos.setDelta(delta);
				
				expectedEOSPosList.add(pos);
				
			}else if(sPos.getAccountType()=='I') {
				Long buyQuantity = new Long(0);
				Long finalQuantity = new Long(0);
				Long sellQuantity = new Long(0);
				Long delta = new Long(0);
				boolean isTransactionExist=false;
				long buyIntrumentVal=0;
				if(buyInstument.containsKey(sPos.getInstrument())) {
					isTransactionExist=true;
					buyIntrumentVal =buyInstument.get(sPos.getInstrument());
					 buyQuantity= sPos.getQuantity()- buyInstument.get(sPos.getInstrument());
				}
				if(sellInstument.containsKey(sPos.getInstrument())) {
					isTransactionExist=true;
					sellQuantity= sellInstument.get(sPos.getInstrument()); 
					
				}
				
				finalQuantity=buyQuantity + sellQuantity;
				
				if(isTransactionExist)
				 delta = finalQuantity-sPos.getQuantity();
				
				pos.setAccount(sPos.getAccount());
				pos.setAccountType(sPos.getAccountType());
				pos.setInstrument(sPos.getInstrument());
				pos.setQuantity(sPos.getQuantity()-buyIntrumentVal+sellQuantity);
				pos.setDelta(delta);
				
				expectedEOSPosList.add(pos);
			}
			
		}
		generateOutputFile(expectedEOSPosList);
	}
	
	
	private static List<StartOfTheDayPostions> readFromstartOfDayPosFile(String string) {
		
	        String line = "";
	        String cvsSplitBy = ",";
	        StartOfTheDayPostions startPos;
	        List<StartOfTheDayPostions> inputPosList= new ArrayList<StartOfTheDayPostions>();
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(string))) {
	        	br.readLine();
	            while ((line = br.readLine()) != null) {
	                String[] inputArr = line.split(cvsSplitBy);
	                
	                startPos = new StartOfTheDayPostions();
	                startPos.setAccount(Long.parseLong(inputArr[1]));
	                startPos.setAccountType(inputArr[2].charAt(0));
	                startPos.setInstrument(inputArr[0]);
	                startPos.setQuantity(Long.parseLong(inputArr[3]));
	                
	                inputPosList.add(startPos);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return inputPosList;
	}


	//readJson File
	
	public static List<Transactions> readFromJsonFile(String jsonFilePath){
		List<Transactions> transactionList= new ArrayList<Transactions>();
        try {
			Object obj = new JSONParser().parse(new FileReader(jsonFilePath));
			JSONArray list = (JSONArray) obj;
			
			Transactions tran ;
			
			for(int n = 0; n < list.size(); n++)
			{
				tran =  new Transactions();
			    JSONObject object = (JSONObject) list.get(n);
			    tran.setTransactionId(Long.parseLong(object.get("TransactionId").toString()));
			    tran.setInstrument(object.get("Instrument").toString());
			    tran.setTransactionQuantity(Long.parseLong(object.get("TransactionQuantity").toString()));
			    tran.setTransactionType(object.get("TransactionType").toString().charAt(0));
			    
			    transactionList.add(tran);
			    
			}
				
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
        return transactionList;	  

	}
	
	public static void generateOutputFile(List<ExpectedEODPositions> list) {
        try {
            //Whatever the file path is.
            File file = new File("OutPut.txt");
            FileOutputStream is = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);
              w.write("Instrument,Account,AccountType,Quantity,Delta");
              w.write("\r\n");
              for(ExpectedEODPositions p :list) {
            	  w.write( p.getInstrument()+","+p.getAccount()+","+p.getAccountType()+","+p.getQuantity()+","+p.getDelta() ); 
            	  w.write("\r\n");
              }
            w.close();
            System.out.println("Output file  OutPut.txt has been created successfully in classpath");
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
    }


	
	
}
