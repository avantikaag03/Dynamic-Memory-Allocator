// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        if (this.type == 2)
        {
            BSTree tree = new BSTree();
            Dictionary current = freeBlk.getFirst();
            if (current == null)
            {
                return;
            }
            while (current != null)
            {
                tree.Insert(current.address, current.size, current.address);
                current = current.getNext();
            }
            current = tree.getFirst();
            while (current.getNext() != null)
            {
                if (current.address + current.size == current.getNext().address)
                {
                    Dictionary next = current.getNext();
                    int s1 = current.size;
                    int s2 = next.size;
                    int a1 = current.address;
                    int a2 = next.address;
                    freeBlk.Delete(new BSTree(a1, s1, s1));
                    freeBlk.Delete(new BSTree(a2, s2, s2));
                    freeBlk.Insert(a1, s1 + s2, s1 + s2);
                    tree.Delete(current);
                    tree.Delete(next);
                    current = tree.Insert(a1, s1 + s2, a1);
                }
                else
                {
                    current = current.getNext();
                }
            }
            tree = null;
            return;
        }
        else if (this.type == 3)
        {
            AVLTree tree = new AVLTree();
            Dictionary current = freeBlk.getFirst();
            if (current == null)
            {
                return;
            }
            while (current != null)
            {
                tree.Insert(current.address, current.size, current.address);
                current = current.getNext();
            }
            current = tree.getFirst();
            while (current.getNext() != null)
            {
                if (current.address + current.size == current.getNext().address)
                {
                    Dictionary next = current.getNext();
                    int s1 = current.size;
                    int s2 = next.size;
                    int a1 = current.address;
                    int a2 = next.address;
                    freeBlk.Delete(new AVLTree(a1, s1, s1));
                    freeBlk.Delete(new AVLTree(a2, s2, s2));
                    freeBlk.Insert(a1, s1 + s2, s1 + s2);
                    tree.Delete(current);
                    tree.Delete(next);
                    current = tree.Insert(a1, s1 + s2, a1);
                }
                else
                {
                    current = current.getNext();
                }
            }
            tree = null;
            return;
        }
        else
        {
            return;
        }
    }
}