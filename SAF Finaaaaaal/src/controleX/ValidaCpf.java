package controleX;

import java.util.InputMismatchException;


public class ValidaCpf {
 
    //private String cpf;    
    private boolean valido; 
    boolean validarCpf;
    
    public void validaCpf(String cpf){
       if(cpf.equals("00000000000") || cpf.equals("11111111111") ||
           cpf.equals("22222222222") || cpf.equals("33333333333") ||
           cpf.equals("44444444444") || cpf.equals("55555555555") ||
           cpf.equals("66666666666") || cpf.equals("77777777777") ||
           cpf.equals("88888888888") || cpf.equals("99999999999") ||
           cpf.length() != 11){ 
            
            valido =  false;
        
       } else {
            char digito10;
            char digito11;        
            int somatorio;
            int i;
            int resto;
            int numero;
            int peso;

            try{
                // cáculo do primeiro dígito verficador
                somatorio = 0;
                peso = 10;

                for(i = 0; i < 9; i++){
                    numero = (int)(cpf.charAt(i) - 48);
                    somatorio = somatorio + (numero * peso);
                    peso = peso - 1;                
                }

                resto = 11  - (somatorio % 11);
                if(resto == 10 || resto == 11){
                    digito10 = '0';
                }else{
                    digito10 = (char)(resto + 48);
                }


                // segundo dígito verificador
                somatorio = 0;
                peso = 11;
                for(i = 0; i < 10; i++){
                    numero = (int)(cpf.charAt(i) - 48);
                    somatorio = somatorio + (numero * peso);
                    peso = peso - 1;
                }

                resto = 11 - (somatorio % 11);
                if(resto == 10 || resto == 11){
                    digito11 = '0';
                }else{
                    digito11 = (char)(resto + 48);
                }

                // verifica se os digitos verificados são válidos
                if((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10))){
                    //this.cpf = cpf;
                    valido =  true;
                }else{
                    valido =  false;
                }           

            }catch(InputMismatchException erro){
                valido =  false;
            }  
           }
        
                
    }

    public boolean isValido() {
        return valido;
    }

    /**
     * @param valido the valido to set
     */
    public void setValido(boolean valido) {
        this.valido = valido;
    }
}