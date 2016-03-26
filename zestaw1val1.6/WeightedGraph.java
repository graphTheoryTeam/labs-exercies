import java.util.ArrayList;

class WeightedGraph extends Graph{
    private ArrayList<Integer> list_of_degrees_of_edges;

    public WeightedGraph(BasicGraphRepr b_g_r){
        super(b_g_r);
        list_of_degrees_of_edges = new ArrayList<Integer>();
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
            System.out.printf("%d %d: %d\n", edge.get_v(0) + 1, edge.get_v(1) + 1,get_degree(i));
	    i++;
        }
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
				
}
