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
    boolean inaction = false; // flag for movement
    Circle centerCircle = new Circle(); //ref to center circle
    Circle next; // next center circle
    Circle[] Circles; // list of all circles
    Circle[] circleUsed; // list of circles around center circle
    float angles; // angle bewteen circles around the center circle
    float centerX; //
    float centerY;
    int currentColor, baseColor; // color of the background
    int numCircle; //number of circles - same as circleUsed.length
    int maxH = 150; // max distance from center circle
    int speed = 20; // speed of movement
    int H = maxH; // current distance from center circle
    PFont fontA; //font used
    Project currentProject = Project.current; //project used
    public Frame(){
        //PApplet.main(new String[]{"--bgcolor=#DFDFDF", "relationbrowser.Frame"});
    }

    public void setup() {
        size(500, 500); // set window size
        smooth();
        centerX = width / 2;
        centerY = height / 2;
        baseColor = color(200);
        currentColor = baseColor;
        Circles = new Circle[Node.nodeList.size()];
        for (int i = 0; i < Node.nodeList.size(); i++) {
            Circles[i] = new Circle(Node.nodeList.get(i));
            Node.nodeList.get(i).setCircle(Circles[i]);
        }
        ellipseMode(CENTER);
        setCenterCircle(currentProject.centerNode().getCircle());
        fontA = loadFont("CourierNew36.vlw");
        textAlign(CENTER);
        textFont(fontA, 12);
    }
    public void setCenterCircle(Circle nextCenter){
        if(nextCenter == null){
            nextCenter = new Circle(currentProject.centerNode());
        }
        centerCircle.center = false;
        centerCircle.edit = false;
        centerCircle = nextCenter;
        centerCircle.center = true;
        currentProject.centerNode = nextCenter.node;
        centerCircle= nextCenter;
        numCircle = nextCenter.node.numRelations();
        angles = radians(360) / numCircle;
        Node[] related = nextCenter.node.getRelations();
        for (int i = 0; i < Circles.length; i++) {
            Circles[i].show = false;
        }
        circleUsed = new Circle[numCircle];
        for (int i = 0; i < numCircle; i++) {
            circleUsed[i] = related[i].getCircle();
            circleUsed[i].show = true;
            circleUsed[i].angle = angles * i;
        }
    }

    public void draw() {
        update(mouseX, mouseY);
        background(currentColor);
        stroke(255);
        if(inaction){
            H-=2*speed;
            if(H <= 0){
                inaction = false;
                setCenterCircle(next);
            }
        }
        if(H<150){
            H+=speed;
        }
        for (int i = 0; i < numCircle; i++) {
            stroke(color(0,0,255));
            line(centerCircle.X(), centerCircle.Y(), circleUsed[i].X(), circleUsed[i].Y());
            circleUsed[i].draw();
        }
        centerCircle.draw();
        stroke(0);
    }

    public void update(int x, int y) {
        centerCircle.overCircle();
        for (int i = 0; i < numCircle; i++) {
            circleUsed[i].overCircle();
        }
    }
    boolean pressed = false;
    int currentX;
    int currentY;
    public void mousePressed() {
        for (int i = 0; i < numCircle; i++) {
           if(circleUsed[i].Over){
                next = circleUsed[i];
                inaction = true;
            }
        }
    }
    class Circle {
        int Size = 100;
        int Color = 255;
        int Highlight = 100;
        boolean Over = false;
        Node node;
        float angle;
        boolean center = false;
        boolean show = false;
        boolean edit = false;
        float X;
        float Y;
        Circle() {

        }
        Circle(Node n) {
            node = n;
        }
        Circle(float an,Node n) {
            angle = an;
            node = n;
        }
        public void resetCircle(float an,Node n){
            angle = an;
            node = n;
        }
        public float X(){
            if(!edit){
                if(center){
                    X = centerX;
                }else{
                    X = cos(angle) * H + centerX;
                }
            }
            return X;
        }
        public float Y(){
            if(!edit){
                if(center){
                    Y = centerY;
                }else{
                    Y = sin(angle) * H + centerY;
                }
            }
            return Y;
        }
        public void draw() {
            fill(Color);
            ellipse(X(), Y(), Size, Size);
            fill(0);
            text(node.getName(),X(),Y());
        }

        public void overCircle() {
            float disX = X() - mouseX;
            float disY = Y() - mouseY;
            if (sqrt(sq(disX) + sq(disY)) < Size / 2) {
                Over = true;
            } else {
                Over = false;
            }
        }
    }
}
