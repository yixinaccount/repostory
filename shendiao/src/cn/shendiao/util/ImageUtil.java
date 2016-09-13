package cn.shendiao.util;



import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {
	
	public static Map<String,Object> uploadUser( HttpServletRequest request,String filename,MultipartFile multipartFile){
		// 取文件要保存的绝对路径
		String basePath = request.getSession().getServletContext().getRealPath("/");
		// 文件原名
		String tempName = multipartFile.getOriginalFilename();
		// 扩展名
		String extension = tempName.substring(tempName.lastIndexOf("."), tempName.length());

		// 文件夹
		String folder = basePath + "upload\\user\\"+filename;
		// 如果不存在则创建
		if (!new File(folder).exists()) {
			new File(folder).mkdirs();
		}
		
		//获取新名称
		String name = Long.toString(new Date().getTime());
		
		//保存数据库路径
		String picPath = "/upload/user/"+filename+"/" + name+extension;
		
		// 要生成的新文件的路径
		/*String val = basePath + "upload\\img" + File.separator + name+extension;*/
		String val = basePath + "upload\\user\\"+ filename + File.separator + name+extension;
		File targetFile = new File(val);
		Map<String,Object> map = new HashMap<String, Object>();
			
			try {
				multipartFile.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				map.put("state", 0);
				return map;
			}		
			map.put("state", 1);
			map.put("picture", picPath);
			return map;				
	}
	
	
	public static Map<String,Object> uploadMer( HttpServletRequest request,String filename,MultipartFile multipartFile){
		// 取文件要保存的绝对路径
		String basePath = request.getSession().getServletContext().getRealPath("/");
		// 文件原名
		String tempName = multipartFile.getOriginalFilename();
		// 扩展名
		String extension = tempName.substring(tempName.lastIndexOf("."), tempName.length());

		// 文件夹
		String folder = basePath + "upload\\merchant\\"+filename;
		// 如果不存在则创建
		if (!new File(folder).exists()) {
			new File(folder).mkdirs();
		}
		
		//获取新名称
		String name = Long.toString(new Date().getTime());
		
		//保存数据库路径
		String picPath = "/upload/merchant/"+filename+"/" + name+extension;
		
		// 要生成的新文件的路径
		/*String val = basePath + "upload\\img" + File.separator + name+extension;*/
		String val = basePath + "upload\\merchant\\"+ filename + File.separator + name+extension;
		File targetFile = new File(val);
		Map<String,Object> map = new HashMap<String, Object>();
			
			try {
				
				multipartFile.transferTo(targetFile);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				map.put("state", 0);
				return map;
			}		
			map.put("state", 1);
			map.put("picture", picPath);
			return map;				
	}
	
	
	
	public static Map<String,Object> uploadExchangeImg( HttpServletRequest request,MultipartFile multipartFile){
		// 取文件要保存的绝对路径
		String basePath = request.getSession().getServletContext().getRealPath("/");
		// 文件原名
		String tempName = multipartFile.getOriginalFilename();
		// 扩展名
		String extension = tempName.substring(tempName.lastIndexOf("."), tempName.length());

		// 文件夹
		String folder = basePath + "upload\\exchange";
		// 如果不存在则创建
		if (!new File(folder).exists()) {
			new File(folder).mkdirs();
		}
		
		//获取新名称
		String name = Long.toString(new Date().getTime());
		
		//保存数据库路径
		String picPath = "/upload/exchange/" + name+extension;
		
		// 要生成的新文件的路径
		/*String val = basePath + "upload\\img" + File.separator + name+extension;*/
		String val = basePath +"upload\\exchange" + File.separator + name+extension;
		File targetFile = new File(val);
		
		Map<String,Object> map = new HashMap<String, Object>();
			
			try {
				multipartFile.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				map.put("state", 0);
				return map;
			}		
			map.put("state", 1);
			map.put("picture", picPath);
			return map;				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*******************************************************************************
	 * 缩略图类（通用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换。 具体使用方法
	 * compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
	 *//*
	private File file = null; // 文件对象
	private String inputDir; // 输入图路径
	private String outputDir; // 输出图路径
	private String inputFileName; // 输入图文件名
	private String outputFileName; // 输出图文件名
	private int outputWidth = 100; // 默认输出图片宽
	private int outputHeight = 100; // 默认输出图片高
	private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

	public ImageUtil() { // 初始化变量
		inputDir = "";
		outputDir = "";
		inputFileName = "";
		outputFileName = "";
		outputWidth = 100;
		outputHeight = 100;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}

	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}

	public void setWidthAndHeight(int width, int height) {
		this.outputWidth = width;
		this.outputHeight = height;
	}

	
	 * 获得图片大小 传入参数 String path ：图片路径
	 
	public long getPicSize(String path) {
		file = new File(path);
		return file.length();
	}
	
	
	
	
	
	public static Map<String,Object> uploadExchangeImg1( HttpServletRequest request,MultipartFile multipartFile){
		// 取文件要保存的绝对路径
		String basePath = request.getSession().getServletContext().getRealPath("/");
		// 文件原名
		String tempName = multipartFile.getOriginalFilename();
		// 扩展名
		String extension = tempName.substring(tempName.lastIndexOf("."), tempName.length());

		// 文件夹
		String folder = basePath + "upload\\exchange";
		// 如果不存在则创建
		if (!new File(folder).exists()) {
			new File(folder).mkdirs();
		}
		
		//获取新名称
		String name = Long.toString(new Date().getTime());
		
		//保存数据库路径
		String picPath = "/upload/exchange/" + name+extension;
		
		// 要生成的新文件的路径
		String val = basePath + "upload\\img" + File.separator + name+extension;
		String val = basePath +"upload\\exchange" + File.separator + name+extension;
		File targetFile = new File(val);
		
		
		
		
		
		
	
					Image img = ImageIO.read(targetFile);
					// 判断图片格式是否正确
					if (img.getWidth(null) == -1) {
						System.out.println(" can't read,retry!" + "<BR>");
						//return "no";
					} else {
						int newWidth;
						int newHeight;
						// 判断是否是等比缩放
						if (this.proportion == true) {
							// 为等比缩放计算输出的图片宽度及高度
							double rate1 = ((double) img.getWidth(null))
									/ (double) outputWidth + 0.1;
							double rate2 = ((double) img.getHeight(null))
									/ (double) outputHeight + 0.1;
							// 根据缩放比率大的进行缩放控制
							double rate = rate1 > rate2 ? rate1 : rate2;
							newWidth = (int) (((double) img.getWidth(null)) / rate);
							newHeight = (int) (((double) img.getHeight(null)) / rate);
						} else {
							newWidth = outputWidth; // 输出的图片宽度
							newHeight = outputHeight; // 输出的图片高度
						}
						BufferedImage tag = new BufferedImage((int) newWidth,
								(int) newHeight, BufferedImage.TYPE_INT_RGB);

						
						 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
						 
						tag.getGraphics().drawImage(
								img.getScaledInstance(newWidth, newHeight,
										Image.SCALE_SMOOTH), 0, 0, null);
						File f=new File(outputDir);
						if (!f.exists()){
							f.mkdirs(); 
						}
						FileOutputStream out = new FileOutputStream(outputDir
								+ outputFileName);
						// JPEGImageEncoder可适用于其他图片类型的转换
						JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
						encoder.encode(tag);
						out.close();
					}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Map<String,Object> map = new HashMap<String, Object>();
			
			try {
				multipartFile.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				map.put("state", 0);
				return map;
			}		
			map.put("state", 1);
			map.put("picture", picPath);
			return map;				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  public static String zipImageFile(File oldFile,File newFile, int width, int height,    
	            float quality) {    
	        if (oldFile == null) {    
	            return null;    
	        }    
	        try {    
	            *//** 对服务器上的临时文件进行处理 *//*    
	            Image srcFile = ImageIO.read(oldFile);    
	            int w = srcFile.getWidth(null);    
	        //  System.out.println(w);    
	            int h = srcFile.getHeight(null);    
	        //  System.out.println(h);    
	            double bili;    
	            if(width>0){    
	                bili=width/(double)w;    
	                height = (int) (h*bili);    
	            }else{    
	                if(height>0){    
	                    bili=height/(double)h;    
	                    width = (int) (w*bili);    
	                }    
	            }    
	            *//** 宽,高设定 *//*    
	            BufferedImage tag = new BufferedImage(width, height,    
	                    BufferedImage.TYPE_INT_RGB);    
	            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);    
	            //String filePrex = oldFile.getName().substring(0, oldFile.getName().indexOf('.'));    
	            *//** 压缩后的文件名 *//*    
	            //newImage = filePrex + smallIcon+  oldFile.getName().substring(filePrex.length());    
	    
	            *//** 压缩之后临时存放位置 *//*    
	            FileOutputStream out = new FileOutputStream(newFile);    
	    
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
	            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);    
	            *//** 压缩质量 *//*    
	            jep.setQuality(quality, true);    
	            encoder.encode(tag, jep);    
	            out.close();    
	    
	        } catch (FileNotFoundException e) {    
	            e.printStackTrace();    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }    
	        return newFile.getAbsolutePath();    
	    }    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	


	*//** 
	 * * @author WQ * @date 2011-01-14 * @versions 1.0 图片压缩工具类 提供的方法中可以设定生成的 
	 * 缩略图片的大小尺寸等 
	 *//*  

	    *//** * 图片文件读取 * * @param srcImgPath * @return *//*  
	    private static BufferedImage InputImage(String srcImgPath) {  
	        BufferedImage srcImage = null;  
	        try {  
	            FileInputStream in = new FileInputStream(srcImgPath);  
	            srcImage = javax.imageio.ImageIO.read(in);  
	        } catch (IOException e) {  
	            System.out.println("读取图片文件出错！" + e.getMessage());  
	            e.printStackTrace();  
	        }  
	        return srcImage;  
	    }  
	  
	    *//** 
	     * * 将图片按照指定的图片尺寸压缩 * * @param srcImgPath :源图片路径 * @param outImgPath * 
	     * :输出的压缩图片的路径 * @param new_w * :压缩后的图片宽 * @param new_h * :压缩后的图片高 
	     *//*  
	    public static void compressImage(String srcImgPath, String outImgPath,  
	            int new_w, int new_h) {  
	        BufferedImage src = InputImage(srcImgPath);  
	        disposeImage(src, outImgPath, new_w, new_h);  
	    }  
	  
	    *//** 
	     * * 指定长或者宽的最大值来压缩图片 * * @param srcImgPath * :源图片路径 * @param outImgPath * 
	     * :输出的压缩图片的路径 * @param maxLength * :长或者宽的最大值 
	     *//*  
	    public static void compressImage(String srcImgPath, String outImgPath,  
	            int maxLength) {  
	        // 得到图片  
	        BufferedImage src = InputImage(srcImgPath);  
	        if (null != src) {  
	            int old_w = src.getWidth();  
	            // 得到源图宽  
	            int old_h = src.getHeight();  
	            // 得到源图长  
	            int new_w = 0;  
	            // 新图的宽  
	            int new_h = 0;  
	            // 新图的长  
	            // 根据图片尺寸压缩比得到新图的尺寸  
	            if (old_w > old_h) {  
	                // 图片要缩放的比例  
	                new_w = maxLength;  
	                new_h = (int) Math.round(old_h * ((float) maxLength / old_w));  
	            } else {  
	                new_w = (int) Math.round(old_w * ((float) maxLength / old_h));  
	                new_h = maxLength;  
	            }  
	            disposeImage(src, outImgPath, new_w, new_h);  
	        }  
	    }  
	  
	    *//** * 处理图片 * * @param src * @param outImgPath * @param new_w * @param new_h *//*  
	    private synchronized static void disposeImage(BufferedImage src,  
	            String outImgPath, int new_w, int new_h) {  
	        // 得到图片  
	        int old_w = src.getWidth();  
	        // 得到源图宽  
	        int old_h = src.getHeight();  
	        // 得到源图长  
	        BufferedImage newImg = null;  
	        // 判断输入图片的类型  
	        switch (src.getType()) {  
	        case 13:  
	            // png,gifnewImg = new BufferedImage(new_w, new_h,  
	            // BufferedImage.TYPE_4BYTE_ABGR);  
	            break;  
	        default:  
	            newImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);  
	            break;  
	        }  
	        Graphics2D g = newImg.createGraphics();  
	        // 从原图上取颜色绘制新图  
	        g.drawImage(src, 0, 0, old_w, old_h, null);  
	        g.dispose();  
	        // 根据图片尺寸压缩比得到新图的尺寸  
	        newImg.getGraphics().drawImage(  
	                src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0,  
	                null);  
	        // 调用方法输出图片文件  
	        OutImage(outImgPath, newImg);  
	    }  
	  
	    *//** 
	     * * 将图片文件输出到指定的路径，并可设定压缩质量 * * @param outImgPath * @param newImg * @param 
	     * per 
	     *//*  
	    private static void OutImage(String outImgPath, BufferedImage newImg) {  
	        // 判断输出的文件夹路径是否存在，不存在则创建  
	        File file = new File(outImgPath);  
	        if (!file.getParentFile().exists()) {  
	            file.getParentFile().mkdirs();  
	        }// 输出到文件流  
	        try {  
	            ImageIO.write(newImg, outImgPath.substring(outImgPath  
	                    .lastIndexOf(".") + 1), new File(outImgPath));  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  

	    }
	*/
	

}
