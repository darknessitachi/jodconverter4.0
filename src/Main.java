import java.io.File;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Main {

	public static void main(String[] args) {
		convert();
	}
	
	public static void convert() {
		String[] files = { "1.dps", "2.et", "3.wps", "4.ppt", "5.xlsx", "6.pptx", "7.vsd", "8.doc", "9.docx" };

		String userDir = System.getProperty("user.dir") + File.separator;
		for (int i = 0; i < 9; i++) {
			File inputFile = new File(userDir + "test/" + files[i]);
			File outputFile = new File(userDir + "test/" + (i + 1) + ".pdf");
			new ConverThread(inputFile, outputFile).start();
		}
	}
	
	static class ConverThread extends Thread {
		private File input;
		private File output;
		
		public ConverThread(File in, File out){
			input = in;
			output = out;
		}

		@Override
		public void run() {
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);   
			  
			try {
				connection.connect();
				long t1 = System.currentTimeMillis();
				System.out.println(input+"，开始转换...");
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
				
				converter.convert(input, output);
				long t2 = System.currentTimeMillis();
				System.out.println(input+"，转换完毕，耗时："+(t2-t1)+" ms");
			} catch (ConnectException e) {
				e.printStackTrace();
			} finally {
				if (connection.isConnected()){
					connection.disconnect();
				}
			}
		}
	}
	
}
 