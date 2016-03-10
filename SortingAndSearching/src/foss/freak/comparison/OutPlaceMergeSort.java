/**
 * 
 */
package foss.freak.comparison;

import java.util.Scanner;

/**
 * @author Codename 47
 *
 */
public class OutPlaceMergeSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int size =sc.nextInt();
		int input[] =new int[size];
		for(int i=0;i<size;i++)
			input[i] = sc.nextInt();
		sc.close();
		input = mergeSort(input, size);
		showArray(input, size);
		System.exit(0);
	}

	private static void showArray(int[] input, int size) {
		for(int i=0;i<size;i++)
			System.out.print(input[i]+"\t");
		System.out.println("\n");
		
	}

	private static int[] mergeSort(int[] input, int size) {
		int a [] = split(input , 0,size-1);
		return a;
	}

	private static int[] split(int[] input, int low, int high) {
		if(low==high){
			int in[] =new int[1];
			in[0]= input[low];
			return in;
		}
			
		if(high == low+1)
		{		int in[] =new int[2];		
			if(input[low]>input[high])
			{			
				in[0]=input[high];
				in[1]=input[low];				
			}
			else{
				in[0] = input[low];
				in[1] = input[high];
			}
			return in;
		}
		else{
				int mid = (low+high)/2;
				int firstHalf[] = split(input, low,mid);
				int secondHalf[] = split(input,mid+1,high);
				return merge(firstHalf, mid-low+1, secondHalf, high-mid);/**lowerHalfSize-> mid-low+1 and secondHalf->high-(mid+1)+1= high-mid-1+1=(high-mid)*/
		}
	}
	
	
	private static int[] merge(int first[], int firstSize, int second[], int secondSize)
	{
		int total = firstSize+secondSize;
		int totalArray[] =new int[total];
		int firstIndex = 0;
		int secondIndex = 0;
		int totalIndex=0;
		while((firstIndex < firstSize) && (secondIndex < secondSize))
		{
			if(first[firstIndex]<=second[secondIndex])
			{
				totalArray[totalIndex++] = first[firstIndex];
				if(first[firstIndex++]==second[secondIndex])
					totalArray[totalIndex++] =second[secondIndex++];					
			}
			else
			{
				totalArray[totalIndex++] = second[secondIndex++];
			}
		}
		if((firstIndex == firstSize) && (secondIndex == secondSize))
		{
			return totalArray;
		}
		else{
			if(firstIndex == firstSize)
			{
				while(secondIndex!=secondSize)
					totalArray[totalIndex++] = second[secondIndex++];
			}			
			else
			{
				while(firstIndex!=firstSize)
					totalArray[totalIndex++] = first[firstIndex++];
			}
		}
		return totalArray;
	}

}
