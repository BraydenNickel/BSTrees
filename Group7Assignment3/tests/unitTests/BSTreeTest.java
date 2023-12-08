/**
 * 
 */
package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.classes.BSTree;
import com.project.classes.BSTreeNode;
import com.project.interfaces.Iterator;

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

	@After
	public void tearDown() throws Exception {
		tree = null;
	}
	
	@Test
	public void testGetRoot( ) {
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        
        BSTreeNode<E> root = tree.getRoot();
        
        BSTreeNode<E> expectedRoot = new BSTreeNode<>((E) Integer.valueOf(10));
        expectedRoot.setLeft(new BSTreeNode<>((E) Integer.valueOf(5)));
        expectedRoot.setRight(new BSTreeNode<>((E) Integer.valueOf(15)));

        assertEquals(expectedRoot.getData(), root.getData());
        assertEquals(expectedRoot.getLeft().getData(), root.getLeft().getData());
        assertEquals(expectedRoot.getRight().getData(), root.getRight().getData());
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
        assertEquals(2, tree.getHeight());
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
        assertEquals(2, tree.getHeight());
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

    @Test
    public void testGetHeight() {
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        
        assertEquals(2, tree.getHeight());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(tree.isEmpty());
        tree.add((E) Integer.valueOf(10));
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
    }
    
    @Test
    public void testContains() {
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        assertTrue(tree.contains((E) Integer.valueOf(10)));
        assertFalse(tree.contains((E) Integer.valueOf(50)));
        
    }
    
    @Test
    public void testSize() {
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));
        assertEquals(3, tree.size());
    }
    
    @Test
    public void testInorderIteratorWithElements() {
        // Add elements to the tree for testing
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));

        Iterator<E> iterator = tree.inorderIterator();
        List<E> result = iterateToList(iterator);

        List<Integer> expected = Arrays.asList(5, 10, 15);
        assertEquals(expected, result);
    }

    @Test
    public void testPreorderIteratorWithElements() {
        // Add elements to the tree for testing
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));

        Iterator<E> iterator = tree.preorderIterator();
        List<E> result = iterateToList(iterator);

        List<Integer> expected = Arrays.asList(10, 5, 15);
        assertEquals(expected, result);
    }

    @Test
    public void testPostorderIteratorWithElements() {
        // Add elements to the tree for testing
        tree.add((E) Integer.valueOf(10));
        tree.add((E) Integer.valueOf(5));
        tree.add((E) Integer.valueOf(15));

        Iterator<E> iterator = tree.postorderIterator();
        List<E> result = iterateToList(iterator);

        List<Integer> expected = Arrays.asList(5, 15, 10);
        assertEquals(expected, result);
    }

    private <E> List<E> iterateToList(Iterator<E> iterator) {
        List<E> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
