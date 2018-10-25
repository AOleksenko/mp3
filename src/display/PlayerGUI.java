package display;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayerGUI {

    private JFrame frame;
    private JTextField pathField;

    private File songFile;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    PlayerGUI window = new PlayerGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PlayerGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("MP3 Player by Oleksenko");
        frame.setBounds(100, 100, 290, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

             try{
                 Player p = new Player(new FileInputStream(songFile));
                 p.play();
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "No file selected", "Eror failed to play", JOptionPane.ERROR_MESSAGE);
             }
            }
        });

        startBtn.setFont(new Font("Tahoma", Font.PLAIN,16));
        startBtn.setBounds(10, 60, 255, 61);
        frame.getContentPane().add(startBtn);

        pathField = new JTextField();
        pathField.setForeground(Color.DARK_GRAY);
        pathField.setEditable(false);
        pathField.setText("Song Path");
        pathField.setBounds(10, 10, 182, 35);
        frame.getContentPane().add(pathField);
        pathField.setColumns(10);

        JButton openBtn = new JButton("Open");
        openBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        openBtn.setBounds(205, 10, 59, 35);
        frame.getContentPane().add(openBtn);
    }

    private void open() {
        try{
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choose Song to load...");
            chooser.showOpenDialog(null);
            songFile = chooser.getSelectedFile();
            System.out.println("File"+songFile.getName()+", Selected");
            pathField.setText(songFile.getAbsolutePath());

            if(!songFile.getName().endsWith(".mp3")){
                JOptionPane.showMessageDialog(null, "Invalid file type selected", "Eror", JOptionPane.ERROR_MESSAGE);
                open();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
