public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static  double G=6.67*1e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
    xxPos=xP;
    yyPos=yP;
    xxVel=xV;
    yyVel=yV;
    mass=m;
    imgFileName=img;
    }

    public Planet(Planet b)
    {
        xxPos=b.xxPos;
        yyPos=b.yyPos;
        xxVel=b.xxVel;
        yyVel=b.yyVel;
        mass=b.mass;
        imgFileName=b.imgFileName;
    }
    public double calcDistance(Planet b)
    {
        double sum=(b.xxPos-xxPos)*(b.xxPos-xxPos)+(b.yyPos-yyPos)*(b.yyPos-yyPos);
        return Math.sqrt(sum);
    }
    public double calcForceExertedBy(Planet b)
    {
       double F=G*mass*b.mass/(this.calcDistance(b)*this.calcDistance(b));
       return F;
    }
    public double calcForceExertedByX(Planet b)
    {

        double dx=b.xxPos-xxPos;
        double F=this.calcForceExertedBy(b)*dx/this.calcDistance(b);
        return F;
    }
    public double calcForceExertedByY(Planet b)
    {

        double dy=b.yyPos-yyPos;
        double F=this.calcForceExertedBy(b)*dy/this.calcDistance(b);
        return F;
    }
    public  double calcNetForceExertedByX(Planet b[])
    {
        double F=0.0;
        int len=b.length;
        for(int i=0;i<len;i++)
        {
            if (this.equals(b[i]))
            {
                continue;
            }
            else
            {
                F+=this.calcForceExertedByX(b[i]);
            }
        }
        return F;

    }
    public  double calcNetForceExertedByY (Planet b[])
    {
        double F=0.0;
        int len=b.length;
        for(int i=0;i<len;i++)
        {
            if (this.equals(b[i]))
            {
                continue;
            }
            else
            {
                F+=this.calcForceExertedByY(b[i]);
            }
        }
        return F;

    }
    public  void update(double dt, double fX, double fY)
    {
    double ax=fX/mass;
    double ay=fY/mass;
    xxVel=ax*dt+xxVel;
    yyVel=ay*dt+yyVel;
    xxPos=xxVel*dt+xxPos;
    yyPos=yyVel*dt+yyPos;

    }
    public void draw()
    {
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
