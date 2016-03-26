import java.util.*;

public class RandomGraph{
    //Matrix of adjacency of a simple graph
    int[][] matrixOfAdjacency;
    Random generator = new Random();
    
    //Method setting every element of matrix to 0
    void clearMatrix(int n){
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j <n ;j ++){
                matrixOfAdjacency[i][j] = 0;
            }
        }
    }
    //Method printing matrix to console
    void printMatrix(int n){
        for(int i = 0 ; i < n ; i ++){
            for(int j = 0 ; j < n ;j ++){
                System.out.print(matrixOfAdjacency[i][j]+ " ");
            }
            System.out.println();
        }        
    }

    //Randomly generates matrix of adjacency of given number of vertices and edges
    BasicGraphRepr generateRandomGnlGraph(int n, int l) throws IllegalArgumentException{
        if(l>(n*(n-1)/2)) throw new IllegalArgumentException("Zbyt wiele krawedzi");
        matrixOfAdjacency = new int [n][n];
        clearMatrix(n);
        //Creating l random edges
        for(int k = 0 ; k < l ; k ++){
            int firstVertex, secondVertex;
            //Creating random edges by choosing two random vertices for each edge
            firstVertex = generator.nextInt(n);
            secondVertex = generator.nextInt(n);
            
            //Checking if graph is simple, fixing if not:
                //Fixing loops
            while (firstVertex == secondVertex){
                secondVertex = generator.nextInt(n);
            }
                //Fixing multiple edges
            while(matrixOfAdjacency[firstVertex][secondVertex]==1){
                firstVertex = generator.nextInt(n);
                secondVertex = generator.nextInt(n);
                while (firstVertex == secondVertex){
                    secondVertex = generator.nextInt(n);
                }     
            }
            //Setting connection between two vertices to the matrix
            matrixOfAdjacency[firstVertex][secondVertex] ++;
            matrixOfAdjacency[secondVertex][firstVertex] ++;
        }
		//Adding random G(n,l) graph to BasicGraphRepr
        //
        //Adding vertices to BasicGraphRepr
        BasicGraphRepr g1 = new BasicGraphRepr();
        for (int i = 0; i < n; i ++){
            g1.add_vertex(i);
        }
        //Adding edges from matrix of adjacency to BasicGraphRepr
        for (int i = 0 ; i < n ; i ++){
            for (int j = i ; j < n ; j ++){
                if(matrixOfAdjacency[i][j]==1){
                    g1.add_edge(new Edge(i, j));
                }
            }
        }
		//Returning BasicGraphRepr object
		return g1;
        
    }
    //Randomly generates matrix of adjacency of given number of vertices
    //p is probability of a each vertex being connected to another
    BasicGraphRepr generateRandomGnpGraph(int n, double p) throws IllegalArgumentException{
        if(p>1) throw new IllegalArgumentException("Prawdopodobienstwo nie moze byc wieksze od 1");
        matrixOfAdjacency = new int [n][n];
        clearMatrix(n);
        for(int i = 0 ; i < n ; i ++){
            for(int j = i + 1 ; j < n ; j ++){
                //Creating each possible edge with given probability
                    //Generating random double [0,1]
                double generatedDouble = generator.nextDouble();               
                if(generatedDouble <= p){
                    matrixOfAdjacency[i][j] ++;
                    matrixOfAdjacency[j][i] ++;
                }
            }
        }
        
		//Adding random G(n,p) graph to BasicGraphRepr
        //
        //Adding vertices to BasicGraphRepr
        BasicGraphRepr g2 = new BasicGraphRepr();
        for (int i = 0; i < n; i ++){
            g2.add_vertex(i);
        }
        //Adding edges from matrix of adjacency to BasicGraphRepr
        for (int i = 0 ; i < n ; i ++){
            for (int j = i ; j < n ; j ++){
                if(matrixOfAdjacency[i][j]==1){
                    g2.add_edge(new Edge(i, j));
                }
            }
        }
		return g2;		
    }

/** set 2 , ex 5 */
/** sorting by vertex degree (using Vertex.class) */
public void sort_list_of_vertices(ArrayList<Vertex> list){
    Vertex temp = new Vertex(0,0);
    for (int i = 0; i < list.size(); i++){
        for (int j = 0; j < list.size() - i - 1; j++){
            if (list.get(j).get_cur_degree() < list.get(j + 1).get_cur_degree()){
                temp = list.get(j + 1);
                list.set(j + 1, list.get(j));
                list.set(j, temp);
            }
        }
    }
} 
/** method which use Havel algorithm to generate regular graphs
 * ATTENTION method   will generate separeted regular graphs  e.g if u type 10
 * vertecies with 2 degree each  it will genarate 2 separeted graphs 5;2 5;2 */ 
BasicGraphRepr generateRandomRegularGraph(int n, int k) throws IllegalArgumentException{
    if((n < (k + 1)) || (((n * k) % 2) != 0))
            throw new IllegalArgumentException("Vertex degree not valid!");

    ArrayList<Vertex> list_of_vertices = new ArrayList<Vertex>();
    BasicGraphRepr g1 = new BasicGraphRepr();

    for (int i = 0; i < n; i++){
        list_of_vertices.add( new Vertex(i , k)); 
        g1.add_vertex(list_of_vertices.get(i).get_index());
    } 

    while (list_of_vertices.size() != 0){
        sort_list_of_vertices(list_of_vertices);
        Vertex vtx1 = list_of_vertices.get(0);
        int next_vertex = 1;
        while (vtx1.get_cur_degree() > 0){
            g1.add_edge(new Edge(vtx1.get_index(), list_of_vertices.get(next_vertex).get_index()));
            list_of_vertices.get(next_vertex).decrement_cur_degree();
            vtx1.decrement_cur_degree();

            if (list_of_vertices.get(next_vertex).get_cur_degree() == 0){
                list_of_vertices.remove(next_vertex);
            }
            else next_vertex++;
        }
        list_of_vertices.remove(0);
    }
	return g1;
}



/**ZWSTAW3 Zad1*/
WeightedGraph generateRandomWGraph(int n, int l)
{
	BasicGraphRepr g1 = generateRandomGnlGraph(n,l);
	g1 = g1.BiggestConnectedComponent();
	WeightedGraph g2 = new WeightedGraph(g1);
	for(int i=0;i<g2.get_edges_number();i++)
		g2.set_degree(i,1+generator.nextInt(10));
	return g2;
}


// end of class
}
