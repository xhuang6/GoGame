
package gogame;

/**
 *
 * @author Xiying Huang
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFrame;
import java.util.ArrayList;


public class Main {

    private static class GoGameMain extends JFrame implements ActionListener{
        private static final long serialVersionUID = 1L;
	static int boardLineCount = 17;
	private JButton board[][] = new JButton[boardLineCount][boardLineCount];
	int boardInt[][] = new int[boardLineCount][boardLineCount];//the statement of the stones, black is '1', white is '-1', empty is '0'
	int testChess[][] = new int[boardLineCount][boardLineCount];
        int userFlag = 1;//current color, black is '1', white is '-1'
        int liberty[][] = new int[boardLineCount][boardLineCount];
        int pointInt[][] = new int[boardLineCount][boardLineCount];
        int pointTestInt[][] = new int[boardLineCount][boardLineCount];
        int stones[][] = new int[200][2];//to save the coordinate of every step
        int steps=0;//the number of step
        int comWin=0;
        String winner;
        boolean pass = false;//ture means one color pass
	boolean userType = true;// ture Man false PC
        boolean comTurn =false;//ture is PC's turn, false is player
        boolean isKo = false;//ture is Ko, false is not
        /*set the Icons */
	String direction = "./src/gogame/Icons/";
	ImageIcon beginIcon = new ImageIcon(direction + "board.jpg");
	ImageIcon whiteIcon = new ImageIcon(direction + "white.jpg");
	ImageIcon blackIcon = new ImageIcon(direction + "black.jpg");
	ImageIcon rightIcon = new ImageIcon(direction + "rightboard.jpg");
	ImageIcon rightWhiteIcon = new ImageIcon(direction + "rightwhite.jpg");
	ImageIcon rightBlackIcon = new ImageIcon(direction + "rightblack.jpg");
	ImageIcon leftIcon = new ImageIcon(direction + "leftboard.jpg");
	ImageIcon leftBlackIcon = new ImageIcon(direction + "leftblack.jpg");
	ImageIcon leftWhiteIcon = new ImageIcon(direction + "leftwhite.jpg");
	ImageIcon upIcon = new ImageIcon(direction + "upboard.jpg");
	ImageIcon upBlackIcon = new ImageIcon(direction + "upblack.jpg");
	ImageIcon upWhiteIcon = new ImageIcon(direction + "upwhite.jpg");
	ImageIcon downIcon = new ImageIcon(direction + "downboard.jpg");
	ImageIcon downBlackIcon = new ImageIcon(direction + "downblack.jpg");
	ImageIcon downWhiteIcon = new ImageIcon(direction + "downwhite.jpg");
	ImageIcon upRightIcon = new ImageIcon(direction + "uprightboard.jpg");
	ImageIcon upRightBlackIcon = new ImageIcon(direction + "uprightblack.jpg");
	ImageIcon upRightWhiteIcon = new ImageIcon(direction + "uprightwhite.jpg");
	ImageIcon upLeftIcon = new ImageIcon(direction + "upleftboard.jpg");
	ImageIcon upLeftBlackIcon = new ImageIcon(direction + "upleftblack.jpg");
	ImageIcon upLeftWhiteIcon = new ImageIcon(direction + "upleftwhite.jpg");
	ImageIcon downRightIcon = new ImageIcon(direction + "downrightboard.jpg");
	ImageIcon downRightBlackIcon = new ImageIcon(direction
			+ "downrightblack.jpg");
	ImageIcon downRightWhiteIcon = new ImageIcon(direction
			+ "downrightwhite.jpg");
	ImageIcon downLeftIcon = new ImageIcon(direction + "downleftboard.jpg");
	ImageIcon downLeftBlackIcon = new ImageIcon(direction + "downleftblack.jpg");
	ImageIcon downLeftWhiteIcon = new ImageIcon(direction + "downleftwhite.jpg");
        ImageIcon boardBPointIcon = new ImageIcon(direction + "board_bPoint.jpg");
        ImageIcon boardWPointIcon = new ImageIcon(direction + "board_wPoint.jpg");
	ImageIcon whiteBPointIcon = new ImageIcon(direction + "white_bPoint.jpg");
        ImageIcon whiteWPointIcon = new ImageIcon(direction + "white_wPoint.jpg");
	ImageIcon blackBPointIcon = new ImageIcon(direction + "black_bPoint.jpg");
        ImageIcon blackWPointIcon = new ImageIcon(direction + "black_wPoint.jpg");
	ImageIcon rightBPointIcon = new ImageIcon(direction + "rightboard_bPoint.jpg");
        ImageIcon rightWPointIcon = new ImageIcon(direction + "rightboard_wPoint.jpg");
	ImageIcon rightWhiteBPointIcon = new ImageIcon(direction + "rightwhite_bPoint.jpg");
        ImageIcon rightWhiteWPointIcon = new ImageIcon(direction + "rightwhite_wPoint.jpg");
	ImageIcon rightBlackBPointIcon = new ImageIcon(direction + "rightblack_bPoint.jpg");
        ImageIcon rightBlackWPointIcon = new ImageIcon(direction + "rightblack_wPoint.jpg");
	ImageIcon leftBPointIcon = new ImageIcon(direction + "leftboard_bPoint.jpg");
        ImageIcon leftWPointIcon = new ImageIcon(direction + "leftboard_wPoint.jpg");
	ImageIcon leftBlackBPointIcon = new ImageIcon(direction + "leftblack_bPoint.jpg");
        ImageIcon leftBlackWPointIcon = new ImageIcon(direction + "leftblack_wPoint.jpg");
	ImageIcon leftWhiteBPointIcon = new ImageIcon(direction + "leftwhite_bPoint.jpg");
        ImageIcon leftWhiteWPointIcon = new ImageIcon(direction + "leftwhite_wPoint.jpg");
	ImageIcon upBPointIcon = new ImageIcon(direction + "upboard_bPoint.jpg");
        ImageIcon upWPointIcon = new ImageIcon(direction + "upboard_wPoint.jpg");
	ImageIcon upBlackBPointIcon = new ImageIcon(direction + "upblack_bPoint.jpg");
        ImageIcon upBlackWPointIcon = new ImageIcon(direction + "upblack_wPoint.jpg");
	ImageIcon upWhiteBPointIcon = new ImageIcon(direction + "upwhite_bPoint.jpg");
        ImageIcon upWhiteWPointIcon = new ImageIcon(direction + "upwhite_wPoint.jpg");
	ImageIcon downBPointIcon = new ImageIcon(direction + "downboard_bPoint.jpg");
        ImageIcon downWPointIcon = new ImageIcon(direction + "downboard_wPoint.jpg");
	ImageIcon downBlackBPointIcon = new ImageIcon(direction + "downblack_bPoint.jpg");
        ImageIcon downBlackWPointIcon = new ImageIcon(direction + "downblack_wPoint.jpg");
	ImageIcon downWhiteBPointIcon = new ImageIcon(direction + "downwhite_bPoint.jpg");
        ImageIcon downWhiteWPointIcon = new ImageIcon(direction + "downwhite_wPoint.jpg");
	ImageIcon upRightBPointIcon = new ImageIcon(direction + "uprightboard_bPoint.jpg");
        ImageIcon upRightWPointIcon = new ImageIcon(direction + "uprightboard_wPoint.jpg");
	ImageIcon upRightBlackBPointIcon = new ImageIcon(direction + "uprightblack_bPoint.jpg");
        ImageIcon upRightBlackWPointIcon = new ImageIcon(direction + "uprightblack_wPoint.jpg");
	ImageIcon upRightWhiteBPointIcon = new ImageIcon(direction + "uprightwhite_bPoint.jpg");
        ImageIcon upRightWhiteWPointIcon = new ImageIcon(direction + "uprightwhite_wPoint.jpg");
	ImageIcon upLeftBPointIcon = new ImageIcon(direction + "upleftboard_bPoint.jpg");
        ImageIcon upLeftWPointIcon = new ImageIcon(direction + "upleftboard_wPoint.jpg");
	ImageIcon upLeftBlackBPointIcon = new ImageIcon(direction + "upleftblack_bPoint.jpg");
        ImageIcon upLeftBlackWPointIcon = new ImageIcon(direction + "upleftblack_wPoint.jpg");
	ImageIcon upLeftWhiteBPointIcon = new ImageIcon(direction + "upleftwhite_bPoint.jpg");
        ImageIcon upLeftWhiteWPointIcon = new ImageIcon(direction + "upleftwhite_wPoint.jpg");
	ImageIcon downRightBPointIcon = new ImageIcon(direction + "downrightboard_bPoint.jpg");
        ImageIcon downRightWPointIcon = new ImageIcon(direction + "downrightboard_wPoint.jpg");
	ImageIcon downRightBlackBPointIcon = new ImageIcon(direction
			+ "downrightblack_bPoint.jpg");
        ImageIcon downRightBlackWPointIcon = new ImageIcon(direction
			+ "downrightblack_wPoint.jpg");
	ImageIcon downRightWhiteBPointIcon = new ImageIcon(direction
			+ "downrightwhite_bPoint.jpg");
        ImageIcon downRightWhiteWPointIcon = new ImageIcon(direction
			+ "downrightwhite_wPoint.jpg");
	ImageIcon downLeftBPointIcon = new ImageIcon(direction + "downleftboard_bPoint.jpg");
        ImageIcon downLeftWPointIcon = new ImageIcon(direction + "downleftboard_wPoint.jpg");
	ImageIcon downLeftBlackBPointIcon = new ImageIcon(direction + "downleftblack_bPoint.jpg");
        ImageIcon downLeftBlackWPointIcon = new ImageIcon(direction + "downleftblack_wPoint.jpg");
	ImageIcon downLeftWhiteBPointIcon = new ImageIcon(direction + "downleftwhite_bPoint.jpg");
        ImageIcon downLeftWhiteWPointIcon = new ImageIcon(direction + "downleftwhite_wPoint.jpg");
	JPanel boardPanel = new JPanel();
        JFrame f = new JFrame("Finish");

        
        public GoGameMain() {
            setMenu();
            initChess(board);
            setChessIcon(board);
            boardInt = initChessInt(boardInt);
            addChessIntoPanel(board, boardPanel);
            this.add(boardPanel);
            this.setTitle("Go 9x9 Game");
            this.setResizable(false);
            this.setLocation(300, 130);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setVisible(true);
        }

        private void setMenu() {
		/* mainMenuBar */
		JMenuBar mainMenuBar = new JMenuBar();
		String[][] menuStrs = { { "Game", "Begin", "PlayWithPC", "Finish","", "Exit" },
                                {"System" ,"Save", "Load"}};
		String[][] menuCommands = {
				{ "Game", "Begin", "PlayWithPC", "Finish", "", "Exit" },
                                {"System" ,"Save", "Load"} };

		for (int i = 0; i < menuStrs.length; i++) {
			JMenu menu = new JMenu(menuStrs[i][0]);
			mainMenuBar.add(menu);
			for (int j = 1; j < menuStrs[i].length; j++) {
				if ("".equals(menuStrs[i][j]))
					menu.addSeparator();
				else {
					JMenuItem menuItem = new JMenuItem(menuStrs[i][j]);
					menuItem.addActionListener(this);
					menuItem.setActionCommand(menuCommands[i][j]);
					menu.add(menuItem);
				}
			}
		}
		this.setJMenuBar(mainMenuBar);
	}

        private void initChess(JButton[][] board) {
            for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new JButton();
				board[i][j].setBounds(0, 0, 0, 0);
				board[i][j].setBorderPainted(false);
				board[i][j].setPreferredSize(new java.awt.Dimension(40, 40));
				board[i][j].addActionListener(this);
			}
		}
        }

        private void setChessIcon(JButton[][] board) {
            for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (i == 4 && j == 4)
					board[i][j].setIcon(upLeftIcon);
				else if (i == 4 && j == board[i].length - 5)
					board[i][j].setIcon(upRightIcon);
				else if (i == board.length - 5 && j == 4)
					board[i][j].setIcon(downLeftIcon);
				else if (i == board.length - 5
						&& j == board[i].length - 5)
					board[i][j].setIcon(downRightIcon);
				else if (i == 4)
					board[i][j].setIcon(upIcon);
				else if (j == 4)
					board[i][j].setIcon(leftIcon);
				else if (j == board[i].length - 5)
					board[i][j].setIcon(rightIcon);
				else if (i == board.length - 5)
					board[i][j].setIcon(downIcon);
				else
					board[i][j].setIcon(beginIcon);
			}
		}
        }

        public void setOnChessIcon(int i, int j, int userFlag) {
            
		if (i == 4 && j == 4)
			board[i][j].setIcon(userFlag == 1 ? upLeftBlackIcon
					: upLeftWhiteIcon);
		else if (i == 4 && j == board[i].length - 5)
			board[i][j].setIcon(userFlag == 1 ? upRightBlackIcon
					: upRightWhiteIcon);
		else if (i == board.length - 5 && j == 4)
			board[i][j].setIcon(userFlag == 1 ? downLeftBlackIcon
					: downLeftWhiteIcon);
		else if (i == board.length - 5 && j == board[i].length - 5)
			board[i][j].setIcon(userFlag == 1 ? downRightBlackIcon
					: downRightWhiteIcon);
		else if (i == 4)
			board[i][j].setIcon(userFlag == 1 ? upBlackIcon : upWhiteIcon);
		else if (j == 4)

			board[i][j].setIcon(userFlag == 1 ? leftBlackIcon : leftWhiteIcon);
		else if (j == board[i].length - 5)
			board[i][j]
					.setIcon(userFlag == 1 ? rightBlackIcon : rightWhiteIcon);
		else if (i == board.length - 5)
			board[i][j].setIcon(userFlag == 1 ? downBlackIcon : downWhiteIcon);
		else
			board[i][j].setIcon(userFlag == 1 ? blackIcon : whiteIcon);
            
	}

        public static void showChess(int[][] boardInt) {
		for (int i = 0; i < boardInt.length; i++) {
			for (int j = 0; j < boardInt[i].length; j++)
				System.out.print("   " + boardInt[i][j]);
			System.out.println();
		}
	}

        public static int[][] initChessInt(int[][] array) {

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
                            if(i>=4 && i<array.length-4 &&j>=4 && j<array.length-4)
				array[i][j] = 0;
                            else
                                array[i][j] = 2;
			}
		}
		return array;
	}

        public static void addChessIntoPanel(JButton[][] buttons, JPanel panel) {
		panel.setLayout(new GridLayout(boardLineCount-8, boardLineCount-8));
		int numbers = 0;
		for (int i = 4; i < buttons.length - 4; i++) {
			for (int j = 4; j < buttons[i].length - 4; j++) {
				buttons[i][j].setActionCommand("" + (numbers++));
				panel.add(buttons[i][j]);
			}
		}
		panel.setPreferredSize(new java.awt.Dimension(40*(boardLineCount-8),40*(boardLineCount-8)));
	}

        	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Begin")) {
			userType = true;
			userFlag = 1;
			beginGame();
		} else if (e.getActionCommand().equals("PlayWithPC")) {
			userType = false;
			beginGame();
			userFlag = 1;
                }else if (e.getActionCommand().equals("Finish")){
                    finish();
                   
                }else if(e.getActionCommand().equals("Yes")) {
                    countPoint();
                    f.dispose();
                }
                else if (e.getActionCommand().equals("Cancel")){
                    f.dispose();
                }
                else if (e.getActionCommand().equals("Save")){
                    try {
                        save();
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}else if (e.getActionCommand().equals("Load")){
                    try {
                        load();
                    } catch (Exception ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
		} else {
			while (true) {
				int commandNumber = 0;
				int x = 0;
				int y = 0;
				if (!comTurn) {
					commandNumber = Integer.parseInt(e.getActionCommand());
					x = commandNumber /(boardLineCount-8) + 4;
					y = commandNumber % (boardLineCount-8) + 4;
                                        if(!checkAvailable(x,y,userFlag) && checkEat(x,y,userFlag).isEmpty())
                                            return;
				} else {
					/* get a next step in Play with PC mode */
					Point p = this.getComputerPoint();
					x = p.x;
					y = p.y;
                                        if(!checkAvailable(x,y,userFlag) && checkEat(x,y,userFlag).isEmpty())
                                            return;
				}
                                Point tp = new Point(x, y);
                                putOnChess(tp);
				if (userType) {
					userFlag *= -1;
					break;
				} else {
					if (userFlag == 1) {
						userFlag = -1;
                                                comTurn = true;
						continue;
					}
					else
                                        {
						userFlag = 1;
                                                comTurn = false;
                                        }
					break;
				}
			}
		}
	}


        private void beginGame() {
                userFlag = 1;
                stones = null;
                stones = new int[200][2];
		for (int i = 0; i < boardInt.length; i++) {
			for (int j = 0; j < boardInt[i].length; j++) {
                            if(i>=4 && i<boardInt.length-4 &&j>=4 && j<boardInt.length-4){
				boardInt[i][j] = 0;
                                board[i][j].removeActionListener(this);
                                board[i][j].addActionListener(this);
                            }else{
                                boardInt[i][j]=2;
                            }
			}
		}
                steps=0;
		setChessIcon(board);
        }

        private void putOnChess(Point tp) {
            int x = tp.x;
            int y = tp.y;
            ArrayList   stonesDead   =   new   ArrayList();
            stonesDead = checkEat(x,y,userFlag);
            if (stonesDead.size()==1 && liberty[x][y]==0)
                isKo = true;
            else
                isKo = false;
            if (!stonesDead.isEmpty())
            {
                boardInt[x][y] = userFlag;
                setOnChessIcon(x, y, userFlag);
                eat(x,y,stonesDead);
                stones[steps][0] = x;
                stones[steps][1] = y;
                steps++;
                board[x][y].removeActionListener(this);
            }
            else
            {
                boardInt[x][y] = userFlag;
                setOnChessIcon(x, y, userFlag);
                stones[steps][0] = x;
                stones[steps][1] = y;
                steps++;
                board[x][y].removeActionListener(this);
            }
        }

        private ArrayList checkEat(int i, int j, int userFlag) {
            int a=i,b=j;
            ArrayList   stonesDead   =   new   ArrayList();
            ArrayList   temp   =   new   ArrayList();
            temp = null;
            int n;
            if(boardInt[a+1][b]!=-userFlag && boardInt[a][b+1]!=-userFlag && boardInt[a-1][b]!=-userFlag && boardInt[a][b-1]!=-userFlag)
                return stonesDead;
            a=i+1;
            if(boardInt[a][b]==-userFlag)
            {
                temp = findEatPart(a, b, userFlag);
                if (temp != null)
                {
                    for(n=0; n<temp.size();n++)
                    stonesDead.add(temp.get(n));
                }
            }
            a=i-1;
            if(boardInt[a][b]==-userFlag && !stonesDead.contains(new   Point(a, b)))
            {
                temp = findEatPart(a, b, userFlag);
                if (temp != null)
                {
                    for(n=0; n<temp.size();n++)
                    stonesDead.add(temp.get(n));
                }
            }
            a=i;b=j+1;
            if(boardInt[a][b]==-userFlag && !stonesDead.contains(new   Point(a, b)))
            {
                temp = findEatPart(a, b, userFlag);
                if (temp != null)
                {
                    for(n=0; n<temp.size();n++)
                    stonesDead.add(temp.get(n));
                }
            }
            b=j-1;
            if(boardInt[a][b]==-userFlag && !stonesDead.contains(new   Point(a, b)))
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
            boardInt[i][j]=userFlag;
            stoneLiberty();
            do
            {
                findCount=0;
                for   (int   a   =   begin;   a   <=   end;   a++)
                {
                    Point newpoint = (Point) stonesVisited.get(a);
                    newx=newpoint.x;
                    newy=newpoint.y;
                    if(boardInt[newx+1][newy]==userFlag && !stonesVisited.contains(new   Point(newx+1, newy)))
                    {
                        stonesVisited.add(new   Point(newx+1,   newy));
                        findCount   +=   1;
                    }
                    if(boardInt[newx-1][newy]==userFlag && !stonesVisited.contains(new   Point(newx-1, newy)))
                    {
                        stonesVisited.add(new   Point(newx-1,   newy));
                        findCount   +=   1;
                    }
                    if(boardInt[newx][newy+1]==userFlag && !stonesVisited.contains(new   Point(newx, newy+1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy+1));
                        findCount   +=   1;
                    }
                    if(boardInt[newx][newy-1]==userFlag && !stonesVisited.contains(new   Point(newx, newy-1)))
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
                boardInt[i][j]=0;
                return false;
        }

        private void stoneLiberty() {
          int a,b;
          for(a=4;a<board.length-4;a++)
          {
              for(b=4;b<board[a].length-4;b++)
              {
                 if(boardInt[a][b] == 0)
                    liberty[a][b] =0;
                 else
                 {
                    if((a==4 || a==board.length - 5) && (b==4 || b==board[a].length - 5))
                    {
                        liberty[a][b] = 2;
                        if(a==4 && b==4)
                        {
                            if (boardInt[a + 1][b] != 0)
                                liberty[a][b]--;
                            if (boardInt[a][b + 1] != 0)
                                liberty[a][b]--;
                        }
                        else if(a == 4 && b == board[a].length - 5)
                        {
                            if (boardInt[a + 1][b] != 0)
                                liberty[a][b]--;
                            if (boardInt[a][b - 1] != 0)
                                liberty[a][b]--;
                        }
                        else if(b == 4)
                        {
                            if (boardInt[a - 1][b] != 0)
                                liberty[a][b]--;
                            if (boardInt[a][b + 1] != 0)
                        liberty[a][b]--;
                        }
                        else
                        {
                            if (boardInt[a - 1][b] != 0)
                                liberty[a][b]--;
                            if (boardInt[a][b - 1] != 0)
                                liberty[a][b]--;
                        }
                    }
                    else if(a==4 || a==board.length - 5 || b==4 || b==board[a].length - 5)
                    {
                        liberty[a][b] = 3;
                        if(a==4)
                        {
                            if(boardInt[a+1][b]!=0)
                                liberty[a][b]--;
                            if(boardInt[a][b+1]!=0)
                                liberty[a][b]--;
                            if(boardInt[a][b-1]!=0)
                                liberty[a][b]--;
                        }
                        else if(a == board.length - 5)
                        {
                            if(boardInt[a-1][b]!=0)
                                liberty[a][b]--;
                            if(boardInt[a][b+1]!=0)
                                liberty[a][b]--;
                            if(boardInt[a][b-1]!=0)
                                liberty[a][b]--;
                        }
                        else if(b==4)
                        {
                            if(boardInt[a][b+1]!=0)
                                liberty[a][b]--;
                            if(boardInt[a+1][b]!=0)
                                liberty[a][b]--;
                            if(boardInt[a-1][b]!=0)
                                liberty[a][b]--;
                        }
                        else
                        {
                            if(boardInt[a][b-1]!=0)
                                liberty[a][b]--;
                            if(boardInt[a+1][b]!=0)
                                liberty[a][b]--;
                            if(boardInt[a-1][b]!=0)
                                liberty[a][b]--;
                        }
                    }
                    else
                    {
                        liberty[a][b]=4;
                        if(boardInt[a+1][b]!=0)
                            liberty[a][b]--;
                        if(boardInt[a-1][b]!=0)
                            liberty[a][b]--;
                        if(boardInt[a][b+1]!=0)
                            liberty[a][b]--;
                        if(boardInt[a][b-1]!=0)
                            liberty[a][b]--;
                    }
                  }
              }
            }
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
                    if(boardInt[newx+1][newy]==-userFlag && !stonesVisited.contains(new   Point(newx+1, newy)))
                    {
                        stonesVisited.add(new   Point(newx+1,   newy));
                        findCount   +=   1;
                    }
                    if(boardInt[newx-1][newy]==-userFlag && !stonesVisited.contains(new   Point(newx-1, newy)))
                    {
                        stonesVisited.add(new   Point(newx-1,   newy));
                        findCount   +=   1;
                    }
                    if(boardInt[newx][newy+1]==-userFlag && !stonesVisited.contains(new   Point(newx, newy+1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy+1));
                        findCount   +=   1;
                    }
                    if(boardInt[newx][newy-1]==-userFlag && !stonesVisited.contains(new   Point(newx, newy-1)))
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
            if(countLiberty<1 && !(stonesVisited.size()==1 && a==stones[steps-1][0] && b==stones[steps-1][1] && isKo))
                return stonesVisited;
            else
                return null;
        }


        private void eat(int x, int y, ArrayList stonesDead) {
            int i,j;
            for(int a=0;a<stonesDead.size();a++)
            {
                Point deadpoint = (Point) stonesDead.get(a);
                i = deadpoint.x;
                j = deadpoint.y;
                if (i == 4 && j == 4)
			board[i][j].setIcon(upLeftIcon);
		else if (i == 4 && j == board[i].length - 5)
			board[i][j].setIcon(upRightIcon);
		else if (i == board.length - 5 && j == 4)
			board[i][j].setIcon(downLeftIcon);
		else if (i == board.length - 5
				&& j == board[i].length - 5)
			board[i][j].setIcon(downRightIcon);
		else if (i == 4)
			board[i][j].setIcon(upIcon);
		else if (j == 4)
			board[i][j].setIcon(leftIcon);
		else if (j == board[i].length - 5)
			board[i][j].setIcon(rightIcon);
		else if (i == board.length - 5)
			board[i][j].setIcon(downIcon);
		else
			board[i][j].setIcon(beginIcon);
                boardInt[i][j]=0;
                board[i][j].addActionListener(this);
            }
        }

        private Point getComputerPoint() {
            int x=0,y=0;
            int i,j,n;
            int i1,i2,j1,j2;
            int mark1[][] = new int[boardLineCount][boardLineCount];
            int mark2[][] = new int[boardLineCount][boardLineCount];
            int mark[][] = new int[boardLineCount][boardLineCount];
            int maxMark=0;
            int miniMark = 100;
            int Mark=0;
            for(i=4;i<boardInt.length-4;i++){
                for(j=4;j<boardInt[i].length-4;j++)
                {
                    if(boardInt[i][j]==0 && (checkAvailable(i,j,userFlag) || !checkEat(i,j,userFlag).isEmpty())){
                        stones[steps][0]=i;
                        stones[steps][1]=j;
                        steps++;
                        monteCarloMethod mCM = new monteCarloMethod();
                        mark[i][j] = mCM.monteCarlomethod(stones,steps);
                        steps--;
                        boardInt[i][j]=0;
                        if(mark[i][j]>Mark){
                            Mark = mark[i][j];
                            x=i;
                            y=j;
                        }
                    }
                }
            }
            if(x!=0 && y!=0)
                return new Point(x, y);
            else
                return null;
    }


        private int countLiberty(int a, int b, int userFlag) {
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
                    if(boardInt[newx+1][newy]==-userFlag && !stonesVisited.contains(new   Point(newx+1, newy)))
                    {
                        stonesVisited.add(new   Point(newx+1,   newy));
                        findCount   +=   1;
                    }
                    if(boardInt[newx-1][newy]==-userFlag && !stonesVisited.contains(new   Point(newx-1, newy)))
                    {
                        stonesVisited.add(new   Point(newx-1,   newy));
                        findCount   +=   1;
                    }
                    if(boardInt[newx][newy+1]==-userFlag && !stonesVisited.contains(new   Point(newx, newy+1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy+1));
                        findCount   +=   1;
                    }
                    if(boardInt[newx][newy-1]==-userFlag && !stonesVisited.contains(new   Point(newx, newy-1)))
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
                return countLiberty;
        }

        private void countPoint() {
            ArrayList   stonesVisited   =   new   ArrayList();
            ArrayList   temp   =   new   ArrayList();
            int n,m;
            int blackPoint=0, whitePoint=0;
            int i,j;
            int boardState; //The state of the cross in the board, -1 means white stone, 1 means black stone, 0 means empty.
            int pointFlag; //pointFlag=-1 means white's point, 1 means black's point.

            for(i=4;i<board.length-4;i++)
            {
                  for(j=4;j<board[i].length-4;j++)
                  {
                      for(m=0;m<stonesVisited.size();m++)
                      {
                          if (stonesVisited.get(m).equals(new Point(i, j))){
                              break;
                              }
                      }
                      if (boardInt[i][j] == 0) {
                          temp = checkState(i,j);
                          for(n=0; n<temp.size();n++){
                            stonesVisited.add(temp.get(n));
                          }
                          if (pointInt[i][j] == 0 || pointInt[i][j] == 2){
                              if (i == 4 && j == 4)
                                  board[i][j].setIcon(upLeftIcon);
                              else if (i == 4 && j == board[i].length - 5)
                                  board[i][j].setIcon(upRightIcon);
                              else if (i == board.length - 5 && j == 4)
                                  board[i][j].setIcon(downLeftIcon);
                              else if (i == board.length - 5 && j == board[i].length - 5)
                                  board[i][j].setIcon(downRightIcon);
                              else if (i == 4)
                                  board[i][j].setIcon(upIcon);
                              else if (j == 4)
                                  board[i][j].setIcon(leftIcon);
                              else if (j == board[i].length - 5)
                                  board[i][j].setIcon(rightIcon);
                              else if (i == board.length - 5)
                                  board[i][j].setIcon(downIcon);
                              else
                                  board[i][j].setIcon(beginIcon);
                          }else{
                              if (i == 4 && j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upLeftBPointIcon: upLeftWPointIcon);
                              else if (i == 4 && j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upRightBPointIcon: upRightWPointIcon);
                              else if (i == board.length - 5 && j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downLeftBPointIcon: downLeftWPointIcon);
                              else if (i == board.length - 5 && j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downRightBPointIcon: downRightWPointIcon);
                              else if (i == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upBPointIcon : upWPointIcon);
                              else if (j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? leftBPointIcon : leftWPointIcon);
                              else if (j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? rightBPointIcon : rightWPointIcon);
                              else if (i == board.length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downBPointIcon : downWPointIcon);
                              else
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? boardBPointIcon : boardWPointIcon);
                          }
                        }
                      if(boardInt[i][j] == 1){
                          stonesVisited.add(new   Point(i,j));
                          pointInt[i][j] = 1;
                              if (i == 4 && j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upLeftBlackBPointIcon: upLeftBlackWPointIcon);
                              else if (i == 4 && j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upRightBlackBPointIcon: upRightBlackWPointIcon);
                              else if (i == board.length - 5 && j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downLeftBlackBPointIcon: downLeftBlackWPointIcon);
                              else if (i == board.length - 5 && j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downRightBlackBPointIcon: downRightBlackWPointIcon);
                              else if (i == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upBlackBPointIcon : upBlackWPointIcon);
                              else if (j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? leftBlackBPointIcon : leftBlackWPointIcon);
                              else if (j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? rightBlackBPointIcon : rightBlackWPointIcon);
                              else if (i == board.length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downBlackBPointIcon : downBlackWPointIcon);
                              else
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? blackBPointIcon : blackWPointIcon);
                      }
                      if(boardInt[i][j]==-1){
                          stonesVisited.add(new   Point(i,j));
                          pointInt[i][j]=-1;
                              if (i == 4 && j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upLeftWhiteBPointIcon: upLeftWhiteWPointIcon);
                              else if (i == 4 && j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upRightWhiteBPointIcon: upRightWhiteWPointIcon);
                              else if (i == board.length - 5 && j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downLeftWhiteBPointIcon: downLeftWhiteWPointIcon);
                              else if (i == board.length - 5 && j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downRightWhiteBPointIcon: downRightWhiteWPointIcon);
                              else if (i == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? upWhiteBPointIcon : upWhiteWPointIcon);
                              else if (j == 4)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? leftWhiteBPointIcon : leftWhiteWPointIcon);
                              else if (j == board[i].length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? rightWhiteBPointIcon : rightWhiteWPointIcon);
                              else if (i == board.length - 5)
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? downWhiteBPointIcon : downWhiteWPointIcon);
                              else
                                  board[i][j].setIcon(pointInt[i][j] == 1 ? whiteBPointIcon : whiteWPointIcon);
                      }
                  }
            }
            for(i=4;i<board.length-4;i++)
            {
                  for(j=4;j<board[i].length-4;j++)
                  {
                      if (pointInt[i][j]==1)
                          blackPoint++;
                      else if (pointInt[i][j]==-1)
                          whitePoint++;
                }
            }
            JFrame win = new JFrame("Win");
            Button OK;
            win.setSize(250,100);
            win.setLocation(350,250);
            win.setLayout(new FlowLayout());
            win.add(new JLabel("BLACK:" + blackPoint + "  WHITE:" + whitePoint));
            if (blackPoint>whitePoint)
                win.add(new JLabel("<html><br><br>BLACK WIN"));
            else if (whitePoint>blackPoint)
                win.add(new JLabel("<html><br><br>WHITE WIN"));
            else
                win.add(new JLabel("<html><br><br>DRAW"));
            win.setResizable(false);
            win.setVisible(true);
        }

        private ArrayList checkState(int a, int b) {
            ArrayList   stonesVisited   =   new   ArrayList();
            ArrayList   pointsList   =   new   ArrayList();
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
                    if(boardInt[newx+1][newy]==0 && !stonesVisited.contains(new   Point(newx+1, newy)))
                    {
                        stonesVisited.add(new   Point(newx+1,   newy));
                        findCount   +=   1;
                    }else if((boardInt[newx+1][newy]==1 || boardInt[newx+1][newy]==-1) && !stonesVisited.contains(new   Point(newx+1, newy))){
                        if (boradState ==2){
                            boradState = boardInt[newx+1][newy];
                        }else if(boradState == -boardInt[newx+1][newy]){
                            boradState = 0;
                        }
                    }
                    if(boardInt[newx-1][newy]==0 && !stonesVisited.contains(new   Point(newx-1, newy)))
                    {
                        stonesVisited.add(new   Point(newx-1,   newy));
                        findCount   +=   1;
                    }else if((boardInt[newx-1][newy]==1 || boardInt[newx-1][newy]==-1) && !stonesVisited.contains(new   Point(newx-1, newy))){
                        if (boradState ==2){
                            boradState = boardInt[newx-1][newy];
                        }else if(boradState == -boardInt[newx-1][newy]){
                            boradState = 0;
                        }
                    }
                    if(boardInt[newx][newy+1]==0 && !stonesVisited.contains(new   Point(newx, newy+1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy+1));
                        findCount   +=   1;
                    }else if((boardInt[newx][newy+1]==1 || boardInt[newx][newy+1]==-1) && !stonesVisited.contains(new   Point(newx, newy+1))){
                        if (boradState ==2){
                            boradState = boardInt[newx][newy+1];
                        }else if(boradState == -boardInt[newx][newy+1]){
                            boradState = 0;
                        }
                    }
                    if(boardInt[newx][newy-1]==0 && !stonesVisited.contains(new   Point(newx, newy-1)))
                    {
                        stonesVisited.add(new   Point(newx,   newy-1));
                        findCount   +=   1;
                    }else if((boardInt[newx][newy-1]==1 || boardInt[newx][newy-1]==-1) && !stonesVisited.contains(new   Point(newx, newy-1))){
                        if (boradState ==2){
                            boradState = boardInt[newx][newy-1];
                        }else if(boradState == -boardInt[newx][newy-1]){
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
//            JFrame f = new JFrame("Finish");
                Button Yes, Cancel;
                f.setSize(250,100);
                f.setLocation(350,250);
                f.setLayout(new FlowLayout());
                f.add(new JLabel("Do you really want to finish the game?"));
                f.add(Yes = new Button("Yes"));
                f.add(Cancel = new Button("Cancel"));
                Yes.addActionListener(this);
                Cancel.addActionListener(this);
                f.setResizable(false);
                f.setVisible(true);

        }

        private void save() throws Exception{
            int n = steps;
            boolean a=false;
            String  save = "save.txt";
            File read = new File(save);
            if(read.exists())
                while(read.delete());
            RandomAccessFile ra = new RandomAccessFile(save, "rw");
            for(int i=0;i<steps;i++){
                ra.writeInt(stones[i][0]);
                ra.writeInt(stones[i][1]);
            }
            ra.close();
        }

        private void load() throws Exception{
            int num;
            int n = 0;
            int x, y;
            Point tp;
            String  save = "save.txt";         
            RandomAccessFile ra = new RandomAccessFile(save, "r");
            beginGame();
            try{
                while (true){
                    num = ra.readInt();
                    stones[n][0]=num;
                    num = ra.readInt();
                    stones[n][1]=num;
                    n++;
                }
            }catch(EOFException e){}
            for(int i=0;i<=n;i++)
            {
                x=stones[i][0];
                y=stones[i][1];
                if(!checkAvailable(x,y,userFlag) && checkEat(x,y,userFlag).isEmpty())
                      return;
                tp = new Point(x, y);
                putOnChess(tp);
                userFlag *= -1;
                }
            }


        
    }


    
public static void main(String[] args) {
       new GoGameMain();
    
}
}



