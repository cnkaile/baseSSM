package com.nouser.utils;
//package com.qcode.core.util;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
///*import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;*/
//
//
//
//
//
//import java.awt.image.AffineTransformOp;
//import java.awt.geom.AffineTransform;
//import java.awt.Image;
//
//public class PictureZoom {
//	@SuppressWarnings("static-access")
//	public static void getFixedBoundIcon(String filePath, int height, int width)
//			throws Exception {
//		// 缩放比例
//		double Ratio = 0.0;
//		File F = new File(filePath);
//		String filename = "H" + F.getName().substring(1);
//		String path = F.getAbsolutePath();
//
//		filename = path.substring(0, path.lastIndexOf(File.separator) + 1)
//				+ filename;
//
//		if (!F.isFile())
//			throw new Exception(F
//					+ " is not image file error in getFixedBoundIcon!");
//
//		BufferedImage Bi = ImageIO.read(F);
//		// 当原始比例比目标比例大的时候执行
//
//		if ((Bi.getHeight() > height) || (Bi.getWidth() > width)) {
//
//			// 取长度和宽度中较大的为准，确定缩放比例
//			if (Bi.getHeight() > Bi.getWidth()) {
//				Ratio = (new Integer(height)).doubleValue() / Bi.getHeight();
//			} else {
//				Ratio = (new Integer(width)).doubleValue() / Bi.getHeight();
//			}
//
//			// int lastLength = filePath.lastIndexOf(".");
//
//			File zoomFile = new File(filename);
//			Image Itemp = Bi.getScaledInstance(width, height, Bi.SCALE_SMOOTH);
//			AffineTransformOp op = new AffineTransformOp(
//					AffineTransform.getScaleInstance(Ratio, Ratio), null);
//			Itemp = op.filter(Bi, null);
//
//			try {
//				ImageIO.write((BufferedImage) Itemp, "jpg", zoomFile);
//
//				// ret = new ImageIcon(zoomFile.getPath());
//
//			} catch (Exception ex) {
//				System.out.println("######## here error : " + ex);
//			}
//		}
//
//		// /**
//		// 55. * 将图片分成九块
//		// 56. *
//		// 57. * @param srcImageFile
//		// 58. * @throws IOException
//		// 59. */
//		// public void cut(String srcImageFile) throws IOException {
//		// Image img;
//		// 62. ImageFilter cropFilter;
//		// 63. String dir = null;
//		// 64. // 读取源图像
//		// 65. BufferedImage src = ImageIO.read(new File(srcImageFile));
//		// 66. int destWidth = src.getWidth() / 3;
//		// 67. int destHeight = src.getHeight() / 3;
//		// 68. // 循环
//		// 69. for (int i = 0; i < 3; i++) {
//		// 70. for (int j = 0; j < 3; j++) {
//		// 71. // 四个参数分别为图像起点坐标和宽高
//		// 72. cropFilter = new CropImageFilter(j * destWidth, i * destHeight,
//		// destWidth, destHeight);
//		// 73. img = Toolkit.getDefaultToolkit().createImage(new
//		// FilteredImageSource(src.getSource(), cropFilter));
//		// 74. BufferedImage tag = new BufferedImage(destWidth, destHeight,
//		// BufferedImage.TYPE_INT_RGB);
//		// 75. Graphics g = tag.getGraphics();
//		// 76. g.drawImage(img, 0, 0, null); // 绘制小图
//		// 77. g.dispose();
//		// 78. // 输出为文件
//		// 79. dir = "d:\\temp\\cut_image_" + i + "_" + j + ".jpg";
//		// 80. File f = new File(dir);
//		// 81. ImageIO.write(tag, "JPEG", f);
//		// 82. }
//		// 83. }
//		// 84. }
//		// 85.
//		// 86. public static void main(String[] args) throws IOException {
//		// 87. String imgFileName = "d:\\temp\\soe-258.jpg";
//		// 88. ImageProcess iZoom = new ImageProcess();
//		// 89.
//		// 90. iZoom.zoomImage(imgFileName);
//		// 91. iZoom.cut(imgFileName);
//		// 92. }
//
//	}
//
//	@SuppressWarnings("unused")
//	public static void zoomImage(String srcImgFileName) throws IOException {
//		// 读入文件
//
//		File _file = new File(srcImgFileName);
//		
//		
//		int index = srcImgFileName.lastIndexOf("/");
//		String path =srcImgFileName.substring(0, index+1);
//		String filename  = srcImgFileName.substring(index+1);
//		
//		int index_= filename.lastIndexOf(".");
//		
//
//		
//		
//		ImagePress.imageCompress(path, filename, filename.substring(0,index_)+"350x350"+filename.substring(index_), 0.9f, 350, 350);
//		ImagePress.imageCompress(path, filename, filename.substring(0,index_)+"180x180"+filename.substring(index_), 0.9f, 180, 180);
//		ImagePress.imageCompress(path, filename, filename.substring(0,index_)+"50x50"+filename.substring(index_), 0.9f, 50, 50);
//		ImagePress.imageCompress(path, filename, filename.substring(0,index_)+"25x25"+filename.substring(index_), 0.9f, 25, 25);
//		
//		/*
//		//String srcName = srcImgFileName.substring(0, ind);
//		String srcName = _file.getName();
//		int ind = srcName.lastIndexOf(".");
//		String filename = srcName.substring(0, ind);
//		String path = _file.getAbsolutePath();
//
//		filename = path.substring(0, path.lastIndexOf(File.separator) + 1)
//				+ filename+"50x50"+srcName.substring(ind);
//		
//		System.out.println(filename);
//		// System.out.println(filename);
//		// 构造Image对象
//		BufferedImage src = javax.imageio.ImageIO.read(_file);
//		// int width = src.getWidth();
//		// int height = src.getHeight();
//
//		// 边长缩小为二分之一
//		// BufferedImage tag = new BufferedImage(width / 2, height / 2,
//		// BufferedImage.TYPE_INT_RGB);
//		BufferedImage tag = new BufferedImage(50, 50,
//				BufferedImage.TYPE_INT_RGB);
//		// 绘制缩小后的图
//		tag.getGraphics().drawImage(src, 0, 0, 50, 50, null);
//		FileOutputStream out = new FileOutputStream(filename);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(tag);
//		out.close();
//		// 压缩成 350 * 350 的图片
//		filename = srcName.substring(0, ind);
//		filename = path.substring(0, path.lastIndexOf(File.separator) + 1)
//				+ filename+"350x350"+srcName.substring(ind);
//
//		out = new FileOutputStream(filename);
//		tag = new BufferedImage(350, 350, BufferedImage.TYPE_INT_RGB);
//		tag.getGraphics().drawImage(src, 0, 0, 350, 350, null);
//		encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(tag);
//		out.close();
//		
//		filename = srcName.substring(0, ind);
//		filename = path.substring(0, path.lastIndexOf(File.separator) + 1)
//				+ filename+"180x180"+srcName.substring(ind);
//		out = new FileOutputStream(filename);
//		tag = new BufferedImage(180, 180, BufferedImage.TYPE_INT_RGB);
//		tag.getGraphics().drawImage(src, 0, 0, 180, 180, null);
//		encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(tag);
//		out.close();
//		
//		filename = srcName.substring(0, ind);
//		filename = path.substring(0, path.lastIndexOf(File.separator) + 1)
//				+ filename+"25x25"+srcName.substring(ind);
//		out = new FileOutputStream(filename);
//		tag = new BufferedImage(25, 25, BufferedImage.TYPE_INT_RGB);
//		tag.getGraphics().drawImage(src, 0, 0, 25, 25, null);
//		encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(tag);
//		out.close();
//
//		// 边长扩大为2倍
//		// tag = new BufferedImage(width * 2, height * 2,
//		// BufferedImage.TYPE_INT_RGB);
//		// tag.getGraphics().drawImage(src, 0, 0, width * 2, height * 2, null);
//		// out = new FileOutputStream("d:\\temp\\targetIMGx2.jpg");
//		// encoder = JPEGCodec.createJPEGEncoder(out);
//		// encoder.encode(tag);
//		// out.close();
//*/
//	}
//	
//	
//	@SuppressWarnings("unused")
//	public static void zoomImage_(String srcImgFileName) throws IOException {
//		// 读入文件
//
//		File _file = new File(srcImgFileName);
//		
//		
//		int index = srcImgFileName.lastIndexOf("/");
//		String path =srcImgFileName.substring(0, index+1);
//		String filename  = srcImgFileName.substring(index+1);
//		
//		int index_= filename.lastIndexOf(".");
//		ImagePress.imageCompress(path, filename, filename.substring(0,index_)+"_mobile"+filename.substring(index_), 0.9f, 400, 240);
//		
//
//	}
//	
//
//	public static void zoomheadImage(String srcImgFileName) throws IOException {
//		// 读入文件
//
//		File _file = new File(srcImgFileName);
//
//		String filename = "H" + _file.getName();
//		String path = _file.getAbsolutePath();
//
//		filename = path.substring(0, path.lastIndexOf(File.separator) + 1)
//				+ filename;
//		System.out.println(filename);
//		// 构造Image对象
//		BufferedImage src = javax.imageio.ImageIO.read(_file);
//		// int width = src.getWidth();
//		// int height = src.getHeight();
//
//		// 边长缩小为二分之一
//		// BufferedImage tag = new BufferedImage(width / 2, height / 2,
//		// BufferedImage.TYPE_INT_RGB);
//		BufferedImage tag = new BufferedImage(145, 145,
//				BufferedImage.TYPE_INT_RGB);
//		// 绘制缩小后的图
//		tag.getGraphics().drawImage(src, 0, 0, 145, 145, null);
//		FileOutputStream out = new FileOutputStream(filename);
//		//JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		//encoder.encode(tag);
//		out.close();
//
//		// 压缩成 320 * 320 的图片
//		filename = "S" + _file.getName();
//		path = _file.getAbsolutePath();
//		// 边长扩大为2倍
//		// tag = new BufferedImage(width * 2, height * 2,
//		// BufferedImage.TYPE_INT_RGB);
//		// tag.getGraphics().drawImage(src, 0, 0, width * 2, height * 2, null);
//		// out = new FileOutputStream("d:\\temp\\targetIMGx2.jpg");
//		// encoder = JPEGCodec.createJPEGEncoder(out);
//		// encoder.encode(tag);
//		// out.close();
//	}
//
//	public static void main(String[] args) {
//		try {
//			File f = new File(
//					"C:\\Users\\Administrator\\Desktop\\mobile.jpg");
//
//			System.out.println(f.exists());
//			System.out.println(f.length());
//			zoomImage(f.getAbsolutePath());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		/*String path = "dfsdfsdfsdf.jpg";
//		int index =path.lastIndexOf(".");
//		System.out.println(path.substring(0, index));
//		System.out.println(path.substring(index));*/
//	}
//}
