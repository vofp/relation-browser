/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package relationbrowser2;
import processing.core.*;
/**
 *
 * @author francis
 */
public class Frame extends PApplet {
    PFont fontA; //font used
    Circle[] Circles; // list of all circles
    Project currentProject = Project.current; //project used
    int baseColor; // color of the background
    boolean pressed = false;
    int currentX;
    int currentY;
    Circle pressedCircle = new Circle();
    public void setup() {
        size(500, 500);
        smooth();
        baseColor = color(200);
        Circles = new Circle[Node.nodeList.size()];
        for (int i = 0; i < Node.nodeList.size(); i++) {
            Circles[i] = new Circle(Node.nodeList.get(i));
            Node.nodeList.get(i).setCircle(Circles[i]);
        }
        ellipseMode(CENTER);
        fontA = loadFont("CourierNew36.vlw");
        textAlign(CENTER);
        textFont(fontA, 12);

    }
    public void draw() {
        update(mouseX, mouseY);
        background(baseColor);
        for (int i = 0; i < Relation.relationList.size(); i++) {
            Relation related = Relation.relationList.get(i);
            Circle circle1 = related.node1.getCircle();
            Circle circle2 = related.node2.getCircle();
            if(circle1.enable && circle2.enable){
                line(circle1.X,circle1.Y ,circle2.X ,circle2.Y );
            }
        }
        for (int i = 0; i < Circles.length; i++) {
            Circles[i].draw();
        }
        
    }
    public void update(int x, int y) {
        for (int i = 0; i < Circles.length; i++) {
            Circles[i].over();
        }
        Press();
    }
    public void Press(){
        if(pressed){
            pressedCircle.X += mouseX - currentX;
            pressedCircle.Y += mouseY - currentY;
            currentX = mouseX;
            currentY = mouseY;
        }
    }
    public void mousePressed() {
        pressed = true;
        for (int i = 0; i < Circles.length; i++) {
            if(Circles[i].over()){
                pressedCircle = Circles[i];
                currentX = mouseX;
                currentY = mouseY;
            }
        }
        Press();
    }
    public void mouseReleased(){
        pressed = false;
        pressedCircle.Color = color(255);
        pressedCircle = new Circle();
    }
    public class Circle {
        int Size = 100;
        int Color = 255;
        private float X;
        private float Y;
        private boolean Over = false;
        private Node node;
        boolean enable = true;
        Circle() {

        }
        Circle(Node n) {
            node = n;
        }
        public float X(){
            return X;
        }
        public float Y(){
            return Y;
        }
        public void X(float x){
            X = x;
        }
        public void Y(float y){
            Y = y;
        }
        public void draw() {
            if(!enable){
                return;
            }
            fill(Color);
            ellipse(X, Y, Size, Size);
            fill(0);
            text(node.getName(),X,Y);
        }
        public boolean over() {
            float disX = X() - mouseX;
            float disY = Y() - mouseY;
            if (sqrt(sq(disX) + sq(disY)) < Size / 2) {
                Over = true;
            } else {
                Over = false;
            }
            return Over;
        }
    }
}
