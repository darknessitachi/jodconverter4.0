import java.io.File;

import com.artofsolving.jodconverter.OpenOfficeUtil;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;

public class Main {

	public static void main(String[] args) {
		convert();
	}
	
	public static void convert() {
		String[] files = { "1.dps", "2.et", "3.wps", "4.ppt", "5.xlsx", "6.pptx", "7.vsd", "8.doc", "9.docx", "10.txt" };

		String userDir = System.getProperty("user.dir") + File.separator;
		String testFileFolderPath = userDir + "test" + File.separator;
		for (int i = 0; i < 10; i++) {
			File inputFile = new File(testFileFolderPath + files[i]);
			File outputFile = new File(testFileFolderPath + (i + 1) + ".pdf");
			new Thread(new ConverThread(inputFile, outputFile)).start();
		}
	}
	
	static class ConverThread implements Runnable {
		
		private File input;
		private File output;
		
		public ConverThread(File in, File out){
			input = in;
			output = out;
		}

		@Override
		public void run() {
			int port = SocketOpenOfficeConnection.DEFAULT_PORT;
			port = 8100;
			
			OpenOfficeUtil.convert(input, output, port);
		}
	}
	
}
 