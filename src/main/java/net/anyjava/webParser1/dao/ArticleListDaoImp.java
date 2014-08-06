package net.anyjava.webParser1.dao;

import java.util.ArrayList;

import net.anyjava.webParser1.vo.ArticleVO;

public class ArticleListDaoImp extends Dao implements ArticleListDao {

	@Override
	public int insertArticle(ArticleVO vo) {
		int returnId = 0;
		returnId     = this.session.insert("articleList.insert", vo );
		this.session.commit();
		return returnId;
	}

	@Override
	public ArticleVO getArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ArticleVO> getAllArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ArticleVO> getArticle(int cnt, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getArticleMaxId() {
		int maxId = 0;
		maxId = (Integer) this.session.selectOne( "articleList.selectMax" );
		return maxId ;
	}

}
