package com.crm.common.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class Base64Img {

	private static final String FILE_TEMP = "_tmp";

	// 将图片转换成Base64
	public static String GetImageStr(String imgFilePath) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFilePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return Base64.encodeToString(data);
	}

	// 将Base64转换成图片
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null)
			// 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] c = new byte[imgStr.length()];
			byte[] a = Base64.decodeToByteArray(imgStr);
			for (int i = 0; i < a.length; ++i) {
				c[i] = Byte.valueOf(a[i]);
				if (c[i] < 0) {
					c[i] += 256;
				}
			}
			// byte[] bytes = Base64.decodeToByteArray(imgStr);
			// byte[] bytes = decoder.decodeBuffer(imgStr);
			// for (int i = 0; i < bytes.length; ++i) {
			// if (bytes[i] < 0) {// 调整异常数据
			// bytes[i] += 256;
			// }
			// }
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(c);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getExtendName(String paramString) {
		int i = paramString.lastIndexOf(".");
		if (i == -1) {
			return "";
		}
		return paramString.substring(i + 1);
	}

	public static String createNewFileName(String fileName) {
		RandomGenerator random = new RandomGenerator();
		String strNewFileName = DateTimeProcessing.dateToString(new Date(),
				"yyMMddHHmmss")
				+ random.nextNumberString(4)
				+ "."
				+ getExtendName(fileName);
		return strNewFileName;
	}

	public static void main(String[] args) {
		GenerateImage(GetImageStr("d:\\444.png"), "D:\\323.jpg");
	}
}
