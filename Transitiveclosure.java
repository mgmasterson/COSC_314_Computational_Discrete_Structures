import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
/**
 *@author 	Michael Masterson
 *@EID		E00942993
 *@version	1.0
 *@since	2016-2-12
 *This program will follow and implement the requirements stated in the assignment. It will find the transitive closure
 *based on the files provided by instructor. Also, it will run large relations
 */


public class Transitiveclosure {
	private int V;
	private boolean [][] wc;
	private PrintWriter printwriter;

	/**
	 * perform Warshall algorithm
	 * will take graph's length and then insert new boolean for i and j values
	 * preform Warshall algorithm will take the graph's length and the insert
	 * new boolean for i an j values
	 */

	public void warshall(int[][] graph)
	{
		this.V = graph.length;
		wc = new boolean [V][V];
		for (int i=0;i<V;i++)
		{
			for (int j=0; j<V;j++)
				if (graph[i][j] !=0)
					wc[i][j] =true;
		}
		for (int i=0; i<V;i++){
			for (int j=0; j<V; j++) {
				if (wc[j][i])
					for(int k=0; k<V;k++)
						if(wc[j][i] && wc[i][k])
							(wc[j][k]) = true;
			}
		}
	}

	/**
	 * will print matrix off and align matrix to proper
	 * width and present it in a nice form
	 * will use printwriter to print off matrix
	 */
	public void showMatrix()
	{
		printwriter.println("\nTransitive closure :\n");
		printwriter.println(" ");

		System.out.printf("%5s", "");
		for (int v=0;v<V;v++)
			printwriter.printf("%5s", "");
		printwriter.println();

		for (int v=0; v<V; v++)
		{
			printwriter.printf("%5d",v);
			for(int w=0;w<V;w++)
			{
				if(wc[v][w])
					printwriter.printf("%5d",1);
				else
					printwriter.printf("%5d",0);
			}
			printwriter.println();
		}
		printwriter.close();
	}

	/**
	 * large relations
	 * have to change the new int[][] to accommodate matrix size
	 * will start the time and end time and print it to file so that 
	 * you can put it in table manually
	 * uses algorithm is displayed in class per example
	 */
	public void large()
	{
		long startTime = System.currentTimeMillis();
		Random rand = new Random();
		int[][] p = new int[100][100];
		for (int i=0; i==100;i++)
		{
			for(int j=0;j==100;j++)
			{
				if(rand.nextDouble() < .05)
				{
					p[i][j]=0;
				}
				else
				{
					p[i][j]=1;
				}
			}
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime-startTime;
		printwriter.println();
		printwriter.println("Total Time: " + totalTime + " Miliseconds");
		printwriter.close();

	}

	/**
	 * saves file
	 * will use the printwriter function
	 */
	public void savefile() throws IOException
	{
		//File file = new File("Transitiveclosure.1out.java");
		//File file = new File("Transitiveclosure.2out.java");
		File file = new File("Transitiveclosure.3out.java");
		//File file = new File("Transitiveclosurebinary100.java");
		//File file = new File("Transitiveclosurebinary200.java");
		//File file = new File("Transitiveclosurebinary500.java");
		//File file = new File("Transitiveclosurebinary1000.java");

		printwriter = null;

		printwriter = new PrintWriter(file);
	}

	//will have to comment or uncomment to run specific files
	public static void main(String[] args)
	{
		System.out.println("read file: ");

		Scanner fileScanner = null;

		try {
			//fileScanner = new Scanner(new FileReader("file1"));
			//fileScanner = new Scanner(new FileReader("file2"));
			fileScanner = new Scanner(new FileReader("file3"));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		System.out.println();

		Transitiveclosure w = new Transitiveclosure();

		//System.out.println("\nEnter Vertices\n");
		int V = fileScanner.nextInt();

		//System.out.println("\nEnter Matrix\n");
		int [][]graph= new int [V][V];

		for (int i=0; i<V; i++)
			for(int j=0; j<V;j++)
				graph[i][j] = fileScanner.nextInt();

		//comment in if you wan to use large()
		//Transitiveclosure w = new Transitiveclosure();
		try {
			w.savefile();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}

		//executes warshall, showMatrix, and large functions.
		w.warshall(graph);
		w.showMatrix();
		w.large();
	}
}
