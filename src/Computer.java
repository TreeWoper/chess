public class Computer {

    public Piece[][] board;

    public Computer(Piece[][] b){
        board = b;
    }

    public Piece[][] result(Piece[][] b, Action a){
        Piece[][] copy = board.clone();
        copy[a.pc.row][a.pc.col] = new Piece("Empty", null, a.pc.row, a.pc.col);
        copy[a.row][a.col] = a.pc;
        return copy;
    }
    //Returns the board that results from making move (i, j) on the board.


    public String winner(Piece[][] b){
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b.length; j++){
                if(b[i][j].name.substring(1).equals("King")){
                    if(actionIsPossible(new Action(b[i][j], b[i][j].row+1, b[i][j].col+1))&&
                            actionIsPossible(new Action(b[i][j], b[i][j].row+1, b[i][j].col-1))&&
                            actionIsPossible(new Action(b[i][j], b[i][j].row+1, b[i][j].col))&&
                            actionIsPossible(new Action(b[i][j], b[i][j].row, b[i][j].col+1))&&
                            actionIsPossible(new Action(b[i][j], b[i][j].row, b[i][j].col-1))&&
                            actionIsPossible(new Action(b[i][j], b[i][j].row-1, b[i][j].col+1))&&
                            actionIsPossible(new Action(b[i][j], b[i][j].row-1, b[i][j].col-1))&&
                            actionIsPossible(new Action(b[i][j], b[i][j].row-1, b[i][j].col))
                    ){
                        return b[i][j].name.substring(0, 1);
                    }
                }
            }
        }
        return null;
    }
    //Returns the winner of the game, if there is one.


    public boolean terminal(Piece[][] b){
        if(winner(b) != null){
            return true;
        }
        //NA, DIS IS TOO MUCH BRO
        return false;
    }
    //Returns True if game is over, False otherwise.


    public void utility(Piece[][] b){
    }
    //Returns 1 if X has won the game, -1 if O has won, 0 otherwise.


    public void minimax(Piece[][] b){
    }
    //Returns the optimal action for the current player on the board.


    public void maxV(Piece[][] b) {

    }

    public void minV(Piece[][] b){
                
    }

    public boolean actionIsPossible(Action a){
        return false;
    }
}

class Action{
    int row;
    int col;
    Piece pc;

    public Action(Piece p, int r, int c){
        pc = p;
        row = r;
        col = c;
    }
}