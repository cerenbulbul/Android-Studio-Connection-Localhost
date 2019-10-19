/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure.hw1;

import com.sun.glass.ui.Size;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.CYAN;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.GRAY;
import static java.awt.Color.GREEN;
import static java.awt.Color.MAGENTA;
import static java.awt.Color.ORANGE;
import static java.awt.Color.PINK;
import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import static javax.swing.text.StyleConstants.Size;

/**
 *
 * @author Ceren
 */
public class DataStructureHW1 extends JFrame {
    
    public static int uzunluk, titan3m, titan5m, titan15m, player, engel;

    private final JPanel playArea = new CustomPanel(1000, 800);  
    private final JButton[][] boardSquares = new JButton[uzunluk][uzunluk];
    private final JPanel board , board2;
    static JButton square , titan3mButton, titan5mButton, titan15mButton, playerButton , obstacleButton , start, step , deneme;
    public static ArrayList<JButton> buttonArray = new ArrayList<>();
    public static ArrayList<JButton> Titans = new ArrayList<>();
    public static ArrayList<JButton> Players = new ArrayList<>();
    boolean turn=false;
    int random_x;
    int random_y;
    int random;
    
   

        public  DataStructureHW1() {
            
        playArea.setLayout(new BorderLayout(3,3));
        playArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        
        board = new CustomPanel(900, 900);
        board.setLayout(new GridLayout(0, uzunluk));
        board.setBorder(new LineBorder(Color.BLACK));
        playArea.add(board, BorderLayout.CENTER);
        
        board2 = new CustomPanel(100,100);
        board2.setLayout(new GridLayout(0,10));
        board2.setBorder(new LineBorder(Color.BLACK));
        playArea.add(board2,BorderLayout.NORTH);
        
        start = new JButton();
        start.setText("Start");
        board2.add(start, BorderLayout.EAST);
        
        
        
        Insets buttonMargin = new Insets(0,0,0,0);
        
        // Burada 2D array'in icini gezerek ne kadar titan,player,engel konulmasÄ± gerekildi belirleniyor 
        // 5m'likler 2 kare degil 1 kare kaliyor. 
        // 15m'likler 4 kare degil 1 kare kapliyor
        
        int k=0;
        int l=0;
        int m=0;
        int n=0;
        int o=0;
        int p=0;
        
        for (int i = 0; i < uzunluk; i++) {
           for (int j = 0; j < uzunluk; j++) {
          
                if(p<titan3m) {
                    titan3mButton = new JButton();
                    titan3mButton.setOpaque(true);
                    titan3mButton.setMargin(buttonMargin);
                
                    titan3mButton.setBackground(Color.RED);
                    buttonArray.add(titan3mButton);
                    Titans.add(titan3mButton);
                   
                    p++;
                } else if(l<player) {
                    playerButton = new JButton();
                    playerButton.setOpaque(true);
                    playerButton.setMargin(buttonMargin);
                
                    playerButton.setBackground(Color.CYAN);
                    buttonArray.add(playerButton);
                    Players.add(playerButton);
                    
                    l++;
                    
                } else if(m<engel) {
                    obstacleButton = new JButton();
                    obstacleButton.setOpaque(true);
                    obstacleButton.setMargin(buttonMargin);
                
                    obstacleButton.setBackground(Color.BLACK);
                    buttonArray.add(obstacleButton);
                    
                    m++;
                    
                }else if(n<titan5m){
                    titan5mButton = new JButton();
                    titan5mButton.setOpaque(true);
                    titan5mButton.setMargin(buttonMargin);
                
                    titan5mButton.setBackground(Color.BLUE);
                    buttonArray.add(titan5mButton);
                    Titans.add(titan5mButton);
                    
                    n++;
                    
                }else if(o<titan15m){
                    titan15mButton = new JButton();
                    titan15mButton.setOpaque(true);
                    titan15mButton.setMargin(buttonMargin);
                
                    titan15mButton.setBackground(Color.GREEN);
                    buttonArray.add(titan15mButton);
                    Titans.add(titan15mButton);
                    
                    o++;
                    
                } else{
                square = new JButton();
                square.setOpaque(true);
                square.setMargin(buttonMargin);
                
                square.setBackground(Color.DARK_GRAY);
                buttonArray.add(square);
                
                } 
                  
           }
        }
        //2D array'in icini doldurduktan sonra hepsini shuffle yapiyoruz random olabilmesi icin
        // ve ekrana (board) yazdiriyoruz
        
        Collections.shuffle(buttonArray);
        for (int i = 0; i < uzunluk; i++) {
           for (int j = 0; j < uzunluk; j++) {
               board.add(boardSquares[j][i] = buttonArray.get(k));
               
               k++;
           } 
           
           } 
        
        // start butonumuza bastigimizda oyunumuz basliyor ve olaylari adim adim gostermesi icin start butonuna tekrar tekrar basmamiz lazim
        // tek basimda butun olayi sonuna kadar gosteren bir buton yok onun yerine tek tek adim adim gosteren 1 buton var
        
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                        try {
                            play();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(DataStructureHW1.class.getName()).log(Level.SEVERE, null, ex);
                        } 

           
            }
        });
     
        
        
             
    }     
        //Oyun icin kulanilan method 
        
        // Buton olan titan ve playerlari renklerine gore ayirarak yaptim
        
          // titan3m = RED
          // titan5m = BLUE 
          // titan15m = GREEN
          
          // player 0 level = CYAN
          // player 1 level = PINK 
          // player 2 level = GRAY
          // player 3 level = ORANGE
          // player 4 level = MAGENTA (Bu level titan3m den daha yuksek bir puana sahiptir)
          // player 5 level = LIGHT GRAY (Bu level titan3m den daha yuksek bir puana sahiptir)
          // player 6 level = WHITE (Bu level titan3m ve titan5m den daha yuksek bir puana sahiptir)
          // player 7 level = YELLOW (Bu level titan3m ve titan5m den daha yuksek bir puana sahiptir)
        
          // Engel = BLACK 
        
        private void play() throws InterruptedException {
          
          turn=true;
          for (int i = 0; i < uzunluk; i++) {
           for (int j = 0; j < uzunluk; j++) {
               
               // Random deger titan ve playerlarin 1 birim hareketleri icin. 
               // Titan ve playerlar asagi, yukari, saga, sola ayni zamanda capraz hareket edebilme ve durma islemlerini random bir sekilde yapabilirler. 
               
               Random rand = new Random();
               random = rand.nextInt(uzunluk-1);
               random_x = -1 + (int) (Math.random() * ((1 - (-1)) + 1));
               random_y = -1 + (int) (Math.random() * ((1 - (-1)) + 1));
               
       
               //Random hareket ettikleri icin uc noktalara gelmelerini onlemek amacli bu statement olusturulmustur
               if (i+random_x >uzunluk-1 || i+random_x < 0 || j+random_y > uzunluk-1 || j+random_y<0 ) {
                   random_x = -1 + (int) (Math.random() * ((1 - (-1)) + 1));
                   random_y = -1 + (int) (Math.random() * ((1 - (-1)) + 1));
               } else{
                   //Eger random sayi 0 cikiyorsa demektir ki duruyorlar o yuden hicbir islem yapmasina gerek yoktur. Bundan dolayi burada break kullandim.
                   if(random_x == 0 &&  random_y == 0) {
                       break;
                   } else{
                       
                       //TITANS
                       
                       
                       // Titan RED ise 3m'lik titandÃ„Â±r anlamÃ„Â±na geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) 3m'lik titan engelin ustune gelebilir
                       // Eger random hareket 3m'lik Titan (RED) geliyorsa 3m'lik titanlar birlesip 5m'lik titana donusur
                       // Eger random hareket 5m'lik Titan (BLUE) geliyorsa 5m'lik titan 3m'lik titani icine aliyor
                       // Eger random hareket 15'lik Titan (GREEN) geliyorsa 15lik titan 3mlik titanÃ„Â± icine alir
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), 3mlik titan player0 ustune gelir ve player0 random kacar
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), 3mlik titan player1 ustune gelir ve player1 random kacar ayni zamanda level'i duser player0 olur
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), 3mlik titan player2 ustune gelir ve player2 random kacar ayni zamanda level'i duser player1 olur
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), 3mlik titan player3 ustune gelir ve player3 random kacar ayni zamanda level'i duser player2 olur
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), 3mlik titan yok olur. player4'ÃƒÂ¼n level'i yukselir player5 olur
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), 3mlik titan yok olur. player5'in level'i yukselir player6 olur
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), 3mlik titan yok olur. player6'in level'i yukselir player7 olur
                       // Eger random hareket player 7 level ile bulusuyorsa(WHITE), 3mlik titan yok olur. 
                       
               if(boardSquares[i][j].getBackground() == RED) {
                   
                   
                       if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                            boardSquares[i+random_x][j+random_y].setBackground(Color.RED);
                          
                       
                       }
                       else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                               boardSquares[i+random_x][j+random_y].setBackground(Color.RED); 
                               boardSquares[i][j].setBackground(Color.DARK_GRAY);
                          
                        }
                       else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                            boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                            boardSquares[i][j].setBackground(Color.DARK_GRAY);
                            
                            Titans.remove(0);
                     
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.RED); 
                       boardSquares[random][random].setBackground(CYAN);
               
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                        boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN); 
                        
                        Titans.remove(0);
                    
                        
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                        boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                        
                        Titans.remove(0);
                 
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK ) {
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.RED);
                       boardSquares[random][random].setBackground(CYAN);
               }    
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY){
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.RED); 
                       boardSquares[random][random].setBackground(PINK);
                   } 
                        else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE){
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.RED); 
                       boardSquares[random][random].setBackground(GRAY);
                   } 
                        
                        else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.MAGENTA){
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE); 
                       
                       Titans.remove(0);
                   } 
                       else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY){
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE); 
                       
                       Titans.remove(0);
                   }
                       else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.WHITE){
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW); 
                       
                       Titans.remove(0);
                   }  
                       else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.YELLOW){
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW); 
                       
                       Titans.remove(0);
                   }
                       
                   break;
                   
           }   
                // Titan BLUE ise 5m'lik titandir anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) 5m'lik titan engelin ustune gelebilir
                       // Eger random hareket 3m'lik Titan (RED) geliyorsa 5m'lik titan 3m'lik titani icine aliyor
                       // Eger random hareket 5m'lik Titan (BLUE) geliyorsa 5m'lik titanlar birlesip 15mlik titana donusur
                       // Eger random hareket 15'lik Titan (GREEN) geliyorsa 15lik titan 5mlik titani icine alir
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), 5mlik titan player0 ustune gelir ve player0 random kacar
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), 5mlik titan player1 ustune gelir ve player1 random kacar ayni zamanda level'i duser player0 olur
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), 5mlik titan player2 ustune gelir ve player2 random kacar ayni zamanda level'i duser player1 olur
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), 5mlik titan player3 ustune gelir ve player3 random kacar ayni zamanda level'i duser player2 olur
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), 5mlik titan player4 ustune gelir ve player4 random kacar ayni zamanda level'i duser player3 olur
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), 5mlik titan player5 ustune gelir ve player5 random kacar ayni zamanda level'i duser player4 olur
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), 5mlik titan yok olur. player6'in level'i yukselir player7 olur
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), 5mlik titan yok olur. 
                       
               if(boardSquares[i][j].getBackground() == BLUE) {
                   
                   if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                            boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                         
                    
                       } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                               boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                               boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              
                          
                        }  else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                            boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                            boardSquares[i][j].setBackground(Color.DARK_GRAY);
                            
                            Titans.remove(0);
                           
                            
                        } else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(CYAN);
                     
                      
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                        boardSquares[i+random_x][j+random_y].setBackground(GREEN);
                        
                        Titans.remove(0);
                       
                   }
                    else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                        boardSquares[i+random_x][j+random_y].setBackground(GREEN);
                        
                        Titans.remove(0);
                   }  
                    
                    else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(CYAN);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(PINK);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(GRAY);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.MAGENTA) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(ORANGE);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(MAGENTA);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW); 
                       
                       Titans.remove(0);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.YELLOW) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW); 
                       
                       Titans.remove(0);
                    }
                   
                   
                     break;
                   
               }  // Titan GREEN ise 15m'lik titandir anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) 15m'lik titan engelin ustune gelebilir
                       // Eger random hareket 3m'lik Titan (RED) geliyorsa 15m'lik titan 3m'lik titani icine aliyor
                       // Eger random hareket 5m'lik Titan (BLUE) geliyorsa 15m'lik titan 5m'lik titani icine aliyor
                       // Eger random hareket 15'lik Titan (GREEN) geliyorsa 15m'lik titan 15m'lik titanÃ„Â± icine aliyor
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), 15mlik titan player0 ustune gelir ve player0 random kacar
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), 15mlik titan player1 ustune gelir ve player1 random kacar ayni zamanda level'i duser player0 olur
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), 15mlik titan player2 ustune gelir ve player2 random kacar ayni zamanda level'i duser player1 olur
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), 15mlik titan player3 ustune gelir ve player3 random kacar ayni zamanda level'i duser player2 olur
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), 15mlik titan player4 ustune gelir ve player4 random kacar ayni zamanda level'i duser player3 olur
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), 15mlik titan player5 ustune gelir ve player5 random kacar ayni zamanda level'i duser player4 olur
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), 15mlik titan player6 ustune gelir ve player6 random kaÃƒÂ§ar aynÃ„Â± zamanda level'i duser player5 olur
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), 15mlik titan 5cm'lik titana donusur. 
               
               if(boardSquares[i][j].getBackground() == GREEN) {
                   
                    if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                            boardSquares[i][j].setBackground(Color.DARK_GRAY);
                            boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                      
                    
                       } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                               boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                               boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           
                          
                        }  else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                            boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                            boardSquares[i][j].setBackground(Color.DARK_GRAY);
                            
                            Titans.remove(0);
                         
                            
                        } else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                       boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN); 
                       boardSquares[random][random].setBackground(CYAN);
                      
                      
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                        boardSquares[i+random_x][j+random_y].setBackground(GREEN);
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                        
                        Titans.remove(0);
                       
                   }
                    else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                        boardSquares[i+random_x][j+random_y].setBackground(GREEN);
                        
                        Titans.remove(0);
                   }
                    
                    else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                        boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                        boardSquares[random][random].setBackground(CYAN);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(PINK);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(GRAY);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.MAGENTA) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(ORANGE);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE); 
                       boardSquares[random][random].setBackground(MAGENTA);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                        boardSquares[i][j].setBackground(Color.DARK_GRAY);
                       boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                        boardSquares[random][random].setBackground(Color.LIGHT_GRAY);
                    }
                   
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.YELLOW) {
                       boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW); 
                       boardSquares[i][j].setBackground(Color.BLUE);
                       
                       
                    }
                   break;
               }
               
                //PLAYERS
                
                       // Player CYAN ise player 0 level anlamÃ„Â±na geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player0 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan (RED) 3mlik titan player0 ustune gelir ve player0 random kacarr
                       // Eger random hareket 5m'lik Titan (BLUE) 5mlik titan player0 ustune gelir ve player0 random kacar
                       // Eger random hareket 15'lik Titan (GREEN) 15mlik titan player0 ustune gelir ve player0 random kacar
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), her player'in levellari 1 artar 
                       // Ama diger levellar ile bulusursa level'i ÃƒÂ§ok dÃƒÂ¼Ã…Å¸ÃƒÂ¼k olduÃ„Å¸u icin player0'i icine alirlar
                
                
               if(boardSquares[i][j].getBackground() == CYAN) {
              
                     if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.CYAN); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.CYAN); 
                         
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED ){
                             boardSquares[i+random_x][j+random_y].setBackground(Color.RED);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[random][random].setBackground(CYAN);
                        
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE ){
                             boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[random][random].setBackground(CYAN);
                        
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN ){
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[random][random].setBackground(CYAN);
                        
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.PINK);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.PINK); 
                       
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.PINK);
                             
                             Players.remove(0);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                             
                             Players.remove(0);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE);
                             
                             Players.remove(0);
                   }
                   else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA); 
                             
                             Players.remove(0);
                         
                   }  else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                             Players.remove(0);
                         
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE); 
                             
                             Players.remove(0);
                         
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.YELLOW) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW); 
                             
                             Players.remove(0);
                         
                   }
                     
                   
                   break;
               } 
               
               
                       // Player PINK ise player 1 level anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player1 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan (RED) 3mlik titan player1 ustune gelir ve player1 random kacarr
                       // Eger random hareket 5m'lik Titan (BLUE) 5mlik titan player1 ustune gelir ve player1 random kacar
                       // Eger random hareket 15'lik Titan (GREEN) 15mlik titan player1 ustune gelir ve player1 random kacar
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), player1, player0'i icine alir
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), her player'in levellari 1 artar
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), her player'in levellari 1 artar
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), her player'in levellari 1 artar
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), her player'in levellari 1 artar
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), player1'in level'i 1 artar
               
               if(boardSquares[i][j].getBackground() == PINK) {
                   
                    if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.PINK); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.PINK); 
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.RED);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(CYAN);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(CYAN);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(CYAN);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                             
                             Players.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK){
                             boardSquares[i][j].setBackground(Color.GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                       
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY){
                             boardSquares[i][j].setBackground(Color.GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE); 
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.YELLOW) {
                             boardSquares[i][j].setBackground(Color.GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                   }
                       break;
                  
               }
               
               // Player GRAY ise player 2 level anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player2 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan (RED) 3mlik titan player2 ustune gelir ve player2 random kcarr
                       // Eger random hareket 5m'lik Titan (BLUE) 5mlik titan player2 ustune gelir ve player2 random kacar
                       // Eger random hareket 15'lik Titan (GREEN) 15mlik titan player2 ustune gelir ve player2 random kacar
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), player2, player0'i icine alir
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), her player'in levellari 1 artar
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), her player'in levellari 1 artar
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), her player'in levellari 1 artar
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), her player'in levellari 1 artar
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), player2'in level'i 1 artar
               
               if(boardSquares[i][j].getBackground() == Color.GRAY) {
                   
                   if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.GRAY); 
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.RED);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(PINK);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(PINK);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(PINK);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                             
                             Players.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK){
                             boardSquares[i][j].setBackground(Color.ORANGE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                             boardSquares[i][j].setBackground(Color.ORANGE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.ORANGE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.ORANGE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.ORANGE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.ORANGE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                   }
                   break;
               }
               
                              // Player ORANGE ise player 3 level anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player3 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan (RED) 3mlik titan player3 ustune gelir ve player3 random kacarr
                       // Eger random hareket 5m'lik Titan (BLUE) 5mlik titan player3 ustune gelir ve player3 random kacar
                       // Eger random hareket 15'lik Titan (GREEN) 15mlik titan player3 ustune gelir ve player3 random kacar
                       // Eger random hareket player 0 level ile buluÃ…Å¸uyorsa(CYAN), player3, player0'i icine alir
                       // Eger random hareket player 1 level ile buluÃ…Å¸uyorsa(PINK), her player'in levellari 1 artar
                       // Eger random hareket player 2 level ile buluÃ…Å¸uyorsa(GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 3 level ile buluÃ…Å¸uyorsa(ORANGE), her player'in levellari 1 artar
                       // Eger random hareket player 4 level ile buluÃ…Å¸uyorsa(MAGENTA), her player'in levellari 1 artar
                       // Eger random hareket player 5 level ile buluÃ…Å¸uyorsa(LIGHT GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 6 level ile buluÃ…Å¸uyorsa(WHITE), her player'in levellari 1 artar
                       // Eger random hareket player 7 level ile buluÃ…Å¸uyorsa(YELLOW), player3'in level'i 1 artar
               
               if(boardSquares[i][j].getBackground() == ORANGE){
                   
                   if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.ORANGE);
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.RED);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(GRAY);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(GRAY);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(GRAY);
                             
                   }  else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE); 
                             
                             Players.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK){
                             boardSquares[i][j].setBackground(Color.MAGENTA);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                             boardSquares[i][j].setBackground(Color.MAGENTA);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.MAGENTA);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.MAGENTA);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.MAGENTA);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.MAGENTA);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                   }
                   break;
               } 
               
               // Player MAGENTA ise player 4 level anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player4 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan geliyorsa (RED) 3m'lik titan yok olur. player4 level yukselir player5 olur
                       // Eger random hareket 5m'lik Titan geliyorsa (BLUE) 5mlik titan player4 ustune gelir ve player4 random kacar
                       // Eger random hareket 15'lik Titan geliyorsa (GREEN) 15mlik titan player4 ustune gelir ve player4 random kacar
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), player4, player0'i icine alir
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), her player'in levellari 1 artar
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), her player'in levellari 1 artar
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), her player'in levellari 1 artar
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), her player'in levellari 1 artar
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), player4'in level'i 1 artar
               
               if(boardSquares[i][j].getBackground() == Color.MAGENTA) {
                   
                   if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.MAGENTA); 
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             
                             Titans.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(ORANGE);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(ORANGE);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA); 
                             
                             Players.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK){
                             boardSquares[i][j].setBackground(Color.LIGHT_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                             boardSquares[i][j].setBackground(Color.LIGHT_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.LIGHT_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.LIGHT_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.LIGHT_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.LIGHT_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                   }
                   
                   break;
               } 
               
               // Player LIGHT_GRAY ise player 5 level anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player5 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan geliyorsa (RED) 3m'lik titan yok olur. player5 level yukselir player6 olur
                       // Eger random hareket 5m'lik Titan geliyorsa (BLUE) 5mlik titan player5 ustune gelir ve player4 random kacar
                       // Eger random hareket 15'lik Titan geliyorsa (GREEN) 15mlik titan player5 ustune gelir ve player5 random kacar
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), player5, player0'i icine alÃ„Â±r
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), her player'in levellari 1 artar
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), her player'in levellari 1 artar
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), her player'in levellari 1 artar
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), her player'in levellari 1 artar
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), player5'in level'i 1 artar
               
               if(boardSquares[i][j].getBackground() == Color.LIGHT_GRAY) {
                   
                       if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.LIGHT_GRAY); 
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             
                             Titans.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.BLUE);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                              boardSquares[random][random].setBackground(Color.MAGENTA);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY); 
                             
                             Players.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK){
                             boardSquares[i][j].setBackground(Color.WHITE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                             boardSquares[i][j].setBackground(Color.WHITE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.WHITE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.WHITE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.WHITE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.WHITE);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                   }
                   break;
               } 
               
               // Player WHITE ise player 6 level anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player6 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan geliyorsa (RED) 3m'lik titan yok olur. player6 level yukselir player7 olur
                       // Eger random hareket 5m'lik Titan geliyorsa (BLUE) 3m'lik titan yok olur. player6 level yukselir player7 olur
                       // Eger random hareket 15'lik Titan geliyorsa (GREEN) 15mlik titan player5 ustune gelir ve player6 random kacar
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), player6, player0'i icine alir
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), her player'in levellari 1 artar
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), her player'in levellari 1 artar
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), her player'in levellari 1 artar
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), her player'in levellari 1 artar
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), her player'in levellari 1 artar
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), player6'in level'i 1 artar
               
               if(boardSquares[i][j].getBackground() == Color.WHITE) {
                   
                      
                       if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.WHITE); 
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             
                             Titans.remove(0);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             
                             Titans.remove(0);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GREEN);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[random][random].setBackground(WHITE);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE); 
                             
                             Players.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK){
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                   }
                   break;
               }
               
               // Player YELLOW ise player 7 level anlamina geliyor. 
                       // Eger random hareket engel geliyorsa (BLACK) player7 engelden gecemez ve oldugu yerde kalir.
                       // Eger random hareket 3m'lik Titan geliyorsa (RED) 3m'lik titan yok olur. 
                       // Eger random hareket 5m'lik Titan geliyorsa (BLUE) 5m'lik titan yok olur. 
                       // Eger random hareket 15'lik Titan geliyorsa (GREEN) 15m'lik titan 5cm'lik titana donusur. 
                       // Eger random hareket player 0 level ile bulusuyorsa(CYAN), player7, player0'i icine alir
                       // Eger random hareket player 1 level ile bulusuyorsa(PINK), player1'in leveli 1 artar
                       // Eger random hareket player 2 level ile bulusuyorsa(GRAY), player2'in leveli 1 artar
                       // Eger random hareket player 3 level ile bulusuyorsa(ORANGE), player3'in leveli 1 artar
                       // Eger random hareket player 4 level ile bulusuyorsa(MAGENTA), player4'in leveli 1 artar
                       // Eger random hareket player 5 level ile bulusuyorsa(LIGHT GRAY), player5'in leveli 1 artar
                       // Eger random hareket player 6 level ile bulusuyorsa(WHITE), player6'in leveli 1 artar
                       // Eger random hareket player 7 level ile bulusuyorsa(YELLOW), En yuksek level oldugu icin bir sey olmaz level 7yi icine alir
               
               if(boardSquares[i][j].getBackground() == Color.YELLOW) {
                   
                      
                       if(boardSquares[i+random_x][j+random_y].getBackground() == DARK_GRAY) {
                           boardSquares[i][j].setBackground(Color.DARK_GRAY);
                           boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW); }
                     
                     else if(boardSquares[i+random_x][j+random_y].getBackground() == BLACK ) {
                            boardSquares[i][j].setBackground(Color.YELLOW); 
                            
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == RED) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             
                             Titans.remove(0);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == BLUE) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             
                             Titans.remove(0);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == GREEN) {
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                             boardSquares[i][j].setBackground(Color.BLUE);
                             
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == CYAN) {
                             boardSquares[i][j].setBackground(Color.DARK_GRAY);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE); 
                             
                             Players.remove(0);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == PINK){
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.GRAY); 
                       
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == GRAY) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.ORANGE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == ORANGE) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.MAGENTA);
                             
                   } else if(boardSquares[i+random_x][j+random_y].getBackground() == MAGENTA) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.LIGHT_GRAY);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == Color.LIGHT_GRAY) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.WHITE);
                             
                   }else if(boardSquares[i+random_x][j+random_y].getBackground() == WHITE) {
                             boardSquares[i][j].setBackground(Color.YELLOW);
                             boardSquares[i+random_x][j+random_y].setBackground(Color.YELLOW);
                   }
                   break;
               }
                
               
               
