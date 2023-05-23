package controller;


import listener.GameListener;
import model.Constant;
import model.PlayerColor;
import model.Chessboard;
import model.ChessboardPoint;
import view.CellComponent;
import view.ChessboardComponent;
import view.ElephantChessComponent;
import view.MouseChessComponent;
import view.LionChessComponent;
import view.WolfChessComponent;
import view.DogChessComponent;
import view.TigerChessComponent;
import view.CatChessComponent;
import view.PantherChessComponent;


/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
*/
public class GameController implements GameListener {


    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;

    public GameController(ChessboardComponent view, Chessboard model) {
        //用于初始化视图、模型和当前玩家，并注册控制器对象，初始化棋盘和棋子，并重绘视图。
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;

        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }


    private void initialize() {
        //用于初始化棋盘上的每个格子。这里没有写具体的代码，可能是需要根据不同的游戏规则来设置。
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }

    // after a valid move swap the player
    private void swapColor() {
        //用于在一个有效的移动后交换当前玩家的颜色。
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
    }


        //用于检查棋盘上是否有赢家。这里也没有写具体的代码，可能是需要根据不同的游戏规则来判断。
        public boolean win() { // 首先判断棋盘上是否有一个棋子进入了对方的巢穴，如果是，就返回true。
        if (model.getGrid()[0][3].getPiece() != null && model.getGrid()[0][3].getPiece().getOwner() == PlayerColor.RED) { return true; }
        if (model.getGrid()[6][3].getPiece() != null && model.getGrid()[6][3].getPiece().getOwner() == PlayerColor.BLUE) { return true; }
        boolean hasBlue = false;
        boolean hasRed = false;
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (model.getGrid()[i][j].getPiece() != null) {
                    if (model.getGrid()[i][j].getPiece().getOwner() == PlayerColor.BLUE) {
                        hasBlue = true; }
                    if (model.getGrid()[i][j].getPiece().getOwner() == PlayerColor.RED) {
                        hasRed = true; }
                }
            }
        }
        if (!hasRed || !hasBlue) { return true; }
        return false;
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        //用于处理玩家点击一个空白格子的事件。
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            //首先判断selectedPoint是否为空，如果不为空，表示之前已经选中了一个棋子，就要判断是否可以移动到点击的位置。
            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            view.repaint();
            // TODO: if the chess enter Dens or Traps and so on
        }
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ElephantChessComponent component) {
        //用于处理玩家点击一个有棋子的格子的事件。
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }
        //如果点击了不同的棋子，则需要实现捕获功能。
        // TODO: Implement capture function
    }

    public static void loadGameFromFile(String path) {
        //用于从文件中加载游戏。这里也没有写具体的代码，可能是需要读取文件中保存的游戏状态，并恢复到模型和视图层。
        // TODO: Implement loud function
    }
}
