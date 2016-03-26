import java.util.*;

//Pair represents a vertex, contains its integer degree and its integer id
class Pair{
    int deg;
    int id;
    public Pair(int _deg, int _id){
        deg=_deg;
        id=_id;
    }
}

public class GraphicSequence{
 
private static int c[];

private static void sort(int c[], int n) {
int j,k;
int flag;
 
flag=1;
while (flag==1) {
flag=0;
for (j=0; j<n-1; j++)
if (c[j]<c[j+1]) {
k=c[j];
c[j]=c[j+1];
c[j+1]=k;
flag=1;
}
}
}

private static void pairSort(Pair p[],int n){
    Pair swap;
    for (int i = 0 ;i < (n-1) ; i ++){
        for (int j =0 ; j < (n-i-1); j ++){
            if((p[j].deg) < (p[j+1].deg)){
                swap = p[j];
                p[j] = p[j+1];
                p[j+1] = swap;           
            }
        }
    }   
}

//Checking if sequence is graphic and constructing graph if so
BasicGraphRepr checkSequenceAndConstruct(){
    int graficzny;
    int n,i,s; 
    Scanner input = new Scanner(System.in);
    System.out.println("Sprawdzanie czy sekwencja jest graficzna i konstruowanie grafu z tej sekwencji");
    System.out.println("Podaj liczbe wierzcholkow grafu");
    n = input.nextInt();
    c = new int[n];
    for (i=0; i<n; i++) {
        System.out.println("Podaj stopien wierzcholka " + (i+1));
        c[i] = input.nextInt();
    }
    int[] saved = new int[n];
    for (i=0; i<n; i++) {
        saved[i] = c[i];
    }
    
    graficzny=1;
 
    //
    s=0;
    for (i=0; i<n; i++)
        s+=c[i];
        if ((s%2)!=0)
            graficzny=0;
        else
            sort(c,n); 
    while ((c[0]!=0) && (graficzny==1)) {
        s=0;
            for (i=1; i<n; i++)
                if (c[i]!=0)
                    s++;
                if (c[0]>s)
                   graficzny=0;
                else
            for (i=1; i<=c[0]; i++)
                c[i]--;
            c[0]=0;
            sort(c,n);
}
 

if (graficzny != 1){
    System.out.println("Given sequence is not graphic");
    return null;
}
else{
    System.out.println("Given sequence is graphic");
}


//
//
//Constructing Graph
//
//

sort(saved,n);
Pair[] pairTab = new Pair[n];
for(i = 0 ; i < n ; i++){
    pairTab[i] = new Pair(saved[i],i); //degree,ID 
}

int[][] MatrixOfAdjacency= new int[n][n];
for(i=0 ;i <n ; i++){
    for(int j =0 ; j <n ; j++){
        MatrixOfAdjacency[i][j] = 0;
    }
}
int highDeg;

pairSort(pairTab, n);

while(pairTab[0].deg > 0){
    highDeg = pairTab[0].deg;
    for(i = 1 ; i<highDeg+1 ; i++){
        MatrixOfAdjacency[pairTab[0].id][pairTab[i].id] = 1;
        MatrixOfAdjacency[pairTab[i].id][pairTab[0].id] = 1;
        pairTab[i].deg -= 1;
    }
pairTab[0].deg = 0;
pairSort(pairTab, n);
}
        //Adding vertices to BasicGraphRepr
        BasicGraphRepr g1 = new BasicGraphRepr();
        for (i = 0; i < n; i ++){
            g1.add_vertex(i);
        }
        //Adding edges from matrix of adjacency to BasicGraphRepr
        for (i = 0 ; i < n ; i ++){
            for (int j = i ; j < n ; j ++){
                if(MatrixOfAdjacency[i][j]==1){
                    g1.add_edge(new Edge(i, j));
                }
            }
        }
		//Returning BasicGraphRepr object
		return g1;
}

}
