import java.util.ArrayList;

class AdjadencyListRepr extends Graph{
    private ArrayList<ArrayList<Integer>> _adj_list;
    public AdjadencyListRepr(BasicGraphRepr b_g_r){
        super(b_g_r);
        _adj_list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < get_vertices_number(); i++){
            _adj_list.add(new ArrayList<Integer>());
        }
        for (int i =0; i < get_edges_number(); i++){
            if (get_b_g_r().get_edge(i).get_v(0) == get_b_g_r().get_edge(i).get_v(1)){
                _adj_list.get(get_b_g_r().get_edge(i).get_v(0)).add(get_b_g_r().get_edge(i).get_v(1));
            }
            else{
                _adj_list.get(get_b_g_r().get_edge(i).get_v(0)).add(get_b_g_r().get_edge(i).get_v(1));
                _adj_list.get(get_b_g_r().get_edge(i).get_v(1)).add(get_b_g_r().get_edge(i).get_v(0));
            }
        }
    }
    public AdjadencyListRepr(AdjadencyMatrixRepr a_m_r){
        super(a_m_r);
        _adj_list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < get_vertices_number(); i++){
            _adj_list.add(new ArrayList<Integer>());
        }
        for (int i =0; i < get_edges_number(); i++){
            if (get_b_g_r().get_edge(i).get_v(0) == get_b_g_r().get_edge(i).get_v(1)){
                _adj_list.get(get_b_g_r().get_edge(i).get_v(0)).add(get_b_g_r().get_edge(i).get_v(1));
            }
            else{
                _adj_list.get(get_b_g_r().get_edge(i).get_v(0)).add(get_b_g_r().get_edge(i).get_v(1));
                _adj_list.get(get_b_g_r().get_edge(i).get_v(1)).add(get_b_g_r().get_edge(i).get_v(0));
            }
        }
    }
    public AdjadencyListRepr(IncidenceMatrixRepr i_m_r){
        super(i_m_r);
        _adj_list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < get_vertices_number(); i++){
            _adj_list.add(new ArrayList<Integer>());
        }
        for (int i =0; i < get_edges_number(); i++){
            if (get_b_g_r().get_edge(i).get_v(0) == get_b_g_r().get_edge(i).get_v(1)){
                _adj_list.get(get_b_g_r().get_edge(i).get_v(0)).add(get_b_g_r().get_edge(i).get_v(1));
            }
            else{
                _adj_list.get(get_b_g_r().get_edge(i).get_v(0)).add(get_b_g_r().get_edge(i).get_v(1));
                _adj_list.get(get_b_g_r().get_edge(i).get_v(1)).add(get_b_g_r().get_edge(i).get_v(0));
            }
        }
    }
    public void print_adjadency_list(){
        System.out.println("---------------------ADJADENCY LIST-------------------");
        for (int r = 0; r < get_vertices_number(); r++){
            System.out.printf("%d: ", r + 1);
            for (int c = 0; c <_adj_list.get(r).size() ; c++)
                System.out.printf("%d ", _adj_list.get(r).get(c) + 1);
            System.out.println();
        }
    }
    public ArrayList<ArrayList<Integer>> get_adj_list(){
        return _adj_list;
    }
    public void transform_to_adjadency_matrix(){
	int n=get_vertices_number();
	int[][] tab=new int[n][n];
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<_adj_list.get(i).size();j++)
			tab[i][_adj_list.get(i).get(j)]=1;
	}
    System.out.println("Transform to Adjadency_matrix:");
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
			System.out.print(tab[i][j]+" ");
		System.out.println();
	}
    }

    public void transform_to_incidence_matrix(){
	int n=get_vertices_number();
	int k=get_edges_number();
	int[][] tab=new int[n][k];
	int temp=0;
	for(int i=0;i<n;i++)
		for(int j=0;j<_adj_list.get(i).size();j++)
			if(_adj_list.get(i).get(j)>i)
			{
				tab[i][temp]=1;
				tab[_adj_list.get(i).get(j)][temp++]=1;
			}
    	System.out.println("Transform to Incidence_matrix:");
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<k;j++)
			System.out.print(tab[i][j]+" ");
		System.out.println();
	}
    }
}
