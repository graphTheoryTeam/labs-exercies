import java.util.*;
import java.io.*;
class Main3_1{
    public static void main(String args[]){
        RandomGraph rg = new RandomGraph();
        Scanner scan = new Scanner(System.in);
        System.out.println("Wprowadz liczbe wierzcholkow");
        int numberOfVertices = scan.nextInt();
        System.out.println("Wprowadz liczbe krawedzi");
        int numberOfEdges = scan.nextInt();
        WeightedGraph g = rg.generateRandomWGraph(numberOfVertices, numberOfEdges);

        System.out.println("Wagi krawedzi: ");
        g.print_edges();

        try
        {
            g.write_to_file_and_print();
        }
        catch(IOException e)
        {
            System.out.print("Cos... cos sie zepsulo :( "); 
        }

        System.out.println("Wprowadz wiezcholek wzgledem ktorego beda liczone dlugosci.");
        int particularVertex = scan.nextInt();
        int[][] array = g.Dijkstra(particularVertex);
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                System.out.print(array[i][j] + " "); 
            }
           System.out.println(); 
        
        }
    }
}
