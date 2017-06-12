package Flocking;

import java.util.ArrayList;


import processing.core.PApplet;
import processing.core.PVector;

//reference: https://processing.org/examples/flocking.html

public class Flock extends SteeringParams {
	 PApplet parent;	
	  float maxAcc;    
	  float maxSpeed;    

	    Flock(float x, float y,PApplet p) {
	    acceleration = new PVector(0, 0);
	    parent = p;
	    setOrientation(parent.random(2*PApplet.PI));
	    velocity = new PVector(PApplet.cos(getOrientation()), PApplet.sin(getOrientation()));
	    position = new PVector(x, y);
	    maxSpeed = 4;
	    maxAcc = (float) 0.1;
	  }

	  void flock(ArrayList<Flock> flocks) {
	    PVector sep = separate(flocks);   
	    PVector ali = align(flocks);      
	    PVector coh = cohesion(flocks);   
	    sep.mult((float) 2.3);
	    ali.mult((float) 1.5);
	    coh.mult((float) 1.5);
	    acceleration.add(sep);
	    acceleration.add(ali);
	    acceleration.add(coh);
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
			System.out.println(rand1+" "+rand2);
		    velocity2.normalize();
		    velocity2.mult(maxSpeed);	 
		    PVector acc = PVector.sub(velocity2,velocity);
		    acc.limit(maxAcc);
		    acceleration.add(acc);
		  }
	  
	   PVector seek(PVector target) {
	    PVector dir = PVector.sub(target, position); 
	    dir.normalize();
	    dir.mult(maxSpeed);
	    PVector acc = PVector.sub(dir, velocity);
	    acc.limit(maxAcc); 
	    return acc;
	  }
	   PVector separate (ArrayList<Flock> flocks) {
		   float separation = (float) 40.0;
		   PVector acc = new PVector(0, 0, 0);
		   int count = 0;
		   
		   for (Flock other : flocks) {
		      float dist = PVector.dist(position, other.position);
		      if ((dist > 0) && (dist < separation)) {
		    	  PVector dir = PVector.sub(position, other.position);
		    	  dir.normalize();
		    	  dir.div(dist);       
		    	  acc.add(dir);
		    	  count++;           
		      }
		    }
		    if (count > 0) {
		    	acc.div((float)count);
		    }
		    if (acc.mag() > 0) {
		    	acc.normalize();
		    	acc.mult(maxSpeed);
		    	acc.sub(velocity);
		    	acc.limit(maxAcc);
		    }
		    return acc;
		  } 
	   PVector align (ArrayList<Flock> flocks) {
		    float neighbordistance = 100;
		    PVector acc = new PVector(0, 0);
		    int count = 0;
		    for (Flock other : flocks) {
		    	float dist = PVector.dist(position, other.position);
		    	if ((dist > 0) && (dist < neighbordistance)) {
		    		acc.add(other.velocity);
		    		count++;
		    	}
		    	}
		    	if (count > 0) {
		    		acc.div((float)count);
		    		acc.normalize();
		    		acc.mult(maxSpeed);
		    		acc = PVector.sub(acc, velocity);
		    		acc.limit(maxAcc);
		    		return acc;
		    	} 
		    	else {
		    		return new PVector(0, 0);
		    	}
		  	}

		  PVector cohesion (ArrayList<Flock> flocks) {
			  float roc = 100;
			  PVector acc = new PVector(0, 0);   
			  int count = 0;
			  for (Flock other : flocks) {
		      float dist = PVector.dist(position, other.position);
		      if ((dist > 0) && (dist < roc)) {
		    	  acc.add(other.position);
		    	  count++;
		      }
		    }
			  if (count > 0) {
				  acc.div(count);
				  return seek(acc);  
			  } 
			  else {
				  return new PVector(0, 0);
			  }
		  }
		  
	  void display() {
	        setOrientation(velocity.heading() + PApplet.PI/2);
		    parent.pushMatrix();
		    parent.translate(position.x,position.y);
		    parent.rotate(getOrientation());
		    parent.beginShape();
		    parent.fill(0);
		  	parent.triangle(-7,-3,7,-3,0,-14);
		  	parent.ellipse(0, 0, 15, 15);
		    parent.endShape();
		    parent.popMatrix();
	  }


}
