package crawler.example.exam;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 練習：取得任一篇或多篇文章的 Reactions 總數
 *
 *
 * 重點
 * 1. 先利用Graph Api調整出需要的資料
 * 2. 修改程式，使用爬蟲包取得資料
 * 3. 上傳至GitHub
 * 
 * @author Abola Lee
 *
 */
public class FacebookExam {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
//crazyck101/feed?fields=id,link,message,created_time,likes.limit(0).summary(total_count),reactions.limit(0).summary(total_count)
		String uri = 
				"https://graph.facebook.com/v2.6"
				+ "/crazyck101/feed?fields=id,reactions.limit(0).summary(total_count)"
				+ "&access_token=664670030408291%7CT4mz8lNWqi0YgfZ-0BkYh1EnlLE";


		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		StringBuffer output = new StringBuffer("id,reactions\n");

		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();

			// FIXIT
			String reactions = data.select("reactions summary total_count").text();


			output.append(id).append(",").append(reactions).append("\n");
		}

		System.out.println( output );
	} 
}
