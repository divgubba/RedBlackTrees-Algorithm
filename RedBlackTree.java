// @ author Divya Gubba
// CS 3345.004, Spring 2019
// Project 4:
/*
    Red Black Tree class uses generics, taking in only String and Integer objects,
    and places them in pre-order traversal in the Red Black Tree.
    The class has three methods: insert, contains, and toString methods
    insert method takes in element and places in correct place, while also using
        helper methods to rebalance the Red black tree to check all violations of
        RBT properties
    contains method takes in object to check if tree contains node
    toString method used to print tree when called
 */
import java.util.StringJoiner;
public class RedBlackTree<E extends Comparable<E>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    // every node has nil node, set to black
    private Node<E> nil = new Node<E>();
    // var root to allow for direction of element
    private Node<E> root = nil;

    public static class Node< E extends Comparable<E> > {

        // declare variables
        // element should extend comparable interface
        E element;
        // left child of node
        Node<E> leftChild;
        // right child of node
        Node<E> rightChild;
        // parent to node
        Node<E> parent;
        // color of node red : false, black : true
        boolean color;

        // default constructor
        public Node() {
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
            this.color = BLACK;
        }

        // overloaded constructor
        public Node(E element) {
            // calls constructor to the class
            this();
            this.element = element;
        }

        /*
        // getter and setter methods
        // get element returns element
        public E getElement() {
            return element;
        }
        // current element replaced with new element
        public void setElement(E element) {
            this.element = element;
        }

        // return left child
        public Node<E> getLeftChild() {
            return leftChild;
        }
        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        // return right child of node
        public Node<E> getRightChild() {
            return rightChild;
        }
        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        // return parent
        public Node<E> getParent() {
            return parent;
        }
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }
*/

        // returns color of node
        public boolean getColor() { return color; }
        public void setColor(boolean color) { this.color = color; }

    }

    // default constructor
    public RedBlackTree(){
        if (root != null){
            root.leftChild = nil;
            root.rightChild = nil;
            root.parent = nil;
        }
    }

    /*
    < method to call recursive insert method >
     @param:  element (to insert)
     @return: returns true or false from recursive insert method,
              true if inserted, false if duplicate element
    */
    public boolean insert(E element){
        return insert(new Node<E>(element));
    }

    /*
     insert method
     @param:  currentRoot,
              insertElem is the node that is inserted into tree, as root initially, and
              then inserts next nodes as leaf in appropriate place.
     @return: boolean value is returned to indicate whether node was inserted or not:
              true if inserted,
              false if there is a duplicate of insertElem in tree already
    */
    private boolean insert (Node<E> currentRoot)
    {
        Node<E> current = root  ;       // Create a new node to reference the root
        Node<E> currentParent = nil;    // Create a parent to this node and initialize to nil

        while (current != nil)          // Runs while the end of the tree is not reached
        {
            currentParent = current;

            // Traverses the tree and goes left or right based on the values of the nodes
            if (currentRoot.element.compareTo(current.element) < 0)
                current = current.leftChild;        // Traverses left if the new node is less than the current node

            else if (currentRoot.element.compareTo(current.element) > 0)
                current = current.rightChild;       // Traverses right if the new node is less than the current node

            else
                return false;                       // Returns false if none of the above cases are true
        }

        currentRoot.parent = currentParent;             // Reset the currentParent as the parent of the new node

        // Setting the new node as the left or right child of currentParent based on the value of the elements
        if (currentParent == nil)
            root = currentRoot;

        else if (currentRoot.element.compareTo(currentParent.element) < 0)
            currentParent.leftChild = currentRoot;

        else
            currentParent.rightChild = currentRoot;

        // Set the new node's color to red and initialize its children
        currentRoot.color = RED;
        currentRoot.leftChild = nil;
        currentRoot.rightChild = nil;
        rebalanceTree(currentRoot);              // Call the reorient method to re-balance the tree

        return true;                    // Return true as default
    }

    /*
     < contains method to check if node is contained in tree >
     @param: object,
             comparable object passed through
     @return: returns whether tree contains element that compares equal to object
              if given object is null, contains method returns false
    */
    public boolean contains(E object){
        if (object == nil){
            return false;
        } else {
            return contains(root, object);
        }

    }

    /*
     < contains helper method traverses through tree starting from root, until finds
     element that compares equal to object >
     @param: tempRoot Node passed
     @return: returns true/false for whether node searched for is found or not
    */
    private boolean contains(Node<E> tempRoot, E object){

        // return false if tempRoot/object is null, meaning not found
        if(tempRoot == nil){
            return false;
        }
        // returns true if element compares equal to object
        if(object.compareTo(tempRoot.element) == 0){
            return true;
        }
        // recursive traversal of tree until reaches end of tree
        else if(object.compareTo(tempRoot.element) < 0){
            // pass in left child of tempRoot to compare with object again
            // until reaches end of tree or finds match
            return contains(tempRoot.leftChild, object);
        } else {
            // pass in right child of tempRoot to compare with object again
            // until reaches end of tree or finds match
            return contains(tempRoot.rightChild, object);
        }

    }

    /*
      < toString method >
      @param : none
      @return : String from toStringTrav method
    */
    public String toString(){
        return toStringTrav(root);
    }

    /*
     < toString helper method >
     @param:  takes in tempRoot node, to traverse from root
     @return: returns string for pre-order traversal of tree
              returns ordered concatenation string of each element in traversal
              every 2 adjacent elements is separated by a space
              nodes that are red should have "*" in front of element, black elements
                  wont have asterisk
    */
    private String toStringTrav(Node<E> tempRoot){
        if(tempRoot == nil){
            return "";
        }
        // keeps track of strings for concatenation
        String str = "";
        String strL;
        String strR;

        // if color of root = black, no asterisk in concatenation
        if (tempRoot.color == BLACK)
            str += tempRoot.element + " ";

            // if color of root = red, need asterisk in concatenation
        else
            str += "*" + tempRoot.element + " ";


        // need left child and right child of root for concatenation
        strL = toStringTrav(tempRoot.leftChild);
        strR = toStringTrav(tempRoot.rightChild);

        //return total string
        return str + strL + strR;

    }

    /*
     < helper method for rebalanceTree method
     need to recolor and rotate within rebalance method
     called to check for violations after element gets inserted >
     @param: Node<E> tempNode
    */
    private void rebalanceTree(Node<E> tempNode){
        // aunt node for knowing when to rotate or recolor if violation
        // if aunt red : recolor
        // if aunt black : rotate
        Node<E> aunt = nil;

        // if 2 reds in a row, violation of properties
        while (tempNode.parent.getColor() == RED){

            // if tempNode's parent is left child to grandparent
            if (tempNode.parent == tempNode.parent.parent.leftChild){
                // aunt must be right child of grandparent
                aunt = tempNode.parent.parent.rightChild;

                // or if aunt is red, color flip
                if (aunt.getColor() == RED){
                    // set grandparent to red
                    tempNode.parent.parent.setColor(RED);

                    // set grandparents children to black (aunt also to black)
                    tempNode.parent.setColor(BLACK);
                    aunt.setColor(BLACK);

                    // set tempNode to grandparent, to go up tree to check for further potential violations
                    tempNode = tempNode.parent.parent;
                }
                // and if aunt is black, right rotate around grandparent, and recolor
                else {
                    // if tempNode is right child to parent
                    // need to do left rotation around parent
                    if (tempNode == tempNode.parent.rightChild){
                        tempNode = tempNode.parent;
                        leftRotate(tempNode);
                    }
                    // if tempNode is left child to parent
                    // need to do right rotation around grandparent
                    else {
                        tempNode.parent.color = BLACK;
                        tempNode.parent.parent.color = RED;
                        rightRotate(tempNode.parent.parent);
                    }
                }
             }

            // if tempNode's parent is right child to grandparent
            else {
                // aunt must be left child of grandparent
                aunt = tempNode.parent.parent.leftChild;

                // or if aunt is red, color flip
                if (aunt.getColor() == RED){
                    // set grandparent to red
                    tempNode.parent.parent.setColor(RED);

                    // set grandparents children to black (aunt also to black)
                    tempNode.parent.setColor(BLACK);
                    aunt.setColor(BLACK);

                    // set tempNode to grandparent, to go up tree to check for further potential violations
                    tempNode = tempNode.parent.parent;
                }
                // and if aunt is black, left rotate around grandparent, and recolor
                else {
                    // if tempNode is left child to parent
                    // need to do right rotation around parent
                    if (tempNode == tempNode.parent.leftChild) {
                        tempNode = tempNode.parent;
                        rightRotate(tempNode);
                    }
                    // if tempNode is right child to parent
                    // need to do left rotation around grandparent
                    else {
                        tempNode.parent.color = BLACK;
                        tempNode.parent.parent.color = RED;
                        // set tempNode to grandparent to do left right rotation around grandparent
                        leftRotate(tempNode.parent.parent);
                    }
                }
            }
        }
        root.color = BLACK;
    } // End of rebalanceTree method

    /*
        < rightRotate method used to rebalance tree >
        @param: Node<E> node
     */
    private void rightRotate(Node<E> node){
        // set temp as grandparent's left child
        Node<E> temp = node.leftChild;
        node.leftChild = temp.rightChild;

        // if temp node's right child is not nil
        //  set node as parent to temp's right child
        if(temp.rightChild != nil){
            temp.rightChild.parent = node;
         }
        // set temp's parent to what node's parent was
        temp.parent = node.parent;

        // if node's parent is nil, and rotation occurs, root should be set as temp
        if(node.parent == nil){
            root = temp;
            // if node has parent, and node is right child of parent, set parent's
            // right child to temp
        } else if (node.parent.rightChild == node) {
            node.parent.rightChild = temp;
            // if node has parent, and node is left child of parent, set parent's
            // left child to temp
        } else {
            node.parent.leftChild = temp;
        }

        // set temp's right child to node for right rotation
        temp.rightChild = node;
        // node's parent is temp
        node.parent = temp;
    }

    /*
        < leftRotate method used to rebalance tree >
        @param: Node<E> node
     */
    private void leftRotate(Node<E> node){
        // set temp as grandparent's right child
        Node<E> temp = node.rightChild;
        node.rightChild = temp.leftChild;

        // if temp node's left child is not nil
        //  set node as parent to temp's left child
        if(temp.leftChild != nil){
            temp.leftChild.parent = node;
        }
        // set temp's parent to what node's parent was
        temp.parent = node.parent;

        // if node's parent is nil, and rotation occurs, root should be set as temp
        if(node.parent == nil){
            root = temp;
            // if node has parent, and node is right child of parent, set parent's
            // left child to temp
        } else if (node.parent.leftChild == node) {
            node.parent.leftChild = temp;
            // if node has parent, and node is right child of parent, set parent's
            // left child to temp
        } else {
            node.parent.rightChild = temp;
        }
        // set temp's left child to node for left rotation
        temp.leftChild = node;
        // node's parent is temp
        node.parent = temp;
    }


}
