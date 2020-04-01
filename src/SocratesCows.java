import java.util.*;
import java.util.Map.Entry;

public class SocratesCows {

	static final int INFINITY =Integer.MAX_VALUE; 
	ArrayList<ArrayList<String>> input_data = new ArrayList<>();
	int no_of_paths=0;
	long start_time, end_time;
	public SocratesCows() {
	}
	public static void main(String[] args) {
		getMaxMemory();
		SocratesCows obj = new SocratesCows();
		obj.input();
		obj.createGraph();
		obj.runTimeAnalysis();	
	}

	private void createGraph() {
		HashMap<String,Node> hashNodes= new HashMap<>();
		ArrayList<Node> nodes= new ArrayList<>();
		for(ArrayList<String> line : input_data) {
			String u= line.get(0); String v= line.get(1); 
			Node nodeU = new Node(u);
			Node nodeV	 = new Node(v);
			if(hashNodes.containsKey(u))nodeU= hashNodes.get(u);
			else nodeU = new Node(u);
			if(hashNodes.containsKey(v))nodeV= hashNodes.get(v);
			else nodeV = new Node(v);
			int weight= Integer.parseInt(line.get(2));
			nodeV.addEdge(nodeU, weight);
			hashNodes.put(nodeU.name,nodeU);
			hashNodes.put(nodeV.name,nodeV);
		}
		Graph graph = new Graph();
		for (Entry < String, Node> entry: hashNodes.entrySet()) {
			graph.addNode(entry.getValue());
			nodes.add(entry.getValue());
		}
		System.out.print("\n\nadjacency list");
		for (Node node : nodes) {
			System.out.print("\n"+node.name+":");
			for (Entry < Node, Integer> entry: node.adjacent_nodes.entrySet()) {
				System.out.print(entry.getKey().name+",");
			}
		}
		System.out.println("	\n");
		ArrayList<Node> cowNodes = graph.getCowNodes();
		graph = calcShortestPath(graph, graph.getNode("z"));
		for(Node node: cowNodes) {
			node.printShortestPath();
		}
		Node fastestCow= cowNodes.get(0);
		for(Node node: cowNodes) {
			if(node.distance < fastestCow.distance)
				fastestCow= node;
		}
		System.out.println("Answer :"+fastestCow.name+ " "+ fastestCow.distance);
		System.out.println("\nThe cow from Meadow "+ fastestCow.name+" reaches first");

	}

	public void runTimeAnalysis() {
		end_time = System.currentTimeMillis();
		System.out.println("\nRun Time Analysis:-");
		System.out.println("Total memory used: " + getUsedMemory()/1000000 + " MB");
		float elapsed_time = end_time - start_time;
		System.out.println("Total time elapsed: " + elapsed_time/1000+ " Seconds");
	}

	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter:");
		no_of_paths = Integer.parseInt(sc.nextLine());
		start_time = System.currentTimeMillis();
		for (int i = 0; i < no_of_paths; i++) {
			ArrayList<String> line = new ArrayList<>();
			line.addAll(Arrays.asList(sc.nextLine().split("\\s")));
			input_data.add(line);
		}
	}

	public static class Graph{
		ArrayList<Node> nodes = new ArrayList<>();
		public  void addNode(Node pnode) {
			nodes.add(pnode);
		}
		public Node getNode(String pname) {
			for(Node node:nodes) {
				if(node.name.equals(pname))
					return node;
			}	
			return null;
		}
		public ArrayList<Node> getCowNodes() {
			ArrayList<Node> cows = new ArrayList<>();
			for(Node node:nodes) {
				char ch= node.name.charAt(0);
				if(Character.isUpperCase(ch))
					cows.add(node);
			}	
			return cows;
		}
	}

	public static class Node{
		String name;
		HashMap<Node,Integer> adjacent_nodes = new HashMap<>();
		int distance= INFINITY;
		LinkedList<Node> shortest_path = new LinkedList<>();
		public Node(String pname) {
			this.name = pname;
		}
		public void addEdge(Node pnode, int pweight) {
			adjacent_nodes.put(pnode, pweight);
		}
		public void printShortestPath() {
			System.out.println("Cow at Meadow "+ this.name);
			for(Node n : shortest_path) {
				System.out.print(n.name+":"+"<---");
			}
			System.out.println(this.name);
			System.out.println("Shortest path distance:"+ this.distance);
			System.out.println();
		}
	}

	public Graph calcShortestPath(Graph g, Node source) {
		source.distance=0;
		ArrayList<Node> settled = new ArrayList<>();
		ArrayList<Node> unsettled = new ArrayList<>();
		unsettled.add(source);
		while(unsettled.size()!=0) {
			Node currentNode= getEvaluationNode(unsettled);
			unsettled.remove(currentNode);
			for (Entry < Node, Integer> entry: 
				currentNode.adjacent_nodes.entrySet()) {
				Node adjacentNode= entry.getKey();
				int weight= entry.getValue();
				if(!settled.contains(adjacentNode)) {
					setDistance(adjacentNode, weight,currentNode);
					unsettled.add(adjacentNode);
				}
			}
			settled.add(currentNode);
		}
		return g;
	} 
	public  Node getEvaluationNode(ArrayList<Node> unsettled) {
		int min = INFINITY;
		Node evaluationNode = null;
		for(Node n: unsettled) {
			if(n.distance< min) {
				min=n.distance;
				evaluationNode= n;
			}
		}
		return evaluationNode;
	}
	public void setDistance(Node p_adjacentNode, int p_edgeWeight, Node p_currentNode ) {
		int currentDistance= p_currentNode.distance;
		if(currentDistance + p_edgeWeight < p_adjacentNode.distance) {
			p_adjacentNode.distance= currentDistance + p_edgeWeight;
			LinkedList<Node> shortestPath = new LinkedList<>(p_currentNode.shortest_path);
			shortestPath.add(p_currentNode);
			p_adjacentNode.shortest_path= shortestPath;
		}
	} 

	public static long getMaxMemory() { return Runtime.getRuntime().maxMemory();}
	public static long getUsedMemory() {return getMaxMemory() - getFreeMemory();}
	public static long getTotalMemory() { return Runtime.getRuntime().totalMemory();}
	public static long getFreeMemory() { return Runtime.getRuntime().freeMemory();}
}
