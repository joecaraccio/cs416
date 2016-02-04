package src;

/**
 * Created by Joe on 2/3/2016.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class FrameTimer extends Timer {
    private NewFrame _mover;

    public FrameTimer(int var1, NewFrame var2) {
        super(var1, (ActionListener)null);
        this._mover = var2;
        FrameTimer.MoveListener var3 = new FrameTimer.MoveListener();
        this.addActionListener(var3);
    }

    private class MoveListener implements ActionListener {
        private MoveListener() {
        }

        public void actionPerformed(ActionEvent var1) {
            FrameTimer.this._mover.newFrame();
        }
    }
}
