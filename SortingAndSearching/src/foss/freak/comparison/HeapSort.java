/**
 * 
 */
package foss.freak.comparison;

import java.util.Scanner;

import foss.freak.heap.Heap;
import foss.freak.heap.HeapBuildLinearTime;

/**
 * @author Codename 47
 *
 */
public class HeapSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int input[] = new int[size];
		for(int i=0;i<size;i++)
			input[i] = sc.nextInt();
		Heap maxHeap =new Heap(size, Heap._MAXHEAP, input);
		HeapBuildLinearTime.buildMaxHeapBottomUp(input, size);
		for(int i=0;i<size;i++)
		{			
			input[size-i-1]=maxHeap.extractMax();
		}
		for(int i =0;i<size;i++)
		{
			System.out.print(input[i]+"\t");
		}
		sc.close();
	}

}
