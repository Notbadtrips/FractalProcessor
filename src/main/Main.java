/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import control.Control;
import model.Model;
import view.View;

/**
 *
 * @author Marta
 */
public class Main {

    private Model mod;    
    private View vis;   
    private Control con;  

    public Main() {
        mod = new Model();
        con = new Control(this, mod);
        vis = new View("Backtracking on Repetitive Patterns", mod, con);
    }

    public View getView() {
        return vis;
    }

    public static void main(String[] args) {
        new Main();

    }

}
