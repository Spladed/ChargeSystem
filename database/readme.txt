在MySQL内使用以下命令创建数据库chargesystem：
create database chargesystem;

使用以下指令退出MySQL：
quit;

在CMD中输入以下命令导入数据库：
mysql -h localhost -u root -p chargesystem <你的文件路径\cs.sql
（比如我的文件路径是mysql -h localhost -u root -p chargesystem <F:\eclipse-workspace\ChargeSystem\database\cs.sql）
输入密码后完成导入

（将数据库mydb中的mytable导出到e:\MySQL\mytable.sql文件中：

c:\>MySQLdump -h localhost -u root -p mydb mytable>e:\MySQL\mytable.sql）