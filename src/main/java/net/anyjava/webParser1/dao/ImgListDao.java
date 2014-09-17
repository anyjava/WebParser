package net.anyjava.webParser1.dao;

import java.util.ArrayList;

import net.anyjava.webParser1.vo.ImgVO;

public interface ImgListDao {

	int insertImg( ImgVO vo );
	ImgVO getImg();
	ArrayList<ImgVO> getAllImg();
	ArrayList<ImgVO> getAImg( int cnt, int limit );
}
