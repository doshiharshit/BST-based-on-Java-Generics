package Driver;

import com.tree.nodes.Node;
import com.tree.search.binary.BBST;

public class Driver {

	public static void main(String[] args) {
		
		BBST<Integer> binarySearchTree = new BBST<Integer>();
		binarySearchTree.insert(10);
		binarySearchTree.insert(20);
		binarySearchTree.insert(30);
		binarySearchTree.insert(40);
		binarySearchTree.insert(50);
		binarySearchTree.insert(25);
		binarySearchTree.insert(10);
		binarySearchTree.display();

		binarySearchTree.delete(10);
		binarySearchTree.display();
		binarySearchTree.delete(30);
		binarySearchTree.display();
		binarySearchTree.delete(50);
		binarySearchTree.display();
		binarySearchTree.delete(20);
		binarySearchTree.display();
		binarySearchTree.delete(25);
		binarySearchTree.display();
		binarySearchTree.delete(40);
		binarySearchTree.display();
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
