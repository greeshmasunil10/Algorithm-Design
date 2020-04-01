import java.util.*;
import java.util.Map.Entry;




public class SocratesCows {

	static final int INFINITY =Integer.MAX_VALUE; 
	ArrayList<ArrayList<String>> input_data = new ArrayList<>();
	int no_of_paths=0;
	long start_time, end_time;
	public SocratesCows() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		getMaxMemory();
		SocratesCows obj = new SocratesCows();
		obj.input();
		obj.createGraph();
		obj.analysis();
	}

	private void createGraph() {
		HashMap<String,Node> hashnodes= new HashMap<>();
		ArrayList<Node> nodes= new ArrayList<>();
		for(ArrayList<String> line : input_data) {
			String u= line.get(0); String v= line.get(1); 
			Node nodeu = new Node(u);
			Node nodev	 = new Node(v);
			if(hashnodes.containsKey(u)) {
				nodeu= hashnodes.get(u);}
			else 	{
				nodeu = new Node(u);}
			if(hashnodes.containsKey(v)) {
				nodev= hashnodes.get(v);}
			else 	{
				nodev = new Node(v);}
			int weight= Integer.parseInt(line.get(2));
			nodev.addEdge(nodeu, weight);
			hashnodes.put(nodeu.name,nodeu);
			hashnodes.put(nodev.name,nodev);
		}
		Graph graph = new Graph();
		System.out.print("Graph nodes:");
		for (Entry < String, Node> entry: hashnodes.entrySet()) {
			graph.addNode(entry.getValue());
			nodes.add(entry.getValue());
			System.out.print(" "+entry.getValue().name);
		}
		System.out.print("\n\nadjacency list");
		for (Node node : nodes) {
			System.out.print("\n"+node.name+":");
			for (Entry < Node, Integer> entry: node.adjacentNodes.entrySet()) {
				System.out.print(entry.getKey().name+",");
			}
		}
		System.out.println("	\n");


		ArrayList<Node> cownodes = graph.getCowNodes();
		graph = RunShortestPathAlgorithm(graph, graph.getNode("z"));
		for(Node node: cownodes) {
			System.out.println(node.name+":"); node.getpath();
		}
		Node min= cownodes.get(0);
		for(Node n: cownodes) {
			if(n.distance < min.distance)
				min= n;
		}
		System.out.println("Answer :"+min.name+ " "+ min.distance);

	}


	public void analysis() {
		end_time = System.currentTimeMillis();
		System.out.println("Total memory used: " + getUsedMemory()/1000000 + " MB");
		float elapsedTime = end_time - start_time;
		System.out.println("Total time elapsed: " + elapsedTime/1000+ " Seconds");
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
		public  void addNode(Node n) {
			nodes.add(n);
		}
		public Node getNode(String name) {
			for(Node node:nodes) {
				if(node.name.equals(name))
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
		HashMap<Node,Integer> adjacentNodes = new HashMap<>();
		int distance= INFINITY;
		LinkedList<Node> shortestPath = new LinkedList<>();

		public Node(String name) {
			this.name = name;
		}
		public void setDistance(int d) {
			this.distance = d;
		}
		public void addEdge(Node n, int weight) {
			adjacentNodes.put(n, weight);
		}
		public void getpath() {
			for(Node n : shortestPath) {
				System.out.print(n.name+":"+"--->");
			}
			System.out.println(this.name);
			System.out.println("distance:"+ this.distance);
			System.out.println();

		}

	}
	private  void dispnodes(ArrayList<Node> nodes) {
		for(Node n: nodes)
			System.out.print(n.name+", ");
	}

	public Graph RunShortestPathAlgorithm(Graph g, Node source) {
		source.distance=0;
		ArrayList<Node> settled = new ArrayList<>();
		ArrayList<Node> unsettled = new ArrayList<>();

		unsettled.add(source);
		while(unsettled.size()!=0) {
			Node currentNode= getEvaluationNode(unsettled);
			unsettled.remove(currentNode);
			for (Entry < Node, Integer> entry: 
				currentNode.adjacentNodes.entrySet()) {
				Node adjnode= entry.getKey();
				int weight= entry.getValue();
				if(!settled.contains(adjnode)) {
					setDistance(adjnode, weight,currentNode);
					unsettled.add(adjnode);
				}
			}
			settled.add(currentNode);
		}

		return g;

	} 
	public  Node getEvaluationNode(ArrayList<Node> unsettled) {
		int min = INFINITY;
		Node EvaluationNode = null;
		for(Node n: unsettled) {
			if(n.distance< min) {
				min=n.distance;
				EvaluationNode= n;
			}
		}
		return EvaluationNode;
	}
	public void setDistance(Node adjNode, int weight, Node currentNode ) {
		int currentDistance= currentNode.distance;
		if(currentDistance + weight < adjNode.distance) {
			adjNode.distance= currentDistance + weight;
			LinkedList<Node> shortestPath = new LinkedList<>(currentNode.shortestPath);
			shortestPath.add(currentNode);
			adjNode.shortestPath= shortestPath;
		}
	} 

	public static long getMaxMemory() { return Runtime.getRuntime().maxMemory();}
	public static long getUsedMemory() {return getMaxMemory() - getFreeMemory();}
	public static long getTotalMemory() { return Runtime.getRuntime().totalMemory();}
	public static long getFreeMemory() { return Runtime.getRuntime().freeMemory();}
}
