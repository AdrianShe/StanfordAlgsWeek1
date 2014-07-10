import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	static Graph graph;
	static int numEdges;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		File edges = new File("edges.txt");
		readFile(edges);
		// graph.printGraph();
		System.out.println("Running Prim's Algorithm");
		
		 Graph spanningtree = graph.runPrims();
		System.out.println("The Cost is" + spanningtree.computeCost());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void readFile(File fin) throws IOException {
		FileInputStream fis = new FileInputStream(fin);
	 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		String line = null;
		int counter = 0;
		int edges = 0;
		while ((line = br.readLine()) != null) {
			String[] result = line.split(" ");
			if (counter == 0) {
				graph = new Graph(Integer.parseInt(result[0]));
				numEdges = Integer.parseInt(result[1]);
			}
			else {
				int source = Integer.parseInt(result[0]);
				int destination = Integer.parseInt(result[1]);
				int weight = Integer.parseInt(result[2]);
				graph.addEdge((source - 1), (destination - 1), weight);
				edges++;
			}
			counter++;
		}
	 
		System.out.println("Parsed " + edges + " edges " + "Supposed to be " + numEdges + " edges");
		br.close();
	}

}
