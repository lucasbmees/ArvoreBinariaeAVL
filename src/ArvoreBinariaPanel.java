import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Random;

public class ArvoreBinariaPanel extends JPanel {
    private ArvoreBinaria arvore;
    private int nodeSpacing = 60;
    private int levelHeight = 80;
    private int treeWidth;  // Largura total da árvore
    private int treeHeight; // Altura total da árvore

    public ArvoreBinariaPanel(ArvoreBinaria arvore) {
        this.arvore = arvore;
        calcularLargura(arvore.raiz, 0, 0);
        setPreferredSize(new Dimension(treeWidth, treeHeight));
    }

    private void calcularLargura(Node nodo, int nivel, int altura) {
        if (nodo == null) {
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
        calcularLargura(nodo.getEsquerda(), nivel + 1, altura);
        calcularLargura(nodo.getDireita(), nivel + 1, altura);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arvore.raiz != null) {
            desenharArvore(g, arvore.raiz, treeWidth / 2, 30, treeWidth / 4);
        }
    }

    private void desenharArvore(Graphics g, Node nodo, int x, int y, int espaco) {
        if (nodo == null) {
            return;
        }

        g.setColor(Color.WHITE);
        g.fillOval(x - 20, y - 20, 40, 40);
        g.setColor(Color.BLACK);
        g.drawOval(x - 20, y - 20, 40, 40);
        g.drawString(Integer.toString(nodo.getValor()), x - 5, y + 5);

        if (nodo.getEsquerda() != null) {
            int novoX = x - espaco;
            int novoY = y + levelHeight;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, novoX, novoY);
            desenharArvore(g, nodo.getEsquerda(), novoX, novoY, espaco / 2);
        }

        if (nodo.getDireita() != null) {
            int novoX = x + espaco;
            int novoY = y + levelHeight;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, novoX, novoY);
            desenharArvore(g, nodo.getDireita(), novoX, novoY, espaco / 2);
        }
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Random random = new Random();

        for (int i = 1; i <= 20000; i++) {
            int valor_aleatorio = random.nextInt(200) + 1; // Gere um número aleatório entre 1 e 200
            arvore.inserir(valor_aleatorio);
        }


        JFrame frame = new JFrame("Árvore Binária");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(new ArvoreBinariaPanel(arvore));
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
