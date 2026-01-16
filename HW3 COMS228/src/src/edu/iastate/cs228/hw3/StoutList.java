package src.edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Order;


/**
 * @author Joshua Seeley
 * 
 *         Implementation of the list interface based on linked nodes that store
 *         multiple items per node. Rules for adding and removing elements
 *         ensure that each node (except possibly the last one) is at least half
 *         full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
	/**
	 * Default number of elements that may be stored in each node.
	 */
	private static final int DEFAULT_NODESIZE = 4;

	/**
	 * Number of elements that can be stored in each node.
	 */
	private final int nodeSize;

	/**
	 * Dummy node for head. It should be private but set to public here only for
	 * grading purpose. In practice, you should always make the head of a linked
	 * list a private instance variable.
	 */
	public Node head;

	/**
	 * Dummy node for tail.
	 */
	private Node tail;

	/**
	 * Number of elements in the list.
	 */
	private int size;

	/**
	 * Comparator for sorts
	 */
	protected Comparator<E> eComparator = null;
	

	/**
	 * Constructs an empty list with the default node size.
	 */

	public StoutList() {
		this(DEFAULT_NODESIZE);

	}

	/**
	 * Constructs an empty list with the given node size.
	 * 
	 * @param nodeSize number of elements that may be stored in each node, must be
	 *                 an even number
	 */
	public StoutList(int nodeSize) {
		if (nodeSize <= 0 || nodeSize % 2 != 0)
			throw new IllegalArgumentException();

		// dummy nodes
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		this.nodeSize = nodeSize;
	}

	/**
	 * Constructor for grading only. Fully implemented.
	 * 
	 * @param head
	 * @param tail
	 * @param nodeSize
	 * @param size
	 */
	public StoutList(Node head, Node tail, int nodeSize, int size) {
		this.head = head;
		this.tail = tail;
		this.nodeSize = nodeSize;
		this.size = size;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean add(E item) {
		Node yes = new Node();

		if (tail.previous == head) { // check for if list is empty
			head.next.previous = yes;
			yes.next = head.next;
			head.next = yes;
			yes.previous = head;

		} else if (tail.previous.count == nodeSize) { // checks if node is full
			tail.previous.next = yes;
			yes.previous = tail.previous;
			yes.next = tail;
			tail.previous = yes;

		}
		tail.previous.addItem(item);
		size++;
		return true;

	}

	@Override
	public void add(int pos, E item) {
		// TODO Auto-generated method stub
		if (head.next == tail) {
			this.add(item);
		}
		Node newNode = head.next;
		int offs = pos % nodeSize;
		int holder = 0;

		while (holder < pos / nodeSize) {
			newNode = newNode.next;
			holder++;

		}
		if (offs == 0) {

			if (newNode.previous != head && newNode.previous.count < nodeSize) {

				newNode.previous.addItem(item);
			}
			if (newNode == tail && newNode.previous.count == nodeSize) {
				this.add(item);
			}

		} else if (newNode.count < nodeSize) {
			newNode.addItem(offs, item);
		} else {
			Node yes = new Node();
			int half = nodeSize / 2;

			newNode.next.previous = newNode;
			yes.next = newNode.next;
			newNode.next = yes;
			yes.previous = newNode;

			E[] a = (E[]) new Comparable[pos / nodeSize]; // creates new arrays to store front and back
			E[] b = (E[]) new Comparable[pos / nodeSize]; // halfs of nodes

			for (int i = 0; i < pos / nodeSize; i++) {
				a[i] = newNode.data[i];
				b[i] = newNode.data[i + (pos / nodeSize)];
			}
			for (int j = 0; j < nodeSize; j++) {
				newNode.removeItem(0);
			}
			for (int c = 0; c < pos / nodeSize; c++) {
				newNode.addItem(a[c]);
				yes.addItem(b[c]);
			}
			if (offs <= pos / nodeSize) {
				newNode.addItem(offs, item);
			}
			if (offs > pos / nodeSize) {
				yes.addItem(offs - (pos / nodeSize), item);
			}
		}
	}

	@Override
	public E remove(int pos) {
		// TODO Auto-generated method stub
		if (tail.previous == head) {
			return null;
		}
		int divide = pos / nodeSize;
		int offs = Math.max((pos % nodeSize) - divide, 0);
		Node newNode = head.next;
		int holder = newNode.count;
		E obj;
		E[] a = (E[]) new Comparable[nodeSize / 2];
		while (holder <= pos) {
			if (holder == pos) {
				offs = 0;
				newNode = newNode.next;
				break;
			} else {
				newNode = newNode.next;

			}
			holder += newNode.count;
		}
		if (newNode.next == tail && newNode.count == 1) {
			newNode.next.previous = newNode.previous;
			newNode.previous.next = newNode.next;

		} else if (newNode.next == tail && newNode.count > 1 || newNode.count > nodeSize / 2) {

			newNode.removeItem(offs);
		} else {

			if (newNode.next.count > nodeSize / 2) {
				obj = newNode.next.data[0];
				newNode.next.removeItem(0);
				newNode.removeItem(offs);
				newNode.addItem(obj);

			} else if (newNode.next.count < nodeSize / 2) {
				newNode.removeItem(offs);
				for (int i = 0; i < nodeSize / 2; i++) {
					newNode = newNode.next;
					obj = newNode.data[i];
					newNode = newNode.previous;
					newNode.addItem(obj);
				}
				newNode = newNode.next;
				newNode.next.previous = newNode.previous;
				newNode.previous.next = newNode.next;

			}
		}
		--size;
		return null;
	}

	/**
	 * Sort all elements in the stout list in the NON-DECREASING order. You may do
	 * the following. Traverse the list and copy its elements into an array,
	 * deleting every visited node along the way. Then, sort the array by calling
	 * the insertionSort() method. (Note that sorting efficiency is not a concern
	 * for this project.) Finally, copy all elements from the array back to the
	 * stout list, creating new nodes for storage. After sorting, all nodes but
	 * (possibly) the last one must be full of elements.
	 * 
	 * Comparator<E> must have been implemented for calling insertionSort().
	 */
	public void sort() {
		// TODO
		E[] node = (E[]) new Comparable[nodeSize];
		
		while (head.next != tail) {
			Node yes = new Node();
			head.next = yes;
			while(yes.next != tail) {
				node = yes.data;
				yes = head.next;
				yes = null;
				
			}
			
			insertionSort(node, eComparator);
			
			}
		Node newNode = new Node();
		newNode = head.next;
		for(int i = 0;  i < node.length; i++) {
			newNode.data = node;
			if(newNode.count == nodeSize) {
				head.previous = head;
				newNode.next = newNode;
				
			}
		
		}

		}

	

	/**
	 * Sort all elements in the stout list in the NON-INCREASING order. Call the
	 * bubbleSort() method. After sorting, all but (possibly) the last nodes must be
	 * filled with elements.
	 * 
	 * Comparable<? super E> must be implemented for calling bubbleSort().
	 */
	public void sortReverse() {
		// TODO
		while(head.next != tail) {
			
			
		}
	}

	@Override
	public Iterator<E> iterator() {

		StoutListIterator list = new StoutListIterator();
		return list;
	}

	@Override
	public ListIterator<E> listIterator() {

		StoutListIterator list = new StoutListIterator();
		return list;
	}

	@Override
	public ListIterator<E> listIterator(int index) {

		StoutListIterator list = new StoutListIterator(index);
		return list;
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes.
	 */
	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal structure
	 * of the nodes and the position of the iterator.
	 *
	 * @param iter an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail) {
			sb.append('(');
			E data = current.data[0];
			if (data == null) {
				sb.append("-");
			} else {
				if (position == count) {
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < nodeSize; ++i) {
				sb.append(", ");
				data = current.data[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size && count == size) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Node type for this list. Each node holds a maximum of nodeSize elements in an
	 * array. Empty slots are null.
	 */
	private class Node {
		/**
		 * Array of actual data elements.
		 */
		// Unchecked warning unavoidable.
		public E[] data = (E[]) new Comparable[nodeSize];

		/**
		 * Link to next node.
		 */
		public Node next;

		/**
		 * Link to previous node;
		 */
		public Node previous;

		/**
		 * Index of the next available offset in this node, also equal to the number of
		 * elements in this node.
		 */
		public int count;

		/**
		 * Adds an item to this node at the first available offset. Precondition: count
		 * < nodeSize
		 * 
		 * @param item element to be added
		 */
		void addItem(E item) {
			if (count >= nodeSize) {
				return;
			}
			data[count++] = item;
			// useful for debugging
			// System.out.println("Added " + item.toString() + " at index " + count + " to
			// node " + Arrays.toString(data));
		}

		/**
		 * Adds an item to this node at the indicated offset, shifting elements to the
		 * right as necessary.
		 * 
		 * Precondition: count < nodeSize
		 * 
		 * @param offset array index at which to put the new element
		 * @param item   element to be added
		 */
		void addItem(int offset, E item) {
			if (count >= nodeSize) {
				return;
			}
			for (int i = count - 1; i >= offset; --i) {
				data[i + 1] = data[i];
			}
			++count;
			data[offset] = item;
			// useful for debugging
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
		}

		/**
		 * Deletes an element from this node at the indicated offset, shifting elements
		 * left as necessary. Precondition: 0 <= offset < count
		 * 
		 * @param offset
		 */
		void removeItem(int offset) {
			E item = data[offset];
			for (int i = offset + 1; i < nodeSize; ++i) {
				data[i - 1] = data[i];
			}
			data[count - 1] = null;
			--count;
		}
	}

	private class StoutListIterator implements ListIterator<E> {
		// constants you possibly use ...

		// instance variables ...
		Node newNode;

		int pos;
		int offs;
		boolean set;

		/**
		 * Default constructor
		 */
		public StoutListIterator() {
			// TODO
			if (head.next != tail) {
				newNode = head.next;

			}
			offs = 0;
			pos = 0;
			set = false;
		}

		/**
		 * Constructor finds node at a given position.
		 * 
		 * @param pos
		 */
		public StoutListIterator(int pos) {
			// TODO
			this.pos = pos;
		}

		@Override
		public boolean hasNext() {
			// TODO
			if (newNode == head && newNode.next == tail) {
				return false;

			} else if (newNode.count == nodeSize && newNode.next == tail) {
				return false;

			} else {
				return true;
			}
		}

		@Override
		public E next() {
			if (!this.hasNext()) {
				return null;
			}
			E cur = null;

			if (newNode.count == nodeSize) {
				newNode = newNode.next;
				offs = 0;
				cur = newNode.data[offs];
			} else {
				cur = newNode.data[offs];
				offs++;
			}
			pos++;
			set = true;

			return cur;

		}

		@Override
		public void remove() {
			// TODO
		}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		if(tail.previous == head) {
			return false;
			
		}
		return set;
	
	}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method st
			if (newNode.next == null) {
				return size;
			}
			return offs;
			
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub

		}

		// Other methods you may want to add or override that could possibly facilitate
		// other operations, for instance, addition, access to the previous element,
		// etc.
		//
		// ...
		//
	}

	/**
	 * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING
	 * order.
	 * 
	 * @param arr  array storing elements from the list
	 * @param comp comparator used in sorting
	 */
	private void insertionSort(E[] arr, Comparator<? super E> comp) {
		// TODO
		int i = 0;
		int j = 0;
		E temp;

		for (i = 1; i < arr.length; i++) {
			E holder = arr[i];
			j = i - 1;

			while ((j > -1) && comp.compare(arr[j], holder) == 1) {
				temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				--j;
			}
			arr[j + 1] = holder;
		}
	}


	/**
	 * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
	 * description of bubble sort please refer to Section 6.1 in the project
	 * description. You must use the compareTo() method from an implementation of
	 * the Comparable interface by the class E or ? super E.
	 * 
	 * @param arr array holding elements from the list
	 */
	private void bubbleSort(E[] arr) {
		// TODO
	
		for(int i = 0; i < arr.length - 1; i++) {
			for(int  j = 0; j < arr.length - i - 1; j++){
				if(arr[j].compareTo(arr[j+1]) < 0) {
					E temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
	}

/**
 * 
 * @param obj sets the comparator for eComparator
 * @throws IllegalArgumentException if argument is invalid
 */

public void setComparator(E obj) throws IllegalArgumentException{
	
	if (obj == null) {

		eComparator = new Comparator<E>() {

			@Override
			public int compare(E order1, E order2) {
				if (order1.compareTo(order2) > 0) {
					return 1;
				} else if (order1.compareTo(order2) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		};
	}

	
}

}


