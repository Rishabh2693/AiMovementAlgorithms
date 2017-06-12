package Flocking;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
public class FlockingBehaviour extends PApplet {
	PVector dest;
	public static void main(String[] args){
		    PApplet.main("Flocking.FlockingBehaviour");
		  }
	  		
		  public void settings(){
		    size(1000,1000);
		  }
		  ArrayList<Flock> flocks;
		  Flock f1;
		  Flock f2;
		  public void setup() {
			 flocks = new ArrayList<Flock>();  
		        for (int i = 0; i < 100; i++) {
		      flocks.add(new Flock(width/2,height/2,this));
		      
		    }
		        f1 = new Flock(width/2,height/2,this);
		        f2 = new Flock(width/2,height/2,this);
		    }

		  public void draw() {
				  
		    background(255);
		   
		    f1.wander(random(-.1f,.1f), random(-.1f,.1f));
		    f2.wander(random(-.1f,.1f), random(-.1f,.1f));
		    for (Flock f : flocks){
		    	
		    	 if(f.position.x<0)
		    		 f.position.x=1000;
				  if(f.position.y<0)
					  f.position.y=1000;
				  if(f.position.x>1000)
					  f.position.x=0;
				  if(f.position.y>1000)
					  f.position.y=0;
				  
			
			f.flock(flocks);
		    f.update();
		    f.display();
			
		    }
		  }


}
