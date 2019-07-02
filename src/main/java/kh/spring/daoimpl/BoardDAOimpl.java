package kh.spring.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAOimpl implements BoardDAO{

	@Autowired
	private JdbcTemplate template;

	static int recordCountPerPage = 5;// 한 페이지에 보여줄 글 개수
	static int naviCountPerPage = 5;// 한 페이지에 보여줄 페이지 번호 수 

	@Override
	public int insert(BoardDTO dto) {
		String sql = "insert into board values(board_seq.nextval,?,?,?,?,default,default)";
		return template.update(sql, new Object[] 
				{dto.getTitle(), dto.getContents(), dto.getImg(), dto.getWriter()});
	}
	@Override
	public List<BoardDTO> selectAll() {
		String sql = "select * from board";
		return template.query(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setWriter(rs.getString("writer"));
				dto.setWriteDate(rs.getTimestamp("writeDate"));
				dto.setViewCount(rs.getInt("viewCount"));
				return dto;
			}
		});

	}
	@Override
	public int recordCount() {
		String sql = "select count(*)as count from board";
		int result = template.queryForObject(sql,Integer.class);
		return result;
	}

	@Override
	public String getNavi(int currentPage) {
		int recordTotalCount = this.recordCount(); //전체 글 갯수

		int pageTotalCount = 0;//전체 페이지 수(한 페이지에 보여줄 페이지네이게이터 번호 수)
		if(recordTotalCount % recordCountPerPage > 0) {// 전체 글 갯수 / 한 페이지에 보여줄 글 갯수 > 0 : 
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;//나머지가 있으면 한페이지 더 필요
		}else if(recordTotalCount % recordCountPerPage == 0){
			pageTotalCount = recordTotalCount/recordCountPerPage;
		}
		//보안코드
		if(currentPage < 1) {// 현재 페이지가 최소 페이지보다 작으면 현재페이지는 1 -> 0, -1 페이지는 없으니까
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {// 현재페이지가 전체 페이지 보다 크면 현재 페이지는 전체페이지번호 
			currentPage = pageTotalCount;
		}

		//내 위치의 기준으로 첫페이지와 끝페이지 알아내기
		int startNavi = (currentPage -1)/naviCountPerPage*naviCountPerPage + 1;
		int endNavi = startNavi + (naviCountPerPage - 1);
		//ex) startNavi: 만약 14 페이지에 있다면 첫 번호는 11 ->14/10 = 1(몫) -> 1*10 = 10 -> 10+1=11 끝 번호는 20
		//10 20 30..페이지일 경우 : (currentPage - 1)/10*10+1 이 공식은 다 적용!!!!!

		//최대 페이지  번호보다 endNavi 번호가 크게 나올 경우
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;//끝번호 = 전체 페이지 번호
		}

		boolean needPrev = true; // 이전 버튼
		boolean needNext = true; // 다음 버튼

		if(startNavi == 1) { // 시작페이지가 1일 경우 이전 버튼 필요 없음
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {// 끝 번호가 전체페이지 번호(끝번호)일경우 다음 버튼 필요 없음
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href='toBaordList?currentPage="+(startNavi -1) + "'><이전 </a>");
		}
		for(int i = startNavi; i <= endNavi; i++) {
			sb.append("<a class='pageNum' href='toBaordList?currentPage=" + i +"'> " + i + " </a>");
		}
		if(needNext) {
			sb.append("<a href='toBaordList?currentPage="+(endNavi + 1)+"'> 다음></a>");
		}
		return sb.toString();
	}
	@Override
	public List<BoardDTO> selectByPage(int currentPage) {
		String sql = "select * from (select row_number()over(order by seq desc) as rown, board.*from board) where rown between ? and ?";
		int endNum = currentPage * recordCountPerPage;
		int startNum = endNum - 4;
		return template.query(sql, new Object[] {startNum, endNum}, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setWriter(rs.getString("writer"));
				dto.setWriteDate(rs.getTimestamp("writeDate"));
				dto.setViewCount(rs.getInt("viewCount"));
				return dto;
			}
		}
				);
	}
	@Override
	public BoardDTO content(int seq){
		String sql = "select * from board where seq = ?";
		return template.queryForObject(sql, new Object[] {seq}, new RowMapper<BoardDTO>(){
			@Override
			public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setWriter(rs.getString("writer"));
				dto.setWriteDate(rs.getTimestamp("writeDate"));
				dto.setViewCount(rs.getInt("viewCount"));
				return dto;
			}
		});
		
	}
	@Override
	public int deletePost(int seq) {
		String sql = "delete from board where seq = ?";
		return template.update(sql, new Object[] {seq});
	}
	@Override
	public int viewCount(int seq) {
		String sql = "update board set viewCount = viewCount+1 where seq = ?";
		return template.update(sql,new Object[] {seq});
	}
	//------검색기능-----------------------------------------------------------------------------------
	@Override
	public int searchRecordCount(String opt, String searchWord) {
		String sql = "select count(*)as count from board where "+opt+" like ?";
		return template.queryForObject(sql, new Object[] {"%"+searchWord+"%"} ,Integer.class);
	}
	@Override
	public List<BoardDTO> searchSelectByPage(int currentPage, String opt, String searchWord) {
		String sql = "select * from (select row_number()over(order by seq desc) as rown, board.*from board where " + opt + " like  ? ) where rown between ? and ?";
		int endNum = currentPage * recordCountPerPage;
		int startNum = endNum - 4;
		return template.query(sql, new Object[] {"%"+searchWord+"%", startNum, endNum}, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rn) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setImg(rs.getString("img"));
				dto.setWriter(rs.getString("writer"));
				dto.setWriteDate(rs.getTimestamp("writeDate"));
				dto.setViewCount(rs.getInt("viewCount"));
				return dto;
			}
		}
				);
	}
	@Override
	public String getSearchNavi(int currentPage , String opt , String searchWord) {
		int recordTotalCount = this.searchRecordCount(opt, searchWord); //전체 글 갯수

		int pageTotalCount = 0;//전체 페이지 수(한 페이지에 보여줄 페이지네이게이터 번호 수)
		if(recordTotalCount % recordCountPerPage > 0) {// 전체 글 갯수 / 한 페이지에 보여줄 글 갯수 > 0 : 
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;//나머지가 있으면 한페이지 더 필요
		}else if(recordTotalCount % recordCountPerPage == 0){
			pageTotalCount = recordTotalCount/recordCountPerPage;
		}
		//보안코드
		if(currentPage < 1) {// 현재 페이지가 최소 페이지보다 작으면 현재페이지는 1 -> 0, -1 페이지는 없으니까
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {// 현재페이지가 전체 페이지 보다 크면 현재 페이지는 전체페이지번호 
			currentPage = pageTotalCount;
		}

		//내 위치의 기준으로 첫페이지와 끝페이지 알아내기
		int startNavi = (currentPage -1)/naviCountPerPage*naviCountPerPage + 1;
		int endNavi = startNavi + (naviCountPerPage - 1);
		//ex) startNavi: 만약 14 페이지에 있다면 첫 번호는 11 ->14/10 = 1(몫) -> 1*10 = 10 -> 10+1=11 끝 번호는 20
		//10 20 30..페이지일 경우 : (currentPage - 1)/10*10+1 이 공식은 다 적용!!!!!

		//최대 페이지  번호보다 endNavi 번호가 크게 나올 경우
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;//끝번호 = 전체 페이지 번호
		}

		boolean needPrev = true; // 이전 버튼
		boolean needNext = true; // 다음 버튼

		if(startNavi == 1) { // 시작페이지가 1일 경우 이전 버튼 필요 없음
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {// 끝 번호가 전체페이지 번호(끝번호)일경우 다음 버튼 필요 없음
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if(needPrev) {
			sb.append("<a href='searchPost?opt="+opt+"&&searchWord="+searchWord+"&&searchPage="+(startNavi -1) + "'><이전 </a>");
		}
		for(int i = startNavi; i <= endNavi; i++) {
			sb.append("<a class='pageNum' href='searchPost?opt="+opt+"&&searchWord="+searchWord+"&&searchPage=" + i +"'> " + i + " </a>");
		}
		if(needNext) {
			sb.append("<a href='searchPost?opt="+opt+"&&searchWord="+searchWord+"&&searchPage="+(endNavi + 1)+"'> 다음></a>");
		}
		return sb.toString();
	}
	@Override
	public int alterContent1(int seq, String title, String content) {
		String sql = "update board set title = ?, contents = ? where seq = ?"; 
		int result = template.update(sql, new Object[] {title, content, seq});
		return result;
	}
	@Override
	public int alterContent2(int seq, String title, String content, String img) {
		String sql = "update board set title = ?, contents = ?, img = ? where seq = ?"; 
		int result = template.update(sql, new Object[] {title, content, img, seq});
		return result;
	}

}
