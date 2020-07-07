/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleX;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class CriptografaSenha {
    private String senha;

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        
        
         
        try {
             MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        
        
        byte[] md = messageDigest.digest(senha.getBytes("UTF-8"));
        
        StringBuilder stringBuilder = new StringBuilder();
        
        for(byte b: md){
            stringBuilder.append(String.format("%02X",0xFF & b));
        }
        
        this.senha = stringBuilder.toString();
        
        } 
        catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(CriptografaSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedEncodingException ex) {
        Logger.getLogger(CriptografaSenha.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
