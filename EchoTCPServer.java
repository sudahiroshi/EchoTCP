import java.io.*;
import java.net.*;

public class EchoTCPServer {
	public static void main( String[] args ) {
		ServerSocket server = null;
		Socket socket = null;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			server = new ServerSocket( 8100 );
			socket = server.accept();

			os = socket.getOutputStream();
			osw = new OutputStreamWriter( os );
			bw = new BufferedWriter( osw );

			is = socket.getInputStream();
			isr = new InputStreamReader( is );
			br = new BufferedReader( isr );

			bw.write( "Welcome to Echo TCP Server.\r\n" );
			bw.flush();
			String msg = br.readLine();
			System.out.println( "Receive: " + msg );
			bw.write( msg + "\r\n" );
			bw.flush();

			bw.close();
			osw.close();
			os.close();

			br.close();
			isr.close();
			is.close();

			socket.close();
			server.close();
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
