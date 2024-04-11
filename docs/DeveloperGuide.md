# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

### Architecture

Given below is a higher-level overview of the main components for the app to work.

```Main``` has methods which are responsible for:
1. App launch: Initialises the various classes needed and starting up the game
2. App running: Calls the various methods in other Classes to run the game
3. App shutdown: Saves the game state

listed below are a collection of classes used by multiple components which will be generalised as ```Commons```.
1. ```TextBox``` which is used to set all the messages and narrations for the user.
2. ```FileReader``` to read our design.txt files in order to print certain screens.
3. ```PlayerStatus``` which stores the status and inventory of the player.

```Ui``` responsible for displaying the game's UI, interactions and narrations to the user.<br>
```Storage``` responsible for saving the current state of the game when quitting the app.<br>
```Parser``` is a collection of classes that converts the user's commands and starts the command execution process. <br>
<br>
Below is how some of the architecture components would interact with each other when the user inputs the command to move. 
![architecture_sequence_diagram](../picture/ArchitectureSequenceDiagram.png)
The section below gives more details of each component.
### UI Component

### Parser

##  Implementation

### User Command Component

User can type command to do things on the Map. 

If the user type the command, `parseCommand` function will be called. After that, the original input will be
analyzed by `analyseCommand` function to see if it matches a kind of `CommandType`. Then, we will return new
Command back to the main based on the command type. The Final step is to call the `execute` function. If the command
is the type of `fightCommand`, we will call the execute function with one parameter `Scanner`. For all other
conditions, we will call the execute function with no parameter.

### Map Component

The API of this component is defined in BaseMap.java.

Each map instance consists of a `currentMap` which stores a 2-dimensional array of characters which represents the 
printed map for the player. All maps will come with a given `height` and `width`, all of these attributes are inherited
from the AMap abstract class. Currently, the `FirstMap` and `BattleInterface` classes
extend AMap. `FirstMap` is the first map displayed upon entering the game and it displays the position of the player. 
The `BattleInterface` is the map displayed when the player interacts with an `interactable`. The following image
shows the architecture of the Map component

