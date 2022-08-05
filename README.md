# OAuth2 Practice

#### Authorization
![Screenshot 2022-08-05 at 08 53 57](https://user-images.githubusercontent.com/27693622/183030126-d9028293-ba34-4f38-9c62-336b8bd234e7.png)

```
curl --location --request POST 'localhost:8080/oauth/token' \
--header 'Authorization: Basic amF2YWludXNlLWNsaWVudDpqYXZhaW51c2Utc2VjcmV0' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials'
```

Also you could do this curl:
```
curl --user 'javainuse-client:javainuse-secret' --data-urlencode 'grant_type=client_credentials' --request POST 'localhost:8080/oauth/token'
{"access_token":"f7c62af0-a0bb-44d7-805c-6ce25ddde53b","token_type":"bearer","expires_in":25567,"scope":"resource-server-read resource-server-write"}⏎
```

#### Response from authorization
```
{
"access_token": "3a337e31-e725-4d60-aed5-b744625321ae",
"token_type": "bearer",
"expires_in": 42833,
"scope": "resource-server-read resource-server-write"
}
```

#### Resource
![Screenshot 2022-08-05 at 08 54 06](https://user-images.githubusercontent.com/27693622/183030024-a2d04704-1e91-4ac2-884a-22e585b5a16c.png)

```
curl --location --request GET 'localhost:9090/test' \
--header 'Authorization: Bearer 3a337e31-e725-4d60-aed5-b744625321ae'
```

##### Diagram
```
+--------+                                           +---------------+
|        |--(A)------- Authorization Grant --------->|               |
|        |                                           |               |
|        |<-(B)----------- Access Token -------------|               |
|        |               & Refresh Token             |               |
|        |                                           |               |
|        |                            +----------+   |               |
|        |--(C)---- Access Token ---->|          |   |               |
|        |                            |          |   |               |
|        |<-(D)- Protected Resource --| Resource |   | Authorization |
| Client |                            |  Server  |   |     Server    |
|        |--(E)---- Access Token ---->|          |   |               |
|        |                            |          |   |               |
|        |<-(F)- Invalid Token Error -|          |   |               |
|        |                            +----------+   |               |
|        |                                           |               |
|        |--(G)----------- Refresh Token ----------->|               |
|        |                                           |               |
|        |<-(H)----------- Access Token -------------|               |
+--------+           & Optional Refresh Token        +---------------+
```
