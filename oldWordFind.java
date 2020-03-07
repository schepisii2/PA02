import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

public class oldWordFind
{
	public static void printArray(char[][] arr, int r, int c)
	{
		for (int i=0;i<r;i++)
		{
			for (int j=0;j<c;j++)
				System.out.print(arr[i][j]);
			System.out.println(" ");
		}
	}
	public static boolean north(char[][] arr, String target, int r, int c)
	{
		if (r<target.length()-1)
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r-i][c]!=target.charAt(i))
				return false;
		return true;
	}
	public static boolean south(char[][] arr, String target, int r, int c)
	{
		if (15-r<target.length()-1)
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r+i][c]!=target.charAt(i))
				return false;
		return true;
	}
	public static boolean east(char[][] arr, String target, int r, int c)
	{
		if (15-c<target.length()-1)
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r][c+i]!=target.charAt(i))
				return false;
		return true;
	}
	public static boolean west(char[][] arr, String target, int r, int c)
	{
		if (c<target.length()-1)
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r][c-i]!=target.charAt(i))
				return false;
		return true;
	}
	public static boolean northeast(char[][] arr, String target, int r, int c)
	{
		if ((r<target.length()-1)||(15-c<target.length()-1))
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r-i][c+i]!=target.charAt(i))
				return false;
		return true;
	}
	public static boolean northwest(char[][] arr, String target, int r, int c)
	{
		if ((r<target.length()-1)||(c<target.length()-1))
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r-i][c-i]!=target.charAt(i))
				return false;
		return true;
	}
	public static boolean southeast(char[][] arr, String target, int r, int c)
	{
		if ((15-r<target.length())||(15-c<target.length()-1))
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r+i][c+i]!=target.charAt(i))
					return false;
		return true;
	}
	public static boolean southwest(char[][] arr, String target, int r, int c)
	{
		if ((15-r<target.length()-1)||(c<target.length()-1))
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r+i][c-i]!=target.charAt(i))
				return false;
		return true;
	}
	public static void findWord(char[][] myArray, String myWord, int rows, int columns)
	{
		boolean isFound;
		for (int i=0;i<rows;i++)
	        {
	                for (int j=0;j<columns;j++)
			{
				boolean isNorth = north(myArray,myWord,i,j);
	         		boolean isSouth = south(myArray,myWord,i,j);
			        boolean isEast = east(myArray,myWord,i,j);
			        boolean isWest = west(myArray,myWord,i,j);
			        boolean isNortheast = northeast(myArray,myWord,i,j);
			        boolean isNorthwest = northwest(myArray,myWord,i,j);
			        boolean isSoutheast = southeast(myArray,myWord,i,j);
			        boolean isSouthwest = southwest(myArray,myWord,i,j);
				/*
				 * Problem: This isFound variable is not updated in the next line of code.
				 * This problem is resolved in WordFind.java
				 */
				if (isFound = (isNorth||isSouth||isEast||isWest||isNortheast||isNorthwest||isSoutheast||isSouthwest))
				{
					if (isNorth)
				        	System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented North");      
			                if (isSouth)
			                        System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented South");      
			                if (isEast)
			                        System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented East");       
					if (isWest)
                                                System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented West");       
					if (isNortheast)
						System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Northeast");
					if (isNorthwest)						 
				                System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Northwest");  
					if (isSoutheast)					
			                        System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Southeast");  
					if (isSouthwest)						 
                                                System.out.println(myWord + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Southwest");  
					break;					 
				}
			}
		}
		if (!isFound)
			System.out.println(myWord + " was not found.");
	}

	public static void main(String[] args) throws IOException
	{
		//declare variables
		File f=new File(/*"cashiers.txt"*/args[0]);
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		int c=0;
		/* 
		 * The variables rows and columns are predetermined to be 15,
		 * but they should be dependent on the number of rows/columns in arg[0]
		 */
		int rowCount=0,columnCount=0,rows=15,columns=15;
		char[][] myArray = new char[rows][columns];
		String myWord = new String();
		//read arg[0] file
		while((c=br.read()) != -1)
		{
			char character = (char) c;
			//put char in 2d array
			if ((character!='-')&&(character!='|')&&(character!='\n'))
			{
				myArray[rowCount][columnCount]=character;
				columnCount++;

				if (columnCount==columns)
				{
					columnCount=0;
					rowCount++;
				}
			}	
		}
		//print array
		printArray(myArray,rows,columns);
		/* if there is not a second argument,
		 * prompt user for word to find */
		if (args.length==1)
		{
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter word:");
			myWord=myObj.nextLine();
			do{
				findWord(myArray,myWord,rows,columns);
				System.out.println("Enter word:");
				myWord=myObj.nextLine();
			}while(myWord.length()!=0);
		}
		/* if there is a second argument,
		 * read in words from file */
		if (args.length==2)
		{
			File myFile = new File(/*cashwords.txt*/args[1]);
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine())
			{
				String word = myReader.nextLine();
				findWord(myArray,word,rows,columns);

			}
			myReader.close();
		}

	}
}
