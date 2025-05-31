package com.library.util;

import com.library.dao.DataBaseDAO;

import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class BackupScheduler {

    // 备份记录文件路径
    private static final String BACKUP_TIME_FILE = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\config\\last_backup_time.txt";
    // 备份脚本的路径
    private static final String BACKUP_SCRIPT_PATH = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\config\\backup.bat";
    // 恢复脚本的路径
    private static final String RESTORE_SCRIPT_PATH = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\config\\restore.bat";
    // 五分钟的毫秒数
    private static final long FIVE_MINUTES_IN_MS = TimeUnit.MINUTES.toMillis(5);
    // 五秒钟的毫秒数
    private static final long FIVE_SECONDS_IN_MS = TimeUnit.SECONDS.toMillis(5);
    // 每隔5秒检查一次
    private static final long CHECK_INTERVAL_IN_MS = 5000; // 5秒

    public static void main(String[] args) {
        try {
            // 无限循环，程序一直运行
            while (true) {
                long lastBackupTime = getLastBackupTime();
                long currentTime = System.currentTimeMillis();

                // 判断是否超过五分钟，如果超过则执行备份
                if (currentTime - lastBackupTime >= FIVE_MINUTES_IN_MS) {
                    System.out.println("超过五分钟，开始备份...");
                    // 执行备份脚本
                    executeBackupScript();
                    // 更新备份时间
                    updateLastBackupTime(currentTime);
                } else {
                    System.out.println("距离上次备份不到五分钟，跳过备份。");
                }

                // 等待5秒钟再继续检查
                Thread.sleep(CHECK_INTERVAL_IN_MS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取上次备份时间
     * @return 上次备份的时间戳
     * @throws IOException 如果文件读取失败
     */
    private static long getLastBackupTime() throws IOException {
        Path path = Paths.get(BACKUP_TIME_FILE);
        if (Files.exists(path)) {
            // 读取文件中的时间戳
            String timestampStr = new String(Files.readAllBytes(path)).trim();
            try {
                return Long.parseLong(timestampStr);
            } catch (NumberFormatException e) {
                // 如果无法解析时间戳，返回一个很久以前的时间
                return 0;
            }
        } else {
            // 如果文件不存在，返回一个初始时间（例如：1970年1月1日）
            return 0;
        }
    }

    /**
     * 执行备份脚本
     * @throws IOException 如果执行脚本失败
     * @throws InterruptedException 如果执行被中断
     */
    private static void executeBackupScript() throws IOException, InterruptedException {

        // 如果脚本是 .bat 文件，确保以命令行格式传递参数
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", BACKUP_SCRIPT_PATH);
        processBuilder.inheritIO();  // 将进程的输入输出与当前Java进程绑定
        processBuilder.start();
        System.out.println("备份成功!");
    }


    /**
     * 更新上次备份时间
     * @param timestamp 当前时间戳
     * @throws IOException 如果写入文件失败
     */
    private static void updateLastBackupTime(long timestamp) throws IOException {
        String timestampStr = String.valueOf(timestamp);
        Files.write(Paths.get(BACKUP_TIME_FILE), timestampStr.getBytes());
        System.out.println("已更新备份时间为: " + timestampStr);
    }

    /**
     * 恢复数据库
     * @param backupFilePath 备份文件的路径
     */
    public static void restoreDatabase(String backupFilePath) {
        Connection conn = null;
        Statement stmt = null;
        BufferedReader reader = null;

        try {
            // 1. 确保备份文件存在
            Path backupPath = Paths.get(backupFilePath);
            if (!Files.exists(backupPath)) {
                throw new IOException("备份文件不存在: " + backupFilePath);
            }

            // 2. 连接到数据库
            conn = DataBaseDAO.connectMySQL();
            stmt = conn.createStatement();

            // 3. 读取备份文件并执行恢复操作
            reader = new BufferedReader(new FileReader(backupFilePath));
            StringBuilder sqlCommand = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // 跳过注释行，注释行可能是以 -- 开头或者 /* ... */ 的形式
                if (line.startsWith("--") || line.startsWith("/*") || line.startsWith("*/")) {
                    continue;
                }

                // 跳过空行
                if (line.isEmpty()) {
                    continue;
                }

                sqlCommand.append(line);

                // 检查是否是一条完整的 SQL 语句（以分号结尾）
                if (line.endsWith(";")) {
                    stmt.execute(sqlCommand.toString());  // 执行 SQL 语句
                    sqlCommand.setLength(0);  // 清空 StringBuilder 准备下一条 SQL 语句
                }
            }

            System.out.println("数据库恢复成功!");

        } catch (IOException e) {
            System.err.println("读取备份文件时发生IOException: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("执行 SQL 语句时发生 SQL 异常: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("恢复数据库时发生错误: " + e.getMessage());
        } finally {
            // 4. 关闭资源
            try {
                if (reader != null) reader.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (IOException | SQLException e) {
                System.err.println("关闭资源时发生错误: " + e.getMessage());
            }
        }
    }

}

