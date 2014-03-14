package logica;

import java.util.Stack; //Clase para la simulacion de pilas

public class Calculadora {
    
    Postfijo postfijo =new Postfijo();// Instanciamos la clase postfijo, que nos retorna el respectivo postfijo
  
  
    //-----------------------------------------------------------------------------------------------
    // Tabla de operaciones aritmeticas disponibles, con su respectivo retorno 
    //-----------------------------------------------------------------------------------------------
    
    public float evaluar(String operacion, String numero1, String numero2) {
   
        // Convertimos los numeros enteros a flotantes
        float num1 = Float.parseFloat(numero1);
        float num2 = Float.parseFloat(numero2);
   
        if (operacion.equals("+")) return (num1 + num2);
        if (operacion.equals("-")) return (num1 - num2);
        if (operacion.equals("*")) return (num1 * num2);
        if (operacion.equals("/")) return (num1 / num2); //Añdir al excepcion matematica, division por cero
        if (operacion.equals("%")) return (num1 % num2);
        if (operacion.equals("^")) return (float) (Math.pow(num1,num2));

        return 0;
    }
   
    
   
    
    //-----------------------------------------------------------------------------------------------
    // Algoritmo que realiza el calculo matematico de una expresion postfija
    //-----------------------------------------------------------------------------------------------
    
    public String calcular(String sintaxis){
      
      
        String sintaxisPostfija=postfijo.convertir_a_Postfijo(sintaxis);        // Validamos y convertimos la expresion ingresada por el usuario en postfijo

        
        //******************************************************************************
        //*            
        //*               Algoritmo de calculo de un postfijo
        //*            
        //******************************************************************************
        
        
        // Si no hay error de sintaxis realice la operacion matematica, de lo contrario retorne error
        if(!sintaxisPostfija.equals("error")){  

        String[] listadoPostfijo = sintaxisPostfija.split(" ");                 // guardamos en una lista los terminos del postfijo
        String caracterActual;                                                  // Almacena el caracter actual que se esta procesando durante la lectura del postfijo

        // variables TEMPORALES, numero 1 y 2, el operador aritmetico y el resultado
        String numero1="";
        String numero2="";
        String operacion="";
        float resultado;


        // PILA que guardara los numero del postfijo
        Stack < String > pilaNumeros = new Stack < String > ();

        // Recorremos la lista creada de terminos del postfijo
        for (int i = 0; i < listadoPostfijo.length; i++) {  
        
            caracterActual=listadoPostfijo[i];                                      // Para almacenar el caracter actual temporalmente
            
        // Si el caracterActual es un numero, añadalo a la pila, de lo contrario haga la respectiva operacion aritmetica
        if(Character.isDigit(caracterActual.charAt(0))){
            pilaNumeros.push(caracterActual);
        }else{

            numero2=pilaNumeros.pop();                                          // Eliminamos el numero de la pila y lo almacenamos como el segundo numero
            numero1=pilaNumeros.pop();                                          // Eliminamos el numero de la pila y lo almacenamos como el segundo numero
            operacion=caracterActual;                                           // Guardamos en la variable operacion el carcter actual

            resultado = evaluar(operacion,numero1,numero2);                     //Evaluamos los dos ultimos elemntos eliminados de la pila y guardamos el resultado

            pilaNumeros.push(""+resultado);                                     // Añadimos el respectivo resultado a la pila

          }
        }

            //Retorno de resultado, el ultimo numero de la pila
            return pilaNumeros.peek();
            
        }else{
            return "Error de sintaxis!";
        }
    }
}