import java.util.ArrayList;
import java.util.List;

public class Halloween {
    public final class Node {
        private int candy;
        private Node left, right, antecessor;
        private boolean verificouDireita = false;
        private boolean verificouEsquerda = false;

        public Node(int candy, Node left, Node right, Node antecessor) {
            this.candy = candy;
            this.left = left;
            this.right = right;
            this.antecessor = antecessor;
        }
    }

    private int index;
    private String line;

    public void solve(String line) {
        this.index = 0;
        this.line = line;
        Node tree = parseTree(null);
        int candy = 0;
        int streets = 0;
        // System.out.println(candy + " " + streets);

        // System.out.println(percorrer(tree, 0));

        if (tree.right == null && tree.left == null) {
            System.out.println("0 " + tree.candy);
        } else {
            int alturaDireita = altura(tree.right);
            int alturaEsquerda = altura(tree.left);

            if (alturaDireita <= alturaEsquerda){
                tree.verificouDireita = true;
                int[] caminhos = new int[2];
                percorrer(tree.right, tree, 1, caminhos);
                System.out.println(caminhos[0]+" "+caminhos[1]);
            }
            else{
                tree.verificouEsquerda = true;
                int[] caminhos = new int[2];
                percorrer(tree.left, tree, 1, caminhos);
                System.out.println(caminhos[0]+" "+caminhos[1]);
            }
        }

        // System.out.println(altura(tree));
    }

    private Node parseTree(Node ant) {
        if (index >= line.length())
            throw new IllegalArgumentException("The index cannot be greater than or equal to the length of the line.");

        while (line.charAt(index) == ' ')
            index++;

        if (line.charAt(index) == '(') {
            index++;
            Node antecessor = new Node(0, null, null, ant);
            Node left = parseTree(antecessor);
            Node right = parseTree(antecessor);
            antecessor.left = left;
            antecessor.right = right;
            index++;
            return antecessor;
        } else {
            int start = index;
            while (index < line.length() && Character.isDigit(line.charAt(index)))
                index++;
            int end = index;
            return new Node(Integer.parseInt(line.substring(start, end)), null, null, ant);
        }
    }

    public int[] percorrer(Node no, Node raiz, int caminhosDiferentes, int[] caminhos) {
        if(no == null)
            return caminhos;
        caminhos[0] += 1;
        caminhos[1] += no.candy;
        
        int altura = altura(raiz);

        int alturaDireita = altura(no.right);
        int alturaEsquerda = altura(no.left);

        if (alturaDireita <= alturaEsquerda) {
            if(caminhosDiferentes < altura){
                if (no.right != null && !no.verificouDireita) {
                    no.verificouDireita = true;
                    // caminho += 1;
                    //System.out.println("Direita: " + caminhos); // Adicionando print para depuração
                    caminhosDiferentes++;
                    percorrer(no.right, raiz, caminhosDiferentes, caminhos);
                    return caminhos;
                } else if (no.left != null && !no.verificouEsquerda) {
                    no.verificouEsquerda = true;
                    // caminho += 1;
                    //System.out.println("Esquerda: " + caminhos); // Adicionando print para depuração
                    caminhosDiferentes++;
                    percorrer(no.left, raiz, caminhosDiferentes, caminhos);
                    return caminhos;
                } else{
                    if(no.antecessor == raiz)
                        caminhosDiferentes++;
                    //System.out.println("Voltou: " + caminhos);
                    percorrer(no.antecessor, raiz, caminhosDiferentes, caminhos);
                }
            }
        }

        /*
         * if (alturaDireita == alturaEsquerda) {
         * if (no.verificouDireita == false) {
         * caminho += 1;
         * System.out.println("Direita: " + caminho);
         * caminho += percorrer(no.right);
         * no.verificouDireita = true;
         * }
         * 
         * caminho += 1;
         * System.out.println("Esquerda: " + caminho);
         * caminho += percorrer(no.left); // Corrigido para chamar percorrer(no.left)
         * return caminho;
         * }
         */

        if (alturaDireita > alturaEsquerda) {
            if(caminhosDiferentes < altura){
                if (no.left != null && !no.verificouEsquerda) {
                    no.verificouEsquerda = true;
                    // caminho += 1;
                    //System.out.println("Esquerda: " + caminhos); // Adicionando print para depuração
                    caminhosDiferentes++;
                    percorrer(no.left, raiz, caminhosDiferentes, caminhos);
                    return caminhos;
                } else if (no.right != null && !no.verificouDireita) {
                    no.verificouDireita = true;
                    // caminho += 1;
                    //System.out.println("Direita: " + caminhos); // Adicionando print para depuração
                    caminhosDiferentes++;
                    percorrer(no.right, raiz, caminhosDiferentes, caminhos);
                    return caminhos;
                } else{
                    if(no.antecessor == raiz)
                        caminhosDiferentes++;
                    //System.out.println("Voltou: " + caminhos);
                    percorrer(no.antecessor, raiz, caminhosDiferentes, caminhos);
                }
            }
        }

        /*if (no.verificouDireita == true && alturaDireita > alturaEsquerda) {
            if (no.right != null) {
                caminho += 1;
                System.out.println("Direita: " + caminho); // Adicionando print para depuração
                caminho += percorrer(no.right);
                return caminho;
            }
        }

        if (no.verificouEsquerda == true && alturaEsquerda > alturaDireita) {
            if (no.left != null) {
                caminho += 1;
                System.out.println("Esquerda: " + caminho);
                caminho += percorrer(no.left); // Corrigido para chamar percorrer(no.left)
                return caminho;
            }
        }*/
        return caminhos;
    }

    public static int altura(Node no) {

        if (no == null) {
            return 0;
        }

        int alturaEsquerda = altura(no.left);
        int alturaDireita = altura(no.right);

        return (alturaEsquerda + alturaDireita) + 1;
    }

}
