# Api Gateway

          #id: identity_service (id of microservice)
          #uri: http://localhost:8080 (domain and port of microservice)
          #predicates:
          #- Path=${app.api-prefix}/identity/** (liet ke cac api  se nhan dieu huong tu cong 8888, dau phay "," se phan cach cac api )
          #filters:
          #- StripPrefix=2 (loai bo 2 path content "/path1/path2" cua cong 8888 va so sanh gia tri nhan duoc voi api cua microservice)
          
          
          
 ed:         
          # Path=${app.api-prefix}/profile/users/**, ${app.api-prefix}/profile/internal/users/**          
