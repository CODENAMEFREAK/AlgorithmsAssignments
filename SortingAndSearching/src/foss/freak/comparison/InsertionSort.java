/**
 * 
 */
package foss.freak.comparison;

import java.util.Scanner;

/**
 * @author Codename 47
 *
 */
public class InsertionSort {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int size =sc.nextInt();
		int input[] =new int[size];
		for(int i=0;i<size;i++)
			input[i]=sc.nextInt();		
		sc.close();
		//traditionalInsretionSort(input, size);
		//showSortedArray(input, size);
		binarysearchInsertionSort(input, size);
		showSortedArray(input, size);
	}
	
	private static void showSortedArray(int[] input, int size) {
		for(int i=0;i<size;i++)
			System.out.print(input[i]+"\t");
		System.out.println("\n");
		
	}

	/**
	 * private static void traditionalInsretionSort(int input[] , int size)
	 * 
	 * Number of Comparisons = O(n^2).
	 * Number of Swaps = O(n^2).
	 * */
	private static void traditionalInsretionSort(int input[] , int size){
		if(size <=1)
		{
			return;
		}
		int tempDeck;
		int correctPos;
		for(int currentCardIndex = 1 ;currentCardIndex < size;currentCardIndex++)
		{
			tempDeck = input[currentCardIndex];
			correctPos=currentCardIndex-1;
			while(correctPos>=0 && input[correctPos]>tempDeck)
			{
				input[correctPos+1]=input[correctPos];
				correctPos--;
			}
			input[correctPos+1]=tempDeck;			
		}		
	}

	/**
	 * private static void binarysearchInsertionSort(int input[], int size)
	 * 
	 * Number of Comparisons = O(nlogn).
	 * Number of Swaps = O(n^2).	 * 
	 * */
	private static void binarysearchInsertionSort(int input[], int size){
		if(size<=1)
		{
			return;
		}
		int tempDeck;
		int correctPos;		
		int highestSortedLessValueIndex;
		
		for(int currentCardIndex = 1; currentCardIndex < size; currentCardIndex++)
		{
			highestSortedLessValueIndex = binarySearchLessVal(input, 0, currentCardIndex-1, input[currentCardIndex]);
			if(highestSortedLessValueIndex < currentCardIndex -1)
			{
				tempDeck = input[currentCardIndex];
				correctPos=currentCardIndex-1;
				while(correctPos!= highestSortedLessValueIndex)
					{
						input[correctPos+1]=input[correctPos];
					    correctPos--;
					}
				input[correctPos+1] = tempDeck;
			}			
		}
		
	}

	/**
	 * @return index which has highest value less than searchKey & -1 if all values are greater than searchKey.
	 * */
	private static int binarySearchLessVal(int[] input, int low, int high, int searchKey) {
		if(high == low+1)
		{
			if(input[high]<=searchKey)
			{
				return high;
			}
			else
			{
				if(input[low]<=searchKey)
					return low;
				else
					return low-1;
			}
		}
		else if(high == low)
		{
			if(input[low]<= searchKey)
				return low;
			else 
				return low-1;
		}		
		int mid = (low+high)/2;
		if(input[mid]<=searchKey)
		{
			if(input[mid+1]>searchKey)
				return mid;
			else
				return binarySearchLessVal(input, mid+1, high, searchKey);
		}
		else //i.e. input[mid]>searchKey
		{
			return binarySearchLessVal(input, low, mid-1, searchKey);
		}		
	}
	 
}
