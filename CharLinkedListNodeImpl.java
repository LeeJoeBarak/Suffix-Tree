
public class CharLinkedListNodeImpl implements ICharLinkedListNode {

	private char data;
	private ICharLinkedListNode next;
	
	 /**
     * Defualt Constructor for the node
     * @param value -> A char
     */
	public CharLinkedListNodeImpl(char value) {
		this.data = value;
		this.next = null;
	}
	

	public char getChar() {
	
		return this.data;
	}

	
	public ICharLinkedListNode getNext() {
		
		return this.next;
	}



	public void setNext(ICharLinkedListNode next) {
		this.next = next;
		
	}


}
