/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package relationbrowser2;

import processing.core.PApplet;

/**
 *
 * @author francis
 */
public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        PApplet.main(new String[]{"--bgcolor=#DFDFDF", "relationbrowser2.Frame"});
    }
    public Main() {
        Project P = new Project();
        P.test2();
    }

}
