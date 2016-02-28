/**
 * 
 */
package foss.freak.oneD;

import java.util.Scanner;

/**
 * @author Codename 47
 * Recursively finding 1D Peak.
 */
public class _1DPeakFinding {

	private static Scanner sc = new Scanner(System.in);
	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		int size =sc.nextInt();
		if(size<=0) 
		{
		System.out.println("Invalid size");
		System.exit(1);
		}
		int arr[] =new int[size];
		for(int i=0;i<size;i++)
			arr[i]=sc.nextInt();
		System.out.println("Peak found at "+(new _1DPeakFinding().reccfindPeak(arr,0,size-1)+1));
		System.out.println("Peak found at "+(new _1DPeakFinding().iterfindPeak(arr,0,size-1)+1));	
	}
	
	private int reccfindPeak(int a[],int low,int high)
	{
		if(low==high)//base case when number of elements in subproblem is 1
			return low;
		
		int mid = (low+high)/2;
		if(mid==low)//base case when number of elements in subproblem is 2
		{
			return a[low]>=a[high]?low:high;
		}
		else
		{
		 //Middle has atleast one element on both sides
			if(a[mid]<=a[mid+1] && a[mid]>=a[mid-1])
				return mid;
			else{				
				if(a[mid]<a[mid+1]) {return reccfindPeak(a, mid+1, high); }
				else { return reccfindPeak(a,low,mid-1);}
			}
				
		}
				
	}

	private int iterfindPeak(int a[],int low, int high)
	{
		if(low==high) return low;
		int peak=low;
		if(a[peak]>=a[peak+1])
			return peak;
		
		for(peak=low+1;peak<high;peak++)
		{
			if((a[peak-1]<=a[peak]) &&(a[peak]>=a[peak+1]))
				return peak;				
		}		
		return high;
	}
}
