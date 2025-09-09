package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
        private Node(K k, V v, Node ln, Node rn) {
            key = k;
            value = v;
            left = ln;
            right = rn;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.equals(p.key)) {
            return p.value;
        }
        if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, this.root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private void putHelper(K key, V value, Node p) {
        if (key.equals(p.key)) {
            return;
        }
        if (key.compareTo(p.key) < 0) {
            if (p.left == null) {
                Node newNode = new Node(key, value, null, null);
                p.left = newNode;
                this.size++;
            } else {
                putHelper(key, value, p.left);
            }
        } else {
            if (p.right == null) {
                Node newNode = new Node(key, value, null, null);
                p.right = newNode;
                this.size++;
            } else {
                putHelper(key, value, p.right);
            }
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value, null, null);
            root.value = value;
            size++;
        } else {
            putHelper(key, value, this.root);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    private void nodeIter(Set<K> keySet, Node p) {
        if (p == null) {
            return;
        }
        keySet.add(p.key);
        nodeIter(keySet, p.left);
        nodeIter(keySet, p.right);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        nodeIter(keySet, root);
        return keySet;
    }

    private Node removeHelper(Node curr, K key, removeBox<V> val) {
        if (curr == null) {
            return null;
        }
        if (curr.key.compareTo(key) < 0) {
            curr.right = removeHelper(curr.right, key, val);
        } else if (curr.key.compareTo(key) > 0) {
            curr.left = removeHelper(curr.left, key, val);
        } else {
            val.val = curr.value;
            this.size--;
            if (curr.left == null && curr.right == null) {
                return null;
            } else if (curr.left != null && curr.right == null) {
                return curr.left;
            } else if (curr.left == null && curr.right != null) {
                return curr.right;
            } else {
                Node nextNode = curr.right;

                while (nextNode.left != null) {
                    nextNode = nextNode.left;
                }
                curr.key = nextNode.key;
                curr.value = nextNode.value;
                curr.right = removeHelper(curr.right, nextNode.key, new removeBox<>());
            }

        }
        return curr;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        removeBox<V> val = new removeBox<>();
        Node currNode = root;

        root = removeHelper(currNode,key,val);
        return val.val;
    }
    private class removeBox<T> {
        public T val;
    }
    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (remove(key) != null && remove(key) == value) {
            return value;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
