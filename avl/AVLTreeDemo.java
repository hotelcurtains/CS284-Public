package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
       // Create an empty AVLTree object.
       AVLTree tree = new AVLTree();
 
       // Insert some random-looking integers into the tree.
       int[] keys = { 5, 7, 50, 10, 3, 30, 25, 8, 15 };
       for (int i=0 ; i<9; i++) {
          Node node = new Node(keys[i]);
          tree.insert(node);
          System.out.println(BSTPrint.treeToString(tree.getRoot())+"\n");
       }
       
       // Print the tree after all inserts are complete.
       System.out.println("Tree after initial insertions:");
       System.out.println(BSTPrint.treeToString(tree.getRoot()));
       System.out.println();
    }
 }
 