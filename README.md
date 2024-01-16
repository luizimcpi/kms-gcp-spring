1 - https://cloud.google.com/sdk/docs/install?hl=pt-br

2 - https://cloud.google.com/kms/docs/create-encryption-keys?hl=pt-br

3 - Create credentials file*
```
gcloud auth application-default login
```

4- Criar service account with kms access (crypt and decrypt)
https://cloud.google.com/iam/docs/keys-create-delete?hl=pt-br#creating
```
generate base64 string of json key file downloaded and paste in application.properties
spring.cloud.gcp.credentials.encoded-key
```

5 - Start MongoDB
```
docker-compose up 

create ``secrets`` database using mongoDB compass or shell
```

6 - Start Application Running KmsGcpApplication.java

7 - Use VSCode plugin Rest Client to do requests using api.http file 