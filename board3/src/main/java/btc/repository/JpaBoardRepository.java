package btc.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import btc.entity.BoardEntity;
import btc.entity.BoardFileEntity;

// 스프링 데이터 JPA에서 제공하는 CrudRepository 인터페이스를 상속받음
public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer> {
	
//	게시글 번호를 정렬하여 전체 게시글을 조회
//	메서드 조합 규칙에 맞도록 메서드를 조합하여 추가하면 실행 시 메서드의 이름에 따라 쿼리가 자동 생성됨
	
	List<BoardEntity> findAllByOrderByBoardIdxDesc();
	
//	@Query 어노테이션을 사용하여 SQL 쿼리를 직접 정의할 수 있음
	@Query("SELECT file FROM BoardFileEntity file WHERE board_idx = :boardIdx And idx = :idx")
	BoardFileEntity findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);
}

// 스프링데이터 JPA 리포지토리 인터페이스는 Repository<T, ID> 인터페이스가 최상위에 있고 그 다음에 CrudRepository<T, ID> 인터페이스가 존재함
// CrudRepository를 상속받는 PagingAndSortingRepository<T, ID> 인터페이스가 존재함
// 마지막으로 PagingAndSortingRepository를 상속받는 JpaRepository<T, ID>가 존재함

// 최상위의 Repository 인터페이스는 아무런 기능이 없음
// CrudRepository 인터페이스는 CRUD 기능을 기본적으로 제공함
// CRUD에 해당하는 기능을 작성하지 않더라도 인터페이스에서 제공되는 기능을 바로 사용할 수 있음

// CrudRepository가 제공하는 기본 메서드
// <S extends T> S save(S entity) : 주어진 엔티티를 저장
// <S extends T Iterable<S> saveAll(Iterable<S> entitlies) : 주어진 엔티티 목록을 저장

//	쿼리 메소드
//	스프링 데이터 JPA는 규칙에 맞게 메소드를 추가하면 그 메소드 이름으로 쿼리를 생성하는 기능을 제공합니다.
//	• 쿼리 메소드는 find By, read..By, query By, count By, get By로 시작해야 합니다.
//	· 첫 번째 By 뒤쪽은 컬럼 이름으로 구성됩니다. 즉, 첫 By는 쿼리의 검색조건이 됩니다.
//	• 예를 들어, BoardEntity의 제목으로 검색하려면 find ByTitle(String title)과 같이 작성해야 합니다.
//	. findByTitle(String title) 메소드가 실행되면 다음과 같은 JPQL(Java Persistence Query Language) 문이 실행됩니
//	다.
//	• select jpaboard0_.board_idx as board_id1_0. ... from t_jpa_board jpaboardo_ where jpaboard0_.title=?
//	· 두개 이상의 속성을 조합하려면 And 키워드를 사용하면 됩니다. 제목과 내용으로 검색하려면 아래와 같이 메소드를 작성하면 됩니다.
//	• findByTitleAndContents(String title, String contents)