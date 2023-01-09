/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package campominado;

import campominado.ENUM.EnumEstado;
import campominado.ENUM.EnumTipo;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 *
 * @author PIETRO BORGES PARRI - GRR 20204445
 * TARDE - UFPR TADS
 */
public class GUI extends javax.swing.JFrame implements ActionListener, MouseListener {
    
    private Botao[][] matriz;
    private int altura = 0;
    private int largura = 0;
    private int qtdBombas = 10;//quantidade de bombas
    private int NumFlags = 0;// = número de flags
    //private ArrayList<Posicao> PosicaoBombas;
    boolean jogando = true;//true = está jogando, false = acabou o jogo

    /**
     * Creates new form GUI
     */
    public GUI(int altura, int largura, int qtdBombas) {
        this.altura = altura;
        this.largura = largura;
        this.qtdBombas = qtdBombas;
        initComponents();
        setupGridLayout(altura, largura);
        iniciarMatriz(altura, largura);
        colocaMatrizTela(altura, largura);
    }
    
    
    private void AdicionaNumeros(int posX, int posY)//esse método recebe a posição das bombas e adiciona +1 nas casas em volta
    {
        if (posX==0 && posY==0)//canto superior esquerdo (0,0)
        {
            matriz[posX+1][posY].addValor();
            matriz[posX+1][posY+1].addValor();
            matriz[posX][posY+1].addValor();
        }
         else if (posX==altura-1 && posY==largura-1)//canto inferior direito (3,3)
        {
            matriz[posX-1][posY].addValor();
            matriz[posX-1][posY-1].addValor();
            matriz[posX][posY-1].addValor();
        }
         else if (posX==altura-1 && posY==0)//canto inferior esquerdo (3,0)
        {
            matriz[posX-1][posY].addValor();
            matriz[posX-1][posY+1].addValor();
            matriz[posX][posY+1].addValor();
        }
         else if (posX==0 && posY==largura-1)//canto inferior direito (0, 3)
        {
            matriz[posX][posY-1].addValor();
            matriz[posX+1][posY-1].addValor();
            matriz[posX+1][posY].addValor();
        }
         else if (posX==altura-1)
        {
            matriz[posX][posY+1].addValor();    
            matriz[posX-1][posY-1].addValor();
            matriz[posX-1][posY].addValor();
            matriz[posX-1][posY+1].addValor();
            matriz[posX][posY-1].addValor();
        }
         else if (posY==largura-1)
        {
            matriz[posX+1][posY].addValor();
            matriz[posX-1][posY-1].addValor();
            matriz[posX-1][posY].addValor();
            matriz[posX][posY-1].addValor();
            matriz[posX+1][posY-1].addValor();
        }
         else if (posX==0)
        {
            matriz[posX+1][posY].addValor();
            matriz[posX+1][posY+1].addValor();
            matriz[posX][posY+1].addValor();    
            matriz[posX][posY-1].addValor();
            matriz[posX+1][posY-1].addValor();
        }
         else if (posY==0)
        {  
            matriz[posX+1][posY].addValor();
            matriz[posX+1][posY+1].addValor();
            matriz[posX][posY+1].addValor();    
            matriz[posX-1][posY].addValor();
            matriz[posX-1][posY+1].addValor();
        }
         else
        {
            matriz[posX+1][posY].addValor();
            matriz[posX+1][posY+1].addValor();
            matriz[posX][posY+1].addValor();    
            matriz[posX-1][posY-1].addValor();
            matriz[posX-1][posY].addValor();
            matriz[posX-1][posY+1].addValor();
            matriz[posX][posY-1].addValor();
            matriz[posX+1][posY-1].addValor();
        }
    }
    
    private void setupGridLayout(int altura, int largura)
    {
        GridLayout grid = new GridLayout(altura, largura);
        jPanel1.setLayout(grid);
    }
    
    private void RNG(int altura, int largura, int qtdBombas)//random number generator
    {
        Random gerador = new Random();
        int n = qtdBombas;
        
        while (n > 0)
        {
            int l = gerador.nextInt(altura);
            int c = gerador.nextInt(largura);   
            
            if (matriz[l][c].getTipo()!=EnumTipo.BOMBA)
            {
                n--; 
                matriz[l][c].setTipo(EnumTipo.BOMBA);
            }
        }               
    }
    
