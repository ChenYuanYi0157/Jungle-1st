package Model;

import java.util.HashSet;
import java.util.Set;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    private Cell[][] grid;
    private final Set<ChessboardPoint> river = new HashSet<>();
    public void initiateGridComponents() {

        river.add(new ChessboardPoint(3, 1));
        river.add(new ChessboardPoint(3, 2));
        river.add(new ChessboardPoint(4, 1));
        river.add(new ChessboardPoint(4, 2));
        river.add(new ChessboardPoint(5, 1));
        river.add(new ChessboardPoint(5, 2));

        river.add(new ChessboardPoint(3, 4));
        river.add(new ChessboardPoint(3, 5));
        river.add(new ChessboardPoint(4, 4));
        river.add(new ChessboardPoint(4, 5));
        river.add(new ChessboardPoint(5, 4));
        river.add(new ChessboardPoint(5, 5));
        river.add(new ChessboardPoint(0,3));
        river.add(new ChessboardPoint(8,3));
    }



    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
        initiateGridComponents();
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    private void initPieces() {
        grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "lion",7));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "lion",7));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.RED,"tiger",6));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE,"tiger",6));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.RED,"dog",3));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE,"dog",3));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.RED,"cat",2));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE,"cat",2));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.RED,"mouse",1));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE,"mouse",1));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.RED,"leopard",5));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE,"leopard",5));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.RED,"wolf",4));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE,"wolf",4));
        grid[2][6].setPiece(new ChessPiece(PlayerColor.RED,"elephant",8));
        grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE,"elephant",8));
    }

    private ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        setChessPiece(dest, removeChessPiece(src));
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }
        removeChessPiece(dest);
        setChessPiece(dest,removeChessPiece(src));
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        int i=0;
        for (ChessboardPoint a:river) {
            if (a.getCol()==dest.getCol()&&a.getRow()==dest.getRow()){i=1;break;}
        }
        if (i==0) {
            if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
                return false;
            } else {
                if (calculateDistance(src, dest) != 1) {
                    return false;
                } else {
                    return true;
                }
            }
        }else {
            if (dest.getRow() != 0 && dest.getCol() != 3 || dest.getRow() != 8 && dest.getCol() != 3) {
                if (getChessPieceAt(src).getRank() == 1 && calculateDistance(src, dest) == 1) {
                    return true;
                } else {
                    return false;
                }
            }else {if (dest.getRow() == 0 && dest.getCol() == 3 &&getChessPieceAt(src).getOwner()==PlayerColor.RED){
                return false;
            }else {if (dest.getRow() == 8 && dest.getCol() == 3 &&getChessPieceAt(src).getOwner()==PlayerColor.BLUE){
                return false;
            }else {return true;}}}
        }

    }


    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) == null) {
            return false;
        }else {if (calculateDistance(src, dest) != 1){return false;}else {if (getChessPieceAt(src).canCapture(getChessPieceAt(dest))==false){
            return false;
        }else {return true;}}}


    }
}
