<h2>Part 1</h2>
<br/>
<h3>Singleton</h3>
Why?<br/>
I decided to use the singleton pattern in the game class. I thought it's one of the most important classes in the project because without it the entire game doesn't start. I used the singleton to instantiate the Game class in order to prevent multiple instances, because otherwise we could run the application with two (or more) games in parallel.<br/>
How?<br/>
I created a static instance of game in the Game class, I changed the constructor to private and afterwards created a new static method to instantiate the class or return the already instance of the class. In the main class, where the class is instantiated, I changed the normal constructor with a static call for the Game class.
<p align="center">
  <img src="https://github.com/BINF4241-group41/Project_3/blob/master/images/SoftwareConstruction1ClassDiagramm_Cattura.PNG" width="350">
  <img src="https://github.com/BINF4241-group41/Project_3/blob/master/images/SoftwareConstuction1Se.PNG" width="350">
</p>
<br/>
<h3>Iterator</h3>
Why?<br/>
As for my second pattern, I used the Iterator pattern, because it's the pattern that fits the most in my case. I used that in the Game class as well. In the 'identifyPiece' method there is a part which searches for all the pieces that can do a certain move, thus the easyest way to do that is implementing an iterator pattern. It iterates with collection.hasnext() and takes the next element with collection.next().<br/>
How?<br/>
I got an arraylist with all the pieces and put them in the Collection, which implement the iterator of type Piece. Afterwards, I used the Iterator methods to loop through them and get the next element.
<p align="center">
  <img src="https://github.com/BINF4241-group41/Project_3/blob/master/images/SoftwareConstruction2ClassDia.PNG" width="350">
  <img src="https://github.com/BINF4241-group41/Project_3/blob/master/images/SoftwareConstruction2Se.PNG" width="350">
</p>
<br/>
<br/>
<h2>Part 2</h2>

![part2](https://github.com/BINF4241-group41/Project_3/blob/master/images/Part2.PNG)

The first sequence is the constuctor and then in the second one is the makecopy() method. These are the 2 most import parts of the GameBoard class.
<br/>
<h2>Part 3 (Functionality 1: Superqueen, Archbishop)</h2> <br/>
<br/>
<h3>Input Format</h3><br/>
The input is the same as before:
Piece you want to move (empty == Pawn).
Origin (File (Rank)) if piece can't be determined otherwise.
x iff a capture is made (can be used to filter out pieces).
"+" at the end iff a check occurs.
Checkmate doesn't have to be specially indicated (just with +). <br/>
Input examples:

- Move Pawn to e5: e5
- Move tower from file a, rank 5 to e5: Ta5e5
- Castling: 0-0 (kingside) or 0-0-0 (queenside)
- Promotion to Queen/Tower/...: e8Q/e8T/...
- Queen captures piece on field a1: Qxa1
- Queen will set King to check at position a5: Qa5+

<br/>
<br/>
<h3>Code Changes</h3>
<br/>
When initialiting the gameBoard, the code to create the queens and bishops was commented out, and new code to initialize the new non-standard pieces and their adapters was inserted. No other changes were made.
<br/>
Implementation Choices
<br/>
Both non-standard pieces do not inherit from Piece like the standard pieces, and the field names and methods have been changed to reflect this. The adapters are Pieces and are used to adapt the interface of the new pieces to the one of the abstract Piece class, which is used by the other classes.<br/>
SuperQueen has the methods teleportPiece() and isTeleportAllowed() instead of movePiece() and isMoveAllowed(). SuperQueenAdapter is the adapter that corresponds to SuperQueen.<br/>
ArchBishop has the methods gallopToPosition() and isGallopAllowed() instead of movePiece() and isMoveAllowed(). ArchBishopAdapter is the adapter that corresponds to ArchBishop.<br/>