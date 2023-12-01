public class SortedMapNode<K, V> {
    private K key;
    private V value;
    private SortedMapNode<K, V> lessThanNode;
    private SortedMapNode<K, V> greaterThanNode;

    public SortedMapNode(K key, V value, SortedMapNode<K, V> lessThanNode, SortedMapNode<K, V> greaterThanNode) {
        this.key = key;
        this.value = value;
        this.lessThanNode = lessThanNode;
        this.greaterThanNode = greaterThanNode;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public SortedMapNode<K, V> getLessThanNode() {
        return lessThanNode;
    }

    public void setLessThanNode(SortedMapNode<K, V> lessThanNode) {
        this.lessThanNode = lessThanNode;
    }

    public SortedMapNode<K, V> getGreaterThanNode() {
        return greaterThanNode;
    }

    public void setGreaterThanNode(SortedMapNode<K, V> greaterThanNode) {
        this.greaterThanNode = greaterThanNode;
    }
}

