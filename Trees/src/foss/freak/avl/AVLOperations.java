/**
 * 
 */
package foss.freak.avl;


/**
 * @author Codename 47
 *
 */
public class AVLOperations {
	
	private static boolean keyFound =false; 
	public static AVLNode insertNode(int key,AVLNode root)
	{
		
		if(root == null)
		{
			root  = new AVLNode(key);
		}
		else
		{
			insertAndBalance(root , key);
		}		
		
		{//To fix the root 
		while(root.getParent()!=null)
			root=root.getParent();
		}
		return root;
	}
	private static void insertAndBalance(AVLNode node, int key) {
		if(node.getKey()==key)
			return;
		else
		{
			if(node.getKey() > key)
			{	if(node.getLeftChild()==null)
			    {
					AVLNode newNode = new AVLNode(key);
					newNode.setParent(node);
					node.setLeftChild(newNode);
					node.evaluateHeight();
					return;
			    }
			  else{
				insertAndBalance(node.getLeftChild(), key);
			  }
			}
			else
			{
				if(node.getRightChild()==null)
			    {
					AVLNode newNode = new AVLNode(key);
					newNode.setParent(node);
					node.setRightChild(newNode);
					node.evaluateHeight();
					return;
			    }
				else
				{
				 insertAndBalance(node.getRightChild(),key);
				}
			}			
		}
		node.evaluateHeight();//First let the new height to be evaluated
		balanceNode(node,key);
		return;		
	}
	
	public static AVLNode deleteNode(int key,AVLNode root)
	{
		if(root != null)
		{			
			root =  deleteAndBalance(root,key);
			if(root == null)
				return root;
			while(root.getParent()!=null)
				{
				root =root.getParent();
				}
		}
		return root;
	}
	
