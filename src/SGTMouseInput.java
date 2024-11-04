import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public interface SGTMouseInput extends MouseListener {
    @Override
    default void mouseClicked(MouseEvent e){}
    @Override
    default void mousePressed(MouseEvent e){}
    @Override
    default void mouseReleased(MouseEvent e){}
    @Override
    default void mouseEntered(MouseEvent e){}
    @Override
    default void mouseExited(MouseEvent e){}
}
