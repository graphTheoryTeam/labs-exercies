class Edge{
    int weight;
    int v1;
    int v2;
    public Edge(int vertex1, int vertex2){
            v1 = vertex1;
            v2 = vertex2;
    }
    public Edge(Edge e){
           v1=e.v1;
	   v2=e.v2;
    }
    public int get_v(int vertex){
        if (vertex == 0){
            return v1;
        }
        else{
            return v2;
        }
    }
    public boolean does_the_edge_is(int i,int j)
    {
	if((v1==i&&v2==j)||(v1==j&&v2==i))
		return true;
	return false;
    }
    public void set_v(int vertex1, int vertex2){
        v1 = vertex1;
        v2 = vertex2;
    }
}

