1 - https://cloud.google.com/sdk/docs/install?hl=pt-br

2 - https://cloud.google.com/kms/docs/create-encryption-keys?hl=pt-br

3 - Create credentials file
```
gcloud auth application-default login
```

4 - Start MongoDB
```
docker-compose up 

create ``secrets`` database using mongoDB compass or shell
```

5 - Start Application Running KmsGcpApplication.java

6 - Use VSCode plugin Rest Client to do requests using api.http file 