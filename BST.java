package project4;
import java.util.*;

/**
 * The BST<E> class is an implementation of a binary search tree. This implementation 
 * provides guaranteed O(H) (H is the height of this tree which could be as low as logN 
 * for balanced trees, but could be as large as N for unbalanced trees) time cost for 
 * the basic operations (add, remove and contains). This class implements many of the 
 * methods provided by the Java framework's TreeSet class. Note that in the documentations 
 * of this class's methods, M is the size of the collection. Some parts and concepts of this 
 * code were understood and adapted from popular websites like GeeksForGeeks, StackOverflow 
 * and other websites.
 * @author  Mofoluwake Adesanya
 * @version 11/29/2023
 */
public class BST<E extends Comparable<E>> extends Object implements Iterable<E>, Cloneable {
	public Node<E> root;
	private int height = -1;
	private int size = 0;
	/**
	 * Constructs a new, empty tree, sorted according to the natural 
	 * ordering of its elements. All elements inserted into the tree 
	 * must implement the Comparable interface. This operation is O(1).
	 */
	public BST() {
		root = null;
	}
	/**
	 * Constructs a new tree containing the elements in the specified collection, 
	 * sorted according to the natural ordering of its elements. All elements inserted into 
	 * the tree must implement the Comparable interface. This operation is O(N logN) where 
	 * N is the number of elements in the collection. This implies that this tree has a height
	 * that is approximately logN, not N.
	 * @param E[] collection whose elements will comprise this tree
	 * @throws NullPointerException if the specified collection is null
	 */
	public BST(E[] collection) throws NullPointerException {
		//Arrays.sort(collection);
		for (E each : collection ) {
			// add each element in collection to this tree
			this.add​(each);
		}
	}
	/**
	 * Adds the specified element to this tree if it is not already present. More formally, adds 
	 * the specified element e to this tree if the set contains no element e2 such that Objects.equals(e, e2).
	 * If this tree already contains the element, the tree is left unchanged and returns false i.e. this tree 
	 * does not permit duplicates. This operation is O(H).
	 * @param E e the element to be added to this set
	 * @return true if this tree did not already contain the specified element
	 * @throws NullPointerException if the specified element is null and this tree uses natural ordering, or 
	 * its comparator does not permit null elements.
	 */
	public boolean add​(E e) throws NullPointerException {
		// if e is null, throw exception
		if ( e == null )
			throw new NullPointerException( "This tree does not accept null elements." );
		Node<E> newNode = new Node<E> (e);   // new node to add
		// make e the root node if this tree is empty
		if ( this.isEmpty() ) {	
			root = newNode;
			size++;   // increase the size everytime a node is added
			return true; 
		}
		else {
			//always start from root
			Node<E> parent = null, current = root;
			while ( current != null ) {
				// keep track of parent
				parent = current;
				// if the current node is greater than or equal to e
				if ( current.data.compareTo(e) == 1 ) {
					current = current.left;	// move left
				} // if the current node is less than or equal to e
				else if ( current.data.compareTo(e) == -1 ) {
					current = current.right; // move right
				} // if e is already in this tree, return false
				else {
					return false;
				}
			} // once leaf node is found, check it 
			// if e is less than current
			if ( parent.data.compareTo(e) == 1 ) {
				parent.left = newNode;
				size++;   // increase the size everytime a node is added
				return true;
			} // if e is greater than current
			else if ( parent.data.compareTo(e) == -1 ) {
				parent.right = newNode;
				size++;   // increase the size everytime a node is added
				return true;
			}
		}
		return false;
	}
	/**
	 * Adds all of the elements in the specified collection to this tree. This operation is O(MH).
	 * @param collection containing elements to be added to this tree
	 * @return true if this tree changed as a result of the call
	 * @throws NullPointerException if the specified collection is null or if any element of the collection
	 * is null
	 */
	public boolean addAll​(Collection<? extends E> collection) throws NullPointerException {
		// if collection is null or has null elements
		if ( collection == null ) {
			throw new NullPointerException("Null Collection.");
		}
		Iterator<? extends E> iter = collection.iterator(); 
		while ( iter.hasNext() ) {
			// get next element 
			E next = iter.next();
			// this tree does not accept null elements
			if ( next == null )
				throw new NullPointerException("Collection contains null element(s).");
			// add each element in the collection to this tree
			this.add​(next);
		}
		return true;	
	}
	/** 
	 * Removes the specified element from this tree if it is present. More formally, removes an element e 
	 * such that Objects.equals(o, e), if this tree contains such an element. Returns true if this tree 
	 * contained the element (or equivalently, if this tree changed as a result of the call). (This tree 
	 * will not contain the element once the call returns.) This operation is O(H).
	 * @param Object o the object to be removed from this set, if present
	 * @return true if this tree contained the specified element
	 * @throws ClassCastException if the specified object cannot be compared with the elements currently 
	 * in this tree
 	 * @throws NullPointerException if the specified element is null
	 */
	public boolean remove​(Object o) throws ClassCastException, NullPointerException {
		// check if this tree is empty
		if ( this.isEmpty() )
			return false;
		// this tree does not have null elements so throw exception
		if ( o == null )
			throw new NullPointerException("No null elements here.");
		// only remove if o is in this tree
		Node<E> parent = null, current = root;
		// check if o is in this tree
		if ( !this.contains​(o) ) return false;
		// if it is, find it first and save its parent node
		while ( current != null && !current.data.equals(o) ) {
			parent = current; // parent is always saved
			// if current is greater, go left
			if ( current.data.compareTo((E) o) == 1 ) 
				current = current.left;
			// if current is smaller, go right
			else if ( current.data.compareTo((E) o) == -1 ) 
				current = current.right;
		} // remove o if it exist in this tree. we deal with 3 cases here:
		// case 1: o is a leaf node  
		if ( current.left == null && current.right == null ) {
			// if it is the root then we empty the tree
			if ( current == root ) 
			{	root = null; size--;}
			// otherwise 
			else {	
				// if node to delete is the right child of the parent
				if ( parent.right.equals(current) ) {
					parent.right = null; 
					size--;
					return true;
				}
				// if it is the left child of parent
				else {
					parent.left = null;
					size--;
					return true;
				}
			}
		} // case 2a : o has only one child (left) 
		else if ( current.right == null ) {
			// check if it is root first
			if ( current == root ) {
				root = current.left;
				size--;
				return true;
			} // check which child of the parent it is and replace 
			  // with left child of the node to be deleted
			else if ( current.equals(parent.left) ) {	
				parent.left = current.left;	
			} 
			else { parent.right = current.left; }
			size--;
			return true;
		} // case 2b : o has only one child (right)
		else if ( current.left == null ) {
			// check if it is root first
			if ( current == root ) {
				root = current.right;
				size--;
				return true;
			} // check which child of the parent it is and replace 
			  // with right child of the node to be deleted
			else if ( current.equals(parent.left) ) {	
				parent.left = current.right;	
			} 
			else { parent.right = current.right; }
			size--;
			return true;
		}		
		// case 3: o has 2 child nodes
		else {
			// get the left most node of o's right child 
			Node<E> pointer = current.right;
		    Node<E> replace = null, replaceParent = null;

		    while ( pointer != null ) {
		      replaceParent = replace;
		      replace = pointer;
		      pointer = pointer.left;
		    } // once the leftmost node of the right child is found 
		    if ( replace != current.right ) {
		      replaceParent.left = replace.right;
		      replace.right = current.right;
		    }
			//
			if ( current == root ) { root = replace; }
			// if it is the left child of its parent
			else if ( current.equals(parent.left) ) 
			{ parent.left = replace; }
			// if it is the right child of its parent
			else { parent.right = replace; }
			replace.left = current.left;
			size--;
			return true;
		}
		return false;
	}	
	/**
	 * Removes all of the elements from this tree. This tree will be empty after this call returns. 
	 * This operation is O(M).
	 */
	public void clear() {
		// if this is already empty, simply return
		if ( this.isEmpty() )
			return;
		// using remove() to remove each element in this tree inorder
		for ( Object o : this ) {
			remove​(o);
		}
		// then set the root and size to empty
		this.root = null; this.size = 0;
	}
	/**
	 * Returns true if this set contains the specified element. More formally, returns true if and only if 
	 * this set contains an element e such that Objects.equals(o, e). This operation is O(H).
	 * @param Object o the object to be checked for containment in this tree
	 * @return true if this tree contains the specified element
	 * @throws ClassCastException if the specified object cannot be compared with the elements currently 
	 * in the set
	 * @throws NullPointerException if the specified element is null and this tree uses natural ordering, 
	 * or its comparator does not permit null elements
	 */
	public boolean contains​(Object o) throws ClassCastException, NullPointerException {
		Node<E> current = root; // to traverse tree
		// if o is null, throw exception
		if ( o == null )
			throw new NullPointerException( "This tree does not have null elements." );
		// return false if this tree is empty
		if ( this.isEmpty() ) {	
			return false; 
		}
		else {
			while ( current != null ) {
				// if root is greater, go left
				if ( current.data.compareTo((E) o) == 1 ) {
					current = current.left;
				} // if root is smaller, go right
				else if ( current.data.compareTo((E) o) == -1 ) {
					current = current.right;
				} // when the node is found, return true
				else { return true; }
			}
		}
		return false;
	}
	/**
	 * Returns true if this collection contains all of the elements in the specified collection. This 
	 * implementation iterates over the specified collection, checking each element returned by the iterator 
	 * in turn to see if it is contained in this tree. If all elements are so contained true is returned, otherwise
	 * false. This operation is O(MH).
	 * @param Collection<?> c the collection to be checked for containment in this tree
	 * @return true if this tree contains all of the elements in the specified collection
	 * @throws NullPointerException if the specified collection contains one or more null elements and this 
	 * collection does not permit null elements, or if the specified collection is null.
	 */
	public boolean containsAll​(Collection<?> c) throws NullPointerException {
		// int checker - would be used to check if this tree contains all elements in c
		int checks = 0;
		// if collection is null or has null elements
		if ( c == null ) {
			throw new NullPointerException("Null Collection.");
		}
		Iterator<?> iter = c.iterator(); 
		while ( iter.hasNext() ) {
			// get next element 
			 E next = (E) iter.next();
			// this tree does not accept null elements
			if ( next == null )
				throw new NullPointerException("Collection contains null element(s).");
			// then check if it is here in this tree
			if ( this.contains​(next) )	checks++;
		}
		return checks == c.size();
	}
	/**
	 * Returns the number of elements in this tree. This operation is O(1).
	 * @return the number of elements in this tree
	 */
	public int size() {		
		// simply return size. it is updated in add() and remove() functions.
		return size;	}
	/**
	 * Returns true if this tree contains no elements. This operation is O(1).
	 * @return true if this tree contains no elements
	 */
	public boolean isEmpty() {
		// this tree is empty when the root node is null
		return root == null;
	}
	/**
	 * Returns the height of this tree. The height of a leaf is 1. The height of the tree is the height of its 
	 * root node. This operation is O(H).
	 * @return the height of this tree or zero if the tree is empty
	 */
	public int height() {
		// if this is empty 
		if ( this.isEmpty() )
			return height;
		// using level order traversal
		Queue<Node<E>> buffer = new LinkedList<> ();
		buffer.add(root); // level 0
		while ( ! buffer.isEmpty() ) {
			int countNodesAtLevel = buffer.size();
			// increment height at every leve
			height++;
			for ( int i=0; i < countNodesAtLevel; i++ ) {
				Node<E> store = buffer.poll();
				// traverse to the next level 
				if ( store.left != null )
					buffer.add(store.left);
				if ( store.right != null )
					buffer.add(store.right);
			}
		}
		return height;
	}
	/**
	 * Returns an iterator over the elements in this tree in ascending order (inorder traversal). This operation 
	 * is O(N).
	 * @return an iterator over the elements in this set in ascending order
	 */
	@Override
	public Iterator<E> iterator() {
		// return new inorder iterator 
		return new Iter<E>();
	}
	/**
	 * Returns an iterator over the elements in this tree in order of the preorder traversal.
	 * This operation is O(N).
	 * @return an iterator over the elements in this tree in order of the preorder traversal
	 */
	public Iterator<E> preOrderIterator() {
		// return new preorder iterator
		return new PreOrderIter<E> ();
	}
	/**
	 * Returns an iterator over the elements in this tree in order of the postorder traversal.
     * This operation is O(N).
     * @return an iterator over the elements in this tree in order of the postorder traversal
	 */
	public Iterator<E> postOrderIterator() {
		// return new postorder iterator
		return new PostOrderIter<E> ();
	}
	/**
	 * Returns the element at the specified position in this tree. The order of the indexed elements is 
	 * the same as provided by this tree's iterator. The indexing is zero based (i.e., the smallest element 
	 * in this tree is at index 0 and the largest one is at index size()-1). This operation is O(index).
	 * @param int index the index of the element to return
	 * @return the element at the specified position in this tree
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
	 */
	public E get​(int index) throws IndexOutOfBoundsException {
		// throw exception if index is greater than or equal to size or tree is empty
		if ( this.isEmpty() || index >= size )
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		//Node<E> current = root;
		int track = 0; // to track the index of nodes
		Queue<E> tree = new LinkedList<E> (); // to save nodes
		Iterator<E> iter = this.iterator();
		while ( iter.hasNext() ){
			if ( track == index ) {
				System.out.println();
				return iter.next();
			} 
			track++;
			// add that node to the queue so that iteration continues
			tree.add( iter.next() );
		} 
		return null;
	}
	/**
	 * Returns a collection whose elements range from fromElement, inclusive, to toElement, inclusive. The returned 
	 * collection/list is backed by this tree, so changes in the returned list are reflected in this tree, and 
	 * vice-versa (i.e., the two structures share elements. The returned collection should be organized according to 
	 * the natural ordering of the elements (i.e., it should be sorted). This operation is O(M).
	 * @param E fromElement the low endpoint (inclusive) of the returned collection
	 * @param E toElement the high endpoint (inclusive) of the returned collection
	 * @return a collection containing a portion of this tree whose elements range from fromElement, inclusive, 
	 * to toElement, inclusive
	 * @throws NullPointerException if fromElement or toElement is null
	 * @throws IllegalArgumentException if fromElement is greater than toElement
	 */
	public ArrayList<E> getRange​(E fromElement, E toElement) throws NullPointerException, IllegalArgumentException {
		// exception 1: if one part of the range or both is null
		if ( fromElement == null || toElement == null )
			throw new NullPointerException("Range is null!");
		// exception 2: if lower range is greater than higher range
		if ( fromElement.compareTo(toElement) == 1 )
			throw new IllegalArgumentException("Range is invalid!");
		// instantiate array
		ArrayList<E> array = new ArrayList<> ();
		for ( int i = 0; i<size; i++ ) {
			E element = this.get​(i);
			// add each element in the given range to the new ArrayList
			if ( (element.compareTo(fromElement) == 1 || element.equals(fromElement)) &&
					(element.compareTo(toElement) == -1 || element.equals(toElement)) )  
					 { array.add(this.get​(i)); }
		} // return the array
		return array;
	}
	/**
	 * Returns the least element in this tree greater than or equal to the given element, or null if there is no such 
	 * element. This operation is O(H).
	 * @param E e the value to match
	 * @return the least element greater than or equal to e, or null if there is no such element
	 * @throws ClassCastException if the specified element cannot be compared with the elements currently in the tree
	 * @throws NullPointerException if the specified element is null
	 */
	public E ceiling​(E e) throws ClassCastException, NullPointerException {
		// check if empty first
		if ( this.isEmpty() )
			return null;
		// if e is null, throw exception since this tree has no null elements
		if ( e == null )
			throw new NullPointerException("No null elements in this tree!");
		// used to traverse the tree
		Node<E> current = root, ceiling = null;
		// if e is smaller than the least element in this tree, the least is its ceiling
		if ( e.compareTo(this.first()) == -1 ) 
			return this.first();
		// if e is greater than the greatest element here, then it has no ceiling in this tree
		if ( e.compareTo(this.last()) == 1 )
			return null;
		// if e is in this tree, return it
		if ( this.contains​(e) ) 
			return e;
		// if e is not in this tree but in range [this.first(), this.last()]
		else { // there are 2 cases in this part:
			// look for the first node that is smaller than e while storing its parent
			while ( current != null ) {
				// if e is smaller, go left
				if ( e.compareTo(current.data) == -1 ) {
					ceiling = current;
					current = current.left;
				} // otherwise, go right
				else {
					current = current.right;
				} 
			} // when the ceiling is found
		} return ceiling.data;
	}
	/**
	 * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
	 * This operation is O(H).
	 * @param E e the value to match
	 * @return the greatest element less than or equal to e, or null if there is no such element
	 * @throws ClassCastException if the specified element cannot be compared with the elements currently in the tree
	 * @throws NullPointerException if the specified element is null
	 */
	public E floor​(E e) throws ClassCastException, NullPointerException {
		// check if empty first
		if ( this.isEmpty() )
			return null;
		// if e is null, throw exception since this tree has no null elements
		if ( e == null )
			throw new NullPointerException("No null elements in this tree!");
		// used to traverse the tree
		Node<E> current = root, floor = null;
		// if e is smaller than the least element in this tree, the floor is not in this tree
		if ( e.compareTo(this.first()) == -1 ) 
			return null;
		// if e is greater than the greatest element here, we have found its floor
		if ( e.compareTo(this.last()) == 1 )
			return this.last();
		// if e is in this tree, return it
		if ( this.contains​(e) ) 
			return e;
		// if e is not in this tree but in range [this.first(), this.last()]
		else { // there are 2 cases in this part:
			// look for the first node that is smaller than e while storing its parent
			while ( current != null ) {
				// if e is smaller, go left
				if ( e.compareTo(current.data) == 1 ) {
					floor = current;
					current = current.right;
				} // otherwise, go right
				else {
					current = current.left;
				} 
			} // when the ceiling is found
		} return floor.data;
	}
	/**
	 * Returns the first (lowest) element currently in this tree. This operation is O(H).
	 * @return the first (lowest) element currently in this tree
	 * @throws NoSuchElementException if this set is empty
	 */
	public E first() throws NoSuchElementException {
		// throw exception if this tree is empty
		if ( this.isEmpty() )
			throw new NoSuchElementException("This tree is empty!");
		// otherwise return the first element of the inorder traversal
		return (E) this.toArray()[0];
	}
	/**
	 * Returns the last (highest) element currently in this tree. This operation is O(H).
	 * @return the last (highest) element currently in this tree
	 * @throws NoSuchElementException if this tree is empty
	 */
	public E last() throws NoSuchElementException {
		// throw exception if this tree is empty
		if ( this.isEmpty() )
			throw new NoSuchElementException("This tree is empty!");
		// otherwise return the last element of the inorder traversal
		return (E) this.toArray()[size-1];
	}
	/**
	 * Returns the greatest element in this tree strictly less than the given element, or null if there is no such element.
	 * This operation is O(H).
	 * @param E e the value to match
	 * @return the greatest element less than e, or null if there is no such element
	 * @throws ClassCastException if the specified element cannot be compared with the elements currently 
	 * in the tree
	 * @throws NullPointerException if the specified element is null
	 */
	public E lower​(E e) throws ClassCastException, NullPointerException {
		// check if empty first
		if ( this.isEmpty() )
			return null;
		// if e is null, throw exception since this tree has no null elements
		if ( e == null )
			throw new NullPointerException("No null elements in this tree!");
		// if e is smaller than the least element in this tree, lower is not in this tree
		if ( e.compareTo(this.first()) == -1 ) 
			return null;
		// if e is greater than the greatest element here, we have found lower
		if ( e.compareTo(this.last()) == 1 )
			return this.last();
		// use the inorder array to do this
		Object[] array = this.toArray();
		// use two pointers to find lower
		int low = 0, high = array.length - 1, i = -1; // i stores the index of the lower
		while ( low <= high ) {
			int middle = low + (high - low)/2;
			//
			if ( e.compareTo((E) array[middle]) == 1) {
				i = middle;	low = middle + 1;
			}  else { high = middle - 1; }	
		}
		return (E) array[i];
	}
	/**
	 * Returns the least element in this tree strictly greater than the given element, or null 
	 * if there is no such element. This operation is O(H).
	 * @param E e the value to match
	 * @return the least element greater than e, or null if there is no such element
	 * @throws ClassCastException if the specified element cannot be compared with the elements currently in the set
	 * @throws NullPointerException if the specified element is null
	 */
	public E higher​(E e) throws ClassCastException, NullPointerException {
		// check if empty first
		if ( this.isEmpty() )
			return null;
		// if e is null, throw exception since this tree has no null elements
		if ( e == null )
			throw new NullPointerException("No null elements in this tree!");
		// if e is smaller than the least element in this tree, higher is found
		if ( e.compareTo(this.first()) == -1 ) 
			return this.first();
		// if e is greater than the greatest element here, higher is not in this tree
		if ( e.compareTo(this.last()) == 1 )
			return null;
		// use the inorder array to do this
		Object[] array = this.toArray();
		// use two pointers to find higher
		int low = 0, high = array.length - 1, i = -1; // i stores the index of the lower
		while ( low <= high ) {
			int middle = low + (high - low)/2;
			//
			if ( e.compareTo((E) array[middle]) == 1) {
				low = middle + 1;
			}  else { 
				i = middle;
				high = middle - 1; 
			}	
		}
		return (E) array[i];
	}
	/**
	 * Returns a shallow copy of this tree instance (i.e., the elements themselves are not cloned but the nodes are).
     * This operation is O(N).
     * @return a shallow copy of this tree
	 * @throws CloneNotSupportedException if this does not implement Clonable interface
	 */
	public BST<E> clone() throws CloneNotSupportedException {
		// we have a null clone if this is empty
		if ( this.isEmpty() )
			return null;
		// the clone to return 
		BST<E> clone = new  BST<>();
		// add each node in level order so it can be a proper clone
		Queue<Node<E>> queue = new LinkedList<> ();
		queue.add(root); //
		// queue is used as a buffer when traversing per level
		while ( !queue.isEmpty() ) {
			// store number of nodes at each level
			int countOfNodes = queue.size();
			// this allows the child nodes of each node to be added to the queue
			for ( int i = 0; i < countOfNodes; i++ ) {
				Node<E> node = queue.poll();
				// add each node according to level
				clone.add​(node.data);
				// add the child node of each node if it exists
				if ( node.left != null )
					queue.add(node.left);
				if ( node.right != null )
					queue.add(node.right);
			}
		} // return the clone
		return clone;
	}
	/**
	 * Compares the specified object with this tree for equality. Returns true if the given object is also a 
	 * tree, the two trees have the same size, and every member of the given tree is contained in this tree.
	 * This operation is O(N).
	 * @param Object obj the object to be compared for equality with this tree
	 * @return true if the specified object is equal to this tree
	 */
	@Override
	public boolean equals(Object obj) {
		// check simply
		if ( this == obj )
			return true;
		// obj cannot be null
		if ( obj == null )
			return false;
		// if obj is not a BST, it is not equal to this
		if ( !(obj instanceof BST) )
			return false;
		// sizes of this and obj must be equal
		BST<E> that = (BST<E>) obj;
		if ( this.size() != that.size() )
			return false;
		// now check every element
		for ( int i = 0; i < this.size(); i++ ) {
			// check if every element in this tree is in obj
			if ( ! this.get​(i).equals(that.get​(i)) ) 
				return false;	
		}
		return false;
	}
	/**
	 * Returns a string representation of this tree. The string representation consists of a list of the tree's elements 
	 * in the order they are returned by its iterator (inorder traversal), enclosed in square brackets ("[]"). Adjacent 
	 * elements are separated by the characters ", " (comma and space). Elements are converted to strings as 
	 * by String.valueOf(Object). This operation is O(N).
	 * @return a string representation of this collection
	 */
	@Override
	public String toString() {
		// check empty first
		if ( this.isEmpty() )
			return "[]";;
		String tree = "[";
		// append a comma to the end of every element except the last
		for ( int i = 0; i < size - 1; i++ ) {			
			tree += this.get​(i) + ", ";
		}
		// add ']' to the end of the last element instead of the ','
		tree += this.last() + "]";
		return tree;
	}
	/**
	 * This function returns an array containing all the elements returned by this tree's iterator, in the 
	 * same order, stored in consecutive elements of the array, starting with index 0. The length of the returned 
	 * array is equal to the number of elements returned by the iterator. This operation is O(N).
	 * @return an array, whose runtime component type is Object, containing all of the elements in this tree
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		// iterate over this tree inorder
		for ( int i = 0; i < size; i++ ) {
			// add each element in this tree to the array
			array[i] = this.get​(i);
		}
		return array;
	}
	/**
	 * Produces tree like string representation of this tree. Returns a string representation of this tree in a 
	 * tree-like format. The string representation consists of a tree-like representation of this tree. Each node 
	 * is shown in its own line with the indentation showing the depth of the node in this tree. The root is printed 
	 * on the first line, followed by its left subtree, followed by its right subtree.
	 * This operation is O(N).
	 * @return string containing tree-like representation of this tree.
	 * @author BST class example from CSC017 course 
	 */
    public String toStringTreeFormat() {

        StringBuilder s = new StringBuilder();

        preOrderPrint(root, 0, s);
        return s.toString();
    }
	/**
	 * Returns the root of this tree.
	 * @return the root of the tree.
	 */
	public Node<E> root() {
		return root;
	}
	/**
	 * 
	 * @param Node<E> tree
	 * @param int level 
	 * @param StringBuilder output
	 * @author BST class example from CSC017
	 */
	private void preOrderPrint(Node<E> tree, int level, StringBuilder output) {
        if (tree != null) {
            String spaces = "\n";
            if (level > 0) {
                for (int i = 0; i < level - 1; i++)
                    spaces += "   ";
                spaces += "|--";
            }
            output.append(spaces);
            output.append(tree.data);
            preOrderPrint(tree.left, level + 1, output);
            preOrderPrint(tree.right, level + 1, output);
        }
        // uncomment the part below to show "null children" in the output
        else {
            String spaces = "\n";
            if (level > 0) {
                for (int i = 0; i < level - 1; i++)
                    spaces += "   ";
                spaces += "|--";
            }
            output.append(spaces);
            output.append("null");
        }
    }
	
