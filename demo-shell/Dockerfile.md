##### Mysql

> FROM mysql:5.7
>
> ENV MYSQL_ALLOW_EMPTY_PASSWORD yes
>
> LABEL name=lowcode:0.0.1
>
> COPY db.sh /mysql/
> COPY *.sql /mysql/
>
> RUN chmod -R 777 /mysql/
>
> CMD ["sh", "/mysql/db.sh"]

##### CMD

docker build -t mysql:0.0.1 . && docker run --name mysql-dockers -p 10001:3306 -d lowcode:0.0.1
