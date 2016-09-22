/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Alexander
 */
public class FXMLDocumentController implements Initializable {

    CalculatorWork calculator = new CalculatorWork();

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btnSub;
    @FXML
    private Button btn0;
    @FXML
    private Button btnPoint;
    @FXML
    private Button btnCalc;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtFldDisplay;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btnMul;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btnDiv;
    @FXML
    private Button btnC;
    @FXML
    private Button BtnAC;
    @FXML
    private Button btnMC;
    @FXML
    private Button btnMR;
    @FXML
    private Button btnMAdd;
    @FXML
    private Button btnMSub;
    @FXML
    private TextField txtFldActions;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnNeg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    /**
     * Zeigt die gedrückte Ziffer im Display an
     * ist die einzige Ziffer im Display 0 und es folgt eine Zahl zwischen 1 und 9
     * wird die 0 durch die gedrückte Ziffer ersetzt
     * @param event 
     */
    @FXML
    private void handleBtnDigit(ActionEvent event) {
        String oldText = txtFldDisplay.getText();
        if (oldText.startsWith("0.")) {
            txtFldDisplay.setText(oldText + ((Button) event.getSource()).getText());
        } else if (oldText.startsWith("0")) {
            txtFldDisplay.setText(((Button) event.getSource()).getText());
        } else {
            txtFldDisplay.setText(oldText + ((Button) event.getSource()).getText());
        }
    }
    
    /**
     * Setzt Dezimalpunkt (nur einer pro Zahl möglich)
     * @param event 
     */
    @FXML
    private void handleBtnPoint(ActionEvent event) {
        String oldText = txtFldDisplay.getText();
        if (!oldText.contains(".")) {
            txtFldDisplay.setText(oldText + ".");
        }
    }
    
    /**
     * Die Zahl im Display wird mit dem zuvor eingegebenen Operand1 und Operator berechnet und ausgegeben
     * @param event 
     */
    @FXML
    private void handleBtnCalc(ActionEvent event) {
        if(calculator.tryValueOfDisplay(txtFldDisplay)){
            calculator.setOp2(Double.valueOf(txtFldDisplay.getText()));
            txtFldDisplay.setText(Double.toString(calculator.calculate()));
            txtFldActions.setText(calculator.getOp1() + calculator.getOperator() + calculator.getOp2());
            calculator.setOperator(null);
        }        
    }
    
    /**
     * Setzt den Operator
     * Solange nicht "=" gedrückt wurde wird mit dem Ergebnis der letzten Operation weiter gerechnet
     * @param event 
     */
    @FXML
    private void handleBtnOperation(ActionEvent event) {
        double value = 0.0f;
        if(calculator.tryValueOfDisplay(txtFldDisplay)) value = Double.valueOf(txtFldDisplay.getText());
        
        //Rechnung ist noch nicht abgeschlossen
        if (calculator.getOp1() != null && calculator.getOperator() != null) {
            calculator.setOp2(value);
            calculator.setOp1(calculator.calculate());
            calculator.setOperator(((Button) event.getSource()).getText());
        } //Rechnung wurde zuvor abgeschlossen
        else {
            calculator.setOp1(value);
            calculator.setOperator(((Button) event.getSource()).getText());
        }
        txtFldDisplay.setText("");
        txtFldActions.setText(calculator.getOp1() + calculator.getOperator());

    }
    
    /**
     * Die Taste AC löscht alle bisherigen Eingaben
     * Die Taste C löscht den zuletzt angezeigten Operand
     * Die Taste DEL löscht die letzte Ziffer im Display
     * @param event 
     */
    @FXML
    private void handleBtnDelete(ActionEvent event) {
        String oldText;
        switch (((Button) event.getSource()).getText()) {
            case "C": {
                txtFldDisplay.setText("");
                break;
            }
            case "AC": {
                txtFldDisplay.setText("");
                txtFldActions.setText("");
                calculator = new CalculatorWork();
                break;
            }
            case "DEL": {
                oldText = txtFldDisplay.getText();
                if (oldText.length() > 0) {
                    txtFldDisplay.setText(oldText.substring(0, oldText.length() - 1));
                }
            }
        }
    }
    
    
    /**
     * Die M-Tasten bedienen einen Speicherplatz:
     * M+ addiert die angezeigte Zahl auf die Zahl im Speicherplatz,
     * M- subtrahiert die angezeigte Zahl von der im Speicherplatz,
     * MR zeigt die Zahl aus dem Speicherplatz im Display an,
     * MC löscht die Zahl im Speicherplatz
     * @param event 
     */
    @FXML
    private void handleBtnMem(ActionEvent event) {
        switch (((Button) event.getSource()).getText()) {
            case "MC": {
                calculator.setMemory(null);
                break;
            }
            case "MR": {
                if(calculator.getMemory() != null)txtFldDisplay.setText(calculator.getMemory().toString());
                else System.err.println("Memory noch nicht gesetzt");
                break;
            }
            case "M+": {
                if (calculator.getMemory() != null && calculator.tryValueOfDisplay(txtFldDisplay)) {
                    calculator.setMemory(calculator.getMemory() + Double.valueOf(txtFldDisplay.getText()));
                } else if(calculator.tryValueOfDisplay(txtFldDisplay)){
                    calculator.setMemory(Double.valueOf(txtFldDisplay.getText()));
                }
                break;
            }
            case "M-": {
                if (calculator.getMemory() != null && calculator.tryValueOfDisplay(txtFldDisplay)) {
                    calculator.setMemory(calculator.getMemory() - Double.valueOf(txtFldDisplay.getText()));
                } else if(calculator.tryValueOfDisplay(txtFldDisplay)){
                    calculator.setMemory(0 - Double.valueOf(txtFldDisplay.getText()));
                }
                break;
            }
        }
        txtFldActions.setText("MEMORY: " + calculator.getMemory());        
    }
    
    /**
     * Negiert den Inhalt des Displays
     * @param event 
     */
    @FXML
    private void handleBtnNeg(ActionEvent event) {
        String oldText = txtFldDisplay.getText();
        txtFldActions.setText("NEG(" + txtFldDisplay.getText() + ")");
        if (oldText.startsWith("-")) {
            txtFldDisplay.setText(oldText.substring(1));
        } else {
            txtFldDisplay.setText("-" + oldText);
        }
    }

}
