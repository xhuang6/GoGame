/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gogame;

/**
 *
 * @author Xiying Huang
 */


import java.awt.*;
import java.util.ArrayList;


public class monteCarloMethod {
    static int boardLineCount = 17;
    int userFlag;
    int testInt[][] = new int[boardLineCount][boardLineCount];
    int testStones[][] = new int[200][2];//to save the coordinate of every step
    int testSteps=0;//the number of step
    int comWin=0;
    int liberty[][] = new int[boardLineCount][boardLineCount];
    int pointInt[][] = new int[boardLineCount][boardLineCount];
    int round = 1;
    boolean pass = false;//ture means one color pass
    boolean isKo = false;//ture is Ko, false is not


    public void test() {
        int i, n=200;
            for(i=0;i<n;i++){
                Point tp = getComputerPoint();
                if(tp!=null){
                    putOnTestBoard(tp);
                    userFlag = userFlag*-1;
                }else{
                    if(pass){
                        break;
                    }
                    pass = true;
                    testStones[testSteps][0]=0;
                    testStones[testSteps][1]=0;
                    testSteps++;
                    userFlag = userFlag*-1;
                }
            }
        finish();
    }


    private Point getComputerPoint() {
        int x,y;
            int n=0,m=0;
            boolean suicide=false;
            do{
                suicide=false;
                m=0;
                do{
                    x=(int)(Math.random()*9+4);
                    y=(int)(Math.random()*9+4);
                    m++;
                }while(m<50 && (testInt[x][y]!=0 || (!checkAvailable(x,y,userFlag) && checkEat(x,y,userFlag).isEmpty())));
                if (m<50){
                    if(x==4 && y ==4){
                        if(testInt[4][5]+testInt[5][5]+testInt[5][4]==3*userFlag)
                            suicide=true;
                    }else if(x==4 && y==12){
                        if(testInt[4][11]+testInt[5][11]+testInt[5][12]==3*userFlag)
                            suicide=true;
                    }else if(x==12 && y==4){
                        if(testInt[12][5]+testInt[11][5]+testInt[11][4]==3*userFlag)
                            suicide=true;
                    }else if(x==12 && y==12){
                        if(testInt[12][11]+testInt[11][11]+testInt[11][12]==3*userFlag)
                            suicide=true;
                    }else{
                        if(x==4){
                            if(testInt[4][y+1]+testInt[4][y-1]+testInt[5][y+1]+testInt[5][y-1]+testInt[5][y]==5*userFlag)
                                suicide=true;
                        }else if(x==12){
                            if(testInt[12][y+1]+testInt[12][y-1]+testInt[11][y+1]+testInt[11][y-1]+testInt[11][y]==5*userFlag)
                                suicide=true;
                        }else if(y==4){
                            if(testInt[x+1][4]+testInt[x-1][4]+testInt[x+1][5]+testInt[x-1][5]+testInt[x][5]==5*userFlag)
                                suicide=true;
                        }else if(y==12){
                            if(testInt[x+1][12]+testInt[x-1][12]+testInt[x+1][11]+testInt[x-1][11]+testInt[x][11]==5*userFlag)
                                suicide=true;
                        }else{
                            if(testInt[x][y+1]+testInt[x][y-1]+testInt[x+1][y]+testInt[x-1][y]==4*userFlag &&
                                    (testInt[x+1][y+1]+testInt[x+1][y-1]+testInt[x-1][y+1]==3*userFlag ||
                                     testInt[x+1][y+1]+testInt[x+1][y-1]+testInt[x-1][y-1]==3*userFlag ||
                                     testInt[x+1][y+1]+testInt[x-1][y+1]+testInt[x-1][y-1]==3*userFlag ||
                                     testInt[x+1][y-1]+testInt[x-1][y+1]+testInt[x-1][y-1]==3*userFlag))
                                suicide=true;
                        }
                    }
                    if(suicide){
                        n++;
                        testInt[x][y]=0;
                    }
                }else{
                    return null;
                }
                if(n>10){
                    return null;
                }
            }while(suicide);
            return new Point(x, y);
    }

