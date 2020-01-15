# 水库蓄水量计算系统
水库蓄水量计算系统是一个展示全国范围内水库蓄水量变化情况的系统，同时能处理SAR影像，光学影像，雷达高度计数据。蓄水量、水位、水域面积相关数据将会以图表的形式展现。
### 部署
1. 修改application.yml 中 static-locations，更改成服务器上静态文件位置
2. 修改application.yml 中自定义资源路径 resource-path
3. 修改文件showUploadImg.html中展示图片路径
### 执行
1. 启动tomcat时切换至 webapps/reservoir/ 执行 startup.bat
