// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    {
        BSTree new_el = new BSTree(address, size, key);
        BSTree current = this;
        while (current.parent != null)
        {
            current = current.parent;
        }
        if (current.right == null)
        {
            current.right = new_el;
            new_el.parent = current;
            return new_el;
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
                current.left = new_el;
                new_el.parent = current;
                return new_el;
            }
            else if (key > current.key && current.right != null)
            {
                current = current.right;
            }
            else if (key > current.key && current.right == null)
            {
                current.right = new_el;
                new_el.parent = current;
                return new_el;
            }
            else
            {
                if (address <= current.address && current.left != null)
                {
                    current = current.left;
                }
                else if (address <= current.address && current.left == null)
                {
                    current.left = new_el;
                    new_el.parent = current;
                    return new_el;
                }
                else if (address > current.address && current.right != null)
                {
                    current = current.right;
                }
                else
                {
                    current.right = new_el;
                    new_el.parent = current;
                    return new_el;
                }
            }
        }
        return new_el;
    }

    public boolean Delete(Dictionary e)
    {
        BSTree current = this;
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
            else if (e.key == current.key)
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
                    BSTree parent = current.parent;
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
                        current.right = null;
                        current.parent = null;
                        return true;
                    }
                    else
                    {
                        BSTree temp = current.right;
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
                        BSTree b = temp.left.right;
                        temp.left.right = null;
                        temp.left = b;
                        if (temp.left != null)
                        {
                            temp.left.parent = temp;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

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
        BSTree current = this;
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
            BSTree node_min = current;
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

    public BSTree getFirst()
    {
        BSTree current = this;
        while (current.parent != null)
        {
            current = current.parent;
        }
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

    private BSTree getfirst()
    {
        BSTree current = this;
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

    public BSTree getNext()
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
            BSTree current = this;
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
        BSTree current = this.getFirst();
        while (current != null)
        {
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

}


 


