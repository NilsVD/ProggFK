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
	public final void testTwoEmptyQueues() { // FINAL!?!?!?!?! :O
		assertTrue(IntQueueOne.isEmpty());
		assertTrue(IntQueueOne.size() == 0);
		assertTrue(IntQueueTwo.isEmpty());
		assertTrue(IntQueueTwo.size() == 0);
		try {
			((FifoQueue<Integer>) IntQueueOne).append((FifoQueue<Integer>) IntQueueTwo); // varför (FifoQueue<Integer>) 
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
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
		assertTrue("Wrong size after append", IntQueueTwo.size() == 0);
	}
	
	/**
	 * Test appending non empty queue appended to empty queue.
	 */
	@Test
	public final void testAppendQueueToEmpty() {
		assertTrue(IntQueueOne.isEmpty());
		int nbrNodes = 10;
		for (int j = 0; j < nbrNodes; j++) {
			IntQueueTwo.offer(j);
		}
		assertTrue(IntQueueTwo.size()==10);
		((FifoQueue<Integer>) IntQueueOne).append((FifoQueue<Integer>) IntQueueTwo);
		assertTrue("Wrong size after append", IntQueueOne.size() == nbrNodes);
		assertTrue("Appended que is not empty", IntQueueTwo.isEmpty());
		assertTrue("Appended que is not empty", IntQueueTwo.peek() == null);
	}
	
	/**
	 * Test appending non empty queue appended to non empty queue.
	 */
	@Test
	public final void testAppendQueueToQueue() {
		for (int j = 0; j < 5; j++) {
			IntQueueOne.offer(j);
		}
		for (int j = 0; j < 7; j++) {
			IntQueueTwo.offer(j+5);
		}
		assertTrue(IntQueueOne.size()==5);
		assertTrue(IntQueueTwo.size()==7);
		((FifoQueue<Integer>) IntQueueOne).append((FifoQueue<Integer>) IntQueueTwo);
		assertTrue("Wrong size after append", IntQueueOne.size() == 12);
		assertTrue("Appended que is not empty", IntQueueTwo.isEmpty());
		assertTrue("Appended que is not empty", IntQueueTwo.peek() == null);
		for(int k = 0; k < 12; k++) {
			assertEquals("Wrong result from poll", Integer.valueOf(k), IntQueueOne.poll());
		}
		
	}
	
	// Test appending identical queues
	@Test
	public final void testAppendIdenticalQueue() {
		for (int j = 0; j < 5; j++) {
			IntQueueOne.offer(j);
		}
		try {
			((FifoQueue<Integer>) IntQueueOne).append((FifoQueue<Integer>) IntQueueOne);
			fail("Should raise IllegalArgumentException");
		}
		catch(IllegalArgumentException e) {
			// Success
		}
		
	}
}
