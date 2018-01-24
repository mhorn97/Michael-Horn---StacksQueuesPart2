/**
 * Michael Horn & Anthony Thompson
 * Jan 22, 2018
 * TwoWayQueue.java
 */
package structures;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import exceptions.EmptyQueueException;

/**
 * @author Michael Horn & Anthony Thompson
 * @version 1.0
 */
public class TwoWayQueue<T> implements ITwoWayQueue<T>
{

	TwoWayNode head;
	private int modCount;

	/**
	 * The TwoWayQueue constructor stores a head object for itself which is null when initialized
	 */
	public TwoWayQueue()
	{
		this.head = new TwoWayNode(null, null, null);
	}

	/**
	 * Removes and returns the first element in the queue.
	 *
	 * @throws exceptions.EmptyQueueException
	 *             if the queue is empty and dequeueFirst() is called
	 * @return the first element
	 * 				element in the queue
	 */
	@Override
	public T dequeueFirst()
	{
		modCount++;

		if (isEmpty())
		{
			throw new EmptyQueueException();
		}

		T returnElement = head.getData();

		if (head.next != null)
		{
			head.setData(head.getNext().getData());
			head.setNext(head.getNext().getNext());
		} else
		{
			head.setData(null);
		}

		return returnElement;
	}

	/**
	 * Removes and returns the last element in the queue.
	 *
	 * @throws exceptions.EmptyQueueException
	 *             if the queue is empty and dequeueLast() is called
	 * @return the last element
	 * 				The last element in the queue
	 */
	@Override
	public T dequeueLast()
	{
		modCount++;
		TwoWayNode current = head;
		T returnElement;

		if (isEmpty())
		{
			throw new EmptyQueueException();
		}

		if (head.next == null)
		{
			returnElement = head.getData();
			head.setData(null);
			return returnElement;
		} else
		{
			while (current.getNext().getNext() != null)
			{
				current = current.next;
			}

			returnElement = current.getNext().getData();
			current.setNext(null);
			return returnElement;
		}
	}

	/**
	 * Removes and returns all elements in the queue. The first element in the queue
	 * should be located at the last index of the resulting list (index size() - 1)
	 * and the last element in the queue at index zero.
	 *
	 * @return a list of all elements in the queue
	 */
	@Override
	public List<T> dequeueAll()
	{
		modCount++;
		if (isEmpty())
		{
			throw new EmptyQueueException();
		}
		TwoWayNode tail = head;
		ArrayList<T> list = new ArrayList<>();
		while (tail.getNext() != null)
		{
			tail = tail.next;
		}
		while (tail.getPrevious() != null)
		{
			list.add(tail.getData());
			tail = tail.previous;
		}
		list.add(head.getData());
		head.setData(null);
		head.setNext(null);
		return list;
	}

	/**
	 * Adds a new element to the front of the queue. The queue should continually
	 * resize to make room for new elements.
	 *
	 * @param element
	 *            the new element to be added to the front of the queue
	 */
	@Override
	public void enqueueFirst(T element)
	{
		modCount++;
		if (head.getData() == null)
		{
			head = new TwoWayNode(element, null, null);
		} else
		{
			TwoWayNode current = new TwoWayNode(head.getData(), head.next, head);
			head.setData(element);
			if (head.next != null)
			{
				head.next.setPrevious(current);
			}
			head.setNext(current);
		}
	}

	/**
	 * Adds a new element to the end of the queue. The queue should continually
	 * resize to make room for new elements.
	 *
	 * @param element
	 *            the new element to be added to then end of the queue
	 */
	@Override
	public void enqueueLast(T element)
	{
		modCount++;
		if (head.getData() == null)
		{
			head = new TwoWayNode(element, null, null);
		} else
		{
			TwoWayNode current = head;
			while (current.getNext() != null)
			{
				current = current.next;
			}
			current.next = new TwoWayNode(element, null, current);
		}
	}

	/**
	 * Adds a group of elements to the front of the queue.
	 *
	 * @param elements
	 *            an array of elements to be added to the beginning of the queue
	 */
	@Override
	public void enqueueAllFirst(T[] elements)
	{
		for (T element : elements)
		{
			enqueueFirst(element);
		}
		modCount++;

	}

	/**
	 * Adds a group of elements to the end of the queue.
	 *
	 * @param elements
	 *            an array of elements to be added to the end of the queue
	 */
	@Override
	public void enqueueAllLast(T[] elements)
	{
		for (T element : elements)
		{
			enqueueLast(element);
		}
		modCount++;

	}

