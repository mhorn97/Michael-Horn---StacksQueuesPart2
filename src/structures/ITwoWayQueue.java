package structures;

import java.util.Iterator;
import java.util.List;

/**
 * This interface describes a two-way queue that allows insertions from both
 * ends as well as retrievals from both ends.
 *
 * DO NOT ALTER THIS FILE!
 *
 * @author Josh Archer
 * @version 1.0
 */
public interface ITwoWayQueue<T> extends Iterable<T>
{
	/**
	 * Removes and returns the first element in the queue.
	 *
	 * @throws exceptions.EmptyQueueException
	 *             if the queue is empty and dequeueFirst() is called
	 * @return the first element
	 */
	public T dequeueFirst();

	/**
	 * Removes and returns the last element in the queue.
	 *
	 * @throws exceptions.EmptyQueueException
	 *             if the queue is empty and dequeueLast() is called
	 * @return the last element
	 */
	public T dequeueLast();

	/**
	 * Removes and returns all elements in the queue. The first element in the queue
	 * should be located at the last index of the resulting list (index size() - 1)
	 * and the last element in the queue at index zero.
	 *
	 * @return a list of all elements in the queue
	 */
	public List<T> dequeueAll();

	/**
	 * Adds a new element to the front of the queue. The queue should continually
	 * resize to make room for new elements.
	 *
	 * @param element
	 *            the new element
	 */
	public void enqueueFirst(T element);

	/**
	 * Adds a new element to the end of the queue. The queue should continually
	 * resize to make room for new elements.
	 *
	 * @param element
	 *            the new element
	 */
	public void enqueueLast(T element);

	/**
	 * Adds a group of elements to the front of the queue.
	 *
	 * @param elements
	 *            an array of elements
	 */
	public void enqueueAllFirst(T[] elements);

	/**
	 * Adds a group of elements to the end of the queue.
	 *
	 * @param elements
	 *            an array of elements
	 */
	public void enqueueAllLast(T[] elements);

	/**
	 * Returns the number of elements in the queue.
	 *
	 * @return the number of elements
	 */
	public int size();

	/**
	 * Reports whether the queue is empty or not.
	 *
	 * @return true if no elements are in the queue, otherwise returns false
	 */
	public boolean isEmpty();

	/**
	 * Removes all elements from the queue.
	 */
	public void clear();

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
	public Iterator<T> iterator();
}
