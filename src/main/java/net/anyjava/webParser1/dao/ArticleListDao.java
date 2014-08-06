package net.anyjava.webParser1.dao;

import java.util.ArrayList;

import net.anyjava.webParser1.vo.ArticleVO;

public interface ArticleListDao {

	int insertArticle( ArticleVO vo );
	int getArticleMaxId();
	ArticleVO getArticle();
	ArrayList<ArticleVO> getAllArticle();
	ArrayList<ArticleVO> getArticle( int cnt, int limit );
}
