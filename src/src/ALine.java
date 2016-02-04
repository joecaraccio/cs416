package src;

/**
 * Created by Joe on 2/3/2016.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D.Double;

public class ALine extends Double implements AShape {
    protected Color _lineColor;
    protected int _lineWidth;
    protected Color defaultColor;

    public ALine() {
        this._lineWidth = 1;
        this.defaultColor = Color.BLACK;
        this.setColor(this.defaultColor);
    }

    public ALine(Color var1) {
        this._lineWidth = 1;
        this.defaultColor = Color.BLACK;
        this._lineColor = var1;
    }

    public ALine(int var1, int var2, int var3, int var4) {
        this(Color.RED);
        this.setPoints(var1, var2, var3, var4);
    }

    public void setColor(Color var1) {
        this._lineColor = var1;
    }

    public Color getColor() {
        return this._lineColor;
    }

    public void setThickness(int var1) {
        this._lineWidth = var1;
    }

    public void setLineWidth(int var1) {
        this._lineWidth = var1;
    }

    public int getXLocation() {
        return (int)this.getX1();
    }

    public int getYLocation() {
        return (int)this.getY1();
    }

    public void setPoints(int var1, int var2, int var3, int var4) {
        this.setLine((double)var1, (double)var2, (double)var3, (double)var4);
    }

    public void setLocation(int var1, int var2) {
        this.moveBy((int)((double)var1 - this.getX1()), (int)((double)var2 - this.getY1()));
    }

    public void setSize(int var1, int var2) {
        this.setLine(this.getX1(), this.getY1(), this.getX1() + (double)var1, this.getY1() + (double)var2);
    }

    public void moveBy(int var1, int var2) {
        this.setLine(this.getX1() + (double)var1, this.getY1() + (double)var2, this.getX2() + (double)var1, this.getY2() + (double)var2);
    }

    public void display(Graphics2D var1) {
        this.draw(var1);
    }

    public void draw(Graphics2D var1) {
        Color var2 = var1.getColor();
        var1.setColor(this._lineColor);
        Stroke var3 = var1.getStroke();
        var1.setStroke(new BasicStroke((float)this._lineWidth));
        var1.draw(this);
        var1.setStroke(var3);
        var1.setColor(var2);
    }
}
