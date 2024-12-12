# DnDbot
A bot designed to simplify gameplay for Dungeons & Dragons. 
It allows you to generate cities, classes, names, encounters, 
and perform dice rolls. The bot supports both console mode and 
graphical user interface (GUI) mode.
## GUI
![Alt text](https://i.imgur.com/CKGuxjJ.png)
## Commands for console:
| Command                                                                                         | Action                                                                                  |
|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| help                                                                                            | Displays a list of all available commands.                                              |
| generate_name                                                                                   | Generates a random name suitable for characters or NPCs.                                |
| generate_city                                                                                   | Generates a random city with various characteristics.                                   |
| generate_class                                                                                  | Generates a random character class.                                                     |
| roll 'formula'                                                                                  | Rolls dice based on a specified formula.                                                |
| d20 'modifier' 'normal/advantage/disadvantage'                                                  | Rolls a 20-sided die with a modifier and an option for advantage/disadvantage.          |
| generate_encounter 'NORMAL/MEDIUM/HARD' 'PLAYERS COUNT' 'PLAYERS LEVEL'                         | Generates a random encounter based on difficulty, number of players, and player levels. |
| generate_encounter_filter 'NORMAL/MEDIUM/HARD' 'PLAYERS COUNT' 'PLAYERS LEVEL' 'MONSTER FILTER' | Generates an encounter with a specific monster type filter.                             |
| encounter_end                                                                                   | Ends the current encounter.                                                             |
| attack 'monster index' 'damage'                                                                 | Attacks a specified monster, applying the given damage amount.                          |