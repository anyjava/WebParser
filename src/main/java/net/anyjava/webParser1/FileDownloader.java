package net.anyjava.webParser1;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class FileDownloader {
	

	public static String download( String downUrl, String path, String downloadPath, Logger log ) {
		
		// 다운로드 URL
		HttpClient httpclient = new DefaultHttpClient();
		String fileName = "";
		
        try {
        	
        	// =============================================================================
        	//  로그인처리 시도 
        	// -----------------------------------------------------------------------------
        	
        	// Create a response handler
        	/*
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
        	
            HttpPost httppost = new HttpPost("http://www.btzoa.com/cgi/login_check.php");
//            HttpPost httppost = new HttpPost("http://www.anyjava.net:8888/torrent/test.jsp");
            
        		
            log.info("executing request " + httppost.getURI());
            for( Header hd : httppost.getAllHeaders()  )
            	System.out.println( hd );
            log.info("");

            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            paramList.add( new BasicNameValuePair( "url", "http://www.btzoa.com/board.php?mode=list&b_id=guide" ) );
            paramList.add( new BasicNameValuePair( "mb_id", "shiniks" ) );
            paramList.add( new BasicNameValuePair( "mb_password", "1234" ) );
            paramList.add( new BasicNameValuePair( "x", "12" ) );
            paramList.add( new BasicNameValuePair( "y", "1" ) );
            
            httppost.setEntity( new UrlEncodedFormEntity( paramList ) );
            
            
            try {
	            String responseBody2 = httpclient.execute(httppost, responseHandler);
	            System.out.println("----------------------------------------");
	            System.out.println( new String( responseBody2.getBytes("iso-8859-1") ) );
	//            System.out.println( responseBody );
	            System.out.println("----------------------------------------");
            } catch( Exception e ) {
            	
            }
            */
        	
            // =============================================================================
        	//  로그인처리 시도 종료
        	// -----------------------------------------------------------------------------
            
        	/****************************************************************/
            /* 다운로드 URL로 torrent 파일 다운로드 시도                    */
        	/****************************************************************/
            HttpGet httpget2 = new HttpGet( downUrl );
            
//            for( Header hd : httpget.getAllHeaders()  )
//            	System.out.println( hd );
//            System.out.println();

            // Create a response handler
//            ResponseHandler<String> responseHandler2 = new BasicResponseHandler();
            HttpResponse responseBody2 = httpclient.execute( httpget2 );
            
//            fileName = responseBody2.getLastHeader("content-disposition").toString();
//            fileName = fileName.substring(43, fileName.length()-1 );
//            fileName = new String( fileName.getBytes( "UTF-8") );
//            System.out.println(" **** " + new String( fileName.getBytes( "UTF-8") ) );
//            log.info(" **** " + fileName );
            
            
            // Create file
            File folder = new File( path );
            if( !folder.isDirectory() ) {
            	folder.mkdirs();
            }

            OutputStream os = new FileOutputStream( path + downloadPath );
            InputStream is = responseBody2.getEntity().getContent();
            byte[] buf = new byte[4096];
            int read;
            while ((read = is.read(buf)) != -1) {
                os.write(buf, 0, read);
            }
            os.close();
            
            
          
//            System.out.println("----------------------------------------");
//            System.out.println(responseBody2);
//            System.out.println("----------------------------------------");

        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			log.error( e );
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			log.error( e );
			return null;
		} catch (Exception e) {
			e.printStackTrace();
//			log.error( e );
			return null;
			
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
		return downloadPath + fileName;
	}
	
//	public static void main( String[]  args ) {
//		FileDownloader.download("http://www.anyjava.net/upload/omock/0004.jpg","/Users/sht21c/Downloads/a.jpg", null);
//	}
}
