package DFS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int initialVertex, endVertex;

        System.out.print("Vértice inicial: ");
        initialVertex = in.nextInt();
        System.out.print("Vértice final: ");
        endVertex = in.nextInt();
        DepthFirstSearch dfs = new DepthFirstSearch("src\\DFS\\pequenoG.txt", initialVertex, endVertex);

        dfs.showVertexesInfo();
    }
}
