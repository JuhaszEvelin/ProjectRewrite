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
        if (this.head == null) {                    //első elemet hozza létre
            this.head = new Link<T>(value);
            head.next = null;                   //sor végét jelzi, beállítom nullra mert ez az utolsó elem lesz
        } else {                                 //második elemtől hozza létre
            Link<T> iterator = head;
            while (iterator.next != null) {      //ha adott értékhez tartozó link null azaz utolsó elem
                iterator = iterator.next;       //iterátort következőre állítom
            }
            Link<T> temp = new Link<T>(value);  //segédváltozónak megadom az értéket
            temp.next = null;                   // adott értékhez tartozó következő elemre mutató link nulla mert utolsó elem
            iterator.next = temp;               //előző elem (iteratorba mentve) linkje (next) ez a létrehozott érték (temp)

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

        } else if (index > this.size() - 1) {                        //ha nagyobb indexet ad meg mint a size
            throw new IndexOutOfBoundsException("There no element in this index please give an index between 0- " + this.size() + " !");

        } else if (index == 0) {
            Link<T> iterator = head;
           /* while (iterator == null && iterator.next != null) {      //ha adott értékhez tartozó link nem null azaz utolsó elem
                iterator = iterator.next;       //iterátort következőre állítom így megáll az utolsó elemen
            }*/
            return iterator.getValue();                    //visszaadom iterátort ami az utolsó elem headje azaz értéke
        } else {
            Link<T> iterator = head;
            int indexToIterate = 0;
            while (indexToIterate != index) {      //amíg adott értékhez tartozó index = keresett index-szel
                iterator = iterator.next;       //iterátort következőre állítom így megáll az utolsó elem értékén
                indexToIterate++;              //indexek számát léptetem, megáll a keresett indexen
            }
            return iterator.getValue();         //utolsó elem értékét visszaadja


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
            while (iterator != null) {      // végignézem
                if (iterator.getValue().equals(number)) {
                    return indexToIterate;       //ha megtalálom a keresett értéket akkor visszatérek az adott indexxel
                } else {
                    iterator = iterator.next;       //iterátort következőre állítom így megáll az utolsó elemen
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
    public void insert(int index, T number) {       //T value lesz
        if (index == 0) {             //első elemnek berakja ha index ==0
            Link<T> newElement = new Link<T>(number);    //létrehozom az első elem értékét
           newElement.next = head;
            this.head = newElement;            //megadom az új első elem linkjét azaz a korábbi első elem értékét
        } else if (index == this.size()) {           //utolsó elemnek berakja ha index = lista méretével
            this.add(number);
        } else if(index < 0 || index > this.size()){
            throw new IndexOutOfBoundsException("Index is not valid, please give a valid index between 0-"+ this.size() + " !");
        } else {
            Link<T> iterator = head;
            int indexToIterate = 0;
            while (indexToIterate != index-1) {      //amíg adott értékhez tartozó index előtti elemhez nem ér

                iterator = iterator.next;       //iterátort következőre állítom így megáll a keresett elem előtti értékén
                indexToIterate++;
                    //indexek számát léptetem, megáll a keresett indexen
            }

            Link<T> newItem = new Link<T>(number);  //segédváltozónak megadom az értéket
            newItem.next = iterator.next;   // adott értékhez tartozó következő elemre mutató link az erdeti elem érétke
            iterator.next = newItem;      //előző elem előtti (iterátorba mentve) linkje az új érték (temp)

        }

        }


        /**
         * Returns with the amount of inserted nodes.
         *
         * @return Size of list.
         */
        public int size () {                                //2. amit megcsinálunk
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

         * @param index Position of value to be deleted.
         */
        public void remove ( int index){
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
            if (this.size() == 0){
                return "[]";
            } else {
                Link<T> iterator = this.head;
                String listToString = "";
                while (iterator != null) {      //amíg adott értékhez tartozó index előtti elemhez nem ér
                    listToString += iterator.getValue() + ", ";
                    iterator = iterator.next;       //iterátort következőre állítom így megáll a keresett elem előtti értékén
                }
                return listToString;
            }
    }
}




