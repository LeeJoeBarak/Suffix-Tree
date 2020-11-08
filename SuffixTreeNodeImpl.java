import java.util.Iterator;

public class SuffixTreeNodeImpl extends SuffixTreeNode {

	/**
	 * Default constructor for the SuffixTreeNodeImpl class
	 */
	SuffixTreeNodeImpl() {
		super();
	}

	/**
	 * Constructor for the SuffixTreeNodeImpl class
	 * 
	 * @param Linkedlist containing the value of the node and the parent to the node
	 *                   which is SuffixTreeNode object
	 */
	SuffixTreeNodeImpl(CharLinkedList chars, SuffixTreeNode parent) {
		super(chars, parent);

	}

	/**
	 * Wrapper for the binary search method
	 * 
	 * @param c Character to search for
	 * @return A child node with c as his first character
	 */
	public SuffixTreeNode search(char c) {

		return binarySearch(c, 0, this.numOfChildren );
	}

	/**
	 * Finds and returns the node's child with target as his first character, using
	 * the binary search operation.
	 * 
	 * @param target Character to search for
	 * @param left   Left boundary index for searching in the children array
	 * @param right  Right boundary index for searching in the children array
	 * @return A child node with c as his first character, or null if no such child
	 *         exists
	 */
	public SuffixTreeNode binarySearch(char target, int left, int right) {
		int middle = (left + right) / 2;
		

		if (this.children[middle] == null) {
			return null;
		}
		
		if (target == this.children[middle].chars.firstChar()) {
			return this.children[middle];
		}

		if (right <= left) {
			return null;
		}

	
		if (target < this.children[middle].chars.firstChar()) {
			return binarySearch(target, left, middle - 1);
		}

		if (target > this.children[middle].chars.firstChar()) {
			return binarySearch(target, middle + 1, right);
		}

	
		return null;
	}

	/**
	 * Shifts all elements one cell to the right, until the "until" index,
	 * including. Assume the array is big enough even after the shifting
	 * 
	 * @param until Left boundary index of shifting
	 */
	public void shiftChildren(int until) {
	
		
		for (int slot = this.numOfChildren; slot >= until; slot--) {
		
			if (children[slot] != null && slot >= 0) {
				children[slot + 1] = children[slot];
				children[slot]=null;

			}
		

		}

	}

	   /**
     * Add a new node as a child to this node.
     * To preserve the lexicographic order, shifting some of the elements in the array might be needed.
     * Note: To compare two siblings in this tree, you need to compare only their first character, as they are surely different
     * @param node node to add
     */
	public void addChild(SuffixTreeNode node) {
		if (this.numOfChildren != 0) {
			for (int i = 0; i <= this.numOfChildren; i++) {
				
				if(this.children[i] == null) {
					this.children[i] = node; //when node.chars > this.chars \
					node.parent = this;
				}
				
				else if (this.children[i].chars.firstChar() > node.chars.firstChar()) {
					this.shiftChildren(i);
					this.children[i] = node;
					node.parent = this;
					break;
					
				}
			
			}
		}
		else {
			this.children[0] = node;
			
		}
		this.numOfChildren++;
	}

	/**
	 * Adds the suffix word[from:] to the node and recursively to its children.
	 * Since this method is called before the compression method, we can assume all
	 * nodes contain only one character each
	 * 
	 * @param word The tree's full word
	 * @param from Suffix index
	 */
	public void addSuffix(char[] word, int from) {
		SuffixTreeNodeImpl child;
		if (from != word.length) {
			char c = word[from];
			child = (SuffixTreeNodeImpl) this.search(c);
			if (child!= null) {
				child.addSuffix(word, from+1);

			} 
			else {
				child = new SuffixTreeNodeImpl(CharLinkedList.from(c), this);
				this.addChild(child);
				child.addSuffix(word, from+1);
			}
			
		}
		this.descendantLeaves++;
		
	}

