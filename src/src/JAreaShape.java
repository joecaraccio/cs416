package src;

/**
 * Created by Joe on 2/3/2016.
 */

import java.awt.Color;

public abstract class JAreaShape extends JShape {
    private Color _fillColor;

    public JAreaShape() {
        this(Color.RED);
    }

    public JAreaShape(Color var1) {
        this(0, 0);
        this.setColor(var1);
    }

    public JAreaShape(int var1, int var2) {
        super(var1, var2);
        this.setSize(50, 50);
        this.setColor(Color.RED);
    }

    public void setFillColor(Color var1) {
        this._fillColor = var1;
        this.repaint();
    }

    public void setColor(Color var1) {
        this.setFrameColor(var1);
        this.setFillColor(var1);
    }

    public Color getFillColor() {
        return this._fillColor;
    }

    public Color getColor() {
        return this._fillColor;
    }
}
