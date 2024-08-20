import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessV2 extends JFrame{
    private JPanel[][] board;
    private JFrame frame = new JFrame();
    private int SIZE = 600;

    public ChessV2() throws IOException {
        setTitle("ChessV2");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpBoard();
        pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (.5 * (screensize.width - getWidth())), (int) (.5 * (screensize.height - getHeight())), getWidth(), getHeight());
    }

    public void setUpBoard() throws IOException {
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setPreferredSize(new Dimension(SIZE, SIZE));
        getContentPane().setLayout(new GridLayout(8, 8));
        board = new JPanel[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new JPanel();
                board[i][j].setBackground((i+j)%2 == 0 ? Color.CYAN:Color.BLUE);
                board[i][j].setPreferredSize(new Dimension(SIZE/8, SIZE/8));
                BufferedImage myPicture = ImageIO.read(new File("BPawn.png"));
                Image im = myPicture.getScaledInstance((SIZE/8) - ((SIZE/8)/8), (SIZE/8) - ((SIZE/8)/8), Image.SCALE_SMOOTH);
                JLabel picLabel = new JLabel(new ImageIcon(im));
                board[i][j].add(picLabel);
                getContentPane().add(board[i][j]);
            }
        }
        BufferedImage myPicture = ImageIO.read(new File("WPawn.png"));
        Image im = myPicture.getScaledInstance((SIZE/8) - ((SIZE/8)/8), (SIZE/8) - ((SIZE/8)/8), Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(im));
        board[0][0].removeAll();
        board[0][0].add(picLabel);
    }

    public static void main(String[] args) throws IOException {
        new ChessV2();
    }
}

class Square extends Component{
    private int LEN = 400/8;
    String piece;
    int row;
    int col;
    JPanel jp;

    public Square(String p, int r, int c){
        piece = p;
        row = r;
        col = c;
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor((row+col)%2 == 0 ? Color.CYAN:Color.BLUE);
        g2d.fillRect(row*LEN, col*LEN, LEN, LEN);
    }

}