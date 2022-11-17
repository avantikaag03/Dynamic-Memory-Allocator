// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.

    private void leftRotate(AVLTree parent)
    {
        AVLTree z = this;
        AVLTree y = this.left;
        AVLTree x = this.left.left;
        if (parent.left == this)
        {
            parent.left = this.left;
        }
        else
        {
            parent.right = this.left;
        }
        this.left.parent = parent;
        //this.height -= 1;
        this.parent = this.left;
        this.left = this.left.right;
        if (this.left != null)
        {
            this.left.parent = this;
        }
        this.parent.right = this;
        x.set_h();
        z.set_h();
        y.set_h();
        //System.out.println("z " + z.height);
        //System.out.println("y " + y.height);
        //System.out.println("x " + x.height);
        return;
    }

    private void rightRotate(AVLTree parent)
    {
        AVLTree z = this;
        AVLTree y = this.right;
        AVLTree x = this.right.right;
        if (parent.left == this)
        {
            parent.left = this.right;
        }
        else
        {
                //System.out.println("abc");
            parent.right = this.right;
                //System.out.println(parent.right.key);
        }
        this.right.parent = parent;
        //this.height -= 1;
        this.parent = this.right;
        this.right = this.right.left;
        if (this.right != null)
        {
            this.right.parent = this;
        }
        this.parent.left = this;
        z.set_h();
        x.set_h();
        y.set_h();
        //System.out.println("z " + z.height);
        //System.out.println("y " + y.height);
        //System.out.println("x " + x.height);
        return;
    }

    private void leftRightRotate(AVLTree parent)
    {
        //this.height -= 1;
        //this.left.height -= 1;
        //this.left.right.height += 1;
        AVLTree z = this;
        AVLTree y = this.left;
        AVLTree x = this.left.right;
        if (parent.left == this)
        {
            //System.out.println("f");
            parent.left = this.left.right;
            //parent.left.parent = parent;
            //System.out.println(parent.left.key);
        }
        else
        {
            parent.right = this.left.right;
        }
        this.left.right.parent = this.parent;
        //System.out.println(this.left.right.parent.key + " v");
        this.parent = x;
        this.left = x.right;
        if (this.left != null)
        {
            this.left.parent = this;
        }
        this.parent.right = this;
        y.parent = x;
        y.right = x.left;
        if (y.right != null)
        {
            y.right.parent = y;
        }
        x.left = y;
        y.set_h();
        z.set_h();
        x.set_h();
        //System.out.println("z " + z.height);
        //System.out.println("y " + y.height);
        //System.out.println("x " + x.height);
        return;
    }

    private void rightLeftRotate(AVLTree parent)
    {
        AVLTree z = this;
        AVLTree y = this.right;
        AVLTree x = this.right.left;
        //this.height -= 1;
        //this.right.height -= 1;
        //this.right.left.height += 1;
        if (parent.left == this)
        {
            parent.left = x;
        }
        else
        {
            parent.right = x;
        }
        x.parent = parent;
        this.parent = x;
        this.right = x.left;
        if (this.right != null)
        {
            this.right.parent = this;
        }
        x.left = this;
        y.parent = x;
        y.left = x.right;
        if (y.left != null)
        {
            y.left.parent = y;
        }
        x.right = y;
        z.set_h();
        y.set_h();
        x.set_h(); 
        //System.out.println("z " + z.height);
        //System.out.println("y " + y.height);
        //System.out.println("x " + x.height);
        return;
    }

    private boolean checkBal()
    {
        if (this.left != null && this.right != null)
        {
            //System.out.println("p");
            int a = this.left.height - this.right.height;
            return (a <= 1 && a >= -1);
        }
        if (this.left == null && this.right == null)
        {
            //System.out.println("q");
            return true;
        }
        if (this.left == null)
        {
            //System.out.println("r");
            return (this.right.height <= 1);
        }
        if (this.right == null)
        {
            //System.out.println("s");
            return (this.left.height <= 1);
        }
        return true;
    }

    private void balance()
    {
        AVLTree current = this;
        while (current.parent != null)
        {
            if (current.checkBal())
            {
                //System.out.println("bal true insert " + current.key);
                current.set_h();
                //System.out.println(current.key + " " + current.height);
                current = current.parent;
            }
            else
            {
                //System.out.println("bal not true insert " + current.key);
                AVLTree left = current.left;
                AVLTree right = current.right;
                if (left == null)
                {
                    //System.out.println("left is null");
                    if (right.left == null)
                    {
                        //System.out.println("a");
                        current.rightRotate(current.parent);
                    }
                    else if (right.right == null)
                    {
                        //System.out.println("b");
                        current.rightLeftRotate(current.parent);
                    }
                    else if (right.right.height > right.left.height)
                    {
                        //System.out.println("c");
                        current.rightRotate(current.parent);
                    }
                    else
                    {
                        //System.out.println("d");
                        current.rightLeftRotate(current.parent);
                    }
                    break;
                }
                else if (right == null)
                {
                    //System.out.println("right is null");
                    if (left.left == null)
                    {
                        //System.out.println("e");
                        //System.out.println(current.parent.key);
                        current.leftRightRotate(current.parent);
                    }
                    else if (left.right == null)
                    {
                        current.leftRotate(current.parent);
                    }
                    else if (left.left.height > left.right.height)
                    {
                        current.leftRotate(current.parent);
                    }
                    else
                    {
                        current.leftRightRotate(current.parent);
                    }
                    break;
                }
                else if (left.height > right.height)
                {
                    //System.out.println("left");
                    if (left.left == null)
                    {
                        current.leftRightRotate(current.parent);
                    }
                    else if (left.right == null)
                    {
                        current.leftRotate(current.parent);
                    }
                    else if (left.left.height > left.right.height)
                    {
                        current.leftRotate(current.parent);
                    }
                    else
                    {
                        current.leftRightRotate(current.parent);
                    }
                    break;
                }
                else
                {
                    //System.out.println("right");
                    if (right.left == null)
                    {
                        current.rightRotate(current.parent);
                    }
                    else if (right.right == null)
                    {
                        current.rightLeftRotate(current.parent);
                    }
                    if (right.right.height > right.left.height)
                    {
                        current.rightRotate(current.parent);
                    }
                    else
                    {
                        current.rightLeftRotate(current.parent);
                    }
                    break;
                }
            }
        }
        return;
    }

    public AVLTree Insert(int address, int size, int key) 
    {
        AVLTree current = this;
        AVLTree new_tree = new AVLTree(address, size, key);
        if (current.parent == null && current.right == null)
        {
            //System.out.println("sentinel right is null " + new_tree.key);
            this.right = new_tree;
            new_tree.parent = this;
            new_tree.height += 1;
            //System.out.println(this.right == null);
            return new_tree;
        }
        while (current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        while (current != null)
        {
            if (key < current.key && current.left != null)
            {
                current = current.left;
            }
            else if (key < current.key && current.left == null)
            {
                current.left = new_tree;
                new_tree.parent = current;
                new_tree.balance();
                return new_tree;
            }
            else if (key > current.key && current.right != null)
            {
                current = current.right;
            }
            else if (key > current.key && current.right == null)
            {
                current.right = new_tree;
                new_tree.parent = current;
                new_tree.balance();
                return new_tree;
            }
            else
            {
                if (address <= current.address && current.left != null)
                {
                    current = current.left;
                }
                else if (address <= current.address && current.left == null)
                {
                    current.left = new_tree;
                    new_tree.parent = current;
                    new_tree.balance();
                    return new_tree;
                }
                else if (address > current.address && current.right != null)
                {
                    current = current.right;
                }
                else
                {
                    current.right = new_tree;
                    new_tree.parent = current;
                    new_tree.balance();
                    return new_tree;
                }
            }
        } 
        return new_tree;
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree current = this;
        while (current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        while (current != null)
        {
            if (e.key < current.key)
            {
                current = current.left;
            }
            else if (e.key > current.key)
            {
                current = current.right;
            }
            else
            {
                if (e.address != current.address || e.size != current.size)
                {
                    if (e.address <= current.address)
                    {
                        current = current.left;
                    }
                    else
                    {
                        current = current.right;
                    }
                }
                else
                {
                    AVLTree parent = current.parent;
                    boolean a = (parent.left == current);
                    if (current.left == null && current.right == null)
                    {
                        if (a)
                        {
                            parent.left = null;
                        }
                        else
                        {
                            parent.right = null;
                        }
                        current.parent = null;
                        parent.delbal();
                        return true;
                    }
                    else if (current.left != null && current.right == null)
                    {
                        if (a)
                        {
                            parent.left = current.left;   
                        }
                        else
                        {
                            parent.right = current.left;
                        }
                        current.left.parent = parent;
                        parent.delbal();
                        current.left = null;
                        current.parent = null;
                        return true;
                    }
                    else if (current.left == null && current.right != null)
                    {
                        if (a)
                        {
                            parent.left = current.right;
                        }
                        else
                        {
                            parent.right = current.right;
                        }
                        current.right.parent = parent;
                        parent.delbal();
                        current.right = null;
                        current.parent = null;
                        return true;
                    }
                    else
                    {
                        AVLTree temp = current.right;
                        if (temp.left == null)
                        {
                            current.key = temp.key;
                            current.address = temp.address;
                            current.size = temp.size;
                            current.right = temp.right;
                            if (current.right != null)
                            {
                                current.right.parent = current;
                            }
                            current.delbal();
                            temp.parent = null;
                            temp.right = null;
                            return true;
                        }
                        while (temp.left.left != null)
                        {
                            temp = temp.left;
                        }
                        current.key = temp.left.key;
                        current.size = temp.left.size;
                        current.address = temp.left.address;
                        temp.left.parent = null;
                        AVLTree b = temp.left.right;
                        temp.left.right = null;
                        temp.left = b;
                        if (temp.left != null)
                        {
                            temp.left.parent = temp;
                        }
                        temp.delbal();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void set_h()
    {
        AVLTree current = this;
        if (current.left == null && current.right == null)
        {
            current.height = 1;
        }
        else if (current.left == null && current.right != null)
        {
            current.height = current.right.height + 1;
        }
        else if (current.left != null && current.right == null)
        {
            current.height = current.left.height + 1;
        }
        else
        {
            if (current.left.height >= current.right.height)
            {
                current.height = current.left.height + 1;
            }
            else
            {
                current.height = current.right.height + 1;
            }
        }
    }

    private void delbal()
    {
        AVLTree current = this;
        while (current.parent != null)
        {
            if (current.checkBal())
            {
                //System.out.println("Bal true " + current.key);
                current.set_h();
                //System.out.println(current.key + " " + current.height);
                current = current.parent;
            }
            else
            {
                //System.out.println("Bal not true " + current.key);
                AVLTree left = current.left;
                AVLTree right = current.right;
                if (left == null)
                {
                    //System.out.println("left is null");
                    if (right.left == null)
                    {
                        //System.out.println("a");
                        current.rightRotate(current.parent);
                    }
                    else if (right.right == null)
                    {
                        //System.out.println("b");
                        current.rightLeftRotate(current.parent);
                    }
                    else if (right.right.height > right.left.height)
                    {
                        //System.out.println("c");
                        current.rightRotate(current.parent);
                    }
                    else
                    {
                        //System.out.println("d");
                        current.rightLeftRotate(current.parent);
                    }
                }
                else if (right == null)
                {
                    //System.out.println("right is null");
                    if (left.left == null)
                    {
                        //System.out.println("e");
                        //System.out.println(current.parent.key);
                        current.leftRightRotate(current.parent);
                    }
                    else if (left.right == null)
                    {
                        current.leftRotate(current.parent);
                    }
                    else if (left.left.height > left.right.height)
                    {
                        current.leftRotate(current.parent);
                    }
                    else
                    {
                        current.leftRightRotate(current.parent);
                    }
                }
                else if (left.height > right.height)
                {
                    //System.out.println("left");
                    if (left.left == null)
                    {
                        current.leftRightRotate(current.parent);
                    }
                    else if (left.right == null)
                    {
                        current.leftRotate(current.parent);
                    }
                    else if (left.left.height > left.right.height)
                    {
                        current.leftRotate(current.parent);
                    }
                    else
                    {
                        current.leftRightRotate(current.parent);
                    }
                }
                else
                {
                    //System.out.println("right");
                    if (right.left == null)
                    {
                        current.rightRotate(current.parent);
                    }
                    else if (right.right == null)
                    {
                        current.rightLeftRotate(current.parent);
                    }
                    else if (right.right.height > right.left.height)
                    {
                        current.rightRotate(current.parent);
                    }
                    else
                    {
                        current.rightLeftRotate(current.parent);
                    }
                }
                current = current.parent;
            }
        }
        return;
    }
        
    //public AVLTree Find(int k, boolean exact)
    //{ 
    //    return null;
    //}

    private BSTree find(int key)
    {
        BSTree l, r;
        if (this.key == key)
        {
            if (this.left == null)
            {
                return this;
            }
            l = this.left.find(key);
            if (l == null)
            {
                return this;
            }
            return l;
        }
        else
        {
            if (this.right == null)
            {
                return null;
            }
            r = this.right.find(key);
            return r;
        }
    }

    public BSTree Find(int key, boolean exact)
    {
        AVLTree current = this;
        while (current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        if (current == null)
        {
            return null;
        }
        if (exact)
        {
            while (current != null)
            {
                if (current.key == key)
                {
                    return current.find(key);
                }
                else if (current.key > key)
                {
                    current = current.left;
                }
                else
                {
                    current = current.right;
                } 
            }
            return null;
        }
        else
        {
            AVLTree node_min = current;
            while (current != null)
            {
                if (current.key == key)
                {
                    return current.find(key);
                }
                else if (current.key < key)
                {
                    current = current.right;
                }
                else
                {
                    node_min = current;
                    current = current.left;
                }
            }
            if (node_min.key > key)
            {
                return node_min;
            }
            return null;
        }
    }

    public AVLTree getFirst()
    {
        AVLTree current = this;
        //System.out.println(current.parent.key);
        while (current.parent != null)
        {
            //System.out.println("D");
            current = current.parent;
        }
        if (current.parent == null && current.right == null)
        {
            //System.out.println("C");
            return null;
        }
        if (current.parent == null && current.right != null)
        {
            //System.out.println("A");
            current = current.right;
        }
        while (current.left != null)
        {
            //System.out.println("B");
            current = current.left;
        } 
        return current; 
        //return null;
    }

    public AVLTree getNext()
    {
        if (this.parent == null)
        {
            return null;
        }
        if (this.right == null && this.parent.left == this)
        {
            return this.parent;
        }
        if (this.right == null && this.parent.right == this)
        {
            AVLTree current = this;
            while (current.parent.right == current)
            {
                if (current.parent.parent == null)
                {
                    return null;
                }
                current = current.parent;
            }
            return current.parent;
        }
        if (this.right != null)
        {
            return this.right.getfirst();
        } 
        return null;
        //return null;
    }

    private AVLTree getfirst()
    {
        AVLTree current = this;
        if (current.parent == null && current.right == null)
        {
            return null;
        }
        if (current.parent == null && current.right != null)
        {
            current = current.right;
        }
        while (current.left != null)
        {
            current = current.left;
        } 
        return current;
    }

    public boolean sanity()
    {
        if (this.parent == null && this.right == null)
        {
            return true;
        }
        if (this.parent == null)
        {
            return this.right.sanity();
        }
        AVLTree current = this.getFirst();
        while (current != null)
        {
            if (!current.checkBal())
            {
                return false;
            }
            if (current.getNext() != null && current.key > current.getNext().key)
            {
                return false;
            }
            if (current.left != null && current.left.parent != current)
            {
                return false;
            }
            if (current.right != null && current.right.parent != current)
            {
                return false;
            }
            current = current.getNext();
        } 
        return true; 
    }

    private void preorder()
    {
        System.out.println(this.key);
        if (this.left != null)
        {
            this.left.preorder();
        }
        if (this.right != null)
        {
            this.right.preorder();
        }
        return;
    }
}


