package net.anyjava.webParser1;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebGetter {

	// private ResponseHandler<String> responseHandler;

	public void go() {
		wget("http://kbodata.news.naver.com/m_rank/rank_team.asp");
	}

	public static String wget( String url ) {
		return wget( url, "utf-8" );
	}
	
	public static String wget(String url, String encoding ) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String res = null;

		try {
			HttpGet httpget = new HttpGet(url);
			// HttpGet httpget = new
			// HttpGet("http://rss.joinsmsn.com/joins_it_list.xml");

			System.out.println("executing request " + httpget.getURI());
			
//			for (Header hd : httpget.getAllHeaders())
//				 System.out.println( hd );
//			System.out.println();

			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();

			String responseBody = httpclient.execute(httpget, responseHandler);

			// ���������� �׳� ������� �ѱ��� ������. ���ڵ� ó���� �ؾ��ؿ�.
			res = new String( responseBody.getBytes("8859_1"), encoding );
			
			/*
			Document doc = Jsoup.parse(res);
			Elements rows = doc.select("table.table_board2 tbody tr");
			String[] items = new String[] { "����", "��", "����", "��", "��", "��",
					"�·�", "����", "�ֱ� 10���" };
			for (Element row : rows) {
				Iterator<Element> iterElem = row.getElementsByTag("td")
						.iterator();
				StringBuilder builder = new StringBuilder();
				for (String item : items) {
					builder.append(item + ": " + iterElem.next().text()
							+ "   \t");
				}
				System.out.println(builder.toString());
			}
			*/

			// System.out.println("----------------------------------------");
			// System.out.println(responseBody);
			// System.out.println("----------------------------------------");

			// fout = new FileOutputStream( new File( path ) );
			// fout.write( responseBody.getBytes("iso-8859-1") );

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return res;
	}

	public static void main(String args[]) {

		new WebGetter().go();
	}
}
