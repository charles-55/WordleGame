import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordleController implements ActionListener {

    private final WordleModel model;

    public WordleController(WordleModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField textField = (JTextField) e.getSource();
        model.play(textField.getText());
        textField.setText("");
    }
}
