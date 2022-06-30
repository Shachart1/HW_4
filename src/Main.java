public class Main {
    public static void main(String[] args) {
        testPartA();
        //testPartB();

    }

    private static void testPartA() {
        System.out.println("Testing part A...");
        testPartA1();
        testPartA2();
        testPartA2B();
        testPartA2C();
    }

    private static void testPartA1() {
        System.out.println("Testing part A1...");

        Date[] dates = {new Date(31, 12, 2022), new Date(1, 1, 10),
                new Date(28, 2, 555), new Date(1, 3, 2022),
                new Date(29, 3, 2016),new Date(28, 2, 555),
                new Date(28, 2, 2016)};
        int[] nums = {0, 1, -1, 100, -200, -12350, 21030, 365, 366, -365, -366, -365-366};


        for (Date date : dates) {
            for (int num : nums) {
                testAddToDate(date, num);
            }
        }

        System.out.println();
    }

    private static void testAddToDate(Date date, int num) {
        Date result = DateCalculator.addToDate(date, num);
        if (num >= 0) {
            System.out.println("Adding " + num + " to " + date + " result: " + result);
        } else {
            System.out.println("Subtracting " + -num + " from " + date + " result: " + result);
        }
    }

    private static void testPartA2() {
        System.out.println("Testing part A2...");
        BinNode<Integer> root = new BinNode<>(5);
        root.setLeft(new BinNode<>(7));
        root.setRight(new BinNode<>(3));
        root.getLeft().setLeft(new BinNode<>(7, new BinNode<>(5), null));
        root.getLeft().setRight(new BinNode<>(2, new BinNode<>(9), new BinNode<>(5)));
        System.out.println("Level with most occurrences of 7: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 7));
        System.out.println("Level with most occurrences of 5: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 5));
        System.out.println("Level with most occurrences of 6: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 6));
        System.out.println("Level with most occurrences of 2: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 2));
        System.out.println();
    }

    private static void testPartA2B() {
        System.out.println("Testing part A2...");
        BinNode<Integer> root = new BinNode<>(5);
        root.setLeft(new BinNode<>(7));
        root.getLeft().setLeft(new BinNode<>(7, new BinNode<>(5),  new BinNode<>(2)));
        root.getLeft().setRight(new BinNode<>(2, null, new BinNode<>(5)));
        root.getLeft().getRight().setRight(new BinNode<>(6));
        System.out.println("Level with most occurrences of 7: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 7));
        System.out.println("Level with most occurrences of 5: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 5));
        System.out.println("Level with most occurrences of 6: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 6));
        System.out.println("Level with most occurrences of 2: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 2));
        System.out.println();
    }


    private static void testPartA2C() {
        System.out.println("Testing part A2...");
        BinNode<Integer> root = new BinNode<>(10);
        root.setLeft(new BinNode<>(7));
        root.setRight(new BinNode<>(7));
        root.getRight().setRight(new BinNode<>(4, new BinNode<>(6), new BinNode<>(9)));
        root.getLeft().setLeft(new BinNode<>(7, new BinNode<>(7),  new BinNode<>(7)));
        root.getLeft().setRight(new BinNode<>(2, null, new BinNode<>(5)));
        root.getLeft().getRight().setRight(new BinNode<>(6));
        System.out.println("Level with most occurrences of 7: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 7));
        System.out.println("Level with most occurrences of 5: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 5));
        System.out.println("Level with most occurrences of 6: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 6));
        System.out.println("Level with most occurrences of 2: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 2));
        System.out.println("Level with most occurrences of 3: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 3));
        System.out.println("Level with most occurrences of 4: " + LevelMostOccurrences.getLevelWithMostOccurrences(root, 4));
        System.out.println();
    }
}