/* 
 * Isabella Schepisi
 * PA02 - WordFind
 * CSCI 271 001
 */
import java.io.File;
import java.io.IOException;
import java.util.*;

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
	public static boolean south(char[][] arr, String target, int r, int c, int rows)
	{
		if (rows-r<target.length()-1)
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r+i][c]!=target.charAt(i))
				return false;
		return true;
	}
	public static boolean east(char[][] arr, String target, int r, int c, int columns)
	{
		if (columns-c<target.length()-1)
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
	public static boolean northeast(char[][] arr, String target, int r, int c, int columns)
	{
		if ((r<target.length()-1)||(columns-c<target.length()-1))
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
	public static boolean southeast(char[][] arr, String target, int r, int c, int rows, int columns)
	{
		if ((rows-r<target.length())||(columns-c<target.length()-1))
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r+i][c+i]!=target.charAt(i))
					return false;
		return true;
	}
	public static boolean southwest(char[][] arr, String target, int r, int c, int rows)
	{
		if ((rows-r<target.length()-1)||(c<target.length()-1))
			return false;
		for (int i=0;i<target.length();i++)
			if(arr[r+i][c-i]!=target.charAt(i))
				return false;
		return true;
	}
	public static void findWord(char[][] word_grid, String word, int rows, int columns)
	{
		String temp_word = word.replaceAll(" ","").toUpperCase();
		boolean isFound=false;
		for (int i=0;i<rows;i++)
	        {
	            for (int j=0;j<columns;j++)
				{
					boolean isNorth = north(word_grid,temp_word,i,j);
	         		boolean isSouth = south(word_grid,temp_word,i,j,rows);
			    	boolean isEast = east(word_grid,temp_word,i,j,columns);
			        boolean isWest = west(word_grid,temp_word,i,j);
			        boolean isNortheast = northeast(word_grid,temp_word,i,j,columns);
			        boolean isNorthwest = northwest(word_grid,temp_word,i,j);
			        boolean isSoutheast = southeast(word_grid,temp_word,i,j,rows,columns);
			        boolean isSouthwest = southwest(word_grid,temp_word,i,j,rows);
			        isFound = (isNorth||isSouth||isEast||isWest||isNortheast||isNorthwest||isSoutheast||isSouthwest);
				if (isFound)
			    	{
					if (isNorth)
				        System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented North");      
			        if (isSouth)
			            System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented South");      
			    	if (isEast)
			            System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented East");       
					if (isWest)
                        System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented West");       
					if (isNortheast)
						System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Northeast");
					if (isNorthwest)						 
				        System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Northwest");  
					if (isSoutheast)					
			            System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Southeast");  
					if (isSouthwest)						 
                        System.out.println(word + " was found starting at " + (i+1) + "," + (j+1) + " and oriented Southwest");  
					return;					 
				}
			}
		}
		if (!isFound)
			System.out.println(word + " was not found.");
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
		File grid_file=new File(args[0]);
		/* checks that the word grid file exists
		 * if not, the program quits */
		if(!(grid_file.exists()))
		{
			System.out.println("ERROR: could not open word grid file.");
			System.exit(2);
		}
		/* read file
		 * record number of rows and columns */
		int rows=0,columns=0;
		Scanner scan_dimension = new Scanner(grid_file);
		String grid_line = new String();
		while (scan_dimension.hasNextLine())
		{
			
			grid_line=scan_dimension.nextLine().replaceAll("-", "").replace("|","");
			if (!(grid_line.length()==0)){
				columns=grid_line.length();
			}
			rows++;
		}
		scan_dimension.close();
		rows=(rows-1)/2;
		/* read file again
		 * record data into 2d array */
		char[][] word_grid = new char[rows][columns];
		int r=0;
		Scanner scan_grid = new Scanner(grid_file);
		while (scan_grid.hasNextLine())
		{
			grid_line=scan_grid.nextLine().replace("-","").replace("|","");
			if (!(grid_line.length()==0))
			{
				for (int c=0;c<columns;c++)
				{
					word_grid[r][c]=grid_line.charAt(c);
				}
				r++;
			}	
		}
		scan_grid.close();
		/* if there is not a second argument,
		 * prompt user for word to find */
		String word = new String();
		if (args.length==1)
		{
			Scanner scan_input = new Scanner(System.in);
			System.out.print("Enter word: ");
			word=scan_input.nextLine();
			do{
				findWord(word_grid,word,rows,columns);
				System.out.print("Enter word: ");
				word=scan_input.nextLine();
			}while(word.length()!=0);
			scan_input.close();
		}
		/* if there is a second argument,
		 * read in words from file */
		if (args.length==2)
		{
			File word_file = new File(/*word file*/args[1]);
			Scanner scan_word = new Scanner(word_file);
			while (scan_word.hasNextLine())
			{
				word = scan_word.nextLine();
				findWord(word_grid,word,rows,columns);
			}
			scan_word.close();
		}

	}
}
