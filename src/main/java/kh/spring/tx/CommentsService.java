package kh.spring.tx;

import java.util.List;

import org.springframework.stereotype.Component;

import kh.spring.dto.CommentsDTO;


public interface CommentsService {
	public int comments (CommentsDTO cdto);
}