	private static AVLNode deleteAndBalance(AVLNode node, int key) {		
		if(node.getKey()==key)
		{
			keyFound =true;
			/**
			 * Three cases for delete
			 * */			
			if((node.getLeftChild() == null) && (node.getRightChild() == null))
			{
				/**
				 * Both childs are null
				 * */
				if(node.getParent()==null)
					return null;
				if(node.getParent().getKey() > key) //i.e. node on left of parent
					{
					node.getParent().setLeftChild(null);
					}
				else
					{
					node.getParent().setRightChild(null);
					}				
				return  node.getParent();
			}
			else if(node.getLeftChild() == null || node.getRightChild()==null)
			{
				/**
				 * Exactly one of child is null
				 * */
				if(node.getParent()==null)
				{
					if(node.getLeftChild()!=null)
						{
						node.getLeftChild().setParent(null);
						node = node.getLeftChild();
						}
					else
					{					
						node.getRightChild().setParent(null);
						node = node.getRightChild();							
					}
				
				}
				else{
				if(node.getParent().getKey() > node.getKey())
				{
					node.getParent().setLeftChild( (node.getLeftChild()==null)? (node.getRightChild()): (node.getLeftChild()) );
				}
				else
				{
					node.getParent().setRightChild( (node.getLeftChild()==null)? (node.getRightChild()): (node.getLeftChild()) );
				}
				}
				return node;
			}
			else
			{
				/**
				 * Both are non null
				 * */
				AVLNode successorNode  = successor(node);
				node.setKey(successorNode.getKey());
				successorNode.setKey(key);
				/**
				 * Detach right subtree
				 * */
				AVLNode rightSubtreeRoot = node.getRightChild();
				rightSubtreeRoot.setParent(null);
				rightSubtreeRoot =deleteAndBalance(rightSubtreeRoot, key);
				if(rightSubtreeRoot!=null){
					while(rightSubtreeRoot.getParent()!=null)
						rightSubtreeRoot = rightSubtreeRoot.getParent();
				}
				rightSubtreeRoot.setParent(node);
				node.setRightChild(rightSubtreeRoot);
				node.evaluateHeight();
				balanceNode_Delete(node);
			}
			
		}
		else
		{
			if(node.getKey() > key)
			{
				if(node.getLeftChild()==null)
					keyFound= false;
				else
					 deleteAndBalance(node.getLeftChild(), key);
			}
			else
			{
				if(node.getRightChild()==null)
					keyFound = false;
				else
					 deleteAndBalance(node.getRightChild(), key);
			}
			if(keyFound)
			{
				/*
				 * If keyFound update and rotate height if necessary
				 * */
				node.evaluateHeight();
				balanceNode_Delete(node);
				return node;
			}	
			
		}
		return node;
	}
	private static void balanceNode_Delete(AVLNode node) {
		int balanceFactor = computeBalanceFactor(node);
		if(balanceFactor ==0 || balanceFactor==-1 || balanceFactor==+1)
			return;
		else{
			int rotationDeterminer1 = (balanceFactor>0)?-1:1;
			int rotationDeterminer2 =0;
			switch(rotationDeterminer1)
			{
			case -1:
			{
				AVLNode child = node.getLeftChild();
				if(child.getLeftChild()==null)
				{
					rotationDeterminer2 = 1;					
				}
				else if(child.getRightChild() == null)
				{
					rotationDeterminer2 = -1;
				}		
				else{
					rotationDeterminer2 = (child.getLeftChild().getHeight()<child.getRightChild().getHeight())?1:-1;
				}
				break;				
			}
			case 1:
			{
				AVLNode child = node.getRightChild();
				if(child.getLeftChild()==null)
				{
					rotationDeterminer2 = 1;					
				}
				else if(child.getRightChild() == null)
				{
					rotationDeterminer2 = -1;
				}		
				else{
					rotationDeterminer2 = (child.getLeftChild().getHeight()<child.getRightChild().getHeight())?1:-1;
				}
				break;
			}
		}
		
		switch(rotationDeterminer1+rotationDeterminer2)
		{
		case 2:{
			System.out.println("\n Right-Right heavy at node "+node.getKey()+" ----Doing 1 left Rotation.");
			leftRotate(node);
			break;			
		}
		case -2:{
			System.out.println("\n Left-Left heavy at node "+node.getKey()+" ----Doing 1 right Rotation.");
			rightRotate(node);					
			break;
		}
		case 0:
		{
			if(rotationDeterminer1==-1)
			{
				//Left - Right heavy
				System.out.println("\n Left-Right heavy at node "+node.getKey()+" ----Doing 1 left and 1 Right Rotation.");
				leftRotate(node.getLeftChild());
				rightRotate(node);
			}
			else{
				//Right - Left heavy
				System.out.println("\n Right-Left heavy at node "+node.getKey()+" ----Doing 1 Right and 1 Left Rotation.");
				rightRotate(node.getRightChild());
				leftRotate(node);				
			}
			break;
		}		
		}			
		}
	}
	private static void balanceNode(AVLNode node, int key) {
		
		int balanceFactor = computeBalanceFactor(node);
		if(balanceFactor==-1 || balanceFactor==0 || balanceFactor==1)
			return;
		else{
			/*
			 *Rotation
			 * */
			int rotationDeterminer = 0;
			if(key<node.getKey())
				{
					rotationDeterminer=-1;
					if(key < node.getLeftChild().getKey())
						rotationDeterminer -= 1;
					else
						rotationDeterminer += 1;
				}
			else
				{
					rotationDeterminer=1;
					if(key < node.getRightChild().getKey())
						rotationDeterminer -= 1;
					else
						rotationDeterminer += 1;
				}
			
			switch(rotationDeterminer)
			{
				case -2:
				{
					System.out.println("\n Left-Left heavy at node "+node.getKey()+" ----Doing 1 right Rotation.");
					rightRotate(node);					
					break;
				}
				case 2:
				{
					System.out.println("\n Right-Right heavy at node "+node.getKey()+" ----Doing 1 left Rotation.");
					leftRotate(node);
					break;
				}
				case 0:
				{
					if(key<node.getKey() && key>node.getLeftChild().getKey())
					{
						System.out.println("\n Left-Right heavy at node "+node.getKey()+" ----Doing 1 left and 1 Right Rotation.");
						leftRotate(node.getLeftChild());
						rightRotate(node);
						
					}
					else
					{
						System.out.println("\n Right-Left heavy at node "+node.getKey()+" ----Doing 1 Right and 1 Left Rotation.");
						rightRotate(node.getRightChild());
						leftRotate(node);
					}
					break;
				}
			}
						
			
		}		
	}
	private static void leftRotate(AVLNode node) {
		AVLNode grandParent = node.getParent();
		if(grandParent!=null)
		{
			if(node.getKey() < grandParent.getKey())
			{
				grandParent.setLeftChild(node.getRightChild());
			}
			else
			{
				grandParent.setRightChild(node.getRightChild());
			}			
		}
		
		node.setParent(node.getRightChild());
		node.setRightChild(node.getParent().getLeftChild());			
		node.getParent().setLeftChild(node);
		if(node.getRightChild()!=null)
			node.getRightChild().setParent(node);
		node.getParent().setParent(grandParent);
		
		node.evaluateHeight();
		node.getParent().evaluateHeight();
	}
	private static void rightRotate(AVLNode node) {
		AVLNode grandParent = node.getParent();
		if(grandParent!=null)
		{
			if(node.getKey() < grandParent.getKey())
			{
				grandParent.setLeftChild(node.getLeftChild());
			}
			else
			{
				grandParent.setRightChild(node.getLeftChild());
			}
		}
		
		node.setParent(node.getLeftChild());
		node.setLeftChild(node.getParent().getRightChild());
		node.getParent().setRightChild(node);		
		if(node.getLeftChild()!=null)
			node.getLeftChild().setParent(node);				
		node.getParent().setParent(grandParent);		
		node.evaluateHeight();
		node.getParent().evaluateHeight();
	}
	public static AVLNode searchNode(int key, AVLNode node)
	{
		if(node != null)
		{	
			if(node.getKey() == key) return node;
			if((node.getKey() > key) && (node.getLeftChild()!=null)) return searchNode(key,node.getLeftChild());
			if((node.getKey() < key) && (node.getRightChild()!=null)) return searchNode(key,node.getRightChild());
			return node;				
		}
		return null;
	}

