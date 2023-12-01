// PROGRAMMER: HOANG LE
// TESTING AND REVISING: MC NGUYEN

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
            smallestValueNode = new SortedMapNode<K, V>(
                sortedMap.smallestValueNode.getKey(),
                sortedMap.smallestValueNode.getValue(),
                null, null);
            largestValueNode = smallestValueNode;
        } else {
            this.size = sortedMap.size;
            smallestValueNode = new SortedMapNode<K, V>(
                sortedMap.smallestValueNode.getKey(),
                sortedMap.smallestValueNode.getValue(),
                null, null);
                
            SortedMapNode<K, V> current = sortedMap.smallestValueNode.getGreaterThanNode();
            SortedMapNode<K, V> follow = this.smallestValueNode;
            while (current != null) {
                SortedMapNode<K, V> newNode = new SortedMapNode<K, V>(
                    sortedMap.smallestValueNode.getKey(),
                    sortedMap.smallestValueNode.getValue(),
                    follow, null);
                follow.setGreaterThanNode(newNode);
                follow = newNode;
                current = current.getGreaterThanNode();
            }
            largestValueNode = follow;
        }
    }

    // INSTANCE METHODS
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V getSmallestValue() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("The sorted map is empty");
        }

        return smallestValueNode.getValue();
    }

    public V getLargestValue() 
    {
        if (this.isEmpty()) 
        {
            throw new IllegalArgumentException("The sorted map is empty");
        }

        return largestValueNode.getValue();
    }
    public boolean contains(K key) 
    {
        SortedMapNode currentNode = smallestValueNode;
        while (currentNode != null) {
            // Directly compare keys using comparison operators
            if (key.equals(currentNode.getKey())) {
                return true;  // Key found
            } else if (key.hashCode() < currentNode.getKey().hashCode()) {
                currentNode = currentNode.getLessThanNode();
            } else {
                currentNode = currentNode.getGreaterThanNode();
            }
        }
        return false;  // Key not found
    }

    public boolean containsAll(K[] keyArray) 
    {
        for (K key : keyArray) {
            if (!contains(key)) {
                return false;  // If any key is not found, return false
            }
        }
        return true;  // All keys are found
    
    }
    public void add(K key, V value) {
        SortedMapNode<K, V> newNode = new SortedMapNode<>(key, value, null, null);
    
        // If the SortedMap is empty, set the new node as both smallest and largest
        if (smallestValueNode == null) {
            smallestValueNode = newNode;
            largestValueNode = newNode;
        } else {
            insertNode(newNode, smallestValueNode, null);
        }
        size++;
    }

    private void insertNode(SortedMapNode<K, V> newNode, SortedMapNode<K, V> currentNode, SortedMapNode<K, V> parentNode) {
        while (currentNode != null) 
        {
            // Directly compare keys using comparison operators
            if (newNode.getKey().equals(currentNode.getKey())) {
                // Duplicate key, handle accordingly (e.g., update value or ignore)
                return;
            } else if (newNode.getKey().hashCode() < currentNode.getKey().hashCode()) {
                if (currentNode.getLessThanNode() == null) {
                    currentNode.setLessThanNode(newNode);
                    return;
                } else {
                    insertNode(newNode, currentNode.getLessThanNode(), currentNode);
                    return;
                }
            } else {
                if (currentNode.getGreaterThanNode() == null) {
                    currentNode.setGreaterThanNode(newNode);
                    return;
                } else {
                    insertNode(newNode, currentNode.getGreaterThanNode(), currentNode);
                    return;
                }
            }
        }
    }

    public void addAll(SortedMap<K, V> sortedMap) 
    {
    if (sortedMap != null) {
        SortedMapNode<K, V> currentNode = smallestValueNode;

        while (currentNode != null) {
            add(currentNode.getKey(), currentNode.getValue());
            currentNode = currentNode.getGreaterThanNode();
        }
    }
    }

    public K removeSmallestValue() 
    {
        if (smallestValueNode == null) {
            return null;
        }
        K smallestKey = smallestValueNode.getKey();
        remove(smallestKey);
        size--;
        return smallestKey;
    }

    public K removeLargestValue() {
        if (largestValueNode == null) 
        {
            return null;
        }
        K largestKey = largestValueNode.getKey();
        remove(largestKey);
        size--;
        return largestKey;
    }

    public void remove(K key) {
        if (smallestValueNode != null) 
        {
            removeKey(key, smallestValueNode, null);
        }
        --size;
    }

    private void removeKey(K key, SortedMapNode<K, V> currentNode, SortedMapNode<K, V> parentNode) {
        if (currentNode == null) {
            // Key not found
            return;
        }
    
        if (key.equals(currentNode.getKey())) {
            // Found the key, remove the node
            removeNode(currentNode, parentNode);
        } else if (key.hashCode() < currentNode.getKey().hashCode()) {
            removeKey(key, currentNode.getLessThanNode(), currentNode);
        } else {
            removeKey(key, currentNode.getGreaterThanNode(), currentNode);
        }
    }

    private void removeNode(SortedMapNode<K, V> currentNode, SortedMapNode<K, V> parentNode) {
        if (currentNode.getLessThanNode() == null) {
            if (parentNode == null) {
                smallestValueNode = currentNode.getGreaterThanNode();
            } else if (parentNode.getLessThanNode() == currentNode) {
                parentNode.setLessThanNode(currentNode.getGreaterThanNode());
            } else {
                parentNode.setGreaterThanNode(currentNode.getGreaterThanNode());
            }
        } else if (currentNode.getGreaterThanNode() == null) {
            if (parentNode == null) {
                smallestValueNode = currentNode.getLessThanNode();
            } else if (parentNode.getLessThanNode() == currentNode) {
                parentNode.setLessThanNode(currentNode.getLessThanNode());
            } else {
                parentNode.setGreaterThanNode(currentNode.getLessThanNode());
            }
        } else {
            // Node has two children, find the smallest node in the right subtree
            SortedMapNode<K, V> smallestInRightSubtree = findSmallest(currentNode.getGreaterThanNode(), currentNode);
            // Replace the current node's key and value with the smallest in the right subtree
            currentNode.setKey(smallestInRightSubtree.getKey());
            currentNode.setValue(smallestInRightSubtree.getValue());
            // Remove the smallest node in the right subtree
            removeNode(smallestInRightSubtree, currentNode);
        }
    }

    private SortedMapNode<K, V> findSmallest(SortedMapNode<K, V> currentNode, SortedMapNode<K, V> parentNode) {
        while (currentNode.getLessThanNode() != null) {
            parentNode = currentNode;
            currentNode = currentNode.getLessThanNode();
        }
    
        if (parentNode != null) {
            parentNode.setLessThanNode(currentNode.getGreaterThanNode());
        }
    
        return currentNode;
    }

    public void removeAll(SortedMap<K, V> sortedMap) {
        if (sortedMap != null) {
            SortedMapNode<K, V> currentNode = smallestValueNode;
            while (currentNode != null) {
                remove(currentNode.getKey());
                currentNode = currentNode.getGreaterThanNode();
            }
        }
    }
    public void replace(K key, V newValue) 
    {
        SortedMapNode currentNode = smallestValueNode;
        while (currentNode != null) 
        {
            if (key.equals(currentNode.getKey())) {
                currentNode.setValue(newValue);
                return;
            } else if (key.hashCode() < currentNode.getKey().hashCode()) {
                currentNode = currentNode.getLessThanNode();
            } else {
                currentNode = currentNode.getGreaterThanNode();
            }
        }
    }

    public void clear() 
    {
        size = 0;
        smallestValueNode = null;
        largestValueNode = null;
    }

}
