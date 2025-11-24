package Actividad_05;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class AppAudio {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mancilla-Márquez-Reproducción de Audio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton playButton = new JButton("Reproducir Audio");
        playButton.addActionListener(e -> playAudio("sample-3s.wav"));
        
        frame.add(playButton);
        frame.setVisible(true);
    }

    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); // Reproduce el audio [cite: 406-410]
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}