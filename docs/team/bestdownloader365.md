# Fang Sihan - Project Portfolio Page

## Project: CalculaChroniclesOfTheAlgorithmicKingdom

This is a simple text based game. Your goal in this game is to defeat the enemies within and claim victory   
for yourself as a math wizard. The user interacts with it using a CLI. It is written in Java 11.

### Summary of Contributions

- New Feature:
    - Added the ability to use `w`, `a`, `s`, `d` command on the map.
        - Added the ability to use `e` command to interact with shop or monsters.
        - Added the ability to use `fight` or `run` command when the user is in battle interface.
            - What it does: Created an inner loop framework in `FightingCommand` to interact with various
              monsters' interfaces
              or shop interface.
        - Added the ability to save `map`, `player status` and `inventory` data in the local disk.
            - What it does: It can save the player's playing status to ensure that they can read the archive
              and   
              return to the place where they last played the next time they enter the game.
        - Added a singleton pattern class `MapGenerator` for generating random map at the beginning of the
          game.

- Code contributed:
  [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=fang&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23)


- Project management:
    - Create and manage the release `v1.0` - `v2.1` (3 releases) on Github
    - The overall framework was written down by me and another teammate at the beginning of the project, and
      the
      framework was continuously optimized in the subsequent time.

- Enhancements to existing features:
    - Added exception handling in class `PlayerStatusStorage`
    - Added new `execute(Scanner)` method to class `Command`, made fighting with monsters and entering shop
      possible

- Documentation:
    - User Guide:
        - Added documentation for the features `Saving` and `Inventory`
    - Developer Guide:
        - Added `Parser` and `Ui` component
        - Added the implementation details of `Saving` feature

- Community:
    - Reported bugs and suggestions for other teams in the class (PE-D)

