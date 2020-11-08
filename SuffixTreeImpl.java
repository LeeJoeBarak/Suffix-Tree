
public class SuffixTreeImpl extends SuffixTree {

	public SuffixTreeImpl(String word) {
		super(word);
		// TODO Auto-generated constructor stub
	}

	/**
     * Returns true if and only if the tree's word contains the specified subword.
     * Examples: new SuffixTree("mississippi").contains("ssi") -> true,
     * new SuffixTree("mississippi").contains("ms") -> false
     * @param subword String to check if contained in tree's word
     * @return True if and only if the tree's word contains the specified subword
     */
	public boolean contains(String subword) {
		char[] sub = subword.toCharArray();
		return this.getRoot().numOfOccurrences(sub, 0) > 0;
	}

	  /**
     * Calculates the number of occurrences of subword in the tree's word
     * Examples: new SuffixTree("mississippi").numOfOccurrences("ssi") -> 2,
     * new SuffixTree("mississippi").numOfOccurrences("s") -> 4,
     * new SuffixTree("mississippi").numOfOccurrences("ms") -> 0
     * @param subword String to calculate the number of its occurrences in tree's word
     * @return Number of occurrences of subword in the tree's word (0 or more)
     */
	public int numOfOccurrences(String subword) {
		
		char[] sub = subword.toCharArray();
		return this.getRoot().numOfOccurrences(sub , 0);
	}

}
