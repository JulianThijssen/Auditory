package com.auditory.util;

import java.util.Iterator;

import org.lwjgl.util.vector.Vector3f;

import com.auditory.Entity;
import com.auditory.components.Transform;

public class Map<V> implements Iterable<V> {
	Node nodes = null;
	
	public void add(int key, V value) {
		if(nodes == null) {
			nodes = new Node(key, value);
			return;
		}
		Node n = nodes;
		while(n.next != null) {
			n = n.next;
		}
		n.next = new Node(key, value);
	}
	
	public V get(int key) {
		Node n = nodes;
		while(n != null) {
			if(key == n.key) {
				return n.value;
			}
			n = n.next;
		}
		return null;
	}
	
	public V get(Entity e) {
		Node n = nodes;
		while(n != null) {
			if(e.id == n.key) {
				return n.value;
			}
			n = n.next;
		}
		return null;
	}
	
	public int size() {
		Node n = nodes;
		int size = 0;
		while(n != null) {
			size++;
			n = n.next;
		}
		return size;
	}
	
	private class Node {
		int key;
		V value;
		
		Node next = null;
		
		public Node(int key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	@Override
	public Iterator<V> iterator() {
		return new MapIterator();
	}
	
	private class MapIterator implements Iterator<V> {
		Node current = null;
		
		public MapIterator() {
			current = nodes;
		}
		
	    public boolean hasNext() {
	    	if(current != null) {
	    		return true;
	    	}
	    	return false;
	    }

	    public V next() {
	    	V value = current.value;
	        current = current.next;
	        return value;
	    }

	    public void remove() {
	        //implement... if supported.
	    }
	}
	
	public static void main(String[] args) {
		Map<Transform> map = new Map<Transform>();
		
		Transform t1 = new Transform(1, new Vector3f(0, 0, 0));
		Transform t2 = new Transform(2, new Vector3f(1, 2, 3));
		Transform t3 = new Transform(3, new Vector3f(2, 4, 6));
		Transform t4 = new Transform(4, new Vector3f(3, 6, 9));
		
		map.add(t1.id, t1);
		map.add(t2.id, t2);
		map.add(t3.id, t3);
		map.add(t4.id, t4);
		
		for(Transform t: map) {
			Log.debug(t.position.toString());
		}
	}
}
