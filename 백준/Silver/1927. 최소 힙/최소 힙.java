import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class CustomHeap<E extends Comparable<E>> {
    private int size;

    Object[] queue;

    CustomHeap() {
        size = 0;
        queue = new Object[100000];
    }

    private boolean grow(int minCapacity) {
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + oldCapacity < 64 ?
                oldCapacity + 2 :
                oldCapacity >>> 1;
        queue = Arrays.copyOf(queue, newCapacity);
        return true;
    }

    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException();
//        if (size >= queue.length)
//            grow(size + 1);

        //shift 연산
        int i = size;
        while (i > 0) {
            int parent = (i - 1) >>> 1;
            if (e.compareTo((E) queue[parent]) > 0)
                break;
            queue[i] = queue[parent];
            i = parent;
        }
        queue[i] = e;
        size++;

        return true;
    }

    public E remove() {
        if (size == 0) return (E) Integer.valueOf(0);
        E root = (E) queue[0];
        E e = (E) queue[size - 1];
        queue[size - 1] = null;
        int i = 0;
        while (i < (size - 1) >> 1) {
            int child1 = (i << 1) + 1;
            int child2 = child1 + 1;
            E c1 = (E) queue[child1];
            E c2 = (E) queue[child2];
            if(c2 == null || c1.compareTo(c2) < 0){
                if(c1.compareTo(e) < 0) {
                    queue[i] = c1;
                    i = child1;
                } else break;
            } else {
                if(c2.compareTo(e) < 0) {
                    queue[i] = c2;
                    i = child2;
                } else break;
            }
        }
        if(size > 1) queue[i] = e;
        size--;
        return root;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        CustomHeap<Integer> heap = new CustomHeap<>();
        for(int i = 0; i < N; i++) {
            int op = Integer.parseInt(br.readLine());
            switch(op) {
                case 0:
                    sb.append(heap.remove()).append("\n");
                    break;
                default:
                    heap.add(op);
            }
        }
        System.out.println(sb);
    }
}