// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if (blockSize <= 0)
        {
            return -1;
        }
        Dictionary block = freeBlk.Find(blockSize, false);
        if (block != null)
        {
            if (block.size > blockSize)
            {
                A1List new_free = new A1List(block.address + blockSize, block.size - blockSize, block.size - blockSize);
                A1List new_alloc = new A1List(block.address, blockSize, block.address);
                freeBlk.Delete(block);
                freeBlk.Insert(new_free.address, new_free.size, new_free.key);
                allocBlk.Insert(new_alloc.address, new_alloc.size, new_alloc.key);
                return new_alloc.address;     
            }
            else
            {
                int address = block.address;
                int size = block.size;
                int key = block.address;
                freeBlk.Delete(block);
                allocBlk.Insert(address, size, key);
                return address;
            }
        }
        return -1;
    } 
    
    public int Free(int startAddr) {
        if (startAddr < 0)
        {
            return -1;
        }
        Dictionary current = allocBlk.Find(startAddr, true);
        if (current == null)
        {
            return -1;
        }
        int address = current.address;
        int size = current.size;
        int key = current.size;
        allocBlk.Delete(current);
        freeBlk.Insert(address, size, key);
        return 0;
    }
}