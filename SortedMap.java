// PROGRAMMER: HOANG LE
// TESTING AND REVISING: MC NGUYEN

import java.io.IOException;
import java.io.PrintWriter;

public class SortedMap<K, V> {
    // INSTANCE METHODS
    private int size;
    private SortedMapNode<K, V> smallestValueNode;
    private SortedMapNode<K, V> largestValueNode;

    // CONSTRUCTORS
    public SortedMap() { this.size = 0; } // the other two variables are auto-null
    public SortedMap(SortedMap<K, V> sortedMap) {
        if (sortedMap == null) this.size = 0;
        else if (sortedMap.size == 0) this.size = 0;
        else if (sortedMap.size == 1) {
            this.size = 1;
            this.smallestValueNode = new SortedMapNode<K, V>(
                sortedMap.smallestValueNode.getKey(),
                sortedMap.smallestValueNode.getValue(),
                null, null);
            this.largestValueNode = smallestValueNode;
        } else {
            this.size = sortedMap.size;
            this.smallestValueNode = new SortedMapNode<K, V>(
                sortedMap.smallestValueNode.getKey(),
                sortedMap.smallestValueNode.getValue(),
                null, null);
                
            SortedMapNode<K, V> current = sortedMap.smallestValueNode.getGreaterThanNode();
            SortedMapNode<K, V> follow = this.smallestValueNode;
            while (current != null) {
                SortedMapNode<K, V> newNode = new SortedMapNode<K, V>(
                    current.getKey(),
                    current.getValue(),
                    follow, null);
                follow.setGreaterThanNode(newNode);
                follow = newNode;
                current = current.getGreaterThanNode();
            }
            this.largestValueNode = follow;
        }
    }

    // INSTANCE METHODS
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
    public K getSmallestValue() {
        if (this.isEmpty()) throw new IllegalArgumentException("The sorted map is empty");
        return smallestValueNode.getKey();
    }
    public K getLargestValue() {
        if (this.isEmpty()) throw new IllegalArgumentException("The sorted map is empty");
        return largestValueNode.getKey();
    }
    public boolean contains(K key) {
        SortedMapNode<K, V> currentNode = smallestValueNode;
        while (currentNode != null)
            // Directly compare keys using comparison operators
            if (key.equals(currentNode.getKey())) return true;  // Key found
            else if (key.hashCode() < currentNode.getKey().hashCode()) return false;
            else currentNode = currentNode.getGreaterThanNode();
        return false;  // Key not found
    }
    public boolean containsAll(K[] keyArray) {
        for (K key : keyArray) if (!contains(key)) return false;  // If any key is not found, return false
        return true;  // All keys are found
    }
    public void add(K key, V value) {
        SortedMapNode<K, V> newNode = new SortedMapNode<>(key, value, null, null);
    
        // If the SortedMap is empty, set the new node as both smallest and largest
        if (smallestValueNode == null) {
            smallestValueNode = newNode;
            largestValueNode = newNode;
        } 
        // if key smaller than the smallest
        else if (key.hashCode() < smallestValueNode.getKey().hashCode()) {
            smallestValueNode.setLessThanNode(newNode);
            newNode.setGreaterThanNode(smallestValueNode);
            smallestValueNode = newNode;
        } 
        // if key greater than the largest
        else if (key.hashCode() > largestValueNode.getKey().hashCode()) {
            largestValueNode.setGreaterThanNode(newNode);
            newNode.setLessThanNode(largestValueNode);
            largestValueNode = newNode;
        } 
        // key in between the smallest and largest
        else {
            SortedMapNode<K, V> current = smallestValueNode;
            while (current != null && current.getKey().hashCode() <= key.hashCode()) {
                // update value of a key found
                if (key.equals(current.getKey())) {
                    current.setValue(value);
                    return;
                }
                current = current.getGreaterThanNode();
            }
            // while loop stop when a node key greater than key
            if (current != null) {
                current.getLessThanNode().setGreaterThanNode(newNode);
                newNode.setLessThanNode(current.getLessThanNode());
                newNode.setGreaterThanNode(current);
                current.setLessThanNode(newNode);
            }
        }
        size++;
    }
    public void addAll(SortedMap<K, V> sortedMap) {
        if (sortedMap != null) {
            SortedMapNode<K, V> currentNode = sortedMap.smallestValueNode;
    
            while (currentNode != null) {
                this.add(currentNode.getKey(), currentNode.getValue());
                currentNode = currentNode.getGreaterThanNode();
            }
        }
    }
    public K removeSmallestValue() {
        if (smallestValueNode == null) return null;
        
        K smallestKey = smallestValueNode.getKey();
        if (size > 1) {
            smallestValueNode = smallestValueNode.getGreaterThanNode();
            smallestValueNode.setLessThanNode(null);
            --size;
        } else clear();
        
        return smallestKey;
    }
    public K removeLargestValue() {
        if (largestValueNode == null) return null;
        if (size == 1) return removeSmallestValue();
        K largestKey = largestValueNode.getKey();
        largestValueNode = largestValueNode.getLessThanNode();
        largestValueNode.setGreaterThanNode(null);
        --size;
        return largestKey;
    }

    public void remove(K key) {
        if (key.hashCode() >= smallestValueNode.getKey().hashCode() || key.hashCode() <= largestValueNode.getKey().hashCode())
            if (key.equals(smallestValueNode.getKey())) removeSmallestValue();
            else if (key.equals(largestValueNode.getKey())) removeLargestValue();
            else {
                SortedMapNode<K, V> current = smallestValueNode;
                while (current != null && !key.equals(current.getKey())) current = current.getGreaterThanNode();
                if (current != null) { // found a key greater than smallest and less than largest
                    current.getGreaterThanNode().setLessThanNode(current.getLessThanNode());
                    current.getLessThanNode().setGreaterThanNode(current.getGreaterThanNode());
                    --size;
                }
            }
    }
    public void removeAll(SortedMap<K, V> sortedMap) {
        if (sortedMap != null) {
            SortedMapNode<K, V> currentNode = sortedMap.smallestValueNode;
            while (currentNode != null) {
                remove(currentNode.getKey());
                currentNode = currentNode.getGreaterThanNode();
            }
        }
    }
    public void replace(K key, V newValue) {
        SortedMapNode current = smallestValueNode;
        while (current != null && !key.equals(current.getKey())) current = current.getGreaterThanNode();
        if (current != null) current.setValue(newValue);
    }
    public void clear() {
        size = 0;
        smallestValueNode = null;
        largestValueNode = null;
    }
    public void saveToFile(String filename) throws IOException {
        PrintWriter inputFile = new PrintWriter(filename);
        for (SortedMapNode<K, V> current = smallestValueNode; 
            current != null; 
            current = current.getGreaterThanNode()
        )
            inputFile.println(current.getKey() + ": " + current.getValue());
        inputFile.close();
    }
}
