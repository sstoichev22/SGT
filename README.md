# SGT
SGT(Swing GamePanel Template) simplifies the Swing game-making process by allowing the entire Game to be make in 1 file.


```
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class Main{
    //to use variables in the lambdas, they have to be static, any others like the speed doesn't have to be
    static boolean up, down, left, right;
    static int px, py;
    static final int speed = 3;
    public static void main(String[] args) {
        //make the jframe
        JFrame frame = new JFrame("simple square moving game");
        //treat the sgt like a gamepanel as sgt stands for swing gamepanel template
        SGT sgt = new SGT(800, 800, 60);
        //to print the fps each second
        sgt.showFPS();
        //add the sgt to the jframe
        frame.add(sgt);
        //mouse input, methods are default therefore no need to override all of them
        //All mouse input methods:
        //MouseClicked, MousePressed, MouseReleased, MouseEntered, MouseExited
        sgt.SGTMouseInput(new SGTMouseInput() {

        });
        //key input, methods are default therefore no need to override all of them
        //All key input methods:
        //KeyPressed, KeyReleased, KeyTyped
        sgt.SGTKeyInput(new SGTKeyInput() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W) up = true;
                if(e.getKeyCode() == KeyEvent.VK_S) down = true;
                if(e.getKeyCode() == KeyEvent.VK_A) left = true;
                if(e.getKeyCode() == KeyEvent.VK_D) right = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W) up = false;
                if(e.getKeyCode() == KeyEvent.VK_S) down = false;
                if(e.getKeyCode() == KeyEvent.VK_A) left = false;
                if(e.getKeyCode() == KeyEvent.VK_D) right = false;
            }
        });
        //these inputs are basically lambdas for classed that implement KeyListener/MouseListener
        //by setting the gamepanel size and not frame size its more accurate, pack is required
        frame.pack();
        //these are qol and not required but if I don't have them it's very annoying
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //visible required
        frame.setVisible(true);
        //your update method code, only use static variables if you change them
        sgt.SGTUpdate(() -> {
            if(up) py -= speed;
            if(down) py += speed;
            if(left) px -= speed;
            if(right) px += speed;
        });
        //your paintcomponent method, all drawing goes here
        sgt.SGTPaintComponent((g) -> {
            g.fillRect(px, py, 100, 100);
        });
        //!make sure you start your thread!
        sgt.startThread();
    }
}
```
