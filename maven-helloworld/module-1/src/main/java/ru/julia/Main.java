package ru.julia;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) {
        List<Integer> example = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            example.add(i);
        }

        System.out.println(Lists.reverse(example));
        LibClass.print();
    }
}
