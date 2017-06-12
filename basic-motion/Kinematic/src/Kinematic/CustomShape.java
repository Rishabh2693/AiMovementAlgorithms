package Kinematic;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

class CustomShape extends SteeringParams{
	  PApplet parent;
	  float maxAcc;
	  float maxSpeed;
	  ArrayList<PVector> breadcrumb = new ArrayList<PVector>();
	 
	  CustomShape(float x, float y,PApplet p) {
	    acceleration = new PVector(0,0);
	    position = new PVector(x,y);
	    velocity = new PVector(0,0);
	    maxSpeed = 4;
	    maxAcc = (float) 0.1;
	    parent = p;
	  }
	 
	  void update() {
		position.add(velocity);
	    velocity.add(acceleration);
	    velocity.limit(maxSpeed);
	    acceleration.mult(0);
	  }
	 
	 void arrive(PVector target,float ros,float rod) {
	    PVector dir = PVector.sub(target,position);
	 
	    float dist = dir.mag();
	    dir.normalize();
	    if (dist < ros)
	    	dir.mult(0);
	    else if (dist < rod) {
	      float m = PApplet.map(dist,0,rod,0,maxSpeed);
	      dir.mult(m);
	    } else {
	      dir.mult(maxSpeed);
	    }
	 
	    PVector acc = PVector.sub(dir,velocity);
	    acc.limit(maxAcc);
	    acceleration.add(acc);
	  }
	 	
	  void drawBreadcrumbs(){
		  if(parent.frameCount%20==0){
			  breadcrumb.add(new PVector(position.x+50,position.y+940));
		  }
		  for(PVector a:breadcrumb){
			  parent.beginShape();
			  parent.ellipse(a.x, a.y, 10, 10);
			  parent.endShape();
		  }
			  
	  }
	  void display() {
	    setOrientation(velocity.heading() + PApplet.PI/2);
	    parent.pushMatrix();
	    parent.translate(position.x+50,position.y+940);
	    parent.rotate(getOrientation());
	    parent.beginShape();
	    parent.fill(0);
	  	parent.triangle(-35,-15,35,-15,0,-70);
	  	parent.ellipse(0, 0, 75, 75);
	    parent.endShape();
	    parent.popMatrix();
	  }
}