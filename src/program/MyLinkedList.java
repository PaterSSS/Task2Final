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

    //    public void shuffle() {
//        MyLinkedListNote<T> headTmp = null;
//        MyLinkedListNote<T> tailTmp = null;
//        MyLinkedListNote<T> prevCurr = this.head;
//        Random random = new Random();
//        int resListLength = 1;
//        int resIterator = 0;
//        int i = 1;
//
//        for (MyLinkedListNote<T> curr = head.next; (curr != null) || (i == size()); curr = curr.next, i++) {
//            int randIn = random.nextInt(resListLength + 1);
//
//            for (MyLinkedListNote<T> currRes = headTmp; currRes != null; currRes = currRes.next, resIterator++) {
//                if (resIterator == randIn - 1) {
//                    if(randIn == resListLength) {
//                        tailTmp = prevCurr;
//                    }
//                    prevCurr.next = currRes.next;
//                    currRes.next = prevCurr;
//                    resListLength++;
//                    break;
//                } else if (randIn == 0) {
//                    prevCurr.next= headTmp;
//                    headTmp = prevCurr;
//                    resListLength++;
//                    break;
//                }
//            }
//            if (i == 1) {
//                headTmp = prevCurr;
//                headTmp.next = null;
//            }
//            prevCurr = curr;
//            resIterator = 0;
//            if (i == this.size()) {
//                break;
//            }
//        }
//        this.head = headTmp;
//        this.tail = tailTmp;
//    }
    public void shuffle() {

        MyLinkedListNote<T> prevCurr = this.head;
        MyLinkedListNote<T> tmp = head.next;

        MyLinkedListNote<T> headTmp = prevCurr;
        headTmp.next = null;
        MyLinkedListNote<T> tailTmp = null;

        Random random = new Random();
        int resListLength = 1;
        int resIterator = 0;
        int i = 0;

        for (MyLinkedListNote<T> curr = tmp; i < count; curr = curr.next, i++) {
            int randIn = random.nextInt(resListLength + 1);

            if (i > 0) {
                for (MyLinkedListNote<T> currRes = headTmp; currRes != null; currRes = currRes.next, resIterator++) {
                    if (resIterator == randIn - 1) {
                        if (randIn == resListLength) {
                            tailTmp = prevCurr;
                        }
                        prevCurr.next = currRes.next;
                        currRes.next = prevCurr;
                        resListLength++;
                        break;
                    } else if (randIn == 0) {
                        prevCurr.next = headTmp;
                        headTmp = prevCurr;
                        resListLength++;
                        break;
                    }
                }
            }

            prevCurr = curr;
            resIterator = 0;
            if (i == this.size() - 1) {
                break;
            }
        }
        this.head = headTmp;
        this.tail = tailTmp;
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