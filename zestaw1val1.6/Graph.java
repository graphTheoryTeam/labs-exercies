import java.util.*;
import java.io.*;
abstract class Graph{
    protected BasicGraphRepr _b_g_r;
    private int _vertices_number;
    private int _edges_number;
    
    public Graph(IncidenceMatrixRepr i_m_r){
       _b_g_r = new BasicGraphRepr(i_m_r.get_b_g_r());
       _vertices_number = _b_g_r.get_amount_of_vertices();
       _edges_number = _b_g_r.get_amount_of_edges();
    }
    public Graph(AdjadencyListRepr a_l_r){
       _b_g_r = new BasicGraphRepr(a_l_r.get_b_g_r());
       _vertices_number = _b_g_r.get_amount_of_vertices();
       _edges_number = _b_g_r.get_amount_of_edges();
    }
    public Graph(AdjadencyMatrixRepr a_m_r){
       _b_g_r = new BasicGraphRepr(a_m_r.get_b_g_r());
       _vertices_number = _b_g_r.get_amount_of_vertices();
       _edges_number = _b_g_r.get_amount_of_edges();
    }
  
    public Graph(BasicGraphRepr b_g_r){
       _b_g_r = b_g_r;
       _vertices_number = _b_g_r.get_amount_of_vertices();
       _edges_number = _b_g_r.get_amount_of_edges();
    }
    public BasicGraphRepr get_b_g_r(){
        return _b_g_r;
    }
    public int get_vertices_number(){
        return _vertices_number;
    }
    public int get_edges_number(){
        return _edges_number;
    }
    public void write_to_file_and_print_with_cycle() throws IOException{
        PrintWriter writer = new PrintWriter("print.txt");
        for (int v : _b_g_r.get_hamCycle())
            writer.println(v);
        writer.println("#");

        for (int v : _b_g_r.get_list_of_vertices())
            writer.println(v);
        writer.println("#");

        for (Edge e : _b_g_r.get_list_of_edges()){
            writer.println(e.get_v(0));
            writer.println(e.get_v(1));
        }
        writer.println("#");
        writer.close();
        ProcessBuilder pb = new ProcessBuilder("python","printing_cycle.py");
        Process p = pb.start();
    }
    public void write_to_file_and_print() throws IOException{
        PrintWriter writer = new PrintWriter("print.txt");
        for (int v : _b_g_r.get_list_of_vertices())
            writer.println(v);
        writer.println("#");

        for (Edge e : _b_g_r.get_list_of_edges()){
            writer.println(e.get_v(0));
            writer.println(e.get_v(1));
        }
        writer.println("#");
        writer.close();
        ProcessBuilder pb = new ProcessBuilder("python","printing.py");
        Process p = pb.start();
    }

}
