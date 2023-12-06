/**
 * 
 */
package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.classes.BSTree;

/**
 * @author Ruthless
 *
 */
public class BSTreeTest<E extends Comparable<? super E>> {
	
	private BSTree<E> tree;

	@Before
	public void setUp() throws Exception {
		tree = new BSTree<>();
	}

	public void tearDown() throws Exception {
		tree = null;
	}

    @Test
    public void testAdd() {
        assertTrue(tree.isEmpty());
        tree.add((E) Integer.valueOf(10));
        assertFalse(tree.isEmpty());
        assertEquals(1, tree.size());
        assertEquals(1, tree.getHeight());
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        assertEquals(3, tree.size());
        assertEquals(2, tree.getHeight());
    }

    @Test
    public void testRemoveMin() {
        assertNull(tree.removeMin());
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        assertEquals(5, ((Integer) tree.removeMin().getData()).intValue());
        assertEquals(2, tree.size());
        assertEquals(1, tree.getHeight());
        assertEquals(10, ((Integer) tree.getRoot().getData()).intValue());
    }

    @Test
    public void testRemoveMax() {
        assertNull(tree.removeMax());
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        assertEquals(15, ((Integer) tree.removeMax().getData()).intValue());
        assertEquals(2, tree.size());
        assertEquals(1, tree.getHeight());
        assertEquals(10, ((Integer) tree.getRoot().getData()).intValue());
    }

    @Test
    public void testSearch() {
        assertFalse(tree.contains((E) Integer.valueOf(10)));
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        assertTrue(tree.contains((E) Integer.valueOf(10)));
        assertTrue(tree.contains((E) Integer.valueOf(5)));
        assertTrue(tree.contains((E) Integer.valueOf(15)));
        assertFalse(tree.contains((E) Integer.valueOf(20)));
    }

    @Test
    public void testClear() {
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    // Add more test cases for other methods

    @Test
    public void testIsEmpty() {
        assertTrue(tree.isEmpty());
        tree.add((E) Integer.valueOf(10));
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
    }
}
