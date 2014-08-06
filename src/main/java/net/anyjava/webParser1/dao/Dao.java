package net.anyjava.webParser1.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class Dao {

	protected SqlSession session;
	protected Logger log = Logger.getLogger( Dao.class );
	
	public Dao() {
		try {
			log.info("==== MyBatis Config LOADING Start! ====");
			URL xmlResource = getClass().getResource("/Configure-mybatis.xml"); 
			log.debug( "-- Mybatis XML FileName : =[" + xmlResource.getPath() + "]" );
			File xmlFile = new File(xmlResource.getPath()); // ...
			
			Reader reader = new InputStreamReader( new FileInputStream( xmlFile ) );
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();
			log.info("==== MyBatis Config LOADING End! ====");

		}
		catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			
			System.exit(-1);
		}
	}
	
	public void close() {
		session.close();
	}
	
	public void test() {
		
		int a = 1;
		int result = 0;
		result = (Integer) session.selectOne( "test.select", a );
		
		System.out.println( result );
	}
	
	
	
	
	public static void main(String[] args) {
		Dao dao = new Dao();
		dao.test();
	}



}


