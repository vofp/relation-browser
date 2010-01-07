/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relationbrowser2;

/**
 *
 * @author francis
 */
class oneWayRelation extends Relation {
    public oneWayRelation(Node n1, Node n2) {
        n1.setRelation(this);
        node1 = n1;
        node2 = n2;
        relationList.add(this);
    }
}
