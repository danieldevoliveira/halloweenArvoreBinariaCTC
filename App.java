import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) {

       //quandoi 

        // Substitua o caminho do arquivo abaixo pelo caminho do seu arquivo
        String caminhoDoArquivo = "C:\\Users\\Home\\Desktop\\estruturas-trabalho-dnieloliveira\\input.txt";

        Halloween halloween = new Halloween();

        File arquivoSelecionado = new File(caminhoDoArquivo);

        if (arquivoSelecionado.exists()) {

            String teste1, teste2, teste3, teste4, teste5;

            try {

                FileReader leitorArquivo = new FileReader(arquivoSelecionado);
                BufferedReader leitorLinha = new BufferedReader(leitorArquivo);

                teste1 = leitorLinha.readLine();
                teste2 = leitorLinha.readLine();
                teste3 = leitorLinha.readLine();
                teste4 = leitorLinha.readLine();
                teste5 = leitorLinha.readLine();

                leitorLinha.close();

                halloween.solve(teste1);
                halloween.solve(teste2);
                halloween.solve(teste3);
                halloween.solve(teste4);
                halloween.solve(teste5);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo não existe: " + caminhoDoArquivo);
        }
    }

}
