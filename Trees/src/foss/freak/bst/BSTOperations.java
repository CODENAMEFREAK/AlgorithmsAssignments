/**
 * 
 */
package foss.freak.bst;

/**
 * @author Codename 47
 *
 */
public class BSTOperations {
	
	public static Node insertNode(int key, Node root)
	{
		if(root == null)
		{
			root  = new Node(key);
			return root;
		}
		else
		{
			Node parent = searchNode(key,root);
			if(parent.getKey() == key)
			{
				return parent;
			}
			else
			{
				Node newNode = new Node(key);
				newNode.setParent(parent);
				if(key > parent.getKey())
				{
					parent.setRightChild(newNode);
				}
				else{
					parent.setLeftChild(newNode);
				}
				return newNode;
			}
		}		
	}

	/**
	 * @return node where the key is found or the last node where the search terminates.
	 * */
	public static Node searchNode(int key, Node root) {
		if(root == null) return null;		
		Node tempNode = root;
		while((key>tempNode.getKey() && tempNode.getRightChild()!=null) || (key<tempNode.getKey() && tempNode.getLeftChild()!=null))
		{
			if(key>tempNode.getKey())
				tempNode = tempNode.getRightChild();
			else
				tempNode = tempNode.getLeftChild();
		}		
		return tempNode;
	}
	
	public static Node getMinimum(Node root)
	{
		if(root != null)
		{
			while(root.getLeftChild()!=null)
				root = root.getLeftChild();
		}
		return root;
	}
	public static Node getMaximum(Node root)
	{
		if(root != null)
		{
			while(root.getRightChild()!=null)
				root = root.getRightChild();
		}
		return root;
	}
	
	public static Node successor(Node node)
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
	
	public static Node predecessor(Node node)
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
	
	/**
	 * Returns the root of the modified tree
	 * */
	public static Node deleteNode(Node root , int key)
	{
		if(root ==null) return null;
		
		Node node = searchNode(key,root);
		if(node.getKey() !=key)
			return root;
		else
		{
			if(node.getLeftChild()==null && node.getRightChild()==null)//both childs are null
			{
				if(node.getParent()==null)
					return null;//i.e. last node deleted
				else
				{
				if(node.getParent().getKey()>node.getKey())
					node.getParent().setLeftChild(null);
				else
					node.getParent().setRightChild(null);
				return getRoot(node.getParent());//Root of modified tree is returned
				}
			}
			else if(node.getLeftChild()==null || node.getRightChild()==null)//exactly one child is null
			{
				if(node.getParent() ==null)
				{
					if(node.getLeftChild() != null)
					{
						node.getLeftChild().setParent(null);
						return node.getLeftChild();
					}
					else
					{
						node.getRightChild().setParent(null);
						return node.getRightChild();
					}
					
				}
				else{
					if(node.getParent().getKey()>node.getKey())
						{
							node.getParent().setLeftChild(  (node.getLeftChild()==null)?node.getRightChild():node.getLeftChild() );				
						}
					else{
							node.getParent().setRightChild(  (node.getLeftChild()==null)?node.getRightChild():node.getLeftChild() );					
						}
					return getRoot(node.getParent());
						}
			}
			else //both child not null
			{
				Node successorNode =successor(node);
				node.setKey(successorNode.getKey());
				successorNode.setKey(key);
				Node rightSubTreeRoot =node.getRightChild();
				rightSubTreeRoot.setParent(null);//detaching left subtree
				rightSubTreeRoot = deleteNode(rightSubTreeRoot,key);
				rightSubTreeRoot.setParent(node);
				return getRoot(node);//Node is returned				
			}			
		}		
	}
	
	public static void inorderTraversalRecursive(Node node)
	{
		if(node == null)
			return;
		else{
			inorderTraversalRecursive(node.getLeftChild());
			System.out.print(node.getKey()+"\t");
			inorderTraversalRecursive(node.getRightChild());
		}
	}
	
	public static void preorderTraversalRecursive(Node node)
	{
		if(node == null)
			return;
		else{
			System.out.print(node.getKey()+"\t");
			preorderTraversalRecursive(node.getLeftChild());			
			preorderTraversalRecursive(node.getRightChild());
		}
	}
	
	public static void postorderTraversalRecursive(Node node)
	{
		if(node == null)
			return;
		else{
			postorderTraversalRecursive(node.getLeftChild());			
			postorderTraversalRecursive(node.getRightChild());
			System.out.print(node.getKey()+"\t");
		}
	}
	
	private static Node getRoot(Node node)
	{
		if(node!=null)
		{
			while(node.getParent()!=null)
				node=node.getParent();
		}
		return node;
	}
}
