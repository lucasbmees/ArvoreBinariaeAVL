// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);

        Nodo resultado = arvore.busca(30);

        if (resultado != null) {
            System.out.println("Valor encontrado: " + resultado.getValor());
        } else {
            System.out.println("Valor não encontrado.");
        }

    }}
