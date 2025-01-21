public class QB extends Adventurer{
  int armPower, maxArmPower;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public QB(String name, int hp, String language){
    super(name,hp);
    maxArmPower = 35;
    armPower = 0;
  }

  public QB(String name, int hp){
    this(name,hp,"c++");
  }

  public QB(String name){
    this(name,60);
  }

  public QB(){
    this("Allen (QB)");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "Arm Power";
  }

  public int getSpecial(){
    return armPower;
  }

  public void setSpecial(int n){
    armPower = n;
  }

  public int getSpecialMax(){
    return maxArmPower;
  }

  /*Deal 10-15 damage to opponent, restores 5 strength*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*6)+10;
    other.applyDamage(damage);
    restoreSpecial(5);
    return this + " passed "+ other + " and dealt "+ damage +
    " points of damage. They then gain 5 strength.";
  }

  /*Deal30 damage to opponent, only if arm power is high enough.
  *Reduces arm power by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 20){
      setSpecial(getSpecial()-20);
      int damage = 30;
      other.applyDamage(damage);
      return this + " used their arm skills to make a long pass to win the game."+
      " This severly blew out "+other+" morals dealing "+ damage +" points of damage.";
    }else{
      return "Not enough ArmPower to cluctch the game. Instead "+attack(other);
    }

  }
  /*Restores 10 special to other if enough arm power*/
  public String support(Adventurer other){
    if(getSpecial() >= 10){
      setSpecial(getSpecial() - 10);
    if(other.getHP() < other.getmaxHP() - 10){
    other.setHP(other.getHP()+10);
    return "Gives a pass to "+other+" and restores 10 HP.";
    }
    else{
      other.setHP((other.getmaxHP()));
      return "Gives a pass to " + other + " and restores back to max.";
    }
  }
  else{
    return "Not enough arm strength to heal teammate.";
  }
  }

  /*Restores 10 hp to self.*/
  public String support(){
    int hp = 10;
    if(getSpecial() >= 10){
      setSpecial(getSpecial()-10);
      if (getHP() < getmaxHP()- 10){
      setHP(getHP()+hp);
      }
      else setHP(getmaxHP());
    return this+" takes a knee to restore "+hp+" HP.";
  }
  else{
    return "Not enough power to take a knee at this current time.";
  }
}
}
