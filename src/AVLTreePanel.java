import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Random;

public class AVLTreePanel extends JPanel {
    private AVLTree tree;
    private int nodeSpacing = 60;
    private int levelHeight = 80;
    private int treeWidth;  // Largura total da árvore
    private int treeHeight; // Altura total da árvore

    public AVLTreePanel(AVLTree tree) {
        this.tree = tree;
        calcularLargura(tree.raiz, 0, 0);
        setPreferredSize(new Dimension(treeWidth, treeHeight));
    }

    private void calcularLargura(Node node, int nivel, int altura) {
        if (node == null) {
            return;
        }
        int currentWidth = nodeSpacing * (int) Math.pow(2, nivel);
        if (currentWidth > treeWidth) {
            treeWidth = currentWidth;
        }
        altura += levelHeight;
        if (altura > treeHeight) {
            treeHeight = altura;
        }
        calcularLargura(node.esquerda, nivel + 1, altura);
        calcularLargura(node.direita, nivel + 1, altura);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree.raiz != null) {
            desenharArvore(g, tree.raiz, treeWidth / 2, 30, treeWidth / 4);
        }
    }

    private void desenharArvore(Graphics g, Node node, int x, int y, int espaco) {
        if (node == null) {
            return;
        }

        g.setColor(Color.WHITE);
        g.fillOval(x - 20, y - 20, 40, 40);
        g.setColor(Color.BLACK);
        g.drawOval(x - 20, y - 20, 40, 40);
        g.drawString(Integer.toString(node.valor), x - 5, y + 5);

        if (node.esquerda != null) {
            int novoX = x - espaco;
            int novoY = y + levelHeight;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, novoX, novoY);
            desenharArvore(g, node.esquerda, novoX, novoY, espaco / 2);
        }

        if (node.direita != null) {
            int novoX = x + espaco;
            int novoY = y + levelHeight;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, novoX, novoY);
            desenharArvore(g, node.direita, novoX, novoY, espaco / 2);
        }
    }

    public static void main(String[] args) {
        long seed = 1234567;
        AVLTree tree = new AVLTree();
        Random random = new Random(seed);

        // Inserir 100 elementos
        for (int i = 1; i <= 100; i++) {
            tree.raiz = tree.inserir(tree.raiz, random.nextInt(200) + 1);
        }

        /*// Inserir 500 elementos
        for (int i = 1; i <= 500; i++) {
            tree.raiz = tree.inserir(tree.raiz, random.nextInt(200) + 1);
        }

        // Inserir 1000 elementos
        for (int i = 1; i <= 1000; i++) {
            tree.raiz = tree.inserir(tree.raiz, random.nextInt(200) + 1);
        }

        // Inserir 10000 elementos
        for (int i = 1; i <= 10000; i++) {
            tree.raiz = tree.inserir(tree.raiz, random.nextInt(200) + 1);
        }

        // Inserir 20000 elementos
        for (int i = 1; i <= 20000; i++) {
            tree.raiz = tree.inserir(tree.raiz, random.nextInt(200) + 1);
        }*/


        int valorProcurado = 5;
        Node resultadoBusca = tree.buscar(valorProcurado);
        if (resultadoBusca != null) {
            System.out.println("Valor encontrado: " + resultadoBusca.valor);
        } else {
            System.out.println("Valor não encontrado: " + valorProcurado);
        }

        tree.excluir(6);



        JFrame frame = new JFrame("Árvore AVL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(new AVLTreePanel(tree));
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);

        try {
            String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
            ProcessBuilder processBuilder = new ProcessBuilder("jconsole", pid);
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
