# Api Gateway

          #id: identity_service (id of microservice)
          #uri: http://localhost:8080 (domain and port of microservice)
          #predicates:
          #- Path=${app.api-prefix}/identity/** (liet ke cac api  se nhan dieu huong tu cong 8888, dau phay "," se phan cach cac api )
          #filters:
          #- StripPrefix=2 (loai bo 2 path content "/path1/path2" cua cong 8888 va so sanh gia tri nhan duoc voi api cua microservice)
          
          
          
 #ed:         
          # Path=${app.api-prefix}/profile/users/**, ${app.api-prefix}/profile/internal/users/**    
          
          
  
  
  #authorization
    + get token from api-gate way, ==> config to lay token, di qua filter khong bi chan
    + edit AuthenticationFilter , "ServerHttpRequest"
    + Matches endpoint of ServerHttpRequest with list EndPoint is declared in API-getway
    + Viec khai bao cac public  end point  co the truoc tiep o AuthenticationFilter, hoac dua ra file yml roi day vao.
    
    + Viec verify token da duoc api-gate way xu ly, dan toi viec verify o identiy-service la khong can thiet.
    ==> can thay doi logic cua JwtDecoder
    
    + Dang ky security cho profile-service  gom CustomerJwtDecoder, SecurityConfig, JwtAuthenticationEntryPoint
    + Phan quyen Authorization cho profile-service
    + Dang ky them cac errorcode va bat exception cho profile-service.
    
    
