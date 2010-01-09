/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package relationbrowser2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francis
 */
public class Project {

    // <editor-fold defaultstate="collapsed" desc="Varables">

    // <editor-fold defaultstate="collapsed" desc="Static">
    static Project current;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Class">
    ArrayList<Node> nodeList = Node.nodeList;
    ArrayList<String> attriNames = Node.attriNames;
    Node centerNode;
    // </editor-fold>

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Def">
    public void test3(){
        load("test2.csv");
    }
    public void test2() {
        load("test.csv");
        Node sNode = search("awesome");
        centerNode = sNode;
        sNode.displayRelated();
    }
    public void test() {
        //Node.setData("description");
        new Node("Programming");//0
        new Node("Java");//1
        new Node("ruby");//2
        new Node("c++");//3
        new Node("Python");//4
        new Node("Awesome");//5
        new Node("not so Awesome");//6
        Node.getNode(1).setData("description", "Java is a programming language originally developed by James Gosling at Sun Microsystems and released in 1995 as a core component of Sun Microsystems\' Java platform.");
        Node.getNode(2).setData("description","Ruby is a dynamic, reflective, general purpose object-oriented programming language that combines syntax inspired by Perl with Smalltalk-like features.");
        Node.getNode(3).setData("description","C++ (pronounced \"See plus plus\") is a statically typed, free-form, multi-paradigm, compiled, general-purpose programming language.");
        Node.getNode(4).setData("description","Python is a general-purpose high-level programming language.");
        Node.getNode(0).setNewRelation("",Node.getNode(1));
        Node.getNode(0).setNewRelation("",Node.getNode(2));
        Node.getNode(0).setNewRelation("",Node.getNode(3));
        Node.getNode(0).setNewRelation("",Node.getNode(4));
        Node.getNode(5).setNewRelation("",Node.getNode(2));
        Node.getNode(5).setNewRelation("",Node.getNode(4));
        Node.getNode(6).setNewRelation("",Node.getNode(1));
        Node.getNode(6).setNewRelation("",Node.getNode(3));
        //Node.nodeList.get(0).displayCenter();
        search("awesome").displayRelated();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Def">
    public Project(){
        current = this;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">
    public Node centerNode(){
        if(centerNode == null){
            if(nodeList.get(0) == null){
                System.out.println("Error: No stored nodes");
            }
            centerNode = nodeList.get(0);
        }
        return centerNode;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Setters">
    // </editor-fold>

    public Node search(String keyword){
        searchNode sNode = new searchNode(keyword);
        System.out.println("Searching for the keyword:\n"+keyword);
        sNode.search(keyword);
        return sNode;
    }
    public void load(String aFile){
        try {
            BufferedReader input = new BufferedReader(new FileReader(aFile));
            String line = "";
            while(line!=null){
                Node currentNode = new Node();
                while (( line = input.readLine())!= null && ! line.isEmpty()){
                    String[] str = line.split(",",2);
                    if(str[0].equals("NAME")){
                        if(Node.getNode(str[1]) == null){
                            currentNode.setName(str[1]);
                            nodeList.add(currentNode);
                        }else{
                            currentNode = Node.getNode(str[1]);
                        }
                    }else if(str[0].equals("RELATED")){
                        new Relation(currentNode,str[1]);
                    }else{
                        currentNode.setData(str[0], str[1]);
                    }
                }
            }
            for(int i = 0; i < nodeList.size() ;i++){
                System.out.println(Node.getNode(i).getName());
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
