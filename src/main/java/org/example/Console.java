package org.example;

import java.util.Scanner;

public class Console implements InputOutput {
    private final Scanner scanner;

    Console() {
        scanner = new Scanner(System.in);
    }

    public void print(String text) {
        System.out.println(text);
    }

    public String getText() {
        return scanner.nextLine();
    }
}
