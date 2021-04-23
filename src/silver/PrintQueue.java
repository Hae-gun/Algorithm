package silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class PrintQueue {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
		
		List<Integer> resultList = new ArrayList<>();
		while(n--!=0) {
			String tmp[] = br.readLine().split(" ");
			int count = Integer.valueOf(tmp[0]);
			int index = Integer.valueOf(tmp[1]);
			List<Integer> nums = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
			Queue<QueuePoint> queue = new LinkedList<>();
			PriorityQueue<Integer> priQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
			for(int i=0; i<nums.size(); i++) {
				queue.add(new QueuePoint(i, Integer.valueOf(nums.get(i))));
				priQueue.add(Integer.valueOf(nums.get(i)));
			}
			int res =1;
			while(!queue.isEmpty()) {
//				System.out.println(queue);
//				System.out.println(priQueue);
				int curPri = queue.peek().priority;
				int outPri = priQueue.peek();
				int curIndex = queue.peek().index;
				
				
				if(curPri==outPri) {
					if(curIndex == index) {
						resultList.add(res);
						break;
					}
					queue.poll();
					priQueue.poll();
					res++;
				}else {
					queue.add(queue.poll());
				}
				
			}
//			System.out.println("###############");
		}
		for(int num : resultList) {
			System.out.println(num);
		}
	}
}

class QueuePoint{
	int index;
	int priority;
	public QueuePoint(int index, int priority) {
		this.index = index;
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "QueuePoint [index=" + index + ", priority=" + priority + "]";
	}
}
