import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FibonacciUI {
    public void startUIApp() {
        FibonacciCalculatorService fcs = new FibonacciCalculatorService();

        JFrame frame = new JFrame("Fibonacci Calculator");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JTextField textField = new JTextField();
        textField.setBounds(50, 30, 300, 30);
        frame.add(textField);

        JButton button = new JButton("Calculate");
        button.setBounds(150, 70, 100, 30);
        frame.add(button);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(50, 110, 300, 30);
        frame.add(resultLabel);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button) {
                    fibonacciAction(textField, button, resultLabel, fcs);
                } else if (e.getSource() == textField) {
                    fibonacciAction(textField, button, resultLabel, fcs);
                }
            }
        };

        textField.addActionListener(actionListener);
        button.addActionListener(actionListener);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                return false;
            }
        });

        frame.setVisible(true);
    }

    private void fibonacciAction(JTextField textField, JButton button, JLabel resultLabel, FibonacciCalculatorService fcs) {
        try {
            int n = Integer.parseInt(textField.getText());
            if (n < 0) {
                resultLabel.setText("Invalid input. Please enter a positive number.");
            } else {
                final int num = n;
                button.setEnabled(false);
                button.setText("Calculating...");
                SwingWorker<Long, Void> worker = new SwingWorker<Long, Void>() {
                    @Override
                    protected Long doInBackground() throws Exception {
                        return fcs.fibonacciPerformance(num);
                    }

                    @Override
                    protected void done() {
                        try {
                            long result = get();
                            resultLabel.setText("Result: " + result);
                        } catch (Exception e) {
                            resultLabel.setText("Error occurred during calculation.");
                        } finally {
                            button.setEnabled(true);
                            button.setText("Calculate");
                        }
                    }
                };
                worker.execute();
            }
        } catch (NumberFormatException nfe) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }
}
