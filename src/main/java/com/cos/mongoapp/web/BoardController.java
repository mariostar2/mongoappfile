package com.cos.mongoapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mongoapp.domain.Board;
import com.cos.mongoapp.domain.BoardRepository;
import com.cos.mongoapp.web.dto.BoardSaveDto;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController //데이터 리턴 서버 
public class BoardController {

	//DI
	private final BoardRepository boardRepository;
	
	@PutMapping("/board/{id}")
	public void update(@RequestBody BoardSaveDto dto ) {
		
		Board board = dto.toEntity();
		board.set_id(null);
		
		boardRepository.save(null);
	}
	
	@DeleteMapping("/board/{id}")
	public int deleteByid(@PathVariable String id) {
		boardRepository.deleteById(id); //내부적으로 실행되다가 오류 발생하면 Exception 발동 
	
		return 1;// 1. 성공 -1실패
	}
	
	
	@GetMapping("/board/{id}")
	public Board findById(@PathVariable String id) {
		return boardRepository.findById(id).get();
	}
	@GetMapping("/board")
	public List<Board> findAll() { //리턴을 javaobject로 하면 스프링 내부적으로 json으로 자동변환해줌
		return boardRepository.findAll();
		
	}
	@PostMapping("/board") //RequestBody => JSON파일을 받음 (object->json)
	public Board save(@RequestBody BoardSaveDto dto) {//	
		Board boardEntity = boardRepository.save(dto.toEntity());
		
		return boardEntity;
	}
	
}
