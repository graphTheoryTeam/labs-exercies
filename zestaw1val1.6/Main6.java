import java.lang.*;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
class Main6
{
    public static void main(String args[])throws IOException
    {
        Cycle cycle = new Cycle();
        cycle.readDataFromFile();
        cycle.create_simple_path();

        ProcessBuilder pb1 = new ProcessBuilder("python","plotin.py");
        Process p1 = pb1.start();

        //cycle.print_cycle();
        SimulatedAnnealing tsp = new SimulatedAnnealing(cycle, 100, 0.0001 );
        System.out.println("Final Solution: " + tsp.get_solution());
        //tsp.get_current_cycle().print_cycle();
        tsp.writeSolutionToFile();

        ProcessBuilder pb2 = new ProcessBuilder("python","ploting_solution.py");
        Process p2 = pb2.start();
    }
}
