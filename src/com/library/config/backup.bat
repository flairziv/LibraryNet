@echo off

set host=127.0.0.1
set port=3306
set user=root
set pass=646484


set dbname=library


set hour=%time:~0,2%
if "%time:~0,1%"==" " set hour=0%time:~1,1%

set backup_date=%Date:~0,4%%Date:~5,2%%Date:~8,2%%hour%%Time:~3,2%%Time:~6,2%


set backupFile=E:\code\IntelliJ_IDEAProjects\LibraryNet\backups\%dbname%-%backup_date%.sql


"D:\ProgramData\MySQL\MySQL Server 8.0\bin\mysqldump.exe" -h%host% -P%port% -u%user% -p%pass% -c --add-drop-table %dbname% > %backupFile%

