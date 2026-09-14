import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {

    int boardWidth = 360;
    int boardHeight = 540;

    Color customLightGray = new Color(211, 211, 211);
    Color customDarkgray = new Color(169, 169, 169);
    Color customBlack = new Color(0, 0, 0);
    Color customOrange = new Color(255, 165, 0);

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();

    JPanel buttonsPanel = new JPanel();
    double firstNumber;
    String pendingOperator;
    boolean startingNewNumber = true;
    String[] buttonLabels = {
        "AC", "±", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√" , "="
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "±", "%", "." };


    Calculator() {

        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

    
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);


        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for (String buttonLabel : buttonLabels) {
            JButton button = new JButton(buttonLabel);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setBorder(new LineBorder(customBlack, 1));
            button.setFocusPainted(false);

            if (Arrays.asList(rightSymbols).contains(buttonLabel)) {
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);
            } else if (Arrays.asList(topSymbols).contains(buttonLabel)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            } else {
                button.setBackground(customDarkgray);
                button.setForeground(Color.WHITE);
            }

            buttonsPanel.add(button);
            button.addActionListener(event -> handleInput(buttonLabel));
        }

        frame.setVisible(true);
    }

    private void handleInput(String input) {
        if (input.matches("[0-9]")) {
            enterDigit(input);
        } else if (input.equals(".")) {
            enterDecimalPoint();
        } else if (input.equals("AC")) {
            clear();
        } else if (input.equals("±")) {
            toggleSign();
        } else if (input.equals("%")) {
            applyPercent();
        } else if (input.equals("√")) {
            applySquareRoot();
        } else if (input.equals("=")) {
            calculateResult();
        } else {
            chooseOperator(input);
        }
    }

    private void enterDigit(String digit) {
        if (displayLabel.getText().equals("Error") || startingNewNumber) {
            displayLabel.setText(digit);
            startingNewNumber = false;
        } else if (displayLabel.getText().equals("0")) {
            displayLabel.setText(digit);
        } else {
            displayLabel.setText(displayLabel.getText() + digit);
        }
    }

    private void enterDecimalPoint() {
        if (displayLabel.getText().equals("Error") || startingNewNumber) {
            displayLabel.setText("0.");
            startingNewNumber = false;
        } else if (!displayLabel.getText().contains(".")) {
            displayLabel.setText(displayLabel.getText() + ".");
        }
    }

    private void chooseOperator(String operator) {
        if (displayLabel.getText().equals("Error")) {
            clear();
            return;
        }

        double currentNumber = getDisplayedNumber();
        if (pendingOperator != null && !startingNewNumber) {
            if (!performOperation(currentNumber)) {
                return;
            }
        } else {
            firstNumber = currentNumber;
        }

        pendingOperator = operator;
        startingNewNumber = true;
    }

    private void calculateResult() {
        if (pendingOperator == null || displayLabel.getText().equals("Error")) {
            return;
        }

        if (performOperation(getDisplayedNumber())) {
            pendingOperator = null;
            startingNewNumber = true;
        }
    }

    private boolean performOperation(double secondNumber) {
        switch (pendingOperator) {
            case "+":
                firstNumber += secondNumber;
                break;
            case "-":
                firstNumber -= secondNumber;
                break;
            case "×":
                firstNumber *= secondNumber;
                break;
            case "÷":
                if (secondNumber == 0) {
                    showError();
                    return false;
                }
                firstNumber /= secondNumber;
                break;
            default:
                return false;
        }

        displayLabel.setText(formatNumber(firstNumber));
        return true;
    }

    private void toggleSign() {
        if (displayLabel.getText().equals("Error")) {
            return;
        }

        double number = -getDisplayedNumber();
        displayLabel.setText(formatNumber(number));
    }

    private void applyPercent() {
        if (displayLabel.getText().equals("Error")) {
            return;
        }

        double number = getDisplayedNumber() / 100;
        displayLabel.setText(formatNumber(number));
        startingNewNumber = true;
    }

    private void applySquareRoot() {
        if (displayLabel.getText().equals("Error")) {
            return;
        }

        double number = getDisplayedNumber();
        if (number < 0) {
            showError();
            return;
        }

        displayLabel.setText(formatNumber(Math.sqrt(number)));
        startingNewNumber = true;
    }

    private double getDisplayedNumber() {
        return Double.parseDouble(displayLabel.getText());
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return Long.toString((long) number);
        }
        return Double.toString(number);
    }

    private void clear() {
        displayLabel.setText("0");
        firstNumber = 0;
        pendingOperator = null;
        startingNewNumber = true;
    }

    private void showError() {
        displayLabel.setText("Error");
        firstNumber = 0;
        pendingOperator = null;
        startingNewNumber = true;
    }
}
