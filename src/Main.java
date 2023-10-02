
public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);

        arvore.busca(30);
        arvore.remover(70);

        // Imprimir a árvore
        System.out.println("Árvore Binária:");
        arvore.imprime(arvore.raiz, "", true);


    }}
