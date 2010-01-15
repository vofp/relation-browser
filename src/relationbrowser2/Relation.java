/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package relationbrowser2;

import java.util.ArrayList;

/**
 *
 * @author francis
 */
public class Relation {
    // <editor-fold defaultstate="collapsed" desc="Varables"> 
    // <editor-fold defaultstate="collapsed" desc="Static Varable"> 
    static ArrayList<Relation> relationList = new ArrayList<Relation>();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Class Varables"> 
    Node node1;
    Node node2;
    // </editor-fold>  
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="def"> 
    public Relation() {
    }

    public Relation(Node n1, String name) {
        Node n2;
        if(Node.getNode(name) == null){
            n2 = new Node(name);
        }else{
            n2 = Node.getNode(name);
        }
        
        setRelation(n1, n2);
        relationList.add(this);
    }
    public Relation(Node n1, Node n2) {
        setRelation(n1, n2);
        relationList.add(this);
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Private"> 
    private void setRelation(Node n1, Node n2){
        n1.setRelation(this);
        node1 = n1;

        n2.setRelation(this);
        node2 = n2;
    }
    // </editor-fold> 
    // <editor-fold defaultstate="collapsed" desc="Getters"> 
    public Node getOtherNode(Node n) {
        if (n.equals(node1)) {
            return node2;
        }
        return node1;
    }
    static public boolean relationship(Node n1, Node n2){
        System.out.println("n1 "+n1.getName());
        System.out.println("n2 "+n2.getName());
        for (int i = 0; i < n1.numRelations(); i++) {
            System.out.println(n1.getRelation(i).getOtherNode(n1).getName());
            if(n1.getRelation(i).getOtherNode(n1) == n2){
                return true;
            }
        }
        System.out.println("No relation");
        return false;
    }
    // </editor-fold> 
}
