public abstract class ReverseSuffixTree extends CompressedTrie {

    protected char[] word;
    protected char[] reverseWord;

    /**
     * Constructs a reverse suffix tree containing all suffices of a single word in both normal and reverse order.
     * A dollar and hashtag signs are appended to the end of the normal order and reverse order words respectively
     * to distinguish between them
     * @param word The word to create a tree of all its suffices
     */
    public ReverseSuffixTree(String word) {
        super();
        this.word = (word + "$").toCharArray();
        this.reverseWord = new StringBuilder(word).reverse().append('#').toString().toCharArray();
        for (int i = 0; i< this.word.length; i++)
            addSuffix(this.word, i);
        for (int i = 0; i< this.reverseWord.length; i++)
            addSuffix(this.reverseWord, i);
        compressTree();
    }

    /**
     * Calculates and returns the longest palindrome in the tree's word.
     * If the palindrome length is uneven then the median character is replaced with 'X'.
     * If there are multiple longest palindromes, then the first one is returned.
     * Examples: new SuffixTree("mississippi").longestPalindrome() -> "issXssi",
     * new SuffixTree("xabccbay").longestPalindrome() -> "abccba",
     * new SuffixTree("abcd").longestPalindrome() -> "X",
     * new SuffixTree("a").longestPalindrome() -> "X",
     * new SuffixTree("aabbc").longestPalindrome() -> "aa"
     * @return Longest palindrome in the tree's word
     */
    public abstract String longestPalindrome();

}
