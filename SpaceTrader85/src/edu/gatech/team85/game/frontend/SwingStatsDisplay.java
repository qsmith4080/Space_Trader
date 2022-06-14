package edu.gatech.team85.game.frontend;

import javax.swing.*;

import edu.gatech.team85.game.backend.Game;
import edu.gatech.team85.game.backend.Player;

/**
 * Read-only display of player's info after they have selected their character name and skills
 */
public class SwingStatsDisplay implements UIScreen {
    private  JPanel statsDisplayPanel;
    private GameWindow parentWindow;


    public SwingStatsDisplay(GameWindow parent) {
        parentWindow = parent;

        statsDisplayPanel = new JPanel();

        Player player = Game.getPlayerCharacter();

        statsDisplayPanel.add(new JLabel("Player name: " + player.getName()));
        statsDisplayPanel.add(new JLabel("Difficulty level: " + Game.getDifficulty()));
        statsDisplayPanel.add(new JLabel("Money: " + player.getMoney()));

        statsDisplayPanel.add(new JLabel("Pilot Skill: " + player.getPilotSkill()));
        statsDisplayPanel.add(new JLabel("Fighter Skill: " + player.getFighterSkill()));
        statsDisplayPanel.add(new JLabel("Merchant Skill: " + player.getMerchantSkill()));
        statsDisplayPanel.add(new JLabel("Engineer Skill: " + player.getEngineerSkill()));

        statsDisplayPanel.setLayout(new BoxLayout(statsDisplayPanel, BoxLayout.Y_AXIS));

        JButton enterUniverseButton = new JButton("Enter Universe");
        enterUniverseButton.addActionListener(e -> confirmData());
        statsDisplayPanel.add(enterUniverseButton);
    }

    private void confirmData() {
        parentWindow.setWindowPanel(new RegionsDisplay(parentWindow));
    }

    public JPanel getMainPanel() {
        return statsDisplayPanel;
    }
}
