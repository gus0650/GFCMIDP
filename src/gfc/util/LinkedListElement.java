package gfc.util;

public class LinkedListElement
{
    LinkedListElement next = null;
    LinkedListElement prev = null;


    protected LinkedListElement()
    {
    	//empty constructor
    }


    public void remove()
    {
        if ((next == null) && (prev == null))
        {
            throw new IllegalArgumentException("remove item not in a list");
        }

        if (next != null)    // not last in list
        {
            next.prev = prev;
        }
        prev.next = next;

        next = null;
        prev = null;
    }
}
