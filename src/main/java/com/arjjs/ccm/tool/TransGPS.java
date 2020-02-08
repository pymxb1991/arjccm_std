package com.arjjs.ccm.tool;

import java.text.ParseException;

/**
 * 坐标系转换
 * @author pengjianqiang
 *
 */
public class TransGPS {

	// ?lat=38.48289&lng=106.202932

	public static void main(String args[]) throws ParseException,
			InterruptedException {
		TransGPS ins = new TransGPS();
		ins.setBaidulat(39.039255);
		ins.setBaidulng(117.663396);
		System.out.println(ins.zhuanhuan());

	}
	private double baidulat = 0;
	private double baidulng = 0;
	

	public double getBaidulat() {
		return baidulat;
	}

	public void setBaidulat(double baidulat) {
		this.baidulat = baidulat;
	}

	public double getBaidulng() {
		return baidulng;
	}

	public void setBaidulng(double baidulng) {
		this.baidulng = baidulng;
	}

	public String zhuanhuan() {
		Location baidu = new Location();
		baidu.setLat(baidulat);
		baidu.setLng(baidulng);
		return zhuanhuan(baidu);
	}

	public String zhuanhuan(Location baidu) {

		Location result = new TransGPS().BD_WGS(baidu);//WGS_BD(baidu);
		System.out.println("baidulat:" + baidulat + "  baidulng:" + baidulng);
		System.out.println("gpslat:" + result.getLat() + "  gpslng:"
				+ result.getLng());
		return result.getLng() + "," + result.getLat();
	}

	// 说明：
	// ///WGS----->GCJ
	// transformFromWGSToGCJ
	//
	// ///GCJ------>WGS
	// transformFromGCJToWGS
	//
	// ///GCJ------->BD
	// bd_encrypt
	//
	// ///BD--------->GCJ
	// bd_decrypt
	//
	// ///WGS------>BD
	// 方法是WGS----->GCJ------>BD
	//
	// ///BD------->WGS
	// 方法是BD------>GCJ------->WGS

	public Location BD_WGS(Location bdLoc) {
		Location GCJ = bd_decrypt(bdLoc);
		Location result = transformFromGCJToWGS(GCJ);
		return result;

	}
	
	public Location WGS_BD(Location bdLoc) {
		Location GCJ = transformFromWGSToGCJ(bdLoc);
		Location result = bd_encrypt(GCJ);
		return result;

	}

	public class Location {
		private double lat;	//纬度
		private double lng;	//经度

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLng() {
			return lng;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

	}

	Location LocationMake(double lng, double lat) {
		Location loc = new Location();
		loc.lng = lng;
		loc.lat = lat;
		return loc;
	}

	static double pi = 3.14159265358979324;

	static double a = 6378245.0;
	static double ee = 0.00669342162296594323;

	int outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return 1;
		if (lat < 0.8293 || lat > 55.8271)
			return 1;
		return 0;
	}

	double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
				+ 0.2 * Math.sqrt(x > 0 ? x : -x);
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
				* Math.sqrt(x > 0 ? x : -x);
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
				* pi)) * 2.0 / 3.0;
		return ret;
	}

	public Location transformFromWGSToGCJ(Location wgLoc) {
		Location mgLoc = new Location();
		if (1 == outOfChina(wgLoc.lat, wgLoc.lng)) {
			mgLoc = wgLoc;
			return mgLoc;
		}
		double dLat = transformLat(wgLoc.lng - 105.0, wgLoc.lat - 35.0);
		double dLon = transformLon(wgLoc.lng - 105.0, wgLoc.lat - 35.0);
		double radLat = wgLoc.lat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		mgLoc.lat = wgLoc.lat + dLat;
		mgLoc.lng = wgLoc.lng + dLon;

		return mgLoc;
	}

	public Location transformFromGCJToWGS(Location gcLoc) {
		Location wgLoc = new Location();
		wgLoc.lat = gcLoc.lat;
		wgLoc.lng = gcLoc.lng;

		Location currGcLoc = new Location();
		Location dLoc = new Location();
		while (true) {
			currGcLoc = transformFromWGSToGCJ(wgLoc);
			dLoc.lat = gcLoc.lat - currGcLoc.lat;
			dLoc.lng = gcLoc.lng - currGcLoc.lng;
			if (Math.abs(dLoc.lat) < 1e-7 && Math.abs(dLoc.lng) < 1e-7) {
				return wgLoc;
			}
			wgLoc.lat += dLoc.lat;
			wgLoc.lng += dLoc.lng;
		}

	}

	static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	Location bd_encrypt(Location gcLoc) {
		double x = gcLoc.lng, y = gcLoc.lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		return LocationMake(z * Math.cos(theta) + 0.0065, z * Math.sin(theta)
				+ 0.006);
	}

	Location bd_decrypt(Location bdLoc) {
		double x = bdLoc.lng - 0.0065, y = bdLoc.lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		return LocationMake(z * Math.cos(theta), z * Math.sin(theta));
	}

}
