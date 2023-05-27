import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JButton getNewWallpaper;
    private JComboBox apiComboBox;
    private JButton loadAPIbutton;
    private JPanel panel;
    private JTextField apiNameText;
    private JTextField apiUrl;
    private JTextField apiUrlVar;
    private JButton addAPI;
    private JButton removeAPI;
    private JButton exit;

    private wallpaperSetter myWallpaperSetter = wallpaperSetter.getInstance();

    public GUI() {

    // set new wallpaper - button click (action listener)
    getNewWallpaper.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedAPI = apiComboBox.getSelectedItem().toString();  // get selected API
            try {
                String msg = myWallpaperSetter.setNewWallpaper(selectedAPI); // set wallpaper
                JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // show success message
            }
            catch (Exception ex) { // if error, display error message
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, "Can't change wallpaper: " + errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });


        // load default APIs - button click (action listener)
        loadAPIbutton.addActionListener(e -> {
            // try to load default APIs
            try {
                // load default APIs to hashmap
                String msg = myWallpaperSetter.LoadDefaultAPIs();
                // APIs to combobox
                for (String key : myWallpaperSetter.getAPIs()) {
                    apiComboBox.addItem(key);
                }
                JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // show success message
            }
            catch (Exception ex) { // if already loaded, display error message
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // add API - button click (action listener)
        addAPI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String msg = myWallpaperSetter.addNewAPI(apiNameText.getText(), apiUrl.getText(), apiUrlVar.getText()); // try to add new API
                    apiComboBox.addItem(apiNameText.getText()); // add new api to combobox
                    JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // show success message
                }
                catch (Exception ex) { // if error, display error message
                    String errorMsg = ex.getMessage();
                    JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
                }
                finally {
                    // clear text fields
                    apiNameText.setText("");
                    apiUrl.setText("");
                    apiUrlVar.setText("");
                }
            }
        });

        // remove API - button click (action listener)
        removeAPI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String msg = myWallpaperSetter.removeAPI(apiNameText.getText()); // try to remove API
                    apiComboBox.removeItem(apiNameText.getText()); // remove API from combobox
                    JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE); // show success message
                }
                catch (Exception ex) { // if error, display error message
                    String errorMsg = ex.getMessage();
                    JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
                }
                finally {
                    // clear text fields
                    apiNameText.setText("");
                    apiUrl.setText("");
                    apiUrlVar.setText("");
                }
            }
        });

        // exit - button click (action listener)
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // exit program
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wallpaper Changer");
        frame.setContentPane(new GUI().panel);
        frame.setPreferredSize(new java.awt.Dimension(450, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
