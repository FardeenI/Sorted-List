import java.util.Iterator;

public class SortedList<E extends Comparable<? super E>> extends List<E>
{
    public Iterator<E> iterator()
    {
        return new Iterator<E>()
        {
            public boolean hasNext()
            {
                return curr != null;
            }


            public E next()
            {
                E temp = curr.data;
                curr = curr.next;
                return temp;
            }


            private Node<E> curr = head;
        };
    }


    public void insert(E data)
    {
        Node<E> temp = new Node<E>(data);
        head = insert(head, temp); // Aim to call private method 'insert' in order to update list, and return updated reference to beginning of list
    }


    private Node<E> insert(Node<E> curr, Node<E> node) // curr as node being used to traverse list, node as node being inserted
    {
        if (curr == null) // IF THE LIST IS EMPTY / IF THE END OF THE LIST IS REACHED
        {
            // Inserts first node if list is empty, inserts new node at end of the list otherwise
            node.next = null;
            curr = node;
        }
        else if (node.data.compareTo(curr.data) < 0) // IF INSERTION POINT IS REACHED / If data to be inserted is less than current node
        {
            // Insert new node before current node
            node.next = curr;
            curr = node;
        }
        else // If list is not empty, and if end of list has not been reached, and if insertion point has not yet been reached
        {
            // Traverse list by moving to the next node
            curr.next = insert(curr.next, node);
        }


        // Should return reference to head after updating list
        return curr;
    }



    public void remove(E data)
    {
        head = remove(head, data); // Aim to call private method 'remove' in order to update list, and return updated reference to beginning of list
    }


    private Node<E> remove(Node<E> curr, E data) // curr as node being used to traverse list, data as data element being removed
    {
        if (curr == null) // IF THE LIST IS EMPTY / IF THE END OF THE LIST IS REACHED
        {
            // Cannot remove from an empty list, return null
            return null;
        }
        else if (data.compareTo(curr.data) == 0) // If data being removed is equal to current node's data
        {
            curr = curr.next; // Move next node's next pointer to following node
        }
        else // If list is not empty, and if end of list has not been reached, and if removal point has not yet been reached
        {
            // Traverse list by moving to the next node
            curr.next = remove(curr.next, data);
        }


        // Should return reference to head after updating list
        return curr;
    }



    public E retrieve (int index)
    {
        return retrieve(head, index); // Aim to call private method 'retrieve' in order to return data at desired index, otherwise null
    }


    public E retrieve(Node<E> curr, int index) // curr as node being used to traverse list, index as index of desired element
    {
        if (curr == null) // IF THE LIST IS EMPTY / IF THE END OF THE LIST IS REACHED
        {
            // Data element does not exist within list, return null
            return null;
        }
        if (index == 0) // If retrieval point has been reached
        {
            // return data at retrieval point
            return curr.data;
        }
        else // If list is not empty, and if end of list has not yet been reached, and if retrieval point has not yet been reached
        {
            // Update index, traverse list
            index--;
            return retrieve(curr.next, index);
        }
    }



    public boolean search(E data)
    {
        return search(head, data); // Aim to call private method 'search' in order to check if data is within list
    }


    private boolean search(Node<E> curr, E data) // curr as node being used to traverse list, data as element being searched for
    {
        if (curr == null) // IF LIST IS EMPTY / IF THE END OF LIST HAS BEEN REACHED
        {
            return false; // Data element does not exist within list, return false
        }
        else if (data.compareTo(curr.data) == 0) // If data element being searched for is equal to data of current node
        {
            return true; // Data does exist within the list, return true
        }
        else // If list is not empty, and if element has not yet been found
        {
            // Traverse list
            return search(curr.next, data);
        }
    }
}