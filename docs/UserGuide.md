# User Guide

## Introduction

Welcome to Calcula: Chronicles of the Algorithmic Kingdom. This is a simple text based
game. Your goal in this game is to defeat the enemies within and claim victory for yourself as a 
math wizard.

## Quick Start


1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `CalculaChroniclesOfTheAlgorithmicKingdom` from [here](https://github.com/AY2324S2-CS2113-W12-3/tp/releases).

## Features 

### The Map
This game displays the character's position on a 2D grid, the player is denoted by a `P` 
and the other interactable entities are denoted by other symbols.

### Movement
Traversing the map is similar to many common computer games. We use the "WASD" system such that
entering `w` to the program shifts the player up by 1 space, entering `a` shifts the player to the left,
entering `s` shifts the player down 1 space and lastly, entering `d` shifts the player to the right by 1 space.
This movement can only take place when the 2D grid map is visible.


### Interacting 

To interact with any entity in the game the player first has to move towards the symbol and be within close proximity
to the symbol. We define close as the player needing to be directly above, below, to the left or to the right of an
entity symbol, in other words, there can be no gap in between the player and the entity and interactions cannot occur
diagonally. 

To interact the player enters `e`. 

This action will bring up a new interface called the battle interface. For now there will only be battles using this 
interface but we plan to implement shops and other entities using this interface. For now, this interface will display 
the enemy the symbol represents and gives you 2 options, to `run` or to `fight`.

### Run 

Should the player, in the battle interface, determine that the enemy is too strong, the player may choose to type `run` 
to back out of the fight and return to the main map to continue moving around.

### Fight

Should the player, in the battle interface, wish to fight the enemy. Entering `fight` in the battle interface will 
start a fight. A series of math questions will appear based on the strength of the enemy. Generally, stronger enemies
will come with harder math questions. 

The player will then enter the correct answer. The answer is guaranteed to be an integer. Getting the question correct
will cause the player to attack the enemy and getting it wrong will cause the enemy to attack the player. Getting the 
questions wrong repeatedly will deplete the player's health and once the player's health reaches 0, the player dies and 
the game is over. However, depleting the enemy's health to 0 will result in a victory and the player is rewarded with 
some gold and exp before returning the player to the main map. 

### Hints

Sometimes, hints are scattered throughout the map such that some text will display just below the map to help the player,
hints are entirely passive and no user input is required for the hints to trigger.

### Quit

Enter `q`to quit the game.


### Help

If you need a refresher on the controls, entering either `h` or `help` will bring up a set of instructions.

### Saving

Our program can save map when you are playing the game.  
Also, it will save the player status and your inventory items

### Inventory

In our map, there is always a shop. In the shop, you can buy things and use them to battle with the monster!





## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

`w` `a` `s` `d` to move around
`e` to interact
`q` to quit
`h` to print help menu
`run` to escape the battle interface
`fight` to commence a fight
 all math questions have integer answers.
