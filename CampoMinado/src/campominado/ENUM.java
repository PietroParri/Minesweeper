/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campominado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pietro
 */
public class ENUM extends javax.swing.JFrame
{   
    enum EnumTipo
    {
        VAZIO, NUMERICO, BOMBA;
    };
    enum EnumEstado
    {
        ATIVADO, DESATIVADO, FLAG;
    };
    private EnumTipo tipo = EnumTipo.NUMERICO;
    private EnumEstado estado = EnumEstado.ATIVADO;
}
