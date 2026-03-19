import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class LayoutDemo extends JFrame implements ActionListener{
    private JPanel topPanel, bottomPanel;
    private JLabel topLabel;
    private JButton topButton;
    private Grid [][] grid;
    private int x,y;

    private int currentPlayer;
    private Random random;
    private int count;

    private String id = "154.009.0525";
    
    public LayoutDemo(int x, int y){
        this.x = x;
        this.y = y;
        this.count = 0;

        this.random = new Random();

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(x,y));
        
        topLabel = new JLabel("Keep the total below 22. Click 'New game' to begin.");
        topButton= new JButton("New game!");
        topButton.addActionListener(this);
        
        topPanel.add(topLabel);
        topPanel.add(topButton);
        
        grid= new Grid [x][y];
        for (int column = 0; column < x; column ++){
            for (int row = 0; row < y; row++){
                grid [column][row] = new Grid(x,y);
                grid [column][row].setColor (column + row);
                grid [column][row].setOpaque(true);
                grid [column][row].setBorderPainted(false);
                grid [column][row].setEnabled(false);
                grid [column][row].setPreferredSize(new Dimension(80, 80));

                
                grid [column][row].addActionListener(this);
                bottomPanel.add(grid[column][row]);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel,BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        pack();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent aevt){
        Object selected = aevt.getSource();

        // Start Game
        // When "New Game" button is clicked
        if (selected.equals(topButton)) {
            this.currentPlayer = this.random.nextInt(2) + 1;
            this.count = 0;

            topLabel.setText("Player: " + currentPlayer + "'s turn  Current: 0");

            for (int column = 0; column < x; column++) {
                for (int row = 0; row < y; row++) {
                    int value = random.nextInt(5) + 1;
                    grid[column][row].setText(String.valueOf(value)); 
                    grid[column][row].setColor(column + row);
                    grid[column][row].setEnabled(true);
                }
            }
        }

        // When a grid cell is clicked
        if (selected instanceof Grid) {
            boolean isPlayer1 = currentPlayer == 1;

            ((Grid)selected).setPlayerColor(currentPlayer);
            ((Grid)selected).setEnabled(false);
            count += Integer.parseInt(((Grid)selected).getText());

            if (count > 21) {
                if (isPlayer1) {
                    topLabel.setText("Player 2 is the winner!");
                } else {
                    topLabel.setText("Player 1 is the winner!");
                }

                // Disable all grid buttons
                for (int column = 0; column < x; column++) {
                    for (int row = 0; row < y; row++) {
                        grid[column][row].setEnabled(false);
                    }
                }

            } else {
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
                topLabel.setText("Player " + currentPlayer + "'s turn  Count: " + count);
            }
        }
    }


    public String getID() {return id;}
}
