package com.artofsolving.jodconverter;

import java.io.File;
import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 *  
 * @author Darkness
 * @date 2016年9月28日 下午4:14:41
 * @version V1.0
 */
public class OpenOfficeUtil {
	
	protected static final Logger logger = LoggerFactory.getLogger(OpenOfficeUtil.class);

	public static void convert(File input, File output) {
		convert(input, output, SocketOpenOfficeConnection.DEFAULT_HOST, SocketOpenOfficeConnection.DEFAULT_PORT);
	}
	
	public static void convert(File input, File output, String host) {
		convert(input, output, host, SocketOpenOfficeConnection.DEFAULT_PORT);
	}
	
	public static void convert(File input, File output, int port) {
		convert(input, output, SocketOpenOfficeConnection.DEFAULT_HOST, port);
	}
	
	public static void convert(File input, File output, String host, int port) {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(host, port);   
		  
		try {
			connection.connect();
			
			long t1 = System.currentTimeMillis();
			logger.info(input+"，开始转换...");
			
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(input, output);
			
			long t2 = System.currentTimeMillis();
			logger.info(input+"，转换完毕，耗时："+(t2-t1)+" ms");
		} catch (ConnectException e) {
			e.printStackTrace();
		} finally {
			if (connection.isConnected()){
				connection.disconnect();
			}
		}
	}
}
