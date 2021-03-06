import java.util.Scanner;

/*
 * Michael Masterson
 * E00942993
 * Ranjan Chauduri
 * COSC 221 Sec. 1
 * Fall 2014
 * September 18, 2014
 * Write a program to compute the transitive closure of a relation R on a set A by using Warshall's algoritm.
 * 
 * Input to your program will br the size of n of the set A followed b the nxn boolean matrix corresponding to the relation R.
 * 
 * Run your program on the following input matrix and display all the intermediate matrices generated by the algorithm in a nice format.
 * 
 * KNOWN ISSUES: Does not show intermediate steps.   
 */
public class hw2 {

	private int V;
	private boolean [][] wc;

	//Warshall's Algorithm
	public void warshall(int[][] graph)
	{
		this.V = graph.length;
		wc = new boolean[V][V];
		for(int i=0; i<V;i++)
		{
			for (int j=0; j<V; j++)
				if(graph[i][j] !=0)
					wc[i][j]=true;
		}
		for (int i=0; i<V; i++)
		{
			for(int j=0;j<V;j++)
			{
				if(wc[j][i])
					for(int k=0;k<V;k++)
						if(wc[j][i] && wc[i][k])
							(wc[j][k]) =true;
			}
		}
	}
	//print matrix
	public void showMatrix()
	{
		System.out.println("\nTransitive closure :\n");
		System.out.println(" ");

		for(int v=0; v<V; v++)
			System.out.print("   " + v);
		System.out.println();

		for (int v=0; v<V; v++)
		{
			System.out.print(v +" ");
			for (int w=0; w<V; w++)
			{
				if (wc[v][w])
					System.out.print("  1  ");
				else
					System.out.print("  0  ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println();
		hw2 g = new hw2();

		System.out.println("\nEnter Vertices\n");
		int V = keyboard.nextInt();

		System.out.println("\nEnter Matrix\n");
		int [][] graph = new int [V][V];
		for (int i=0; i<V;i++)
			for(int j=0; j<V;j++)
				graph[i][j] = keyboard.nextInt();

		g.warshall(graph);
		g.showMatrix();
	}
}