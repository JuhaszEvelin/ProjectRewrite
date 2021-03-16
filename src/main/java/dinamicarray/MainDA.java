package dinamicarray;

public class MainDA {

    public static void main(String[] args) {
        System.out.println("Execute all tests to check your work.");

        DynamicArray d = new DynamicArray(4);
        d.add(2);
        d.add(3);
        d.add(4);

        System.out.println("Our array is: ");
        d.prettyPrint();

        System.out.println("insert to index 3 the value 5: ");
        d.insert(3,5);
        d.prettyPrint();

        System.out.println("insert to index 2 the value 22: ");
        d.insert(2, 22);
        d.prettyPrint();

        System.out.println("insert to index 10 the value 10: ");
        d.insert(10, 10);
        d.prettyPrint();

        System.out.println("remove from index 3 ");
        d.remove(3);
        d.prettyPrint();

        System.out.println("remove from index 4 ");
        d.remove(4);
        d.prettyPrint();

        System.out.println("remove from index 1  ");
        d.remove(1);
        d.prettyPrint();

        System.out.println( d.toString());

    }
}

