//MIT License
//
//Copyright (c) 2022 Zuehlke, Nicolas Marty
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.

import com.jcraft.jsch.*;

/**
 * Simple SFTP client for the raging-rachel challenge
 *
 * @author Nicolas Favre
 * @version 1.0.0
 * @date 03.07.2023
 * @email khronozz-dev@proton.me
 * @userid khronozz
 */
public class SftpClient {
    private static final int SFTP_PORT = 22;
    private static final String SFTP_USER = "rachel";
    private static final String SFTP_PASSWORD = "Wolf7-Popper-Pantry";

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Pass Raspberry Pi's IP address as argument!");
            return;
        }

        String host = args[0];
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp sftpChannel = null;

        try {
            // Connect to the remote SFTP server
            session = jsch.getSession(SFTP_USER, host, SFTP_PORT);
            session.setPassword(SFTP_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Open an SFTP channel
            sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();

            // Retrieve the file on the /home/rachel directory
            sftpChannel.get("/home/rachel/file.txt", "file.txt");
            System.out.println("File file.txt retrieved from " + host);

        } catch (JSchException | SftpException e) {
            System.out.println("Make sure the IP address format is right !");
            System.out.println("Exception :");
            e.printStackTrace();
        } finally {
            // Exit channel and quit session
            if (sftpChannel != null && sftpChannel.isConnected()) {
                sftpChannel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
