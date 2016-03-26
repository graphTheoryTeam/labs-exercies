import java.util.ArrayList;
class BasicGraphRepr{

    private ArrayList<Edge> list_of_edges;
    private ArrayList<Integer> list_of_vertices;
	
    public BasicGraphRepr(){
        list_of_edges = new ArrayList<Edge>();
        list_of_vertices = new ArrayList<Integer>();
    }
    public BasicGraphRepr(BasicGraphRepr b_g_r){
	list_of_edges = new ArrayList<Edge>();
        list_of_vertices = new ArrayList<Integer>();
	for(int i = 0; i < b_g_r.get_amount_of_edges() ; i++){
		list_of_edges.add(new Edge(b_g_r.list_of_edges.get(i)));
	}
	for(int i = 0; i < b_g_r.get_amount_of_vertices() ; i++){
		list_of_vertices.add(b_g_r.list_of_vertices.get(i));
	}
    }
    public BasicGraphRepr(ArrayList<Edge> edges, ArrayList<Integer> vertices){
        list_of_edges = edges;
        list_of_vertices = vertices;
    }
    public void add_vertex(int vertex){
        list_of_vertices.add(vertex);
    }
     public void add_edge(Edge edge){
        list_of_edges.add(edge);
    }
    public int get_vertex(int index){
        return list_of_vertices.get(index);
    }
    public Edge get_edge(int index){
        return list_of_edges.get(index);
    }
    public Edge get_edge(int v1,int v2){
    	for(int i =0;i<list_of_edges.size();i++)
		if(get_edge(i).does_the_edge_is(v1,v2))
			return get_edge(i);
	return null;
    }
    public int get_index_edge(int v1,int v2){
    	for(int i =0;i<list_of_edges.size();i++)
		if(get_edge(i).does_the_edge_is(v1,v2))
			return i;
	return -1;
    }
    public int get_amount_of_vertices(){
        return list_of_vertices.size(); 
    }
    public int get_amount_of_edges(){
        return list_of_edges.size(); 
    }
    public ArrayList<Edge> get_list_of_edges(){
        return list_of_edges; 
    }
    public ArrayList<Integer> get_list_of_vertices(){
        return list_of_vertices; 
    }
    /*public void set_degrees_of_vertices()*/
  
    public void print_edges(){
        System.out.println("---------------------EDGES-------------------");
        for (Edge edge: list_of_edges){
            System.out.printf("%d %d\n", edge.get_v(0) + 1, edge.get_v(1) + 1);
        }
    }
    public void print_vertices(){
        System.out.println("---------------------VERTICES-------------------");
        for (Integer vertex: list_of_vertices){
            System.out.printf("%d\n", vertex + 1);
        }
    }

    public void del_edge(int v1,int v2)
    {
	Edge e = get_edge(v1,v2);
	list_of_edges.remove(e);
     }
	
    /**Checks if edge is in graph.*/
    public boolean does_the_edge_is(int v1,int v2)
    {
	for(int i =0;i<list_of_edges.size();i++)
		if(get_edge(i).does_the_edge_is(v1,v2))
			return true;
	return false;
    }

    //(*(*(*(*(*(*(*(*(*()*)*)*)*)*)*)*)*
    // SECOND SET
    //(*(*(*(*(*(*(*(*(*()*)*)*)*)*)*)*)*
    //
    // checking if it is hamilton's graph
    //  Jeśli graf prosty o n wierzchołkach ma co najmniej m krawędzi, gdzie:
    //  m = 0.5 * (n - 1)(n - 2) + 2
    //  to jest hamiltonowski
    //  A Hamiltonian graph, also called a Hamilton graph,
    //  is a graph possessing a Hamiltonian cycle.
    //  A graph that is not Hamiltonian is said to be nonhamiltonian. 
    public void check_if_its_hamiltonian(){
         float n = get_amount_of_vertices();
         float m = get_amount_of_edges();
        if (m == (0.5 * (n - 1) * (n - 2) + 2)){
            System.out.println("*************************");
            System.out.println("It is hamiltonian graph!");
            System.out.println("*************************");
        }
        else{
            System.out.println("*************************");
            System.out.println("It is nonhamiltionian graph!!");
            System.out.println("*************************");
        }
    }

