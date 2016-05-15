import java.lang.*;
import java.util.*;
import java.io.*;
class SimulatedAnnealing{
    private Cycle _current_cycle;
    private double _current_energy;
    private double _neighbour_energy;
    public double _lowest_energy;
    private double _temperature;
    private double _coolingRate;
    
    public void writeSolutionToFile() throws IOException
    {
        PrintWriter writer = new PrintWriter("solution_cycle.txt");
        
        for (Edge edge: _current_cycle.get_cycle())
        {
            int v1 = edge.get_vertex(0).get_index();
            int v2 = edge.get_vertex(1).get_index();
            writer.println(v1 + " " + v2);
        }
        writer.close();
    
    }
    public Cycle get_current_cycle()
    {
        return _current_cycle; 
    }

    public SimulatedAnnealing(Cycle initialCycle, double temp, double coolingRate)
    {
        _current_cycle = initialCycle;
        _current_energy = _lowest_energy = _current_cycle.calculate_global_weight();
        _neighbour_energy = 0;
        _temperature = temp;
        _coolingRate = coolingRate;
    }

    public double acceptanceProbability(double energy, double newEnergy, double temperature)
    {
    // If the new solution is better return 1;
        if (newEnergy < energy)
            return 1.0;
        return Math.exp( (energy - newEnergy) / temperature );
    }
    public void initial_information()
    {
        System.out.println("\n-------------------------------------------");
        System.out.println("Initial system energy: " + _lowest_energy );
        System.out.println("-------------------------------------------");
        System.out.println("Initial temperature: " + _temperature);
        System.out.println("-------------------------------------------");
        System.out.println("Cooling rate: " + _coolingRate);
        System.out.println("-------------------------------------------");


        System.out.println("\nStarting Simulated Annealing...\n");
        System.out.println("Please wait for solution.\n");
        System.out.println("Procesing...");
    }
    public double  get_solution()
    {
        this.initial_information();
        while (_temperature > 1)
        {
            Cycle neighbourCycle = _current_cycle.deepClone();
            // Swap
            int edge_index_1 = (int) ( neighbourCycle.get_cycle().size()  * Math.random() );
            int edge_index_2 = (int) ( neighbourCycle.get_cycle().size()  * Math.random() );

            if (Math.abs(edge_index_1 - edge_index_2) < 2) continue;
            int lower_index, higher_index;
            if (edge_index_1 > edge_index_2)
            {
                lower_index = edge_index_2;
                higher_index = edge_index_1;
            }
            else
            {
                lower_index = edge_index_1;
                higher_index = edge_index_2;
            }
            ArrayList<Edge> new_path = new ArrayList<Edge>();

            Edge edge_1 = neighbourCycle.get_cycle().get(lower_index);
            Edge edge_2 = neighbourCycle.get_cycle().get(higher_index);

            // 2 -opt operation
            Edge new_edge_1 = new Edge(edge_1.get_vertex(0), edge_2.get_vertex(0));

            Edge new_edge_2 = new Edge(edge_1.get_vertex(1), edge_2.get_vertex(1));

            neighbourCycle.get_cycle().set(lower_index, new_edge_1);
            neighbourCycle.get_cycle().set(higher_index, new_edge_2);
            
            // creating path
            for (int i = lower_index + 1; i < higher_index; i++)
            {
                Edge edge = neighbourCycle.get_cycle().get(i);
                edge.set_vertecies(edge.get_vertex(1), edge.get_vertex(0));
                new_path.add(edge);

            }
            // reverse new_path
            Collections.reverse(new_path);
            // swap to new path
            int j = 0;
            for (int i = lower_index + 1; i < higher_index;  i++)
            {
                neighbourCycle.get_cycle().set(i, new_path.get(j));
                j++;
            }
            //----------------------
            _neighbour_energy = neighbourCycle.calculate_global_weight();
            _current_energy = _current_cycle.calculate_global_weight();

            if (acceptanceProbability(_current_energy, _neighbour_energy, _temperature ) > Math.random() ){
                _current_cycle = neighbourCycle;
            }
            if (_current_cycle.calculate_global_weight() < _lowest_energy ){
                _lowest_energy = _current_cycle.calculate_global_weight();
                System.out.println("current system energy: " + this._lowest_energy
                                    + "\tTemperature: " + this._temperature );
            }
            // cooling harmonogram
            _temperature *= 1 - _coolingRate;
        }
        return _lowest_energy;
    }
}

