class IncidenceMatrixRepr extends Graph{
    private int [][] _ind_mtrx;
    public IncidenceMatrixRepr(BasicGraphRepr b_g_r){
        super(b_g_r);
        _ind_mtrx = new int[get_vertices_number()][get_edges_number()];
        for (int i = 0; i < get_edges_number(); i++){
            if (get_b_g_r().get_edge(i).get_v(0) == get_b_g_r().get_edge(i).get_v(1)){
                _ind_mtrx[get_b_g_r().get_edge(i).get_v(0)][i] = 2;
            }
            else{
            _ind_mtrx[get_b_g_r().get_edge(i).get_v(0)][i] = 1;
            _ind_mtrx[get_b_g_r().get_edge(i).get_v(1)][i] = 1;
            }
        }
    }
    public IncidenceMatrixRepr(AdjadencyMatrixRepr a_m_r){
        super(a_m_r);
        _ind_mtrx = new int[get_vertices_number()][get_edges_number()];
        for (int i = 0; i < get_edges_number(); i++){
            if (get_b_g_r().get_edge(i).get_v(0) == get_b_g_r().get_edge(i).get_v(1)){
                _ind_mtrx[get_b_g_r().get_edge(i).get_v(0)][i] = 2;
            }
            else{
            _ind_mtrx[get_b_g_r().get_edge(i).get_v(0)][i] = 1;
            _ind_mtrx[get_b_g_r().get_edge(i).get_v(1)][i] = 1;
            }
        }
    }
    public IncidenceMatrixRepr(AdjadencyListRepr a_l_r){
        super(a_l_r);
        _ind_mtrx = new int[get_vertices_number()][get_edges_number()];
        for (int i = 0; i < get_edges_number(); i++){
            if (get_b_g_r().get_edge(i).get_v(0) == get_b_g_r().get_edge(i).get_v(1)){
                _ind_mtrx[get_b_g_r().get_edge(i).get_v(0)][i] = 2;
            }
            else{
            _ind_mtrx[get_b_g_r().get_edge(i).get_v(0)][i] = 1;
            _ind_mtrx[get_b_g_r().get_edge(i).get_v(1)][i] = 1;
            }
        }
    }
    public void print_incidence_matrix(){
        System.out.println("---------------------INCIDENCE MATRIX-------------------");
        System.out.print("   ");

        for (int c = 0; c < get_edges_number(); c++)
            System.out.printf("e%d ", c + 1);
        System.out.println();


        for (int r = 0; r < get_vertices_number(); r++){
           System.out.printf("v%d ", r + 1);
            for (int c = 0; c < get_edges_number(); c++)
                System.out.print(_ind_mtrx[r][c] + "  ");
           System.out.println();
        }
    }
    public int[][] get_ind_mtrx(){
        return _ind_mtrx;
    }
    public void transform_to_adjadency_matrix(){
	int n=get_vertices_number();
	int k=get_edges_number();
	int[][] tab=new int[n][n];
	for(int j=0;j<k;j++)
	{
		int[] ptk=new int[2];
		int temp=0;
		for(int i=0;i<n;i++)
			if(_ind_mtrx[i][j]==1)
				ptk[temp++]=i;
		tab[ptk[0]][ptk[1]]=1;
		tab[ptk[1]][ptk[0]]=1;
	}
    System.out.println("Transform to Adjadency_matrix:");
	for(int i=0;i<tab.length;i++)
	{
		for(int j=0;j<tab[i].length;j++)
			System.out.print(tab[i][j]+" ");
		System.out.println();
	}	
}

public void transform_to_adjadency_list(){
	int n=get_vertices_number();
	int k=get_edges_number();
	int[][] tab=new int[n][];
	for(int i=0;i<n;i++)
	{
		int temp=0;
		for(int j=0;j<k;j++)
			temp+=_ind_mtrx[i][j];
		tab[i]=new int[temp];
	}
	int[] index=new int[n];
	for(int j=0;j<k;j++)
	{
		int[] ptk=new int[2];
		int temp=0;
		for(int i=0;i<n;i++)
			if(_ind_mtrx[i][j]==1)
				ptk[temp++]=i;	
		tab[ptk[0]][index[ptk[0]]++]=ptk[1];
		tab[ptk[1]][index[ptk[1]]++]=ptk[0];
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
}
