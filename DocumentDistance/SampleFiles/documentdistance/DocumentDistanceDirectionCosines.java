/**
 * 
 */
package foss.freak.documentdistance;

import java.io.File;
import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Set;

/**
 * @author Codename 47
 *
 */
public class DocumentDistanceDirectionCosines {

	/**
	 * @param args
	 */
	private static BigDecimal ZERO = new BigDecimal(0);
	
	public static void main(String[] args) {
		
		File fp1 =new File("");
		File fp2 = new File("");
		DocumentVector v1= new DocumentVector(args);
		DocumentVector v2 = new DocumentVector(args);
		computeDotProduct(v1,v2);
	}

	private static double computeDotProduct(DocumentVector v1, DocumentVector v2) {
		
		Hashtable<String, BigDecimal> vector1 = v1.get_vector();
		Hashtable<String, BigDecimal> vector2 = v2.get_vector();
		BigDecimal dot = new BigDecimal(0);
		Set<String> keys = (vector1.size()<=vector2.size())?vector1.keySet():vector2.keySet();
		for(String str: keys)
		{
			dot.add(new BigDecimal(((vector1.get(str)!=null)?vector1.get(str):ZERO).add(((vector2.get(str)!=null)?vector2.get(str):ZERO)))); 
		}
		return 0L;
	}

}