![Map UML](https://raw.githubusercontent.com/AY2324S2-CS2113-W12-3/tp/master/picture/Map.png)

At the moment the `MapGenerator` class allows the positions of enemies to be placed randomly throughout instances of
`FirstMap` only. 

The reason why the player's map(FirstMap), the shop's interface and the battle interface all extend off of the `BaseMap`
class is because during the game loop, these maps are being cycled through as the main screen the user will view.

### Interacting with Environment Component

The API of this component is defined in InteractingCommand.java

![Interaction UML](https://raw.githubusercontent.com/AY2324S2-CS2113-W12-3/tp/master/picture/Interaction.png)

This component happens when the user chooses to key the interact command ```e```. <br>
Here is how it works:
1. When the user chooses to fight, the command is parsed.
2. The ```CalculaChroniclesOfTheAlgorithmicKingdom``` component then calls the execute() method in ```InteractingCommand```.
3. It executes the method and creates other objects like ```Enemy``` and ```ShopKeaper``` components which are responsible for the 
entity classes in the game and also ```BattleInterface``` and ```ShopMap``` which are responsible for displaying these entities among other things.
4. The ```BattleInterface``` or ```ShopKeeper``` will then read from a .txt file to store their displays by creating the ```FileReader``` object and 
running their respective methods.
5. These displays, textboxes and player status will then be subsequently printed by the ```CalculaChroniclesOfTheAlgorithmicKingdom``` object.

### Battling Component

The API of this component is defined in FightCommand.java.

![BattleInterface UML](https://raw.githubusercontent.com/AY2324S2-CS2113-W12-3/tp/master/picture/BattleInterface.png)

This component occurs when the user chooses to fight an enemy after interacting with it using the command 
```f``` or ```fight```. <br>
Here is how it works:
1. When the user chooses to fight, the command is parsed.
2. The ```CalculaChroniclesOfTheAlgorithmicKingdom``` object then calls the execute() method in ```FightCommand``` and enables the fighting.
3. The ```MathPool``` object is created, which is responsible for the math questions to answer and another ```Ui``` object is created to interact with the user.
4. In the enableFight() method, it has a loop which asks the user math questions to answer until the player or enemy dies. The player takes damage for 
every wrong answer and deals damage to the enemy for every correct answer.
5. In this loop, there is another loop to parse the answer given by the user, and displays an error message  and the same math question
until the user gives an answer which is a valid integer.
6. Once either the player or enemy dies, it then exits and runs the relevant checks to eventually print the output to be shown to 
the user after battle, handled by ```CalculaChroniclesOfTheAlgorithmicKingdom```.

### Item Usage Component

The API of the following component is defined in OpenInventoryCommand.java.
![OpenInventory_UML](../picture/OpenInventory.png)
This component occurs when the user decides to open up the inventory. The user opens up the inventory using the command ```i```
or ```inventory```.<br>
Here is how it works:
1. When the user chooses to open the inventory, the command is parsed.
2. The ```CalculaChroniclesOfTheAlgorithmicKingdom``` object then calls the execute() method in ```OpenInventoryCommand```to get
the inventory from the stored maps in ```BaseMap```.
3. The inventory would then be printed on the Ui for display.

The API of the following component is defined in UseCommand.java.
![UseItem_UML](../picture/ItemUsage.png)
This component occurs when the user decides to use an item after navigating to the inventory page containing consumable items.
Here is how it works:
1. When the user chooses to use an item after navigating to the consumable items page, the command is parsed.
2. The ```CalculaChroniclesOfTheAlgorithmicKingdom``` object then calls the execute() method
3. The method goes through the necessary checks to check if the item intended to use has been indicated as stated in the UserGuide.
4. If any of the checks fail, an error message would be displayed to flag out what went wrong.
5. If all the checks passes, the inventory is obtained from the ```PlayerStatus``` object. The inventory is then searched to check if it
contains the item.
6. The method useItem(item) in the ```PlayerInventory``` object is called if the item is found. Subsequently,
an error message is printed outlining the error.

## Product scope
### Target user profile

Our target users are young students who are hoping the revise their mathematical skills.

### Value proposition

It allows the target user to supplement their existing revision with a more fun and interacting way to revise their mathematics knowledge.

## User Stories

| Priority | As a ...       | I want to ...                                               | So that I can ...                                       |
|----------|----------------|-------------------------------------------------------------|---------------------------------------------------------|
| ***      | new player     | to get access to a help menu                                | refer to them when I don't know the commands to proceed |
| ***      | player         | to see a map of the play area                               | see the map and location in real-time                   |
| ***      | player         | to move around at will                                      | explore the world as I want to                          |
| ***      | player         | to have an ending to the game                               | win the game                                            |
| ***      | player         | to be able to track stats/items                             | gauge how my characters progress                        |
| ***      | player         | save my game                                                | come back and finish it when I have time                |
| ***      | player         | To have a death and restart mechanic                        | add challenge to the game                               |
| **       | player         | To be able to fight entities                                | battle in and interactive way                           |
| **       | player         | To collect items                                            | enhance my character                                    |
| **       | player         | To interact with things in the environment                  | be more immersed into the game                          |
| **       | player         | See an actual image of the characters                       | know what I am fighting against                         |
| **       | student player | To have variations in the questions asked                   | revise more stuff rather than the same questions        |
| **       | player         | to have clear distinctions between entities I interact with | have a clearer picture of what I'm doing                |
| **       | student player | refresh my knowledge of math                                | revise as I play at the same time                       |
| *        | player         | To know the background of this game                         | follow the storyline                                    |
| *        | player         | to see funny and engaging dialogue                          | enjoy the story                                         |
| *        | player         | To have access to hints to the questions                    | make calculations easier                                |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
