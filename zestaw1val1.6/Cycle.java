import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
class Cycle implements Serializable
{
    private BasicGraphRepr _graph;
    private ArrayList<Edge> _cycle;
    private double global_weight;

    public Cycle()
    {
        _graph = new BasicGraphRepr();
        _cycle = new ArrayList<Edge>();
        global_weight = 0;
    }
    public Cycle(Cycle c)
    {
        this._graph = c._graph;
        this._cycle= c._cycle;
        this.global_weight = c.global_weight;
    }
    public Cycle deepClone()
    {
         try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Cycle) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
    
    }

    public BasicGraphRepr get_graph(){ return _graph; };

    public ArrayList<Edge> get_cycle(){ return _cycle; };

    public double calculate_global_weight()
    {
        global_weight = 0;
        for ( Edge edge: _cycle )
            global_weight += edge.get_weight();
        return global_weight;
    }
    public void print_cycle()
    {
        for (Edge edge: _cycle)
            edge.print_edge();
    }
    public void readDataFromFile() throws IOException
    {
        String path = "coordinates.txt";
        String particular_line = "";
        FileReader file = new FileReader( path );
        BufferedReader buffer = new BufferedReader( file );
        Pattern whitespace = Pattern.compile("[ \t\n\f\r]");
        // reading from file index , x , y coordinates of vertices
        while ( ( particular_line = buffer.readLine() ) != null )
        {
            String copy[];
            copy = particular_line.split(whitespace.pattern());
            int index = Integer.parseInt(copy[0]);
            int x = Integer.parseInt(copy[1]);
            int y = Integer.parseInt(copy[2]);
            
            Vertex v = new Vertex( index, x, y );
            _graph.add_vertex(v);
            int size = _graph.get_amount_of_object_vertices();

            for (int i = 0; i < size - 1; i++)
            {
                if (size >= 2)
                {
                    _graph.add_edge(new Edge(_graph.get_object_vertex(i), _graph.get_object_vertex(size - 1) ) );
                }
            }
        }
        file.close();
    }
    public void create_simple_path()
    {
        int size = _graph.get_amount_of_object_vertices();
        // creating simple path including all vertices
        for (int i = 0; i < size - 1; i++)
        {
            _cycle.add(new Edge( _graph.get_object_vertex(i), _graph.get_object_vertex(i + 1) ) );
            this.calculate_global_weight();
        }
        // adding connection from last vertex to first
        _cycle.add(new Edge( _graph.get_object_vertex(size - 1), _graph.get_object_vertex( 0 ) ) );
        this.calculate_global_weight();
    }
}

