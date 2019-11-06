package chess;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionPiece implements Iterator<Piece>{

	private ArrayList<Piece> piece;
	private int currentIndex=0;
	
	public CollectionPiece() {
	}

	public void get(ArrayList<Piece> list) {
		piece=list;
		currentIndex=0;
	}
	
	@Override
	public boolean hasNext() {
		if((piece.indexOf(this)+1)<=piece.size())return true;
		return false;
	}

	@Override
	public Piece next() {
		return piece.get(currentIndex++);
	}

}
