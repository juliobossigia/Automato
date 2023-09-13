package classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 14418115676
 */
public class transicao {
    private int origem;
    private char letra;
    private int destino;
    private List<transicao> lista;
    private String palavra;
    private char[] caracter;
    
    public transicao(){
        this.origem=0;
        this.letra='z';
        this.destino=0;
        this.lista= new ArrayList<>();
        this.palavra="a";
        this.caracter=new char[0];
    }

   public void setOrigem(int origem){
       this.origem=origem;
   }
   
    public int getOrigem(){
       return origem;
    }
   
    public void setLetra(char letra){
       this.letra=letra;
    }
   
    public char getLetra(){
       return letra;
    }
   
    public void setDestino(int destino){
       this.destino=destino;
    }
   
    public int getDestino(){
       return destino;
    }
    
    public void setLista(List<transicao> lista){
       this.lista=lista;
    }
   
    public List<transicao> getTransicao(){
       return lista;
    }
    
    public void setPalavra(String palavra){
       this.palavra=palavra;
    }
    
    public String getPalavra(){
       return palavra;
    }
   
    
    public void setCaracter(int i, char caracter){
       this.caracter[i]=caracter;
    }
   
    public char getCaracter(int i){
       return caracter[i];
    }
    
    public void fill(){
       System.out.println("Digite uma palavra");
       Scanner Scanner = new Scanner(System.in);
       this.palavra=Scanner.nextLine();
    }
   
    public void cvsParaAtributo(String csv){
       transicao t=new transicao();
       String vetor[]=csv.split(",");
       this.origem = Integer.parseInt(vetor[0]);
       this.letra = vetor[1].charAt(0);
       this.destino = Integer.parseInt(vetor[2]);
       t.setOrigem(origem);
       t.setLetra(letra);
       t.setDestino(destino); 
    }

    public void loadTransicao() throws FileNotFoundException, IOException{
        FileReader f=null;
        this.lista = new ArrayList<>();
        f=new FileReader("arquivo.csv");
        try (Scanner arquivoLido = new Scanner(f)) {
            arquivoLido.useDelimiter("\n");
            String linhaLida=arquivoLido.nextLine();
            while (arquivoLido.hasNext()) {
                transicao t=new transicao();
                linhaLida = arquivoLido.nextLine().replaceAll("q", "").replaceAll("f", "-1");
                t.cvsParaAtributo(linhaLida);
                lista.add(t);
            }
            f.close();
        }  
    }
   
    public void imprimeTabela() {
        System.out.println("Lista de transicoes:");
        System.out.printf("+------------+------------+------------+\n");
        System.out.printf("|   Origem   |   Letra    |  Destino   |\n");
        System.out.printf("+------------+------------+------------+\n");

        for (transicao t : lista) {
            if(t.destino!=-1){
                System.out.printf("| q%-10d| %10c | q%-10d|\n",t.origem,t.letra,t.destino);
            }else{
                System.out.printf("| q%-10d| %10c | %-10s |\n",t.origem,t.letra,"qf");
            }
            System.out.printf("+------------+------------+------------+\n");
        }
    }
    
public int encontrarTransicao(int origemPalavra,char letraPalavra,int i){
        for (transicao t : lista) {
            if(t.origem==origemPalavra && t.letra==letraPalavra){
                if(i<palavra.length()-1 || t.destino!=-1){
                    return t.destino;
                }else{
                    for (int j = lista.size(); j==0; j--) {
                        if(t.origem==origemPalavra && t.letra==letraPalavra){
                            return t.destino;
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    public boolean verificaEstado(int estadoFinalPalavra){
         for (transicao t : lista) {
            if(estadoFinalPalavra==t.origem && t.destino==-1){
                return true;
            }
        }
        return false;
    }
    
       public void verificaCaracter() throws FileNotFoundException, IOException{
       char[] caracter=palavra.toCharArray();
       transicao t=new transicao();
       t.loadTransicao();
       for(int i=0; i<palavra.length();i++){
            origem=(t.encontrarTransicao(origem, caracter[i],i));
            System.out.println("Estado: "+origem);
            if(origem==0){
               break;
            }
        }
       if(t.verificaEstado(origem)){
            System.out.println("Palavra aceita");
        }else{
            System.out.println("Palavra nao aceita");
       }
    }
}    