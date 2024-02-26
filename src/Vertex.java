class Vertex{
    /*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
    public int label;
    public  String dijPrev="";
    public boolean wasVisited;
    public Vertex(int label) // constructor
    {
        this.label = label;
        this.wasVisited = false;

    }
}