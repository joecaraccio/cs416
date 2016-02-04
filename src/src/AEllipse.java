package src;

/**
 * Created by Joe on 2/3/2016.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D.Double;

public class AEllipse extends Double implements AShape {
    protected Color _borderColor;
    protected Color _fillColor;
    protected int _lineWidth;
    protected final int defaultW;
    protected final int defaultH;
    protected final Color defaultColor;

    public AEllipse(Color var1) {
        this(0, 0);
        this.setColor(var1);
    }

    public AEllipse(int var1, int var2) {
        this._lineWidth = 1;
        this.defaultW = 30;
        this.defaultH = 30;
        this.defaultColor = Color.RED;
        this.setLocation(var1, var2);
        this.setSize(30, 30);
        this.setColor(this.defaultColor);
    }

    public AEllipse() {
        this(0, 0);
        this.setColor(this.defaultColor);
    }

    public void setFrameColor(Color var1) {
        this._borderColor = var1;
    }

    public void setFillColor(Color var1) {
        this._fillColor = var1;
    }

    public void setColor(Color var1) {
        this._fillColor = var1;
        this._borderColor = var1;
    }

    public Color getColor() {
        return this._fillColor;
    }

    public Color getFillColor() {
        return this._fillColor;
    }

    public Color getFrameColor() {
        return this._borderColor;
    }

    public void setThickness(int var1) {
        this._lineWidth = var1;
    }

    public void setLineWidth(int var1) {
        this._lineWidth = var1;
    }

    public int getXLocation() {
        return (int)this.getX();
    }

    public int getYLocation() {
        return (int)this.getY();
    }

    public void setLocation(Point var1) {
        this.setLocation(var1.x, var1.y);
    }

    public void setLocation(int var1, int var2) {
        super.setFrame((double)var1, (double)var2, this.getWidth(), this.getHeight());
    }

    public void setSize(int var1, int var2) {
        super.setFrame(this.getX(), this.getY(), (double)var1, (double)var2);
    }

    public void moveBy(int var1, int var2) {
        super.setFrame(this.getX() + (double)var1, this.getY() + (double)var2, this.getWidth(), this.getHeight());
    }

    public void display(Graphics2D var1) {
        this.fill(var1);
        this.draw(var1);
    }

    public void fill(Graphics2D var1) {
        Color var2 = var1.getColor();
        var1.setColor(this._fillColor);
        var1.fill(this);
        var1.setColor(var2);
    }

    public void draw(Graphics2D var1) {
        Color var2 = var1.getColor();
        var1.setColor(this._borderColor);
        Stroke var3 = var1.getStroke();
        var1.setStroke(new BasicStroke((float)this._lineWidth));
        var1.draw(this);
        var1.setStroke(var3);
        var1.setColor(var2);
    }
}
