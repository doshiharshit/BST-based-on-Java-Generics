package com.tree.nodes;

import com.data.Data;

public class Node<T extends Comparable<T>>{
	private Node<T> left = null;
	private Node<T> right = null;
	private Data<T> data = null;
	private int height = 0;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Node(T dataIn) {
		setData(new Data<T>(dataIn));
		setHeight(1);
	}
	
	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public Data<T> getData() {
		return this.data;
	}

	public void setData(Data<T> data) {
		this.data = data;
	}
	/*
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if(obj != null){
			return this.getData().getT() == ((Node<T>) obj).getData().getT();
		}
		return false;
	}*/

	@Override
	public String toString() {
		return "Node [left=" + this.getLeft() + ", data=" + this.getData().getT() + ", right=" + this.getRight()  + "]";
	}

}
