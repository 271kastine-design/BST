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
        return null;
    }

    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse()
    {
        return null;
    }

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        return null;
    }

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
    }

    // Restructures this BST to be optimally balanced
    public void balance()
    {
    }
}