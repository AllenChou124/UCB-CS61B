
public class NBody{

private static String starField = "./images/starfield.jpg";

      public static double readRadius(String path){
          In in = new In(path);
          in.readDouble();
          double radius = in.readDouble();
          return radius;
      }

    public static Body[] readBodies(String path){
      In in = new In(path);

      int number = in.readInt();
      Body[] Bodies = new Body[number];
      for(int i = 0; i<number; i++){
        Bodies[i] = new Body();
      }
      in.readDouble();
      int j = 0;
      while(!in.isEmpty()){
        for(j = 0; j<number; j++){
          Bodies[j].xxPos = in.readDouble();
          Bodies[j].yyPos = in.readDouble();
          Bodies[j].xxVel = in.readDouble();
          Bodies[j].yyVel = in.readDouble();
          Bodies[j].mass  = in.readDouble();
          Bodies[j].imgFileName = in.readString();
          }
          if(j == number){
            break;
          }
    }
    return Bodies;
  }

  public static void main(String[] args){
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    double radius = readRadius(filename);

    Body[] Bodies = readBodies(filename);
    StdDraw.setScale(-radius, radius);

    for (Body b : Bodies) {
        b.draw();
    }

    double timer = 0.0;
    while(timer < T){
      double[] xForce = new double[Bodies.length];
      double[] yForce = new double[Bodies.length];

      for(int i=0;i<Bodies.length;i++){
        xForce[i] = Bodies[i].calcNetForceExertedByX(Bodies);
        yForce[i] = Bodies[i].calcNetForceExertedByY(Bodies);
      }

      for(int i=0;i<Bodies.length;i++){
        Bodies[i].update(dt,xForce[i],yForce[i]);

      }

      StdDraw.picture(0, 0, starField);

      for (Body b : Bodies) {
          b.draw();
      }

      StdDraw.show();
      StdDraw.pause(40);

      StdDraw.clear();

      timer = timer + dt;

    }
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bodies.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                      bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   

  }




}
