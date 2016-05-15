import java.lang.*;
import java.io.*;

class Edge implements Serializable {
    int weight;
    int v1;
    int v2;
    // for TSP set 6
    Vertex _v1;
    Vertex _v2;
    double _weight;
    public Edge(int vertex1, int vertex2)
    {
            v1 = vertex1;
            v2 = vertex2;
    }
    // TSP
    public Edge(Vertex v1, Vertex v2)
    {
        _v1 = v1;
        _v2 = v2;
        Calculate_Weight();
    }
    // caclulate euklides distance
    public void Calculate_Weight()
    {
        int xDistance = Math.abs(_v1.getX() - _v2.getX());
        int yDistance = Math.abs(_v1.getY() - _v2.getY());
        _weight = Math.sqrt( Math.pow( xDistance, 2 ) + Math.pow( yDistance, 2 ) );
    }
    public Edge(Edge e)
    {
           v1=e.v1;
	   v2=e.v2;
    }
    public int get_v(int vertex)
    {
        if (vertex == 0){
            return v1;
        }
        else{
            return v2;
        }
    }
    // TSP
    public Vertex get_vertex(int num)
    {
        if (num == 0) return _v1;
        else return _v2;
    }
    public boolean does_the_edge_is(int i,int j)
    {
	if((v1==i&&v2==j)||(v1==j&&v2==i))
		return true;
	return false;
    }
    // TSP
    public void set_vertecies(Vertex v1, Vertex v2)
    {
        _v1 = v1;
        _v2 = v2;
    
    }
    public void set_v(int vertex1, int vertex2){
        v1 = vertex1;
        v2 = vertex2;
    }
    public void print_edge()
    {
        System.out.println(_v1.get_index() + " " + _v2.get_index()); 
    }
    public double get_weight(){ return _weight; }
}

