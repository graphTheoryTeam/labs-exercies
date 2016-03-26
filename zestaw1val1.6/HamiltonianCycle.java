/* Java program for solution of Hamiltonian Cycle problem
   using backtracking */
class HamiltonianCycle
{
    int path[];
 
    /** A utility function to check if the vertex v can be
       added at index 'pos'in the Hamiltonian Cycle
       constructed so far (stored in 'path[]') */
    boolean validVertex(int v, int graph[][], int path[], int pos)
    {
        /** Check if this vertex is an adjacent vertex of
           the previously added vertex. */
        if (graph[path[pos - 1]][v] == 0)
            return false;
 
        /** Check if the vertex has already been included. */
        /**   This step can be optimized by creating an array
           of size V */
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;
 
        return true;
    }
 
    /** A recursive  function to solve hamiltonian
       cycle problem */
    boolean hamCycleRecursiveFinder(int graph[][], int path[], int pos, int V)
    {
        /** fundamental case: If all vertices are included in
           Hamiltonian Cycle */
        if (pos == V)
        {
            /** And if there is an edge from the last included
             vertex to the first vertex */

            if (graph[path[pos - 1]][path[0]] == 1)
                return true;
            else
                return false;
        }
 
        /** Try different vertices as a next candidate in
         Hamiltonian Cycle.
       /**  We don't try for 0 as we
         included 0 as starting point in in hamCycle() */
        for (int v = 1; v < V; v++)
        {
            /** Check if this vertex can be added to Hamiltonian
               Cycle */
            if (validVertex(v, graph, path, pos))
            {
                path[pos] = v;
 
                /** recur to construct rest of the path */
                if (hamCycleRecursiveFinder(graph, path, pos + 1, V) == true)
                    return true;
 
                /* If adding vertex v doesn't lead to a solution,
                   then remove it */
                path[pos] = -1;
            }
        }
 
        /** If no vertex can be added to Hamiltonian Cycle
           constructed so far, then return false */
        return false;
    }
 
    /* This function solves the Hamiltonian Cycle problem using
       Backtracking. It mainly uses hamCycleRecursiveFinder() to solve the
       problem. It returns false if there is no Hamiltonian Cycle
       possible, otherwise return true and prints the path.
       This function prints one of the feasible solutions. */
    int hamCycle(int graph[][], int size)
    {
        path = new int[size];
        final int V = size;
        for (int i = 0; i < V; i++)
            path[i] = -1;
 
        /* Let us put vertex 0 as the first vertex in the path.
           If there is a Hamiltonian Cycle, then the path can be
           started from any point of the cycle as the graph is
           undirected */
        path[0] = 0;
        if (hamCycleRecursiveFinder(graph, path, 1, V) == false)
        {
            System.out.println("\nIt is not hamiltionian graph!");
            return 0;
        }
 
        printSolution(path, V);
        return 1;
    }
 
    /* A utility function to print solution */
    void printSolution(int path[], int V)
    {
        System.out.println("It is hamiltionian graph!\nFollowing" +
                           " is one Hamiltonian Cycle");
        for (int i = 0; i < V; i++)
            System.out.print(" " + path[i] + " ");
 
        // Let us print the first vertex again to show the
        // complete cycle
        System.out.println(" " + path[0] + " ");
    }
} 
