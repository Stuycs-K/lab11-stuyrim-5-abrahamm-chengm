public class DL extends Adventurer{
    int strength, maxStrength;
    String preferredLanguage;

    /*the other constructors ultimately call the constructor
    *with all parameters.*/
    public DL(String name, int hp, String language){
      super(name,hp);
      maxStrength = 60;
      strength = 0;
      preferredLanguage = language;
    }

    public DL(String name, int hp){
      this(name,hp,"c++");
    }

    public DL(String name){
      this(name,100);
    }

    public DL(){
      this("Dawkins (DL)");
    }

    /*The next 8 methods are all required because they are abstract:*/
    public String getSpecialName(){
      return "Strength";
    }

    public int getSpecial(){
      return strength;
    }

    public void setSpecial(int n){
      strength = n;
    }

    public int getSpecialMax(){
      return maxStrength;
    }

    /*Deal 10 damage to opponent, restores 10 Strength*/
    public String attack(Adventurer other){
      int damage = 10;
      other.applyDamage(damage);
      restoreSpecial(10);
      return this + " tackled "+ other + " and dealt "+ damage +
      " points of damage. They then gain strength.";
    }

    /*Deal 30 damage to opponent, only if strength is high enough.
    *Reduces strength by 25.
    */
    public String specialAttack(Adventurer other){
      if(getSpecial() >= 25){
        setSpecial(getSpecial()-25);
        int damage = 30;
        other.applyDamage(damage);
        return this + " used their strength to sack the opponent."+
        " This hurt  "+other+" dealing "+ damage +" points of damage.";
      }else{
        return "Not enough strength to sack. Instead "+attack(other);
      }

    }
    /*Restores 10 special to other and give 20 HP if strength is more than 30*/
    public String support(Adventurer other){
    if(getSpecial() >= 30){
    other.setHP(other.getHP() + 10);
    this.setSpecial(getSpecial()-30);
      return "Gives a heal to "+other+" and restores "
      + other.restoreSpecial(10)+" "+other.getSpecialName();
    }
    else{
        return "Not enough strength to support ally. Instead " + support();
    }
}
    /*Restores half of strength to health to self.*/
    public String support(){
      int hp = strength/2;
      if(getHP() < getmaxHP() - hp){
      setHP(getHP()+hp);
      return this+" rests to restore " +hp+" HP.";
      }
      else{
        setHP(getmaxHP());
        return this+ " rests to get back to max hp";
      }
    }
  }
