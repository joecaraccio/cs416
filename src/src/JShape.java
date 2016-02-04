package src;

/**
 * Created by Joe on 2/3/2016.
 */

import java.awt.Color;
import java.awt.Point;
import javax.swing.JComponent;

public abstract class JShape extends JComponent {
    private Color _lineColor;
    private int _lineWidth;
    private Point _lastMouse;
    private int _nominalWidth;
    private int _nominalHeight;
    protected static Color defaultColor;

    public JShape() {
        this(defaultColor);
    }

    public JShape(Color var1) {
        this._lineWidth = 1;
        this._lineColor = var1;
    }

    public JShape(int var1, int var2) {
        this(Color.RED);
        this.setLocation(var1, var2);
    }

    public void setFrameColor(Color var1) {
        this.setLineColor(var1);
    }

    public void setLineColor(Color var1) {
        this._lineColor = var1;
        this.repaint();
    }

    public void setColor(Color var1) {
        this.setLineColor(var1);
        this.repaint();
    }

    public void setThickness(int var1) {
        this.setLineWidth(var1);
    }

    public void setLineWidth(int var1) {
        this._lineWidth = var1;
        this.setSize(this._nominalWidth, this._nominalHeight);
        this.repaint();
    }

    public void setLocation(Point var1) {
        this.setLocation(var1.x, var1.y);
    }

    public void setSize(int var1, int var2) {
        this._nominalWidth = var1;
        this._nominalHeight = var2;
        super.setSize(var1 + this._lineWidth, var2 + this._lineWidth);
        this.repaint();
    }

    public Color getColor() {
        return this._lineColor;
    }

    public Color getFrameColor() {
        return this._lineColor;
    }

    public Color getLineColor() {
        return this._lineColor;
    }

    public Color getBorderColor() {
        return this._lineColor;
    }

    public int getThickness() {
        return this._lineWidth;
    }

    public int getLineWidth() {
        return this._lineWidth;
    }

    public int getXLocation() {
        return this.getX();
    }

    public int getYLocation() {
        return this.getY();
    }

    public void moveBy(int var1, int var2) {
        this.setLocation(this.getX() + var1, this.getY() + var2);
    }

    static {
        defaultColor = Color.RED;
    }
}
