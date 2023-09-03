
## [Spring Initializr](https://start.spring.io/)

### Dependency :
- Spring Web
- Spring Data Couchbase
- Lombok
___
### Project Details:
1) Couchbase (DB) Port : 8091
2) Java Version used 11
3) Spring Boot 2.7.15
___
## API Requests:

### Register Request
#### [ POST ] : http://localhost:8083/api/user/register
```
{
    "username": "M7mdSsed",
    "email":"user123@mail.com",
    "password":"123456"
}
```
___
### Login Request
#### [ POST ] : http://localhost:8083/api/v1/user/login
```
{
    "username":"M7mdSsed",
    "password":"123456"
}
```
___
### Return All Users Request
#### [ GET ] : http://localhost:8083/api/v1/user/getAllUsers
___
### Delete User By ID Request
#### [ DEL ] : http://localhost:8083/api/v1/user/delete/f4ee1186-e240-4d10-9d74-b4a5d537a5ed
___
### Find By Email (Text) Request
#### [POST] : http://localhost:8083/api/v1/user/find
```
mohamedsayedahmd@mail.com
```
___