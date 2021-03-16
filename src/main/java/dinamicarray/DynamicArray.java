package dinamicarray;

public class DynamicArray {

    private int[] array;
    private int countElements = 0;

    public DynamicArray(int capacity) {
        this.array = new int[capacity];
    }

    public DynamicArray() {
        this.array = new int[4];
    }

    public int getCapacity() {
        return array.length;
    }

    public int size() {
        return countElements;
    }

    public void add(int value) {
        if (countElements == getCapacity()) {
            int[] temp = array.clone();
            this.array = new int[2 * (countElements)];
            for (int i = 0; i < temp.length; i++) {
                array[i] = temp[i];
            }
        }
        array[countElements] = value;
        countElements++;
    }

    public int get(int index) {

        if (index >= countElements) {
            throw new ArrayIndexOutOfBoundsException(index + " is not exist. This array has only " + countElements +
                    " elements. Please choose between 0 - " + (countElements - 1) + " !");
        }

        try {
            return array[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(index + " is not exist. This array has only " + countElements +
                    " elements. Please choose between 0 - " + (countElements - 1) + " !");
        }
    }

    public void remove(int indexToBeRemoved) {

        if (indexToBeRemoved < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative index is not valid!");
        } else if (indexToBeRemoved > countElements) {
            throw new ArrayIndexOutOfBoundsException("Index cannot be bigger than size of Array!");
        } else {
            int[] temp = array.clone();
            this.array = new int[(countElements * 2)];
            for (int i = 0; i < indexToBeRemoved; i++) {
                array[i] = temp[i];
            }

            for (int j = indexToBeRemoved; j < countElements; j++) {
                array[j] = temp[(j + 1)];
            }
            countElements--;
        }

    }

    public void insert(int index, int newValue) {

        if (index == countElements) {
            array[index] = newValue;
            countElements++;
        } else if (index > 0 && index < countElements) {
            int[] temp = array.clone();
            this.array = new int[(countElements * 2)];
            for (int i = 0; i < index; i++) {
                array[i] = temp[i];
            }
            array[index] = newValue;

            for (int j = index; j < countElements; j++) {
                array[j + 1] = temp[(j)];
            }
            countElements++;
        } else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException("Negative index is not valid!");
        } else if (index > countElements) {
            if (countElements == getCapacity()) {
                int[] temp = array.clone();
                this.array = new int[2 * (countElements)];
                for (int i = 0; i < temp.length; i++) {
                    array[i] = temp[i];
                }
            }
            array[countElements] = newValue;
            countElements++;
        }

    }

    public void prettyPrint() {
        for (int i = 0; i < countElements; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("The array has " + countElements + " elements.");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("The array has " + array.length + " length.");

        System.out.println("----------------------");
        System.out.println();

    }

    @Override
    public String toString() {

        String elements = "[";
        if (countElements == 0) {
            return "[]";
        } else {
            for (int i = 0; i < countElements - 1; i++) {
                elements += array[i] + ", ";
            }
        }

        return elements + array[countElements - 1] + "]";
    }
}
