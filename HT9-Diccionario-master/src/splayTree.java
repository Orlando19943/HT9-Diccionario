/**
 * @author olivverde
 * @author Orlando
 * 
 * Class purpose: SplayTree implementation, implements map class in order to 
 * enable the Factory.
 * 
 * SplayTree implementation has been retrieved from: https://algs4.cs.princeton.edu/33balanced/SplayBST.java.html
 * Written by Josh Israel.
 */

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class splayTree<Key extends Comparable<Key>, Value> implements Map <Key,Value> {

    private Node root;   // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;            // key
        private Value value;        // associated data
        private Node left, right;   // left and right subtrees

        public Node(Key key, Value value) {
            this.key   = key;
            this.value = value;
        }
    }

    /**
     * @pre: Hay un valor en el arbol
     * @post> Devuelve true si el objeto se encuentra en el arbol
     * @return: True si el valor se encuentra en el arbol o null si no se encuentra
     */
    @Override
	public boolean containsKey(Object key) {
    	return get(key) != null;
		
	}
        

    // return value associated with the given key
    // if no such value, return null
    /**
     * @pre: Hay un valor en el arbol
     * @post: Devuelve el valor de la llave asociada
     * @return: El valor de la llave
     */
    @Override
	public Value get(Object key) {
    	root = splay(root, key);
        int cmp = ((Comparable<Key>) key).compareTo(root.key);
        if (cmp == 0) return root.value;
        else          return null;
    }    
    

   /***************************************************************************
    *  Splay tree insertion.
    ***************************************************************************/
    /**
     * @post: Introduce una llave con su valor al arbol
     */
    public Value put(Key key, Value value) {
        // splay key to root
        if (root == null) {
            root = new Node(key, value);
            return null;
        }
        
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        
        // Insert new node at root
        if (cmp < 0) {
            Node n = new Node(key, value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        }

        // Insert new node at root
        else if (cmp > 0) {
            Node n = new Node(key, value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        }

        // It was a duplicate key. Simply replace the value
        else {
            root.value = value;
        }
		return null;

    }
    
   /***************************************************************************
    *  Splay tree deletion.
    ***************************************************************************/
    /* This splays the key, then does a slightly modified Hibbard deletion on
     * the root (if it is the node to be deleted; if it is not, the key was 
     * not in the tree). The modification is that rather than swapping the
     * root (call it node A) with its successor, it's successor (call it Node B)
     * is moved to the root position by splaying for the deletion key in A's 
     * right subtree. Finally, A's right child is made the new root's right 
     * child.
     */
    /**
     * @pre: Hay un objeto en el arbol
     * @post: Elimina la raiz del arbol y posiciona al hijo derecho como sucesor 
     * @param key
     */
    public void remove(Key key) {
        if (root == null) return; // empty tree
        
        root = splay(root, key);

        int cmp = key.compareTo(root.key);
        
        if (cmp == 0) {
            if (root.left == null) {
                root = root.right;
            } 
            else {
                Node x = root.right;
                root = root.left;
                splay(root, key);
                root.right = x;
            }
        }

        // else: it wasn't in the tree to remove
    }
    
    
   /***************************************************************************
    * Splay tree function.
    * **********************************************************************/
    // splay key in the tree rooted at Node h. If a node with that key exists,
    //   it is splayed to the root of the tree. If it does not, the last node
    //   along the search path for the key is splayed to the root.
    /**
     * @pre: Hay un valor en el arbol
     * @post: Reposiciona las hojas del arbol
     * @param h
     * @param key
     * @return
     */
    private Node splay(Node h, Object key) {
        if (h == null) return null;

        int cmp1 = ((Comparable<Key>) key).compareTo(h.key);

        if (cmp1 < 0) {
            // key not in tree, so we're done
            if (h.left == null) {
                return h;
            }
            int cmp2 = ((Comparable<Key>) key).compareTo(h.left.key);
            if (cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            }
            else if (cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }
            
            if (h.left == null) return h;
            else                return rotateRight(h);
        }

        else if (cmp1 > 0) { 
            // key not in tree, so we're done
            if (h.right == null) {
                return h;
            }

            int cmp2 = ((Comparable<Key>) key).compareTo(h.right.key);
            if (cmp2 < 0) {
                h.right.left  = splay(h.right.left, key);
                if (h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if (cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }
            
            if (h.right == null) return h;
            else                 return rotateLeft(h);
        }

        else return h;
    }


   /***************************************************************************
    *  Helper functions.
    ***************************************************************************/

    // height of tree (1-node tree has height 0)
    /**
     * @post: Devuelve la altura del arbol
     * @return: Un int con la altura del arbol
     */
    public int height() { return height(root); }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * @post: Devuelve el tamanio del arbol
     */
    public int size() {
        return size(root);
    }
    /**
     * @post: Devuelve el tamanio del arbol
     */
    private int size(Node x) {
        if (x == null) return 0;
        else return 1 + size(x.left) + size(x.right);
    }
    /**
     * @pre: Hay un valor en el arbol
     * @post: Devuelve el nodo rotado hacia la derecha
     * @param h
     * @return: El nodo rotado a la dereha
     */
    // right rotate
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }
    /**
     * @pre: Hay un valor en el arbol
     * @post: Devuelve el nodo rotado hacia la izquierda
     * @param h
     * @return: El nodo rotado a la izquierda
     */
    // left rotate
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }

    //Metodos que no se utilizaron
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Entry<Key, Value>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Key> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void putAll(Map<? extends Key, ? extends Value> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Value remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Value> values() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}