public class RB extends Adventurer{
    int speed, maxSpeed;
    String preferredLanguage;
    boolean attackIncrease;
    /*the other constructors ultimately call the constructor
    *with all parameters.*/
    public RB(String name, int hp, String language){
      super(name,hp);
      speed = 0;
      maxSpeed = 60;
      preferredLanguage = language;
      attackIncrease = false;
    }

    public RB(String name, int hp){
      this(name,hp,"c++");
    }

    public RB(String name){
      this(name,80);
    }

    public RB(){
      this("Cook");
    }
    /*The next 8 methods are all required because they are abstract:*/
    public String getSpecialName(){
      return "speed";
    }

    public int getSpecial(){
      return speed;
    }

    public void setSpecial(int n){
      speed = n;
    }

    public int getSpecialMax(){
      return maxSpeed;
    }

    /*Deal 5-10 damage to opponent, restores 10 speed*/
    public String attack(Adventurer other){
      int damage = (int)(Math.random()*6)+5;
      other.applyDamage(damage);
      if (attackIncrease){
        other.applyDamage(damage * 0.20);
      }
      restoreSpecial(10);
      return this + " rushed "+ other + " and dealt "+ damage +
      " points of damage. They then get more warmed up and gain speed.";
    }

    /*Deal 15 damage to opponent, only if speed is high enough.
    *Reduces speed by 25.
    */
    public String specialAttack(Adventurer other){
      if(getSpecial() >= 25){
        setSpecial(getSpecial()-25);
        int damage = 15;
        other.applyDamage(damage);
        if (attackIncrease){
            other.applyDamage(damage*0.2);
        }
        setHP(getHP()+15);
        return this + "used their speed to truck through the enemy." +
        " This hurt "+other+" dealing "+ damage +" points of damage." +"Also " +
        "get a health increase of 5. However, self lost 25 speed.";
      }else{
        return "Not enough speed to truck. Instead "+attack(other);
      }

    }
    /*cannot support other*/
    public String support(Adventurer other){
      return "Nothing happenening here. Instead "
      + support();
    }
    /*Gain damage increase based off of health.*/
    public String support(){
      if(getSpecial() >= 25){
      attackIncrease = true;
      return this+" drinks gatorade to increase attack power.";
      }
      else{
        return this + "does nothing.";
      }
    }
  }
