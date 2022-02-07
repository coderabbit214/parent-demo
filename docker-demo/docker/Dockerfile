FROM java:8

COPY demo-0.0.1-SNAPSHOT.jar /app.jar

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]