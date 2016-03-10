/**
 * 
 */
package foss.freak.bst;

/**
 * @author Codename 47
 *
 */
public class Node {

	private int key;
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	
	
	public Node(int key)
	{
		this.key= key;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}	

}
