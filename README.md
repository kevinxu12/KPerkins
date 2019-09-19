## Welcome to my Console Game Project.

I've made this site to showcase the terminal game I made for my 2020 Kleiner Perkins engineering fellow application.

### Overview

I built a version of draw poker with some little twists of my own. 
If you're curious about what draw poker is, take a look here: [https://en.wikipedia.org/wiki/Draw_poker]
# Rules
In short, draw poker is a two player game. The specific game I made has a 5 dollar buy in for each round and gives each player 100 dollars to start.
Each round goes as follow. Player1 and player2 are both dealt 5 cards. Player1 goes first. Player1 gets one free reroll where he can pick cards in his hand and asked them to be redrawn. Once Player1 has chosen to use or skip this reroll option, he can choose to fold, stay, or raise his bets. This marks the end of Player1's turn. Player2's turn starts now. Player2 gets all the same options as Player1. Once both players have finished, we compare the players hands using traditional poker rules, and whoever wins wins the pot!

I've added a modification where if you can get more rerolls for an additional cost!


# Video Demonstration
Come take a look at how the game is played! I've attached a video below

# Design
OO Design: 
- Class for decks. Decks can deal, shuffle, and be initialized
- Class for cards. Cards have a print command. Can be shown or not shown
- Class for player. The player has his sets of cards: can see, get, replace certain ones.
- Class for scorer. The scorer compares player cards and finds out who wins.
- Class for game. Game keeps track of score, and runs the logic for everything.
- Class for testing. Test the winning logic


Data Structure/Variable Usage: 
- LinkedList for deck. Because will be constantly peeking, popping, and pushing

- Set to determine if straights: idea is if one unique suit, then we have a straight

- Constants class for variables, suits, symbols, etc

- Variables to keep track of highest value/second highest value to distinguish between different pair/triple/quad cases
```

For more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/kevinxu12/KPerkins/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
