package basicoperations;

public class ProjectRewrite {
    public static void main(String[] args) {

        System.out.println("Method min result (-2; 4):");
        System.out.println(min(-2, 4));
        System.out.println("Method min2 result (-2; 4):");
        System.out.println(min2(-2, 4));
        System.out.println("_______________________________________________\n");

        System.out.println("Method max result (-1,-4,-29):");
        System.out.println(max(new int[]{-1, -4, -29}));
        System.out.println("_______________________________________________\n");

        System.out.println("Method length result (int[] {1,4,9,34,12,3,7}):");
        System.out.println(length(new int[]{1, 4, 9, 34, 12, 3, 7}));
        System.out.println("_______________________________________________\n");

        System.out.println("Method multiply result (2; -3):");
        System.out.println(multiply(2, -3));
        System.out.println("Method multiply2 result (2; -3):");
        System.out.println(multiply2(2, -3));
        System.out.println("_______________________________________________\n");

        System.out.println("Method pow result (10, 10):");
        System.out.println(pow(10, 10));
        System.out.println("Method pow2 result (10, 10):");
        System.out.println(pow2(10, 10));
        System.out.println("_______________________________________________\n");

        System.out.println("Method divmod result (5; 2):");
        int[] divmodResult = divmod(5, 2);
        System.out.println(divmodResult[0] + ", " + divmodResult[1]);
        System.out.println("_______________________________________________\n");

    }

    private static int min(int x, int y) {

        if (x < y) {
            return x;
        }
        return y;
    }

    private static int min2(int x, int y) {

        int result = (x > y) ? y : x;

        return result;
    }

    private static int max(int[] values) {
        int result = values[0];
        for (int t : values) {
            if (t > result) {
                result = t;
            }
        }
        return result;
    }


    private static int length(int[] values) {
        int count = 0;
        for (int t : values) {
            ++count;
        }

        return count;
    }

    private static int multiply(int x, int y) {
        int result = 0;
        if (x < 0) {
            for (int i = 1; i <= -x; i++) {
                result += -y;
            }
            return result;
        }
        for (int i = 1; i <= x; i++) {
            result += y;
        }
        return result;
    }

    public static int multiply2(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        } else if (num2 > 0) {
            return num1 + multiply2(num1, num2 - 1);
        } else {
            return -num1 + multiply2(num1, num2 + 1);
        }
    }

    private static int pow(int x, int y) {
        int result = 1;

        if (x == 0) {
            result = 0;
            return result;
        }

        if (y == 0) {
            result = 1;
        } else {
            for (int i = 1; i <= y; i++) {
                result *= x;
            }
        }

        return result;
    }

    private static int pow2(int x, int y) {
        int result = 1;
        double newResult = 1;

        if (x == 0) {
            result = 0;

        } else if (y == 0) {
            result = 1;

        } else if (y > 0) {
            for (int i = 1; i <= y; i++) {
                result *= x;
            }
        } else {
            for (int i = 1; i <= -y; i++) {
                newResult = newResult / (double) x;
            }
        }
        return result;
    }


    private static int[] divmod(int x, int y) {
        int rest = x;
        int count = 0;
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        if (x == 0 || y == 0) {
            return new int[]{0, 0};
        }

        while (rest >= y) {
            rest = x - y;
            x = rest;
            ++count;
        }

        return new int[]{count, rest};
    }
}
