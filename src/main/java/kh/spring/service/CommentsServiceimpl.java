package kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dao.CommentsDAO;
import kh.spring.dto.CommentsDTO;
import kh.spring.tx.CommentsService;

@Service
public class CommentsServiceimpl implements CommentsService{
	@Autowired
	private CommentsDAO cdao;
	
	@Override
	@Transactional("TxManager")
	public int comments (CommentsDTO cdto) {
		 int result = cdao.insert(cdto);
		 return result;
		
	}

	
	
	
}
