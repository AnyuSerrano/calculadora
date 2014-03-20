/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import analizador.Calculadora;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Developer
 */
@ManagedBean
@SessionScoped
public class CalculadoraBean {
    public String expresion="";
    public String resultadoDigitado="";
    /** Creates a new instance of CalculadoraBean */
    public CalculadoraBean() {
        
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }

    public String getResultadoDigitado() {
        return resultadoDigitado;
    }

    public void setResultadoDigitado(String resultadoDigitado) {
        this.resultadoDigitado = resultadoDigitado;
    }
    
    public void actualizaExpresion(String valor){
        expresion=expresion.concat(valor);
    }
    public void limpiaExpresion(){
        expresion="";
    }
    
    public void resultado(){
        try{
            System.out.println("ingresa try");
            String expresionTMP=expresion;
            Calculadora calculadora=new Calculadora();
            String resultado=calculadora.calcular(expresion);
            expresion=resultado;
            
        }catch(Exception e){
            System.out.println("ingresa catch");
            System.out.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Excepcion de Calculadora",e.getMessage()));  
            
        }
    }
}
