FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR app
COPY ./target/*.jar financial_stat.jar
EXPOSE 8500
RUN java -Djarmode=layertools -jar financial_stat.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR app
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]