    private ArrayList checkEat(int i, int j, int userFlag) {
        int a=i,b=j;
            ArrayList   stonesDead   =   new   ArrayList();
            ArrayList   temp   =   new   ArrayList();
            temp = null;
            int n;
            if(testInt[a+1][b]!=-userFlag && testInt[a][b+1]!=-userFlag && testInt[a-1][b]!=-userFlag && testInt[a][b-1]!=-userFlag)
                return stonesDead;
            a=i+1;
            if(testInt[a][b]==-userFlag)
            {
                temp = findEatPart(a, b, userFlag);
                if (temp != null)
                {
                    for(n=0; n<temp.size();n++)
                    stonesDead.add(temp.get(n));
                }
            }
            a=i-1;
            if(testInt[a][b]==-userFlag && !stonesDead.contains(new   Point(a, b)))
            {
                temp = findEatPart(a, b, userFlag);
                if (temp != null)
                {
                    for(n=0; n<temp.size();n++)
                    stonesDead.add(temp.get(n));
                }
            }
            a=i;b=j+1;
            if(testInt[a][b]==-userFlag && !stonesDead.contains(new   Point(a, b)))
            {
                temp = findEatPart(a, b, userFlag);
                if (temp != null)
                {
                    for(n=0; n<temp.size();n++)
                    stonesDead.add(temp.get(n));
                }
            }
            b=j-1;
            if(testInt[a][b]==-userFlag && !stonesDead.contains(new   Point(a, b)))
            {
                temp = findEatPart(a, b, userFlag);
                if (temp != null)
                {
                    for(n=0; n<temp.size();n++)
                    stonesDead.add(temp.get(n));
                }
            }
            return stonesDead;
    }


    private boolean checkAvailable(int i, int j, int userFlag) {
            ArrayList   stonesVisited   =   new   ArrayList();
            int findCount;
            int countLiberty=0;
            int begin=0, end=0;
            int newx,newy;
            stonesVisited.add(new Point(i,j));
            testInt[i][j]=userFlag;
            stoneLiberty();
            do
            {
                findCount=0;
                for   (int   a   =   begin;   a   <=   end;   a++)
                {
                    Point newpoint = (Point) stonesVisited.get(a);
                    newx=newpoint.x;
                    newy=newpoint.y;
                    if(testInt[newx+1][newy]==userFlag && !stonesVisited.contains(new   Point(newx+1, newy)))
                    {
                        stonesVisited.add(new   Point(newx+1,   newy));
                        findCount   +=   1;
                    }
                    if(testInt[newx-1][newy]==userFlag && !stonesVisited.contains(new   Point(newx-1, newy)))
                    {
                        stonesVisited.add(new   Point(newx-1,   newy));
                        findCount   +=   1;
                    }
                    if(testInt[newx][newy+1]==userFlag && !stonesVisited.contains(new   Point(newx, newy+1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy+1));
                        findCount   +=   1;
                    }
                    if(testInt[newx][newy-1]==userFlag && !stonesVisited.contains(new   Point(newx, newy-1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy-1));
                        findCount   +=   1;
                    }
                }
                begin   =   end   +   1;
                end   =   end   +   findCount;
            }
            while (findCount>0);
            for(int count=0;count<stonesVisited.size();count++)
            {
                Point point= (Point) stonesVisited.get(count);
                countLiberty+=liberty[point.x][point.y];
            }
            if(countLiberty!=0)
                return true;
            else
                testInt[i][j]=0;
                return false;
        }
    private ArrayList findEatPart(int a, int b, int userFlag) {
        ArrayList   stonesVisited   =   new   ArrayList();
            int findCount;
            int countLiberty=0;
            int begin=0, end=0;
            int newx,newy;
            stonesVisited.add(new Point(a,b));
            do
            {
                findCount=0;
                for   (int   i   =   begin;   i   <=   end;   i++)
                {
                    Point newpoint = (Point) stonesVisited.get(i);
                    newx=newpoint.x;
                    newy=newpoint.y;
                    if(testInt[newx+1][newy]==-userFlag && !stonesVisited.contains(new   Point(newx+1, newy)))
                    {
                        stonesVisited.add(new   Point(newx+1,   newy));
                        findCount   +=   1;
                    }
                    if(testInt[newx-1][newy]==-userFlag && !stonesVisited.contains(new   Point(newx-1, newy)))
                    {
                        stonesVisited.add(new   Point(newx-1,   newy));
                        findCount   +=   1;
                    }
                    if(testInt[newx][newy+1]==-userFlag && !stonesVisited.contains(new   Point(newx, newy+1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy+1));
                        findCount   +=   1;
                    }
                    if(testInt[newx][newy-1]==-userFlag && !stonesVisited.contains(new   Point(newx, newy-1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy-1));
                        findCount   +=   1;
                    }
                }
                begin   =   end   +   1;
                end   =   end   +   findCount;
            }
            while (findCount>0);
            for(int count=0;count<stonesVisited.size();count++)
            {
                Point point= (Point) stonesVisited.get(count);
                countLiberty+=liberty[point.x][point.y];
            }
            if(countLiberty<1 && !(stonesVisited.size()==1 && a==testStones[testSteps-1][0] && b==testStones[testSteps-1][1] && isKo))
                return stonesVisited;
            else
                return null;
    }

