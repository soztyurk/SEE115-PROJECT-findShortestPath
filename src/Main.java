import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the input file: ");
        String inputFilePath = sc.nextLine();
        System.out.println("Enter the output file: ");
        String outputFilePath = sc.nextLine();

        try {
            Scanner fileScanner = new Scanner(new File(inputFilePath));
            int cityCount = Integer.parseInt(fileScanner.nextLine().trim());
            City[] cities = new City[cityCount];
            String[] cityNames = fileScanner.nextLine().trim().split(" ");


            for (int i = 0; i < cityCount; i++) {
                cities[i] = new City(cityNames[i]);
            }


            int[][] adjacencyMatrix = new int[cityCount][cityCount];
            for (int i = 0; i < cityCount; i++) {
                for (int j = 0; j < cityCount; j++) {
                    adjacencyMatrix[i][j] = Integer.MAX_VALUE; // infinity if there is no connection between two city
                }
            }

            int routeCount = Integer.parseInt(fileScanner.nextLine().trim());
            for (int i = 0; i < routeCount; i++) {
                String[] routeData = fileScanner.nextLine().trim().split(" ");
                int city1Index = findCityIndex(routeData[0], cities);
                int city2Index = findCityIndex(routeData[1], cities);
                int time = Integer.parseInt(routeData[2]);
                adjacencyMatrix[city1Index][city2Index] = time;
                adjacencyMatrix[city2Index][city1Index] = time;
            }


            String[] startEndCities = fileScanner.nextLine().trim().split(" ");
            String startCity = startEndCities[0];
            String endCity = startEndCities[1];


            CountryMap map = new CountryMap(cities, adjacencyMatrix);
            WayFinder finder = new WayFinder(map);


            String result = finder.findShortestPath(startCity, endCity);


            System.out.println(result);
            writeOutput(outputFilePath, result);

        } catch (IOException e) {
            System.out.println("File read or write error: " + e.getMessage());
        }

    }

    private static int findCityIndex(String cityName, City[] cities) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getName().equals(cityName)) {
                return i;
            }
        }
           return -1;
    }


    private static void writeOutput(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
            System.out.println("The result was successfully saved in the " + filePath + " File.");
        } catch (IOException e) {
            System.out.println("Error during file writing: " + e.getMessage());
        }
    }
}
