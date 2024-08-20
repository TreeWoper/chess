import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends Canvas {

    private static int SIZE = 600;
    private static int len = SIZE/8;
    private static Canvas canvas = new Main();
    private static Piece[][] matt;
    private static JFrame frame = new JFrame("Chess");
    private static Piece selected;
    private static Boolean whiteT = true;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setUpMatt();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.validate();
        frame.setSize(new Dimension(SIZE, SIZE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setSize(SIZE, SIZE);
        frame.add(canvas);
        frame.pack();
        canvas.setBackground(Color.BLACK);
        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}
            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Piece curr = matt[e.getY()/len][e.getX()/len];
                if(curr.name != "Empty"){
                    if(selected != null && !curr.name.substring(0, 1).equals(selected.name.substring(0, 1)) && selected != curr){
                        matt[selected.row][selected.col] = new Piece("Empty", null, selected.row, selected.col);
                        canvas.repaint(selected.col*len, selected.row*len, len, len);
                        matt[e.getY()/len][e.getX()/len] = new Piece(selected.name, selected.img, e.getY()/len, e.getX()/len);
                        if(matt[e.getY()/len][e.getX()/len].name.equals("WPawn") && matt[e.getY()/len][e.getX()/len].row == 0){
                            matt[e.getY()/len][e.getX()/len] = new Piece("WQueen", getImage("WQueen"), e.getY()/len, e.getX()/len);
                        }
                        if(matt[e.getY()/len][e.getX()/len].name.equals("BPawn") && matt[e.getY()/len][e.getX()/len].row == 7){
                            matt[e.getY()/len][e.getX()/len] = new Piece("BQueen", getImage("BQueen"), e.getY()/len, e.getX()/len);
                        }
                        canvas.repaint((e.getX()/len)*len, (e.getY()/len)*len, len, len);
                        selected = null;
                        whiteT = !whiteT;
                        return;
                    }
                    selected = curr;
                    canvas.repaint(selected.col*len, selected.row*len, len, len);
                }
                else{
                    if(selected != null) {
                        matt[selected.row][selected.col] = curr;
                        canvas.repaint(selected.col*len, selected.row*len, len, len);
                        matt[e.getY()/len][e.getX()/len] = new Piece(selected.name, selected.img, e.getY()/len, e.getX()/len);
                        if(matt[e.getY()/len][e.getX()/len].name.equals("WPawn") && matt[e.getY()/len][e.getX()/len].row == 0){
                            matt[e.getY()/len][e.getX()/len] = new Piece("WQueen", getImage("WQueen"), e.getY()/len, e.getX()/len);
                        }
                        if(matt[e.getY()/len][e.getX()/len].name.equals("BPawn") && matt[e.getY()/len][e.getX()/len].row == 7){
                            matt[e.getY()/len][e.getX()/len] = new Piece("BQueen", getImage("BQueen"), e.getY()/len, e.getX()/len);
                        }
                        canvas.repaint((e.getX()/len)*len, (e.getY()/len)*len, len, len);
                        whiteT = !whiteT;
                        selected = null;
                        return;
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public static void setUpMatt(){
        matt = new Piece[8][8];
        matt[0][0] = new Piece("BRook", getImage("BRook"), 0, 0);
        matt[0][1] = new Piece("BKnight", getImage("BKnight"), 0, 1);
        matt[0][2] = new Piece("BBishop", getImage("BBishop"), 0, 2);
        matt[0][3] = new Piece("BQueen", getImage("BQueen"), 0, 3);
        matt[0][4] = new Piece("BKing", getImage("BKing"), 0, 4);
        matt[0][5] = new Piece("BBishop", getImage("BBishop"), 0, 5);
        matt[0][6] = new Piece("BKnight", getImage("BKnight"), 0, 6);
        matt[0][7] = new Piece("BRook", getImage("BRook"), 0, 7);
        for(int i = 0; i < 8; i++){
            matt[1][i] = new Piece("BPawn", getImage("BPawn"), 1, i);
            matt[6][i] = new Piece("WPawn", getImage("WPawn"), 6, i);
        }
        for(int i = 2; i < 6; i++){
            for(int j = 0; j < 8; j++){
                matt[i][j] = new Piece("Empty", null, i, j);
            }
        }
        matt[7][0] = new Piece("WRook", getImage("WRook"), 7, 0);
        matt[7][1] = new Piece("WKnight", getImage("WKnight"), 7, 1);
        matt[7][2] = new Piece("WBishop", getImage("WBishop"), 7, 2);
        matt[7][3] = new Piece("WQueen", getImage("WQueen"), 7, 3);
        matt[7][4] = new Piece("WKing", getImage("WKing"), 7, 4);
        matt[7][5] = new Piece("WBishop", getImage("WBishop"), 7, 5);
        matt[7][6] = new Piece("WKnight", getImage("WKnight"), 7, 6);
        matt[7][7] = new Piece("WRook", getImage("WRook"), 7, 7);
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                g2d.setColor((j+i)%2 == 0 ? Color.CYAN:Color.BLUE);
                g2d.fillRect(i*len, j*len, len, len);
            }
        }
        g2d.drawImage(getImage("background"), 0, 0, SIZE, SIZE, null);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(matt[i][j].name != "Empty"){
                    if(matt[i][j].name.substring(1, 2).equals("Q")){
                        g2d.drawImage(matt[i][j].img, (j*len)+(len/8), (i*len)+(len/8), len-(len/4), len-(len/4), null);
                    }
                    else {
                        g2d.drawImage(matt[i][j].img, (j * len) + (len / 16), (i * len) + (len / 16), len - (len / 8), len - (len / 8), null);
                    }
                }
            }
        }
    }

    public static Image getImage(String loc){
        loc = loc+".png";
        Image imageOfPiece = null;
        try {
            imageOfPiece = ImageIO.read(new File(loc));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageOfPiece;
    }
}

class Piece{
    String name;
    Image img;
    int row;
    int col;
    boolean wT = true;
    public Piece(String n, Image i, int r, int c){
        name = n;
        img = i;
        row = r;
        col = c;
    }
    public void setRow(int r){
        row = r;
    }
    public void setCol(int c){
        col = c;
    }
    public void changeT(){wT = !wT;}
}