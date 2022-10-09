package com.everytime.clipping.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.clipping.dao.ClippingDAO;

@Service
public class ClippingBO {

	@Autowired
	private ClippingDAO clippingDAO;

	public void clipping(int boardId, int postId, int userId) {
		if (clippingDAO.existClippingByBoardIdAndPostIdAndUserId(boardId, postId, userId) == false) {
			// 좋아요
			clippingDAO.insertClipping(boardId, postId, userId);
		} else {
			// 해제
			clippingDAO.deleteClippingByBoardIdAndPostIdAndUserId(boardId, postId, userId);
		}
	}

	public int getClippingCountByPostId(int postId) {
		return clippingDAO.selectClippingCountByPostId(postId);
	}

}