	/* ---------------------------------------------------------------------------------------------------------------------*/
	
	
	/**
	 * The Node class represents the nodes of the tree. Each node holds the data of that node, and a reference
	 * to its left and right nodes. Its type is generic which means it can be used for any type of element.
	 */
	private static class Node <E> {
		// Node data
		E data;
		// for AVL inheritance
		int height;
		// reference to the left and right nodes (child nodes)
		Node <E> left;
		Node <E> right;
		/**
		 * Constructs a Node instance with the data, height in the tree and
		 * references to its child Nodes.
		 * @param e the Node data
		 */
		Node (E e) {
			data = e;
			left = null;
			right = null;
			height = -1;
		}
	}
 	

	/**
	 * This Iter<E> class is responsible for iterating over this tree in the order of inorder traversal. 
	 * Inorder traversal guarantees the order of node data to be printed as [left -> node -> right]. It 
	 * implements the Iterator<E> over this collection and has the {@link next()} and {@link hasNext()} methods. 
	 * The remove() method is not implemented because its functionality is not needed.
	 */
	private class Iter<E> implements Iterator<E> {
		//Node<E> pointer = (Node<E>) root;
		//Stack<Node<E>> stack = new Stack<Node<E>> ();
		public Queue<E> nexts = inorder(); // stores the node data inorder
		/**
		 * @return true if the iteration has more elements. In other words, 
		 * returns true if {@link next()} would return an element rather than throwing
		 * an exception.
		 */
		@Override
		public boolean hasNext() {
			// hasNext can only be called while nodes are still in queue
			while ( !nexts.isEmpty() ) {
				return true;
			} // when all the elements in queue are returned, return false
			return false;
		}
		/**
		 * Returns the next element in the iteration. 
		 * @return the next E element in the iteration.
		 * @throws NoSuchElementException if the iteration has no more elements.
		 */
		@Override
		public E next() throws NoSuchElementException {
			// if queue is empty, there is no element to return anymore
			if ( nexts.isEmpty() )		
				throw new NoSuchElementException("No next element to return!");
			return nexts.poll();
		}
		/**
		 * Traverses the tree inorder given a node. {Helper method}
		 * @param Node<E> node the node to start from
		 * @return the Queue<E> with all the elements in this tree inorder
		 */
		public Queue<E> inorder() {
			Queue<E> nextsQueue = new LinkedList<> (); // the next elementS to return			
			Stack<Node<E>> stack = new Stack<Node<E>> ();
			Node<E> pointer = (Node<E>) root;
			boolean check = false; // to monitor while loop
			while ( !check ) {
				if ( pointer != null ) {
					// save every left node till last
					stack.push(pointer);
					pointer = pointer.left;
				}
				else {
					if ( !stack.empty() ) {
						// assign pointer to the top of the stack
						pointer = (Node<E>) stack.pop();
						// save the data to the elements of the queue
						nextsQueue.add(pointer.data);	
						// go to the right node after touching data
						pointer = pointer.right;
					} else 
						check = true;
				}
			}
			return nextsQueue;
		}
	}
	
