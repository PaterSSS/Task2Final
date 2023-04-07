package program;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;

import static java.util.Locale.setDefault;

public class Main {
    public static void main(String[] args) throws MyLinkedList.MyException {
//        setDefault(Locale.ROOT);
//        MainFrame frame = new MainFrame();
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        list.shuffle();
        for(int i: list) {
            System.out.print(i+ " ");
        }
    }
}