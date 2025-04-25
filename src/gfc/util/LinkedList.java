package gfc.util;

public class LinkedList
{
    private final LinkedListElement head = new LinkedListElement();


	/** Implements a doubly linked list */
	public LinkedList()
    {
		//empty constructor
    }


	public boolean isEmpty()
    {
        return head.next == null;
    }


	public LinkedListElement getFirst()
    {
        return head.next;
    }


	public LinkedListElement getNext(LinkedListElement li)
    {
        if ((li.next == null) && (li.prev == null))
        {
            throw new IllegalArgumentException(
                "getNext from item not in list");
        }
        return li.next;
    }


	public void remove(LinkedListElement li)
    {
        li.remove();
    }


	public void addFirst(LinkedListElement li)
    {
        if ((li.next != null) || (li.prev != null))
        {
            throw new IllegalArgumentException(
                "addFirst item already in list");
        }
        li.next = head.next;
        if (li.next != null)
        {
            li.next.prev = li;
        }
        li.prev = head;
        head.next = li;
    }
}
