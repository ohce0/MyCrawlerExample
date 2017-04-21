package crawler.example;

import com.github.abola.crawler.CrawlerPack;
import org.apache.commons.logging.impl.SimpleLog;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.ParseException;
import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
 * 爬蟲包程式的全貌，就只有這固定的模式
 * 
 * @author Abola Lee
 *
 */
public class CWBTry {
	// commit test  second!
	public static void main(String[] args) throws UnsupportedEncodingException, ParseException {
		// set to debug level
		//CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_DEBUG);
		// turn off logging
		CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_OFF);

		Integer stationSerial=2;

        String datepicked="2016-04-21";
        Integer duration =365; //days

		String stationNo=stationNos[stationSerial];
		String station=java.net.URLEncoder.encode(stations[stationSerial],"UTF-8");



        // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(datepicked));


        for (int d =0;d<duration;d++) {


            // 遠端資料路徑
            String uri = "http://e-service.cwb.gov.tw/HistoryDataQuery/DayDataController.do?command=viewMain&" +
                    "station=" + stationNo + "&" +
                    "stname=" + station + "&" +
                    "datepicker=" + datepicked;

            //System.out.println(uri);
            //		System.out.println(
            //				CrawlerPack.start()
            //				// 參數設定
            //			    //.addCookie("key","value")	// 設定cookie
            //				//.setRemoteEncoding("big5")// 設定遠端資料文件編碼
            //				// 選擇資料格式 (三選一)
            //				//.getFromJson(uri)
            //			    .getFromHtml(uri)
            //			    //.getFromXml(uri)
            //
            //			    // 這兒開始是 Jsoup Document 物件操作
            //			    .select("td")
            //
            //		);
            Elements data = CrawlerPack.start().getFromHtml(uri).select("td");

            StringBuffer result = new StringBuffer("");

            if(data.size()<5) {
                System.out.println("End");
                return;
            }
            //        System.out.println(data.get(19));

            for (int i = 0; i < 24; i++) {
                //            System.out.print(datepicked);
                result.append(stationNo).append(", ").append(datepicked).append(", ");
                for (int j = 4; j < 19; j++) {
                    //                System.out.print(data.get(i*15+j).text()+", ");
                    result.append(data.get(i * 15 + j).text()).append(", ");
                }
                result.append("\n");
            }
            //System.out.println(result);
            Save(result.toString(),stations[stationSerial]);
            c.add(Calendar.DATE, 1);  // number of days to add
            datepicked = sdf.format(c.getTime());  // dt is now the new date
        }
        System.out.println("Finished");
    }

    public static void Save( String textToSave, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName+".csv", true);


            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(textToSave);
            out.close();
        } catch (IOException e) {
            System.out.println("Error during saving file");
            e.printStackTrace();
        }
    }

    static String[] stations= {"五分山雷達站", "板橋", "淡水", "鞍部", "臺北", "竹子湖", "基隆", "彭佳嶼",
            "花蓮", "新屋", "蘇澳", "宜蘭", "金門", "東吉島", "澎湖", "臺南", "永康", "高雄",
            "嘉義", "臺中", "阿里山", "大武", "玉山", "新竹", "恆春", "成功", "蘭嶼", "日月潭",
            "臺東", "梧棲", "墾丁雷達站", "馬祖", "山佳", "坪林", "四堵", "泰平", "福山", "桶後",
            "屈尺", "石碇", "火燒寮", "瑞芳", "大坪", "五指山", "福隆", "雙溪", "富貴角", "三和",
            "金山", "鼻頭角", "三貂角", "社子", "大直", "石牌", "天母", "士林", "內湖", "南港",
            "三重", "大屯山", "三峽", "信義", "文山", "新莊", "三芝", "八里", "深坑", "蘆洲",
            "土城", "鶯歌", "中和", "汐止", "永和", "五分山", "平等", "林口", "復興", "桃園",
            "八德", "中壢", "埔心", "觀音", "蘆竹", "大溪", "龜山", "平鎮", "楊梅", "龍潭", "梅花",
            "關西", "峨眉", "打鐵坑", "橫山", "雪霸", "竹東", "香山", "寶山", "新豐", "湖口",
            "新竹市東區", "竹南", "南庄", "大湖", "三義", "後龍", "明德", "通霄", "馬都安", "頭份",
            "造橋", "苗栗", "銅鑼", "卓蘭", "西湖", "獅潭", "苑裡", "大河", "觀霧2", "大肚",
            "雪山圈谷", "石岡", "東勢", "梨山", "雙崎", "大甲", "大坑", "中竹林", "神岡", "大安",
            "后里", "豐原", "大里", "潭子", "清水", "外埔", "龍井"};
	static String[] stationNos={"466850", "466880", "466900", "466910", "466920", "466930",
            "466940", "466950", "466990", "467050", "467060", "467080", "467110",
            "467300", "467350", "467410", "467420", "467440", "467480", "467490",
            "467530", "467540", "467550", "467571", "467590", "467610", "467620",
            "467650", "467660", "467770", "467790", "467990", "C0A520", "C0A530",
            "C0A540", "C0A550", "C0A560", "C0A570", "C0A580", "C0A640", "C0A650",
            "C0A660", "C0A860", "C0A870", "C0A880", "C0A890", "C0A920", "C0A931",
            "C0A940", "C0A950", "C0A970", "C0A980", "C0A9A0", "C0A9B0", "C0A9C0",
            "C0A9E0", "C0A9F0", "C0A9G0", "C0A9I1", "C0AC40", "C0AC60", "C0AC70",
            "C0AC80", "C0ACA0", "C0AD00", "C0AD10", "C0AD20", "C0AD30", "C0AD40",
            "C0AD50", "C0AG90", "C0AH00", "C0AH10", "C0AH30", "C0AH40", "C0AH50",
            "C0C460", "C0C480", "C0C490", "C0C520", "C0C540", "C0C590", "C0C620",
            "C0C630", "C0C640", "C0C650", "C0C660", "C0C670", "C0D360", "C0D390",
            "C0D430", "C0D480", "C0D540", "C0D550", "C0D560", "C0D570", "C0D580",
            "C0D590", "C0D650", "C0D660", "C0E420", "C0E430", "C0E520", "C0E530",
            "C0E540", "C0E550", "C0E590", "C0E610", "C0E730", "C0E740", "C0E750",
            "C0E780", "C0E790", "C0E810", "C0E820", "C0E830", "C0E850", "C0E860",
            "C0F000", "C0F0A0", "C0F0B0", "C0F850", "C0F861", "C0F900", "C0F930",
            "C0F970", "C0F9A0", "C0F9I0", "C0F9K0", "C0F9L0", "C0F9M0", "C0F9N0",
            "C0F9O0", "C0F9P0", "C0F9Q0", "C0F9R0"};
}
//4 19