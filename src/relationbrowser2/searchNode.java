/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package relationbrowser2;


import java.io.*;
import java.util.ArrayList;
/*
 *
 * @author francis
 */
public class searchNode extends Node{
    static ArrayList<Node> searchNodeList = new ArrayList<Node>();
    public searchNode(String n){
        this.setName(n);
        searchNodeList.add(this);
    }
    public void displayCenter(){
        Node[] related = this.getRelated();
        System.out.println("nodes found:");
        for (int i = 0; i < related.length; i++) {
            System.out.println("   (" + i + ")" + related[i].getName());
        }

        try {
            BufferedReader bR = new BufferedReader(new InputStreamReader(System.in));
            String[] C = bR.readLine().split(" ");

            int n = Integer.parseInt(C[0]);
            related[n].displayCenter();
            bR.close();
        } catch (Exception ex) {
        }
    }
}
