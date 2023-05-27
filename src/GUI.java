import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JButton getNewWallpaper;
    private JComboBox apiComboBox;
    private JButton loadAPIbutton;
    private JPanel panel;

    private wallpaperSetter myWallpaperSetter = wallpaperSetter.getInstance();

    public GUI() {
    getNewWallpaper.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
        apiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        // load default APIs - button click (action listener)
        loadAPIbutton.addActionListener(e -> {
            // try to load default APIs
            try {
                // load default APIs to hashmap
                myWallpaperSetter.LoadDefaultAPIs();
                // APIs to combobox
                for (String key : myWallpaperSetter.getAPIs()) {
                    apiComboBox.addItem(key);
                }
            }
            catch (Exception ex) { // if already loaded, display error message
                String errorMsg = ex.getMessage();
                JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
