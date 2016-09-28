jodconverter4.0版本可以转换dps，et，wps，ppt，xlsx，pptx，vsd，doc，docx文档，在centos系统，mac系统下已经通过，使用转换服务为 LibreOffice_5.2.1

（进入安装目录：/opt/openoffice4/program

执行启动命令：./soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;" --nofirststartwizard &  （centos下验证通过）
如果提示如下错误：
	/optbreoffice5.2/program/soffice.bin: error while loading shared libraries: libSM.so.6: cannot open shared object file: No such file or directory
	
安装缺失的包：yum install libSM* 
再执行


使用示例：
import java.io.File;

import com.artofsolving.jodconverter.OpenOfficeUtil;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;

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
 