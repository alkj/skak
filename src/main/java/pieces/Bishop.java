package pieces;

import board.Board;
import board.BoardUtils;
import board.Move;
import board.Move.AttackMove;
import board.Move.MajorMove;
import board.Tile;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-7, -9, 7, 9 };

    Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                if (isFirstColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)||
                        isEigthColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)){
                    break;
                }
                candidateDestinationCoordinate+=candidateCoordinateOffset;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new MajorMove(board,this,candidateDestinationCoordinate));
                    } else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAllience();
                        if (this.PieceAllience != pieceAlliance){
                            legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && candidateOffset==-9 || candidateOffset==7;
    }
    private static boolean isEigthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGTH_COLUMN[currentPosition] && candidateOffset==-7 || candidateOffset==9;
    }
}
