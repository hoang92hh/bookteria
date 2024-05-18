# Identity service
This microservice is responsible for:
* Onboarding users
* Roles and permissions
* Authentication

## Tech stack
* Build tool: maven >= 3.9.5
* Java: 21
* Framework: Spring boot 3.2.x
* DBMS: MySQL

## Prerequisites
* Java SDK 21
* A MySQL server

## Start application
`mvn spring-boot:run`

## Build application
`mvn clean package`


#18.05.2024
#sercurity-service to service
khi profile-service duoc thiet lap security, 
cac request tu phia api-gate-way to profile-service se duoc indentiy service authentication,
sau do gate-way se router den api cua profiles-service.
Tuy nhien, cac reques duoc gui tu phia identiy-service to profile-service qua FeignClient thi phia security cua profile-service chan lai
==> Branch nay se thiet lap cach authen va author cho api thong qua feignClient giua cac micro-service.


C1. Truyen token tu phia identiy-service cho profile-service de spring xac thuc.
==> Trong param gui di phai co token dang nhap truoc do, neu khong co token thi se bi bao loi
-> them thuoc tinh "Authorization" :"Bear token"
Them param @RequestHeader String token vao phan mo ta cua FeignClient tuong ung


C2. Su dung interceptor de 2 mircoservice co the trao doi voi nhau, 
ma khong phai gui token qua moi method su dung (gay lap code)
+ Tao mot interceptor implements tu RequestInterceptor
+ Override lai method apply(), trong ta se  setting header for RequestTemplate.header
+ Danh dau interceptor nay thanh mot bean de co the su dung instance toi da.

C3, Khong danh dau bean, ma khai bao customer RequestInterceptor cho  config FeignClient 
+ them thuoc tinh configuration ={AuthenticationRequestInterceptor.class}

Diem khac nhau giua cach 2 va cach 3
+ neu khong khai bao cu the Interceptor cho tung FeignClient ( internal Globlal)
 ->thi no se su dung all bean (2,3,4) la con cua RequestInterceptor
+ Khi khai bao cu the Interceptor cho FeignClient thi  no chi su dung this interceptor
+ ngoai ra trong mot vai truong hop can ket noi toi cac service ngoai bo so voi  (external service)
+ cach 3 duoc su dung hieu qua hon
 





