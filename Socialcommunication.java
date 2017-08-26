import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 *@author 	Michael Masterson
 *@EID		E00942993
 *@version	1.0
 *@since	2016-2-12
 *This program will follow and implement the requirements stated in the assignment. It will find the transitive closure
 *based on the files provided by instructor.
 * 
 */



public class Socialcommunication {
	private int V;
	private boolean [][] wc;
	private PrintWriter printwriter;

	
	/**
	 * perform Warshall algorithm
	 * will take graph's length and then insert new boolean for i and j values
	 * perform Warshall algorithm will take the graph's length and the insert
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
	 * saves file
	 * will use the printwriter function
	 */
	public void savefile() throws IOException
	{
		File file = new File("Socialcommunicationout.java");

		printwriter = null;

		printwriter = new PrintWriter(file);
	}

	public static void main(String[] args)
	{
		System.out.println("read file: ");

		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(new FileReader("file4"));
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println();

		Socialcommunication w = new Socialcommunication();

		int V = fileScanner.nextInt();
		int numentry = fileScanner.nextInt();
		int[][] graph = new int[V][V];

		for (int i=0; i<numentry; i++)
		{
			int r = fileScanner.nextInt() -1;
			int c = fileScanner.nextInt() -1;
			graph [r][c] =1;
		}

		try {
			w.savefile();
		}catch(IOException e) {
			e.printStackTrace();
		}

		//make sure that the program is running correctly
		/*int[][]graph = new in [V][V];
		for (int i=0;i<V;i++) {
			for(int j=0;j<V; j++)
			{
				System.out.print(graph[i][j] + "    ");
			}
			System.out.println();
		}*/
		w.warshall(graph);
		w.showMatrix();
	}
}