	/**
	 * Returns the number of elements in the queue.
	 *
	 * @return the number of elements in the queue
	 */
	@Override
	public int size()
	{
		int counter = 0;
		TwoWayNode current = head;

		if (head.getData() == null)
		{
			return 0;
		}

		if (current.getData() != null)
		{
			counter++;
		}

		while (current.next != null)
		{
			current = current.next;
			counter++;
		}

		return counter;
	}

	/**
	 * Reports whether the queue is empty or not.
	 *
	 * @return true if no elements are in the queue, otherwise returns false
	 */
	@Override
	public boolean isEmpty()
	{
		if (head.getData() == null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Removes all elements from the queue. Sets the head to null
	 */
	@Override
	public void clear()
	{
		modCount++;
		head.setData(null);
		head.setNext(null);

	}

	/**
	 * Returns an iterator over the elements of the queue. It should not be possible
	 * to use the iterator while making any changes to the stack itself.
	 *
	 * Elements should return in FIFO order (i.e. The first element added should be
	 * the first returned by the iterator. The last element added should be the last
	 * returned by the iterator.)
	 *
	 * @return an object using the Iterator<T> interface
	 */
	@Override
	public Iterator<T> iterator()
	{
		TwoWayNode tail = head;
		while (tail.next != null)
		{
			tail = tail.next;
		}
		return new TwoWayQueueIterator(tail, modCount);
	}

	private class TwoWayQueueIterator implements Iterator<T>
	{

		TwoWayNode tail;
		int savedModCount;

		/**
		 * TwoWayQueue iterator object that keeps track of its own head node
		 * 
		 * @param head
		 *            node that is the start of the queue
		 * @param savedModCount
		 *            the count of times the queue was manipulated
		 */
		public TwoWayQueueIterator(TwoWayNode tail, int savedModCount)
		{
			this.savedModCount = savedModCount;
			this.tail = tail;
		}

		/**
		 * Checks if the stack has the next iteration by checking if the next node has
		 * data or not
		 * 
		 * @return if the iterator can do the next method again
		 */
		@Override
		public boolean hasNext()
		{
			if (savedModCount != TwoWayQueue.this.modCount)
			{
				throw new ConcurrentModificationException();
			}

			if (tail.getData() == null)
			{
				return false;
			} else
			{
				return tail.getPrevious() != null;
			}
		}

		/**
		 * Returns the data of the next node in the stack
		 * 
		 * @return the data in the next part of the stack
		 */
		@Override
		public T next()
		{
			if (savedModCount != TwoWayQueue.this.modCount)
			{
				throw new ConcurrentModificationException();
			}

			T data = tail.getData();
			tail = tail.previous;
			return data;
		}

	}

	/**
	 * Class that represents a node for the queue for the TwoWayQueue class
	 * 
	 * @author Michael Horn & Anthony Thompson
	 * @version 1.0
	 */
	private class TwoWayNode
	{
		public T data;
		public TwoWayNode next;
		public TwoWayNode previous;

		/**
		 * TwoWayQueue Node object that contains data, a reference to next node, and a reference to the 
		 * previous node in the queue
		 * 
		 * @param data
		 *            element that is unknown until the stack is created
		 * @param next
		 *            reference to the next node in the queue
		 * @param previous
		 * 			  reference to the previous node in the queue
		 */
		public TwoWayNode(T data, TwoWayNode next, TwoWayNode previous)
		{
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

		/**
		 * Sets the data of the node
		 * 
		 * @param element
		 *            to be placed into the node
		 */
		public void setData(T element)
		{
			this.data = element;
		}

		/**
		 * Changes the reference of the node next in queue
		 * 
		 * @param the
		 *            reference to the node next in queue
		 */
		public void setNext(TwoWayNode next)
		{
			this.next = next;
		}

		/**
		 * Sets the previous node reference of the current node
		 * 
		 * @param The reference to the node previous in the queue
		 */
		public void setPrevious(TwoWayNode previous)
		{
			this.previous = previous;
		}

		/**
		 * Gets the previous node within the current nodes references
		 * @return node that is previous in the queue
		 */
		public TwoWayNode getPrevious()
		{
			return previous;
		}

		/**
		 * Gets the next node within the current nodes references
		 * @return node that is next in the queue
		 */
		public TwoWayNode getNext()
		{
			return next;
		}

		/**
		 * Returns the data of the current node
		 * 
		 * @return element/data of current node
		 */
		public T getData()
		{
			return data;
		}

		/**
		 * Returns the to string of the data of the current node
		 * 
		 * @return tostring of the data of the current node
		 */
		public String toString()
		{
			return data.toString();
		}
	}

}
