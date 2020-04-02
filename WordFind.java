/* 
 * Isabella Schepisi
 * PA02 - WordFind
 * CSCI 271 001
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;

public class WordFind
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
		String tempWord = myWord.replaceAll(" ","");
		boolean isFound=false;
		for (int i=0;i<rows;i++)
	        {
	                for (int j=0;j<columns;j++)
			{
				boolean isNorth = north(myArray,tempWord,i,j);
	         		boolean isSouth = south(myArray,tempWord,i,j);
			        boolean isEast = east(myArray,tempWord,i,j);
			        boolean isWest = west(myArray,tempWord,i,j);
			        boolean isNortheast = northeast(myArray,tempWord,i,j);
			        boolean isNorthwest = northwest(myArray,tempWord,i,j);
			        boolean isSoutheast = southeast(myArray,tempWord,i,j);
			        boolean isSouthwest = southwest(myArray,tempWord,i,j);
			        isFound = (isNorth||isSouth||isEast||isWest||isNortheast||isNorthwest||isSoutheast||isSouthwest);
				if (isFound)
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
					return;					 
				}
			}
		}
		if (!isFound)
			System.out.println(myWord + " was not found.");
	}

	public static void main(String[] args) throws IOException
	{
		/* checks that an input file was specified
		 * if not, the program quits */
		if (args.length==0)
		{
			System.out.println("ERROR: no input file specified.");
			System.exit(1);
		}
		File f=new File(/*"cashiers.txt"*/args[0]);
		/* checks that the input file exists
		 * if not, the program quits */
		if(!(f.exists()))
		{
			System.out.println("ERROR: could not open input file.");
			System.exit(2);
		}
		/* read file
		 * record number of rows and columns */
		int rows=0,columns=0;
		Scanner s1 = new Scanner(f);
		while (s1.hasNextLine())
		{
			String temp1 = new String();
			temp1=s1.nextLine();
			columns=temp1.length();
			rows++;
		}
		s1.close();
		rows=(rows-1)/2;
		columns=(columns-1)/2;
		/* read file again
		 * record data into 2d array */
		char[][] myArray = new char[rows][columns];
		int r=0;
		Scanner s2 = new Scanner(f);
		while (s2.hasNextLine())
		{
			String temp2 = new String();
			temp2=s2.nextLine().replace("-","").replace("|","");
			if (!(temp2.length()==0))
			{
				for (int c=0;c<columns;c++)
				{
					myArray[r][c]=temp2.charAt(c);
				}
				r++;
			}	
		}
		s2.close();
		/* if there is not a second argument,
		 * prompt user for word to find */
		String myWord = new String();
		if (args.length==1)
		{
			Scanner myObj = new Scanner(System.in);
			System.out.print("Enter word: ");
			myWord=myObj.nextLine().toUpperCase();
			do{
				findWord(myArray,myWord,rows,columns);
				System.out.print("Enter word: ");
				myWord=myObj.nextLine().replaceAll(" ","").toUpperCase();
			}while(myWord.length()!=0);
			myObj.close();
		}
		/* if there is a second argument,
		 * read in words from file */
		if (args.length==2)
		{
			File myFile = new File(/*cashwords.txt*/args[1]);
			Scanner myReader = new Scanner(myFile);
			while (myReader.hasNextLine())
			{
				String word = myReader.nextLine().replaceAll(" ","").toUpperCase();
				findWord(myArray,word,rows,columns);

			}
			myReader.close();
		}

	}
}
