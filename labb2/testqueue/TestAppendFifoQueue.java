package testqueue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;

import org.junit.Test;

public class TestAppendFifoQueue {
	private Queue<Integer> IntQueueOne;
	private Queue<Integer> IntQueueTwo;
	private Queue<String> myStringQueue;

	@Before
	public void setUp() throws Exception {
		IntQueueOne = new FifoQueue<Integer>();
		IntQueueTwo = new FifoQueue<Integer>();
		myStringQueue = new FifoQueue<String>();
	}

	@After
	public void tearDown() throws Exception {
		IntQueueOne = null;
		IntQueueTwo = null;
		myStringQueue = null;
	}
	
	/**
	 * Test two empty queues.
	 */
	@Test
	public final void testTwoEmptyQueues() {
		assertTrue(IntQueueOne.isEmpty());
		assertTrue(IntQueueOne.size() == 0);
		assertTrue(IntQueueTwo.isEmpty());
		assertTrue(IntQueueTwo.size() == 0);
		try {
			((FifoQueue<Integer>) IntQueueOne).append((FifoQueue<Integer>) IntQueueTwo);
		} catch (NullPointerException e) {
			//Sucess
		}
	}
	
	/**
	 * Test appending empty queue appended to non-empty queue.
	 */
	@Test
	public final void testAppendEmptyQueue() {
		IntQueueOne.offer(1);
		IntQueueOne.offer(2);
		IntQueueOne.offer(3);
		assertTrue(IntQueueTwo.isEmpty());
		((FifoQueue<Integer>) IntQueueOne).append((FifoQueue<Integer>) IntQueueTwo);
		assertTrue("Wrong size after append", IntQueueOne.size() == 3);
	}
	
	/**
	 * Test appending non empty queue appended to empty queue.
	 */
	@Test
	public final void testAppendQueueToEmpty() {
		for (int i = 0; i < IntQueueOne.size(); i++) {
			IntQueueOne.poll();
		}
		assertTrue(IntQueueOne.isEmpty());
		int nbrNodes = 10;
		for (int j = 0; j < nbrNodes; j++) {
			IntQueueTwo.offer(j);
		}
		assertTrue(IntQueueTwo.size()==10);
		((FifoQueue<Integer>) IntQueueOne).append((FifoQueue<Integer>) IntQueueTwo);
		assertTrue("Wrong size after append", IntQueueOne.size() == nbrNodes);
		assertTrue(IntQueueTwo.isEmpty());
		assertTrue(IntQueueTwo.peek() == null);
	}
}
