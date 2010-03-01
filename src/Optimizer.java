import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;


public class Optimizer {
	
	public static void main( final String[] arguments ) throws Exception {
		final BufferedReader input = new BufferedReader( new InputStreamReader( new FileInputStream( "S.java" ), "UTF-8" ) );
		final char[] buffer = new char[ (int) new File( "S.java" ).length() ];
		final int charCount = input.read( buffer );
		input.close();
		
		final String content = new String( buffer, 0, charCount );
		final String lines[] = content.split( "\n" );
		
		final Set< String[] > replacableSet = new HashSet< String[] >();
		
		for ( int i = 0; i < lines.length; i++ ) {
			final String line = lines[ i ].trim();
			if ( line.startsWith( "private static final int " ) ) {
				replacableSet.add( new String[] { line.substring( "private static final int ".length(), line.indexOf( '=' ) ).trim(), line.substring( line.indexOf( '=' ) + 1, line.indexOf( ';' ) ).trim() } );
				lines[ i ] = "";
			}
		}
		
		final PrintWriter output = new PrintWriter( new OutputStreamWriter( new FileOutputStream( "S.java" ), "UTF-8" ) );
		for ( String line : lines ) {
			for ( final String[] replacable : replacableSet )
				line = line.replace( replacable[ 0 ], replacable[ 1 ] );
			
			output.println( line );
		}
		output.close();
	}
	
}
