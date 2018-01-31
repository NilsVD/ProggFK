package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible.
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		if(size == 0) {
			n.next = n;
		} else {
			n.next = last.next;
			last.next = n;
		}
		last = n;
		size++;
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last == null) {
			return null;	//	Är detta nödvändigt eller är last.next.element == null?
		} else {
			return last.next.element;
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(last == null) {
			return null;	//	Är detta nödvändigt eller är last.next.element == null?
		} else {
			QueueNode<E> r = last.next;
			last.next = last.next.next;
			size--;
			return r.element;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int count;
		
		private QueueIterator() {
			pos = last;
			this.count = 0;
		}
		
		public boolean hasNext() {
			if (pos == null || count == size) {
				return false;
			} else {
				return true;
			}
		}
		
		public E next() {
			if (pos == null || count == size) {
				throw new NoSuchElementException();
			}
			pos = pos.next;
			count++;
			return pos.element;
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	public void append(FifoQueue<E> q) {
		if (last == q.last) {
			throw new IllegalArgumentException();
		} else if (last == null && q.last != null) {
			last = q.last;
			size = q.size();
			q.last = null;
			q.size = 0;
		} else if (last != null && q.last == null) {
			// Dont do anything
		} else if (last != null && q.last != null) {
			size += q.size();
			QueueNode<E> j = last.next;
			last.next = q.last.next;
			q.last.next = j;
			last = q.last;
			q.last = null;
			q.size = 0;
		}
	}
}
