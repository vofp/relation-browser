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
    Circle centerCircle = new Circle(); //ref to center circle
    int currentColor, baseColor; // color of the background
    int numCircle; //number of circles - same as circleUsed.length
    float angles; // angle bewteen circles around the center circle
    Circle[] Circles; // list of all circles
    Circle[] circleUsed; // list of circles around center circle
    int maxH = 150; // max distance from center circle
    int speed = 20; // speed of movement
    int H = maxH; // current distance from center circle
    float centerX; //
    float centerY;
    Project currentProject = Project.current; //project used
    boolean inaction = false; // flag for movement
    Circle next; // next center circle
    class Circle {
        
    }
}
