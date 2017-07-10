package suangrenduobao.daiqile.com.mvlib.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * SD卡相关的辅助类
 * 
 * 
 * 
 */
public class SDCardUtils {
	private SDCardUtils() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断SDCard是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}

	/**
	 * 
	 * 获取手机内部可用空间大小
	 * 
	 * @return
	 */
	public static long getAvailableInternalMemorySize() {

		File path = Environment.getDataDirectory();

		StatFs stat = new StatFs(path.getPath());

		long blockSize = stat.getBlockSize();

		long availableBlocks = stat.getAvailableBlocks();

		return availableBlocks * blockSize;

	}

	/**
	 * 
	 * 获取手机内部总空间大小
	 * 
	 * @return
	 */

	public static long getTotalInternalMemorySize() {

		File path = Environment.getDataDirectory();// Gets the Android data
													// directory

		StatFs stat = new StatFs(path.getPath());

		long blockSize = stat.getBlockSize(); // 每个block 占字节数

		long totalBlocks = stat.getBlockCount(); // block总数

		return totalBlocks * blockSize;

	}

	/**
	 * 
	 * 获取手机外部可用空间大小
	 * 
	 * @return
	 */

	public static long getAvailableExternalMemorySize() {

		if (isSDCardEnable()) {

			File path = Environment.getExternalStorageDirectory();// 获取SDCard根目录

			StatFs stat = new StatFs(path.getPath());

			long blockSize = stat.getBlockSize();

			long availableBlocks = stat.getAvailableBlocks();

			return availableBlocks * blockSize;

		} else {

			return -1;

		}

	}

	/**
	 * 
	 * 获取手机外部总空间大小
	 * 
	 * @return
	 */

	public static long getTotalExternalMemorySize() {

		if (isSDCardEnable()) {

			File path = Environment.getExternalStorageDirectory(); // 获取SDCard根目录

			StatFs stat = new StatFs(path.getPath());

			long blockSize = stat.getBlockSize();

			long totalBlocks = stat.getBlockCount();

			return totalBlocks * blockSize;

		} else {

			return -1;

		}

	}

	/**
	 * 获取系统存储路径
	 * 
	 * @return
	 */
	public static String getRootDirectoryPath() {
		return Environment.getRootDirectory().getAbsolutePath();
	}

}