public class NBody {
    static public double readRadius(String s)
    {
        In in =new In(s);
        int f=in.readInt();
        double result=in.readDouble();
        return result;
    }
    static public Planet[] readPlanets(String s)
    {
        In in=new In(s);
        int f=in.readInt();
        double result=in.readDouble();
        Planet b[]=new Planet[f];//对象数组默认初始化是Null
        for (int i = 0; i < f; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            b[i] = planet;
        }

        return b;
    }

    static public void main(String[] args) {

        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=readRadius(filename);
        Planet []planets=readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        int len=planets.length;
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(int i=0;i<len;i++)
        {
            planets[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        double time =0.0;
        while(time!=T)
        {
            double[] xForces=new double[len];
            double[] yForces=new double[len];
            for(int i=0;i<len;i++)
            {
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<len;i++)
            {
                planets[i].update(dt,xForces[i],yForces[i]);

            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i=0;i<len;i++)
            {
                planets[i].draw();
            }
            StdDraw.show();
            int waitTimeMilliseconds = 10;
            StdDraw.pause(waitTimeMilliseconds);
            time+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
