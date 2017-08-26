import java.util.Random;
import java.util.Scanner;

/**
 *@author 	Michael Masterson
 *@EID		E00942993
 *@version	1.0
 *@since	2016-2-12
 *This program will follow and implement the requirements stated in the assignment. Also, it will take the p value and number and compute the
 *transitive closure. Also, it will give the percent of one's. It will print in console.
 */
public class VirusSimulator 
{
	private int num;
	private double pvalue;
	private int seed;
	private int[][] wc;

	/**
	 * Constructor for matrix
	 */
	public VirusSimulator(int num, double pvalue, int seed)
	{
		this.num = num;
		this.pvalue = pvalue;
		this.seed = seed;
		wc = new int [num][num];
		randommat();
	}

	/**
	 * perform Warshall algorithm
	 * will take graph's length and then insert new boolean for i and j values
	 * perform Warshall algorithm will take the graph's length and the insert
	 * new boolean for i an j values
	 */
	public void warshall (int num, double pvalue)
	{
		for (int i=0; i<num; i++)
		{
			for (int j=0; j<num; j++)
				if(wc[i][j] !=0)
					wc[i][j] =1;
		}
		for (int i=0; i<num; i++)
		{
			for (int j=0; j<num; j++)
			{
				if(wc[j][i] ==1)
					for(int k=0;k<num;k++)
						if(wc[j][i]==1 && wc[i][k]==1)
							(wc[j][k])=1;
			}
		}
	}

	/**
	 * will print matrix off and align matrix to proper
	 * width and present it in a nice form
	 * will use System.out to print off matrix
	 */
	public void showMatrix()
	{
		System.out.println("\nTransitive closure :\n");
		System.out.println(" ");

		System.out.printf("%5s", "");
		for (int v=0; v<num; v++)
			System.out.printf("%5s", "");
		System.out.println();

		for (int v=0; v<num; v++)
		{
			System.out.printf("%5d",v);
			for(int w=0;w<num;w++)
			{
				if(wc[v][w]==1)
					System.out.printf("%5d",1);
				else
					System.out.printf("%5d",0);
			}
			System.out.println();
		}
	}

	/**
	 * will calculate the percent of 1's in the matrix
	 * based on the i and j places and return the decimal
	 * of the value to be calculated in the main
	 */
	public double percentFilled()
	{
		int numone = 0;

		for (int i=0;i<wc.length;i++)
		{
			for (int j=0; j<wc.length; j++)
			{
				if(wc[i][j]==1)
				{
					numone++;
				}
			}
		}
		return (double) numone/(wc.length*wc.length);
	}

	/**
	 * uses algorithm is displayed in class per example
	 * and uses seed value for user.
	 */
	private void randommat()
	{
		Random rand = new Random();
		rand.setSeed(seed);
		for (int i=0; i<wc.length;i++)
		{
			for (int j=0; j<wc.length; j++)
			{
				if(rand.nextDouble()<pvalue)
				{
					wc[i][j]=1;
				}
				else
				{
					wc[i][j]=0;
				}
			}
		}
	}


	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Population Size: "); //take user input from user
		int num = keyboard.nextInt();

		//take input from user
		System.out.println("Connection of probabilty: ");
		double pvalue = keyboard.nextDouble();

		System.out.println("What is your seed value "); //take user input from user
		int seed = keyboard.nextInt();

		VirusSimulator vs = new VirusSimulator(num,pvalue,seed);

		//execute program methods above
		vs.warshall(num, pvalue);
		vs.showMatrix();
	}
}
