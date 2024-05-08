package avl;
class Node {
    public enum Child {
       LEFT, RIGHT
    }
 
    public int key;
    public Node parent;
    public Node left;
    public Node right;
    public int height;
 
    // Constructor with a key parameter creates the Node object.
    public Node(int nodeKey) {
       key = nodeKey;
       parent = null;
       left = null;
       right = null;
       height = 0;
    }
    
    // Calculate this nodes' balance factor, defined as 
    // height(left subtree) - height(right subtree)
    public int getBalance() {
       // Get current height of left subtree, or -1 if null
       int leftHeight = -1;
       if (left != null) {
          leftHeight = left.height;
       }
             
       // Get current height of right subtree, or -1 if null
       int rightHeight = -1;
       if (right != null) {
          rightHeight = right.height;
       }
             
       // Calculate the balance factor.
       return leftHeight - rightHeight;
    }
    
    // Recalculate the current height of the subtree rooted at
    // the node, usually called after a subtree has been 
    // modified.
    public void updateHeight() {
       // Get current height of left subtree, or -1 if null
       int leftHeight = -1;
       if (left != null) {
          leftHeight = left.height;
       }
             
       // Get current height of right subtree, or -1 if null
       int rightHeight = -1;
       if (right != null) {
          rightHeight = right.height;
       }
 
       // Assign height with calculated node height.
       height = Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Assign either the left or right data member with a new
    // child. Returns true if the new child is successfully 
    // assigned to this node, false otherwise.
    public boolean setChild(Child whichChild, Node child) {
       // Assign the left or right data member.
       if (whichChild == Child.LEFT) {
          left = child;
       }
       else {
          right = child;
       }
 
       // Assign the parent data member of the new child,
       // if the child is not null.
       if (child != null) {
          child.parent = this;
       }
 
       // Update the node's height, since the subtree's structure
       // may have changed.
       updateHeight();
       return true;
    }
    
    // Replace a current child with a new child. Determines if
    // the current child is on the left or right, and calls
    // setChild() with the new node appropriately.
    // Returns true if the new child is assigned, false otherwise.
    public boolean replaceChild(Node currentChild, Node newChild) {
       if (left == currentChild) {
          return setChild(Child.LEFT, newChild);
       }
       else if (right == currentChild) {
          return setChild(Child.RIGHT, newChild);
       }
       
       // If neither of the above cases applied, then the new child
       // could not be attached to this node.
       return false;
    }
 }
 