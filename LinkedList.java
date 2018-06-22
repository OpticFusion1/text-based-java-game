import java.util.Iterator;
 
public class LinkedList<T> implements Iterable<T> {
 
    private class Node<T> {
        T data;
        Node<T> next;
 
        public Node(T data){
            this.data = data;
        }
 
        public Node(T data, Node<T> next){
            this(data);
            this.next = next;
        }
 
        public String toString(){
            return "" + data;
        }
    }
    
    public class LLIterator<U> implements Iterator<U> {
        LinkedList<U> ll;
        LinkedList<U>.Node<U> curr;
 
        public LLIterator(LinkedList<U> ll){
            this.ll = ll;
            curr = null;
        }
 
		@Override
        public boolean hasNext(){
            if (curr == ll.tail && ll.tail != null) return false;
            return true;
        }
 
		@Override
        public U next(){
            if (curr == null)
                curr = ll.head;
            else curr = curr.next;
            return curr.data;
        }
    }
 
    private Node<T> head, tail;
    private int count;
 
    public LinkedList(){
        count = 0;
    }
 
    public void prepend (T i){
        if (count == 0){
            head = tail = new Node<T>(i);
        }
        else {
            head = new Node<T>(i, head);
        }
        count++;
    }
 
    public void append(T i){
        if (count == 0){
            head = tail = new Node<T>(i);
        }
        else {
            tail = tail.next = new Node<T>(i);
        }
        count++;
    }
	
	public void insert(T data, int index){
		if(index < 0 || index > count){
			System.out.println("IndexOutOfBoundsException");
		}
		else if(index == 0){
			prepend(data);
		}
		else if(index == count){
			append(data);
		}
		else if(index < count){
			Node<T> n = get(index);
			Node<T> n2 = get(index - 1);
			if(n != null){
				n = n.next;
				Node<T> n3 = new Node<T>(data, n); 
				n2.next = n3;
				count++;
			}
		}
	}
	
	public boolean exists(T data){
		Node<T> n = head;
		if(n != null){
			for(int i = 0; i < count; i++){
				n = n.next;
				if(n == data){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean remove(T data){
		Node<T> n = head;
		for(int i = 0; i < count; i++){
			n = n.next;
			if(n.data == data){
				n = get(count - 1);
				return true;
			}
		}
		return false;
	}
	
	public Node<T> get(int index){
		Node<T> n = head;
		for(int i = 0; i < count; i++){
			if(n != null){
				n = n.next;
				return n;
			}
		}
		return n;
	}
	

	
 
    public int size(){
        return count;
    }
 
    public boolean isEmpty(){
        return head == null;
    }
 
	@Override
    public String toString() {
        String retVal = "Linked list with " + count + " elements\nNodes:";
 
        for (Node<T> temp = head; temp != null; temp = temp.next)
            retVal += temp + " ";
        
        return retVal;
    }
	
	
	
	
 
    public Iterator<T> iterator(){
        return new LLIterator<T>(this);
    }
	

	
	
	
	
	
	public static void main(String[] args){
		/*LinkedList<String> d = new LinkedList<String>();
		LinkedList<String>.LLIterator<String> ll = d.new LLIterator<String>(d);
		
		d.append("Apple");
		d.append("Banana");
		d.prepend("Grape");
		d.insert("Kiwi", 2);
		System.out.println(d.get(1));
		for(String s : d){
			System.out.println(s);
		}
		*/
	}
}