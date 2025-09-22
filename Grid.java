import java.awt.Color;
import javax.swing.*;
public class Grid extends JButton{
    private int xcord, ycord;
    
    public Grid (int xcord, int ycord){
        super();
        this.xcord = xcord;
        this.ycord = ycord;
    }
        
    public void setColor(int decider){
        Color colour;
        if ((int)(decider / 2.0) == (decider/2.0)){
            colour = Color.pink;
        }else{
            colour = Color.white;
        }
        this.setBackground(colour);
            }
    public void switchColor(){
        Color colour;
        if (getBackground() == Color.pink){
            colour = Color.white;
        } else{
            colour = Color.pink;
        }
        this.setBackground(colour);
    }
    public void setPlayerColor(int decider){
      Color colour;
        if ((int)(decider / 2.0) == (decider/2.0)){
            colour = Color.yellow;
        }else{
            colour = Color.blue;
        }
        this.setBackground(colour);
    }

            
    }