package advprog.lab2.unitTest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import advprog.lab2.interpretor;

public class interpretorTest {

	
	interpretor obj;
	@Before
	public void setUp() throws Exception {
			obj= new interpretor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFloat() {
		try {
			ArrayList<String> fileRead = readFromFile("/home/aunn/Desktop/test1");
			System.out.println("File :"+fileRead.toString()+"\n");
			obj.run(fileRead);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testInteger() {
		try {
			ArrayList<String> fileRead = readFromFile("/home/aunn/Desktop/test2");
			System.out.println("File :"+fileRead.toString()+"\n");
			obj.run(fileRead);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
		@Test
	public void testForString() {
		try {
			ArrayList<String> fileRead = readFromFile("/home/aunn/Desktop/test3");
			System.out.println("File :"+fileRead.toString()+"\n");
			obj.run(fileRead);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	private ArrayList<String> readFromFile(String filename){

		ArrayList<String> matList = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String lineRead;
			while ((lineRead = reader.readLine()) != null) {
				if (lineRead.trim().equals("")) {	//empty line
					continue;
				} else {
					matList.add(lineRead);
				}
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return matList;

	}



}
