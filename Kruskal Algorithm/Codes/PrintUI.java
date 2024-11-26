import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintUI {
    private final Scanner scanner = new Scanner(System.in);

    public void printUI() {
        try {
            // Vertex
            System.out.print("Enter number of vertex: ");
            int numberOfVertex = scanner.nextInt();

            List<Integer> vertices = new ArrayList<>();

            System.out.print("Vertex: ");
            for (int i = 0; i < numberOfVertex; i++) {
                vertices.add(i);
                System.out.print(i + "  ");
            }

            // Edge
            int maxNumberOfEdge = Calculation.combination(numberOfVertex, 2);
            System.out.print("\nEnter number of edge(0, " + maxNumberOfEdge + "): ");
            int numberOfEdge = scanner.nextInt();
            if (numberOfEdge < 0 || numberOfEdge > maxNumberOfEdge)
                throw new Exception();

            List<Edge> edges = new ArrayList<>();

            // Each Edge
            for (int i = 0; i < numberOfEdge; i++) {
                // Source
                System.out.println("\nEdge " + i);
                System.out.print("Source: ");
                int source = scanner.nextInt();
                boolean check = false;
                for (Integer vertex: vertices) {
                    if (source == vertex) {
                        check = true;
                        break;
                    }
                }
                if (!check)
                    throw new Exception();

                // Destination
                System.out.print("Destination: ");
                int destination = scanner.nextInt();
                check = false;
                if (destination == source)
                    throw new Exception();
                for (Integer vertex: vertices) {
                    if (destination == vertex) {
                        check = true;
                        break;
                    }
                }
                if (!check)
                    throw new Exception();

                // Weight
                System.out.print("Weight: ");
                int weight = scanner.nextInt();
                if (weight < 1)
                    throw new Exception();

                // Add Edge
                for (Edge edge: edges)
                    if ((edge.source == source && edge.destination == destination) ||
                            (edge.source == destination && edge.destination == source))
                        throw new Exception();
                edges.add(new Edge(source, destination, weight));
            }

            // Kruskal Algorithm
            KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();
            Edge[] result = kruskalAlgorithm.kruskal(numberOfVertex, edges);
            int sum = 0;
            System.out.println("\nResult(Source, Destination, Weight):");
            for (Edge edge : result) {
                if (edge != null) {
                    sum += edge.weight;
                    System.out.println(edge.source + "  " + edge.destination + "  " + edge.weight);
                }
            }
            System.out.print("\nTotal Weight: " + sum);
        } catch (Exception e) {
            System.out.println("\nInvalid!");

            scanner.nextLine();
            System.out.print("Continue?(y/n) ");
            String userInput = scanner.nextLine();
            if (userInput.equals("y"))
                printUI();
        }
    }
}
