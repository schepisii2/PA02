/*
 * Isabella Schepisi
 * schepisii2@winthrop.edu
 *
 * CSCI 271 001
 * PA02 - WordFind
 *
 **/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordFind
{
	public static boolean north(char[][] myArray, String target, int rowPos, int colPos)
	{
		if (target.length()>rowPos)
			return false;
		for (int i=rowPos;i<rowPos-target.length();i--)
		{
			if (myArray[i][colPos]!=target.charAt(i-rowPos))
				return false;
		}
		return true;
	}

	public static boolean east(char[][] myArray, String target, int rowPos, int colPos)
	{
		for (int i=colPos;i<colPos+target.length();i++)
		{
			if (myArray[rowPos][i]!=target.charAt(i-colPos))
				return false;
		}
		return true;
	}
	public static boolean south(char[][] myArray, String target, int rowPos, int colPos)
	{
		for (int i=rowPos;i<rowPos+target.length();i++)
		{
			if (myArray[i][colPos]!=target.charAt(i-rowPos))
				return false;
		}
		return true;
	}
	public static boolean west(char[][] myArray, String target, int rowPos, int colPos)
	{
		for (int i=colPos;i<colPos-target.length();i--)
		{
			if (myArray[i][colPos]!=target.charAt(i-rowPos))
				return false;
		}
		return true;
	}
	public static boolean northeast(char[][] myArray, String target, int rowPos, int colPos)
	{
		for (int i=0;i<target.length();i++)
		{
			if (myArray[rowPos-i][colPos+i]!=target.charAt(i))
				return false;
		}
		return true;
	}
	public static boolean northwest(char[][] myArray, String target, int rowPos, int colPos)
	{
		for (int i=0;i<target.length();i++)
		{
			if (myArray[rowPos-i][colPos-i]!=target.charAt(i))
				return false;
		}
		return true;
	}
	public static boolean southeast(char[][] myArray, String target, int rowPos, int colPos)
	{
		for (int i=0;i<target.length();i++)
		{
			if (myArray[rowPos+i][colPos+i]!=target.charAt(i))
				return false;
		}
		return true;
	}
	public static boolean southwest(char[][] myArray, String target, int rowPos, int colPos)
	{
		for (int i=0;i<target.length();i++)
		{
			if (myArray[rowPos+i][colPos-i]!=target.charAt(i))
				return false;
		}
		return true;
	}
	public static String checkDirection(char[][] myArray, String target, int rowPos, int colPos)
	{
		if (north(myArray,target,rowPos,colPos))
			return "n";
/*		else if (east(myArray,target,rowPos,colPos))
			return "e";
		else if (south(myArray,target,rowPos,colPos))
			return "s";
		else if (west(myArray,target,rowPos,colPos))
			return "w";
		else if (northeast(myArray,target,rowPos,colPos))
			return "ne";
		else if (northwest(myArray,target,rowPos,colPos))
			return "nw";
		else if (southeast(myArray,target,rowPos,colPos))
			return "se";
		else if (southwest(myArray,target,rowPos,colPos))
			return "sw"; */
		else
			return "default";

	}

	public static void main(String[] args) throws IOException{
		File f=new File("cashiers.txt");     //Creation of File Descriptor for input file
		FileReader fr=new FileReader(f);   //Creation of File Reader object
	        BufferedReader br=new BufferedReader(fr);  //Creation of BufferedReader object
		
		int b = 0;             
		
		int rowCount=0, columnCount=0, rows=15, columns=15;
		char[][] myArray = new char[rows][columns];

		while((b = br.read()) != -1)         //Read char by Char
		{
			char character = (char) b;
			if ((character!='|')&&(character!='-')&&(character!='\n'))
			{
				//System.out.println(character);
				myArray[rowCount][columnCount]=character;
				columnCount++;
				//System.out.println("column" + columnCount);
				if (columnCount==15)
				{
					columnCount=0;
					rowCount++;
					//System.out.println("row" + rowCount);
				}
			}
		}
	
		String myWord="ANTIQUE", direction="default";

		for (int r=0;r<rows;r++)
		{
			for (int c=0;c<columns;c++)
			{
				direction=checkDirection(myArray,myWord,r,c);
			}
		}

/*		String n = "ANTIQUE";
		System.out.println(north(myArray,n,14,4));
		String e = "BASS";
		System.out.println(east(myArray,e,3,1));	
		String s = "BOAT";
		System.out.println(south(myArray, s, 5, 11));
		String w = "LAKE";
		System.out.println(west(myArray,w,13,3));
		String ne = "CROQUET";
		System.out.println(northeast(myArray,ne,14,8));
		String nw = "JELLY";
		System.out.println(northwest(myArray,nw,13,14));
		String se = "LODGE";
		System.out.println(southeast(myArray,se,9,6));
		String sw = "DOWNTOWN"; //5,12
		System.out.println(southwest(myArray,sw,5,12)); */
	}

}
