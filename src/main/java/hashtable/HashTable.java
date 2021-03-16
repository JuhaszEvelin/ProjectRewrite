package hashtable;

import java.util.List;

public class HashTable {

    // Number of all buckets - Do not modify!
    private final int bucketsSize = 16;

    private List<List<Entry>> buckets;

    private int getBucketIndexForKey(String key) {
        throw new RuntimeException("FIXME");
    }

    private List<Entry> getBucketAtIndex(int position) {
        throw new RuntimeException("FIXME");
    }

    private Entry findEntryInBucket(String key, List<Entry> bucket) {
        throw new RuntimeException("FIXME");
    }

    public Integer get(String key) {
        throw new RuntimeException("FIXME");
    }

    public void put(String key, Integer value) {
        throw new RuntimeException("FIXME");
    }

    public Integer remove(String key) {
        throw new RuntimeException("FIXME");
    }

    public void clear() {
        throw new RuntimeException("FIXME");
    }
}

class Entry {

    public String key;
    public Integer value;

    public Entry(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

}
