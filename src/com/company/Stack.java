package com.company;

import java.util.ArrayList;

public class Stack {

    private int top = -1;
    private int size;
    ArrayList<String> elements;

    public Stack() {
        this(100);
        System.out.println("100 is considered as default size");
    }

    public Stack(int size) {
        this.size = size;
        elements = new ArrayList<>(size);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(String input) {
        if (this.isFull()) throw new IllegalStateException("Error: Stack is Full");
        top++;
        elements.add(top, input);
    }

    public String pop() {
        String popReturn;
        if (this.isEmpty()) throw new IllegalStateException("Error: Stack is Empty");
        top--;
        popReturn = elements.get(top + 1);
        elements.remove(top + 1);
        return popReturn;
    }

    public void print() {
        for (int i = 0; i <= top; i++) {
            System.out.print(elements.get(i) + "  ");
        }
        System.out.println("\n");
    }

    public String peak() {
        return elements.get(top);
    }

}
