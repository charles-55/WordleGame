import javax.swing.*;
import java.awt.*;

public class WordleFrame extends JFrame implements WordleView {

    private JButton[][] buttons;
    private final WordleController controller;
    private static final Color GRAY = Color.GRAY;
    private static final Color YELLOW = Color.YELLOW;
    private static final Color GREEN = Color.GREEN;

    public WordleFrame() {
        super("Wordle!");

        WordleModel model;
        while(true) {
            try {
                String correctWord = JOptionPane.showInputDialog("Please enter the " + WordleModel.WORD_LENGTH + "-character word to be guessed");
                model = new WordleModel(correctWord);
                break;
            } catch(Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
        model.addView(this);
        controller = new WordleController(model);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.add(getButtonsPanel());
        contentPanel.add(getTextField());
        this.add(contentPanel);

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private JPanel getButtonsPanel() {
        JPanel panel = new JPanel(new GridLayout(WordleModel.WORD_LENGTH, WordleModel.WORD_LENGTH));
        buttons = new JButton[WordleModel.WORD_LENGTH][WordleModel.WORD_LENGTH];
        for(int i = 0; i < WordleModel.WORD_LENGTH; i++) {
            for(int j = 0; j < WordleModel.WORD_LENGTH; j++) {
                buttons[i][j] = new JButton(" ");
                panel.add(buttons[i][j]);
            }
        }
        panel.setMaximumSize(new Dimension(500, 500));
        panel.setMinimumSize(new Dimension(500, 500));
        panel.setPreferredSize(new Dimension(500, 500));
        return panel;
    }

    private JTextField getTextField() {
        JTextField textField = new JTextField();
        textField.setActionCommand(textField.getText());
        textField.addActionListener(controller);
        return textField;
    }

    @Override
    public void handleInvalidPlay() {
        JOptionPane.showMessageDialog(this, "Invalid play!");
    }

    @Override
    public void handlePlayUpdate(WordlePlayEvent event) {
        buttons[event.getX()][event.getY()].setText(String.valueOf(event.getLetter()));
        switch(event.getPosition()) {
            case INCORRECT -> buttons[event.getX()][event.getY()].setBackground(GRAY);
            case MISPLACED -> buttons[event.getX()][event.getY()].setBackground(YELLOW);
            case CORRECT -> buttons[event.getX()][event.getY()].setBackground(GREEN);
        }

    }

    @Override
    public void handleStatusUpdate(WordleModel.Status status) {
        switch(status) {
            case WON -> {
                JOptionPane.showMessageDialog(this, "Congratulations, you won!");
                this.dispose();
            }
            case LOST -> {
                JOptionPane.showMessageDialog(this, "Boo, you lost!");
                this.dispose();
            }
        }
    }
}
