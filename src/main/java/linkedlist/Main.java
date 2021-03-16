
package linkedlist;

public class Main {

    private static SinglyLinkedList list;

    public static void main(String[] args) {
        System.out.println("Your implementation is correct when there's no failing test after running mvn clean test.");
        if (args.length < 3) {
            terminate();
        }

        int numberOfElements = Integer.valueOf(args[0]);
        String command = args[1];

        list = new SinglyLinkedList();
        System.out.println("After you're done try these to get a feel for computational cost.");

        feedList(numberOfElements);

        System.out.println();
        System.out.println(list.toString());
        System.out.println();

        long start = 0, end = 0;
        if ("access".equals(command)) {
            int i = Integer.valueOf(args[2]);

            start = System.currentTimeMillis();
            list.get(i);
            end = System.currentTimeMillis();
        } else if ("search".equals(command)) {
            int x = Integer.valueOf(args[2]);

            start = System.currentTimeMillis();
            list.indexOf(x);
            end = System.currentTimeMillis();

        } else if ("insert".equals(command)) {
            int i = Integer.valueOf(args[2]);
            int x = Integer.valueOf(args[3]);

            start = System.currentTimeMillis();
            list.insert(i, x);
            end = System.currentTimeMillis();

        } else if ("delete".equals(command)) {
            int i = Integer.valueOf(args[2]);

            start = System.currentTimeMillis();
            list.remove(i);
            end = System.currentTimeMillis();

        } else {
            terminate();
        }

        System.out.format("%s took: %sms\n", command, end - start);
    }

    private static void feedList(int numberOfElements) {
        System.out.println("Building the linked list ... this may take a while :)");
        for (int i = 0; i < numberOfElements; i++) {
            list.insert(i, i);
        }
    }

    private static void terminate() {
        System.out.println("Invalid operation!");
        printAvailableCommands();
        System.exit(1);
    }

    private static void printAvailableCommands() {
        System.out.println("The following operations available: access, search, insert, delete");
        System.out.println("You can run the application like follows:");

        String runPrefix = "java Main <number of elements>";
        System.out.format("%s access <index to access>\n", runPrefix);
        System.out.format("%s search <number to search for>\n", runPrefix);
        System.out.format("%s insert <index to insert at> <number to insert>\n", runPrefix);
        System.out.format("%s delete <index to delete at>\n", runPrefix);
    }

}
