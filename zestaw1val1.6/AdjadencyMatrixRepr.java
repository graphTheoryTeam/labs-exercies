class AdjadencyMatrixRepr extends Graph{
    private int [][] _adj_mtrx;
    public AdjadencyMatrixRepr(BasicGraphRepr b_g_r){
        super(b_g_r);
       _adj_mtrx = new int [get_vertices_number()][get_vertices_number()];
        for (int i = 0; i < get_edges_number(); i++){
            _adj_mtrx[get_b_g_r().get_edge(i).get_v(0)][get_b_g_r().get_edge(i).get_v(1)] = 1;
            _adj_mtrx[get_b_g_r().get_edge(i).get_v(1)][get_b_g_r().get_edge(i).get_v(0)] = 1;
        }
    }
    public AdjadencyMatrixRepr(IncidenceMatrixRepr i_m_r){
    	super(i_m_r);
	_adj_mtrx = new int [i_m_r.get_vertices_number()][i_m_r.get_vertices_number()];
        for (int i = 0; i < get_edges_number(); i++){
            _adj_mtrx[get_b_g_r().get_edge(i).get_v(0)][get_b_g_r().get_edge(i).get_v(1)] = 1;
            _adj_mtrx[get_b_g_r().get_edge(i).get_v(1)][get_b_g_r().get_edge(i).get_v(0)] = 1;
        }
    }
    public AdjadencyMatrixRepr(AdjadencyListRepr a_l_r){
    	super(a_l_r);
	_adj_mtrx = new int [a_l_r.get_vertices_number()][a_l_r.get_vertices_number()];
        for (int i = 0; i < get_edges_number(); i++){
            _adj_mtrx[get_b_g_r().get_edge(i).get_v(0)][get_b_g_r().get_edge(i).get_v(1)] = 1;
            _adj_mtrx[get_b_g_r().get_edge(i).get_v(1)][get_b_g_r().get_edge(i).get_v(0)] = 1;
        }
    }
    public void print_adjadency_matrix(){
        System.out.println("---------------------ADJADENCY MATRIX-------------------");
        System.out.print("   ");
        for (int c = 0; c < get_vertices_number(); c++)
            System.out.printf("v%d ", c + 1);
        System.out.println();

        for (int r = 0; r < get_vertices_number(); r++){
           System.out.printf("v%d ", r + 1);
            for (int c = 0; c < get_vertices_number(); c++)
                System.out.print(_adj_mtrx[r][c] + "  ");
           System.out.println();
        }
    }
    public int[][] get_adj_mtrx(){
        return _adj_mtrx;
    } 
    public void transform_to_adjadency_list(){
	int n=get_vertices_number();
	int[][] tab=new int[n][];
	for(int i=0;i<n;i++)
	{
		int temp=0;
		for(int j=0;j<n;j++)
			temp+=_adj_mtrx[i][j];
		tab[i]=new int[temp];
	}
	for(int i=0;i<n;i++)
	{
		int temp=0;
		for(int j=0;j<n;j++)
		{
			if(_adj_mtrx[i][j]!=0)
				tab[i][temp++]=j;
		}
	}
    System.out.println("Transform to Adjadency_list:");
	for(int i=0;i<n;i++)
	{
		System.out.print((i+1)+": ");
		for(int j=0;j<tab[i].length;j++)
		{
				System.out.print((tab[i][j]+1)+" ");
		}
		System.out.println();
	}
    }

    public void transform_to_incidence_matrix(){
   	int n=get_vertices_number();
	int k=get_edges_number();
	int[][] tab=new int[n][k];
	int temp=0;
	for(int i=0;i<n;i++)
	{
		for(int j=i;j<n;j++)
		{
			if(_adj_mtrx[i][j]!=0)
			{
				tab[i][temp]=1;
				tab[j][temp++]=1;
			}
		}
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
