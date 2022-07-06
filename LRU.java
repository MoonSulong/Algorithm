import java.util.*;

public class LRU<K, V> {
    // fields
    private int limit;
    public CacheUnit<K, V> head;
    public CacheUnit<K, V> tail;
    private HashMap<K, CacheUnit<K, V>> cacheMap;

    // limit is the max capacity of the cache
    public LRU(int limit) {
        this.limit = limit;
        this.cacheMap = new HashMap<K, CacheUnit<K, V>>();
    }

    public void set(K key, V value) {
        CacheUnit<K, V> unit = new CacheUnit<K, V>(key, value);
        int size = this.cacheMap.size();
        CacheUnit<K, V> target = this.cacheMap.get(key);
        if (target == null) {
            if (size >= limit) {
                removeUnit(tail);
            }
        } else {
            removeUnit(target);
        }
        appendUnit(unit);
    }

    public V get(K key) {
        if (key == null || !this.cacheMap.containsKey(key)) {
            return null;
        }

        CacheUnit<K, V> target = this.cacheMap.get(key);
        if (target != head) {
            removeUnit(target);
            appendUnit(target);
        }
        return target.value;
    }

    private void removeUnit(CacheUnit<K, V> target) {
        this.cacheMap.remove(target.key);
        if (target == head) {
            head = target.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (target == tail) {
            tail = target.prev;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            target.prev.next = target.next;
            target.next.prev = target.prev;
        }
    }

    private void appendUnit(CacheUnit<K, V> target) {
        if (head == null) {
            head = target;
            tail = target;
        } else {
            target.next = head;
            head.prev = target;
            head = target;
        }
        this.cacheMap.put(target.key, target);
    }

    public static class CacheUnit<K, V> {
        CacheUnit<K, V> prev;
        CacheUnit<K, V> next;
        K key;
        V value;

        public CacheUnit(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        LRU<String, Integer> lruCache = new LRU<>(3);
        lruCache.set("a", 1);
        lruCache.set("b", 2);
        lruCache.get("a");

        CacheUnit<String, Integer> curr = lruCache.head;

        while (curr != lruCache.tail) {
            System.out.print(curr.key);
            System.out.print(curr.value);
            System.out.print(" <-> ");
            curr = curr.next;
        }

        System.out.print(curr.key);
        System.out.println(curr.value);

    }
}
