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
and the other interactable entities are denoted by other symbols. The entities on the map are randomly generated in each
play through of the game.

### The Player
At all times, the player's health, money and exp is displayed at the top of the screen. Dropping the player's health 
to 0 will cause the player to die and the game is over. 

### The Text Box
At all times, on the bottom part of the screen, a text box will be visible and it will be visible. The text box informs
the player of narrations from the narrator, dialogue from the entities, general instructions from the user interface and
also error messages when they occur.

### Movement
Traversing the map is similar to many common computer games. We use the "WASD" system such that
entering `w` to the program shifts the player up by 1 space, entering `a` shifts the player to the left,
entering `s` shifts the player down 1 space and lastly, entering `d` shifts the player to the right by 1 space.
This movement can only take place when the 2D grid map is visible.

Additionally, should the player wish to move more than 1 space at a time, the player can modify the directional commands
by adding a number in this manner.

E.g `d 10`

The above command moves the player rightwards by 10 spaces.


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


### Items

There are various consumables the player can acquire throughout the game. The items are consumable items, meaning the
item can be only used once before it is destroyed. There are 2 general types of items, the healing item and the damage 
item. Healing items will recover the player's health, if the player's health is already maxed out at 
100hp(health points), the healing item will OVER-HEAl and increase the player's health past the initial 100hp. Damage
items will empower the player's next attack to do increased damage on a successful hit. 

### Shop

Items are purchasable at the shop denoted by a '#' on the map. Interacting with the shop causes the player to enter the
shop. Just like the enemies, the player must enter `fight` to browse the shop's items. After entering the shop, buy an 
item by entering the index of the item and the item will automatically be purchased and transferred to the Inventory.
The purchase will only go through if the player has enough money. To exit the shop, the player enters `exit`.

### Inventory

The inventory of the player can only be accessed when the player is not currently interacting with an entity. To bring
up the inventory the player enters `i`. To use an item the player enters the keyword `use` followed by the index of the 
item.

E.g `use 1`

To close the inventory, the player enters `close`.

### Hints

Sometimes, hints are scattered throughout the map such that some text will display just below the map to help the 
player, hints are entirely passive and no user input is required for the hints to trigger.

### Quit

Enter `q`to quit the game. The game is automatically saved.


### Help

If you need a refresher on the controls, entering either `h` or `help` will bring up a set of instructions.

### Saving

Our program can save map when you are playing the game.  
Also, it will save the player status and your inventory items
Please Note: After buying things in shop or fighting with a monster, you have to move on the map to save your  
file.

### Reset

Should a reset be necessary, the player can completely wipe the current progress of the game and regenerate a new game 
at ANY POINT IN THE GAME by entering `reset`.






## FAQ

**Q**: Is there any prerequisite to run the application.

**A**: Java 11 needs to be installed and the jar file must be in an empty folder before running.

## Command Summary

`w` `a` `s` `d` to move around
`e` to interact
`q` to quit
`h` to print help menu
`run` to escape the battle interface
`fight` to commence a fight
`i` to bring up inventory
`close` to leave inventory
`exit` to leave the shop
`reset` to reset the entire game and start a new game.
 All math questions have integer answers.