    private void stoneLiberty() {
        int a,b;
          for(a=4;a<13;a++)
          {
              for(b=4;b<13;b++)
              {
                 if(testInt[a][b] == 0)
                    liberty[a][b] =0;
                 else
                 {
                    if((a==4 || a==12) && (b==4 || b==12))
                    {
                        liberty[a][b] = 2;
                        if(a==4 && b==4)
                        {
                            if (testInt[a + 1][b] != 0)
                                liberty[a][b]--;
                            if (testInt[a][b + 1] != 0)
                                liberty[a][b]--;
                        }
                        else if(a == 4 && b == 12)
                        {
                            if (testInt[a + 1][b] != 0)
                                liberty[a][b]--;
                            if (testInt[a][b - 1] != 0)
                                liberty[a][b]--;
                        }
                        else if(b == 4)
                        {
                            if (testInt[a - 1][b] != 0)
                                liberty[a][b]--;
                            if (testInt[a][b + 1] != 0)
                        liberty[a][b]--;
                        }
                        else
                        {
                            if (testInt[a - 1][b] != 0)
                                liberty[a][b]--;
                            if (testInt[a][b - 1] != 0)
                                liberty[a][b]--;
                        }
                    }
                    else if(a==4 || a==12 || b==4 || b==12)
                    {
                        liberty[a][b] = 3;
                        if(a==4)
                        {
                            if(testInt[a+1][b]!=0)
                                liberty[a][b]--;
                            if(testInt[a][b+1]!=0)
                                liberty[a][b]--;
                            if(testInt[a][b-1]!=0)
                                liberty[a][b]--;
                        }
                        else if(a == 12)
                        {
                            if(testInt[a-1][b]!=0)
                                liberty[a][b]--;
                            if(testInt[a][b+1]!=0)
                                liberty[a][b]--;
                            if(testInt[a][b-1]!=0)
                                liberty[a][b]--;
                        }
                        else if(b==4)
                        {
                            if(testInt[a][b+1]!=0)
                                liberty[a][b]--;
                            if(testInt[a+1][b]!=0)
                                liberty[a][b]--;
                            if(testInt[a-1][b]!=0)
                                liberty[a][b]--;
                        }
                        else
                        {
                            if(testInt[a][b-1]!=0)
                                liberty[a][b]--;
                            if(testInt[a+1][b]!=0)
                                liberty[a][b]--;
                            if(testInt[a-1][b]!=0)
                                liberty[a][b]--;
                        }
                    }
                    else
                    {
                        liberty[a][b]=4;
                        if(testInt[a+1][b]!=0)
                            liberty[a][b]--;
                        if(testInt[a-1][b]!=0)
                            liberty[a][b]--;
                        if(testInt[a][b+1]!=0)
                            liberty[a][b]--;
                        if(testInt[a][b-1]!=0)
                            liberty[a][b]--;
                    }
                  }
              }
            }
    }

    private void putOnTestBoard(Point tp) {
        int x = tp.x;
            int y = tp.y;
            ArrayList   stonesDead   =   new   ArrayList();
            pass = false;
            stonesDead = checkEat(x,y,userFlag);
            if (stonesDead.size()==1 && liberty[x][y]==0)
                isKo = true;
            else
                isKo = false;
            if (!stonesDead.isEmpty())
            {
                testInt[x][y] = userFlag;
                eat(x,y,stonesDead);
                testStones[testSteps][0] = x;
                testStones[testSteps][1] = y;
                testSteps++;
            }
            else
            {
                testInt[x][y] = userFlag;
                testStones[testSteps][0] = x;
                testStones[testSteps][1] = y;
                testSteps++;
            }
    }

    private void eat(int x, int y, ArrayList stonesDead) {
        int i,j;
            for(int a=0;a<stonesDead.size();a++)
            {
                Point deadpoint = (Point) stonesDead.get(a);
                i = deadpoint.x;
                j = deadpoint.y;
                testInt[i][j]=0;
            }
    }

    private void countPoint() {
        ArrayList   stonesVisited   =   new   ArrayList();
            ArrayList   temp   =   new   ArrayList();
            int n,m;
            double blackPoint=0, whitePoint=1;
            int i,j;

            for(i=4;i<13;i++)
            {
                  for(j=4;j<13;j++)
                  {
                      for(m=0;m<stonesVisited.size();m++)
                      {
                          if (stonesVisited.get(m).equals(new Point(i, j))){
                              break;
                              }
                      }
                      if (testInt[i][j] == 0) {
                          temp = checkState(i,j);
                          for(n=0; n<temp.size();n++){
                            stonesVisited.add(temp.get(n));
                          }

                        }
                      else if(testInt[i][j] == 1)
                      {
                          stonesVisited.add(new   Point(i,j));
                          pointInt[i][j] = 1;

                      }
                      else if(testInt[i][j] == -1)
                      {
                          stonesVisited.add(new   Point(i,j));
                          pointInt[i][j]=-1;

                      }
                  }
            }
            for(n=4;n<13;n++)
            {
                  for(m=4;m<13;m++)
                  {
                      if (pointInt[n][m]==1)
                          blackPoint++;
                      else if (pointInt[n][m]==-1)
                          whitePoint++;
                }
            }
            if(blackPoint<whitePoint)
                comWin++;
    }

