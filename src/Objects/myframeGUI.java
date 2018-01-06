package Objects;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class myframeGUI extends JFrame {

    private JPanel contentPane;
    private JPanel contentPaneC;
    private JTable ProduitsTable;
    private JTable Clients;
    private String[] column = {"Nom","Stock","fournisseurs"};
    private String[] column1 = {"Nom du Produit","Quantité"};
    
    private DefaultTableModel tableModel;
    private DefaultTableModel ClientsModel;

    public myframeGUI() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 500, 600, 620);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tableModel = new DefaultTableModel(column, 0);
        ProduitsTable = new JTable(tableModel);
        ProduitsTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ProduitsTable.setBounds(50, 100, 230, 200);
        contentPane.add(ProduitsTable, BorderLayout.CENTER);
        contentPane.add(ProduitsTable.getTableHeader(), BorderLayout.NORTH);

        JLabel lblLogo = new JLabel("Usine");
        lblLogo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblLogo.setBounds(70, 11, 300, 53);
        contentPane.add(lblLogo);

        JLabel Produits = new JLabel("Les Produits disponibles :");
        Produits.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Produits.setBounds(20, 70, 300, 25);
        contentPane.add(Produits);
        String[] list1 = {"Nom","Stock","fournisseurs"};
        setRows(list1);
        
        /*
        contentPaneC = new JPanel();
        contentPaneC.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPaneC);
        contentPaneC.setLayout(null);
        */
        ClientsModel = new DefaultTableModel(column1, 0);
        Clients = new JTable(ClientsModel);
        Clients.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Clients.setBounds(380, 100, 230, 200);
        contentPane.add(Clients, BorderLayout.CENTER);
        contentPane.add(Clients.getTableHeader(), BorderLayout.NORTH);
        
        JLabel Clients = new JLabel("Demande des Produits :");
        Clients.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Clients.setBounds(350, 70, 300, 25);
        contentPane.add(Clients);
        String[] list2 = {"Nom du Produit","Quantité"};
        setRowsDemande(list2);
        
        
    }

        public void setRows(String[] list){
        tableModel.addRow(list);
    }
        
        public void setRowsDemande(String[] list){
        	ClientsModel.addRow(list);
    }
}