	/**
	 * This PreOrderIter<E> class is responsible for iterating over this tree in the order of preorder traversal. 
	 * Preorder traversal guarantees the order of node data to be printed as [node -> left -> right]. It 
	 * implements the Iterator<E> over this collection and has the {@link next()} and {@link hasNext()} methods. 
	 * The remove() method is not implemented because its functionality is not needed.
	 */
	private class PreOrderIter<E> implements Iterator<E> {
		public Queue<E> nexts = preorder(); // stores the node data preorder		
		/**
		 * @return true if the iteration has more elements. In other words, 
		 * returns true if {@link next()} would return an element rather than throwing
		 * an exception.
		 */
		@Override
		public boolean hasNext() {
			// hasNext can only be called while nodes are still in queue
			while ( !nexts.isEmpty() ) {
				return true;
			} // when all the elements in queue are returned, return false
			return false;
		}
		/**
		 * Returns the next element in the iteration. 
		 * @return the next Object element in the iteration.
		 * @throws NoSuchElementException if the iteration has no more elements.
		 */
		@Override
		public E next() throws NoSuchElementException {
			// if queue is empty, there is no element to return anymore
			if ( nexts.isEmpty() )		
				throw new NoSuchElementException("No next element to return!");
			return nexts.poll();
		}
		/**
		 * Traverses the tree preorder given a node. {Helper method}
		 * @param Node<E> node the node to start from
		 * @return the Queue<E> with all the elements in this tree preorder
		 */
		public Queue<E> preorder() {
			Queue<E> nextsQueue = new LinkedList<> (); // the elements are saved in a queue			
			Stack<Node<E>> stack = new Stack<Node<E>> ();
			if ( root == null ) 
				return nextsQueue;
			Node<E> pointer = (Node<E>) root;
			stack.push((Node<E>) root);
			//boolean check = false; // to monitor while loop
			while ( !stack.isEmpty() ) {
				pointer = stack.peek();
				stack.pop();
				nextsQueue.add(pointer.data);
				if ( pointer.right != null ) {
					// 
					stack.push(pointer.right);
				}
				if ( pointer.left != null ) {
					// 
					stack.push(pointer.left);
				} 
			}
			return nextsQueue;
		}
	}
	
