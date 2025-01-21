public class TE extends Adventurer{
    int muscle, maxMuscle;

    /*the other constructors ultimately call the constructor
    *with all parameters.*/
    public TE(String name, int hp){
      super(name,hp);
      maxMuscle = 18;
      muscle = 0;
    }

    public TE(String name){
      this(name,100);
    }

    public TE(){
      this("Kelce (TE)");
    }

    /*The next 8 methods are all required because they are abstract:*/
    public String getSpecialName(){
      return "Muscle";
    }

    public int getSpecial(){
      return muscle;
    }

    public void setSpecial(int n){
      muscle = n;
    }

    public int getSpecialMax(){
      return maxMuscle;
    }

    /*Deal 10 damage to opponent, restores 10 Strength*/
    public String attack(Adventurer other){
      int damage = (int)(Math.random()*7)+7;
      other.applyDamage(damage);
      restoreSpecial(3);
      return this + " ran a curl route "+ other + " and shamed them into "+ damage +
      " points of damage. They then gained muscle.";
    }

    /*Deal 30 damage to opponent, only if strength is high enough.
    *Reduces strength by 25.
    */
    public String specialAttack(Adventurer other){
      if(getSpecial() >= 6){
        setSpecial(getSpecial()-6);
        int damage = 16;
        other.applyDamage(damage);
        other.setSpecial(other.getSpecial()-5);
        return this + " used their muscles to block their oponent."+
        " This stopped  "+other+" dealing "+ damage +" points of damage. They then laughed at them, making them lose 5 aura (special)";
      }else{
        return "Not enough strength to sack. Instead "+attack(other);
      }

    }
    /*Restores 10 special to other and give 20 HP if strength is more than 30*/
    public String support(Adventurer other){
    if(getSpecial() >= 30){
    other.setHP(other.getHP() + 10);
    this.setSpecial(getSpecial()-2);
      return "Gives a checkdown to "+other+" and restores "
      + other.restoreSpecial(10)+" "+other.getSpecialName();
    }
    else{
        return "Not enough strength to support ally. Instead " + support();
    }
}
    /*Restores half of strength to health to self.*/
    public String support(){
      int hp = muscle/2;
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
