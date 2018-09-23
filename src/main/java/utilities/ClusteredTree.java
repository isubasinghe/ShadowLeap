package utilities;

import java.util.ArrayList;
import java.util.TreeMap;

public class ClusteredTree<K, T> {
    private TreeMap<K, ArrayList<T>> tree;

    public TreeMap<K, ArrayList<T>> getTree() {
        return tree;
    }

    public boolean add(K key, T value) {
        ArrayList<T> node = tree.get(key);
        if(node == null) {
            return false;
        }
        return node.add(value);
    }

    public boolean remove(K key, T value) {
        ArrayList<T> node = tree.get(key);
        if(node == null) {
            return false;
        }
        return node.remove(value);
    }


}
