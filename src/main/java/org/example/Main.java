package org.example;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Core core = new Core();

        while (true)
        {
            String command = in.nextLine();
            core.Input(command);
        }
    }
}