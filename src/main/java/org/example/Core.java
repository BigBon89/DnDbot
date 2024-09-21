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
            System.out.println("Commands:\nhelp\ngenerate city");
        else if (command.equals("generate city"))
            System.out.println("Generated city name: " + cityNameGenerator.GenerateCityName());
        else
            System.out.println("Unknown command, type help for help");
    }

    private CityNameGenerator cityNameGenerator;
}
