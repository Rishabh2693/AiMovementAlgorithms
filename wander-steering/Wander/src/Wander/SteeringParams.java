package Wander;

import processing.core.PVector;
//Steering Data Structure
public class SteeringParams {
	public PVector position;
	public PVector velocity;
	public PVector acceleration;
	private float angularVelocity;
	private float angularAcceleration;
	private float Orientation;
	SteeringParams(){
		this.position = new PVector(0,0);
		this.velocity = new PVector(0,0);
		this.acceleration = new PVector(0,0);
		this.angularAcceleration = 0;
		this.angularVelocity = 0;
		this.Orientation = 0;
		
	}
	SteeringParams(PVector pos,PVector vel,PVector acc,float aV,float aA, float ang){
		this.position = pos;
		this.velocity = vel;
		this.acceleration = acc;
		this.angularAcceleration = aV;
		this.angularVelocity = aA;
		this.Orientation = ang;
		
	}
	void setPosition(PVector pos){
		 this.position=pos;
	}
	void setVelocity(PVector vel){
		this.velocity=vel;
	}
	void setAcceleration(PVector acc){
		this.acceleration=acc;
	}
	void setAngularVelocity(float aV){
		this.angularVelocity = aV;
	}
	void setAngularAcceleration(float aA){
		this.angularAcceleration = aA;
	}
	void setOrientation(float ang){
		this.Orientation=ang;
	}
	PVector getPosition(){
		return this.position;
	}
	PVector getVelocity(){
		return this.velocity;
	}
	PVector getAcceleration(){
		return this.acceleration;
	}
	float getAngularVelocity(){
		return this.angularVelocity;
	}
	float getAngularAcceleration(){
		return this.angularAcceleration;
	}
	float getOrientation(){
		return this.Orientation;
	}
	
}
