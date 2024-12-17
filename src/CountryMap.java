public class CountryMap {
    private City[] cities;
    private int[][] adjacencyMatrix;

    public CountryMap(City[] cities, int[][] adjacencyMatrix) {
        this.cities = cities;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public City[] getCities() {
        return cities;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
