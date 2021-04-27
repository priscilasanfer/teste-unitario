package br.com.caelum.matematica;

import org.junit.Assert;
import org.junit.Test;

public class MatematicaMalucaTest {

    @Test
    public void deveMultiplicarNumerosMaioresQue30(){
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
        Assert.assertEquals(31*4, matematicaMaluca.contaMaluca(31));
    }

    @Test
    public void deveMultiplicarNumerosMaioresQue10EMenoresQue30(){
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
        Assert.assertEquals(15*3, matematicaMaluca.contaMaluca(15));
    }

    @Test
    public void deveMultiplicarNumerosMenoresQue10(){
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();
        Assert.assertEquals(2*2, matematicaMaluca.contaMaluca(2));
    }

}