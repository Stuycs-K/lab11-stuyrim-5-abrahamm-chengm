[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

Make a clear list of features that work/dont work

:white_check_mark: This feature works.

:question: This feature works partially.

:ballot_box_with_check: This extra (beyond the things that the lab was supposed to do) feature works.

:x: This required feature does not work.

:beetle: This is a bug that affects the game.


## Adventurer Subclasses

### <ins>Quarterback</ins>
- Initial Arm Power: **0**
- Max Arm Power: **35**
- Initial HP: **60 HP**
- HP: **60 MAX**
- Attack: **Pass**: Deals 10 to 15 damage to enemy and also increases self Arm Power by 5
- Support: **Take a Knee**: ***lose 10 Arm Power*** but heals self for 10 HP
- Support (Ally): **Protect the QB**: **Self loses 10 Arm Power** Heal the reciever for 10 HP; 
- Special Attack: **Hail Mary** ***(Requires 20 Arm Power)***: Deals 30 damage to enemy but self loses 20 Arm Power
- Special Resource: <ins>Arm Power</ins>
### <ins>Running Back</ins>
- Initial Speed: **0**
- Max Speed: **60**
- Initial HP: **80 HP**
- HP: **80 MAX**
- Attack: **Rush**: Deals 5 to 10 damage to enemy and increases self speed by 10
- Support: **Gatorade sip**: ***give self 10% damage increase if below 40 HP***, ***otherwise only <ins>5%</ins> damage increase*** ; ***Need 25 speed to use*** ; If used remove 25 Speed from self; Damage boost does not stack
- Support(ally): **Cannot support ally so uses regular support instead**.
- Special Attack: **Truck** ***(Requires 25 Speed)***: Deals 15 damage to enemy but self lose 25 Speed; If not enough speed, attack instead; **Also gains 15 health.**
- Special Resource: <ins>Speed</ins>
### <ins>Defensive Line</ins>
- Initial Strength: **0**
- Max Strength: **60**
- Initial HP: **100 HP**
- HP: **100 HP MAX**
- Attack: **Tackle**: Deals 10 damage to enemy and increases self strength by 10;
- Support: **Sideline Rest**: Self converts <ins>half</ins> of current strength to HP, adds that number to the HP and then reset self strength to **zero**
- Support (Ally): **Protect**: Heal the protected for 20 HP and give him 10 super charge; Self loses 30 Strength. If not enough strength, self support instead.
- Special Attack:  **Sack** ***(Requires 25 Strength)***: Deals 30 damage to enemy but self loses 25 Strength
- Special Resource: <ins>Strength</ins>
### <ins>Mahomes the Boss</ins>
- Initial Ref Blindness: **0**
- Max Ref Blindness: **76**
- Initial HP: **150 HP**
- HP: **150 HP MAX**
- Attack: **Flopping**: Deals 15 damage to enemy and also increases ref blindess by 4
- Support: **Holding Call**: ***lose 20 Ref Blindness*** but heals self for 30 HP
- Support (Ally): None
- Special Attack: **Unneccesary Roughness** ***(Requires 20 Ref Blindness)***: Deals 20 damage to enemy and gain 20 health but self loses 20 Ref Blindness
- Special Resource: <ins>Ref blindness</ins>