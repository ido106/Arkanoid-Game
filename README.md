# Arkanoid Game

### About the game
Arkanoid is a known block breaker arcade game.  
Using the paddle that appears at the bottom of the screen, the player is asked to clear a set of colored blocks that appear on the screen by deflecting a ball towards it, without letting the balls leave the bottom edge of the playfield.  
Each level has its own unique difficulty, arrangement of blocks, background, paddle size and speed, balls size, balls speed, balls amount and more.  

### Gaining points
With every block you destroy, you gain 5 points. When all the blocks on the playground are destroyed, you gain another 100 points and also pass to the next level.
If you managed to pass all four levels, you won. If all the balls managed to slip to the bottom of the playground, you lost. Either way, your final score is shown on the screen.  

### Controlling the game 
At the beginning of each level, a screen of ```3... 2... 1... GO``` is displayed. When the message reaches 'GO', the game begins.  
You can control the paddle with the left and right arrows on your keyboard. When a ball hits the paddle, it bounces back to the playground. Notice that the velocity and direction of the ball are changed depending on where the paddle was hit.  
You can pause the game with the 'p' button. that will display a screen with the message ```paused -- press space to continue``` until a key is pressed, then the screen of 3... 2... 1... GO will appear again.  

### Implementation and code arrangement
The code files are arranged in packages. For example, if you want to see how I implemented the sprites and graphics, go to arkanoid&#8594;Sprites.  
The main package list is:  
- *Levels-* all stages
- *Animations-* all screens (end, pause, countdown and more) and the animation runner
- *Geometry-* all geometry required: velocity, line, point and more
- *HitListeners-* ball remover, counter and more
- *Sprites-* all sprites and graphics
- *Run-* game flow

In the implementation of the code we can find various design patterns: Observer, Decorator, Template and more. In addition, the main basics of OOP principles are kept: abstraction, encapsuliation, inheritance, polymorphism and more.
