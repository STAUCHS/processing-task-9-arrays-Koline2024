import processing.core.PApplet;

public class Sketch extends PApplet {

  float[] snowX = new float[42];
  float[] snowY = new float[42];
  boolean[] snowHide = new boolean [42];
  int intSnowDiameter = 30;
  int intGrav = 0;
  float fltX = 400;
  float fltY = 400;
  float fltVelocityX = 0;
  float fltVelocityY = 0;
  int intLives = 3;
  boolean blnHit = false;
  boolean blnOver = false;
  boolean blnClick = false;

  public void settings() {
    size(800, 800);
  }

  public void setup() {
    background(0);

    for(int i = 0; i < snowX.length; i++){
      snowX[i] = random(width);
      snowY[i] = random(height) - 50;
      snowHide[i] = false;
    }
  }

  public void draw() {
    background(0);

    if(blnOver == true){
    background(255);
    return;
    }

    fill(255);
    snow(intGrav);
    collision();
    lives();
  }
  
  /**
   * Snow method draws snow as well as resets it in collisions.
   * @param intGrav
   * @author ChrisX
   */
  public void snow(int intGrav){

    fltX += fltVelocityX;
    fltY += fltVelocityY;
    fill(0, 0, 255);
    ellipse(fltX, fltY, 20, 20);

    for(int i = 0; i < snowX.length; i++){

      fill(255);
      ellipse(snowX[i], snowY[i], intSnowDiameter, intSnowDiameter);
      if(snowHide[i] == true){
        snowY[i] = 0;
        snowX[i] = random(width);
        snowHide[i] = false;
      }
      snowY[i] += 5 + intGrav;
      if(snowY[i] > height){
        snowY[i] = 0;
        snowX[i] = random(width);
      }
    }
  }

  /**
   * Collision method deals with collisions between snowflakes.
   * @author ChrisX
   */
  public void collision(){
    for(int i = 0; i < snowX.length; i++){
      if(dist(fltX, fltY, snowX[i], snowY[i]) < 25){
        blnHit = true;
        snowHide[i] = true;
        intLives -= 1;
      } if(blnClick == true){
        if(dist(mouseX, mouseY, snowX[i], snowY[i]) < 15){
          snowHide[i] = true;
          blnClick = false;
        }
      } 
    }
  }
/**
   * Lives method computes and displays lives on the screen.
   * @author ChrisX
   */
  public void lives(){
    for(int i = 0; i < snowX.length; i++){
      if(intLives == 3){
        fill(255, 0, 0);
        rect(750, 0, 50, 50);   
        rect(690, 0, 50, 50);
        rect(630, 0, 50, 50);
        }if(intLives == 2){
        fill(255, 0, 0);
        rect(690, 0, 50, 50);
        rect(630, 0, 50, 50);
        }if(intLives == 1){
        fill(255, 0, 0);
        rect(630, 0, 50, 50);
        }if(intLives == 0){
          blnOver = true;
        }
    }
  }
  
/**
 * Keypressed detects when a key is pressed and runs the corresponding code. 
 * @author ChrisX
 */
  public void keyPressed(){
    if(keyCode == DOWN){
      intGrav = 6;
    } if(keyCode == UP){
      intGrav = -3;
    } if(key == 'w'){
      fltVelocityY = -5;
    } if(key == 's'){
      fltVelocityY = 5;
    } if(key == 'a'){
      fltVelocityX = -5;
    } if(key == 'd'){
      fltVelocityX = 5;
    }
  }

/**
 * Keyreleased detects when a key is released and sets the corresponding inputs to zero. 
 * @author ChrisX
 */
  public void keyReleased(){
    if(keyCode == DOWN){
      intGrav = 0;
    } if(keyCode == UP){
      intGrav = 0;
    } if(key == 'w'){
      fltVelocityY = 0;
    } if(key == 's'){
      fltVelocityY = 0;
    } if(key == 'a'){
      fltVelocityX = 0;
    } if(key == 'd'){
      fltVelocityX = 0;
    } 
  }
 
  public void mouseClicked(){
    blnClick = true;
  }
}