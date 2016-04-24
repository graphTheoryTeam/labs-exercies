# labs-exercies
class hierarchy:

             BasicGraphRepresentation - contains class Edge


	     Abstract class Graph (contains class BasicGraphRepr)
	
       /                             |                         \	
AdjadencyMatrixRepr       	          IncidenceMatrixRepr	               AdjadencyListRepr

    	\			                 |                        /
		     
		     retrun BasicGraphRepr(ready to be printed)	
		     
		    

+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_

If u wanna test e.g set 2
Start test --->>>>>> java Main2

if set 1 ->> java Main    
etc.

_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+

PRINTING GRAPHS:
use BaseGraphRepr method write_to_file_and_print()
dont forget about catching possible exception!
	try{
                g2.write_to_file_and_print_with_cycle();
              }
            catch(IOException e)
            {
                 System.out.print("Cos... cos sie zepsulo :( "); 
            }


if u want to include some cycle(hamiltonian , euleran) 
use BaseGraphRepr method write_to_file_and_print_with_cycle()

