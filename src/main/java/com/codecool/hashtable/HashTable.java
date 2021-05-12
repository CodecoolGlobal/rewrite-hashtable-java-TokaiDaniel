package com.codecool.hashtable;

import java.util.ArrayList;
import java.util.List;

public class HashTable <K, V>{

    // Number of all buckets - Do not modify!
    private final int bucketsSize = 16;

    private List<List<Entry>> buckets;

    public HashTable() {
        buckets = new ArrayList<>();
        for (int i = 0; i < bucketsSize; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int getBucketIndexForKey(K key) {
        if (key == null) return 0;
        int hashCode = key.hashCode();
        int index = hashCode % bucketsSize;
        // key.hashCode() coule be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }

    private List<Entry> getBucketAtIndex(int position) {
        return buckets.get(position);
    }

    private Entry findEntryInBucket(K key, List<Entry> bucket) {
        for (Entry entry: bucket) {
            if (entry.key == key) return entry;
        }
        return null;
    }

    public V get(K key) {
        int index = getBucketIndexForKey(key);
        List<Entry> bucket = getBucketAtIndex(index);
        Entry entry = findEntryInBucket(key, bucket);
        if (entry != null) return (V) entry.value;
        else return null;
    }

    public void put(K key, V value) {
        Entry newEntry = new Entry(key, value);

        int bucketIndex = getBucketIndexForKey(key);
        List<Entry> bucket = getBucketAtIndex(bucketIndex);

        if (findEntryInBucket(key, bucket) != null) {
            remove(key);
        }
        bucket.add(newEntry);
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndexForKey(key);
        List<Entry> bucket = getBucketAtIndex(bucketIndex);

        V value = null;
        Entry remove = null;
        for (Entry entry: bucket) {
            if (entry.key == key) {
                value = (V) entry.value;
                remove = entry;
            }
        }
        bucket.remove(remove);
        return value;
    }

    public void clear() {
        buckets = new ArrayList<>();
        for (int i = 0; i < bucketsSize; i++) {
            buckets.add(new ArrayList<>());
        }
    }
}

class Entry <K, V>{

    public K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
