package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Stack stackNumbers;
    public static Stack stackOperands;
    public static String input;
    public static String stackSizeChoice;
    public static int stackSize;

    public static void main(String[] args) {
        ArrayList<String> userInput = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome ! this is a program that convert INFIX expression to PREFIX expression  " + "||  Created by Sepehr Sadri & Fatemeh Torkzaban");
        System.out.println("If you want to set size for your Number or Operand Stack press \"y\" else press another keyboard");
        stackSizeChoice = scanner.next();
        Character charStackSizeSet = stackSizeChoice.charAt(0);
        if (charStackSizeSet.toString().equalsIgnoreCase("y")) {
            scanner = new Scanner(System.in);
            System.out.println("Enter size of your Stacks");
            try {
                stackSize = scanner.nextInt();


            } catch (InputMismatchException err) {
                System.out.println("Wrong Input !\n" + "Please enter Number");
                scanner = new Scanner(System.in);
                stackSize = scanner.nextInt();
            } finally {
                stackNumbers = new Stack(stackSize);
                stackOperands = new Stack(stackSize);
                System.out.println("size set successfully !");
            }
        } else {
            stackNumbers = new Stack();
            stackOperands = new Stack();
        }

        System.out.println("Please add your infix expression \n" + "Enter \"q\" for Exit");
        while (true) {
            scanner = new Scanner(System.in);
            input = scanner.next();
            Character firstCharacter = input.charAt(0);
            if (firstCharacter.equals(null)) throw new IllegalArgumentException("Invalid Argument : Null");
            else if (firstCharacter.equals('q')) break;
            for (int i = 0; i < input.trim().length(); i++) {
                userInput.add(input.valueOf(input.charAt(i)));
            }
        }
        Token token = new Token(userInput, stackNumbers, stackOperands);
    }
}

