package dynamicarray;

import dinamicarray.DynamicArray;
import org.junit.jupiter.api.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DynamicArrayTest {
    DynamicArray arrayUnderTest;

    @BeforeEach
    void setUp() {
        arrayUnderTest = new DynamicArray(15);
    }

    @Test
    @Order(1)
    void constructor_takesOneInteger_setCapacity() {
        int expected = 15;

        arrayUnderTest = new DynamicArray(15);
        int arrayCapacity = arrayUnderTest.getCapacity();

        assertEquals(expected, arrayCapacity);
    }

    @Test
    @Order(2)
    void constructor_parameterLess_setDefaultCapacityToFour() {
        int expected = 4;

        arrayUnderTest = new DynamicArray();
        int arrayCapacity = arrayUnderTest.getCapacity();

        assertEquals(expected, arrayCapacity);
    }

    @Test
    @Order(3)
    void size_afterCreation_setToZero() {
        int expected = 0;

        int size = arrayUnderTest.size();

        assertEquals(expected, size);
    }

    @Test
    @Order(4)
    void add_multipleValues_changesSizeAccordingly() {
        int expected = 3;

        feedArray(3);

        int size = arrayUnderTest.size();

        assertEquals(expected, size);
    }

    @Test
    @Order(5)
    void get_onlyOneElementAdded_returnValue() {
        int expected = 321;
        arrayUnderTest.add(321);
        int result = arrayUnderTest.get(0);

        assertEquals(expected, result);
    }

    @Test
    @Order(6)
    void get_multipleElementsAdded_returnValueByIndex() {
        int expected = 321;
        feedArray(3);
        arrayUnderTest.add(321);

        int result = arrayUnderTest.get(3);

        assertEquals(expected, result);
    }

    @Test
    @Order(7)
    void get_indexBiggerThanSize_throwArrayIndexOutOfBoundException() {
        arrayUnderTest.add(21);
        arrayUnderTest.add(321);
        arrayUnderTest.add(1000);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayUnderTest.get(5));
    }

    @Test
    @Order(8)
    void add_moreValuesThanCapacity_dynamicallyIncreasesArray() {
        arrayUnderTest = new DynamicArray(5);
        int expected = 321;

        int count = 5;
        feedArray(count);

        arrayUnderTest.add(321);

        assertEquals(expected, arrayUnderTest.get(5));
    }

    @Test
    @Order(9)
    void remove_lastElement_decreasesSizeByOne() {
        feedArray(5);
        int expected = 4;

        arrayUnderTest.remove(4);
        int size = arrayUnderTest.size();

        assertEquals(expected, size);
    }

    @Test
    @Order(10)
    void remove_negativeIndex_throwArrayIndexOutOfBoundException() {
        feedArray(5);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayUnderTest.remove(-4));
    }

    @Test
    @Order(11)
    void remove_indexBiggerThanSize_throwArrayIndexOutOfBoundException() {
        feedArray(5);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayUnderTest.remove(6));
    }

    @Test
    @Order(12)
    void insert_indexLowerThanSize_valueCanBeGetWithGivenIndex() {
        feedArray(10);
        int newValue = 654;

        arrayUnderTest.insert(5, newValue);
        int result = arrayUnderTest.get(5);

        assertEquals(newValue, result);
    }

    @Test
    @Order(13)
    void insert_anyValue_elementsAfterIndexAreShifted() {
        feedArray(10);
        int newValue = 654;
        int prevValue = arrayUnderTest.get(5);
        arrayUnderTest.insert(5, newValue);
        int result = arrayUnderTest.get(6);

        assertEquals(prevValue, result);
    }

    @Test
    @Order(14)
    void insert_anyValue_changeSizeAccordingly() {
        int expected = 13;
        feedArray(10);
        int newValue = 654;
        arrayUnderTest.insert(5, newValue);
        arrayUnderTest.insert(5, newValue);
        arrayUnderTest.insert(5, newValue);

        int newSize = arrayUnderTest.size();

        assertEquals(expected, newSize);
    }

    @Test
    @Order(15)
    void insert_indexLowerThanZero_throwsArrayIndexOutOfBound() {
        feedArray(5);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayUnderTest.insert(-5, 10));
    }

    @Test
    @Order(16)
    void insert_indexBiggerThanSize_insertedAsLastElement() {
        feedArray(5);
        int newElement = 987;

        arrayUnderTest.insert(100, newElement);
        int lastElement = arrayUnderTest.get(5);

        assertEquals(newElement, lastElement);
    }

    @Test
    @Order(17)
    void insert_capacityIsFull_doubleCapacityDynamically() {
        arrayUnderTest = new DynamicArray(5);
        feedArray(5);
        int newElement = 987;

        arrayUnderTest.insert(100, newElement);
        int lastElement = arrayUnderTest.get(5);

        assertEquals(newElement, lastElement);
        assertEquals(10, arrayUnderTest.getCapacity());
    }


    @Test
    @Order(18)
    void toString_emptyArray_bracketsWithoutContent() {
        arrayUnderTest = new DynamicArray();
        String representation = arrayUnderTest.toString();

        assertEquals("[]", representation);
    }

    @Test
    @Order(19)
    void toString_notEmptyArray_elementsInBrackets() {
        arrayUnderTest = new DynamicArray();
        arrayUnderTest.add(100);
        arrayUnderTest.add(200);
        arrayUnderTest.add(300);
        String representation = arrayUnderTest.toString();

        assertEquals("[100, 200, 300]", representation);
    }

    private void feedArray(int count) {
        for (int i = 0; i < count; i++) {
            arrayUnderTest.add(i * 1000);
        }
    }
}
