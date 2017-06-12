package Arrive;
import processing.core.PApplet;
import processing.core.PVector;
public class ArriveBehaviour extends PApplet {
	CustomShape cS;
	PVector dest;
	public static void main(String[] args){
		    PApplet.main("Arrive.ArriveBehaviour");
		  }
	  		
		  public void settings(){
		    size(1000,1000);
		  }

		  public void setup() {
		   
			   dest = new PVector(0,0);
			  cS = new CustomShape(mouseX,mouseY,this);	
		    
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
			  if(mousePressed==true)
				  dest = new PVector(mouseX-500,mouseY-500);
			  cS.arrive(dest,3,100);
			  cS.update();
			  cS.drawBreadcrumbs();
		      cS.display();
		      
		  }


}
