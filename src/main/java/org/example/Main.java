package org.example;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BotLogicalCore botLogicalCore = new BotLogicalCore();

        while (true) {
            String command = in.nextLine();
            botLogicalCore.input(command);
        }
    }
}