import java.util.*;
import java.io.*;
class Main2{
    public static void main(String args[]){
		
        RandomGraph graphExample_Regular = new RandomGraph();
        Scanner input = new Scanner(System.in);
        System.out.println("Graf G(n,d)");
        System.out.println("Podaj liczbe wierzcholkow grafu: ");
        int n = input.nextInt();
        System.out.println("Podaj stopien kazdego wierzcholka: ");
        int l = input.nextInt();
        
		BasicGraphRepr g2 = graphExample_Regular.generateRandomRegularGraph(n,l);
        System.out.println("BasicGraphRepr for G(n,d): ");

        AdjadencyMatrixRepr am = new AdjadencyMatrixRepr(g2);

        System.out.println();
        HamiltonianCycle ham = new HamiltonianCycle();
        // if it is hamiltionian graph it will be printed  
        if (ham.hamCycle(am.get_adj_mtrx(), am.get_vertices_number())){
           g2.setHamCycle(ham.returnSolution());
           try{
                g2.write_to_file_and_print_with_cycle();
              }
            catch(IOException e)
            {
                 System.out.print("Cos... cos sie zepsulo :( "); 
            }
        }
        else
        {
            try{
                g2.write_to_file_and_print();
              }
            catch(IOException e)
            {
                 System.out.print("Cos... cos sie zepsulo :( "); 
            }
        }
        System.out.println();
       		
        

        g2.print_vertices();

        g2.print_edges();

        /*
        am.print_adjadency_matrix();
        System.out.println();

        am.transform_to_incidence_matrix();
        System.out.println();

        am.transform_to_adjadency_list();
        System.out.println();

        AdjadencyListRepr al = new AdjadencyListRepr(am);

	    al.print_adjadency_list();
        System.out.println();

        al.transform_to_incidence_matrix();
        System.out.println();

        al.transform_to_adjadency_matrix();
        System.out.println();

        IncidenceMatrixRepr im = new IncidenceMatrixRepr(g2);

        im.print_incidence_matrix();
        System.out.println();

        im.transform_to_adjadency_list();
        System.out.println();

        im.transform_to_adjadency_matrix();
        System.out.println();
	*/	
		//  \/  Ciagi graficzne i konstruowanie grafu z ciagu  \/
		
		GraphicSequence gs = new GraphicSequence();			
		BasicGraphRepr g3 = gs.checkSequenceAndConstruct();
        /*
		if(g3 != null){
			g3.print_vertices();
			g3.print_edges();
		}
        */
        AdjadencyMatrixRepr am2 = new AdjadencyMatrixRepr(g3);

        ham.hamCycle(am2.get_adj_mtrx(), am2.get_vertices_number());
        System.out.println();

        
    }
}
