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



