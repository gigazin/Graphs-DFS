package DFS;

import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class DepthFirstSearch {
    private int vertexes;
    private int edges;
    private int initialVertex;
    private int endVertex;
    private boolean found;
    private String[] color;
    private int[] i, f, ant;
    private int time = 0;
    private LinkedList<LinkedList<Integer>> graph = new LinkedList<>();

    public DepthFirstSearch(String graphFile, int initVertex, int endVertex) {
        try {
            FileReader fileReader = new FileReader(graphFile);
            BufferedReader bfReader = new BufferedReader(fileReader);
            String line = bfReader.readLine();
            this.vertexes = Integer.parseInt(line); // Order
            line = bfReader.readLine();
            this.edges = Integer.parseInt(line); // Size
            this.initialVertex = initVertex;
            this.endVertex = endVertex;
            this.found = false;
            for (int i = 0; i < vertexes; i++) {
                graph.add(new LinkedList<>());
            }
            color = new String[vertexes];
            i = f = ant = new int[vertexes];
            line = bfReader.readLine();
            while (line != null) {
                String[] s = line.split(" ");
                graph.get(Integer.parseInt(s[0])).add(Integer.parseInt(s[1]));
                graph.get(Integer.parseInt(s[1])).add(Integer.valueOf(s[0]));
                line = bfReader.readLine();
            }
            begin(graph, initVertex);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void begin(LinkedList<LinkedList<Integer>> graph, int vertex) {
        for (int j = 0; j < graph.size(); j++) {
            color[j] = "WHITE";
            ant[j] = i[j] = f[j] = -1;
        }
        time++;
        visit(graph, vertex);
    }

    public void showVertexesInfo() {
        for (int j = 0; j < graph.size(); j++) {
            System.out.println("Vértice [" + j + "]: Cor -> " + color[j] + " (" + i[j] + "/" + f[j] + ")");
        }
    }

    public void path(int v) {
        if (v == initialVertex) {
            System.out.print(v + " ");
        } else {
            if (ant[v] == -1) {
                System.out.println("Caminho inexistente.");
            } else {
                path(ant[v]);
                System.out.print(v + " ");
            }
        }
    }

    public void visit(LinkedList<LinkedList<Integer>> graph, int vertex) {
        color[vertex] = "GRAY";
        i[vertex] = time++;
        for (Integer v : graph.get(vertex)) {
            if (color[v].equals("WHITE")) {
                ant[v] = vertex;
                if (found) { break; }
                if (v == endVertex) { found = true; }
                showVertexesInfo();
                path(v);
                visit(graph, v);
            }
        }
        color[vertex] = "BLACK";
        f[vertex] = time++;
    }
}
