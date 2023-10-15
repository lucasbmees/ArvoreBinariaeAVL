public class ArvoreBinaria {
    Node raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(int valor) {
        if (raiz == null) {
            raiz = new Node(valor);
            return;
        }


        Node atual = raiz;
        while (true) {
            if (valor < atual.getValor()) {
                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(new Node(valor));
                    return;
                }
                atual = atual.getEsquerda();
            } else if (valor > atual.getValor()) {
                if (atual.getDireita() == null) {
                    atual.setDireita(new Node(valor));
                    return;
                }
                atual = atual.getDireita();
            } else {
                return;
            }
        }
    }

    public Node busca(int valor) {
        Node atual = raiz;

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


    private int encontrarMenorValor(Node raiz) {
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

    private Node removerNo(Node raiz, int valor) {
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

    private int altura(Node no) {
        if (no == null) {
            return 0;
        }
        return no.getAltura();
    }
}