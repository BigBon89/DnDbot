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
            System.out.println("Commands:\nhelp\ngenerate city\nroll 'formula'");
        else if (command.equals("generate city"))
            System.out.println("Generated city name: " + cityNameGenerator.GenerateCityName());
        else if (command.split(" ")[0].equals("roll"))
            System.out.println("Rolled " );
        else
            System.out.println("Unknown command, type help for help");
    }

    private CityNameGenerator cityNameGenerator;
}
