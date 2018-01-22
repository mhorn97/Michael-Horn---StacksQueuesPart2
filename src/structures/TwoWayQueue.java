/**
 * Michael Horn
 * Jan 22, 2018
 * TwoWayQueue.java
 */
package structures;

import java.util.Iterator;
import java.util.List;


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
		this.head = new TwoWayNode(null, null,null);
	}
	
	@Override
	public T dequeueFirst()
	{
		return null;
	}

	@Override
	public T dequeueLast()
	{
		return null;
	}

	@Override
	public List<T> dequeueAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueueFirst(T element)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enqueueLast(T element)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enqueueAllFirst(T[] elements)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enqueueAllLast(T[] elements)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<T> iterator()
	{
		// TODO Auto-generated method stub
		return null;
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
		
		public void setPrevious(TwoWayNode prev)
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
