package program;

import java.util.*;

public class MyLinkedList<T> implements Iterable<T> {


    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            MyLinkedListNote<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }

    public class MyLinkedListNote<T> {
        public T value;
        public MyLinkedListNote<T> next;

        public MyLinkedListNote(T value, MyLinkedListNote<T> next) {
            this.value = value;
            this.next = next;
        }

        public MyLinkedListNote(T value) {
            this(value, null);
        }
    }

    public static class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    private MyLinkedListNote<T> head = null;
    private MyLinkedListNote<T> tail = null;
    private int count = 0;

    public void checkEmpty() throws MyException {
        if (count == 0) {
            throw new MyException("List is empty");
        }
    }

    private MyLinkedListNote<T> getNode(int index) {
        int i = 0;
        for (MyLinkedListNote<T> curr = head; curr != null; curr = curr.next, i++) {
            if (i == index) {
                return curr;
            }
        }
        return null;
    }

    public void add(T value) {
        MyLinkedListNote<T> temp = new MyLinkedListNote<>(value);
        if (count == 0) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        count++;
    }

    public void addFirst(T value) {
        head = new MyLinkedListNote<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public T get(int index) throws MyException {
        checkEmpty();
        if (index < 0 || index >= count) {
            throw new MyException("Incorrect index");
        }

        return getNode(index).value;
    }

    public T getFirst() throws MyException {
        checkEmpty();
        return head.value;
    }

    public T getLast() throws MyException {
        checkEmpty();
        return tail.value;
    }

    public void insert(int index, T value) throws MyException {
        if (index < 0 || index > count) {
            throw new MyException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            MyLinkedListNote<T> prev = getNode(index - 1);
            prev.next = new MyLinkedListNote<>(value, prev.next);
            if (index == count) {
                tail = prev.next;
            }
        }
        count++;
    }

    public T remove(int index) throws MyException {
        checkEmpty();
        if (index < 0 || index >= count) {
            throw new MyException("Incorrect index");
        }
        T value;
        if (index == 0) {
            value = head.value;
            head = head.next;
            if (count == 1) {
                tail = null;
            }
        } else {
            MyLinkedListNote<T> prev = getNode(index - 1);
            MyLinkedListNote<T> curr = prev.next;
            value = curr.value;
            prev.next = curr.next;
            if (index == count - 1) {
                tail = prev;
            }
        }
        count--;
        return value;
    }

    public int size() {
        return count;
    }

    public void shuffle1() {
        MyLinkedListNote<T> headTmp = null;
        MyLinkedListNote<T> tailTmp = null;
        MyLinkedListNote<T> curr = head;

        Random random = new Random();
        int resLength = 1;
        int resListIterator = 0;

        while (curr != null) {
            MyLinkedListNote<T> tmp = curr.next;

            int randomIn = random.nextInt(resLength);

            if (randomIn == 0) {
                curr.next = headTmp;
                headTmp = curr;
                resLength++;
            } else {
                MyLinkedListNote<T> currTmp = headTmp;

                while (resListIterator < randomIn - 1) {
                    resListIterator++;
                    currTmp = currTmp.next;
                }
                if (randomIn == resListIterator) {
                    tailTmp = curr;
                }
                resListIterator = 0;
                if (resLength - 1 == count) {
                    currTmp.next = curr;
                } else {
                    curr.next = currTmp.next;
                    currTmp.next = curr;
                    resLength++;
                }
            }
            curr = tmp;
        }
        head = headTmp;
        tail = tailTmp;
    }

    public static int[] toArray(MyLinkedList<Integer> list) {
        int[] array = new int[list.size()];
        int i = 0;
        for (int item : list) {
            array[i++] = item;
        }
        return array;
    }
}