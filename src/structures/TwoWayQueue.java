/**
 * Michael Horn
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
 * @author Michael Horn
 *
 */
public class TwoWayQueue<T> implements ITwoWayQueue<T>
{

	TwoWayNode head;
	private int modCount;

	/**
	 * Stack stores a head object for itself which is null when initialized
	 */
	public TwoWayQueue()
	{
		this.head = new TwoWayNode(null, null, null);
	}
	
	@Override
	public T dequeueFirst()
	{	
		if(isEmpty())
		{
			throw new EmptyQueueException();
		}
		T current = head.getData();
		if(head.getNext() != null)
		{
			head = head.next;
		}
		return current;
	}

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

		if (current.next == null)
		{
			returnElement = head.getData();
			head.setData(null);
			return returnElement;
		} else
		{
			while (current.next.getNext() != null)
			{
				current = current.next;
			}

			returnElement = current.next.getData();
			current.setNext(null);
			return returnElement;
		}
	}

	@Override
	public List<T> dequeueAll()
	{
		if(isEmpty())
		{
			throw new EmptyQueueException();
		}
		TwoWayNode current = head;
		ArrayList<T> list = new ArrayList<>();
		while(current.getNext() != null)
		{
			list.add(current.getData());
			current = current.next;
		}
		head = new TwoWayNode(null,null,null);
		return list;
	}

	@Override
	public void enqueueFirst(T element)
	{
		if (head.getData() == null)
		{
			head = new TwoWayNode(element, null, null);
		} else
		{
			TwoWayNode secondHead = new TwoWayNode(head.getData(), head.getNext(), head);
			head.setData(element);
			head.setNext(secondHead);
		}
	}

	@Override
	public void enqueueLast(T element)
	{
		if (head.getData() == null)
		{
			head = new TwoWayNode(element, null, null);
			System.out.println("head: " + head.getData());
		} else
		{
			TwoWayNode current = head;
			while (current.next != null)
			{
				System.out.println("Current: " + current.getData());
				current = current.next;
			}
			System.out.println("DONE");
			current.next = new TwoWayNode(element, null, null);
		}
		modCount++;
		
	}

	@Override
	public void enqueueAllFirst(T[] elements)
	{
		for (T element : elements)
		{
			enqueueFirst(element);
		}
		modCount++;
		
	}

	@Override
	public void enqueueAllLast(T[] elements)
	{
		for (T element : elements)
		{
			enqueueLast(element);
		}
		modCount++;
		
	}

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

	@Override
	public void clear()
	{
		head.setData(null);
		head.setNext(null);
		
	}

	@Override
	public Iterator<T> iterator()
	{
		return new TwoWayQueueIterator(head, modCount);
	}
	
	private class TwoWayQueueIterator implements Iterator<T>
	{

		TwoWayNode head;
		int savedModCount;

		/**
		 * Stack iterator object that keeps track of its own head node
		 * @param head node that is the start of the linked list
		 * @param savedModCount the count of times the linked list was manipulated
		 */
		public TwoWayQueueIterator(TwoWayNode head, int savedModCount)
		{
			this.savedModCount = savedModCount;
			this.head = head;
		}

		/**
		 * Checks if the stack has a next iteration by checking if the next node has
		 * data or not
		 * @return if the iterator can do the next method again
		 */
		@Override
		public boolean hasNext()
		{
			if (savedModCount != TwoWayQueue.this.modCount)
			{
				throw new ConcurrentModificationException();
			}
			
			if (head.getData() == null)
			{
				return false;
			} else
			{
				return head.getNext() != null;
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
			
			T data = head.getData();
			head = head.next;
			return data;
		}

	}
	
	
	/**
	 * Class that represents a node for linked list for the Stack class
	 * 
	 * @author Michael Horn
	 * @version 1.0
	 */
	private class TwoWayNode
	{
		public T data;
		public TwoWayNode next;
		public TwoWayNode previous;

		/**
		 * Stack Node object that contains data and a reference to the next node in the
		 * linked list
		 * 
		 * @param data
		 *            element that is unknown until the stack is created
		 * @param next
		 *            reference to the next node
		 */
		public TwoWayNode(T data, TwoWayNode next, TwoWayNode previous)
		{
			this.data = data;
			this.next = next;
			this.previous = previous;
		}

		/**
		 * Able to set the data of the node
		 * 
		 * @param element
		 *            to be placed into the node
		 */
		public void setData(T element)
		{
			this.data = element;
		}

		/**
		 * Changes the reference of the node
		 * 
		 * @param the
		 *            reference to a node
		 */
		public void setNext(TwoWayNode next)
		{
			this.next = next;
		}

		/**
		 * Returns the reference to the next node
		 * 
		 * @return reference to next node
		 */
		
		public void setPrevious(TwoWayNode previous)
		{
			this.previous = previous;
		}
		
		public TwoWayNode getPrevious()
		{
			return previous;
		}
		
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
