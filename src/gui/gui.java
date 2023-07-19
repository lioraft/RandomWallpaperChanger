package gui;

import setters.wallpaperSetter;

import javax.swing.*;
import java.awt.*;

public class gui extends JFrame {
    private JComboBox<String> comboBox;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField singleItemTextField;
    private JButton singleItemButton;
    private wallpaperSetter myWallpaperSetter = wallpaperSetter.getInstance();

    public gui() {
        // Set up the JFrame
        setTitle("Wallpaper Changer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(700, 350);

        // Set pastel colors
        Color pastelPink = new Color(231, 189, 194);
        Color pastelBlue = new Color(175, 203, 227);
        Color pastelGreen = new Color(187, 221, 155);

        // First Section - Loading existing data and change wallpaper
        JPanel loadingPanel = new JPanel();
        loadingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(pastelPink), "Change Wallpaper"));
        loadingPanel.setLayout(new GridBagLayout());
        loadingPanel.setBackground(pastelPink);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboBox = new JComboBox<>();
        comboBox.setBackground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        loadingPanel.add(comboBox, gbc);

        button1 = new JButton("Load Default APIs");
        button1.setBackground(pastelGreen);
        button1.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.gridwidth = 1;
        loadingPanel.add(button1, gbc);

        button2 = new JButton("Remove API");
        button2.setBackground(pastelGreen);
        button2.setForeground(Color.DARK_GRAY);
        gbc.gridx = 1;
        gbc.gridy = 1;
        loadingPanel.add(button2, gbc);

        button3 = new JButton("Change Wallpaper");
        button3.setBackground(pastelBlue);
        button3.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loadingPanel.add(button3, gbc);

        // Action listener for loading default APIs
        button1.addActionListener(e -> {
            try {
                // Load default APIs to hashmap
                String msg = myWallpaperSetter.LoadDefaultAPIs();
                // APIs to combobox
                for (String key : myWallpaperSetter.getAPIs()) {
                    comboBox.addItem(key);
                }
                JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // Show success message
            } catch (Exception ex) {
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set action listener for removing API
        button2.addActionListener(e -> {
            try {
                String selectedAPI = comboBox.getSelectedItem().toString();  // Get selected API
                String msg = myWallpaperSetter.removeAPI(selectedAPI); // Try to remove API
                comboBox.removeItem(selectedAPI); // Remove API from combobox
                JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // Show success message
            } catch (Exception ex) { // If error, display error message
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set action listener for changing wallpaper
        button3.addActionListener(e -> {
            try {
                String selectedAPI = comboBox.getSelectedItem().toString();  // Get selected API
                String msg = myWallpaperSetter.setWallpaperFromAPI(selectedAPI); // Set wallpaper
                JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // Show success message
            } catch (Exception ex) {
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, "Can't change wallpaper: " + errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Second Section - Adding new data
        JPanel newDataPanel = new JPanel();
        newDataPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(pastelGreen), "Add API"));
        newDataPanel.setLayout(new GridBagLayout());
        newDataPanel.setBackground(pastelGreen);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);

        label1 = new JLabel("API Name:");
        label1.setForeground(Color.DARK_GRAY);
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.WEST;
        newDataPanel.add(label1, gbc2);

        textField1 = new JTextField(10);
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1.0;
        newDataPanel.add(textField1, gbc2);

        label2 = new JLabel("API URL:");
        label2.setForeground(Color.DARK_GRAY);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.weightx = 0.0;
        newDataPanel.add(label2, gbc2);

        textField2 = new JTextField(10);
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1.0;
        newDataPanel.add(textField2, gbc2);

        label3 = new JLabel("API URL Variable:");
        label3.setForeground(Color.DARK_GRAY);
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.weightx = 0.0;
        newDataPanel.add(label3, gbc2);

        textField3 = new JTextField(10);
        gbc2.gridx = 1;
        gbc2.gridy = 2;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1.0;
        newDataPanel.add(textField3, gbc2);

        JButton addButton = new JButton("Add");
        addButton.setBackground(pastelBlue);
        addButton.setForeground(Color.DARK_GRAY);
        gbc2.gridx = 0;
        gbc2.gridy = 3;
        gbc2.gridwidth = 2;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.weightx = 1.0;
        newDataPanel.add(addButton, gbc2);

        // Action listener for adding a new API
        addButton.addActionListener(e -> {
            try {
                String msg = myWallpaperSetter.addNewAPI(textField1.getText(), textField2.getText(), textField3.getText()); // Try to add new API
                comboBox.addItem(textField1.getText()); // Add new API to combobox
                JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // Show success message
            } catch (Exception ex) { // If error, display error message
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                // Clear text fields
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
            }
        });

        // Third Section - Set wallpaper from URL
        JPanel singleItemPanel = new JPanel();
        singleItemPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(pastelBlue), "From URL"));
        singleItemPanel.setLayout(new GridBagLayout());
        singleItemPanel.setBackground(pastelBlue);

        singleItemTextField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        singleItemPanel.add(singleItemTextField, gbc);

        singleItemButton = new JButton("Get Wallpaper");
        singleItemButton.setBackground(pastelGreen);
        singleItemButton.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        singleItemPanel.add(singleItemButton, gbc);

        // Set action listener for setting wallpaper from URL
        singleItemButton.addActionListener(e -> {
            try {
                String msg = myWallpaperSetter.setWallpaperFromURL(singleItemTextField.getText()); // Try to set wallpaper
                JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // Show success message
            } catch (Exception ex) { // If error, display error message
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Create a container panel to hold all sections
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(Color.white);
        containerPanel.add(loadingPanel, BorderLayout.WEST);
        containerPanel.add(newDataPanel, BorderLayout.CENTER);
        containerPanel.add(singleItemPanel, BorderLayout.EAST);

        add(containerPanel, BorderLayout.CENTER);

        // Display the JFrame
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(gui::new);
    }
}
