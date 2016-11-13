import java.io.*;
import java.util.Scanner;    
 
 
public class Homework4_part2 {


    static class Node {
        String dma;
        String city;
        String state;
        int count;
        int N;
        int height;
        Node left;
        Node right;

        public Node() {
            this.left = null;

            this.right = null;
            this.dma = dma;
            this.city = city;
            this.state = state;
            this.count = 0;
            this.N=0;
            this.height = 0;
        }


    }


    static class LinkedListBST {

        private Node root;

        public LinkedListBST() {
            root = null;
        }

        public boolean isEmpty() {
            return root == null;
        }
        public int size() {
            return size(root);
        }

        // return number of key-value pairs in BST rooted at x
        private int size(Node x) {
            if (x == null) return 0;
            else return x.N;
        }
        public void clear() {
            root = null;
        }

        public void insert(String dma){root = insert(dma, root);


        }
        public void delete(String dma){root = insert(dma, root);

        }


        /* Function to get height of node */
        private int height(Node t) {
            return t == null ? -1 : t.height;
        }

        /* Function to max of left/right node */
        private int max(int lhs, int rhs) {
            return lhs > rhs ? lhs : rhs;
        }

        /* Function to insert data recursively */
        private Node insert(String s, Node t) {
            if (t == null) t = new Node();
            int cmp = s.compareTo(t.dma);
            int cmp1 = s.compareTo(t.left.dma);
            int cmp2 = s.compareTo(t.right.dma);
             if  (cmp<0) {
                t.left = insert(s, t.left);
                if (height(t.left) - height(t.right) == 2)

                if (cmp1<0)
                        t = rotateWithLeftChild(t);
                    else
                        t = doubleWithLeftChild(t);
            } else if (cmp > 0) {
                t.right = insert(s, t.right);
                if (height(t.right) - height(t.left) == 2)
                    if (cmp2>0)
                        t = rotateWithRightChild(t);
                    else
                        t = doubleWithRightChild(t);
            } else
                ;  // Duplicate; do nothing

            t.height = max(height(t.left), height(t.right)) + 1;
            return t;
        }

        /* Rotate binary tree node with left child */
        private Node rotateWithLeftChild(Node k2) {
            int count = 0;
            Node k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = max(height(k2.left), height(k2.right)) + 1;
            k1.height = max(height(k1.left), k2.height) + 1;
            return k1;
        }

        /* Rotate binary tree node with right child */
        private Node rotateWithRightChild(Node k1) {
            Node k2 = k1.right;
            k1.right = k2.left;
            k2.left = k1;
            k1.height = max(height(k1.left), height(k1.right)) + 1;
            k2.height = max(height(k2.right), k1.height) + 1;
            return k2;
        }

        /**
         * Double rotate binary tree node: first left child
         * with its right child; then node k3 with new left child
         */
        private Node doubleWithLeftChild(Node k3) {
            k3.left = rotateWithRightChild(k3.left);
            return rotateWithLeftChild(k3);
        }

        /**
         * Double rotate binary tree node: first right child
         * with its left child; then node k1 with new right child
         */
        private Node doubleWithRightChild(Node k1) {
            k1.right = rotateWithLeftChild(k1.right);
            return rotateWithRightChild(k1);
        }

        /* Functions to count number of nodes */
        public int countNodes() {
            return countNodes(root);
        }

        private int countNodes(Node r) {
            if (r == null) return 0;
            else {
                int l = 1;
                l += countNodes(r.left);
                l += countNodes(r.right);
                return l;
            }
        }

        /* Functions to search for an element */
        public boolean search(String dam) {
            return search(root, dam);
        }

        private boolean search(Node r, String d) {
            boolean found = false;
            while ((r != null) && !found) {
                String rd = r.dma;
                int cmp3 = d.compareTo(r.dma);

                if (cmp3<0) r = r.left;
                else if (cmp3>0) r = r.right;
                else {
                    found = true;
                    break;
                }
                found = search(r, d);
            }
            return found;
        }
        public void deleteMin() {
            if (isEmpty()) throw new RuntimeException("Symbol table underflow");
            root = deleteMin(root);

        }

        private Node deleteMin(Node x) {
            if (x.left == null) return x.right;
            x.left = deleteMin(x.left);
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }

        public void deleteMax() {
            if (isEmpty()) throw new RuntimeException("Symbol table underflow");
            root = deleteMax(root);

        }

        private Node deleteMax(Node x) {
            if (x.right == null) return x.left;
            x.right = deleteMax(x.right);
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }
        private Node delete(Node x, String dma) {
            if (x == null) return null;
            int cmp =dma.compareTo(x.dma);
            if      (cmp < 0) x.left  = delete(x.left,  dma);
            else if (cmp > 0) x.right = delete(x.right, dma);
            else {
                if (x.right == null) return x.left;
                if (x.left  == null) return x.right;
                Node t = x;
                x = deleteMin(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }
        public void metrics() {

        }
        /* Function for preorder traversal */
        public void preorder() {
            preorder(root);
        }

        private void preorder(Node r) {
            if (r != null) {
                System.out.print(r.dma + " ");
                preorder(r.left);
                preorder(r.right);
            }


        }

    }

    public static class BalancedTree{
    public static void main(String args[]) throws IOException{
        LinkedListBST bst = new LinkedListBST();

        Scanner keyboard = new Scanner(System.in);
        int count = 0;
        System.out.print("Enter file name: ");
        String file = keyboard.nextLine();
        File fileName = new File(file);
        Scanner input = new Scanner(fileName);
        System.out.println(fileName);


        try { Process p = Runtime.getRuntime().exec("LinkedListBST");



            while (input.hasNextLine()) {

                String content = input.nextLine();
                String[] details = content.split(" ");
                String dma = details[0];
                String city = details[1];
                String state = details[2];

                bst.insert(dma);



            }
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nProgram terminated Safely...");
        }

        keyboard = new Scanner(System.in);
        char ans;

        do{
            System.out.println("***Would you like to ***");
            System.out.println("1. insert ");
            System.out.println("2. search ");
            System.out.println("3. delete ");
            int choice = keyboard.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter dma to insert");
                    bst.insert(keyboard.nextLine());
                    break;
                case 2:
                    System.out.println("Enter dma to search");

                    System.out.println("Search result : "+ bst.search( keyboard.nextLine() ));
                    break;
                case 3:
                    System.out.println("Enter dma to delete");

                    bst.delete(keyboard.nextLine());
                    break;
            }

            System.out.print("\nPre order : ");
            System.out.println("LinkedListBST");
            System.out.println("\nDo you want to continue (Type y or n)");
            ans = keyboard.next().charAt(0);
        }while(ans == 'y');

    }

}
}

