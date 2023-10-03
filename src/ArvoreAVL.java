public class ArvoreAVL {
    Nodo raiz;

    public ArvoreAVL() {
        raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    private Nodo inserir(Nodo no, int valor) {
        if (no == null) {
            return new Nodo(valor);
        }

        if (valor < no.getValor()) {
            no.setEsquerda(inserir(no.getEsquerda(), valor));
        } else if (valor > no.getValor()) {
            no.setDireita(inserir(no.getDireita(), valor));
        } else {
            // Valor já existe, você decide o que fazer aqui
            return no;
        }

        // Atualizar altura do nó atual
        no.setAltura(1 + Math.max(altura(no.getEsquerda()), altura(no.getDireita())));

        // Verificar o fator de balanceamento e realizar rotações se necessário
        int fatorBalanceamento = calcularFatorBalanceamento(no);

        // Rotação à direita
        if (fatorBalanceamento > 1 && valor < no.getEsquerda().getValor()) {
            return rotacaoDireita(no);
        }

        // Rotação à esquerda
        if (fatorBalanceamento < -1 && valor > no.getDireita().getValor()) {
            return rotacaoEsquerda(no);
        }

        // Rotação à esquerda-direita
        if (fatorBalanceamento > 1 && valor > no.getEsquerda().getValor()) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        }

        // Rotação à direita-esquerda
        if (fatorBalanceamento < -1 && valor < no.getDireita().getValor()) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private Nodo rotacaoDireita(Nodo y) {
        Nodo x = y.getEsquerda();
        Nodo T2 = x.getDireita();

        // Realizar rotação
        x.setDireita(y);
        y.setEsquerda(T2);

        // Atualizar alturas
        y.setAltura(Math.max(altura(y.getEsquerda()), altura(y.getDireita())) + 1);
        x.setAltura(Math.max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);

        return x;
    }

    private Nodo rotacaoEsquerda(Nodo x) {
        Nodo y = x.getDireita();
        Nodo T2 = y.getEsquerda();

        // Realizar rotação
        y.setEsquerda(x);
        x.setDireita(T2);

        // Atualizar alturas
        x.setAltura(Math.max(altura(x.getEsquerda()), altura(x.getDireita())) + 1);
        y.setAltura(Math.max(altura(y.getEsquerda()), altura(y.getDireita())) + 1);

        return y;
    }

    private int altura(Nodo no) {
        if (no == null) {
            return 0;
        }
        return no.getAltura();
    }

    private int calcularFatorBalanceamento(Nodo no) {
        if (no == null) {
            return 0;
        }
        return altura(no.getEsquerda()) - altura(no.getDireita());
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
                    " (Altura: " + node.getAltura() + ")");

            String newPrefix = prefix + (isLeft ? "│ " : " ");

            imprime(node.getDireita(), newPrefix, true);
            imprime(node.getEsquerda(), newPrefix, false);
        } else {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "Vazio");
        }
    }
    }