	public static AVLNode getMinimum(AVLNode node)
	{
		if(node != null){
		while(node.getLeftChild()!=null)
			node = node.getLeftChild();
		}
		return node;
	}
	
	public static AVLNode getMaximum(AVLNode node)
	{
		if(node != null){
			while(node.getRightChild()!=null)
				node = node.getRightChild();
			}
			return node;		
	}
	
	public static AVLNode successor(AVLNode node)
	{
		if(node !=null)
		{		
			if(node.getRightChild()!=null)
			{
				return getMinimum(node.getRightChild());
			}
			else if(node.getParent()==null)
					{
					return null;
					}
			else if(node.getParent().getKey() > node.getKey())
			      {
							return node.getParent();
			       }
			else 
			      {
					while(node.getParent().getKey()< node.getKey())
					{
						if(node.getParent().getParent() == null)
							return null;//i.e. hit the root 
						else
							node = node.getParent();
					}
						return node.getParent();								
				  }			
		}		
		return null;
	}
	public static AVLNode predecessor(AVLNode node)
	{
		if(node!=null)
		{
			if(node.getLeftChild()!=null)
			{
				return getMaximum(node.getLeftChild());
			}
			else if(node.getParent()==null)
			{
				return null;
			}
			else if(node.getParent().getKey() < node.getKey())
			{
				return node.getParent();
			}
			else
			{
				while(node.getParent().getKey()>node.getKey())
				{
					if(node.getParent().getParent()==null)
					{
						return null;
					}
					else
						node =node.getParent();
				}
				return node.getParent();
			}
		}
		return null;
	}
	
	
	public static void inorderTraversalRecursive(AVLNode node)
	{

		if(node == null)
			return;
		else{
			inorderTraversalRecursive(node.getLeftChild());
			System.out.print(node.getKey()+":"+node.getHeight()+"\t");
			inorderTraversalRecursive(node.getRightChild());
		}
		
	}
	public static void preorderTraversalRecursive(AVLNode node)
	{
		if(node == null)
			return;
		else{
			System.out.print(node.getKey()+"\t");
			preorderTraversalRecursive(node.getLeftChild());			
			preorderTraversalRecursive(node.getRightChild());
		}
	}
	public static void postorderTraversalRecursive(AVLNode node)
	{
		if(node == null)
			return;
		else{
			postorderTraversalRecursive(node.getLeftChild());			
			postorderTraversalRecursive(node.getRightChild());
			System.out.print(node.getKey()+"\t");
		}
	}
	
	private static int computeBalanceFactor(AVLNode node)
	{
		if(node != null)			
		{
			int hL =node.getLeftChild()!=null?node.getLeftChild().getHeight():-1;
			int hR = node.getRightChild()!=null?node.getRightChild().getHeight():-1;
			return hL-hR;
		}
		return -1;
	}
}
