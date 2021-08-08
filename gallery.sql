--테이블 삭제
Drop table gallery;

--시퀀스삭제
DROP SEQUENCE seq_gallery_no;

--갤러리 테이블 생성
create table gallery (
    no number,
    user_no number not null,
    content VARCHAR2(1000),
    filePath VARCHAR2(500),
    orgName VARCHAR2(200),
    saveName VARCHAR2(500),
    fileSize number ,
    PRIMARY KEY (no),
    CONSTRAINT gallery_fk FOREIGN KEY(user_no)
    REFERENCES users(no)
    );
          
--시퀀스 생성
create SEQUENCE seq_gallery_no
INCREMENT by 1
START WITH 1
NOCACHE;

commit;

--업로드
INSERT INTO gallery VALUES (seq_gallery_no.nextval, 1, 'ㅋㅋㅋ', 'ㅋㅋㅋ', 'ㅋㅋㅋ', 'ㅋㅋㅋ', 246);


--출력
select g.no,
       g.user_no,
       g.content,
       g.filepath,
       g.orgname,
       g.savename,
       g.filesize,
       u.name
from gallery g, users u
where g.user_no = u.no;


--갤러리 하나만 가져오기
select g.no,
       g.user_no,
       g.content,
       g.filePath,
       g.orgName,
       g.saveName,
       g.fileSize,
       u.name
from gallery g, users u
where g.user_no = u.no
and g.no = 4;

--갤러리 하나 삭제하기
delete from gallery
where no = 4;