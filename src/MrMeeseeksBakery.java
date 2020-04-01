import java.util.*;

public class MrMeeseeksBakery {

	int no_of_queues=0;
	long start_time, end_time;
	 ArrayList<ArrayList<String>> input_data = new ArrayList<>();
	 ArrayList<Queue<Integer>> queues = new ArrayList<>();
	 ArrayList<Integer> customers = new ArrayList<>();
	 Queue<Integer> queueNowServing ;
	 int noOfCustomersServed=0;
	 int time=1;

	public static void main(String[] args) {
		MrMeeseeksBakery bakery = new MrMeeseeksBakery();
		bakery.scanInput();
		bakery.makeQueues();
		bakery.takeOrder();
		System.out.println("No of customers served:"+bakery.noOfCustomersServed);
		bakery.runTimeAnalysis();
	}

	private void takeOrder() {
		boolean bakeryOpen= true;
		while(bakeryOpen) {
			int lowestPatienceCustomer=findMin(customers);
			queueNowServing = queueOfCustomer(lowestPatienceCustomer);
			while(customerNowServing()!=lowestPatienceCustomer && bakeryOpen) {
				bakeryOpen= serveLoaf();
			}
			bakeryOpen= serveLoaf();
		}
	}
	private boolean serveLoaf() {
		if(time<=customerNowServing()) {
			System.out.println("Served:"+customerNowServing() +" time: "+time+ " seconds");
			time++;
			customers.remove(customers.indexOf( customerNowServing()));
			queues.remove(queueNowServing);
			queueNowServing.remove();
			noOfCustomersServed++;
			if(!queueNowServing.isEmpty())
				queues.add(queueNowServing);
			if(queues.isEmpty())
				return false;
			return true;
		}
		else {
			System.out.println("Customer "+customerNowServing()+" burned the bakery!");
			return false;
		}
	}

	private int findMin(ArrayList<Integer> customers) {
		return customers.get(customers.indexOf (Collections.min(customers))) ; 
	}
	
	private Queue<Integer> queueOfCustomer(int min) {
		for(Queue<Integer> queue : queues)
			if(queue.contains(min))
				return queue;
		return null;
	}

	private  void makeQueues() {
		for(ArrayList<String> line : input_data) {
			Queue<Integer> queue = new LinkedList<>();
			int noOfPeople= Integer.parseInt(line.get(0));
			for (int i=1; i<=noOfPeople; i++) {
				queue.add(Integer.parseInt(line.get(i)));
				customers.add(Integer.parseInt(line.get(i)));
			}
			queues.add(queue);
		}
	}
	
	public int customerNowServing() {
		return(int)queueNowServing.peek();
	}

	public void scanInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input:");
		no_of_queues = Integer.parseInt(sc.nextLine());
		start_time = System.currentTimeMillis();
		for (int i = 0; i < no_of_queues; i++) {
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