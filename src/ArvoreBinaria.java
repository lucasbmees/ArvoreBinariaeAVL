public class ArvoreBinaria {
    Nodo raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    // Método para inserir um valor na árvore
    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return;
        }

        Nodo atual = raiz;
        while (true) {
            if (valor < atual.getValor()) {
                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(new Nodo(valor));
                    return;
                }
                atual = atual.getEsquerda();
            } else if (valor > atual.getValor()) {
                if (atual.getDireita() == null) {
                    atual.setDireita(new Nodo(valor));
                    return;
                }
                atual = atual.getDireita();
            } else {
                // Valor já existe, você decide o que fazer aqui
                return;
            }
        }
    }

    public Nodo busca(int valor) {
        Nodo atual = raiz;

        while (atual != null) {
            if (valor == atual.getValor()) {
                return atual;
            } else if (valor < atual.getValor()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        return null;
    }
}
