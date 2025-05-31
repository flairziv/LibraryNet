package com.library.util;

import com.library.dao.DataBaseDAO;

import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class BackupScheduler {

    // ���ݼ�¼�ļ�·��
    private static final String BACKUP_TIME_FILE = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\config\\last_backup_time.txt";
    // ���ݽű���·��
    private static final String BACKUP_SCRIPT_PATH = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\config\\backup.bat";
    // �ָ��ű���·��
    private static final String RESTORE_SCRIPT_PATH = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\config\\restore.bat";
    // ����ӵĺ�����
    private static final long FIVE_MINUTES_IN_MS = TimeUnit.MINUTES.toMillis(5);
    // �����ӵĺ�����
    private static final long FIVE_SECONDS_IN_MS = TimeUnit.SECONDS.toMillis(5);
    // ÿ��5����һ��
    private static final long CHECK_INTERVAL_IN_MS = 5000; // 5��

    public static void main(String[] args) {
        try {
            // ����ѭ��������һֱ����
            while (true) {
                long lastBackupTime = getLastBackupTime();
                long currentTime = System.currentTimeMillis();

                // �ж��Ƿ񳬹�����ӣ����������ִ�б���
                if (currentTime - lastBackupTime >= FIVE_MINUTES_IN_MS) {
                    System.out.println("��������ӣ���ʼ����...");
                    // ִ�б��ݽű�
                    executeBackupScript();
                    // ���±���ʱ��
                    updateLastBackupTime(currentTime);
                } else {
                    System.out.println("�����ϴα��ݲ�������ӣ��������ݡ�");
                }

                // �ȴ�5�����ټ������
                Thread.sleep(CHECK_INTERVAL_IN_MS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡ�ϴα���ʱ��
     * @return �ϴα��ݵ�ʱ���
     * @throws IOException ����ļ���ȡʧ��
     */
    private static long getLastBackupTime() throws IOException {
        Path path = Paths.get(BACKUP_TIME_FILE);
        if (Files.exists(path)) {
            // ��ȡ�ļ��е�ʱ���
            String timestampStr = new String(Files.readAllBytes(path)).trim();
            try {
                return Long.parseLong(timestampStr);
            } catch (NumberFormatException e) {
                // ����޷�����ʱ���������һ���ܾ���ǰ��ʱ��
                return 0;
            }
        } else {
            // ����ļ������ڣ�����һ����ʼʱ�䣨���磺1970��1��1�գ�
            return 0;
        }
    }

    /**
     * ִ�б��ݽű�
     * @throws IOException ���ִ�нű�ʧ��
     * @throws InterruptedException ���ִ�б��ж�
     */
    private static void executeBackupScript() throws IOException, InterruptedException {

        // ����ű��� .bat �ļ���ȷ���������и�ʽ���ݲ���
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", BACKUP_SCRIPT_PATH);
        processBuilder.inheritIO();  // �����̵���������뵱ǰJava���̰�
        processBuilder.start();
        System.out.println("���ݳɹ�!");
    }


    /**
     * �����ϴα���ʱ��
     * @param timestamp ��ǰʱ���
     * @throws IOException ���д���ļ�ʧ��
     */
    private static void updateLastBackupTime(long timestamp) throws IOException {
        String timestampStr = String.valueOf(timestamp);
        Files.write(Paths.get(BACKUP_TIME_FILE), timestampStr.getBytes());
        System.out.println("�Ѹ��±���ʱ��Ϊ: " + timestampStr);
    }

    /**
     * �ָ����ݿ�
     * @param backupFilePath �����ļ���·��
     */
    public static void restoreDatabase(String backupFilePath) {
        Connection conn = null;
        Statement stmt = null;
        BufferedReader reader = null;

        try {
            // 1. ȷ�������ļ�����
            Path backupPath = Paths.get(backupFilePath);
            if (!Files.exists(backupPath)) {
                throw new IOException("�����ļ�������: " + backupFilePath);
            }

            // 2. ���ӵ����ݿ�
            conn = DataBaseDAO.connectMySQL();
            stmt = conn.createStatement();

            // 3. ��ȡ�����ļ���ִ�лָ�����
            reader = new BufferedReader(new FileReader(backupFilePath));
            StringBuilder sqlCommand = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // ����ע���У�ע���п������� -- ��ͷ���� /* ... */ ����ʽ
                if (line.startsWith("--") || line.startsWith("/*") || line.startsWith("*/")) {
                    continue;
                }

                // ��������
                if (line.isEmpty()) {
                    continue;
                }

                sqlCommand.append(line);

                // ����Ƿ���һ�������� SQL ��䣨�ԷֺŽ�β��
                if (line.endsWith(";")) {
                    stmt.execute(sqlCommand.toString());  // ִ�� SQL ���
                    sqlCommand.setLength(0);  // ��� StringBuilder ׼����һ�� SQL ���
                }
            }

            System.out.println("���ݿ�ָ��ɹ�!");

        } catch (IOException e) {
            System.err.println("��ȡ�����ļ�ʱ����IOException: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("ִ�� SQL ���ʱ���� SQL �쳣: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("�ָ����ݿ�ʱ��������: " + e.getMessage());
        } finally {
            // 4. �ر���Դ
            try {
                if (reader != null) reader.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (IOException | SQLException e) {
                System.err.println("�ر���Դʱ��������: " + e.getMessage());
            }
        }
    }

}

