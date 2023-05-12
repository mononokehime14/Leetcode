package oa;

public class minyiCitadelOA2022 {
    // points Belong to check point inside triangle
    public static int pointsBelong(int x1, int y1, int x2, int y2, int x3, int y3, int xp, int yp, int xq, int yq) {
        double ab = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
        double bc = Math.sqrt(Math.pow(Math.abs(x2 - x3), 2) + Math.pow(Math.abs(y2 - y3), 2));
        double ac = Math.sqrt(Math.pow(Math.abs(x1 - x3), 2) + Math.pow(Math.abs(y1 - y3), 2));
        if(ab + bc <= ac || ab + ac <= bc || bc + ac <= ab) return 0;
        double areaT = area(x1,  y1,  x2,  y2,  x3,  y3);
        double area1 = area( x1,  y1,  xp,  yp,  x3,  y3);
        double area2 = area( x1,  y1,  x2,  y2,  xp,  yp);
        double area3 = area( xp,  yp,  x2,  y2,  x3,  y3);
        boolean pIn = area1 + area2 + area3 == areaT;
        area1 = area( xq,  yq,  x2,  y2,  x3,  y3);
        area2 = area( x1,  y1,  xq,  yq,  x3,  y3);
        area3 = area( x1,  y1,  x2,  y2,  xq,  yq);
        boolean qIn = area1 + area2 + area3 == areaT;
        if(pIn && !qIn) return 1;
        if(!pIn && qIn) return 2;
        if(pIn && qIn) return 3;
        return 4;
    }
    public static double area(int x1, int y1, int x2, int y2, int x3, int y3)
    {
       return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
    }
}
