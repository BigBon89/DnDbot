package org.example;

public class Core
{
    public Core()
    {
        cityNameGenerator = new CityNameGenerator();
    }
    public void Input(String command)
    {
        if (command.equals("help"))
            System.out.println("its help command");
        else
            System.out.println("Unknown command, type help for help");
    }

    private CityNameGenerator cityNameGenerator;
}
