package org.example;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Core core = new Core();

        while (true) {
            String command = in.nextLine();
            core.input(command);
        }
    }
}