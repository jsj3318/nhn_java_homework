public interface List<V> {
    public void add(V v);
    public void remove(int idx);
    public int size();
    public boolean isEmpty();
    public V get(int idx);
}
