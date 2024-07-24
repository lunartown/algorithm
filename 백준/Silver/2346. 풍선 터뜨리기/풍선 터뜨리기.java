import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

class Balloon {
    int idx;
    int num;

    Balloon(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Balloon [idx=" + idx + ", num=" + num + "]";
    }

    @Override
    public boolean equals(Object o) {
        Balloon b = (Balloon) o;
        return (this.idx == b.getIdx()) && (this.num == b.getNum());
    }

    public int getIdx() {
        return idx;
    }

    public int getNum() {
        return num;
    }
}

class CircularLinkedList<E> extends LinkedList<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    int size = 0;
    Node<E> first;
    Node<E> last;

    public boolean add(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, first);
        last = newNode;
        if (l == null) {
            first = newNode;
            last = newNode;
        } else {
            l.next = newNode;
            first.prev = newNode;
        }
        size++;
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        succ = first;
        pred = last;

        for (Object o : a) {
            E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            last = pred;
        } else {
            last = pred;
            pred.next = succ;
            first.prev = pred;
        }

        size += numNew;
        return true;
    }

    public ListItr listIterator() {
        return new ListItr();
    }

    @Override
    public String toString() {
        return "CircularLinkedList{" +
                "size=" + size +
                ", first=" + first.item +
                ", last=" + last.item +
                '}';
    }
    private class ListItr implements ListIterator<E> {
        public Node<E> next;

        public ListItr() {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Node<E> lastReturned = next;
            next = next.next;
            return lastReturned.item;
        }

        @Override
        public boolean hasPrevious() {
            return size > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            Node<E> lastReturned = next.prev;
            next = next.prev;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return Integer.parseInt(next.item.toString());
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
            if(!hasNext())
                throw new NoSuchElementException();
            final Node<E> p = next.prev;
            final Node<E> n = next.next;
            if(next.item.equals(first.item)) {
                first = n;
            }

            if(next.item.equals(last.item)) {
                last = p;
            }

            p.next = n;
            n.prev = p;
            size--;
            next = n;
        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        AtomicInteger idx = new AtomicInteger(1);
        CircularLinkedList<Balloon> list = Stream.of(br.readLine().split(" "))
                .map(i -> new Balloon(idx.getAndIncrement(), Integer.parseInt(i)))
                .collect(CircularLinkedList::new, CircularLinkedList::add, CircularLinkedList::addAll);

        ListIterator<Balloon> it = list.listIterator();
        int nextNum = 0;
        int index = 1;
        for(int i = 0; i < N - 1; i++) {
            index = it.next().idx;
            it.previous();
            sb.append(index).append(' ');
            Balloon balloon = it.next();
            it.previous();
            it.remove();
            nextNum = balloon.num;
            if(nextNum > 0) {
                for(int j = 0; j < nextNum - 1; j++) {
                    it.next();
                }
            } else {
                for(int j = 0; j < -nextNum; j++) {
                    it.previous();
                }
            }
        }
        sb.append(it.next().idx);

        System.out.println(sb);
    }
}