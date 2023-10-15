public class AVLTree {
    Node raiz;

    int altura(Node no) {
        if (no == null)
            return 0;
        return no.altura;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    public Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    public int fatorBalanceamento(Node no) {
        if (no == null)
            return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    public Node inserir(Node no, int chave) {
        if (no == null)
            return new Node(chave);

        if (chave < no.valor)
            no.esquerda = inserir(no.esquerda, chave);
        else if (chave > no.valor)
            no.direita = inserir(no.direita, chave);
        else
            return no;

        no.altura = 1 + max(altura(no.esquerda), altura(no.direita));

        int balanceamento = fatorBalanceamento(no);

        if (balanceamento > 1 && chave < no.esquerda.valor)
            return rotacaoDireita(no);

        if (balanceamento < -1 && chave > no.direita.valor)
            return rotacaoEsquerda(no);

        if (balanceamento > 1 && chave > no.esquerda.valor) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && chave < no.direita.valor) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public Node noComValorMinimo(Node no) {
        Node atual = no;
        while (atual.esquerda != null)
            atual = atual.esquerda;
        return atual;
    }

    public Node buscar(Node no, int chave) {
        if (no == null || no.valor == chave)
            return no;

        if (chave < no.valor)
            return buscar(no.esquerda, chave);

        return buscar(no.direita, chave);
    }

    public Node buscar(int chave) {
        return buscar(raiz, chave);
    }

    public Node excluirNo(Node raiz, int chave) {
        if (raiz == null)
            return raiz;

        if (chave < raiz.valor)
            raiz.esquerda = excluirNo(raiz.esquerda, chave);
        else if (chave > raiz.valor)
            raiz.direita = excluirNo(raiz.direita, chave);
        else {
            if ((raiz.esquerda == null) || (raiz.direita == null)) {
                Node temp = null;
                if (temp == raiz.esquerda)
                    temp = raiz.direita;
                else
                    temp = raiz.esquerda;

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else
                    raiz = temp;

            } else {
                Node temp = noComValorMinimo(raiz.direita);

                raiz.valor = temp.valor;

                raiz.direita = excluirNo(raiz.direita, temp.valor);
            }
        }

        if (raiz == null)
            return raiz;

        raiz.altura = max(altura(raiz.esquerda), altura(raiz.direita) + 1);

        int balanceamento = fatorBalanceamento(raiz);

        if (balanceamento > 1 && fatorBalanceamento(raiz.esquerda) >= 0)
            return rotacaoDireita(raiz);

        if (balanceamento < -1 && fatorBalanceamento(raiz.direita) <= 0)
            return rotacaoEsquerda(raiz);

        if (balanceamento > 1 && fatorBalanceamento(raiz.esquerda) < 0) {
            raiz.esquerda = rotacaoEsquerda(raiz.esquerda);
            return rotacaoDireita(raiz);
        }

        if (balanceamento < -1 && fatorBalanceamento(raiz.direita) > 0) {
            raiz.direita = rotacaoDireita(raiz.direita);
            return rotacaoEsquerda(raiz);
        }

        return raiz;
    }

    public void excluir(int chave) {
        raiz = excluirNo(raiz, chave);
    }
}
