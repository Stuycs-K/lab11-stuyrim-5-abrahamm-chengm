public class QB extends Adventurer{
  int armPower, maxArmPower;
  String preferredLanguage;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public QB(String name, int hp, String language){
    super(name,hp);
    maxArmPower = 35;
    armPower = 0;
    preferredLanguage = language;
  }

  public QB(String name, int hp){
    this(name,hp,"c++");
  }

  public QB(String name){
    this(name,24);
  }

  public QB(){
    this("Carmack");
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

  /*Deal 3-12 damage to opponent, only if caffeine is high enough.
  *Reduces caffeine by 8.
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
  /*Restores 5 special to other*//*
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  */
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 10;
    if(getSpecial() >= 10){
      setSpecial(getSpecial()-10);
      setHP(getHP()+hp);
    return this+" takes a kneww to restore "+hp+" HP";
  }
  else{
    return "not enough power to take a knee at this current time."
  }
}
