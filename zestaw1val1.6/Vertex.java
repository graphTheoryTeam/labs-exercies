import java.util.*;
public class Vertex{

    private int vertex_index;
    private int vertex_max_degree;
    private int vertex_cur_degree; // current degree

    public Vertex(int index, int degree){
        vertex_index = index;
        vertex_max_degree = degree;
        vertex_cur_degree = degree;
    }
    public void set_max_degree(int degree)throws IllegalArgumentException{
    	if (vertex_max_degree < 0)
            	throw new IllegalArgumentException("Tylko liczby naturalne!");
        vertex_max_degree = degree;
    }
    public int get_max_degree(){
        return vertex_max_degree;
    }
    public int get_cur_degree(){
        return vertex_cur_degree;
    }
    public void decrement_cur_degree() throws IllegalArgumentException{
        if (vertex_cur_degree  < 0)
            throw new IllegalArgumentException("Ten wiezcholek nie moze juz utworzyc krawedzi!");
        vertex_cur_degree--;
    }
    public int get_index(){
        return vertex_index;
    }
    public void set_index(int index){
        vertex_index = index;
    }
}
