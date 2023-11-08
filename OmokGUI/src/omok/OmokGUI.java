package omok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class OmokGUI {
	 private JComboBox<String> opponentComboBox;
	 
    public OmokGUI() {
        var frame = new JFrame("Omok");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        var panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 200));
        panel.add(new JLabel("Welcome!"));
        
        String[] opponents = {"Human", "Computer", "Strategy"};
        opponentComboBox = new JComboBox<>(opponents);
        panel.add(opponentComboBox);
        
        JButton playButton = new JButton("Play");
        panel.add(playButton);
        
        playButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOpponent = (String) opponentComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(frame, "Selected opponent: " + selectedOpponent);
            }
        });



        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

// use JOptionPane.showConfirmDialog() for 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new OmokGUI());

}
    }
