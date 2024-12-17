public class WayFinder {
    private CountryMap map;

    public WayFinder(CountryMap map) {
        this.map = map;
    }

    public String findShortestPath(String startCity, String endCity) {
        City[] cities = map.getCities();
        int[][] graph = map.getAdjacencyMatrix();
        int numCities = cities.length;

        int[] dist = new int[numCities];
        boolean[] visited = new boolean[numCities];
        int[] prev = new int[numCities];

        for (int i = 0; i < numCities; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
            prev[i] = -1;
        }

        int startIndex = findCityInd(startCity, cities);
        int endIndex = findCityInd(endCity, cities);

        if (startIndex == -1 || endIndex == -1) {
            return "No start or end city found!";
        }

        dist[startIndex] = 0;

        for (int i = 0; i < numCities - 1; i++) {
            int minIndex = -1;
            int minDist = Integer.MAX_VALUE;

            for (int j = 0; j < numCities; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minIndex = j;
                }
            }

            if (minIndex == -1) break;

            visited[minIndex] = true;

            for (int j = 0; j < numCities; j++) {
                if (!visited[j] && graph[minIndex][j] != Integer.MAX_VALUE &&
                        dist[minIndex] + graph[minIndex][j] < dist[j]) {
                    dist[j] = dist[minIndex] + graph[minIndex][j];
                    prev[j] = minIndex;
                }
            }
        }

        if (dist[endIndex] == Integer.MAX_VALUE) {
            return "No path found between start and end cities!";
        }


        StringBuilder path = new StringBuilder();
        int current = endIndex;
        while (current != -1) {
            path.insert(0, cities[current].getName() + " -> ");
            current = prev[current];
        }
        path = new StringBuilder(path.substring(0, path.length() - 4));

        return "The Shortest path: " + path + " \nTotal Time: " + dist[endIndex] + " minute.";
    }

    private int findCityInd(String cityName, City[] cities) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getName().equals(cityName)) {
                return i;
            }
        }
        return -1;
    }
}
