package src;

/**
 * Created by Joe on 2/3/2016.
 */

import java.awt.Color;
import java.awt.Container;

public class AMovableRectangle extends ARectangle implements AMovableShape {
    protected Container _parent;
    protected int _moveX;
    protected int _moveY;
    private boolean _draggable;
    private boolean _animated;

    public AMovableRectangle(Color var1) {
        this(0, 0);
        this.setColor(var1);
    }

    public AMovableRectangle(int var1, int var2) {
        super(var1, var2);
        this._parent = null;
        this._draggable = false;
        this._animated = false;
        this._draggable = true;
        this._animated = false;
    }

    public AMovableRectangle() {
        this(0, 0);
        this.setColor(this.defaultColor);
    }

    public void setContainer(Container var1) {
        this._parent = var1;
    }

    public void setDraggable(boolean var1) {
        this._draggable = var1;
    }

    public boolean isDraggable() {
        return this._draggable;
    }

    public void setAnimated(boolean var1) {
        this._animated = var1;
    }

    public boolean isAnimated() {
        return this._animated;
    }

    public void newFrame() {
        if(this._animated) {
            this.moveBy(this._moveX, this._moveY);
            if(this._parent == null) {
                return;
            }

            int var1 = (int)this.getX();
            int var2 = (int)this.getY();
            if(var1 <= this.minX()) {
                this._moveX = -this._moveX;
                this.setLocation(this.minX(), var2);
            } else if(var1 >= this.maxX()) {
                this._moveX = -this._moveX;
                this.setLocation(this.maxX(), var2);
            }

            if(var2 <= this.minY()) {
                this._moveY = -this._moveY;
                this.setLocation(var1, this.minY());
            } else if(var2 >= this.maxY()) {
                this._moveY = -this._moveY;
                this.setLocation(var1, this.maxY());
            }
        }

    }

    public void setMove(int var1, int var2) {
        this._moveX = var1;
        this._moveY = var2;
        this._animated = true;
    }

    private int minX() {
        return this._parent.getX();
    }

    private int minY() {
        return this._parent.getY();
    }

    private int maxX() {
        return (int)((double)(this._parent.getX() + this._parent.getWidth()) - this.getWidth());
    }

    private int maxY() {
        return (int)((double)(this._parent.getY() + this._parent.getHeight()) - this.getHeight());
    }
}