    private ArrayList checkState(int a, int b) {
            ArrayList   stonesVisited   =   new   ArrayList();
            int boradState = 2;//-1 means white stone, 1 means black stone, 0 means empty, 2 is the start stage.
            int findCount;
            int begin=0, end=0;
            int newx,newy;
            stonesVisited.add(new Point(a,b));
            do
            {
                findCount=0;
                for   (int   i   =   begin;   i   <=   end;   i++)
                {
                    Point newpoint = (Point) stonesVisited.get(i);
                    newx=newpoint.x;
                    newy=newpoint.y;
                    if(testInt[newx+1][newy]==0 && !stonesVisited.contains(new   Point(newx+1, newy)))
                    {
                        stonesVisited.add(new   Point(newx+1,   newy));
                        findCount   +=   1;
                    }else if((testInt[newx+1][newy]==1 || testInt[newx+1][newy]==-1) && !stonesVisited.contains(new   Point(newx+1, newy))){
                        if (boradState ==2){
                            boradState = testInt[newx+1][newy];
                        }else if(boradState == -testInt[newx+1][newy]){
                            boradState = 0;
                        }
                    }
                    if(testInt[newx-1][newy]==0 && !stonesVisited.contains(new   Point(newx-1, newy)))
                    {
                        stonesVisited.add(new   Point(newx-1,   newy));
                        findCount   +=   1;
                    }else if((testInt[newx-1][newy]==1 || testInt[newx-1][newy]==-1) && !stonesVisited.contains(new   Point(newx-1, newy))){
                        if (boradState ==2){
                            boradState = testInt[newx-1][newy];
                        }else if(boradState == -testInt[newx-1][newy]){
                            boradState = 0;
                        }
                    }
                    if(testInt[newx][newy+1]==0 && !stonesVisited.contains(new   Point(newx, newy+1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy+1));
                        findCount   +=   1;
                    }else if((testInt[newx][newy+1]==1 || testInt[newx][newy+1]==-1) && !stonesVisited.contains(new   Point(newx, newy+1))){
                        if (boradState ==2){
                            boradState = testInt[newx][newy+1];
                        }else if(boradState == -testInt[newx][newy+1]){
                            boradState = 0;
                        }
                    }
                    if(testInt[newx][newy-1]==0 && !stonesVisited.contains(new   Point(newx, newy-1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy-1));
                        findCount   +=   1;
                    }else if((testInt[newx][newy-1]==1 || testInt[newx][newy-1]==-1) && !stonesVisited.contains(new   Point(newx, newy-1))){
                        if (boradState ==2){
                            boradState = testInt[newx][newy-1];
                        }else if(boradState == -testInt[newx][newy-1]){
                            boradState = 0;
                        }
                    }
                }
                begin   =   end   +   1;
                end   =   end   +   findCount;
            }
            while (findCount>0 );
            for(int count=0;count<stonesVisited.size();count++)
            {
                Point point= (Point) stonesVisited.get(count);
                pointInt[point.x][point.y]=boradState;
            }
            return stonesVisited;
    }


private void finish() {
            countPoint();
            round++;
    }

private void begin(){
    userFlag = 1;
    testSteps=0;
    testStones = null;
    testStones = new int[200][2];
    pass = false;
    isKo = false;
    for (int i = 0; i < testInt.length; i++) {
        for (int j = 0; j < testInt[i].length; j++) {
            if(i>=4 && i<testInt.length-4 &&j>=4 && j<testInt.length-4){
                testInt[i][j] = 0;
            }else{
                testInt[i][j]=10;
            }
        }
    }
}


   public int monteCarlomethod(int[][] stones, int steps) {
        int i;
        int testFlag=1;
        while(round<=5){
            begin();
            for(i=0; i<steps;i++){
                testStones[i][0]=stones[i][0];
                testStones[i][1]=stones[i][1];
                testInt[stones[i][0]][stones[i][1]]= testFlag;
                testFlag = -1*testFlag;
                testSteps++;
            }
            test();
        }
        return comWin;
    }

    



}
