명령어
===

### /책잠금 [플레이어 목록] (/책잠금 Blank_In Test1 Test2 ...)
플레이어를 적지 않으면 본인을 잠금 목록에 추가함
플레이어를 여럿 적을 때 본인을 적지 않으면 본인은 제외됨
목록에 적은 플레이어가 전부 존재하지 않는 플레이어라면 잠금 되지않음

### /책잠금해제
들고있는 책에 권한이 있다면 잠금을 해제함

### /책다시쓰기
들고있는 책이 서명되어 있고 권한이 있다면 책과 깃펜으로 되돌림

### /blankinmusic
버전 확인


권한
=
BlankInBook.op.*
- BlankInBook.blankinbook

BlankInBook.user.*
- BlankInBook.user.책잠금 
- BlankInBook.user.책잠금해제
- BlankInBook.user.책다시쓰기


# 유의사항
권한에 BlankInBook.blankinbook이 포함되어 있거나 OP라면 잠금되어 있는 책을 자유롭게 열람/잠금해제/다시쓰기 할 수 있음

잠금에 포함되지 않은 자는 잠금된 책을 독서대에 올릴 수 없지만 타인이 올려놓은 책을 독서대에서 읽거나 가져갈 수는 있음

책의 복사본에도 잠금 NBT 태그가 유지되는 것을 확인하였음

책이 열리는건 서버에서의 계산이 아닌 클라이언트에서의 계산이어서 서버는 책이 열린 걸 감지해서 강제로 닫도록 처리하고 있음
책과 깃펜의 경우 서버에서의 처리과정에서 잠깐동안 내용이 보일 수 있음 서명된 책에 잠금 하는 것을 권장함
