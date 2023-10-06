import java.util.*;

class MergeSequenceGenerator {

    /*
    You are given an array a[] of numbers, where a[i] is the size of the i-th list to merge.
    You have to produce the sequence in which to merge the lists, and the total cost of merging all the lists.
    Implementation Notes: Use a heap data structure.
    */
    public static void main(String[] args) {
        // using scanner class to take runtime inputs
        Scanner scanner = new Scanner(System.in);

        // input size of the array
        int size = scanner.nextInt();

        int[] a = new int[size];
        int index = 0;
        // set array elements with random number between 1 and 50
        while (size-- > 0) {
            a[index++] = (int) (Math.random() * 50 + 1);
        }

        for (int element : a) {
            System.out.print(element + " ");
        }

        System.out.println();
        System.out.println("Starting time " + System.nanoTime());

        MergeSequence sequence = generateMergeSequenceWithCost(a);

        System.out.println("Merge Sequence : " + sequence.getSequence());
        System.out.println("Merge Cost : " + sequence.getCost());
        System.out.println("End time " + System.nanoTime());

    }

    private static MergeSequence generateMergeSequenceWithCost(final int[] a) {
        int cost = 0;

        // We are creating a min-heap using priority queue.
        // The default PriorityQueue is implemented with Min-Heap,
        // that is the top element is the minimum one in the heap.
        final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (final int listSize : a) {
            priorityQueue.add(listSize);
        }

        final List<Integer> sequence = new ArrayList<>();
        while (priorityQueue.size() > 1) {
            int minSizeList = priorityQueue.poll();
            int secondMinSizeList = priorityQueue.poll();
            cost += minSizeList + secondMinSizeList;
            priorityQueue.add(minSizeList + secondMinSizeList);
            sequence.add(minSizeList);
            sequence.add(secondMinSizeList);
        }

        return new MergeSequence(sequence, cost);
    }
}

class MergeSequence {
    private final List<Integer> sequence;
    private final int cost;

    public MergeSequence(List<Integer> sequence, int cost) {
        this.sequence = sequence;
        this.cost = cost;
    }

    public List<Integer> getSequence() {
        return sequence;
    }

    public int getCost() {
        return cost;
    }
}