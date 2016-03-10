/**
 * 
 */
package foss.freak.heap;

/**
 * @author Codename 47
 *
 */
public class Heap {
	
	public static final int _MAXHEAP = 1;
	public static final int _MINHEAP = 0;
	private static final int _UNDEFINED = -1; 
	
	private int heapSize=0;
	private int heapType=_UNDEFINED;
	private int input[] = null;
	private int currentLastLeafIndex = -1;
	
	public Heap(int size, int heapType)
	{
		this.heapType = heapType;
		this.heapSize = size;
		this.input = new int[heapSize];
	}
	
	public Heap(int size, int heapType,int input[])
	{
		this.heapSize = size;
		this.heapType = heapType;
		this.input = input;
		this.currentLastLeafIndex =size-1;
	}
	
	public void maxHeapify(int index, int input[])
	{
		if(heapType!=_MAXHEAP)
		{	
			System.err.println("INVALID OPERATION");
			return;
		}
		
		int leftChildIndex = (index<<1)+1;
		int rightChildIndex = leftChildIndex+1;
		int maxIndex;
		if(rightChildIndex <= currentLastLeafIndex )
		{
			maxIndex = (input[leftChildIndex] >input[rightChildIndex])?leftChildIndex:rightChildIndex;
		}
		else{
			if(leftChildIndex == currentLastLeafIndex)
			{
				maxIndex = leftChildIndex;
			}
			else 
				return;
		}
		if(input[index] >= input[maxIndex])
		{
				return;
		}
		else
		{
		  int temp = input[maxIndex];
		  input[maxIndex] = input[index];
		  input[index] = temp;
		  maxHeapify(maxIndex, input);
		  return;
		}			
	}
	
	public void minHeapify(int index, int input[])
	{
		if(heapType!=_MINHEAP)
		{	
			System.err.println("INVALID OPERATION");
			return;
		}

		int leftChildIndex = (index<<1)+1;
		int rightChildIndex = leftChildIndex+1;
		int minIndex;
		if(rightChildIndex <= currentLastLeafIndex )
		{
			minIndex = (input[leftChildIndex] <input[rightChildIndex])?leftChildIndex:rightChildIndex;
		}
		else{
			if(leftChildIndex == currentLastLeafIndex)
			{
				minIndex = leftChildIndex;
			}
			else 
				return;
		}
		if(input[index] <= input[minIndex])
		{
				return;
		}
		else
		{
		  int temp = input[minIndex];
		  input[minIndex] = input[index];
		  input[index] = temp;
		  minHeapify(minIndex, input);
		  return;
		}		
	}
		
	
	public int showMin()
	{
		if(heapType!=_MINHEAP)
		{	
			System.err.println("INVALID OPERATION");
			return -1;
		}
		return (heapType == _MINHEAP && currentLastLeafIndex>=0)?input[0]:-1;
	}
	
	public int showMax()
	{
		if(heapType!=_MAXHEAP)
		{	
			System.err.println("INVALID OPERATION");
			return -1;
		}
		return (heapType == _MAXHEAP && currentLastLeafIndex>=0)?input[0]:-1;
	}
	
	public int extractMin()
	{
		if(heapType == _MINHEAP && currentLastLeafIndex>=0)
		{
			int min = input[0];
			if(currentLastLeafIndex == 0)
			{
				--currentLastLeafIndex;				
			}
			else{
				input[0] = input[currentLastLeafIndex--];
				minHeapify(0,input);
			}
			return min;
		}
		else
		{
			System.out.println("Either heap is empty Or Operation Undefined.");
			showHeap();
			return -1;
		}		
	}
	public int extractMax()
	{
		if(heapType == _MAXHEAP && currentLastLeafIndex>=0)
		{
			int max = input[0];
			if(currentLastLeafIndex == 0)
			{
				--currentLastLeafIndex;				
			}
			else{
				input[0] = input[currentLastLeafIndex--];
				maxHeapify(0,input);
			}
			return max;
		}
		else
		{
			System.out.println("Either heap is empty Or Operation Undefined.");
			showHeap();
			return -1;
		}
		
	}
	public void showHeap()
	{
		for(int i=0;i<=currentLastLeafIndex;i++)
			System.out.print(input[i]+"\t");
		System.out.println("\n");
	}
	
	/**
	 * Insert the element in the heap.
	 * */
	public void insertElement(int element)
	{
		if(currentLastLeafIndex < heapSize-1)
		{
			input[++currentLastLeafIndex] = element;
		}
		else
		{
			doubleHeap();
			input[++currentLastLeafIndex] = element;
		}
		
		if(heapType ==_MAXHEAP)
		{
			max_heapifyTop(currentLastLeafIndex);			
		}
		else
		{
			min_heapifyTop(currentLastLeafIndex);
		}
		showHeap();
		
	}
	/**
	 * Move minimum Index up the tree.
	 * */
	private void min_heapifyTop(int currentIndex) {
		if(currentIndex == 0)
			return;
		int parentIndex = parent(currentIndex);
		if(input[parentIndex]>input[currentIndex])
		    {
				int temp = input[currentIndex];
				input[currentIndex] = input[parentIndex];
				input[parentIndex] =temp;
				min_heapifyTop(parentIndex);
			}
		return;		
	}

	/**
	 * Move maximum element up the tree.
	 * */
	private void max_heapifyTop(int currentIndex) {
		if(currentIndex == 0)
			return;
		int parentIndex = parent(currentIndex);
		if(input[parentIndex]<input[currentIndex])
		    {
				int temp = input[currentIndex];
				input[currentIndex] = input[parentIndex];
				input[parentIndex] =temp;
				max_heapifyTop(parentIndex);
			}
		return;		
	}
	
	/**
	 * Increases the size of heap when heap is full.
	 * */
	private void doubleHeap() {
		int finalHeapSize = 2*heapSize;
		int finalHeap[] = new int[finalHeapSize];
		
		for(int i=0;i<heapSize;i++)
		{
			finalHeap[i]=input[i];
		}
		heapSize=finalHeapSize;
		input = finalHeap;
	}
	
	/**Returns the parent of current node
	 * */
	private int parent(int childIndex)
	{
		return (childIndex==0)?-1:((childIndex-1)>>1);
	}	
}
