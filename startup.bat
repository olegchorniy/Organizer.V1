start java -jar service-registry\target\service-registry-0.0.1-SNAPSHOT.jar
timeout 3 > NUL

start java -jar user-service\target\user-service-0.0.1-SNAPSHOT.jar

REM Start with externally configured Eureka server URL.
REM java -jar -Deureka.client.service_url.defaultZone=http://localhost:8761/eureka/ user-service\target\user-service-0.0.1-SNAPSHOT.jar

start mongod --port 27111 --dbpath D:\servers\mongodb_data_directory\notes_db_sa
timeout 2 > NUL

start java -jar notes-service\target\notes-service-0.0.1-SNAPSHOT.jar

start java -jar frontend\target\frontend-0.0.1-SNAPSHOT.jar