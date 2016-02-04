package src;

/**
 * Created by Joe on 2/3/2016.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class JLine extends JShape {
    private int _x1;
    private int _y1;
    private int _x2;
    private int _y2;
    private int _drawX1;
    private int _drawY1;
    private int _drawX2;
    private int _drawY2;

    public JLine() {
        this(Color.BLACK);
    }

    public JLine(Color var1) {
        this(0, 0, 10, 10);
        this.setColor(var1);
    }

    public JLine(int var1, int var2, int var3, int var4) {
        super(Color.RED);
        this.setPoints(var1, var2, var3, var4);
    }

    public int getXLocation() {
        return this._x1;
    }

    public int getYLocation() {
        return this._y1;
    }

    public Point getP1() {
        return new Point(this._x1, this._y1);
    }

    public Point getP2() {
        return new Point(this._x2, this._y2);
    }

    public void setPoints(int var1, int var2, int var3, int var4) {
        this._x1 = var1;
        this._y1 = var2;
        this._x2 = var3;
        this._y2 = var4;
        this.updateComponent();
    }

    public void setLocation(int var1, int var2) {
        this._x1 = var1;
        this._y1 = var2;
        this.updateComponent();
    }

    public void setSize(int var1, int var2) {
        this._x2 = this._x1 + var1;
        this._y2 = this._y1 + var2;
        this.updateComponent();
    }

    public void moveBy(int var1, int var2) {
        this._x1 += var1;
        this._y1 += var2;
        this._x2 += var1;
        this._y2 += var2;
        this.updateComponent();
    }

    private void updateComponent() {
        int var1 = Math.min(this._x1, this._x2);
        int var2 = Math.min(this._y1, this._y2);
        int var3 = Math.max(this._x1, this._x2) - var1;
        int var4 = Math.max(this._y1, this._y2) - var2;
        var3 = Math.max(var3, 1);
        var4 = Math.max(var4, 1);
        super.setLocation(var1, var2);
        super.setSize(var3, var4);
        this._drawX1 = this._x1 - var1;
        this._drawY1 = this._y1 - var2;
        this._drawX2 = this._x2 - var1;
        this._drawY2 = this._y2 - var2;
    }

    public void paintComponent(Graphics var1) {
        super.paintComponent(var1);
        Graphics2D var2 = (Graphics2D)var1;
        int var3 = this.getWidth();
        int var4 = this.getHeight();
        int var5 = this.getLineWidth();
        var2.setClip(-var5, -var5, var3 + 2 * var5, var4 + 2 * var5);
        var2.setColor(this.getColor());
        var2.setStroke(new BasicStroke((float)var5));
        var2.drawLine(this._drawX1, this._drawY1, this._drawX2, this._drawY2);
    }
}
