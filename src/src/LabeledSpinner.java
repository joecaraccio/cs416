package src;

/**
 * Created by Joe on 2/3/2016.
 */

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

public class LabeledSpinner extends JPanel {
    private JSpinner _spinner;

    public LabeledSpinner(String var1, int var2, int var3, int var4) {
        JLabel var5 = new JLabel(var1);
        this.add(var5);
        SpinnerNumberModel var6 = new SpinnerNumberModel(var4, var2, var3, 1);
        this._spinner = new JSpinner(var6);
        this.add(this._spinner);
    }

    public JSpinner getJSpinner() {
        return this._spinner;
    }

    public void addChangeListener(ChangeListener var1) {
        this._spinner.addChangeListener(var1);
    }

    public void setValue(int var1) {
        this._spinner.setValue(Integer.valueOf(var1));
    }
}

