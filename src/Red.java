import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Red {
	int graph_size=0;
	long start_time, end_time;
	ArrayList<ArrayList<String>> input_data = new ArrayList<>();
	Graph graph = new Graph();
	int result=0;

	public static void main(String[] args) {
		Red graph = new Red();
		graph.scanInput();
		graph.createGraph();
		graph.findDomSets();
		System.out.println("\n\nAnswer:"+graph.result);
		graph.runTimeAnalysis();
	}

	void domCheck(ArrayList<Node> vertices,  Node combination[], int start, 
			int end, int index, int r) 
	{ 
		Set<Node> set= new HashSet<>();
		if (index == r) 
		{ System.out.println("\n");
			for (int j=0; j<r; j++) 
			{
				set.add(combination[j]);
				System.out.print(combination[j].interval()+",");
				for(Node node:combination[j].neighbors)
					set.add(node);
			}
			System.out.println();
			if(set.size()==graph_size) {
				System.out.print("Dominating");result++;
			}
			System.out.print("Set:");
			return; 
		} 
		for (int i=start; i<=end && end-i+1 >= r-index; i++) 
		{ 
			 combination[index] = vertices.get(i); 
			domCheck(vertices, combination, i+1, end, index+1, r); 
		} 
	} 
  void findDominantSet(ArrayList<Node> vertices, int n, int r) 
	{ 
		Node data[]=new Node[r]; 
		domCheck(vertices, data, 0, n-1, 0, r); 
	} 

	class Node{
		int left;
		int right;
		private ArrayList<Node> neighbors = new ArrayList<>();
		int marked=0;
		public void setInterval(int pleft, int pright) {
			this.left = pleft;
			this.right = pright;
		}
		public void addNeighbor(Node pnode) {
			if(!neighbors.contains(pnode))
				neighbors.add(pnode);
		}
		private String interval() {
			return("["+String.valueOf(left)+","+String.valueOf(right)+"]");
		}
	}
	class Graph{
		ArrayList<Node> nodes = new ArrayList<>();
		public Graph() {

		}
		public void reset() {
			for(Node node:nodes)
				node.marked=0;
		}
		public void addNode(Node pnode) {
			this.nodes.add(pnode);
		}
		public void removeNode(Node pnode) {
			this.nodes.remove(pnode);
		}
		public Node getNode(int pleft, int pright) {
			for(Node node: nodes) {
				if(node.left==pleft && node.right==pright)
					return node;
			}
			return null;
		}
		public void traverse() {

		}
	}

	private void findDomSets() {
		for(int i=graph_size; i>=1 ; i--)
        	findDominantSet(graph.nodes, graph_size, i); 
	}

	private void createGraph() {
		for (ArrayList<String> line : input_data) {
			Node node = new Node();
			int left = Integer.parseInt(line.get(0));
			int right = Integer.parseInt(line.get(1));
			node.setInterval(left, right);
			graph.addNode(node);
		}
		assignNeighbors();
	}

	private void assignNeighbors() {
		ArrayList<Node> nodes = (ArrayList<Node>) graph.nodes.clone();
		for( int i=0;i<graph_size;i++) {
			for(int j=i+1;j<graph_size;j++) {
				Node node1=nodes.get(i);
				Node node2=nodes.get(j);
				if(( node1.right >= node2.left 	) && node1.left< node2.left) {
					int index1= graph.nodes.indexOf(node1);
					int index2= graph.nodes.indexOf(node2);
					node1.addNeighbor(node2);
					node2.addNeighbor(node1);
					graph.nodes.set(index1, node1);
					graph.nodes.set(index2, node2);
				}
			}
		}
	}

	private void scanInput() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Input:");
		graph_size = Integer.parseInt(sc.nextLine());
		start_time = System.currentTimeMillis();
		for (int i = 0; i < graph_size; i++) {
			ArrayList<String> line = new ArrayList<>();
			line.addAll(Arrays.asList(sc.nextLine().split("\\s")));
			input_data.add(line);
		}
	}
	public void runTimeAnalysis() {
		end_time = System.currentTimeMillis();
		System.out.println("\nRun Time Analysis:-");
		System.out.println("Total memory used: " + getUsedMemory()/1000000 + " MB");
		float elapsed_time = end_time - start_time;
		System.out.println("Total time elapsed: " + elapsed_time/1000+ " Seconds");
	}


	public static long getMaxMemory() { return Runtime.getRuntime().maxMemory();}
	public static long getUsedMemory() {return getMaxMemory() - getFreeMemory();}
	public static long getTotalMemory() { return Runtime.getRuntime().totalMemory();}
	public static long getFreeMemory() { return Runtime.getRuntime().freeMemory();}

}
