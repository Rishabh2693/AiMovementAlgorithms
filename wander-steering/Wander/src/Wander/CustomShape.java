package Wander;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

class CustomShape extends SteeringParams{
	  PApplet parent;
	  ArrayList<PVector> breadcrumb = new ArrayList<PVector>();
		 
	  float maxAcc;
	  float maxSpeed;
	 
	  CustomShape(float x, float y,PApplet p) {
	    acceleration = new PVector(0,0);
	    velocity = new PVector(1,1);
	    position = new PVector(x,y);
	    maxSpeed = 2;
	    maxAcc = (float) .1;
	    parent = p;
	  }
	 
	  void update() {
		position.add(velocity);
		velocity.add(acceleration);
	    velocity.limit(maxSpeed);
	    acceleration.mult(0);
	  }
	 
	  
	 
	 void wander(float rand1,float rand2) {
		PVector velocity2 = velocity.copy();
		velocity2.rotate(rand1-rand2);
	    velocity2.normalize();
	    velocity2.mult(maxSpeed);	 
	    PVector acc = PVector.sub(velocity2,velocity);
	    acc.limit(maxAcc);
	    acceleration.add(acc);
	    allign();
	  }
	
	 //test wander
	 void wander2(float rand1) {
			PVector velocity2 = velocity.copy();
			velocity2.rotate(rand1);
		    velocity2.normalize();
		    velocity2.mult(maxSpeed);	 
		    PVector acc = PVector.sub(velocity2,velocity);
		    acc.limit(maxAcc);
		    acceleration.add(acc);
		    allign();
		  }
	 
	 
	 void drawBreadcrumbs(){
		  if(parent.frameCount%20==0){
			  breadcrumb.add(new PVector(position.x+500,position.y+500));
		  }
		  for(PVector a:breadcrumb){
			  parent.beginShape();
			  parent.ellipse(a.x, a.y, 10, 10);
			  parent.endShape();
		  }
	  }	 
	void allign(){
		setOrientation(velocity.heading() + PApplet.PI/2);
	}
	 
	  void display() {
	    parent.pushMatrix();
	    parent.translate(position.x+500,position.y+500);
	    parent.rotate(getOrientation());
	    parent.beginShape();
	    parent.fill(0);
	  	parent.triangle(-35,-15,35,-15,0,-70);
	  	parent.ellipse(0, 0, 75, 75);
	    parent.endShape();
	    parent.popMatrix();
	  }
}