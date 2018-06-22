class BST<T extends Comparable<T>>{
		
		
		private class BSTNode<T> {
			T value;
			BSTNode<T> left;
			BSTNode<T> right;
 
			public BSTNode(T value){
				this.value = value;
			}
			
			private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
				if(right!=null) {
					right.toString(new StringBuilder().append(prefix).append(isTail ? "|   " : "    "), false, sb);
				}
				sb.append(prefix).append(isTail ? "|__ " : "|-- ").append(value).append("\n");
				if(left!=null) {
					left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "|   "), true, sb);
            }
            return sb;
        }
 
			@Override
			public String toString() {
				return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
			}
		}
		
		public int compareTo(T t){
			if(this.compareTo(t) > 0) return 1;
			if(this.compareTo(t) < 0) return -1;
			else return 0;
			
		}
		
		private BSTNode<T> root;
		
		private BSTNode<T> insert(BSTNode<T> curr, T value){
			if(curr == null) return new BSTNode<T>(value);
			if(curr.value.compareTo(value) > 0) curr.right = insert(curr.right, value);
			if(curr.value.compareTo(value) < 0) curr.left = insert(curr.left, value);
			return curr;
		}	
 
		public void insert(T value){
			root = insert(root, value);
		}
		
		
		private T search(BSTNode<T> curr, T value){
			if(curr == null) return value;
			if(curr.value.compareTo(value) == 0) return value;
			if(curr.value.compareTo(value) > 0) return search(curr.right, value);
			return search(curr.left, value);
		}
 
		public T search(T value){
			return search(root, value);
		}
		
		
		
		private void printInOrder(BSTNode<T> curr){
        if (curr != null) {
            // Print everything in left subtree
            printInOrder(curr.left);
            // Print curr.value
            System.out.print(curr.value + " \n");
            // Print everything in right subtree
            printInOrder(curr.right);
        }
    }
 
		public void printInOrder(){
			printInOrder(root);
			System.out.println();
		}
		
		public void printTree(){
			System.out.println(root.toString());
		}
		
	
	
	public static void main(String[] args){
		/*BST<Integer> bst = new BST<>();
		bst.insert(2);
		bst.insert(7);
		bst.insert(9);
		bst.insert(4);
		bst.insert(1);
		bst.printTree();
		bst.printInOrder();
		System.out.println("5 " + bst.search(5));
		System.out.println("4 " + bst.search(4));*/
		
	}
}