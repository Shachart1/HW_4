import java.util.ArrayDeque;

/**
 * this class represents an object that finds the level with most occurrences of a given number in a tree.
 */
public class LevelMostOccurrences {

    /**
     * scan tree by levels and return the level where the given number appeared the most.
     * @param node - root of tree
     * @param num - given number to check the occurrences of.
     * @return - level in the tree with the most occurrences of the given number
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        ArrayDeque<BinNode> nodeList = new ArrayDeque<>();
        int currentRow = 0;
        int count = 0;
        int max = 0;
        int bestRow = -1;
        nodeList.add(node);
        nodeList.add(new BinNode(null)); // marking end of row
        while(!(nodeList.isEmpty())){
            while(nodeList.peek().getData() != null){
                BinNode temp = nodeList.pop();
                if(temp.getLeft() != null && temp.getLeft().getData() != null){
                    nodeList.add(temp.getLeft());
                }
                if(temp.getRight() != null && temp.getRight().getData() != null){
                    nodeList.add(temp.getRight());
                }
                Integer tempValue = (Integer) temp.getData();
                if(tempValue == num){
                    count++;
                }
            }
            nodeList.removeFirst();
            if(!(nodeList.isEmpty())) { // avoid adding null when finished the tree
                nodeList.add(new BinNode(null));
            }
            if(count > max){
                max=count;
                bestRow = currentRow;
            }
            currentRow++;
            count=0;
        }
        return bestRow;
    }
}
