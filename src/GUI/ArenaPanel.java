package GUI;

import Observe.Observable;
import Observe.Observer;
import game.Competition.Competitor;
import game.entities.MobileEntity;
import utilities.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static GUI.CompetitionGUI.centerPanel;

public class ArenaPanel extends JPanel implements Observer {
    private Image backgroundImage;
    private static List<Competitor> competitors;

    public ArenaPanel() {
        String weather= BuildArenaPanel.getSelectedWeather().toString();
        String Imagpath = CompetitionGUI.IMAGE_PATH+ weather + ".jpg";
        try{
            Image img = ImageIO.read(new File(Imagpath));
            Image resizedImg = img.getScaledInstance(centerPanel.getWidth(), centerPanel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImg);
            CompetitionGUI.setBackgroundImage(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

   /* public void setBackgroundImage(ImageIcon backgroundImage) {
        this.backgroundImage = backgroundImage.getImage();
        repaint();
    }*/

    public  void setCompetitors(List<Competitor> competitors) {
        ArenaPanel.competitors = competitors;
        for (Competitor competitor : competitors) {
            ((MobileEntity) competitor).addObserver(this);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (competitors != null) {
            for (Competitor competitor : competitors) {
                Point location = competitor.getLocation();
                ImageIcon icon = competitor.getIcon(); // Get the icon for each competitor
                if (icon != null) {
                    g.drawImage(icon.getImage(), (int) location.getX(), (int) location.getY(), this);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Competitor) {
            repaint();
        }
    }
}
