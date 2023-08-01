package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")  // 연관 관계 적용
public class BoardImage implements Comparable<BoardImage> {
    // 첨부파일 = BoardImage
    // Comparable 인터페이스를 적용하는 이유는 @OneToMany 처리에서 순번에 맞게 정렬하기 위함

    @Id
    private String uuid;  // 첨부파일의 고유한 uuid값

    private String fileName;

    private int ord;  // 파일 순번

    @ManyToOne
    private Board board;


    @Override
    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }

    public void changeBoard(Board board){
        this.board = board;
    }
    // Board 객체를 나중에 지정 가능
    // 나중에 Board 엔티티 삭제 시에 BoardImage 객체의 참조도 변경하기 위해 사용

}
