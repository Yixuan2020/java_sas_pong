
/**
 * Write a description of class Main here.
 * steuern
 * @author Yixuan
 * @version (a version number or a date)
 */

import sas.*;
import java.awt.Color;
import javax.sound.sampled.*;
public class Main
{
    
    View fenster;
    Circle kreis;
    Rectangle paddle;
    Rectangle paddle_two;
    Text scoreboard_one;
    Text scoreboard_two;
    
    
    int fenster_x = 800;
    int fenster_y = 600;//4:3 seiten verhältnis//
    
    int move_x = 2;
    int move_y = 2;
    
    int score_one = 0;
    int score_two = 0;
    int speed_ball = 5;
    int radius = 10;
    /**
     * Constructor for objects of class Main
     */
    public Main()
    {
            fenster = new View(fenster_x, fenster_y,"Ball");
            
            kreis = new Circle(400, 100, radius,Color.WHITE);
            
            paddle = new Rectangle(fenster_x-50, fenster_y/2, 20, 90,Color.WHITE);
            
            paddle_two = new Rectangle(30, fenster_y/2+50, 20, 90,Color.WHITE);
            
            scoreboard_one = new Text(fenster_x/2-20, 20, Integer.toString(score_one), Color.WHITE);
            scoreboard_two = new Text(fenster_x/2+20, 20, Integer.toString(score_two), Color.WHITE);
                        
            fenster.setBackgroundColor(Color.BLACK);
            while (true){
                ball_move_loop();
                pong_paddle_move();
                pong_paddle_two_move();
                check_touch();
                speed_con();
                fenster.wait(10);
            }
    }
    public void ball_move_loop()
    {
        

        
            double kreis_edge_x = kreis.getShapeX() + 2*radius;
            double kreis_edge_y = kreis.getShapeY() + 2*radius;
            System.out.println(kreis.getShapeY()+"kreis pos");
            if (kreis_edge_x >= fenster.getWidth()){
                move_x = -move_x;
                score_one = score_one + 1;
                scoreboard_one.setText(Integer.toString(score_one));
                }
            else if (kreis_edge_x-2*radius <= 0){
                move_x = -move_x;
                score_two = score_two + 1;
                scoreboard_two.setText(Integer.toString(score_two));
            }
            else if (kreis_edge_y-2*radius <= 0 || kreis_edge_y >= fenster.getHeight()){
                move_y = -move_y;
            
            }
            kreis.move(move_x, move_y);
        
    }
    public void pong_paddle_move()
    {
        if (fenster.keyDownPressed() == true){
            paddle.move(0,3);
        }
        else if (fenster.keyUpPressed() == true){
            paddle.move(0,-3);
        }
    }
        public void pong_paddle_two_move()
    {
        if (fenster.keyPressed('s') == true){
            paddle_two.move(0,4);
        }
        else if (fenster.keyPressed('w') == true){
            paddle_two.move(0,-4);
        }
    }
        public void check_touch()
    {
        System.out.println(paddle.getShapeY()+"touch");
        if (kreis.intersects(paddle))
        {
            move_x = -2;
        }
        if (kreis.intersects(paddle_two))
        {
            move_x = 2;
        }
    }
    public void speed_con()
    {
        if (fenster.keyPressed('t') == true){
            move_x++;
            move_y++;
        }
    }
    public static void main(String[] args)
    {
        new Main();
    }
}