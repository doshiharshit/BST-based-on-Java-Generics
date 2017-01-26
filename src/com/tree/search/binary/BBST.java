package com.tree.search.binary;

import java.lang.reflect.Array;

import com.tree.nodes.Node;

public class BBST<T extends Comparable<T>> {
	private Node<T> root;
	
	public BBST() {
		root = null;
	}
	
	public Node<T> getRoot(){
		return root;
	}
	
	public int getHeightDifference(Node<T> node){
		if(node != null){
			return getHeight(node.getLeft()) - getHeight(node.getRight());
		}
		return 0;
	}
	
	public int getHeight(Node<T> node){
		if(node == null){
			return 0;
		}
		return node.getHeight();
	}
	
	public Node<T> leftRotate(Node<T> node){
		Node<T> tmp1 = node.getRight();
		Node<T> tmp2 = tmp1.getLeft();
		
		tmp1.setLeft(node);
		node.setRight(tmp2);
		
		node.setHeight( Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1 );
		tmp1.setHeight( Math.max(getHeight(tmp1.getLeft()), getHeight(node.getRight())) + 1 );
		
		return tmp1;
	}
	
	public Node<T> rightRotate(Node<T> node){
		Node<T> tmp1 = node.getLeft();
		Node<T> tmp2 = tmp1.getRight();
		
		tmp1.setRight(node);
		node.setLeft(tmp2);
		
		node.setHeight( Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1 );
		tmp1.setHeight( Math.max(getHeight(tmp1.getLeft()), getHeight(node.getRight())) + 1 );
		
		return tmp1;
	}
	
	public void insert(T data) {
		root = this.insert(root, data);
	}
	
	public Node<T> insert(Node<T> node, T data){
		if(node == null){
			return new Node<T>(data);
		}
		
		if(node.getData().compareTo(data) > 0){
			node.setLeft( insert(node.getLeft(), data) );
		}else if(node.getData().compareTo(data) < 0){
			node.setRight( insert(node.getRight(), data) );
		}else{
			node.getData().incrementCount();
			return node;
		}
		
		node.setHeight( Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1 );
		
		int heightDiff = getHeightDifference(node);
		
		/** Left Left **/
		if(heightDiff > 1 && node.getLeft().getData().compareTo(data) > 0 ){
			return rightRotate(node);
		}
		
		/** Right Right **/
		if(heightDiff < -1 && node.getRight().getData().compareTo(data) < 0 ){
			return leftRotate(node);
		}
		
		/** Left Right **/
		if(heightDiff > 1 && node.getLeft().getData().compareTo(data) > 0 ){
			node.setLeft( leftRotate(node.getLeft()) );
			return rightRotate(node);
		}
		
		/** Right Left **/
		if(heightDiff < -1 && node.getRight().getData().compareTo(data) < 0 ){
			node.setRight( rightRotate(node.getRight()) );
			return leftRotate(node);
		}
		
		return node;
	}
	
	public Node<T>[] find(T id){
		Node<T> curr = this.root;
		Node<T> pare = this.root;
		@SuppressWarnings("unchecked")
		Node<T>[] current = (Node<T>[]) Array.newInstance(Node.class, 2);
		while(!curr.getData().getT().equals(id)){
			pare = curr;
			if(curr.getData().getT().compareTo(id) > 0){
				curr = curr.getLeft();
			}else{
				curr = curr.getRight();
			}
			if (curr == null) {
				return null;
			}
		}
		current[0] = curr;
		current[1] = pare;
		return current;
	}
	
	public void delete(T id){
		root = delete(root, id);
	}
	
	public Node<T> delete(Node<T> node, T id){
		if(node == null)
            return node;
		
		if( node.getData().getT().compareTo(id) > 0 ){
            node.setLeft( delete(node.getLeft(), id) );
		}else if( node.getData().getT().compareTo(id) < 0 ){
			node.setRight( delete(node.getRight(), id) );
		}else{
			if(node.getData().getCount() <= 1){	            
				if ((node.getLeft() == null) || (node.getRight() == null))
	            {
	                Node<T> temp = null;
	                if (node.getLeft() == null) {
	                    temp = node.getRight();
	                } else {
	                    temp = node.getLeft();
	                }
	 
	                // No child case
	                if (temp == null) {
	                    node = null;
	                }else{   // One child case
	                    node = temp; // Copy the contents of
	                }                 // the non-empty child
	            }else{
	        		Node<T> temp = getSuccessor(node);
	            	node.setData(temp.getData());
	            	node.setRight( delete(node.getRight(), temp.getData().getT()) );
	            }
			}else{
        		node.getData().decrementCount();
        	}
		}
		
		if (node == null)
            return node;
		
		node.setHeight( Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1 );
		
		int heightDiff = getHeightDifference(node);
		if(heightDiff > 1 && getHeightDifference(node.getLeft()) >= 0){
			return rightRotate(node);
		}
		
		if(heightDiff > 1 && getHeightDifference(node.getLeft()) < 0){
			node.setLeft( leftRotate(node.getLeft()) );
			return rightRotate(node);
		}
		
		if(heightDiff < -1 && getHeightDifference(node.getRight()) <= 0){
			return leftRotate(node);
		}
		
		if(heightDiff < -1 && getHeightDifference(node.getRight()) > 0){
			node.setRight( rightRotate(node.getRight()) );
			return leftRotate(node);
		}
		
		return node;
	}
	
	public Node<T> getSuccessor(Node<T> deleleNode){
		Node<T> current = deleleNode.getRight();
		 
        while (current.getLeft() != null)
           current = current.getLeft();
 
        return current;
	}
	
	public void display(){
		if(root != null){
			System.out.println("ROOT: " + root.getData().getT());
			display(root);
			System.out.println();
		}
	}
	
	public void display(Node<T> root){
		if(root != null){
			display(root.getLeft());
			System.out.print((root.getLeft() == null ? "" : " <- ") + root.getData().getT() + ":" + root.getData().getCount() + (root.getRight() == null ? "" : " -> "));
			display(root.getRight());
		}
	}
}