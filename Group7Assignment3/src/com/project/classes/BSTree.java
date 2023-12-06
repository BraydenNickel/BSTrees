package com.project.classes;
import com.project.interfaces.BSTreeADT;
import com.project.interfaces.Iterator;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {
	
	private BSTreeNode<E> root;

	@Override
	public BSTreeNode<E> getRoot() {
		return root;
	}

    @Override
    public int getHeight() {
        return calculateHeight(root);
    }

    private int calculateHeight(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(calculateHeight(node.getLeft()), calculateHeight(node.getRight()));
    }

    @Override
    public int size() {
        return countNodes(root);
    }

    private int countNodes(BSTreeNode<E> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
        }
    }
    

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean contains(E entry) throws NullPointerException {
		if (entry == null) {
			throw new NullPointerException("Contains null");
		}
		return search(entry) != null;
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException {
		if (entry == null) {
			throw new NullPointerException("Tree is null");
		}
		return searchNode(root, entry);
	}
	private BSTreeNode<E> searchNode(BSTreeNode<E> node, E entry){
		if (node == null || node.getData().equals(entry)) {
			return node;
		}
		if (entry.compareTo(node.getData()) < 0) {
			return searchNode(node.getLeft(), entry);
		} else {
			return searchNode(node.getRight(), entry);
		}
	}

    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            throw new NullPointerException("Null entry not allowed");
        }
        root = addNode(root, newEntry);
        return true;
    }

    private BSTreeNode<E> addNode(BSTreeNode<E> node, E newEntry) {
        if (node == null) {
            return new BSTreeNode<>(newEntry);
        }

        if (newEntry.compareTo(node.getData()) < 0) {
            node.setLeft(addNode(node.getLeft(), newEntry));
        } else if (newEntry.compareTo(node.getData()) > 0) {
            node.setRight(addNode(node.getRight(), newEntry));
        }

        return node;
    }


    @Override
    public BSTreeNode<E> removeMin() {
        if (isEmpty()) {
            return null;
        }

        BSTreeNode<E> parent = null;
        BSTreeNode<E> current = root;

        while (current.getLeft() != null) {
            parent = current;
            current = current.getLeft();
        }

        if (parent == null) {
            root = current.getRight();
        } else {
            parent.setLeft(current.getRight());
        }

        return current;
    }
    
    @Override
    public BSTreeNode<E> removeMax() {
        if (isEmpty()) {
            return null;
        }

        BSTreeNode<E> parent = null;
        BSTreeNode<E> current = root;

        while (current.getRight() != null) {
            parent = current;
            current = current.getRight();
        }

        if (parent == null) {
            root = current.getLeft();
        } else {
            parent.setRight(current.getLeft());
        }

        return current;
    }
	@Override
	public Iterator<E> inorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> preorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> postorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