    /**Search biggest connected component*/  
    BasicGraphRepr BiggestConnectedComponent()
    {
	int[] tmp = new int[get_amount_of_vertices()];
	int k=1;
	for(int i=0;i<tmp.length;i++)
		if(tmp[i]==0)
		{	
			this.DFS(tmp,i,k++);
		}
	int[] count = new int[k-1];
	for(int i=0;i<tmp.length;i++)
		count[tmp[i]-1]+=1; 
	int temp=0;
	int nr_graph=0;
	for(int i=0;i<k-1;i++)
		if(temp<count[i])
		{
			temp=count[i];
			nr_graph=i+1;
		}
	BasicGraphRepr graph_copy = new BasicGraphRepr();
	for(int i=0;i<tmp.length;i++)
		if(tmp[i]==nr_graph)
			graph_copy.add_vertex(i);
	for(int i=0;i<temp;i++)
		for(int j=i+1;j<temp;j++)
			if(does_the_edge_is(i,j))
			{
				Edge e = new Edge(i,j);
				graph_copy.add_edge(e);	
			}
	return graph_copy;
    }
    
    
    void DFS(int[] tmp,int i,int k)
    {
	tmp[i]=k;
	for(int j=0;j<tmp.length;j++)
		if(tmp[j]==0&&does_the_edge_is(i,j))
			this.DFS(tmp,j,k);
    }

    BasicGraphRepr eulerian_graph()
    {
	BasicGraphRepr eulerian_copy = new BasicGraphRepr(this);
	int[] deg = new int[get_amount_of_vertices()];
	for(int i=0;i<deg.length;i++)
		for(int j=0;j<get_amount_of_edges();j++)
		{
			if((get_edge(j).get_v(0)==i)^(get_edge(j).get_v(1)==i))
				deg[i]+=1;
			if((get_edge(j).get_v(0)==i)&&(get_edge(j).get_v(1)==i))
				deg[i]+=2;
		}
	for(int i=0;i<deg.length-1;i++)
		if(deg[i]%2==1)
		{
			if(eulerian_copy.does_the_edge_is(i,i+1))
			{
				eulerian_copy.del_edge(i,i+1);
				deg[i+1]-=1;
				deg[i]-=1;
			}
			else
			{
				eulerian_copy.add_edge(new Edge(i,i+1));
				deg[i+1]+=1;
				deg[i]+=1;
			}
		}
	/*
	for(int i=0;i<deg.length;i++)
			System.out.println(deg[i]);*/
	eulerian_copy = eulerian_copy.BiggestConnectedComponent();
	return eulerian_copy;
    }

    
    public ArrayList<Integer> euler_cycle(int k)
    {
	ArrayList<Integer> vertices = new ArrayList<Integer>();
	BasicGraphRepr temp = new BasicGraphRepr(this);
	int[] deg = new int[get_amount_of_vertices()];
	for(int i=0;i<deg.length;i++)
		for(int j=0;j<get_amount_of_edges();j++)
		{
			if((get_edge(j).get_v(0)==i)^(get_edge(j).get_v(1)==i))
				deg[i]+=1;
			if((get_edge(j).get_v(0)==i)&&(get_edge(j).get_v(1)==i))
				deg[i]+=2;
		}
	vertices.add(k+1);
	temp.eulerian_DFS(vertices,deg,k);
	return vertices;
    }

    public void eulerian_DFS(ArrayList<Integer> vertices,int[] deg,int k)
    {
	for(int i=0;i<get_amount_of_vertices()&&get_amount_of_edges()!=0;i++)
		if(deg[i]>1&&does_the_edge_is(i,k))
		{
			del_edge(i,k);
			deg[i]-=1;
			deg[k]-=1;
			vertices.add(i+1);
			eulerian_DFS(vertices,deg,i);
		}
	for(int i=0;i<get_amount_of_vertices()&&get_amount_of_edges()!=0;i++)
		if(does_the_edge_is(i,k))
		{
			del_edge(i,k);
			deg[i]-=1;
			deg[k]-=1;
			vertices.add(i+1);
			eulerian_DFS(vertices,deg,i);
		}
    }
}
