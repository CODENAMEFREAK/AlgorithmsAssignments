/**
 * 
 */
package foss.freak.avl;

import foss.freak.bst.BSTOperations;
import foss.freak.bst.Node;

/**
 * @author Codename 47
 *
 */
public class MainTesterAVL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*AVLNode root =new AVLNode(10);
		root = AVLOperations.insertNode(8, root);
		root = AVLOperations.insertNode(9, root);*/
		/*root = AVLOperations.insertNode(7, root);
		root = AVLOperations.insertNode(6, root);*/
		
		int inputInitialTree[] = {23,21,22,10,6,18,20,19,15,17,16,13,12,14,25,24,30,28,40,50,45,60,39,38,36,37};
		AVLNode root = AVLOperations.insertNode(inputInitialTree[0],null);
		for(int i=1;i<=25;i++){
			root = AVLOperations.insertNode(inputInitialTree[i],root);
			//AVLOperations.inorderTraversalRecursive(root);
			//System.out.println("\n");
			//AVLOperations.preorderTraversalRecursive(root);
			//System.out.println("\n");
		}
		System.out.println(AVLOperations.getMaximum(root).getKey());
		System.out.println(AVLOperations.getMinimum(root).getKey());
		System.out.println("\n");
		
		/*System.out.println(AVLOperations.successor(AVLOperations.searchNode(13, root)).getKey());
		System.out.println(AVLOperations.successor(AVLOperations.searchNode(14, root)).getKey());
		//System.out.println(AVLOperations.successor(AVLOperations.searchNode(60, root)).getKey());
		System.out.println(AVLOperations.successor(AVLOperations.searchNode(20, root)).getKey());
		System.out.println(AVLOperations.successor(AVLOperations.searchNode(22, root)).getKey());
		System.out.println(AVLOperations.predecessor(AVLOperations.searchNode(13, root)).getKey());
		System.out.println(AVLOperations.predecessor(AVLOperations.searchNode(14, root)).getKey());
		System.out.println(AVLOperations.predecessor(AVLOperations.searchNode(60, root)).getKey());
		System.out.println(AVLOperations.predecessor(AVLOperations.searchNode(20, root)).getKey());
		System.out.println(AVLOperations.predecessor(AVLOperations.searchNode(22, root)).getKey());
		*/
		
		AVLOperations.inorderTraversalRecursive(root);
		System.out.println("\n");
		AVLOperations.preorderTraversalRecursive(root);
		//root =AVLOperations.deleteNode(17, root);
		//root =AVLOperations.deleteNode(16, root);
		System.out.println("\n");
		//root =AVLOperations.deleteNode(18, root);
		//root =AVLOperations.deleteNode(15, root);
		AVLOperations.inorderTraversalRecursive(root);
		System.out.println("\n");
		AVLOperations.preorderTraversalRecursive(root);
	}

}
