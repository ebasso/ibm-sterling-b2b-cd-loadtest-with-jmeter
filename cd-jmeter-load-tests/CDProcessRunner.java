/*
 * Connect:Direct Process Runner
 * 
 * javac -cp ./CDJAI.jar:. CDProcessRunner.java 
 * 
 * java -cp ./CDJAI.jar:. CDProcessRunner
 */
import com.sterlingcommerce.cd.sdk.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CDProcessRunner {

    private static final String LOCALNODE = "<IP Address>";
    private static final String LOCALNODEAPIPORT = "1363";
    private static final String LOCALNODEUSERID = "cdadmin";
    private static final String LOCALNODEPASSWORD = "< cd password>";
    private static final String PROCESS_PATH = "sendfile.cdp";
    private static final String SNODE = "< CDNODE (SNODE) >";
    private static final String SOURCEFILE = "/home/cdadmin/cdunix/ndm/cfg/msgfile.cfg"; // Example source file name
    private static final String DESTFILE = "/home/cdadmin02/cdunix/work/cddelete.me";
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Capture start time
        MediatorEnum me = null;
        Node cdNode = null;
        Integer pnum = 0;
        Integer exitCode = 0;

        try {
            logWithTimestamp("CDJAI Version: " + Version.buildvers);
            logWithTimestamp("Connecting to Connect:Direct server");
            cdNode = new Node(LOCALNODE + ";" + LOCALNODEAPIPORT, LOCALNODEUSERID, LOCALNODEPASSWORD.toCharArray(), "TCPIP");

            String localNode = cdNode.getConnectionInfo().getNodeName();
            logWithTimestamp("Signed on to Connect:Direct -- Node = " + localNode);

            // Submit Process
            StringBuffer st = new StringBuffer();
            st.append("submit PROC=").append(PROCESS_PATH);
            st.append(" &SNODE=").append(SNODE);
            st.append(" &srcFile=").append(SOURCEFILE);
            st.append(" &destFile=").append(DESTFILE).append(" maxdelay=00:15:00");

            // Execute the process
            me = cdNode.execute(st.toString()); 

            // Get Results
            while (me.hasMoreElements()) {
                CDProcess processData = (CDProcess) me.getNextElement();
                pnum = processData.getProcessNumber();
                logWithTimestamp("Process Number: " + pnum);
            }

        } catch (ConnectionException ce) {
            showErrorDetails(ce);
            exitCode = 1;
        } catch (LogonException le) {
            showErrorDetails(le);
            exitCode = 2;
        } catch (KQVException ke) {
            showErrorDetails(ke);
            exitCode = 3;
        } catch (MsgException msge) {
            showErrorDetails(msge);
            exitCode = 4;
        } catch (Exception ex) {
            ex.printStackTrace();
            exitCode = 5;
        } finally {
            // Clean up resources
            try {
                if (me != null) {
                    me.empty();
                }
                me = null;
                if (cdNode != null) {
                    logWithTimestamp("Disconnecting from Connect:Direct server");
                    cdNode.closeNode();
                }
            } catch (Exception ignored) {
                // Exception handling for cleanup
                exitCode = 6;
            }

            long endTime = System.currentTimeMillis(); // Capture end time
            long duration = endTime - startTime; // Calculate duration
            logWithTimestamp("Total execution time: " + duration + " milliseconds");
            
            System.exit(exitCode); // Exit with code 0 for successful execution
        }
    }

    // Method to handle errors with timestamps
    private static void showErrorDetails(Exception e) {
        System.err.println(getCurrentTimestamp() + " Error occurred: " + e.getMessage());
        e.printStackTrace();
    }

    // Helper method to log messages with timestamps
    private static void logWithTimestamp(String message) {
        System.out.println(getCurrentTimestamp() + " " + message);
    }

    // Helper method to get the current timestamp as a string
    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DATE_FORMATTER);
    }
}
