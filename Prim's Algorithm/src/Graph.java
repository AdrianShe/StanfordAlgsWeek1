import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Graph  {
	
	// A representation of a graph as an adjacency matrix
	// from is on the left axis
	// to is on the right;
	
	int numverticies;
	private Integer[][] graph;
	
	public Graph() {
		// Default capacity is 100 verticies
		numverticies = 100;
		graph = new Integer[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				graph[i][j] = 0;
			}
		}
	}
	
	public Graph(int size) {
		numverticies = size;
		graph = new Integer[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				graph[i][j] = 0;
			}
		}
	}
	
	public void addEdge(int s, int d, int w) {
		graph[s][d] = w;
		graph[d][s] = w; // Graph is undirected so adjacency matrix symmetric;
	}
	
	// O(n^2) implementation of Prim's algorithm;
	
	public Graph runPrims() {
		Graph spanningtree = new Graph(numverticies);
		List<Integer> seenverticies = new ArrayList<Integer>();
		
		// Initialize first vertex arbitrarilly
		int curVertex = 0; 
		seenverticies.add(curVertex);
		
		while (seenverticies.size() < numverticies) {
			// Iterate through the edges connected to current vertex to find
			// the edge with minimum weight
			int minEdgeWeight = Integer.MAX_VALUE;
			int minEdgePos = 0;
			for (Integer vertex: seenverticies) {
				Integer[] edges = graph[vertex];
				for (int i = 0; i < edges.length; i++) {
					int curWeight = edges[i];
					if (curWeight != 0) {
						if (curWeight < minEdgeWeight && !seenverticies.contains(i)) {
							curVertex = vertex;
							minEdgeWeight = curWeight;
							minEdgePos = i;
					}
				}
			}
			}
			
			
			spanningtree.addEdge(curVertex, minEdgePos, minEdgeWeight);
			seenverticies.add(minEdgePos);
			curVertex = minEdgePos;
		}
	
		System.out.println("In all, " + "I saw " + seenverticies.size() + " verticies");
		System.out.println("In this order ");
		String s = " ";
		for (Integer curvertex2: seenverticies) {
			s = s + " " + Integer.toString(curvertex2);
		}
		System.out.println(s);
		return spanningtree;
	}
	
	public int computeCost() {
		int cost = 0;
		int counter = 0;
		for (int i = 0; i < numverticies; i++) {
			for (int j = counter; j < numverticies; j++) {
				cost += graph[i][j];
			}
			counter++;
		}
		return cost;
	}
	
	public void printGraph() {
		for (int i = 0; i < numverticies; i++) {
			String toString = "Vertex " + (i + 1) + " :";
			for (int j = 0; j < numverticies; j++) {
				toString = toString + " " + graph[i][j];			
			}
			System.out.println(toString + "\n");
		}
	}


	



	

}
