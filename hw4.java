import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;


/*
MichaelMasterson
E00942993
Ranjan Chaudhuri
COSC 314 Sec. 0
Fall2015
Programming Assignment #4
November 30, 2015

Write a program to find the length of the shortest path from the vertex 1to the vertex n by using the Dijkstra's algorithm in an undirected weighted connected graph G whose vertices
are labeled from 1thru n.The input to your program will be the weighted adjacency matrix of the graph.

RESULT: a -> b(2)  -> e(4)  -> d(5)  -> f(7)
 */
public class hw4
{
	//space for graph from problem.
	private static final Graph.Edge[] GRAPH =
		{
				new Graph.Edge("a","b", 2), 
				new Graph.Edge("a","c",3), 
				new Graph.Edge("b", "a", 2), 
				new Graph.Edge("b","b",0), 
				new Graph.Edge("b","d",5), 
				new Graph.Edge("b","e",2), 
				new Graph.Edge("c","a",3), 
				new Graph.Edge("c","c",0), 
				new Graph.Edge("c","e",5), 
				new Graph.Edge("d","b",5), 
				new Graph.Edge("d", "d",0), 
				new Graph.Edge("d", "e",1), 
				new Graph.Edge("d", "f", 2),
				new Graph.Edge("e","b", 2), 
				new Graph.Edge("e","c",5), 
				new Graph.Edge("e","d",1), 
				new Graph.Edge("e","e",0), 
				new Graph.Edge("e","f", 4), 
				new Graph.Edge("f","d", 2), 
				new Graph.Edge("f","e",4), 
				new Graph.Edge("f","f",0),
		};
	//starting point
	private static final String START = "a";

	//ending point
	private static final String END = "f";

	//run program
	public static void main(String[] args) 
	{
		Graph g = new Graph(GRAPH); 
		g.Dijkstra(START); 
		g.printPath(END);
		//g.printAIIPaths();
	}
}

class Graph
{
	private final Map<String,Vert> graph;
	//mapping of vertex names to Vertex objects,built from a set of Edges

	//One edge of the graph (only used by Graph constructor)
	public static class Edge
	{
		public final String v1,v2;
		public final int distance;
		public Edge(String v1,String v2,int dist)
		{
			this.v1 = v1;
			this.v2 = v2;
			this.distance = dist;

		}

		//One vertex of the graph, complete with mappings to neighboring vertices 
		public static class Vert implements Comparable<Vert>
		{
			public final String name;
			public int dist = Integer.MAX_VALUE;
			public Vert previous = null;
			public final Map<Vert,Integer> neighbors = new HashMap<>();


			public Vert(String name)
			{
				this.name = name;
			}
			private void printPath()
			{
				if (this == this.previous)
				{
					System.out.printf("%s",this.name);
				}

				else if (this.previous == null)
				{
					System.out.printf("%s(unreached)",this.name);
				}
				else
				{
					this.previous.printPath();
					System.out.printf(" -> %s(%d)",this.name,this.dist);
				}
			}
			public int compareTo(Vert other)
			{
				return Integer.compare(dist,other.dist);
			}
		}

		//Builds a graph from a set of edges 
		public Graph(Edge[] edges)
		{
			graph = new HashMap<>(edges.length);

			//one pass to find all vertices for (Edge e:edges)
			{
				if (!graph.containsKey(e.v1)) graph.put(e.v1,new Vert(e.v1));
				if ( !graph.containsKey(e.v2)) graph.put(e.v2, new Vert(e.v2));
			}

			//another pass to set neighboring vertices 
			for (Edge e : edges)
			{
				graph.get(e.v1).neighbors.put(graph.get(e.v2),e.distance);
				//graph.get(e.v2).neighbors.put(graph.get(e.v1), e.dist);
				//also do this for an undirected graph
			}



			/** Runs Dijkstra using a specified source vertex*/
			public void Dijkstra(String startName)
			{
				if (!graph.containsKey(startName))
				{
					System.err.printf("Graph doesn't contain start vertex \"%s\"\n",startName);
					return;
				}
				final Vert source= graph.get(startName); 
				NavigableSet<Vert> q = new TreeSet<>();

				//set-up vertices
				for (Vert v :graph.values())
				{
					v.previous = v == source ? source :null;
					v.dist = v == source? 0: Integer.MAX_VALUE;
					q.add(v);
				}
				dijkstra(q);
			}


			/**Implementation of dijkstra's algorithm using a binary heap.*/
			private void dijkstra(final NavigableSet<Vert> q)
			{
				Vert u,v;
				while (!q.isEmpty())
				{
					u = q.pollFirst();
					//vertex with shortest distance (first iteration will return source)
					if (u.dist == Integer.MAX_VALUE) break;
					//we can ignore u (and any other remaining vertices) since they are unreachable


					//look at distances to each neighbor
					for (Map.Entry<Vert,Integer> a : u.neighbors.entrySet())
					{
						v = a.getKey(); //the neighbor in this iteration

						final int alternateDist = u.dist + a.getValue();
						if (alternateDist < v.dist)
						{
							// shorter path to neighbor found 
							q.remove(v);
							v.dist = alternateDist; 
							v.previous = u; 
							q.add(v);
						}
					}
				}
			}




			//Prints a path from the source to the specified vertex 
			public void printPath(String endName)
			{
				if (!graph.containsKey(endName))
				{
					System.err.printf("Graph doesn't contain end vertex \"%s\"\n",endName);
					return;
				}

				graph.get(endName).printPath(); 
				System.out.println();
			}

			//Prints the path from the source to every vertex (output order is not guaranteed)
			public void printAllPaths()
			{
				for (Vert v: graph.values())
				{
					v.printPath(); 
					System.out.println();
				}
			}
		}
	}