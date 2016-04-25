import java.util.ArrayList;
import java.io.*;

class WeightedGraph extends Graph{
    private ArrayList<Integer> list_of_degrees_of_edges;
    private int[][] distance_matrix;

    public WeightedGraph(BasicGraphRepr b_g_r){
        super(b_g_r);
        list_of_degrees_of_edges = new ArrayList<Integer>();
        distance_matrix = new int[this.get_vertices_number()][this.get_vertices_number()];
	for(int i = 0;i < get_edges_number();i++)
		list_of_degrees_of_edges.add(0);
    }

    
    public void set_degree(int index,int val){
	list_of_degrees_of_edges.set(index,val);
    }
    public int get_degree(int index){
	return list_of_degrees_of_edges.get(index);
    }
    public void print_edges(){
        System.out.println("---------------------EDGES-------------------");
	int i = 0;
        for (Edge edge: _b_g_r.get_list_of_edges()){
            System.out.printf("%d %d: %d\n", edge.get_v(0), edge.get_v(1), get_degree(i));
	    i++;
        }
    }
    public void write_to_file_and_print() throws IOException
    {
        PrintWriter writer = new PrintWriter("print.txt");

        /**writing to file particular vertices */
        for (int v : _b_g_r.get_list_of_vertices())
            writer.println(v);
        writer.println("#");

        /**writing to file particular egdes */
        for (Edge e : _b_g_r.get_list_of_edges()){
            writer.println(e.get_v(0));
            writer.println(e.get_v(1));
        }
        writer.println("#");

        /**writing to file weights of egdes */
        for (Integer weight : list_of_degrees_of_edges)
            writer.println(weight); 
        writer.println("#");

        writer.close();
        ProcessBuilder pb = new ProcessBuilder("python","printing_weight.py");
        Process p = pb.start();
    }


public void write_to_file_and_print2() throws IOException
    {
        PrintWriter writer = new PrintWriter("print2.txt");

        /**writing to file particular vertices */
        for (int v : _b_g_r.get_list_of_vertices())
            writer.println(v);
        writer.println("#");

        /**writing to file particular egdes */
        for (Edge e : _b_g_r.get_list_of_edges()){
            writer.println(e.get_v(0));
            writer.println(e.get_v(1));
        }
        writer.println("#");

        /**writing to file weights of egdes */
        for (Integer weight : list_of_degrees_of_edges)
            writer.println(weight); 
        writer.println("#");

        writer.close();
        ProcessBuilder pb = new ProcessBuilder("python","prin.py");
        Process p = pb.start();
    }




    public int[][] Dijkstra(int k)
    {
	int[][] temp =  new int[2][get_vertices_number()];
	ArrayList<Integer> Q = new  ArrayList<Integer>();
	for(int i = 0;i < get_vertices_number();i++)
	{
		Q.add(_b_g_r.get_vertex(i));
		temp[1][i]=-1;
		temp[0][i]=Integer.MAX_VALUE;
	}
	
	temp[0][k]=0;
	int tmp=Integer.MAX_VALUE;
	int index_min = -1;
	while(Q.size()!=0)
	{
		Q.remove((Integer)k);
		for(Integer i: Q)
			if(_b_g_r.does_the_edge_is((int)i,k)&&_b_g_r.get_index_edge(i,k)+temp[0][k]<temp[0][i])
			{
				temp[0][i] = list_of_degrees_of_edges.get(_b_g_r.get_index_edge(i,k))+temp[0][k];		
				temp[1][i] = k+1;
			}	
		/*for(int i=0;i<temp.length;i++)
		{
			for(int j=0;j<temp[0].length;j++)
				System.out.print(temp[i][j]+", ");
			System.out.println();
		}*/
		for(Integer i: Q)
			if(tmp>=temp[0][i])
			{
				tmp=temp[0][i];
				index_min=i;
			}
		k=index_min;
		tmp=Integer.MAX_VALUE;
	}
	return temp;
    }

    void  create_distance_matrix()
    {
        for (int i = 0; i < this.get_vertices_number(); i++)
        {
            int [][] temp_tab = this.Dijkstra(i);

            for (int j = 0; j < this.get_vertices_number(); j++)
                distance_matrix[i][j] = temp_tab[0][j];
        }
    }
    void print_distance_matrix()
    {
        System.out.println("Distance matrix: ");
        for (int i = 0; i < distance_matrix.length; i++)
        {
            for (int j = 0; j < distance_matrix.length; j++)
                System.out.print(distance_matrix[i][j] + " ");
            System.out.println();
        }
    
    }

}
