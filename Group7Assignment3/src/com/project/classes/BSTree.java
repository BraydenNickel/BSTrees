package com.project.classes;
import java.util.NoSuchElementException;
import java.util.Stack;

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
    public Iterator<E> inorderIterator() {
        return new Iterator<E>() {
            private Stack<BSTreeNode<E>> stack = new Stack<>();
            private BSTreeNode<E> current = root;

            @Override
            public boolean hasNext() {
                return current != null || !stack.isEmpty();
            }

            @Override
            public E next() {
                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                BSTreeNode<E> node = stack.pop();
                current = node.getRight();

                return node.getData();
            }
        };
    }

    public Iterator<E> preorderIterator() {
        return new Iterator<E>() {
            private Stack<BSTreeNode<E>> stack = new Stack<>();

            {
                if (root != null) {
                    stack.push(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                BSTreeNode<E> node = stack.pop();
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }

                return node.getData();
            }
        };
    }

    public Iterator<E> postorderIterator() {
        return new Iterator<E>() {
            private Stack<BSTreeNode<E>> stack = new Stack<>();
            private BSTreeNode<E> current = root;
            private BSTreeNode<E> prev = null;

            @Override
            public boolean hasNext() {
                return current != null || !stack.isEmpty();
            }

            @Override
            public E next() {
                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
                }

                BSTreeNode<E> node = stack.peek();

                if (node.getRight() == null || node.getRight() == prev) {
                    stack.pop();
                    prev = node;
                    current = null; // Move to the next iteration
                    return node.getData();
                } else {
                    current = node.getRight();
                    return next(); // Recursively go to the right subtree
                }
            }
        };
    }

}
