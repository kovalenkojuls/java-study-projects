package ru.julia.bytecodes;

/*
> javac .\SimpleClass.java
> javap -c -verbose SimpleClass.class > SimpleClass.txt
*/
public class SimpleClass {
    int add(int x, int y) {
        return x + y;
    }
}