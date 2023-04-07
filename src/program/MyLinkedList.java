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

    public MyLinkedListNote<T> head = null;
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
// написать реализацию чтобы не зацикливался массив потому что второй цикл начинает идти по первому списку
// значит он должен обнуляться но при этом первый цикл тогда обрывается либо идти по предпоследнему либо
    // либо что-то придумать для этой реальзации. аааааа думать сложно
    public void shuffle() {
        MyLinkedListNote<T> headTmp = null;
        MyLinkedListNote<T> tailTmp = null;

        Random random = new Random();
        int i = 0;
        int j = 0;
        int sizeOfNewList = 1;
        for(MyLinkedListNote<T> curr = head; curr != null; curr = curr.next, i++) {
            int newIndex = random.nextInt(sizeOfNewList);
            if(i ==0) {
                headTmp = curr;
                headTmp.next.next = null;
            }
            for(MyLinkedListNote<T> currTmp = headTmp; currTmp != null; currTmp = currTmp.next, j++) {
                if (newIndex == j - 1) {
                    currTmp.next = curr;
                    curr.next = currTmp.next.next;
                    sizeOfNewList++;
                }
            }
        }
        head = headTmp;
    }

    public MyLinkedListNote<T> shuffleLinkedList(MyLinkedListNote<T> head) {
        if (head == null) return null;

        // select a random node
        MyLinkedListNote<T> node = head;
        while (node.next != null)
            node = node.next;
        MyLinkedListNote<T> randomNode = node;

        // swap the next pointers of linked list
        MyLinkedListNote<T> curr = head;
        while (curr != randomNode) {
            // swap next pointer of curr node
            MyLinkedListNote<T> temp = curr.next;
            curr.next = randomNode.next;
            randomNode.next = temp;

            // move curr and random nodes one
            // step ahead
            curr = temp;
            randomNode = randomNode.next;
        }
        return head;
    }
//public MyLinkedListNote<T> shuffleLinkedList()
//{
//    MyLinkedListNote<T> head = null;
//
//    MyLinkedList<T>
//    // выберите случайную вершину
//    MyLinkedListNote<T> node = head;
//    while (node.next != null)
//        node = node.next;
//    MyLinkedListNote<T> randomNode = node;
//
//    // Перемешать вершины связанного списка между собой
//    MyLinkedListNote<T> curr = head;
//    while (curr != randomNode)
//    {
//        // Swap next pointer of current node and random node
//        MyLinkedListNote<T> temp = curr.next;
//        curr.next = randomNode.next;
//        randomNode.next = temp;
//
//        // Move current and random nodes one step ahead
//        curr = temp;
//        randomNode = randomNode.next;
//    }
//
//    return head;
//}

//    public void shuffleLinkedList()
//    {
//        if (head==null) return;
//
//        // select a random node
//        MyLinkedListNote<T> node = head;
//        while (node.next != null)
//            node = node.next;
//        MyLinkedListNote<T> randomNode = node;
//
//        // swap the next pointers of linked list
//        MyLinkedListNote<T> curr = head;
//        while (curr != randomNode)
//        {
//            // swap next pointer of curr node
//            MyLinkedListNote<T> temp = curr.next;
//            curr.next = randomNode.next;
//            randomNode.next = temp;
//
//            // move curr and random nodes one
//            // step ahead
//            curr = temp;
//            randomNode = randomNode.next;
//        }
//
//    }

//    public MyLinkedList<T> shuffle() {
//        Random random = new Random();
//        HashSet<Integer> values = new HashSet<>();
//        MyLinkedList<T> resultList = new MyLinkedList<>();
//        for (int i = 0; i < count; i++) {
//            values.add(i);
//        }
//        for (int i = 0; i < count; i++) {
//            if (i == count - 1) {
//                Object[] a = values.toArray();
//                T value = getNode(((int) a[0])).value;
//                resultList.add(value);
//                break;
//            }
//            int j = random.nextInt(count);
//
//            if (values.contains(j)) {
//                values.remove(j);
//                T value = getNode(j).value;
//                resultList.add(value);
//            } else {
//                i--;
//            }
//
//        }
//
//
//        return resultList;
//    }

    public static int[] toArray(MyLinkedList<Integer> list) {
        int[] array = new int[list.size()];
        int i = 0;
        for (int item : list) {
            array[i++] = item;
        }
        return array;
    }

}