package hashtable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> {

    private final int bucketsSize = 16;

    private List<List<Entry>> buckets = new ArrayList<>(bucketsSize);

    public HashTable() {
        for (int i = 0; i < bucketsSize; i++) {
            buckets.add(new LinkedList<Entry>());
        }
    }

    private int getBucketIndexForKey(K key) {

        if (key == null) {
            return 0;
        }
        int bucketIndex = Math.abs(key.hashCode() % bucketsSize);

        return bucketIndex;
    }

    private List<Entry> getBucketAtIndex(int position) {
        return buckets.size() > position ? buckets.get(position) : new LinkedList<Entry>();
    }

    private Entry findEntryInBucket(K key, List<Entry> bucket) {
        Entry entryRes = new Entry(key, null);
        if (!bucket.isEmpty()) {

            for (Entry entry : bucket) {
                if (entry.key == null && key == null
                        || entry.key.equals(key)) {
                    entryRes = entry;
                }
            }
        }
        return entryRes;
    }

    public V get(K key) {
        List<Entry> bucket = getBucketAtIndex(getBucketIndexForKey(key));
        Entry entry = findEntryInBucket(key, bucket);
        return entry == null ? null : (V) entry.getValue();
    }

    public void put(K key, V value) {
        List<Entry> bucket = new LinkedList<>();
        int bucketIndexForKey = getBucketIndexForKey(key);
        if (!buckets.isEmpty()
                && bucketIndexForKey < buckets.size()
                && !buckets.get(bucketIndexForKey).isEmpty()) {
            bucket = getBucketAtIndex(bucketIndexForKey);
            V oldValue = get(key);

            if (oldValue == null) {
                bucket.add(new Entry(key, value));
                return;
            } else {
                findEntryInBucket(key, bucket).setValue(value);
                return;
            }
        }
        bucket.add(new Entry(key, value));
        buckets.add(bucketIndexForKey, bucket);
    }

    public V remove(K key) {
        List<Entry> bucket = getBucketAtIndex(getBucketIndexForKey(key));
        Entry entry = findEntryInBucket(key, bucket);
        V result = (V) entry.getValue();
        bucket.remove(entry);
        return result;
    }

    public void clear() {
        buckets.clear();
    }
}

class Entry<K, V> {

    public K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
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
}

