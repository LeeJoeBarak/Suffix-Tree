
public class ReverseSuffixTreeImpl extends ReverseSuffixTree {

	public ReverseSuffixTreeImpl(String word) {
		super(word);
		// constructor for the ReverseSuffixTree
	}


	public String longestPalindrome() {
		
		//Check for the longest equal palindrome
		
		if (word.length == 2) {
			return "X";
		}
	
		String max_LCP_even="";
		for (int i = 0; i < word.length-1; i++) {
			SuffixTreeNode A = this.getRoot().findSuffixLeaf(reverseWord, word.length-i-1);
			SuffixTreeNode B = this.getRoot().findSuffixLeaf(word, i);
			SuffixTreeNode LCA= A.findLCA(B);
			String LCP_temp_even=LCA.restoreStringAlongPath();
			String reverseWordLC= new StringBuilder(LCP_temp_even).reverse().toString();
			LCP_temp_even= reverseWordLC + LCP_temp_even;
			if (LCP_temp_even.length()>max_LCP_even.length()) {
				max_LCP_even=LCP_temp_even;
			
			}
			
		}
		
		//Check for the longest odd length's palindrome

		String max_LCP_odd="";
		

		for (int i = 1; i < word.length-1; i++) {
			SuffixTreeNode A = this.getRoot().findSuffixLeaf(reverseWord, reverseWord.length-i);
			SuffixTreeNode B = this.getRoot().findSuffixLeaf(word, i);
			
			SuffixTreeNode LCA= A.findLCA(B);
			String LCP_temp_odd=LCA.restoreStringAlongPath();
			String reverseWordLC= new StringBuilder(LCP_temp_odd).reverse().toString();
			LCP_temp_odd =  reverseWordLC + 'X' + LCP_temp_odd;
			if (LCP_temp_odd.length()>max_LCP_odd.length()) {
				max_LCP_odd=LCP_temp_odd;

			}
		}
		
		if (word.length == 1) {
			max_LCP_odd = "X";
		}
		
		
		if (max_LCP_even.length()> max_LCP_odd.length()) {
			return max_LCP_even;
		}
	
		
		return max_LCP_odd;
		
		
	}
	
	

}
