
# BINF4241
## Group 41
### Chess (Project 2)
**Members:**
*  Jorge José Vega Espinar
*  Massimo Bertocchi (lite mars)
*  Raffael Botschen

Input description:
Piece you want to move (empty == Pawn).
Origin (File (Rank)) if piece can't be determined otherwise.
x iff a capture is made (can be used to filter out pieces).
+ at the end iff a check occurs.
Checkmate doesn't have to be specially indicated (just with +).


Input examples:
- Move Pawn to e5: e5
- Move tower from file a, rank 5 to e5: Ta5e5
- Castling: 0-0 (kingside) or 0-0-0 (queenside)
- Promotion to Queen/Tower/...: e8Q/e8T/...
- Queen captures piece on field a1: Qxa1
- Queen will set King to check at position a5: Qa5+


