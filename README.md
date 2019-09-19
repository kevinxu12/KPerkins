<p align="center">
  <a>
    <img src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="KPCB logo" width="130" height="85">
  </a>
</p>

<h3 align="center">Draw Poker</h3>

<p align="center">
  My Draw Poker Console Game for the Kleiner Perkins 2020 Engineering Fellowship.
  <br>
  <a href="https://youtu.be/tgN11lCcYIk"><strong>Video Demonstration of My Project »</strong></a>
  <br>
  <br>
</p>

## Table of contents
- [Overview](#overview)
- [Rules](#rules)
- [Design](#design)

## Overview
For my 2020 application, I built a two player poker game that can be played on the terminal! I hope you guys enjoy the game. I'm super excited to apply for the KPCB fellowship and to get a chance to join the community!

## Rules
The specific game has the following set of rules: 
1. Each round has a 5 dollar buy in
2. Each player starts off with 100 dollars
3. Players can use rerolls. A reroll is an instance where a player can get certain cards in the hand replaced with new randomly selected cards.

Each round goes as follow. Player1 and Player2 are both dealt 5 cards. Player1 goes first. Player1 can either keep his hand or use a reroll. Player1 has the option to pay for any additional rerolls. If he declines, Player1 can fold, raise, or stay. This marks the end of Player1's turn. Player2's turn starts now. Player2 gets all the same options as Player1. If Player2 does not match Player1's raise, he loses automatically. Once both players have finished, we compare the players hands using traditional poker rules, and whoever wins wins the pot!

If you're curious about more rules about draw poker, take a look here: [Draw Poker](https://en.wikipedia.org/wiki/Draw_poker)

## Design
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
