package com.arjjs.ccm.modules.plm.storage.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成
 * @author dongqikai
 *
 */
public class QREncodeUtils {
	
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	
	/**
	 * 生成二维码图片
	 * @param bm
	 * @return
	 */
	private static BufferedImage toBufferedImage(BitMatrix bm) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		BufferedImage image = new BufferedImage(width, height,
		        BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				image.setRGB(i, j, bm.get(i, j) ? BLACK : WHITE);
			}
		}
		return image;
	}
	
	/**
	 * 将图片写入到文件
	 * @param bm
	 * @param format
	 * @param file
	 */
	public static void writeBitMatricToFile(BitMatrix bm, String format,
	        File file) {
		BufferedImage image = toBufferedImage(bm);
		try {
			if (!ImageIO.write(image, format, file)) {
				throw new RuntimeException("Can not write an image to file: " + file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将图片写入到输出流
	 * @param matrix
	 * @param format
	 * @param stream
	 * @throws IOException
	 */
	public static void writeToStream(BitMatrix matrix, String format,
	        OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format: " + format);
		}
	}
	
	/**
	 * 生成BitMatrix编码对象
	 * @param contents
	 * @param size
	 * @return
	 */
	public static BitMatrix createBitMatrixWithQR(String contents, int size) {
		BitMatrix bm = null;
		contents += "\r";
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			bm = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, size, size, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return bm;
	}
	
	/**
	 * 将二维码输出为base64码
	 * @param bm
	 * @param format
	 * @param str
	 */
	public static String writeToBase64(BitMatrix bm, String format) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] data = null;
		try {
			writeToStream(bm, format, os);
			data = os.toByteArray();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(data);
	}
	
}