//               
                 revalidate();
                 repaint();
 
                   }
                      
               }
       
          } } 
         try {
                    checkWinner();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DataStructureHW1.class.getName()).log(Level.SEVERE, null, ex);
                }
         
         
         // Bu kisimda sadece Player ve sadece Titanlar kaldiginda bitirilmesi icin yaptim 
         // Herhangi birisi kazanirsa dialog cikip hangisinin kazandigii soyluyor ve OK secenegine tiklayinca sistemden cikiyor
         
         // Titans ve Players diye ArrayList olusturup yaptiklari hareketlere gore remove ve add yaptim 
        
    } private void checkWinner() throws InterruptedException {
         
        if(Titans.size()==0){

 int input = JOptionPane.showOptionDialog(null, "PLAYERS KAZANDI", "OYUN BITTI", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if(input == JOptionPane.OK_OPTION)
            {
                Thread.sleep(1000);
                System.exit(0); 
                
                } 
            }
        
        if(Players.size()==0){

            int input = JOptionPane.showOptionDialog(null, "TITANS KAZANDI", "OYUN BITTI", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

            if(input == JOptionPane.OK_OPTION)
            {
                Thread.sleep(1000);
                System.exit(0); 
                
                }
            
            }
          
    } 


private static class GameWindow {

        public GameWindow() {
        }
    }public final JComponent getGameBoard() {
    return board;
    }

    public final JComponent getGui() {
        return playArea;
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Alanin bir keranarini giriniz");
        uzunluk = scan.nextInt();
        
        System.out.println("Oyuncu icin sayi giriniz");
        player = scan.nextInt();
        
        System.out.println("3m Titanlar icin sayi giriniz");
        titan3m = scan.nextInt();
        
        System.out.println("5m Titanlar icin sayi giriniz");
        titan5m = scan.nextInt();
        
        System.out.println("15 titanlar icin sayi giriniz");
        titan15m = scan.nextInt();
        
        System.out.println("Engel icin sayi giriniz");
        engel = scan.nextInt();
        

        DataStructureHW1 window = new DataStructureHW1();

        JFrame frame = new JFrame("Homework1");
        frame.setResizable(false);
        frame.setContentPane(window.getGui());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();

        frame.setVisible(true);
    }
    
}

