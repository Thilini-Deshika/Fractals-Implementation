

import java.awt.*; /* java abstract window toolkit */
import javax.swing.*;
import java.awt.geom.Line2D;


//Main class
//create fractal class : inherit JPanel 
class Fractal extends JPanel { 

	//initialize parameters for Fractal Class
	private int width = 800;	//width of plot in pixels
	private int height = 800;	//height of plot in pixels
	private double x1;			//start of real range
	private double x2;			//end of real range
	private double y1;			//start of complex range
	private double y2;			//end of complex range
	private int frac_type;		//type of the fractal : Mandelbrot=1 , Julia=2
	private boolean inset;		//whether the point is in considered fractal set or not
	private int iter;			//no of iterations
	private double xc;			//real part of C : only for Julia set
	private double yc;			//imaginary part of C : only for Julia set
	
	
	//initialize Mandelbrot object
	Mandelbrot mdb = null;
	
	//constructor method for Fractal class : if Mandelbrot set 
	public Fractal(double x1, double x2, double y1, double y2, int iter) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.iter = iter;
		frac_type = 1;
		
		//create Mandelbrot object
		mdb = new Mandelbrot(x1,x2,y1,y2,iter);
		
		//set plot size
		setPreferredSize(new Dimension(width, height)); 
	}
	
	
	
	//initialize Julia object
	Julia jul = null;
	
	//constructor method for Fractal class : if Julia set
	public Fractal(double xc, double yc, int iter) {
		this.x1 = -1;
		this.x2 = 1;
		this.y1 = -1;
		this.y2 = 1;
		this.xc = xc;
		this.yc = yc;
		this.iter = iter;
		frac_type = 2;
		
		//create Mandelbrot object
		jul = new Julia(x1,x2,y1,y2,iter,xc,yc);
		
		//set plot size
		setPreferredSize(new Dimension(width, height)); 		
		
	}
	
	
	
	
	//call paintComponent method
	public void paintComponent(Graphics g) {
		
		
		for(int i=0; i<=width; i++) {
			
			for(int j=0; j<=height; j++) {
				
				//if  the fractal type is Mandelbrot 
				if(frac_type == 1) {
					
					//check whether point is in Mandelbrot set or not
					inset = mdb.check(i,j,width,height);
									
				}
				
				
				//if  the fractal type is Julia 
				if(frac_type == 2) {
					
					//check whether point is in Julia set or not
					inset = jul.check(i,j,width,height);
					
				}
				
				
				//paint the point
				//if point is in set : color the point in black
				if(inset){
					g.setColor(Color.BLACK); 
				    ((Graphics2D) g).draw(new Line2D.Double(i,j,i,j)); 
				}
				
				//if point is not in set : color the point in white
				else{
					g.setColor(Color.WHITE); 
				    ((Graphics2D) g).draw(new Line2D.Double(i,j,i,j)); 
				}
				
			}
		}
		
	} 
	
	
	
	
	//main method
	public static void main(String [] args) { 	
		
		//check no of input arguments : should be at least 1
		if(args.length > 0){
			
			
			//initialize frame
			JFrame frame = null;
			
			
			//Mandelbrot set
			if(args[0].equals("Mandelbrot")) {
				
				//initialize parameters for Mandelbrot set
				double x1 = 0;		//start of real range
				double x2 = 0;		//end of real range
				double y1 = 0;		//start of complex range
				double y2 = 0;		//end of complex range
				int iter = 0;		//no of iterations
				
				
				//if user give 1 input arguments ---> use default value for all parameters
				if(args.length == 1) {
					x1 = -1;
					x2 = 1;
					y1 = -1;
					y2 = 1;
					iter = 1000;
				}
				
				
				//if user give 5 input arguments ---> use default value for iterations, user inputs for range parameters
				else if(args.length == 5) {
					x1 = Double.parseDouble(args[1]);
					x2 = Double.parseDouble(args[2]);
					y1 = Double.parseDouble(args[3]);
					y2 = Double.parseDouble(args[4]);
					iter = 1000;				
				}
				
				
				//if user give 6 input arguments ---> use user input values for all parameters
				else if(args.length == 6) {
					x1 = Double.parseDouble(args[1]);
					x2 = Double.parseDouble(args[2]);
					y1 = Double.parseDouble(args[3]);
					y2 = Double.parseDouble(args[4]);
					iter = Integer.parseInt(args[5]);
				}
				
				
				//incorrect no of input arguments for Mandelbrot set
				else {
					System.out.println("\nIncorrect no of input arguments for Mandelbrot set");
					System.exit(0);
				}
				
				
				//check validity of range : valid if x1<x2 and y1<y2
				if(x1>=x2 || y1>=y2) {
					System.out.println("\nInvalid range given for real part or complex part");
					System.exit(0);
				}
				
				
				
				//create frame for Mandelbrot set
				frame = new JFrame("Mandelbrot Set"); 
				
				// set the content of the frame 
				frame.setContentPane(new Fractal(x1,x2,y1,y2,iter)); 
				
			}
			
			
			
			
			
			//Julia set
			else if(args[0].equals("Julia")) {
				
				//initialize parameters for Julia set
				double xc = 0;		//real part of C
				double yc = 0;		//imaginery part of C
				int iter = 0;		//no of iterations
				
				
				//if user give 1 input arguments ---> use default value for all parameters
				if(args.length == 1) {
					xc = -0.4;
					yc = 0.6;
					iter = 1000;
				}
				
				
				//if user give 3 input arguments ---> use default value for iterations, user inputs for C values : xc,yc
				else if(args.length == 3) {
					xc = Double.parseDouble(args[1]);
					yc = Double.parseDouble(args[2]);
					iter = 1000;
				}
				
				
				//if user give 4 input arguments ---> use user input values for all parameters
				else if(args.length == 4) {
					xc = Double.parseDouble(args[1]);
					yc = Double.parseDouble(args[2]);
					iter = Integer.parseInt(args[3]);
				}
				
				
				//incorrect no of input arguments for Julia set
				else {
					System.out.println("\nIncorrect no of input arguments for Julia set");
					System.exit(0);
				}
				
				
				
				//create frame for Julia set
				frame = new JFrame("Julia Set"); 
				
				
				// set the content of the frame 
				frame.setContentPane(new Fractal(xc,yc,iter)); 
								
			}
			
			
			
				
			//incorrect fractal type
			else {
				System.out.println("\nGiven fractal type is not found");
				System.exit(0);
			}
			
			
			//set frame properties
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			frame.pack(); 
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true); 
			
			
		}
		
		//not enough input arguments
		else {
			System.out.println("\nNot enough input arguments");
			System.exit(0);
		}
		
	}
}
