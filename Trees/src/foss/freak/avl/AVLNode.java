/**
 * 
 */
package foss.freak.avl;


/**
 * @author Codename 47
 *
 */
public class AVLNode {
	private int key;
	private AVLNode leftChild;
	private AVLNode rightChild;
	private AVLNode parent;
	private int height;
	
	
	public AVLNode(int key)
	{
		this.key = key;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
		evaluateHeight();
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public AVLNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(AVLNode leftChild) {
		this.leftChild = leftChild;
	}
	public AVLNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(AVLNode rightChild) {
		this.rightChild = rightChild;
	}
	public AVLNode getParent() {
		return parent;
	}
	public void setParent(AVLNode parent) {
		this.parent = parent;
	}	
	public int getHeight()
	{
		return height;
	}
	public void evaluateHeight()
	{
		/**
		 * Here nulls are refered as -1 so as to use common formula Height = max(leftHeight , rightHeight)+1
		 * */
		int leftHeight = (leftChild==null)?-1:leftChild.getHeight();
		int rightHeight = (rightChild==null)?-1:rightChild.getHeight();
		height =(((leftHeight>rightHeight)?leftHeight:rightHeight)+1);
	}	
}
