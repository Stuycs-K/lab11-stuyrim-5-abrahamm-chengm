public class MahomesBoss extends Adventurer{
    int refBlindness, maxRefBlindness;
    String preferredLanguage;
    
    /*the other constructors ultimately call the constructor
    *with all parameters.*/
    public MahomesBoss(String name, int hp, String language){
      super(name,hp);
      refBlindness = 0;
      maxRefBlindness = 76;
      preferredLanguage = language;
      
    }

    public MahomesBoss(String name, int hp){
      this(name,hp,"c++");
    }

    public MahomesBoss(String name){
      this(name,150);
    }

    public MahomesBoss(){
      this("Mighty Mahomo");
    }
    /*The next 8 methods are all required because they are abstract:*/
    public String getSpecialName(){
      return "Ref Blindness";
    }

    public int getSpecial(){
      return refBlindness;
    }

    public void setSpecial(int n){
      refBlindness = n;
    }

    public int getSpecialMax(){
      return maxRefBlindness;
    }

    /*Deal 15 damage to opponent, restores 4 speed*/
    public String attack(Adventurer other){
      int damage = 15;
      other.applyDamage(damage);
      restoreSpecial(4);
      return this + " flopped and dealt "+ damage +
      " points of damage. They then pay the refs and gain refBlindness.";
    }

    /*Deal 20 damage to opponent and gain 20 HP, only if refBlindness is high enough.
    *Reduces caffeine by 20.
    */
    public String specialAttack(Adventurer other){
      if(getSpecial() >= 20){
        setSpecial(getSpecial()-20);
        int damage = 20;
        other.applyDamage(damage);
        setHP(getHP()+20);
        return this + "used their riggedness to get unfair calls on the enemy." +
        " This hurt "+other+" dealing "+ damage +" points of damage." +"Also " +
        "get a health increase of 5. However, self lost 25 refBlindness.";
      }else{
        return "Not enough blindness to cheat. Instead "+attack(other);
      }

    }
    /*cannot support other*/
    public String support(Adventurer other){
      return "Nothing happenening here. Instead "
      + support();
    }
    /*Gain health if enough blidness.*/
    public String support(){
      if(getSpecial() >= 20){
        setHP(getHP() + 30);
      return this+" gets unfair holding call to increase health.";
      }
      else{
        return this + " does nothing.";
      }
    }
  }

