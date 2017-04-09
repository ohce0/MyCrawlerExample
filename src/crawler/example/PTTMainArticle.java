package crawler.example;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.select.Elements;

/**
 * 爬蟲包程式的全貌，就只有這固定的模式
 * 
 * @author Abola Lee
 *
 */
public class PTTMainArticle {
	// commit test  second!
	public static void main(String[] args) {

		// set to debug level
		//CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_DEBUG);

		// turn off logging
		//CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_OFF);

		// 遠端資料路徑
		String uri = "https://www.ptt.cc/bbs/Gossiping/M.1491723668.A.D17.html";
        Elements ele = CrawlerPack.start()

                // 參數設定
                .addCookie("over18", "1")    // 設定cookie
                //.setRemoteEncoding("big5")// 設定遠端資料文件編碼

                // 選擇資料格式 (三選一)
                //.getFromJson(uri)
                .getFromHtml(uri)
                //.getFromXml(uri)


                // 這兒開始是 Jsoup Document 物件操作
                .select("#main-content");

        ele.select("span, div").remove();

        //String str=ele.text().replaceAll("<.*>","");
        String str=ele.text();
		System.out.println(str);
	}
}
