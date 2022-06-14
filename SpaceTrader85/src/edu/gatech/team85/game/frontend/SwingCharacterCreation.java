package edu.gatech.team85.game.frontend;

import javax.swing.*;
import java.awt.*;

import edu.gatech.team85.game.backend.Difficulty;
import edu.gatech.team85.game.backend.Constants;
import edu.gatech.team85.game.backend.Game;

/**
 * Panel containing UI pages for the welcome screen and
 * character attribute selection
 */
public class SwingCharacterCreation implements UIScreen {
    private JPanel characterCreationPanel;
    /*
     * This scene needs a reference to the game window so that
     * it can trigger a transition to the next scene.
     */
    private GameWindow parentWindow;

    // We store this UI panel to be called up when the player clicks 'start'
    private JPanel statsScreenPanel;


    // dynamically changeable components
    private JComboBox<Difficulty> difficultyBox;
    private JTextField nameField;
    private JLabel remainingSkillPointsLabel;

    private SpinnerNumberModel pilotSkillModel;
    private SpinnerNumberModel fighterSkillModel;
    private SpinnerNumberModel merchantSkillModel;
    private SpinnerNumberModel engineerSkillModel;


    public  SwingCharacterCreation(GameWindow parent) {
        parentWindow = parent;

        characterCreationPanel = new JPanel();
        characterCreationPanel.setLayout(new FlowLayout());

        statsScreenPanel = generateStatsScreen();

        this.changePage(generateWelcomeScreen());

    }

    public JPanel getMainPanel() {
        return characterCreationPanel;
    }

    private void changePage(JPanel nextPage) {
        characterCreationPanel.removeAll();
        characterCreationPanel.add(nextPage);
        characterCreationPanel.repaint();
        characterCreationPanel.setVisible(true);
        characterCreationPanel.updateUI();
    }

    private JPanel generateWelcomeScreen() {
        JPanel page = new JPanel();

        JLabel welcomeLabel = new JLabel("Welcome to Space Trader");
        page.add(welcomeLabel);

        JButton startButton = new JButton("Click to Start");
        startButton.addActionListener(e -> changePage(statsScreenPanel));
        page.add(startButton);

        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));

        return page;
    }

    private JPanel generateStatsScreen() {
        JPanel page = new JPanel();

        page.add(new JLabel("Enter Name"));
        nameField = new JTextField();
        page.add(nameField);

        page.add(new JLabel("")); //spacer

        JLabel difficultyLabel = new JLabel("Select Difficulty Level");
        page.add(difficultyLabel);

        difficultyBox = new JComboBox<>(Difficulty.values());
        difficultyBox.addActionListener(e -> {
            // Refreshes display of unspent skill points when difficulty is changed
            updateSkillPointsLabel();
        });
        page.add(difficultyBox);

        remainingSkillPointsLabel = new JLabel("Unspent points count");

        page.add(remainingSkillPointsLabel);

        // spinners for distributing skill points
        page.add(new JLabel("Pilot Skill:"));
        pilotSkillModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner pilotSkillSpinner = new JSpinner(pilotSkillModel);
        pilotSkillSpinner.addChangeListener(e -> updateSkillPointsLabel());
        page.add(pilotSkillSpinner);

        page.add(new JLabel("Fighter Skill:"));
        fighterSkillModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner fighterSkillSpinner = new JSpinner(fighterSkillModel);
        fighterSkillSpinner.addChangeListener(e -> updateSkillPointsLabel());
        page.add(fighterSkillSpinner);

        page.add(new JLabel("Merchant Skill:"));
        merchantSkillModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner merchantSkillSpinner = new JSpinner(merchantSkillModel);
        merchantSkillSpinner.addChangeListener(e -> updateSkillPointsLabel());
        page.add(merchantSkillSpinner);

        page.add(new JLabel("Engineer Skill:"));
        engineerSkillModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner engineerSkillSpinner = new JSpinner(engineerSkillModel);
        engineerSkillSpinner.addChangeListener(e -> updateSkillPointsLabel());
        page.add(engineerSkillSpinner);

        // This has to be done after we set up the skill spinners because it uses them as inputs
        updateSkillPointsLabel();

        JButton confirmButton = new JButton("Click to Start");
        confirmButton.addActionListener(e -> confirmSelection());
        page.add(confirmButton);

        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
        return page;
    }

    /**
     * Creates a player character with the selected information
     * And advances to the next scene
     */
    private void confirmSelection() {

        Object difficultyName = difficultyBox.getSelectedItem();
        if (difficultyName == null) {
            difficultyName = Difficulty.Easy.toString();
        }
        Difficulty difficultySetting = Difficulty.valueOf(difficultyName.toString());

        // Create an error pop-up if the player tries to confirm with too many skill points
        if (unspentSkillPoints() < 0) {
            String difficultyString = difficultyName.toString().toLowerCase();
            int maxSkillPoints = Constants.MAX_SKILL_POINTS.get(difficultySetting);
            String errorMessage = "On " + difficultyString
                    + " difficulty, you cannot spend more than "
                    + maxSkillPoints + " skill points.";

            JOptionPane.showMessageDialog(new JFrame(),
                    errorMessage,
                    "Skill Point Limit",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Substitute for empty names
        String  nameSelection = nameField.getText();
        if (nameSelection.isEmpty()) {
            nameSelection = "Nobody";
        }

        int pilotPoints = pilotSkillModel.getNumber().intValue();
        int fighterPoints = fighterSkillModel.getNumber().intValue();
        int merchantPoints = merchantSkillModel.getNumber().intValue();
        int engineerPoints = engineerSkillModel.getNumber().intValue();

        Game.startGame(difficultySetting, nameSelection, pilotPoints, fighterPoints, merchantPoints,
                engineerPoints);
        parentWindow.setWindowPanel(new SwingStatsDisplay(parentWindow));
    }

    /**
     * Displays how many skill points the player has left, or how far they are above the limit
     */
    private void updateSkillPointsLabel() {
        int unspentPoints = unspentSkillPoints();
        if (unspentPoints >= 0) {
            remainingSkillPointsLabel.setText(unspentSkillPoints() + " skill points remaining");
            remainingSkillPointsLabel.setForeground(Color.black);
        } else {
            int pointDebt = unspentPoints * -1;
            remainingSkillPointsLabel.setText("Exceeded skill cap by " + pointDebt + " points.");
            remainingSkillPointsLabel.setForeground(Color.red);
        }
    }


    private int unspentSkillPoints() {

        Object difficultyName = difficultyBox.getSelectedItem();
        if (difficultyName == null) {
            difficultyName = Difficulty.Easy.toString();
        }
        Difficulty difficultySetting = Difficulty.valueOf(difficultyName.toString());


        int maxPoints = Constants.MAX_SKILL_POINTS.get(difficultySetting);

        int pilotPoints = pilotSkillModel.getNumber().intValue();
        int fighterPoints = fighterSkillModel.getNumber().intValue();
        int merchantPoints = merchantSkillModel.getNumber().intValue();
        int engineerPoints = engineerSkillModel.getNumber().intValue();

        return maxPoints - pilotPoints - fighterPoints - merchantPoints - engineerPoints;
    }
}
