/**
 * 
 */
package foss.freak.twoD;

import java.util.Scanner;

/**
 * @author Codename 47
 *
 */
public class _2DPeakFinding {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter number of Rows and Columns.");
		int numRows = sc.nextInt();
		int numColumns = sc.nextInt();
		int arr[][]=new int[numRows][numColumns];
		for(int i=0;i<numRows;i++)
			for(int j=0;j<numColumns;j++)
			{
				arr[i][j]=i+j/*sc.nextInt()*/;
			}
		System.out.println(new _2DPeakFinding().iterativePeakFinding(arr, 0, numRows-1, 0, numColumns-1));
		System.out.println(new _2DPeakFinding().recurPeakFinding(arr, 0, numRows-1, 0, numColumns-1));
		
	}
	
	private int iterativePeakFinding(int a[][],int rl,int rh,int cl,int ch)
	{		
		for(int i=rl;i<=rh;i++)
		{
			for(int j=cl;j<=ch;j++)
			{				
				if((i==rl || a[i][j]>=a[i-1][j]) && (i==rh || a[i][j]>=a[i+1][j]) && (j==cl || a[i][j]>=a[i][j-1]) && (j==ch || a[i][j]>=a[i][j+1]))
				    return a[i][j];				
			}
		}
		return 0;
	}

	private int recurPeakFinding(int a[][], int rl ,int rh, int cl, int ch)
	{
		if(cl==ch)
			return a[getMaxEleColIndex(a,cl,rl,rh)][cl];
		else if(ch==(cl+1))
		{
			int maxLowerVal = a[getMaxEleColIndex(a,cl,rl,rh)][cl];
			int maxHigherVal = a[getMaxEleColIndex(a,ch,rl,rh)][ch];
			return maxHigherVal>=maxLowerVal?maxHigherVal:maxLowerVal;
		}
		else
		{
			int midCol = (cl+ch)/2;
			int maxRowIndex = getMaxEleColIndex(a, midCol, rl, rh);
			
			if((a[maxRowIndex][midCol]>= a[maxRowIndex][midCol-1])&&(a[maxRowIndex][midCol] >= a[maxRowIndex][midCol+1]))
				return a[maxRowIndex][midCol];
			else if(a[maxRowIndex][midCol] <= a[maxRowIndex][midCol-1])
					return recurPeakFinding(a, rl, rh, cl, midCol);
				 else 
					return recurPeakFinding(a, rl, rh, midCol, ch);
		}		
	}

	private int getMaxEleColIndex(int[][] a, int colNum, int rl, int rh) {
		int maxIndex=rl;
		for(int i=maxIndex;i<=rh;i++)
			maxIndex = a[i][colNum]>a[maxIndex][colNum]?i:maxIndex;
		return maxIndex;		
	}

	
}
