/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author WINDOWS10
 */
public class node {
    char data;
    node left;
    node right;
    node parent;

    node() {        
    }
    
    node(char key) {
        this.data = key;
    }
}
