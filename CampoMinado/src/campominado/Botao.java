/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campominado;

import java.awt.Dimension;
import javax.swing.JButton;
import static campominado.Botao.*;
import static campominado.ENUM.*;

/**
 *
 * @author pietro
 */
public class Botao extends JButton 
{
    private int valor = 0;//valor de 1 a 8
    private int posicaoX = 0;//define qual a posição da matriz pra identificar o botão
    private int posicaoY = 0;
    //variável de tipo (campo vazio, campo numérico e campo bomba)
    //variável de estado (ativado, desativado, flag)
    private EnumTipo tipo;
    private EnumEstado estado;
    
    public Botao(int x, int y)
    {
        this.posicaoX = x;
        this.posicaoY = y;
        this.setMinimumSize(new Dimension(7, 7));
        tipo = EnumTipo.NUMERICO;
        estado = EnumEstado.ATIVADO;
    }
    
    public void addValor()
    {
        valor++;
    }
    
    public int getValor()
    {
        return valor;
    }
    
    public int getPosicaoX()
    {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX)
    {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY()
    {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY)
    {
        this.posicaoY = posicaoY;
    }

    public EnumTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipo tipo) {
        this.tipo = tipo;
    }

    public EnumEstado getEstado() {
        return estado;
    }

    public void setEstado(EnumEstado estado) {
        this.estado = estado;
    }
    
    
}
