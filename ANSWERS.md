Part 1\
__Singleton__\
Why?\
I decided to use the singleton pattern in the game class. I thought it's one of the most important class in the project bacuase without that one the entire game don't start. I used the singleton to instance the Game class in order to prevent double instance otherwise we could run the application with two (or more) parallel games.\
How?\
I created a static instance of game in the Game class, I changed the constructor as private and afterwards I created a new static method to instantiate the class or return the already instance of the class. In the main class, where the class is instantiate i changed the normal constructor with a static call for the Game class.\
__Itertor__\
Why?\
As my second pattern, I used Itertor pattern because it's the most fittable pattern in my case. I used that in the Game class as well. in 'identifyPiece' method there is a part which you search for all the pieces that can do a certain movement, thus the easy way to do that is implimenting a iterator pattern. you can iterate you cicle until iterator.hasnext() and take the next element with iterator.next() and when you get this element put that in our controll\
How?\
I got a arraylist with all my pieces and I put them in Iterator of type "Piece" and afterwards I used the Iterator methods to cycle my loop and get the next element.\