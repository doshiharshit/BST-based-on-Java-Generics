package Driver;

import com.tree.nodes.Node;
import com.tree.search.binary.BST;

public class Driver {

	public static void main(String[] args) {
		
		BST<Integer> binarySearchTree = new BST<Integer>();
		binarySearchTree.insert(10);
		binarySearchTree.insert(0);
		binarySearchTree.insert(1);
		binarySearchTree.insert(115);
		binarySearchTree.insert(112);
		binarySearchTree.insert(10);
		binarySearchTree.insert(10);
		
		binarySearchTree.display(binarySearchTree.getRoot());
		System.out.println();
		binarySearchTree.delete(10);
		binarySearchTree.display(binarySearchTree.getRoot());
		System.out.println();
		binarySearchTree.delete(0);
		binarySearchTree.display(binarySearchTree.getRoot());
		System.out.println();
		binarySearchTree.delete(115);
		binarySearchTree.display(binarySearchTree.getRoot());
		System.out.println();
		binarySearchTree.delete(112);
		binarySearchTree.display(binarySearchTree.getRoot());
		System.out.println();
		binarySearchTree.delete(1);
		binarySearchTree.display(binarySearchTree.getRoot());
		System.out.println();
		System.out.println(IsValidBST(binarySearchTree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
	
	/** Test function **/
	public static Boolean IsValidBST(Node<Integer> node, int MIN, int MAX) 
	{
	     if(node == (null))
	         return true;
	     if(node.getData().compareTo(MIN) > 0
	         && node.getData().compareTo(MAX) < 0
	         && IsValidBST(node.getLeft(), MIN, node.getData().getT())
	         && IsValidBST(node.getRight(), node.getData().getT(), MAX))
	         return true;
	     else 
	         return false;
	}

}
