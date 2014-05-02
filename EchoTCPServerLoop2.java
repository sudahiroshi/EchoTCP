import java.io.*;
import java.net.*;

public class EchoTCPServerLoop2 {
	public static void main( String[] args ) {
		ServerSocket server = null;
		Socket socket = null;
		OutputStreamWriter osw = null;
		InputStreamReader isr = null;
		BufferedWriter bw = null;
		BufferedReader br = null;
		try {
			server = new ServerSocket( 8100 );
			while( true ) {
				socket = server.accept();
				osw = new OutputStreamWriter( socket.getOutputStream() );
				isr = new InputStreamReader( socket.getInputStream() );
				bw = new BufferedWriter( osw );
				br = new BufferedReader( isr );
				bw.write( "Welcome to Echo TCP Server.\r\n" );
				bw.flush();
				String msg;
				do {
					msg = br.readLine();
					System.out.println( msg );
					bw.write( msg + "\r\n" );
				} while( msg.compareTo( "end" ) != 0 );
				bw.flush();
				bw.close();
				br.close();
				osw.close();
				isr.close();
				socket.close();
			}
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch( Exception e ) {}
		}
	}
}
