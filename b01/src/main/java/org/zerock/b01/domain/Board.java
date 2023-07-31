package org.zerock.b01.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false)  // 칼럼의 길이와 null 허용 여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }

    
    // Board 에서도 BoardImage 참조를 가지는 형태
    @OneToMany(mappedBy = "board",
            cascade = {CascadeType.ALL},  // Board 엔티티 객체의 모든 상태 변화에 BoardImage 객체들 역시 같이 변경되도록 구성
            fetch = FetchType.LAZY,  // BoardImage의 board 변수

            orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)  // 쿼리를 모아서 한 번에 실행
    private Set<BoardImage> imageSet = new HashSet<>();

    // 별도의 JPARepository를 생성하지 않고 Board 엔티티에 하위 엔티티 객체들을 관리하는 기능 추가
    // Board 객체 자체에서 BoardImage 객체들을 관리하도록 addImage()와 clearImages()를 이용해서 Board 내에서 BoardImage 객체들을 모두 관리
    public void addImage(String uuid, String fileName) {

        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }

    // 첨부파일들을 모두 삭제하므로 BoardImage 객체의 Board 참조를 null로 변경하게 함
    public void clearImages() {

        imageSet.forEach(boardImage -> boardImage.changeBoard(null));

        this.imageSet.clear();
    }
}
