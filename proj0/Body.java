public class Body{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass  = m;
        imgFileName = img;

  }

  public Body(Body b){
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass  = b.mass;
    imgFileName = b.imgFileName;
    }

    public Body(){
        this.xxPos = 0;
        this.yyPos = 0;
        this.xxVel = 0;
        this.yyVel = 0;
        this.mass = 0;
        this.imgFileName = "";
      }
  public  double calcDistance(Body b){
    double dis = 0;
    dis =Math.sqrt((this.xxPos-b.xxPos)*(this.xxPos-b.xxPos)+(this.yyPos-b.yyPos)*(this.yyPos-b.yyPos));
    return dis;

  }

 public double calcForceExertedBy(Body b){
   double force = 0;
   double G = 6.67e-11;
   force = G*this.mass*b.mass/(this.calcDistance(b)*this.calcDistance(b));
   return force;
 }

 public double calcForceExertedByX(Body b){
   double forceX;
   forceX = this.calcForceExertedBy(b)*(b.xxPos-this.xxPos)/this.calcDistance(b);
   return forceX;
 }

 public double calcForceExertedByY(Body b){
   double forceY;
   forceY = this.calcForceExertedBy(b)*(b.yyPos-this.yyPos)/this.calcDistance(b);
   return forceY;
 }

 public double calcNetForceExertedByX(Body[] bs){
   double netForceX = 0;
   for(Body b : bs){
     if(b.equals(this)){
         continue;
     }
     netForceX += this.calcForceExertedByX(b);
   }
   return netForceX;
   }

   public double calcNetForceExertedByY(Body[] bs){
     double netForceY = 0;
     for(Body b : bs){
       if(b.equals(this)){
           continue;
       }
       netForceY += this.calcForceExertedByY(b);
     }
     return netForceY;
     }


  public void update(double time, double Fx, double Fy){
    double axx = Fx/this.mass;
    double ayy = Fy/this.mass;
    this.xxVel += axx*time;
    this.yyVel += ayy*time;
    this.xxPos += this.xxVel*time;
    this.yyPos += this.yyVel*time;
  }

  public void draw(){
      StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }
}
