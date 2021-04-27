package br.com.caelum.desafio;

public class AnoBissexto {

    public boolean ehBissexto(int ano){
        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)){
            return true;
        }
        return false;
    }
}
