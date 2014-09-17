package net.anyjava.webParser1;

import java.util.ArrayList;
import java.util.Iterator;

import net.anyjava.webParser1.dao.ArticleListDao;
import net.anyjava.webParser1.dao.ArticleListDaoImp;
import net.anyjava.webParser1.dao.ImgListDao;
import net.anyjava.webParser1.dao.ImgListDaoImp;
import net.anyjava.webParser1.vo.ArticleVO;
import net.anyjava.webParser1.vo.ImgVO;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ClienParser {
	
	private final String BASE_URL = "http://clien.net/cs2/bbs/";
	private final String URL      = BASE_URL + "board.php?bo_table=park&page=3";
	
	private Logger log = Logger.getLogger( ClienParser.class );
	
	private ArrayList<ArticleVO> clienVOList;
	
	private ArticleListDao dao;
	private ImgListDao imgDao;

	private int avg 		  = 0;
	private long maxArticleId = 0;
		
	public ClienParser() {
		clienVOList = new ArrayList<ArticleVO>();
		dao         = new ArticleListDaoImp();
		imgDao      = new ImgListDaoImp();

		log.info(" VOlist size1=["+clienVOList.size()+"]");

		maxArticleId = dao.getArticleMaxId();

		parse();
		this.printListOverAvg();
	}
	
	private void printListOverAvg() {
		
		log.info(" VOlist size=["+clienVOList.size()+"]");
		for( ArticleVO vo : clienVOList ) {
			int temp = 0;
			try{
				temp = Integer.parseInt( vo.getHit() );
			} catch( NumberFormatException e ) {
				log.info("신고글 SKIP! : ");
			}
			avg += temp;

			
		}
		
		
		avg = avg / clienVOList.size();
		avg = (int) ( avg * 1.6 );
		
		for( ArticleVO vo : clienVOList ) {
			if( Integer.parseInt( vo.getHit() ) > avg ) {
				log.info( vo.toString() );
				log.info( "RESULT=>" + dao.insertArticle( vo ) );
			}
			
			// 이미지 가져오는 부분을 추가하도록 하자.
			parseImage( vo );
		}
		
	}
	
	private void parseImage( ArticleVO vo ) {
		String response = WebGetter.wget( vo.getUrl() );
		
		ImgVO imgVo;
		
		Document doc = Jsoup.parse(response);
		Elements rows = doc.select(".attachedImage");

		String imgUrl = "";
		int imgSeq = 0;

		for (Element row : rows) {
			imgSeq = 0;

			for( Element img : row.children() ) {
				imgUrl = img.attr("src");
				log.info("SON!! IMG=>" + imgUrl );

				imgVo = new ImgVO();

				imgVo.setId( vo.getArticleNo() );
				imgVo.setImgSeq( "" + imgSeq++ );
				imgVo.setSubject( vo.getSubject() );
				imgVo.setUrl( imgUrl );
				
				imgDao.insertImg( imgVo );

			}
		}
	}
	
	
	private void parse() {
		String response = WebGetter.wget( URL );
		ArticleVO clien;
		
		String subject;
		String url;
		
		Document doc = Jsoup.parse(response);
		Elements rows = doc.select("tr.mytr");
		
		Element temp;
		String articleNo;
		
		for (Element row : rows) {
			clien = new ArticleVO();
			
			Iterator<Element> iterElem = row.getElementsByTag("td").iterator();
			
			StringBuilder builder = new StringBuilder();
			
//			while( iterElem.hasNext() ) {
//				System.out.println( iterElem.next().select("a").attr("href") );
//				System.out.println( iterElem.next().text() );
//			}
			
			articleNo = iterElem.next().text();
			clien.setArticleNo( articleNo );
			
			// MaxId 보다 작을경우는 skip 한다.
			if( maxArticleId >= Long.parseLong( articleNo ) ) {
				log.info(" select SKIP!! article_id = ["+articleNo+"], maxId=["+maxArticleId+"]");
				continue;
			}
			
			temp    = iterElem.next();
			subject = temp.text();
			subject = subject.substring( 2, subject.length() );
			clien.setSubject( subject );
			
			url     = BASE_URL + temp.select("a").attr("href");
			clien.setUrl( url );
			
			clien.setNick(      iterElem.next().text() );
			clien.setDate(      iterElem.next().text() );
			clien.setHit(       iterElem.next().text() );
			
			
			// 신고글 skip
			if( clien.getHit().equals("-") != true )
				clienVOList.add( clien );
			
//			for (String item : items) {
//				System.out.println( iterElem.next().text() );
//				builder.append(item + ": " + iterElem.next().text() + "   \t");
//			}
//			System.out.println(builder.toString());
		}
		
		
//		for( ClienVO vo : clienVOList )
//			System.out.println( vo );
	}
	
}
