/**
 * 
 */
package foss.freak.comparison;

import java.util.Scanner;

/**
 * @author Codename 47
 *
 */
public class BinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int input[] =new int[size];
		System.out.println("Enter sorted list of "+size+" numbers.");
		for(int i=0;i<size;i++)
			input[i] = sc.nextInt();
		System.out.println("Enter value to be searched");
		int searchKey =sc.nextInt();
		sc.close();		
		int index = binarySearch(input,0,size-1, searchKey);
		System.out.println( (index==-1)?("Number NOT present."):("Number found at "+index));
	}

	private static int binarySearch(int[] input, int i, int j, int searchKey) {
		if(j==i+1)
		{
			if(searchKey == input[i]) return i;
			   else if(searchKey == input[j]) return j;
			           else return -1; 
		}
		else if(j==i)
		{
			if(searchKey == input[i]) return i;
			else return -1;
		}
		else{
			int mid = (i+j)/2;
			if(input[mid]==searchKey)
			{
				return mid;
			}
			else if(input[mid]>searchKey)
			{
				return binarySearch(input , i,mid-1,searchKey);
			}
			else{
				return binarySearch(input , mid+1,j,searchKey);
			}
		}
	}

}
