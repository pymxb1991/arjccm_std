package com.arjjs.ccm.tool;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Test {

	public static void main(String[] args) {
		System.out.println("start:" + System.currentTimeMillis());
		long ll = System.currentTimeMillis();
		//文件与BufferedImage间的转换
        BufferedImage bi=file2img("D:\\11.png");  //读取图片
        BufferedImage bii=img_inverse(bi);
        img2file(bii,"png","D:\\22.png");  //生成图片
        long ss = (System.currentTimeMillis() - ll);
		System.out.println("--end:" + System.currentTimeMillis() + ",take " + ss);
	}
	//图片反色
    public static BufferedImage img_inverse(BufferedImage imgsrc) {
        try {
            //创建一个不带透明度的图片
            BufferedImage back=new BufferedImage(imgsrc.getWidth(), imgsrc.getHeight(),BufferedImage.TYPE_INT_RGB);
            int width = imgsrc.getWidth();  
            int height = imgsrc.getHeight();  
            for (int i = 0; i < height; i++) { 
                for (int j = 0; j < width; j++) {  
                    int pixel = imgsrc.getRGB(j, i); 
//                    System.out.println(pixel);
                    back.setRGB(j,i,0xFFFFFF-pixel-120968);
                }
            }
            return back;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
  //读取图片
    public static BufferedImage file2img(String imgpath) {
        try {
            BufferedImage bufferedImage=ImageIO.read(new File(imgpath));
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //保存图片,extent为格式，"jpg"、"png"等
    public static void img2file(BufferedImage img,String extent,String newfile) {
        try {
            ImageIO.write(img, extent, new File(newfile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
