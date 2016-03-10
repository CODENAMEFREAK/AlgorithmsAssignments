/**
 * 
 */
package foss.freak.heap;

import java.util.Scanner;

/**
 * @author Codename 47
 *
 */
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		System.out.println("------MinHeap-----");
		Heap minHeap = new Heap(size, Heap._MINHEAP);
		System.out.println("Enter min heap elements\n");
		for(int i=1;i<size+3;i++)
		{
			minHeap.insertElement(sc.nextInt());			
		}
		System.out.println("Show Min: "+minHeap.showMin());
		minHeap.showHeap();
		System.out.println("Extract Min: "+minHeap.extractMin());
		minHeap.showHeap();
		
		System.out.println("------MaxHeap-----");
		Heap maxHeap = new Heap(size, Heap._MAXHEAP);
		System.out.println("Enter max heap elements\n");		
		for(int i=1;i<size+3;i++)
		{
			maxHeap.insertElement(sc.nextInt());
		}
		System.out.println("Show Max: "+maxHeap.showMax());
		maxHeap.showHeap();
		System.out.println("Extract Max: "+maxHeap.extractMax());
		maxHeap.showHeap();		
		sc.close();
	}

}
