/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package projects.gui.yellow_team.activities;

/**

 @author EMAM
 */
public class Stack {

          private final String[] stack;
          private int pointer;


          public Stack(int i) {
                    stack = new String[i];
                    pointer = -1;
          }


          public boolean isEmpty() {
                    return pointer == -1;
          }


          public String peek() {
                    if ( isEmpty() ) {
                              return null;
                    }
                    return this.stack[pointer];
          }


          public String pop() {
                    if ( isEmpty() ) {
                              return null;
                    }
                    String data = this.stack[pointer];
                    pointer--;
                    return data;
          }


          private void shift() {
                    for ( int i = 1 ; i < this.stack.length ; i++ ) {
                              this.stack[i - 1] = this.stack[i];
                    }
          }


          public void push(String id) {
                    if ( pointer == this.stack.length - 1 ) {
                              shift();
                              this.stack[pointer] = id;
                    } else {
                              this.stack[++pointer] = id;
                    }
          }

}
