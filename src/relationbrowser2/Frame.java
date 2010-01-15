/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package relationbrowser2;
import java.util.Set;
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
    boolean pressedC;
    int distance = 200;
    int movement = 20;
    Circle centerCircle = null;
    //Set<Circle> enabledCircle = new Set<Circle>();
    float centerX = width / 2;
    float centerY = height / 2;
    Circle move_b;
    public void test(){
        Circle c1 = new Circle();
        Circle c2 = new Circle();
        c1.X = 0;
        c1.Y = 0;
        c2.X = 3;
        c2.Y = 3;
        System.out.println("Angle:");
        System.out.println(angle(c1,c2));
    }
    public void setup() {
        size(1000, 600);
        smooth();
        ellipseMode(CENTER);
        fontA = loadFont("CourierNew36.vlw");
        textAlign(CENTER);
        textFont(fontA, 12);
        baseColor = color(200);
        move_b = new Circle();
        move_b.Size = 10;
        move_b.X = 20;
        move_b.Y = 20;
        text("move",10,10);
        Circles = new Circle[Node.nodeList.size()];
        for (int i = 0; i < Node.nodeList.size(); i++) {
            Circles[i] = new Circle(Node.getNode(i));
            Circles[i].score = Node.getNode(i).numRelations();
            Node.getNode(i).setCircle(Circles[i]);
        }
        centerX = width / 2;
        centerY = height / 2;
        Node.getNode(0).getCircle().setPoint(centerX+100,centerY+100);
        Node.getNode(1).getCircle().setPoint(centerX,centerY+200);
        Node.getNode(2).getCircle().setPoint(centerX,centerY);
        Node.getNode(3).getCircle().setPoint(centerX+100,centerY-100);
        Node.getNode(4).getCircle().setPoint(centerX,centerY-200);
        Node.getNode(5).getCircle().setPoint(centerX-100,centerY-100);
        Node.getNode(6).getCircle().setPoint(centerX-100,centerY+100);

        /*
        for (int i = 0; i < Node.nodeList.size(); i++) {
            Node.getNode(i).getCircle().setupMovement(Node.getNode(i).getCircle().X-100, Node.getNode(i).getCircle().Y);
        }
         */
    }
    public void setCenterCircle(Circle nextCenter){
        if(nextCenter != centerCircle){
            if(nextCenter.enable){
                float angle = atan((nextCenter.Y-centerY)/(nextCenter.X-centerX));
                
            }
        }
    }
    public void draw() {
        update(mouseX, mouseY);
        background(baseColor);
        move_b.draw();
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
            if(true){
                
            }
            pressedCircle.X += mouseX - currentX;
            pressedCircle.Y += mouseY - currentY;
            currentX = mouseX;
            currentY = mouseY;
        }
    }
    public void mousePressed() {

        //pressed = true;
        for (int i = 0; i < Circles.length; i++) {
            if(Circles[i].over()){
                pressedC = true;
                pressedCircle = Circles[i];
                currentX = mouseX;
                currentY = mouseY;
            }
        }
        if(pressedC){
            float x = pressedCircle.X-centerX;
            float y = pressedCircle.Y-centerY;
            for (int i = 0; i < Node.nodeList.size(); i++) {
                Node.getNode(i).getCircle().setupMovement(Node.getNode(i).getCircle().X-x, Node.getNode(i).getCircle().Y-y);
            }
            pressedC = false;
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
        float X;
        float Y;
        private boolean Over = false;
        private Node node;
        boolean enable = true;
        int score;
        float movementX;
        float movementY;
        boolean move = false;
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
            movement();
            fill(Color);
            ellipse(X, Y, Size, Size);
            fill(0);
            if(node != null){
                text(node.getName(),X,Y);
            }
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
        public void setupMovement(float x, float y){
            movementX = x;
            movementY = y;
            move = true;
        }
        public void movement(){
            if(move){
                float h = distance(X,Y,movementX,movementY);
                if(h-movement>0){
                    float scale = (h-movement) /h;
                    X = (scale * (X - movementX) + movementX);
                    Y = (scale * (Y - movementY) + movementY);
                }else{
                    X = movementX;
                    Y = movementY;
                    move = false;
                }
                
            }
        }
        public void setPoint(float x,float y){
            X = x;
            Y = y;
        }
    }
        
    
    public float distance(Circle circle1, Circle circle2){
        float X_d = circle1.X - circle2.X;
        float Y_d = circle1.Y - circle2.Y;
        return distance(circle1.X,circle1.Y, circle2.X,circle2.Y);
    }
    public float distance(float X1,float Y1, float X2,float Y2){
        float X_d = X1 - X2;
        float Y_d = Y1 - Y2;
        return (float) Math.sqrt(Math.pow(X_d,2)+Math.pow(Y_d,2));
    }
    public float angle(Circle circle1, Circle circle2){
        float X_d = circle1.X - circle2.X;
        float angle = 0;
        if (X_d < 0){
            //angle = (float) Math.PI;
        }
        angle += Math.acos(distance(circle1,circle2)/(2*distance));
        return angle;
    }
}
