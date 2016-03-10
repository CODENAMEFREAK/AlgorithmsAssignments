/**
 * 
 */
package foss.freak.heap;

import java.util.Scanner;

/**
 * @author Codename 47
 *
 */
public class HeapBuildLinearTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size =sc.nextInt();
		int input [] = new int[size];
		System.out.println("Enter the "+size+" elements in heap.");
		for(int i=0;i<size ;i++)
			input[i]= sc.nextInt();
		sc.close();
		buildMaxHeapBottomUp(input,size);
		System.out.print("MaxHeap:->");
		showHeap(input,size);
		buildMinHeapBottomUp(input,size);
		System.out.print("MinHeap:->");
		showHeap(input,size);
	}

	private static void showHeap(int[] input, int size) {
		for(int i=0;i<size;i++)
		{
			System.out.print(input[i]+"\t");
		}
		System.out.println("\n");
		
	}

	public static void buildMinHeapBottomUp(int[] input, int size) {
		Heap minHeap = new Heap(size, Heap._MINHEAP, input);		
		int lastParentIndex = ((size-1)%2==0)?((size-2)/2):((size-1)/2);
		int currentParent = lastParentIndex;
		while(currentParent>=0)
		{
			minHeap.minHeapify(currentParent, input);
			--currentParent;			
		}
	}
	
	public static void buildMaxHeapBottomUp(int[] input, int size) {
		Heap maxHeap = new Heap(size, Heap._MAXHEAP,input);
		int lastParentIndex = ((size-1)%2==0)?((size-2)/2):((size-1)/2);
		int currentParent = lastParentIndex;		
		while(currentParent>=0)
		{
			maxHeap.maxHeapify(currentParent, input);
			--currentParent;		
		}
	}

}
