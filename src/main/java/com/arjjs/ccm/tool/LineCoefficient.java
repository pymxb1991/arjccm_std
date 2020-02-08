package com.arjjs.ccm.tool;

public class LineCoefficient {

	/**
	 * 最小二乘法 线性回归
	 * y = a x + b 
	 * 
	 * b = sum( y ) / n - a * sum( x ) / n
	 * 
	 * a = ( n * sum( xy ) - sum( x ) * sum( y ) ) / ( n * sum( x^2 ) - sum(x) ^ 2 )
	 * 
	 * @author tian.yj
	 * var nameArr = [],
	        DataArr = [];
	        var data1 = ${ccmPeopleAmount.nativeFloating};
	        for(var i=0;i<data1.length;i++){
	        	nameArr.push(data1[i].place)
	        	DataArr.push({
	        		value:parseInt(data1[i].num),
	        		name:data1[i].place
	        	})
	        }
	 */ 

	// eList1

	public static void main(double[] args) { 
        //double[] x = { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12} ; 
        //double[] y = {23 , 44 , 32 , 56 , 33 , 34 , 55 , 65 , 45 , 55 , 11 , 12 } ; 
		double[] x = new double[args.length];
		double[] y = new double[args.length];
        for(int i=0 ; i<args.length ; i++){
        	x[i] = i + 1.0;
        	y[i] = Double.valueOf(args[i]);
        }
        
        System.out.println( estimate(x, y, x.length )); 
    } 
	
	public static double lineCoefficientNum(double[] args) { 
        //double[] x = { 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12} ; 
        //double[] y = {23 , 44 , 32 , 56 , 33 , 34 , 55 , 65 , 45 , 55 , 11 , 12 } ; 
		double[] x = new double[args.length];
		double[] y = new double[args.length];
        for(int i=0 ; i<args.length ; i++){
        	x[i] = i + 1.0;
        	y[i] = Double.valueOf(args[i]);
        }
        double num =  estimate(x, y, x.length ) ;
        System.out.println("回归算法"); 
        System.out.println(num); 
        System.out.println("回归算法");
		return num; 
    } 
     
    /**
     * 预测
     * @param x
     * @param y
     * @param i
     * @return
     */ 
    public static double estimate( double[] x , double[] y , int i ) { 
        double a = getXc( x , y ) ; 
        double b = getC( x , y , a ) ; 
        return a * i + b ; 
    } 
     
    /**
     * 计算 x 的系数
     * @param x
     * @param y
     * @return
     */ 
    public static double getXc( double[] x , double[] y ){ 
        int n = x.length ; 
        return ( n * pSum( x , y ) - sum( x ) * sum( y ) )  
                / ( n * sqSum( x ) - Math.pow(sum(x), 2) ) ; 
    } 
     
    /**
     * 计算常量系数
     * @param x
     * @param y
     * @param a
     * @return
     */ 
    public static double getC( double[] x , double[] y , double a ){ 
        int n = x.length ; 
        return sum( y ) / n - a * sum( x ) / n ; 
    } 
     
    /**
     * 计算常量系数
     * @param x
     * @param y
     * @return
     */ 
    public static double getC( double[] x , double[] y ){ 
        int n = x.length ; 
        double a = getXc( x , y ) ; 
        return sum( y ) / n - a * sum( x ) / n ; 
    } 
     
    private static double sum(double[] ds) { 
        double s = 0 ; 
        for( double d : ds ) s = s + d ; 
        return s ; 
    } 
     
    private static double sqSum(double[] ds) { 
        double s = 0 ; 
        for( double d : ds ) s = s + Math.pow(d, 2) ; 
        return s ; 
    } 
     
    private static double pSum( double[] x , double[] y ) { 
        double s = 0 ; 
        for( int i = 0 ; i < x.length ; i++ ) s = s + x[i] * y[i] ; 
        return s ; 
    } 
} 


		
