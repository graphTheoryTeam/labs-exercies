import java.util.*;
class Main{
public static void main(String args[]){
RandomGraph graphExample_Gnl = new RandomGraph();
        Scanner input = new Scanner(System.in);
        System.out.println("Graf G(n,l)");
        System.out.println("Podaj liczbe wierzcholkow grafu: ");
        int n = input.nextInt();
        System.out.println("Podaj liczbe krawedzi grafu: ");
        int l = input.nextInt();
        
		BasicGraphRepr g1 = graphExample_Gnl.generateRandomGnlGraph(n,l);

        System.out.println("BasicGraphRepr for G(n,l): ");

        g1.print_vertices();

        g1.print_edges();

        AdjadencyMatrixRepr am = new AdjadencyMatrixRepr(g1);

        am.print_adjadency_matrix();
        System.out.println();

        am.transform_to_incidence_matrix();
        System.out.println();

        am.transform_to_adjadency_list();
        System.out.println();

        AdjadencyListRepr al = new AdjadencyListRepr(am);
	al.print_adjadency_list();
	al.get_b_g_r().add_vertex(5);
        al.get_b_g_r().print_vertices();
        System.out.println();
        am.get_b_g_r().print_vertices();

        al.transform_to_incidence_matrix();
        System.out.println();

        al.transform_to_adjadency_matrix();
        System.out.println();

        IncidenceMatrixRepr im = new IncidenceMatrixRepr(g1);

        im.print_incidence_matrix();
        System.out.println();

        im.transform_to_adjadency_list();
        System.out.println();

        im.transform_to_adjadency_matrix();
        System.out.println();

        //
        //
        //
        //

        RandomGraph graphExample_Gnp = new RandomGraph();
        System.out.println("Graf G(n,p)");
        System.out.println("Podaj liczbe wierzcholkow grafu: ");
        int n_2 = input.nextInt();
        System.out.println("Podaj prawdopodobienstwo [0,1]: ");
        double p = input.nextDouble();

        BasicGraphRepr g2 = graphExample_Gnp.generateRandomGnpGraph(n_2,p); 

        System.out.println("BasicGraphRepr for G(n,p): ");

        g2.print_vertices();
        g2.print_edges();

        am = new AdjadencyMatrixRepr(g2);
        am.print_adjadency_matrix();

        al = new AdjadencyListRepr(g2);
        al.print_adjadency_list();

        im = new IncidenceMatrixRepr(g2);
        im.print_incidence_matrix();

    }
}
