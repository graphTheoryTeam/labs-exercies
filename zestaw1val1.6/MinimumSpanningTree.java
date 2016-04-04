import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Random;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
 
class MinimumSpanningTree{
    private int numberOfVertices;
    private List<Edge> listOfEdges;
    public static final int HIGH_VAL = 999;
    private int spanningTree[][];
    private int visited[];
    public void KruskalAlgorithm(){
        int adjacencyMatrix[][];
        Scanner scan = new Scanner(System.in);
        System.out.println("MST bedzie wyznaczone na grafie losowym o zadanej liczbie wierzcholkow i krawedzi.");
        System.out.println("Wprowadz liczbe wierzcholkow");
        numberOfVertices = scan.nextInt();
        System.out.println("Wprowadz liczbe krawedzi");
        int numberOfEdges = scan.nextInt();
        RandomGraph rwg = new RandomGraph();
        WeightedGraph wg = rwg.generateRandomWGraph(numberOfVertices, numberOfEdges);
        adjacencyMatrix = new int[numberOfVertices + 1][numberOfVertices + 1];        
        listOfEdges = new LinkedList<Edge>();
        spanningTree = new int[numberOfVertices + 1][numberOfVertices + 1];
        visited = new int[numberOfVertices + 1];
        int id = 0;
        for (Edge edge: wg._b_g_r.get_list_of_edges()){
			adjacencyMatrix[edge.get_v(0)+1][edge.get_v(1)+1] = wg.get_degree(id);
			adjacencyMatrix[edge.get_v(1)+1][edge.get_v(0)+1] = wg.get_degree(id);
			id++;
        }  
        System.out.println("Macierz sasiedztwa z wagami krawedzi dla grafu losowego (0 - brak krawedzi):");
        for (int i = 1; i <= numberOfVertices; i++)
            System.out.print("\tv" + i);
        System.out.println();
        for (int i = 1; i <= numberOfVertices; i++){
            System.out.print("v"+ i);
            for (int j = 1; j <= numberOfVertices; j++){   
                System.out.print("\t"+adjacencyMatrix[i][j] + " ");
            }   
            System.out.println();
        }
        for (int i = 1; i <= numberOfVertices; i++){
            for (int j = 1; j <= numberOfVertices; j++){               
                if (i == j){
                    adjacencyMatrix[i][j] = 0;
                    continue;
                }
                if (adjacencyMatrix[i][j] == 0){
                    adjacencyMatrix[i][j] = HIGH_VAL;
                }
            }
        }
        boolean done = false;
        for (int v1 = 1; v1 <= numberOfVertices; v1++){
            for (int v2 = 1; v2 <= numberOfVertices; v2++){
                if ((adjacencyMatrix[v1][v2] != HIGH_VAL) && (v1 != v2)){
                    Edge weightedEdge = new Edge(v1, v2);
                    weightedEdge.weight = adjacencyMatrix[v1][v2];
                    adjacencyMatrix[v2][v1] = HIGH_VAL;
                    listOfEdges.add(weightedEdge);
                }
            }
        }
        Collections.sort(listOfEdges, new EdgeComparator());
        for (Edge weightedEdge : listOfEdges){
            spanningTree[weightedEdge.v1][weightedEdge.v2] = weightedEdge.weight;
            spanningTree[weightedEdge.v2][weightedEdge.v1] = weightedEdge.weight;
            if (hasCycle(weightedEdge.v1, spanningTree)){
                spanningTree[weightedEdge.v1][weightedEdge.v2] = 0;
                spanningTree[weightedEdge.v2][weightedEdge.v1] = 0;
                weightedEdge.weight = -1;
                continue;
            }
            visited[weightedEdge.v1] = 1;
            visited[weightedEdge.v2] = 1;
            for (int i = 0; i < visited.length; i++){
                if (visited[i] == 0){
                    done = false;
                    break;
                }
                else{
                    done = true;
                }
            }
            if (done)
                break;
        }
        System.out.println("Minimalne drzewo rozpinajace zadanego grafu wazonego (pozostale krawedzie zostaly usuniete):");
        for (int i = 1; i <= numberOfVertices; i++)
            System.out.print("\tv" + i);
        System.out.println();
        for (int i = 1; i <= numberOfVertices; i++){
            System.out.print("v" + i + "\t");
            for (int j = 1; j <= numberOfVertices; j++){
                System.out.print(spanningTree[i][j] + "\t");
            }
            System.out.println();
        }
    }

    boolean hasCycle(int sVertex, int tempAdjMatrix[][]){
        boolean hasCycle = false;
        Stack<Integer> stack = new Stack<Integer>();
        int adjacencyMatrix[][];
        int numberOfNodes = tempAdjMatrix[sVertex].length - 1;
        adjacencyMatrix = new int[numberOfNodes + 1][numberOfNodes + 1];
        for (int v1 = 1; v1 <= numberOfNodes; v1++){
            for (int v2 = 1; v2 <= numberOfNodes; v2++){
                adjacencyMatrix[v1][v2] = tempAdjMatrix[v1][v2];
            }
        }
        int visited[] = new int[numberOfNodes + 1];
        int elem = sVertex;
        int i = sVertex;
        visited[sVertex] = 1;
        stack.push(sVertex); 
        while (!stack.isEmpty()){
            elem = stack.peek();
            i = elem;
            while (i <= numberOfNodes){
                if (adjacencyMatrix[elem][i] >= 1 && visited[i] == 1){
                    if (stack.contains(i)){
                        hasCycle = true;
                        return hasCycle;
                    }
                }
                if (adjacencyMatrix[elem][i] >= 1 && visited[i] == 0){
                    stack.push(i);
                    visited[i] = 1;
                    adjacencyMatrix[elem][i] = 0;
                    adjacencyMatrix[i][elem] = 0;
                    elem = i;
                    i = 1;
                    continue;
                }
                i++;
            }
            stack.pop();
        }
        return hasCycle;
    }
}

class EdgeComparator implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2){
        if (e1.weight > e2.weight)
            return 1;
        if (e1.weight < e2.weight)
            return -1;
        return 0;
    }
} 
