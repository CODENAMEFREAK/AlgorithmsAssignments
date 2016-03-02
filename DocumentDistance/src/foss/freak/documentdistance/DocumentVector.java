/**
 * 
 */
package foss.freak.documentdistance;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * @author Codename 47
 *
 */
public class DocumentVector {
	
	private Hashtable<String, Integer> docVector = null;
	
	public Hashtable<String, Integer> getDocVector() {
		return docVector;
	}

	public double getVectorMagnitude() {
		return vectorMagnitude;
	}

	private double vectorMagnitude = 0L;
	
	public DocumentVector(File vectorFile)
	{
		docVector = new Hashtable<String, Integer>();
		defineVector(vectorFile, docVector);
		vectorMagnitude = computeMagnitude();
	}

	private double computeMagnitude() {
		long squares = 0L;
		Set<String> keys = docVector.keySet();
		int val =0;
		for(String str : keys)
		{
			val = docVector.get(str);
			squares+=(val*val);
		}
		return Math.sqrt(squares);		
	}

	private void defineVector(File vectorFile,
			Hashtable<String, Integer> docVector) {
		
		try {
			
			List<String> linesList = Files.readAllLines(vectorFile.toPath(), Charset.defaultCharset());
			for(String line : linesList)
			{
				addToVector(line , docVector);
			}
		} catch (IOException e) {
			docVector.put("", 0);
			e.printStackTrace();
		}	
		
	}

	private void addToVector(String line, Hashtable<String, Integer> docVector) {
		String arr[] = line.split(" ");
		for(String str : arr)
		{
			if(docVector.containsKey(str))
			{
				docVector.put(str, (docVector.get(str)+1));	
			}
			else
				docVector.put(str,  1);
		}
	}

}
