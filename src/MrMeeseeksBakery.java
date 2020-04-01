import java.util.*;

public class MrMeeseeksBakery {

	int no_of_queues=0;
	long start_time, end_time;
	static ArrayList<ArrayList<String>> input_data = new ArrayList<>();
	static ArrayList<Queue> queues = new ArrayList<>();
	static ArrayList<Integer> elements = new ArrayList<>();
	static Queue<Integer> servingq ;
	static int n=0;
	static int time=1;

	public MrMeeseeksBakery() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		MrMeeseeksBakery bakery = new MrMeeseeksBakery();
		bakery.scanInput();
		bakery.makeQueues();
		bakery.takeOrder();
		System.out.println("No of customers served:"+n);
		bakery.runTimeAnalysis();
	}

	private void takeOrder() {
		boolean val= true;
		while(val) {
			ArrayList<Integer> frontPeople = new ArrayList<>();
			int min=elements.get(findMinIndex());
			servingq = getQueue(min);
			Queue<Integer> temp = null;
			while((int)servingq.peek()!=min && val) {
				System.out.println("inside");
				val= serve();
			}
			val= serve();
		}
	}
	private boolean serve() {
		if(time<=(int)servingq.peek()) {
			System.out.println("served:"+servingq.peek() +" time:"+time);
			time++;
			elements.remove(elements.indexOf( (int)servingq.peek()));
			queues.remove(servingq);
			servingq.remove();

			if(!servingq.isEmpty())
				queues.add(servingq);
			else
				queues.remove(servingq);
			n++;
			if(queues.isEmpty())
				return false;
		}
		else {
			System.out.println("Customer "+servingq.peek()+" burned the bakery!");
			return false;
		}

		return true;
	}

	private int findMinIndex() {
		return elements.indexOf (Collections.min(elements)); 
	}
	private Queue<Integer> getQueue(int min) {
		for(Queue q : queues)
			if(q.contains(min))
				return q;
		return null;
	}

	private  void makeQueues() {
		for(ArrayList<String> line : input_data) {
			Queue<Integer> queue = new LinkedList<>();
			int noOfPeople= Integer.parseInt(line.get(0));
			for (int i=1; i<=noOfPeople; i++) {
				queue.add(Integer.parseInt(line.get(i)));
				elements.add(Integer.parseInt(line.get(i)));
			}
			queues.add(queue);
		}
	}

	public void scanInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter:");
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