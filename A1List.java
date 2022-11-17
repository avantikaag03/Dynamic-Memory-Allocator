// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {   
        A1List new_el = new A1List(address, size, key);
        this.next.prev = new_el;
        new_el.next = this.next;
        this.next = new_el;
        new_el.prev = this;
        return new_el;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List current = this.getFirst();
        if (current == null)
        {
            return false;
        }
        while (current.next != null)
        {
            if (current.key == d.key && current.address == d.address && current.size == d.size)
            {
                if (current == this)
                {
                    current.key = current.prev.key;
                    current.address = current.prev.address;
                    current.size = current.prev.size;
                    current.prev = current.prev.prev;
                    if (current.prev != null)
                    {
                        current.prev.next = current;
                    }
                    return true;
                }
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current.next = null;
                current.prev = null;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    {
        A1List current = this.getFirst();
        if (current == null)
        {
            return null;
        }
        if (exact)
        {
            while (current.next != null)
            {
                if (current.key == k)
                {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
        else
        {
            while (current.next != null)
            {
                if (current.key >= k)
                {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
    }

    public A1List getFirst()
    {
        if (this.prev == null && this.next.next == null)
        {
            return null;
        }
        else if (this.prev == null && this.next.next != null)
        {
            return this.next;
        }
        else if (this.next == null && this.prev.prev == null)
        {
            return null;
        }
        else
        {
            A1List current = this;
            while (current.prev.prev != null)
            {
                current = current.prev;
            }
            return current;
        }
    }
    
    public A1List getNext() 
    {
        if (this.next.next == null)
        {
            return null;
        }
        return this.next;
    }

    public boolean sanity()
    {
        if (this == null)
        {
            return false;
        }
        if (this.prev == null && this.next != null && this.next.next == null)
        {
            return true;
        }
        if (this.next == null && this.prev != null && this.prev.prev == null)
        {
            return true;
        }
        if (this.prev == null && this.next == null)
        {
            return false;
        }
        A1List current = this;
        A1List fast = current;
        while (current != null && fast != null && fast.next != null)
        {
            current = current.next;
            fast = fast.next.next;
            if (current == fast)
            {
                return false;
            }
        }
        current = this;
        fast = current;
        while (current != null && fast != null && fast.prev != null)
        {
            current = current.prev;
            fast = fast.prev.prev;
            if (current == fast)
            {
                return false;
            }
        }
        current = this;
        while (current.next != null)
        {
            if (current.next.prev != current)
            {
                return false;
            }
            if (current.prev.next != current)
            {
                return false;
            }
            current = current.next;
        }
        current = this;
        while (current.prev != null)
        {
            if (current.next.prev != current)
            {
                return false;
            }
            if (current.prev.next != current)
            {
                return false;
            }
            current = current.prev;
        }
        return true;
    }

}


