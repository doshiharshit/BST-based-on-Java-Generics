package com.tree.search.binary;

import java.lang.reflect.Array;

import com.tree.nodes.Node;

public class BST<T extends Comparable<T>> {
	private Node<T> root;
	
	public BST() {
		root = null;
	}
	
	public Node<T> getRoot(){
		return root;
	}
	
	public void insert(T data){
		Node<T> newNode = new Node<T>(data);
		if(root == null){
			root = newNode;
			return;
		}
		
		Node<T> current = root;
		Node<T> parent = null;
		while(true){
			parent = current;
			if(current.getData().compareTo(data) > 0){
				current = current.getLeft();
				if(current == null){
					parent.setLeft(newNode);
					return;
				}
			}else if(current.getData().compareTo(data) < 0){
				current = current.getRight();
				if(current == null){
					parent.setRight(newNode);
					return;
				}
			}else{
				current.getData().incrementCount();
				return;
			}
		}
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
	
	public boolean delete(T id){
		Node<T>[] current = this.find(id);
		if(current == null){
			return false;
		}
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current[0].getLeft() == null && current[0].getRight() == null){
			if(current[0].equals(root)){
				root = null;
			}
			if(current[1].getLeft() != null && current[1].getLeft().equals(current[0])){
				current[1].setLeft(null);
			}else{
				current[1].setRight(null);
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current[0].getRight() == null){
			if(current[0].equals(root)){
				root = current[0].getLeft();
			}else if(current[1].getLeft() != null && current[1].getLeft().equals(current[0])){
				current[1].setLeft(current[0].getLeft());
			}else{
				current[1].setRight(current[0].getLeft());
			}
		}
		else if(current[0].getLeft() == null){
			if(current[0].equals(root)){
				root = current[0].getRight();
			}else if(current[1].getLeft() != null && current[1].getLeft().equals(current[0])){
				current[1].setLeft(current[0].getRight());
			}else{
				current[1].setRight(current[0].getRight());
			}
		}else if(current[0].getLeft() != null && current[0].getRight() != null){
			
			//now we have found the minimum element in the right sub tree
			Node<T> successor = getSuccessor(current[0]);
			if(current[0].equals(root)){
				root = successor;
			}else if(current[1].getLeft().equals(current[0])){
				current[1].setLeft(successor);
			}else{
				current[1].setRight(successor);
			}
			successor.setLeft(current[0].getLeft());
		}
		return true;		
	}
	
	public Node<T> getSuccessor(Node<T> deleleNode){
		Node<T> successsor = null;
		Node<T> successsorParent = null;
		Node<T> current = deleleNode.getRight();
		while(current != null){
			successsorParent = successsor;
			successsor = current;
			current = current.getLeft();
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
		// successsorParent
		if(successsor!=deleleNode.getRight()){
			successsorParent.setLeft(successsor.getRight());
			successsor.setRight(deleleNode.getRight());
		}
		return successsor;
	}
	
	public void display(Node<T> root){
		if(root != null){
			display(root.getLeft());
			System.out.print((root.getLeft() == null ? "" : " <- ") + root.getData().getT() + ":" + root.getData().getCount() + (root.getRight() == null ? "" : " -> "));
			display(root.getRight());
		}
	}
}
