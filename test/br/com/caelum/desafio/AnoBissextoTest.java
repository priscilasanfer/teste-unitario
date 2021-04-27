package br.com.caelum.desafio;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnoBissextoTest {

    @Test
    public void deveTestarSeEAnoBissexto(){
        AnoBissexto anoBissexto = new AnoBissexto();
        assertTrue(anoBissexto.ehBissexto(2016));
        assertTrue(anoBissexto.ehBissexto(2012));
    }

    @Test
    public void deveTestarSeNaoEAnoBissexto(){
        AnoBissexto anoBissexto = new AnoBissexto();
        assertFalse(anoBissexto.ehBissexto(1986));
        assertFalse(anoBissexto.ehBissexto(2015));
        assertFalse(anoBissexto.ehBissexto(2011));
    }
}