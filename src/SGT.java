import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class SGT extends JPanel implements Runnable, SGTKeyInput, SGTMouseInput{
    private Thread thread;
    private SGTUpdate sgtUpdate;
    private SGTDraw sgtDraw;
    private SGTKeyInput sgtKeyInput;
    private SGTMouseInput sgtMouseInput;
    private int screenWidth, screenHeight;
    private final int FPS;
    private boolean showfps = false;
    SGT(int screenWidth, int screenHeight, int FPS){
        this.FPS = FPS;
        this.requestFocus();
        setSize(this.screenWidth = screenWidth, this.screenHeight = screenHeight);
    }

    @Override
    public void run() {
        double FPS = 1e9/this.FPS;
        double delta = 0;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        int frames = 0;
        while(thread != null){
            long currentTime = System.nanoTime();
            delta += (currentTime-previousTime)/FPS;
            previousTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                frames++;
            }
            if(System.currentTimeMillis()-lastCheck >= 1000){
                if(showfps)
                    System.out.println("FPS: " + frames);
                lastCheck = System.currentTimeMillis();
                frames = 0;
            }
        }
    }
    public void SGTUpdate(SGTUpdate sgtupdate){
        this.sgtUpdate = sgtupdate;
    }
    private void update(){
        sgtUpdate.update();
    }
    public void SGTPaintComponent(SGTDraw sgtDraw){
        this.sgtDraw = sgtDraw;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        sgtDraw.paintComponent(g);
    }
    public void SGTKeyInput(SGTKeyInput sgtKeyInput){
        this.sgtKeyInput = sgtKeyInput;
        SwingUtilities.getWindowAncestor(this).addKeyListener(sgtKeyInput);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        sgtKeyInput.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        sgtKeyInput.keyReleased(e);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        sgtKeyInput.keyTyped(e);
    }
    public void SGTMouseInput(SGTMouseInput sgtMouseInput){
        this.sgtMouseInput = sgtMouseInput;
        SwingUtilities.getWindowAncestor(this).addMouseListener(sgtMouseInput);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        sgtMouseInput.mouseClicked(e);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        sgtMouseInput.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        sgtMouseInput.mouseReleased(e);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        sgtMouseInput.mouseEntered(e);
    }
    @Override
    public void mouseExited(MouseEvent e) {
        sgtMouseInput.mouseExited(e);
    }
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void setSize(int width, int height){
        Dimension d = new Dimension(width, height);
        this.setPreferredSize(d);
    }
    public void showFPS(){
        this.showfps = true;
    }
    public int getScreenWidth(){
        return screenWidth;
    }
    public int getScreenHeight(){
        return screenHeight;
    }

}
