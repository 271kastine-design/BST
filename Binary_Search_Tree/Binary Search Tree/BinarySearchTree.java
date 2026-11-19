import java.util.*;

/**
 * This BinarySearchTree object defines a reference based binary search tree
 * 
 * @author  
 * @version 
 */

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>
{
    protected BinaryNode<T> root;      // reference to the root of this BST

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return root == null;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        return size(root);
    }

    private int size(BinaryNode<T> tree)
    {
        if (tree == null)
            return 0;
        return 1 + size(tree.getLeft()) + size(tree.getRight());
    }

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        if (root == null)
        {
            root = new BinaryNode<T>(element);
            return;
        }

        BinaryNode<T> current = root;
        while (true)
        {
            int comparison = element.compareTo(current.getInfo());
            if (comparison == 0)
                return;
            else if (comparison < 0)
            {
                if (current.getLeft() == null)
                {
                    current.setLeft(new BinaryNode<T>(element));
                    return;
                }
                current = current.getLeft();
            }
            else
            {
                if (current.getRight() == null)
                {
                    current.setRight(new BinaryNode<T>(element));
                    return;
                }
                current = current.getRight();
            }
        }
    }

    // Returns true if this BST contains an element e such that 
    // e.compareTo(element) == 0; otherwise, returns false.
    public boolean contains (T element)
    {
        BinaryNode<T> current = root;
        while (current != null)
        {
            int comparison = element.compareTo(current.getInfo());
            if (comparison == 0)
                return true;
            current = comparison < 0 ? current.getLeft() : current.getRight();
        }
        return false;
    }

    // Returns a graphical representation of the tree
    public String toString()
    {
        return toString(root, 0);
    }

    private String toString(BinaryNode<T> tree, int level)
    {
        String str = "";
        if (tree != null)
        {
            str += toString(tree.getRight(), level + 1);
            for (int i = 1; i <= level; ++i)
                str = str + "| ";
            str += tree.getInfo().toString() + "\n";
            str += toString(tree.getLeft(), level + 1);
        }
        return str;
    }

    // Returns a list of elements from a preorder traversal
    public List<T> preorderTraverse()
    {
        List<T> tempList = new ArrayList<>();
        Stack<BinaryNode<T>> stack = new Stack<>();
        if (root != null)
            stack.push(root);

        while (!stack.isEmpty())
        {
            BinaryNode<T> current = stack.pop();
            tempList.add(current.getInfo());
            if (current.getRight() != null)
                stack.push(current.getRight());
            if (current.getLeft() != null)
                stack.push(current.getLeft());
        }
        return tempList;
    }

    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse()
    {
        List<T> tempList = new ArrayList<>();
        Stack<BinaryNode<T>> stack = new Stack<>();
        BinaryNode<T> current = root;
        while (current != null || !stack.isEmpty())
        {
            while (current != null)
            {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            tempList.add(current.getInfo());
            current = current.getRight();
        }
        return tempList;
    }

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        List<T> tempList = new ArrayList<>();
        Stack<BinaryNode<T>> stack = new Stack<>();
        Stack<BinaryNode<T>> reverseStack = new Stack<>();
        if (root != null)
            stack.push(root);

        while (!stack.isEmpty())
        {
            BinaryNode<T> current = stack.pop();
            reverseStack.push(current);
            if (current.getLeft() != null)
                stack.push(current.getLeft());
            if (current.getRight() != null)
                stack.push(current.getRight());
        }
        while (!reverseStack.isEmpty())
            tempList.add(reverseStack.pop().getInfo());
        return tempList;
    }

	// Removes an element e from this BST such that e.compareTo(element) == 0
	public void remove(T element)
	{
		root = recRemove(element, root);
	}

	private BinaryNode<T> recRemove(T target, BinaryNode<T> node)
	{
		if (node == null)
			return null;

		int comparison = target.compareTo(node.getInfo());
		if (comparison < 0)
			node.setLeft(recRemove(target, node.getLeft()));
		else if (comparison > 0)
			node.setRight(recRemove(target, node.getRight()));
		else
			return removeNode(node);
		return node;
	}

	private BinaryNode<T> removeNode(BinaryNode<T> node)
	{
		if (node.getLeft() == null)
			return node.getRight();
		if (node.getRight() == null)
			return node.getLeft();

		T predecessor = getPredecessor(node.getLeft());
		node.setInfo(predecessor);
		node.setLeft(recRemove(predecessor, node.getLeft()));
		return node;
	}

	public T getPredecessor(BinaryNode<T> subtree)
	{
		BinaryNode<T> current = subtree;
		while (current.getRight() != null)
			current = current.getRight();
		return current.getInfo();
	}

    // Restructures this BST to be optimally balanced
    public void balance()
    {
        List<T> elements = inorderTraverse();
        root = buildBalanced(elements, 0, elements.size() - 1);
    }

    private BinaryNode<T> buildBalanced(List<T> elements, int low, int high)
    {
        if (low > high)
            return null;

        int mid = low + (high - low) / 2;
        BinaryNode<T> node = new BinaryNode<T>(elements.get(mid));
        node.setLeft(buildBalanced(elements, low, mid - 1));
        node.setRight(buildBalanced(elements, mid + 1, high));
        return node;
    }

}