package automato;

import classes.transicao;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author 14418115676
 */
public class Automato {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        transicao t=new transicao();
        t.loadTransicao();
        t.imprimeTabela();
        t.fill();
        t.verificaCaracter();
    } 
}