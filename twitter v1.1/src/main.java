/**
 * 
 */
import java.util.List;

import twitter4j.TwitterException;

import com.bg.parser.tweets.stopword;
import com.bg.parser.twitter.twitter;
import com.bg.bd.mysql.mysql;
/**
 * @author KRISTIAN
 *
 */
public class main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws TwitterException{
		// TODO Auto-generated method stub
		
		twitter t = new twitter();
		t.Authenticate();
		//t.ObtenerUsuario("cea_brasil");
		t.ObtenerUsuario("naturanet");
		t.ObtenerInformation();
		
		//stopword s = new stopword();
		//s.getWords();

	}

}