    private void iniciarMatriz(int altura, int largura)
    {
        matriz = new Botao[altura][largura];
        
        for (int i=0;i<altura;i++)
        {
            for (int j=0;j<largura;j++)
            {
                Botao bt = new Botao(i,j);
                matriz[i][j] = bt;
            }
        }
        
        RNG(altura, largura, qtdBombas);
        
        for (int i=0;i<altura;i++)
        {
            for (int j=0; j<largura; j++)
            {
                if (matriz[i][j].getTipo()==EnumTipo.BOMBA)
                {
                    NumFlags++;
                    AdicionaNumeros(matriz[i][j].getPosicaoX(),matriz[i][j].getPosicaoY());//manda a posX e posY da bomba
                }
            }
        }
        jLabel3.setText(String.valueOf(NumFlags));
    }
    
    private void colocaMatrizTela(int altura, int largura)
    {
        for (int i=0;i<altura;i++)
        {
            for (int j=0;j<largura;j++)
            {
                //pra usar mouse com 2 botões
                matriz[i][j].addMouseListener(this);
                matriz[i][j].addActionListener(this);
                jPanel1.add(matriz[i][j]);
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        label1 = new java.awt.Label();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 900));

        jButton1.setText("Fácil");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setText("Dificuldade");

        jButton2.setText("Médio");
        jButton2.setActionCommand("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Avançado");
        jButton3.setActionCommand("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setText("Flags Restantes: ");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/campominado/download.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(184, 184, 184))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(150, 150, 150))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setLocationRelativeTo(null);
        new GUI(9, 9, 10).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setLocationRelativeTo(null);
        new GUI(16, 16, 40).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setLocationRelativeTo(null);
        new GUI(30, 16, 99).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (altura==9)
        {
            setLocationRelativeTo(null);
            new GUI(9, 9, 10).setVisible(true);
            this.dispose();
        } else if (altura==16)
        {
            setLocationRelativeTo(null);
            new GUI(16, 16, 40).setVisible(true);
            this.dispose();
        } else if (altura==30)
        {
            setLocationRelativeTo(null);
            new GUI(30, 16, 99).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Motif".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI(9, 9, 10).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Botao bt = (Botao) e.getSource();
        //oi
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        Botao bt = (Botao) e.getSource();
        int n = qtdBombas;
        if (jogando == true)
        {
            if (e.getButton() == 1)
            {
                if (bt.getEstado()!=EnumEstado.FLAG)
                {
                    if (bt.getTipo()==EnumTipo.BOMBA)
                    {
                        bt.setText("B");
                        bt.setEstado(EnumEstado.DESATIVADO);
                        jogando = false;//jogo terminou
                        jLabel1.setText("Você perdeu!");
                        bt.setEnabled(false);
                    }
                    else
                    {
                        bt.setEnabled(false);
                        bt.setText(String.valueOf(bt.getValor()));
                        if (bt.getValor()==0)
                            bt.setText("");
                        bt.setEstado(EnumEstado.DESATIVADO);
                    }
                }
            }
            
            if (e.getButton() == 3)
            {
                if (bt.getEstado()==EnumEstado.ATIVADO)
                {
                    bt.setText("F");
                    bt.setEstado(EnumEstado.FLAG);
                    NumFlags--;
                    jLabel3.setText(String.valueOf(NumFlags));
                }
                else if (bt.getEstado()==EnumEstado.FLAG)
                {
                    bt.setText("");
                    bt.setEstado(EnumEstado.ATIVADO);
                    NumFlags++;
                    jLabel3.setText(String.valueOf(NumFlags));
                }
                
                if (NumFlags==0)//condição de vitória
                {
                    for (int i=0;i<altura;i++)
                    {
                        for (int j=0;j<largura;j++)
                        {
                            if (matriz[i][j].getTipo()==EnumTipo.BOMBA && matriz[i][j].getEstado()==EnumEstado.FLAG)
                            {// se todas as bombas estiverem com flags, o jogo termina
                                n--;
                            }
                        }
                    }
                }
                
                if (n==0)
                {
                    jogando = false;
                    jLabel1.setText("Você ganhou!");
                }
            }
        }
        
        if (jogando == false)
        {
            for (int i=0;i<altura;i++)
            {
                for (int j=0;j<largura;j++)
                {
                    if (matriz[i][j].getTipo()==EnumTipo.BOMBA)
                    {
                        matriz[i][j].setText("B");
                        matriz[i][j].setEstado(EnumEstado.DESATIVADO);
                        matriz[i][j].setEnabled(false); 
                    }
                }
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