	/**
	 * Compress the node and its descendents using the following rule: For each
	 * node, if it has only 1 child - merge it with his (only) child and concatenate
	 * their chars;
	 */
	public void compress() {
		
			
		if(this.getChars() == null && this.numOfChildren == 1) { 
			this.children[0].compress();
			}
			
			
		if (this.numOfChildren == 1 && this.getChars()!= null) {
				
			this.chars.append(this.children[0].chars);
				
			if(this.children[0].numOfChildren != 0) {
					
				this.descendantLeaves  = this.children[0].getDescendantLeaves();
				this.numOfChildren = this.children[0].getNumOfChildren();
				this.children = this.children[0].getChildren();
				this.children[0].parent = this;
		
			}
			else if(this.children[0].numOfChildren == 0) {
				this.numOfChildren = this.children[0].getDescendantLeaves();
				this.numOfChildren = this.children[0].getNumOfChildren();
				this.children = this.children[0].getChildren();
			
			}
			
			
		}
		if(this.numOfChildren == 1 && this.getChars() != null) { //check the number of children after the first compress
					
				this.chars.append(this.children[0].chars);
				this.numOfChildren = this.children[0].getNumOfChildren();
				this.children = this.children[0].getChildren();
				if(this.children[0] != null) {
					this.compress();
				}
				}
		else if (this.numOfChildren > 1) { // this node has 2 or more children
				for (int j = 0; j < this.numOfChildren; j++) {
		
					this.children[j].parent = this;
					this.children[j].compress();
				}
				}
					
	}

	/**
	 * Calculates the number of occurrences of subword[from:] in the tree's word
	 * Examples: new SuffixTree("mississippi").getRoot().numOfOccurrences(new
	 * Char[]{'s', 's', 'i'}, 0) -> 2, new
	 * SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'s', 's',
	 * 'i'}, 3) -> 1, new SuffixTree("mississippi").getRoot().numOfOccurrences(new
	 * Char[]{'s'}, 0) -> 4, new
	 * SuffixTree("mississippi").getRoot().numOfOccurrences(new Char[]{'m', 's'}, 0)
	 * -> 0,
	 * 
	 * @param subword Char array representing string to calculate the number of its
	 *                occurrences in tree's word
	 * @return Number of occurrences of subword in the tree's word (0 or more)
	 */
	public int numOfOccurrences(char[] subword, int from) {
		

		for (int i = 0; i <= this.numOfChildren; i++) {
			
			if(this.numOfChildren==0) {
				break;
			}
			
			if (i == this.numOfChildren) {
				return 0;
			}
		
			if (from == subword.length) {
				return this.getDescendantLeaves();
			}
			
			if (this.children[i].getChars().getFirst().getChar() == subword[from]) {
				Iterator<Character> iter = this.children[i].chars.iterator();
				while (iter.hasNext() && from != subword.length) {
					char current = iter.next();
					if (current != subword[from]) {
						return 0;
					}
					from ++;
				
				}
				if (from == subword.length) {
					return this.children[i].getDescendantLeaves();
				}
				if(from < subword.length && !iter.hasNext()) {
					return this.children[i].numOfOccurrences(subword, from);
				}
			}
		}
		
		return 0;
	}

	
	 /**
     * Given a suffix word[from:], finds the leaf representing this suffix.
     * Recurse this function all the way to the leaf, increasing the "from" index with number of chars in the current node.
     * You can assume this is indeed a suffix from this tree, and thus a leaf surely exists
     * @param word Full word of the tree
     * @param from Starting index of the suffix
     * @return The leaf representing this suffix
     */
	public SuffixTreeNode findSuffixLeaf(char[] word, int from) {
		
		if (word == null) {
			return this;
		}
		
		for (int i = 0; i <= this.numOfChildren; i++) {
			
			if(this.numOfChildren==0) {
				break;
			}
			
			if (i == this.numOfChildren) {
				return null;
			}
		
			if (from == word.length) {
				return this;
			}
			
			if (this.children[i].getChars().getFirst().getChar() == word[from]) {
				Iterator<Character> iter = this.children[i].chars.iterator();
				while (iter.hasNext() && from != word.length) {
					char current = iter.next();
					if (current != word[from]) {
						return null;
					}
					from ++;
				
				}
				if (from == word.length) {
					return this.children[i];
				}
				if(from < word.length && !iter.hasNext()) {
					return this.children[i].findSuffixLeaf(word, from);
				}
			}
		}
		
		return null;
	}

	/**
     * Finds the Least Common Ancestor of two nodes
     * @param node2 The other node
     * @return The LCA of this node and node2
     */
	public SuffixTreeNode findLCA(SuffixTreeNode node2) {
		if(this.totalWordLength == node2.totalWordLength && this == node2) {
		return this;
	}
		else if (this.totalWordLength > node2.totalWordLength) {
			return this.parent.findLCA(node2);
		}
		else {
			return node2.parent.findLCA(this);
		}
	}
}
