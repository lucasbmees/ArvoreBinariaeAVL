public class ArvoreBinaria {
    Nodo raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

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
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getValor() +
                    " (Dif. Balanceamento: " + calcularDiferencaBalanceamento(node) + ")");

            String newPrefix = prefix + (isLeft ? "│ " : " ");

            imprime(node.getDireita(), newPrefix, true);
            imprime(node.getEsquerda(), newPrefix, false);
        } else {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "Vazio");
        }
    }

    private int altura(Nodo no) {
        if (no == null) {
            return 0;
        }
        return no.getAltura();
    }
    private int calcularDiferencaBalanceamento(Nodo no) {
        if (no == null) {
            return 0;
        }
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }
    }

