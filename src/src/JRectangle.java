package src;

/**
 * Created by Joe on 2/3/2016.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class JRectangle extends JAreaShape {
    public JRectangle() {
        this(Color.RED);
    }

    public JRectangle(Color var1) {
        this(0, 0);
        this.setColor(var1);
    }

    public JRectangle(int var1, int var2) {
        super(var1, var2);
    }

    public void paintComponent(Graphics var1) {
        super.paintComponent(var1);
        Graphics2D var2 = (Graphics2D)var1;
        int var3 = this.getWidth();
        int var4 = this.getHeight();
        int var5 = this.getLineWidth();
        var2.setClip(-var5, -var5, var3 + 2 * var5, var4 + 2 * var5);
        var2.setColor(this.getFillColor());
        var2.fillRect(0, 0, var3, var4);
        var2.setStroke(new BasicStroke((float)var5));
        var2.setColor(this.getFrameColor());
        var2.drawRect(0, 0, var3, var4);
    }
}