package JavaProgram;

import java.awt.*;
import java.awt.event.*;

 class Calculator extends Frame implements ActionListener {
 //variables
  private TextField display;
  private Button[] buttons;
  private String[] labels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"};
  private double result = 0; 
  private String operator = "=";
  private boolean start = true;
   
   //layout design
  public Calculator() {
    super("Amrit_Calculator");  
    setLayout(new BorderLayout());  
    display = new TextField("0");  
    display.setEditable(false);  
    display.setBackground(Color.black);  
    display.setForeground(Color.white);  
    add(display, BorderLayout.NORTH);  
    Panel panel = new Panel();  
    panel.setLayout(new GridLayout(4, 4)); 
    buttons = new Button[16]; 
    for (int i = 0; i < 16; i++) {  
      buttons[i] = new Button(labels[i]);  
      buttons[i].addActionListener(this);  
      if (i % 4 == 3) {  
        buttons[i].setBackground(Color.orange); 
        buttons[i].setForeground(Color.white);  
      } else {  
        buttons[i].setBackground(Color.gray); 
        buttons[i].setForeground(Color.black); 
      }
      panel.add(buttons[i]);  
    }
    add(panel, BorderLayout.CENTER);  
    addWindowListener(new WindowAdapter() {  
      public void windowClosing(WindowEvent e) {
        System.exit(0);  
      }
    });
    pack();  
    setSize(400, 300);
    setVisible(true);  
  }

  // Handle button clicks
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();  
    if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) { 
      if (start) {  
        display.setText(command); 
        start = false;  
      } else {
        display.setText(display.getText() + command);  
      }
    } else {  
      if (start) { 
        if (command.equals("-")) {  
          display.setText(command);  
          start = false; 
        } else {
          operator = command;  
        }
      } else {
        double x = Double.parseDouble(display.getText());  
        calculate(x); 
        operator = command;  
        start = true;  
      }
    }
  }
   //calculation
  private void calculate(double n) {
    switch (operator) {  
      case "+": result += n; break;  
      case "-": result -= n; break;  
      case "*": result *= n; break;  
      case "/": result /= n; break;  
      case "=": result = n; break; 
    }
    display.setText("" + result);  
  }

  public static void main(String[] args) {
    new Calculator();  
  }
}