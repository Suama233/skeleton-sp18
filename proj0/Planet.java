public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){

        return Math.sqrt((p.xxPos-xxPos)*(p.xxPos-xxPos) + (p.yyPos-yyPos)*(p.yyPos-yyPos));
    }

    public double calcForceExertedBy(Planet p){
        return G*p.mass*mass/(calcDistance(p)*calcDistance(p));
    }
    public double calcExertedByX(Planet p){
        if(this.equals(p)){
            return 0;
        }
        int sign = 0;
        if(p.xxPos>xxPos){
            sign = -1;
        }else{
            sign = 1;
        }
        return sign*calcForceExertedBy(p)/calcDistance(p);
    }
}