	/**
	 * This PostOrderIter<E> class is responsible for iterating over this tree in the order of postorder traversal. 
	 * Postorder traversal guarantees the order of node data to be printed as [left -> right -> node]. It 
	 * implements the Iterator<E> over this collection and has the {@link next()} and {@link hasNext()} methods. 
	 * The remove() method is not implemented because its functionality is not needed.
	 */
	private class PostOrderIter<E> implements Iterator<E> {
		public Queue<E> nexts = postorder(); // stores the node data postorder
		/**
		 * @return true if the iteration has more elements. In other words, 
		 * returns true if {@link next()} would return an element rather than throwing
		 * an exception.
		 */
		@Override
		public boolean hasNext() {
			// hasNext can only be called while nodes are still in queue
			while ( !nexts.isEmpty() ) {
				return true;
			} // when all the elements in queue are returned, return false
			return false;
		}
		/**
		 * Returns the next element in the iteration. 
		 * @return the next Object element in the iteration.
		 * @throws NoSuchElementException if the iteration has no more elements.
		 */
		@Override
		public E next() throws NoSuchElementException {
			// if queue is empty, there is no element to return anymore
			if ( nexts.isEmpty() )		
				throw new NoSuchElementException("No next element to return!");
			return nexts.poll();
		}
		/**
		 * Traverses the tree postorder given a node. {Helper method}
		 * @param Node<E> node the node to start from
		 * @return the Queue<E> with all the elements in this tree postorder
		 */
		public Queue<E> postorder() {
			Queue<E> nextsQueue = new LinkedList<> (); // the next elementS to return			
			Stack<Node<E>> stack = new Stack<Node<E>> ();
			Node<E> pointer = (Node<E>) root, temp = null;
			while ( pointer != null || !stack.empty() ) {
				if ( pointer != null ) {
					// save every left node till last
					stack.push(pointer);
					pointer = pointer.left;
				}
				else {
					temp = stack.peek().right;
					if ( temp == null ) {
						// 
						temp = (Node<E>) stack.peek();
						// 
						stack.pop();	
						// 
						nextsQueue.add(temp.data);
						while ( !stack.empty() && temp == stack.peek().right ) {
							temp = stack.peek();
							stack.pop();
							nextsQueue.add(temp.data);
						}
					} else 
						pointer = temp;
				}
			}
			return nextsQueue;
		}
	}
	
}
