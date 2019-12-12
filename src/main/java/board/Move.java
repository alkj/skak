package board;

import pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece movedpiece;
    final int destinationCoordinate;

    private Move(final Board board, Piece movedPiece, int destinationCoordinate){
        this.board=board;
        this.movedpiece=movedPiece;
        this.destinationCoordinate=destinationCoordinate;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public abstract Board execute();

    public static final class MajorMove extends Move {
        public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            return null;
        }
    }

    public static final class AttackMove extends Move {
        final Piece attackedPiece;
        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece=attackedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
    }

}
