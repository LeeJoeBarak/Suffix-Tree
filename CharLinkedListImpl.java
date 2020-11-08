
public class CharLinkedListImpl extends CharLinkedList {
	
	public CharLinkedListImpl(){
	super();
	}
	 /**
     * Adds a new character to the end of the list
     * @param c Character to add
     */
	public void add(char c) {
		CharLinkedListNodeImpl node = new CharLinkedListNodeImpl(c);
		if (this.first == null) {
			this.first = node;
			this.last = node;
		}
		else {
			this.last.setNext(node);
			this.last = node;
		}
	}

	
	public char firstChar() {
		
		return this.first.getChar();
	}

	  /**
     * Calculates the size of the list
     * @return The number of characters in the list
     */
	public int size() {
		int res = 1;
		if(this.first == null) {
			return 0;
		}
		
		ICharLinkedListNode temp = this.first;
		while(temp.getNext() != null) {
			temp = temp.getNext();
			res ++;
		}
		return res;
	}

	/**
     * Appends a list to the end of this list
     * @param toAppend The list to be appended at the end of this list
     */
	public void append(CharLinkedList toAppend) {
		this.last.setNext(toAppend.first);
		this.last = toAppend.last;
		
	}

}
