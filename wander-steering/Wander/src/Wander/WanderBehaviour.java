package Wander;

import processing.core.PApplet;
import processing.core.PVector;
import Wander.CustomShape;

public class WanderBehaviour extends PApplet {
	CustomShape cS;
	float num1,num2;
	public static void main(String[] args){
		    PApplet.main("Wander.WanderBehaviour");
		  }
	  		
		  public void settings(){
		    size(1000,1000);
		  }


		  public void setup() {
		    // Initialize all "stripes"
			  cS = new CustomShape(mouseX,mouseY,this);	
			  num1=0;num2=0;
		  }

		  public void draw() {
			  if(cS.position.x<-500)
				  cS.position.x=500;
			  if(cS.position.y<-500)
				  cS.position.y=500;
			  if(cS.position.x>500)
				  cS.position.x=-500;
			  if(cS.position.y>500)
				  cS.position.y=-500;
			  
			  background(255);
			  
			  if(frameCount%10==0){
				  num1 = random((float)-.1,(float).1);
				  	num2 = random((float)-.1,(float).1);
				   
		  } 
			  cS.wander(num1,num2);
			 // cS.wander2(num1);
			  cS.update();
			  cS.drawBreadcrumbs();
		      cS.display();
		     
		  }
}
