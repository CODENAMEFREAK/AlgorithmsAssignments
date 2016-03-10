/**
 * 
 */
package foss.freak.bst;

import java.util.Scanner;

/**
 * @author Codename 47
 *
 */
public class MainTesterBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int inputInitialTree[] = {23,21,22,10,6,18,20,19,15,17,16,13,12,14,25,24,30,28,40,50,45,60,39,38,36,37};
		//Node root = BSTOperations.insertNode(inputInitialTree[0],null);
		/*for(int i=1;i<=25;i++)
			 BSTOperations.insertNode(inputInitialTree[i],root);*/
		Node root = BSTOperations.insertNode(15, null);
		BSTOperations.insertNode(16, root);
		 BSTOperations.insertNode(17, root);
		 BSTOperations.insertNode(10, root);
		 BSTOperations.insertNode(13, root);
		 BSTOperations.insertNode(12, root);
		BSTOperations.insertNode(14, root);
		BSTOperations.insertNode(6, root);
		System.out.println(BSTOperations.getMaximum(root).getKey());
		
		System.out.println(BSTOperations.getMinimum(root).getKey());
		//showBST(root);
		System.out.println("\n");
		//BSTOperations.deleteNode(root, 12);
		//BSTOperations.deleteNode(root, 39);
		//BSTOperations.deleteNode(root, 23);
		root = BSTOperations.deleteNode(root, 15);
		//System.out.println(BSTOperations.successor(BSTOperations.searchNode(13, root)).getKey());
		//System.out.println(BSTOperations.successor(BSTOperations.searchNode(14, root)).getKey());
		//System.out.println(BSTOperations.successor(BSTOperations.searchNode(60, root)).getKey());
		//System.out.println(BSTOperations.successor(BSTOperations.searchNode(20, root)).getKey());
		//System.out.println(BSTOperations.successor(BSTOperations.searchNode(22, root)).getKey());
		//System.out.println(BSTOperations.predecessor(BSTOperations.searchNode(13, root)).getKey());
		//System.out.println(BSTOperations.predecessor(BSTOperations.searchNode(14, root)).getKey());
		//System.out.println(BSTOperations.predecessor(BSTOperations.searchNode(60, root)).getKey());
		//System.out.println(BSTOperations.predecessor(BSTOperations.searchNode(20, root)).getKey());
		//System.out.println(BSTOperations.predecessor(BSTOperations.searchNode(22, root)).getKey());
		showBST(root);
		
	}
	private static void showBST(Node root)
	{
		BSTOperations.inorderTraversalRecursive(root);
		System.out.println("\n");
		BSTOperations.preorderTraversalRecursive(root);
	}

}
