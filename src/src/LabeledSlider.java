package src;

/**
 * Created by Joe on 2/3/2016.
 */

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;

public class LabeledSlider extends JPanel {
    private JSlider _slider;
    private JLabel _label;

    public LabeledSlider(String var1, int var2, int var3, int var4, int var5) {
        this._label = new JLabel(var1);
        this._label.setLocation(0, 0);
        this._slider = new JSlider(var5, var2, var3, var4);
        this._slider.setBorder(new LineBorder(Color.BLACK, 1));
        this.setLayout(new BoxLayout(this, 3));
        this.add(this._label);
        this.add(this._slider);
        this.setTicks(var2, var3);
    }

    public LabeledSlider(String var1, int var2, int var3, int var4) {
        this(var1, var2, var3, var4, 0);
    }

    public JSlider getJSlider() {
        return this._slider;
    }

    public void addChangeListener(ChangeListener var1) {
        this._slider.addChangeListener(var1);
    }

    public void setValue(int var1) {
        this._slider.setValue(var1);
    }

    protected void setTicks(int var1, int var2) {
        this._slider.setPaintTicks(true);
        this._slider.setPaintLabels(true);
        int var5 = var2 - var1;
        int var3;
        int var4;
        if(var5 <= 50) {
            var4 = 10;
            var3 = 0;
        } else if(var5 != 180 && var5 != 360) {
            if(var5 <= 250) {
                var4 = 50;
                var3 = 10;
            } else if(var5 <= 500) {
                var4 = 100;
                var3 = 50;
            } else {
                int var6 = (int)Math.log10((double)var5);
                var4 = (int)Math.pow(10.0D, (double)var6);
                var3 = (int)Math.pow(10.0D, (double)(var6 - 1));
            }
        } else {
            var4 = 30;
            var3 = 10;
        }

        this._slider.setMajorTickSpacing(var4);
        this._slider.setMinorTickSpacing(var3);
    }
}
