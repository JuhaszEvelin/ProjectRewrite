package linkedlist;

public class SinglyLinkedList<T> {

    private class Link<T> {

        private T value;
        private Link<T> next;

        Link(T value) {
            this.value = value;
        }

        T getValue() {
            return value;
        }

        Link<T> getNext() {
            return next;
        }

        void setNext(Link<T> next) {
            this.next = next;
        }
    }

    private Link<T> head;           //

    public SinglyLinkedList() {
        this.head = null;
    }

    /**
     * Add a new element to the list.
     * The new element is appended to the current last item.
     *
     * @param value value to be appended
     */
    public void add(T value) {
        if (this.head == null) {
            this.head = new Link<T>(value);
            head.next = null;
        } else {
            Link<T> iterator = head;
            while (iterator.next != null) {
                iterator = iterator.next;
            }
            Link<T> temp = new Link<T>(value);
            temp.next = null;
            iterator.next = temp;

        }

    }

    /**
     * Get a value based on its index.
     *
     * @param index the position of requested value
     * @return value of element at index
     */
    public T get(int index) {

        if (this.head == null && head.next == null) {
            throw new IndexOutOfBoundsException("It is an empty List, please give an index between 0- " + this.size() + " !");

        } else if (index > this.size() - 1) {
            throw new IndexOutOfBoundsException("There no element in this index please give an index between 0- " + this.size() + " !");

        } else if (index == 0) {
            Link<T> iterator = head;
            return iterator.getValue();
        } else {
            Link<T> iterator = head;
            int indexToIterate = 0;
            while (indexToIterate != index) {
                iterator = iterator.next;
                indexToIterate++;
            }
            return iterator.getValue();
        }

    }

    /**
     * Returns the zero-based index of the first occurrence of a value in the list.
     *
     * @param number value to be searched
     * @return Index of 'number' if it's in the list, otherwise -1;
     */
    public int indexOf(T number) {

        if (this.head == null) {

            return -1;
            //  throw new IndexOutOfBoundsException("It is an empty List, there is no value!");

        } else {

            Link<T> iterator = head;
            int indexToIterate = 0;
            while (iterator != null) {
                if (iterator.getValue().equals(number)) {
                    return indexToIterate;
                } else {
                    iterator = iterator.next;
                    indexToIterate++;
                }
            }
        }
        return -1;

    }

    /**
     * Inserts a value at an index into the array shifting elements if necessary.
     *
     * @param index  Position of the new element
     * @param number Value to be inserted.
     */
    public void insert(int index, T number) {
        if (index == 0) {
            Link<T> newElement = new Link<T>(number);
            newElement.next = head;
            this.head = newElement;
        } else if (index == this.size()) {
            this.add(number);
        } else if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("Index is not valid, please give a valid index between 0-" + this.size() + " !");
        } else {
            Link<T> iterator = head;
            int indexToIterate = 0;
            while (indexToIterate != index - 1) {

                iterator = iterator.next;
                indexToIterate++;
            }

            Link<T> newItem = new Link<T>(number);
            newItem.next = iterator.next;
            iterator.next = newItem;
        }
    }


    /**
     * Returns with the amount of inserted nodes.
     *
     * @return Size of list.
     */
    public int size() {
        int count = 0;
        Link<T> iterator = head;
        if (iterator == null) {
            return count;
        } else {
            count++;
            while (iterator.next != null) {
                iterator = iterator.next;
                count++;
            }
        }
        return count;

    }

    /**
     * Removes the element at 'index' from the array.
     *
     * @param index Position of value to be deleted.
     */
    public void remove(int index) {
        if (index == 0) {
            if (head == null) {
                throw new IndexOutOfBoundsException();
            } else {
                head = head.getNext();
            }
            return;
        }
        Link<T> elementBeforeIndex = head;
        while (index - 1 > 0) {
            elementBeforeIndex = elementBeforeIndex.getNext();
            index--;
            if (elementBeforeIndex == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        Link<T> elementAtIndex = elementBeforeIndex.getNext();
        if (elementAtIndex == null) {
            throw new IndexOutOfBoundsException();
        }
        elementBeforeIndex.setNext(elementAtIndex.getNext());
    }


    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[]";
        } else {
            Link<T> iterator = this.head;
            String listToString = "";
            while (iterator != null) {
                listToString += iterator.getValue() + ", ";
                iterator = iterator.next;
            }
            return listToString;
        }
    }
}




