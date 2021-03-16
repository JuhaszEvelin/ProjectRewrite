package hashtable;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    /*
    @Test
    public void putAndGetElements() {
        // Arrange
        HashTable<String, Integer> hashTable = new HashTable<>();
        String key1 = "a String";
        String key2 = "also a String";
        Integer value1 = 0;
        Integer value2 = Integer.MAX_VALUE;
        // Act
        hashTable.put(key1, value1);
        hashTable.put(key2, value2);
        // Assert
        assertEquals(value1, hashTable.get(key1));
        assertEquals(value2, hashTable.get(key2));
    }

    @Test
    public void putAndGetElementsWithKeyHashCollision() {
        // Arrange
        HashTable<StringWrapper, Integer> hashTable = new HashTable<>();
        StringWrapper key1 = new StringWrapper("a String");
        StringWrapper key2 = new StringWrapper("also a String");
        Integer value1 = 0;
        Integer value2 = Integer.MAX_VALUE;
        // Act
        hashTable.put(key1, value1);
        hashTable.put(key2, value2);
        // Assert
        assertEquals(value1, hashTable.get(key1));
        assertEquals(value2, hashTable.get(key2));
    }

    @Test
    public void putAndGetWithNullKey() {
        // Arrange
        HashTable<Integer, String> hashTable = new HashTable<>();
        Integer key = null;
        String value = "a String";
        // Act
        hashTable.put(key, value);
        // Assert
        assertEquals(value, hashTable.get(key));
    }

    @Test
    public void putAndGetWithNullValue() {
        // Arrange
        HashTable<Integer, String> hashTable = new HashTable<>();
        Integer key = -1;
        // Act
        hashTable.put(key, null);
        // Assert
        assertNull(hashTable.get(key));
    }

    @Test
    public void overrideValueForSpecificKey() {
        // Arrange
        HashTable<String, String> hashTable = new HashTable<>();
        String key = "key";
        String value1 = "value1";
        String value2 = "value2";
        // Act
        hashTable.put(key, value1);
        hashTable.put(key, value2);
        // Assert
        assertEquals(value2, hashTable.get(key));
    }

    @Test
    public void removeKey() {
        // Arrange
        HashTable<Integer, String> hashTable = new HashTable<>();
        Integer key = 0;
        String value = "value";
        // Act
        hashTable.put(key, value);
        String remove = hashTable.remove(key);
        // Assert
        assertNull(hashTable.get(key));
        assertEquals(value, remove);
    }

    @Test
    public void clearDict() {
        // Arrange
        HashTable<Integer, String> hashTable = new HashTable<>();
        String value = "value";
        int keys = 20;
        for (int i = 0; i < keys; i++) {
            hashTable.put(i, value);
        }
        // Act
        hashTable.clear();
        // Assert
        for (int i = 0; i < keys; i++) {
            assertNull(hashTable.get(i));
        }
    }

    *//**
     * Helper class that ensures hash collision.
     *
     * @author Wojciech Makieła
     *//*
    private static class StringWrapper {
        String string;

        public StringWrapper(String string) {
            this.string = string;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StringWrapper that = (StringWrapper) o;
            return Objects.equals(string, that.string);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }*/
}