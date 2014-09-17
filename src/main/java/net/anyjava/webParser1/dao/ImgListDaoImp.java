package net.anyjava.webParser1.dao;

import java.util.ArrayList;

import net.anyjava.webParser1.vo.ImgVO;

public class ImgListDaoImp extends Dao implements ImgListDao {

	@Override
	public int insertImg(ImgVO vo) {
		int returnId = 0;
		returnId     = this.session.insert("img.insert1", vo );
		this.session.commit();
		return returnId;
	}

	@Override
	public ImgVO getImg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ImgVO> getAllImg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ImgVO> getAImg(int cnt, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
