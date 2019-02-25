package com.company;

import java.util.ArrayList;

public class Token {
    public static int checkPriority = 0;
    String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] operands = {"-", "+", "*", "/"};
    String pushableString;
    Stack stackNumbers;
    Stack stackOperands;
    boolean havePermition;
    ArrayList<String> finalArray = new ArrayList<>();

    public Token(ArrayList<String> arrayList, Stack stackNumbers, Stack stackOperands) {
        this.stackNumbers = stackNumbers;
        this.stackOperands = stackOperands;
        arrayList = generateNumbers(arrayList);
        System.out.println("Your first Array is :  " + arrayList.toString());
        System.out.println("Your prefix expression step by step is :  ");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equalsIgnoreCase("(")) {
                havePermition = true;
                break;
            }
        }
        if (havePermition) {
            generatePermition(arrayList);
            generateStack(finalArray, 0, finalArray.size());
        } else {
            generateStack(arrayList, 0, arrayList.size());
        }
        System.out.println("Final prefix expression :  ");
        stackNumbers.print();


    }

    public void generatePermition(ArrayList<String> input) {
        int firstOper;
        int lastOper;
        for (int i = 0; i < input.size(); i++) {
            firstOper = input.lastIndexOf("(");
            lastOper = input.indexOf(")");
            generateStack(input, firstOper + 1, lastOper);
            input = replaceArray(input, firstOper, lastOper);
            finalArray = input;
        }
    }

    public ArrayList<String> replaceArray(ArrayList<String> input, int first, int last) {
        ArrayList<String> newArray = new ArrayList<>();
        for (int j = 0; j < first; j++) {
            newArray.add(input.get(j));
        }
        for (int i = last + 1; i < input.size(); i++) {
            newArray.add(input.get(i));
        }
        return newArray;
    }

    public void generateStack(ArrayList<String> input, int first, int last) {
        for (int i = first; i < last; i++) {
            int level = checkLevel(input.get(i));

            if (checkIsNumber(input.get(i))) {
                stackNumbers.push(input.get(i));

            } else if (level >= checkPriority) {
                stackOperands.push(input.get(i));
                checkPriority = checkLevel(input.get(i));
                stackOperands.print();

            }
            if (i == last - 1 || !(checkLevel(input.get(i)) >= checkPriority)) {

                while (!stackOperands.isEmpty()) {
                    pushableString = stackNumbers.pop() + " " + stackOperands.pop();
                    stackNumbers.push(stackNumbers.pop() + " " + pushableString);

                }
                checkPriority = 0;
            }
            stackNumbers.print();

            stackOperands.print();
        }
    }

    public ArrayList<String> generateNumbers(ArrayList<String> arrayList) {
        String numberElement;
        String zeroElement;

        for (int i = 1; i < arrayList.size(); i++) {
            if (checkIsNumber(arrayList.get(i)) && checkIsNumber(arrayList.get(i - 1))) {
                numberElement = arrayList.get(i - 1).concat(arrayList.get(i));
                arrayList.set(i - 1, numberElement);
                arrayList.remove(i);
                i--;
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).startsWith("0")) {
                zeroElement = arrayList.get(i).substring(1);
                arrayList.set(i, zeroElement);
            }
        }
        return arrayList;
    }

    public int checkLevel(String operand) {
        switch (operand) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "/":
                return 2;
            case "*":
                return 2;
            case "^":
                return 3;
        }
        return 0;
    }

    public boolean checkIsNumber(String element) {
        if (element.length() > 1) return true;
        for (int i = 0; i < numbers.length; i++) {
            if (element.equals(numbers[i]))
                return true;
        }
        return false;
    }
    /*   public void fillOprands(ArrayList<String> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < operandss.length; j++) {
                if (input.get(i).equals(operandss[j])) oper.add(input.get(i));
            }
        }

    }*/
}
