import java.net.*;
import java.io.*;

public class EchoTCPClient {
	public static void main(String[] args) {
		InetSocketAddress socketAddress;
		Socket socket = null;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			socketAddress = new InetSocketAddress( "localhost", 8100 );
			socket = new Socket();
			socket.connect( socketAddress, 10000 );
			InetAddress inadr;
			if( ( inadr = socket.getInetAddress() ) != null ) {
				System.out.println( "Connect to " + inadr );
			} else {
				System.out.println( "Connection failed" );
				System.exit( 0 );
			}
			os = socket.getOutputStream();
			osw = new OutputStreamWriter( os );
			bw = new BufferedWriter( osw );

			is = socket.getInputStream();
			isr = new InputStreamReader( is );
			br = new BufferedReader( isr );

			System.out.println( "Receive: " + br.readLine() );
			String message = "Hello, Java\r\n";
			bw.write( message );
			bw.flush();
			System.out.println( "Receive: " + br.readLine() );

			bw.close();
			osw.close();
			os.close();

			br.close();
			isr.close();
			is.close();

			socket.close();
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
