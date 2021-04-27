package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class TesteDoAvaliador {

    private Avaliador leiloeiro;
    private Usuario meg;
    private Usuario malu;
    private Usuario rafael;

    @Before
    public void setUp() {
        this.leiloeiro = new Avaliador();
        this.meg  = new Usuario("Meg");
        this.malu = new Usuario("Malu");
        this.rafael = new Usuario("Rafael");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Leilao leilao= new CriadorDeLeilao().para("PalyStation 3")
                .lance(meg, 250.00)
                .lance(malu, 300.00)
                .lance(rafael, 400.00)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
    }

    @Test(expected = RuntimeException.class)
    public void testaMediaDeZeroLance(){
        Leilao leilao = new Leilao("Iphone 7");
        leiloeiro.avalia(leilao);
        assertThat(leiloeiro.getMedia(), equalTo(0));
    }

    @Test
    public void deveEntenderLeilaoComApesarUmLance(){
        Leilao leilao= new CriadorDeLeilao().para("PalyStation 3")
                .lance(meg, 1000.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo(1000.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(1000.0));
    }

    @Test
    public void deveEncontrarOsTresMaioresLances(){
        Leilao leilao= new CriadorDeLeilao().para("PalyStation 3")
                .lance(meg, 100.0)
                .lance(malu, 300.00)
                .lance(rafael, 400.00)
                .lance(rafael, 800.00)
                .constroi();

        // Parte 2 - Ação
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertThat(maiores.size(), equalTo(3));
        assertThat(maiores, hasItems(
                new Lance(meg, 100),
                new Lance(malu, 300),
                new Lance(rafael, 400)));
    }

    @Test
    public void deveEntenderValoresRandomicos(){
        Leilao leilao= new CriadorDeLeilao().para("PalyStation 3")
                .lance(meg, 200.0)
                .lance(meg, 700.0)
                .lance(malu, 450.00)
                .lance(rafael, 120.0)
                .lance(rafael, 700.0)
                .lance(malu, 630.00)
                .lance(meg, 2300.00)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo( 2300.0));
        assertThat(leiloeiro.getMenorLance(), equalTo( 120.0));
    }

    @Test
    public void deveEntenderLancesEmOrdemDecrescente() {
        Leilao leilao= new CriadorDeLeilao().para("PalyStation 3")
                .lance(meg, 400.00)
                .lance(malu, 300.00)
                .lance(rafael, 200.00)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(), equalTo( 400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo( 200.0));
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado(){
        Leilao leilao= new CriadorDeLeilao().para("PalyStation 3")
                .constroi();
        leiloeiro.avalia(leilao);
    }


    @Test
    public void testaLeilaoCom5Lances(){
        Leilao leilao= new CriadorDeLeilao().para("PalyStation 3")
                .lance(meg, 400.00)
                .lance(rafael, 200.00)
                .lance(malu, 300.00)
                .lance(meg, 100.00)
                .lance(rafael, 700.00)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertThat(maiores.size(), equalTo(3));
        assertThat(maiores, hasItems(
                new Lance(rafael, 700.0),
                new Lance(meg, 400.0),
                new Lance(malu, 300.0)));
    }

    @Test
    public void testaLeilaoCom2Lances(){
        Leilao leilao = new Leilao("PlayStation 3 Novo");
        leilao.propoe(new Lance(meg, 400.00));
        leilao.propoe(new Lance(malu, 300.00));

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertThat(maiores.size(), equalTo(2));
        assertThat(maiores, hasItems(
                new Lance(meg, 400.0),
                new Lance(malu, 300.0)));
    }

    @Test(expected = RuntimeException.class)
    public void testaLeilaoSemLances(){
        Leilao leilao = new Leilao("PlayStation 3 Novo");
        leiloeiro.avalia(leilao);
        List<Lance> maiores = leiloeiro.getTresMaiores();
    }
}
