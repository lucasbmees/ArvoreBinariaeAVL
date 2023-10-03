public class Main {
    public static void main(String[] args) {
        // Árvore Binária
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        int[] valoresBinarios = {50, 30, 70, 20, 40, 80, 10, 5, 15, 35, 45, 60, 90};
        for (int valor : valoresBinarios) {
            arvoreBinaria.inserir(valor);
        }

        // Imprimir a árvore binária
        System.out.println("Árvore Binária:");
        arvoreBinaria.imprime(arvoreBinaria.raiz, "", true);
        System.out.println();

        // Árvore AVL
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        int[] valoresAVL = {30, 50, 10, 5, 15, 35, 45, 60, 90, 80, 40, 20, 70};
        for (int valor : valoresAVL) {
            arvoreAVL.inserir(valor);
        }

        // Imprimir a árvore AVL com fator de balanceamento
        System.out.println("Árvore AVL:");
        arvoreAVL.imprime(arvoreAVL.raiz, "", true);
        System.out.println();
    }
}
