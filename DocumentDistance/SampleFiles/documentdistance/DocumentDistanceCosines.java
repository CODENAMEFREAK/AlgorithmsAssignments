/**
 * 
 */
package foss.freak.documentdistance;

import java.io.File;
import java.util.Hashtable;
import java.util.Set;

/**
 * @author Codename 47
 *
 */
public class DocumentDistanceCosines {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file1 =new File(".//SampleFiles//1_1.txt");
		File file2 =new File(".//SampleFiles//1_2.txt");
		
		DocumentVector v1 =new DocumentVector(file1);
		DocumentVector v2 =new DocumentVector(file2);
		
		double cosine = performDotProduct(v1,v2);
		System.out.println("Document Cosine ="+  cosine);
		

	}

	private static double performDotProduct(DocumentVector v1, DocumentVector v2) {
		
		long product =0L;
		Hashtable<String, Integer> smaller = (v1.getDocVector().size() <= v2.getDocVector().size() )?(v1.getDocVector()):(v2.getDocVector());
		Hashtable<String, Integer> larger = (v1.getDocVector().size() > v2.getDocVector().size() )?(v1.getDocVector()):(v2.getDocVector());
		
		Set<String> commonMinimum = smaller.keySet();
		for(String comKey : commonMinimum)
		{
			product+= (smaller.get(comKey) * larger.get(comKey));
		}
		
		return (product/(v1.getVectorMagnitude() * v2.getVectorMagnitude()));		
	}

}
