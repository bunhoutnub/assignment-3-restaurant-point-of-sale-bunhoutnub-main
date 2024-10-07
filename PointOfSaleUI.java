package org.csu.cpsc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointOfSaleUI extends JFrame implements ActionListener {
    //Main Panel Components
    private JPanel mainPanel;
    private JPanel navButtonsPanel;
    private JButton newOrderBtn;
    private JButton viewOrdersBtn;
    private JButton homeBtn;
    private Menu menu;
    private OrderTrackerInterface orders;
    private JLabel logoLabel;
    Font buttonFont;
    Font labelFont;


    //New Order Panel Components
    private JComboBox<String> itemNumberBox;
    private JButton addToOrderBtn;
    private JButton placeOrderBtn;
    private OrderInterface currentOrder;
    private JLabel totalPriceLabel;

    //View Orders Panel Components
    JButton skipOrderBtn;
    JButton completeOrderBtn;
    JLabel orderLabel;

    public PointOfSaleUI(){
        loadMenu();
        buttonFont = new Font("Verdana", Font.PLAIN, 16);
        labelFont = new Font("Verdana", Font.PLAIN, 16);
        orders = new OrderTracker();
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        navButtonsPanel = new JPanel();

        logoLabel = new JLabel("Restaurant POS");
        logoLabel.setFont(new Font("Garamond", Font.BOLD, 72));
        logoLabel.setForeground(Color.white);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(logoLabel);
        navButtonsPanel.setLayout(new GridLayout(1,3));

        newOrderBtn = new JButton("New Order");
        viewOrdersBtn = new JButton("View Orders");
        homeBtn = new JButton("Home");

        newOrderBtn.addActionListener(this);
        viewOrdersBtn.addActionListener(this);
        homeBtn.addActionListener(this);

        newOrderBtn.setFont(buttonFont);
        viewOrdersBtn.setFont(buttonFont);
        homeBtn.setFont(buttonFont);

        navButtonsPanel.add(newOrderBtn);
        navButtonsPanel.add(viewOrdersBtn);
        navButtonsPanel.add(homeBtn);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(navButtonsPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setSize(900,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadMenu(){
        menu = new Menu();
        menu.loadMenuItems();  // Load the menu items from the CSV file
        itemNumberBox = new JComboBox<>();
        
        // Populate the combo box with menu items
        for (int i = 0; i < menu.getNumberOfItems(); i++) {
            Item item = menu.getMenuItem(i);
            itemNumberBox.addItem(item.getName());  // Add the item name to the combo box
        }
    }


    private void loadMainPanel(){
        mainPanel.removeAll();
        mainPanel.add(logoLabel);
        this.revalidate();
        this.repaint();
    }

    private void placeOrderPanel(){
        mainPanel.removeAll();

        currentOrder = new Order();
        JLabel menuItemsLabel = new JLabel(convertToHtml("Menu\n\n"+menu.printMenuItems()));
        totalPriceLabel = new JLabel("Order Total: $0.0");

        menuItemsLabel.setFont(labelFont);
        menuItemsLabel.setForeground(Color.WHITE);

        totalPriceLabel.setFont(labelFont);
        totalPriceLabel.setForeground(Color.WHITE);

        String[] choices = new String[menu.getNumberOfItems()];
        for(int i = 0; i < menu.getNumberOfItems(); i++){
            choices[i] = String.valueOf(i);
        }
        itemNumberBox = new JComboBox<>(choices);
        addToOrderBtn = new JButton("Add to Order");
        placeOrderBtn = new JButton("Place Order");

        addToOrderBtn.addActionListener(this);
        placeOrderBtn.addActionListener(this);

        addToOrderBtn.setPreferredSize(new Dimension(140, 40));
        placeOrderBtn.setPreferredSize(new Dimension(140, 40));
        itemNumberBox.setPreferredSize(new Dimension(140,40));

        JPanel addToOrderPanel = new JPanel(new FlowLayout());
        addToOrderPanel.setBackground(Color.BLACK);
        addToOrderPanel.add(itemNumberBox);
        addToOrderPanel.add(addToOrderBtn);
        addToOrderPanel.add(placeOrderBtn);

        mainPanel.setLayout(new GridLayout(4,1));
        mainPanel.add(menuItemsLabel);
        mainPanel.add(totalPriceLabel);
        mainPanel.add(addToOrderPanel);




        this.revalidate();
        this.repaint();
    }

    private void loadViewPanel(){
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        skipOrderBtn = new JButton("Skip Order");
        completeOrderBtn = new JButton("Complete Order");
        skipOrderBtn.addActionListener(this);
        completeOrderBtn.addActionListener(this);

        String orderDetails = "Order Details: \n" + orders.showCurrentOrder();
        orderLabel = new JLabel(convertToHtml(orderDetails));
        orderLabel.setFont(labelFont);
        orderLabel.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(skipOrderBtn);
        buttonPanel.add(completeOrderBtn);

        mainPanel.add(orderLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newOrderBtn){
            placeOrderPanel();
        }else if(e.getSource() == viewOrdersBtn){
            loadViewPanel();
        }else if(e.getSource() == addToOrderBtn){
            currentOrder.addItem(menu.getMenuItem(Integer.parseInt(itemNumberBox.getSelectedItem().toString())));
            totalPriceLabel.setText("Order Total: $" + currentOrder.getTotal());
        }else if(e.getSource() == placeOrderBtn){
            orders.addOrder((Order) currentOrder);
            loadMainPanel();
        }else if(e.getSource() == completeOrderBtn){
            orders.getNextOrder();
            loadViewPanel();
        }else if(e.getSource() == homeBtn){
            loadMainPanel();
        }else if(e.getSource() == skipOrderBtn){
            orders.skipOrder();
            loadViewPanel();
        }
    }

    private String convertToHtml(String s) {
        s = s.replaceAll("\n", "<br/>");
        s = "<html>" + s;
        s = s + "</html>";
        return s;
    }
}
