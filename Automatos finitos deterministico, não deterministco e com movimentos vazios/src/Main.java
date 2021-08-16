import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<Automato> listaAutomato = new ArrayList<>();
    static int ultimaPosicaoAutomato=0;
    static List<EstadoInicial> listaEstadosInicial = new ArrayList<>();
    static int ultimaPosicaoInicial=0;
    static List<EstadoFinal> listaEstadosFinais = new ArrayList<>();
    static int ultimaPosicaoFinal=0;
    static List<String> estadoAtual = new ArrayList<>();
    static int  inicioLista=-1, fimLista=-1;
    static String linha, a[], epsilon = "ε";

    static void leAutomatoTXT(){

        String path = "coloque aqui o caminho do arquivo automato.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            //Lê e cria os estados iniciais
            linha = br.readLine();
            a = linha.split(",");
            for (int i=0; i<a.length; i++){
                listaEstadosInicial.add(new EstadoInicial(a[i]));
                ultimaPosicaoInicial++;
                estadoAtual.add(a[i]);
                fimLista++;
                if(inicioLista==-1)
                    inicioLista++;
            }

            //Lê e cria os estados finais
            linha = br.readLine();
            a = linha.split(",");
            for (int i=0; i<a.length; i++){
                listaEstadosFinais.add(new EstadoFinal(a[i]));
                ultimaPosicaoFinal++;
            }

            //Lê e cria todos os estados do automato
            linha = br.readLine();
            a = linha.split(",");
            for (int i=0; i<a.length; i++){
                a[i] = a[i].replaceAll("\\(", "");
                a[i] = a[i].replaceAll("\\)", "");
                String[] b = a[i].split("\\|");
                String x=b[0],y=b[1],z=b[2];

                listaAutomato.add(new Automato(x,y,z));
                ultimaPosicaoAutomato++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }// fim catch

    }// fim do método leAutomatoTXT

    public static void main(String[] args) {

        leAutomatoTXT();

        String path1 = "coloque aqui o caminho do arquivo entrada.txt";

        try(BufferedReader br1 = new BufferedReader(new FileReader(path1))){

            linha = br1.readLine();
            int novosEstados=0;
            String aux, aux2;
            a = null;

            while (linha != null){

                a = linha.split("");
                
                for (int palavra=0; palavra<a.length; palavra++){//percorre todas as letras da palavra

                    for (int atuais = inicioLista; atuais <= fimLista; atuais++) {//percorre todos os estados atuais do automato

                        for (int automato = 0; automato < ultimaPosicaoAutomato; automato++) {//percorre todos os estados criados a partir do txt


                            if (estadoAtual.get(atuais).equals(listaAutomato.get(automato).getEstado())){//verifica se meu estado atual é igual a algum estado criado no txt

                                if (listaAutomato.get(automato).getSimbolo().equals(epsilon)){// verifica se o simbolo lido é um vazio

                                    fimLista++;
                                    aux = listaAutomato.get(automato).getNovoEstado();
                                    estadoAtual.add(aux);
                                    System.out.println("Estado adicionado "+aux);

                                }
                                else if (listaAutomato.get(automato).getSimbolo().equals(a[palavra])){//verifica se o simbolo da palavra é igual ao simbolo do estado criado no txt

                                    aux = listaAutomato.get(automato).getNovoEstado();
                                    estadoAtual.add(aux);
                                    novosEstados++;
                                    System.out.println("Estado add "+aux);

                                }
                                else {
                                    aux = "Não Existe";
                                    fimLista++;
                                    estadoAtual.add(aux);
                                    novosEstados++;
                                    System.out.println("Est add "+aux);
                                }

                            }//fim do estado atual
                            else {

                                aux = "Não Existe";
                                estadoAtual.add(aux);
                                novosEstados++;

                            }

                        }//fim da lista de automato

                        
                    }// fim dos estados atuais

                    if (novosEstados>0){
                        inicioLista = fimLista+1;
                        fimLista = fimLista+novosEstados;
                        novosEstados=0;
                    }

                }//fim da palavra

                //gravando o arquivo de saida
                int aceitos=0;
                FileWriter fw = new FileWriter("saida.txt",true);

                //percorre a lista gerada de estados atuais, e verifica se algum estado atual é igual ao estado final
                for (int i = inicioLista; i < fimLista ; i++) {
                    for (int j = 0; j < ultimaPosicaoFinal; j++) {
                        if (estadoAtual.get(i).equals(listaEstadosFinais.get(j).getEstado())){
                            aceitos++;
                        }
                    }
                }
                if (aceitos>0){
                    fw.write("Aceito\n");
                    System.out.println("Aceitou a palavra "+ Arrays.stream(a).toList());
                }
                else{
                    System.out.println("Rejeitou a palavra "+ Arrays.stream(a).toList());
                    fw.write("Rejeita\n");
                }

                fw.close();
                aceitos=0;


                //reseta a lista estados atuais
                estadoAtual.removeAll(estadoAtual);
                inicioLista=-1;
                fimLista=-1;

                //reatribui todos os estados iniciais dentro dos estados atuais
                for (int j = 0; j < ultimaPosicaoInicial; j++) {
                    aux2 = listaEstadosInicial.get(j).getEstado();
                    estadoAtual.add(aux2);
                    fimLista++;
                    if (inicioLista==-1)
                        inicioLista++;
                }

                fw.close();

                linha = br1.readLine();

            }// fim do while

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }//fim do main
}//fim da classe
