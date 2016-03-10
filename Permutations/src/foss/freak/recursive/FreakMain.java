/**
 * 
 */
package foss.freak.recursive;

/**
 * @author Codename 47
 *
 */
public class FreakMain {

	/**
	 * @param args
	 */
	static int countPerm =0;
	public static void main(String[] args) {
		countPerm =0; 
		int n = Integer.valueOf(args[0]).intValue();	    
	    int input[] = new int[n];
	    int prefix[]=new int[0];
	    for(int i=0;i<n;i++)
	        input[i]=i+1;

	    genPerm(prefix,0,input,n);
	    return;
	}

	private static void genPerm(int[] prefix, int prefixSize, int[] perm, int permSize) {

	    if(permSize == 0)
	    {
	      printArr(prefix,prefixSize);
	      return;
	    }
	    for(int i=0;i<permSize;i++)
	    {
	        int nextPrefix[] = new int [prefixSize +1];

	        for(int j=0;j<prefixSize;j++)
	             nextPrefix[j] = prefix[j];

	         nextPrefix[prefixSize] = perm[i];

	         int nextPerm[] =new int [permSize-1] ;

	         for(int j=0; j<i;j++)
	         {
	             nextPerm[j] = perm[j];
	         }
	         for(int j=i+1;j<permSize;j++)
	                nextPerm[j-1] = perm[j];

	         genPerm(nextPrefix, prefixSize+1, nextPerm,permSize-1);
	    }
		
		
	}

	private static void printArr(int[] arr, int arrSize) {
		
	    System.out.println("Generating Permutation number: "+ (++countPerm));
	    for(int i=0;i<arrSize;i++)
	        System.out.print(arr[i] + "\t");
	     System.out.println("\n");		
	}

}
