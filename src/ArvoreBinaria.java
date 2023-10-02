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
                System.out.println(atual.getValor());
            } else {
                atual = atual.getDireita();
                System.out.println(atual.getValor());
            }
        }

        return null;

    }
    private int encontrarMenorValor(Nodo raiz) {
        int minValue = raiz.getValor();
        while (raiz.getEsquerda() != null) {
            minValue = raiz.getEsquerda().getValor();
            raiz = raiz.getEsquerda();
        }
        return minValue;
    }
    public void remover(int valor) {
        raiz = removerNo(raiz, valor);
    }
    private Nodo removerNo(Nodo raiz, int valor) {
        if (raiz == null) {
            return null;
        }

        if (valor < raiz.getValor()) {
            raiz.setEsquerda(removerNo(raiz.getEsquerda(), valor));
        } else if (valor > raiz.getValor()) {
            raiz.setDireita(removerNo(raiz.getDireita(), valor));
        } else {
            if (raiz.getEsquerda() == null) {
                return raiz.getDireita();
            } else if (raiz.getDireita() == null) {
                return raiz.getEsquerda();
            }

            raiz.setValor(encontrarMenorValor(raiz.getDireita()));
            raiz.setDireita(removerNo(raiz.getDireita(), raiz.getValor()));
        }

        return raiz;
    }
    public void imprime(Nodo node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getValor());

            String newPrefix = prefix + (isLeft ? "│ " : " ");

            imprime(node.getDireita(), newPrefix, true);
            imprime(node.getEsquerda(), newPrefix, false);
        } else {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "Vazio");
        }
    }
}

