/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javafx.scene.control.TextField;

/**
 * 
 * @author Alexander
 */
public class CalculatorWork {
    private Double op1;
    private Double op2;
    private Double memory = null;    
    private String operator;
    
    /**
     * 
     * @param op1 
     */
    public void setOp1(Double op1) {
        this.op1 = op1;
    }
    
    /**
     * Op2 kann nur gesetzt werden, wenn op1 gesetzt wurde
     * @param op2 
     */
    public void setOp2(Double op2) {
        assert(op1 != null);
        this.op2 = op2;
    }
    
    /**
     * Operator kann nur gesetzt werden, wenn op1 gesetzt wurde
     * Kennt nur die vier Grundrechenoperatoren "+,-,*,/"
     * @param operator 
     */
    public void setOperator(String operator) {
        assert(op1 != null);
        assert("+".equals(operator) || "-".equals(operator) || "*".equals(operator) || "/".equals(operator));
        this.operator = operator;
    }
    
    /**
     * 
     * @return op1
     */
    public Double getOp1() {
        return op1;
    }
    
    /**
     * 
     * @return op2
     */
    public Double getOp2() {
        return op2;
    }
    
    /**
     * 
     * @return operator
     */
    public String getOperator() {
        return operator;
    }
    
    /**
     * 
     * @return memory
     */
    public Double getMemory() {
        return memory;
    }
    
    /**
     * Kann beim ersten Aufruf einfach gesetzt werden, da mit null initialisiert
     * @param memory 
     */
    public void setMemory(Double memory) {
        this.memory = memory;
    }
    
    /**
     * Führt Rechnung aus
     * Gültigkeit der Operatoren wird im Setter geprüft
     * @return Ergebnis der Rechnung
     */
    public double calculate(){
        double result = 0;
        if(this.op1 != null && this.operator != null && this.op2!= null){
            switch(this.getOperator()){
               case "+":{
                   result = getOp1() + getOp2();
                   break;
               }
               case "-":{
                   result = getOp1() - getOp2();
                   break;
               }
               case "*":{
                   result = getOp1() * getOp2();
                   break;
               }
               case "/":{
                   result = getOp1() / getOp2();
                   break;
               }
            }
            return result;
        }
        else {
            System.err.println("Benoetigte Werte nicht vorhanden!");
            return Double.NaN;            
        }
    }    
    
    public boolean tryValueOfDisplay(TextField txtFldDisplay){
        double value;
        try{
           value = Double.valueOf(txtFldDisplay.getText());        
        }catch(RuntimeException e){
            System.err.println("Caught " + e.getClass().getSimpleName() + ": " + e.getMessage());
            return false;
        }        
        return true;        
    }
    
}
