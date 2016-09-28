jodconverter4.0版本可以转换dps，et，wps，ppt，xlsx，pptx，vsd，doc，docx文档，在centos系统，mac系统下已经通过，使用转换服务为libreoffice LibreOffice_5.2.1_Win_x86.msi 

4.1进入/opt/openoffice.org3/program目录
4.2 启动服务soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;" --nofirststartwizard &


（进入安装目录：/opt/openoffice4/program

执行启动命令：soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;" --nofirststartwizard &  （centos下验证通过）