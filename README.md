<p align="center">
  <a>
    <img src="https://getbootstrap.com/docs/4.3/assets/brand/bootstrap-solid.svg" alt="KPCB logo" width="130" height="85">
  </a>
</p>

<h3 align="center">Draw Poker</h3>

<p align="center">
  My Draw Poker Console Game for the Kleiner Perkins 2020 Engineering Fellowship.
  <br>
  <a href="https://youtu.be/1-3oso1MPsc"><strong>Video Demonstration of My Project »</strong></a>
  <br>
  <br>
</p>

## Table of contents
- [Overview](#overview)
- [Instructions](#instructions)
- [Rules](#rules)
- [Design](#design)

## Overview
For my 2020 application, I built a two player poker game that can be played on the terminal! I hope you guys enjoy the game. I'm super excited to apply for the KPCB fellowship and to get a chance to join the community!

## Instructions 
To run, navigate to the Poker/Game.java file and run Game.java

## Rules
The specific game has the following set of rules: 
1. Each round has a 5 dollar buy in
2. Each player starts off with 100 dollars
3. Players can use rerolls. A reroll is an instance where a player can get certain cards in the hand replaced with new randomly selected cards.

Each round goes as follow. Player1 and Player2 are both dealt 5 cards. Player1 goes first. Player1 can either keep his hand or use a reroll. Player1 has the option to pay for any additional rerolls. If he declines, Player1 can fold, raise, or stay. This marks the end of Player1's turn. Player2's turn starts now. Player2 gets all the same options as Player1. If Player2 does not match Player1's raise, he loses automatically. Once both players have finished, we compare the players hands using traditional poker rules, and whoever wins wins the pot!

If you're curious about more rules about draw poker, take a look here: [Draw Poker](https://en.wikipedia.org/wiki/Draw_poker)

## Design
OO Design: 
- Class for decks. The deck class keeps track of 52 cards. Decks can be shuffled. Cards can be dealt from decks. 
- Class for cards. The card holds a certain value and is of a certain suit. The card class has a custom print method.
- Class for player. The player has access to his money and his cards. He can make appropriate actions.
- Class for scorer. The scorer compares the hands of the two players and outputs a winner.
- Class for game. Game is the juice of this project, running most of the flow.
- Class for testing. A JUnit test class to ensure that scorer is determining the correct winners.


Data Structure/Variable Usage: 
- I used a LinkedList for deck because of the constant peeking, popping, and pushing

- I used a set to determine if there were straights.

- I created a Constants class for common variables, suits, symbols, etc

- I defined variables to keep track of highest value/second highest value to distinguish between different pair/triple/quad cases
