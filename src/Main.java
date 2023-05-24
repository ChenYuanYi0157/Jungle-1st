import Controller.GameController;
import Model.Chessboard;
import view.ChessGameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->{ChessGameFrame Frame=new ChessGameFrame(1200,800);
            GameController controller=new GameController(Frame.getChessboardComponent(),new Chessboard());
            Frame.setVisible(true);
        });
                ;
    }
}