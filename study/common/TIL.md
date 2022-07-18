# 공통 Sub1 TIL

## 개발스택 공부

### js불변객체

- const : 객체 재할당은 불가능하지만 객체의 속성은 변경 가능
- Object.freeze() : Object.freeze()는 동결된 객체를 반환하지만 객체의 재할당은 가능

### jpa

- 세팅 : META-INF -> persistence.xml

```jsx
<?xml version="1.0" encoding="UTF-8"?>

<persistence version="2.2"
               xmlns="http://xmlns.jcp.org/xml/ns/persistence"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
                                   http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">

          <persistence-unit name="hello">
      	<properties>
              <!-- 필수 속성 -->
              <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
              <property name="javax.persistence.jdbc.user" value="sa"/>
              <property name="javax.persistence.jdbc.password" value=""/>
              <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>
              <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
              <!-- 옵션 -->
              <property name="hibernate.show_sql" value="true"/>
              <property name="hibernate.format_sql" value="true"/>
              <property name="hibernate.use_sql_comments" value="true"/>
              <!--<property name="hibernate.hbm2ddl.auto" value="create" />-->
      	</properties>
      </persistence-unit>

  </persistence>
```

- CRUD

```jsx
 //CREATE
        Member m = new Member();
        m.setId(1L);
        m.setName("김아무개");
        em.persist(m);

      //READ
        Member fm = em.find(Member.class, 1L);

      //UPDATE
        Member um = em.find(Member.class, 1L);
        um.setName("이아무개");

      //DELETE
        Member dm = em.find(Member.class, 1L);
        em.remove(dm);
```

     

- jpa 성능최적화
1. 1차캐시와 동일성 보장

```jsx
String memberId = "100";
Member m1 = jpa.find(Member.class, memberId); // SQL
Member m2 = jpa.find(Member.class, memberId); // 캐시 (SQL 1번만 실행, m1을 가져옴)
println(m1 == m2) // true
```

→ sql을 한번만 실행한다.

1. 쓰기지연(버퍼링)
- 트랜잭션을 커밋할 때까지 INSERT SQL을 모음

```jsx
transaction.begin(); // [트랜잭션] 시작
em.persist(memberA);
em.persist(memberB);
em.persist(memberC);
// 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
// 커밋하는 순간 데이터베이스에 INSERT SQL을 모아서 보낸다.
```

- JDBC BATCH SQL 기능을 사용해서 한번에 SQL 전송

```jsx
transaction.commit(); // [트랜잭션] 커밋
```

→ 트랜잭션 커밋전까지 sql문을 메모리에 쌓음

## 롬복

- 내부적으로 DTO와 같은 모델에서 변수를 지정하면 자바 클래스로 만들 때 setter, getter, toString 등의 메소드를 자동으로 만들어주는 기능

```jsx
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BoardCategoryLombok {

    private String category_id;

    private String category_name;

    private Date regdate;

    private int board_cnt;

    private int seq;

}
```

```jsx
@Data
public class BoardCategoryLombok {

    private String category_id;

    private String category_name;

    private Date regdate;

    private int board_cnt;

    private int seq;

}
```

→ getter, setter, Tostring, 생성자 등의 기능이 구현된 어노테이션들을 사용할 수 있고, 이들 전체를 @data어노테이션으로 줄여 사용할 수 있다.

## Vue3(vs Vue2)

- 아무 기능이 없는 root엘리먼트를 없앨 수 있다.
- data와 method의 작성방식의 변화
    - props와 data, method가 같은 계층이었는데 이제 props와 setup을 같이두고 data와 method를 그 아래계층으로 편입시킴
    - state → vue reactive

```jsx
// Vue2.x
    export default {
      props: {
        title: String
      },
      data () {
        return {
          username: '',
          password: ''
        }
      },
      methods: {
        login () {
          // login method
        }
      }
    }

// Vue 3.x
    export default {
      props: {
        title: String
      },
      setup () {
        const state = reactive({
          username: '',
          password: ''
        })
    
        const login = () => {
          // login method
        }
        return { 
          login,
          state
        }
      }
    }
```

- 라이프사이클의 변화
    - mount, update같은 라이프사이클 역시 setup안으로 편입
- computed의 변화
    - setup내로 편입됐으며, state선언문 내에서 선언가능

```jsx
//vue2
export default {
      // .. 
      computed: {
        lowerCaseUsername () {
          return this.username.toLowerCase()
        }
      }
    }

//vue3
import { reactive, computed } from 'vue'
    
    export default {
      props: {
        title: String
      },
      setup () {
        const state = reactive({
          username: '',
          password: '',
          lowerCaseUsername: computed(() => state.username.toLowerCase())
        })
    
        // ...
      }
```

- Composition API
    - 컴포넌트를 생성하고 조합하는 API

```jsx
//vue의 핵심기능을 전부 import
import { reactive, computed } from 'vue'
    
		export default {
      props: {
        title: String
      },

			//composition api의 핵심
			//lifecycle hook, 데이터바인딩, computed 사용가능 -> 마지막에는 return
      setup () {
        const state = reactive({
          username: '',
          password: '',
          lowerCaseUsername: computed(() => state.username.toLowerCase())
        })
    
				//컴포넌트 내에서 데이터 사용할 때 state를 선언하고 reactive object로 초기화
    		const changeName = (name) => {
    			state.username = name
    		}
      }
```

- props와 this의 분리
    - this로 props, method, data를 모두 바인딩했기 때문에 이를 분리함.
    
    ```jsx
    //vue2
    mounted () {
            console.log('title: ' + this.title)
        }
    
    //vue3
    //props를 setup의 매개변수로 받고 이를 바인딩하여 호출
    setup (props) {
            onMounted(() => {
              console.log('title: ' + props.title)
            })
        }
    ```
    
- emit과 this의 분리
    - props와 같이 setup의 매개변수로 호출
    
    ```jsx
    //vue2
    login () {
              this.$emit('login', {
                username: this.username,
                password: this.password
              })
         }
    
    //vue3
    //props를 setup의 매개변수로 받고 이를 바인딩하여 호출
    setup (props, { emit }) {
    
            const login = () => {
              emit('login', {
                username: state.username,
                password: state.password
              })
            }
    
        }
    ```