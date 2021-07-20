
//Julia class
public class Julia {

	private double x1;			//start of real range
	private double x2;			//end of real range
	private double y1;			//start of complex range
	private double y2;			//end of complex range
	private int iter;			//no of iterations
	private double xc;			//real part of C
	private double yc;			//imaginary part of C
	
	
	
	//constructor method for Julia class
	public Julia(double x1, double x2, double y1, double y2, int iter, double xc, double yc) {	
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;	
		this.iter = iter;
		this.xc = xc;
		this.yc = yc;		
	}
	
	
	//method to return whether point is in set or not
	public boolean check(int i, int j, int width, int height) {
		
		//map pixel point (i,j) to corresponding coordinate in region of interest (x,y)
		double x = (((double)i*(x2-x1))/width)-Math.abs(x1);
		double y = (((double)(height-j)*(y2-y1))/height)-Math.abs(y1);
		
		//initially Z = x+jy
		double zx = x;
		double zy = y;
		
		
		//compute Z_new = Z^2 + C for iter times : Z_new = zx_new + j(zy_new) , Z = zx +j(zy) , C = xc +j(yc) 
		for (int k=0; k<=iter; k++) {
			
			//zx_new = zx^2 - zy^2 + x
			double zx_new = Math.pow(zx,2) - Math.pow(zy,2) + xc ;
			
			//zy_new = 2*(zx)*(zy) + y
			double zy_new = 2*(zx)*(zy) + yc;
			
			
			//check if ABS(Z_new) > 4 
			if (Math.pow(zx_new,2) + Math.pow(zy_new,2) > 4) {
				return false;
			}
			
			//assign Z_new value to Z for next iteration
			zx = zx_new;
			zy = zy_new;
			
		}	
		
		return true;
	}
}
