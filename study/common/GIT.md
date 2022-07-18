# GIT 

### 기본설정

git flow init -d

→ error 뜰 시, git stash 후에 다시 git flow init -d



각자가 기능을 만들 때

ex) OAuth2기능을 만들고 싶다.



```
git flow feature start OAuth2
git flow feature publish OAuth2

하게 되면 feature/OAuth2 라는 이름으로 브랜치가 생성됨

그리고 개발을 하다가
중간 완료가 된다면,

git add .
git commit -m "커밋 메세지"
git push origin feature/OAuth2

위 명령어를 통하여 중간 저장이 가능하며

최종본이 완성되면 코드리뷰를 통하여 문제가 없음이 확인된다면
git flow feature finish OAuth2

명령어를 사용하여 만들었던 feature/OAuth2 를 develop 브랜치로 merge 한 후,

git add .
git commit -m "커밋 메세지"
git push origin develop
명령어를 사용하여 merge된 develop 변경사항을 push하여 원격 저장소에 저장

그 후,
기능 개발이 끝난 다음 master에 request merge를 보내고
코드 리뷰로 오류가 없음을 확인
마지막으로 우빈이가 merge 승인 하면
최신 코드인 develop에 있던 코드들이 master로 merge가 됨.
```



### 추가

git branch <branch name> :  해당 이름으로 branch 생성

git branch -v : 로컬 branch를 최종 커밋내역과 함께 확인

git branch -r : 리모트 branch를 확인

git branch -a  :  모든 branch 확인

git checkout origin <branch name> : 해당 branch로 이동



리모트 branch를 가져오고 싶을 때

```
[특정이름 지정시]
$ git checkout -b work2 origin/work2
[원격 branch 이름을 그대로 사용할 경우]
$ git checkout -t origin/work2
```

***\*git branch -m (변경할 branch이름) (변경될 branch이름) : branch 이름 변경\****





- git checkout -t
  - 원격저장소에 있는 브랜치를 내 로컬로 끌고 오면서 로컬에도 브랜치 생성
  - 같은 이름으로 생성



